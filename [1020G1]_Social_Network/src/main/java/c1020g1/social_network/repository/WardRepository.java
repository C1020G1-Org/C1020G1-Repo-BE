package c1020g1.social_network.repository;

import c1020g1.social_network.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "SELECT * FROM ward WHERE ward.district_id = :districtId", nativeQuery = true)
    Iterable<Ward> findAllByDistrictId(int districtId);
}
