package com.example.CommunityMarket.Repository;
import com.example.CommunityMarket.model.Item;
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
public interface ItemRepository extends JpaRepository<Item, String> {


    //get
    @Query(value = "select * from item where ((:id is NULL or id = :id) and\n" +
            "                          (:name is NULL or name = :name) and\n" +
            "                          (:description is NULL or description = :description) and\n" +
            "                          (:category is NULL or category = :category))", nativeQuery = true)
    List<Item> findByTemplate(@Param("id") String id,
                                     @Param("name") String name,
                                     @Param("description") String description,
                                     @Param("category") String category
    );
    //add
    @Transactional
    @Modifying
    @Query(value = "insert into item values(:id,:name,:description,:category)",nativeQuery = true)
    void insertItem(@Param("id") String id,
                    @Param("name") String name,
                    @Param("description") String description,
                    @Param("category") String category);
    //delete
    @Transactional
    @Modifying
    @Query(value = "delete from item \n" +
            "where \n" +
            "((id = :id) and\n" +
            "(name = :name) and\n"281
            ,nativeQuery = true)
    void deleteItem(@Param("id") Integer player_id,
                             @Param("name") Integer client_id,
                             @Param("category") Integer league_id);

}
