package uees.project.geo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uees.project.geo.dto.ExtremeEventResponse;
import uees.project.geo.entity.ExtremeEventResult;
import uees.project.geo.service.QueryExtremeEvents;
import uees.project.geo.service.QueryYearsOfExtremeEvents;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequestMapping("geo")
@RequiredArgsConstructor
public class GeoRestController {
    private final QueryExtremeEvents queryExtremeEventsService;
    private final QueryYearsOfExtremeEvents queryYearsOfExtremeEvents;

    @GetMapping("/extreme-events")
    public ResponseEntity<?> getExtremeEvents(
            @RequestParam(name = "from", required = false) String startDate,
            @RequestParam(name = "to", required = false) String endDate) {
        log.info("GET /extreme-events from {} to {}", startDate, endDate);

        List<ExtremeEventResponse> call = queryExtremeEventsService.call(startDate, endDate);

        return ok().body(call);
    }

    @GetMapping("/available-years")
    public ResponseEntity<List<Integer>> getAvailableYears() {
        log.info("GET /available-years");
        List<Integer> years = queryYearsOfExtremeEvents.call();
        return ok().body(years);
    }

}
