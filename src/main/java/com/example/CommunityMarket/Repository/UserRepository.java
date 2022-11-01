package com.example.CommunityMarket.Repository;
import com.example.CommunityMarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "select * from user where ((:user_id is NULL or user_id = :user_id) and\n" +
            "                          (:email is NULL or email = :email) and\n" +
            "                          (:username is NULL or username = :username))", nativeQuery = true)
    List<User> findByTemplate(@Param("user_id") Integer user_id,
                              @Param("email") String email,
                              @Param("username") String username);

    //add method
//    @Transactional
//    @Modifying
//    @Query(value = "insert into Users values(:userID,:username,:password)",nativeQuery = true)
//    void insertUser(@Param("userID") String userID,
//                    @Param("username") String username,
//                    @Param("password") String password);
}
