package c1020g1.social_network.repository;

import c1020g1.social_network.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query(value = "select * from province", nativeQuery = true)
    List<Province> getProvinceList();
}
