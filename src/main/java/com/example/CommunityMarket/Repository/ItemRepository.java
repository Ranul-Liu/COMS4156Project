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
public interface ItemRepository extends CrudRepository<Item, String> {


    //get method
    @Query(value = "select * from item where ((:item_id is NULL or item_id = :item_id) and\n" +
            "                          (:item_name is NULL or item_name = :item_name) and\n" +
            "                          (:item_description is NULL or item_description = :item_description) and\n" +
            "                          (:item_category is NULL or item_category = :item_category))", nativeQuery = true)
    List<Item> findByTemplate(@Param("item_id") Integer item_id,
                                     @Param("item_name") String item_name,
                                     @Param("item_description") String item_description,
                                     @Param("item_category") String item_category
    );
    // post method
    @Transactional
    @Modifying
    @Query(value = "insert into item values(:item_id,:item_name,:item_description,:item_category)",nativeQuery = true)
    void insertItem(@Param("item_id") String item_id,
                    @Param("item_name") String item_name,
                    @Param("item_description") String item_description,
                    @Param("item_category") String item_category);
    /*//delete
    @Transactional
    @Modifying
    @Query(value = """
            delete from item\s
            where\s
            ((item_id = :item_id) and
            (item_name = :item_name)
            """
            ,nativeQuery = true)
    void deleteItem(@Param("item_id")       String item_id,
                    @Param("item_name")     String item_name,
                    @Param("item_description")     String item_description,
                    @Param("item_category") String item_category);
*/
}
