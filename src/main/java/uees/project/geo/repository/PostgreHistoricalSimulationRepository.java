package uees.project.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uees.project.geo.entity.HistoricalSimulation;

import java.util.List;

@Repository
public interface PostgreHistoricalSimulationRepository extends JpaRepository<HistoricalSimulation,Long> {

    @Query(value = "SELECT DISTINCT EXTRACT(YEAR FROM datetime) as  year FROM historical_simulation e", nativeQuery = true)
    List<Integer> findAvailableYears();

}
