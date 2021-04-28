package c1020g1.social_network.repository;

import c1020g1.social_network.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(value = "select * from district", nativeQuery = true)
    List<District> getDistrictList();
}
