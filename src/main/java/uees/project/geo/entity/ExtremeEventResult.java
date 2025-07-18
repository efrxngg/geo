package uees.project.geo.entity;

import lombok.Data;

@Data
public class ExtremeEventResult {
    private Long comid;
    private Double latitude;
    private Double longitude;
    private String location2;
    private Long eventosExtremos;

    // Constructor
    public ExtremeEventResult(Long comid, Double latitude, Double longitude, String location2, Long eventosExtremos) {
        this.comid = comid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location2 = location2;
        this.eventosExtremos = eventosExtremos;
    }

}
