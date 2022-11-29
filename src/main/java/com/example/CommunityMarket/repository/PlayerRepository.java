package com.example.CommunityMarket.repository;
import com.example.CommunityMarket.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query(value = """
            select * from player where ((:player_id is NULL or player_id = :player_id) and
                                      (:email is NULL or email = :email) and
                                      (:playername is NULL or playername = :playername) and
                                      (:login is NULL or login = :login))""", nativeQuery = true)
    List<Player> findByTemplate(@Param("player_id") Integer player_id,
                              @Param("email") String email,
                              @Param("playername") String playername,
                              @Param("login") Boolean login);

    //add method
//    @Transactional
//    @Modifying
//    @Query(value = "insert into Players values(:playerID,:playername,:password)",nativeQuery = true)
//    void insertPlayer(@Param("playerID") String playerID,
//                    @Param("playername") String playername,
//                    @Param("password") String password);
}
