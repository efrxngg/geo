package uees.project.geo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uees.project.geo.dto.ExtremeEventResponse;
import uees.project.geo.repository.DrainageNetworkRepositoryPostgres;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class QueryExtremeEvents {

    private final DrainageNetworkRepositoryPostgres repository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Cacheable(value = "extremeEvents", key = "{#p0,#p1}")
    public List<ExtremeEventResponse> call(String startDate, String endDate) {
        log.info("Calling HistoricalSimulationRepository from: {} to {}", startDate, endDate);
        startDate = ensureDate(startDate, "1900-01-01");
        endDate = ensureDate(endDate, "2100-01-12");

        try {
            // Parsear a LocalDate primero
            LocalDate startLocalDate = LocalDate.parse(startDate, DATE_FORMATTER);
            LocalDate endLocalDate = LocalDate.parse(endDate, DATE_FORMATTER);

            // Convertir a LocalDateTime añadiendo tiempo inicial y final del día
            LocalDateTime start = LocalDateTime.of(startLocalDate.getYear(), startLocalDate.getMonth(), startLocalDate.getDayOfMonth(), 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(endLocalDate.getYear(), endLocalDate.getMonth(), endLocalDate.getDayOfMonth(), 0, 0, 0);

            if (end.isBefore(start)) {
                throw new IllegalArgumentException("La fecha final no puede ser anterior a la fecha inicial");
            }
            log.info("call findExtremeEvents from: {} to {}", startDate, endDate);
            return repository.findExtremeEvents(start, end)
                    .stream()
                    .map(r -> ExtremeEventResponse
                            .builder()
                            .comid(r.getComid())
                            .latitude(r.getLatitude())
                            .longitude(r.getLongitude())
                            .location2(r.getLocation2())
                            .riesgo(evaluateRiesgo(r.getEventosExtremos()))
                            .build())
                    .toList();

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use el formato ISO (yyyy-MM-dd'T'HH:mm:ss)", e);
        }
    }

    private static String ensureDate(String date, String defaultDate) {
        if (date == null || date.isEmpty()) {
            date = defaultDate;
        }
        return date;
    }

    private String evaluateRiesgo(Long eventosExtremos) {
        if (eventosExtremos == null) {
            return "Sin datos";
        } else if (eventosExtremos <= 5) {
            return "Bajo";
        } else if (eventosExtremos <= 20) {
            return "Medio";
        } else {
            return "Alto";
        }
    }

}
