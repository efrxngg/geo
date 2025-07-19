package uees.project.geo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@SpringBootApplication
public class GeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeoApplication.class, args);
    }

}