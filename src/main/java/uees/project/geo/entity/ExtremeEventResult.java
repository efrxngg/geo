package uees.project.geo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExtremeEventResult {
    private Long comid;
    private Double latitude;
    private Double longitude;
    private String location2;
    private Long eventosExtremos;

}
