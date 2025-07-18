package uees.project.geo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drainage_network")
@Data
public class DrainageNetwork {
    @Id
    private Long comid;
    private Double latitude;
    private Double longitude;
    private String location2;
}
