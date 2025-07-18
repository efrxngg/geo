package uees.project.geo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "historical_simulation")
@Data
public class HistoricalSimulation {
    @Id
    private Long id;

    @Column(name = "comid")
    private Long comid;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "value")
    private Double value;
}
