package com.example.CommunityMarket.Repository;

import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


    // get method
    @Query(value = "select * from transaction where ((:transaction_id is NULL or transaction_id = :transaction_id) and\n" +
            "                          (:buyer_id is NULL or buyer_id = :buyer_id) and\n" +
            "                          (:seller_id is NULL or seller_id = :seller_id) and\n" +
            "                          (:quantity is NULL or quantity = :quantity) and\n" +
            "                          (:is_open is NULL or is_open = :is_open) and\n" +
            "                          (:post_time is NULL or post_time = :post_time) and\n" +
            "                          (:close_time is NULL or close_time = :close_time) and\n" +
            "                          (:price is NULL or price = :price))", nativeQuery = true)
    List<Transaction> findByTemplate(@Param("transaction_id") Integer transaction_id,
                              @Param("buyer_id") String buyer_id,
                              @Param("seller_id") String seller_id,
                              @Param("quantity") Integer quantity,
                              @Param("is_open") Boolean is_open,
                              @Param("post_time") LocalDateTime post_time,
                              @Param("close_time") LocalDateTime close_time,
                              @Param("price") Integer price
                              );
    // post method


}
