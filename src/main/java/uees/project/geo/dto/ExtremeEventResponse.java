package uees.project.geo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtremeEventResponse {
    private Long comid;
    private Double latitude;
    private Double longitude;
    private String location2;
    private String riesgo;
}
