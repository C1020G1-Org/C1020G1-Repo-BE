package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(value = "INSERT INTO user (username,birthday,gender,occupation,email,user_avatar,user_background,marriaged,ward_id,address,status_id,account_id) VALUE (:#{#user.userName},:#{#user.birthday},:#{#user.gender},:#{#user.occupation},:#{#user.email},:#{#user.userAvatar},:#{#user.userBackground},:#{#user.marriaged},:#{#user.ward.wardId},:#{#user.address},:#{#user.status.statusId}, :#{#user.account.accountId})", nativeQuery = true)
    void createUser(User user);

    @Query(value = "SELECT * FROM user WHERE user.user_id = :userId", nativeQuery = true)
    User getUserById(@Param("userId") int userId);


    @Query(value = "SELECT * FROM user WHERE user.account_id = :accountId", nativeQuery = true)
    User getUserByAccountId(@Param("accountId") int accountId);

    @Query(value = "SELECT * FROM user WHERE user.email = :email", nativeQuery = true)
    User getUserByEmail(@Param("email") String email);

}
