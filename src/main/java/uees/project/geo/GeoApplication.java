package uees.project.geo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uees.project.geo.repository.DrainageNetworkRepository;

import java.time.LocalDateTime;

@Log4j2
@RestController
@SpringBootApplication
public class GeoApplication {

    private final DrainageNetworkRepository drainageNetworkRepository;

    public GeoApplication(DrainageNetworkRepository drainageNetworkRepository) {
        this.drainageNetworkRepository = drainageNetworkRepository;
    }

    @RequestMapping("/")
    String home() {
        LocalDateTime startDate = LocalDateTime.of(1900, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2100, 12, 31, 23, 59);

        log.info(drainageNetworkRepository.findExtremeEvents(startDate, endDate));
        return "Hello World 3frxngg1!";
    }

    public static void main(String[] args) {
        SpringApplication.run(GeoApplication.class, args);
    }

}