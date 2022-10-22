package com.example.CommunityMarket.Repository;

import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    //get method
    @Query(value = "select * from item where ((:item_id is NULL or item_id = :item_id) and\n" +
            "                          (:item_name is NULL or item_name = :item_name) and\n" +
            "                          (:item_description is NULL or item_description = :item_description) and\n" +
            "                          (:item_category is NULL or item_category = :item_category))", nativeQuery = true)
    List<Item> findByTemplate(@Param("item_id") String item_id,
                              @Param("item_name") String item_name,
                              @Param("item_description") String item_description,
                              @Param("item_category") String item_category
    );
}
