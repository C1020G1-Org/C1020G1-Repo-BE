package c1020g1.social_network.repository;

import c1020g1.social_network.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(value = "SELECT * FROM district WHERE district.province_id = :provinceId", nativeQuery = true)
    Iterable<District> findAllByProvinceId(int provinceId);
}
