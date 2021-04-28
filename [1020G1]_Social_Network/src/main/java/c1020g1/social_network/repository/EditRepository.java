package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EditRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `user` " +
            "SET username = :#{#user.getUserName}," +
            "gender =  :#{#user.getGender()}," +
            "birthday = :#{#user.getBirthday()}," +
            "marriaged = :#{#user.getMarriaged()}," +
            "occupation = :#{#user.getOccupation()}," +
            "email = :#{#user.getEmail()}," +
            "address = :#{#user.getAddress()}," +
            "ward_id = :#{#user?.getWard()?.getWardId()} " +
            "WHERE user_id = :#{#user.userId}"
            , nativeQuery = true)
    void updateUser(User user);
}
