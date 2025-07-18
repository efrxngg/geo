package uees.project.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uees.project.geo.entity.DrainageNetwork;
import uees.project.geo.entity.ExtremeEventResult;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DrainageNetworkRepository extends JpaRepository<DrainageNetwork, Long> {
    @Query("""
            SELECT new uees.project.geo.entity.ExtremeEventResult(
                dn.comid,
                dn.latitude,
                dn.longitude,
                dn.location2,
                COUNT(CASE WHEN hs.value > 200 THEN 1 END)
            )
            FROM DrainageNetwork dn
            LEFT JOIN HistoricalSimulation hs
            ON dn.comid = hs.comid
            WHERE hs.datetime BETWEEN :startDate AND :endDate
            GROUP BY dn.comid, dn.latitude, dn.longitude, dn.location2
            """)
    List<ExtremeEventResult> findExtremeEvents(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
