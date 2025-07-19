package uees.project.geo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import uees.project.geo.repository.HistoricalSimulationRepositoryPostgre;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class QueryYearsOfExtremeEvents {

    private final HistoricalSimulationRepositoryPostgre repository;

    public List<Integer> call() {
        log.info("Calling HistoricalSimulationRepository");
        return repository.findAvailableYears();
    }
}
