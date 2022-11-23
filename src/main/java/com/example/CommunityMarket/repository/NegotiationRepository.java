package com.example.CommunityMarket.repository;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NegotiationRepository extends CrudRepository<Negotiation, Integer>{
    //get by template
    @Query(value = "select * from negotiation where ((:negotiation_id is NULL or negotiation_id = :negotiation_id) and\n" +
            "                          (:buyer_id is NULL or buyer_id = :buyer_id) and\n" +
            "                          (:fk_transaction_id is NULL or fk_transaction_id = :fk_transaction_id) and\n" +
            "                          (:quantity is NULL or quantity = :quantity) and\n" +
            "                          (:open is NULL or open = :open) and\n" +
            "                          (:post_time is NULL or post_time = :post_time) and\n" +
            "                          (:close_time is NULL or close_time = :close_time) and\n" +
            "                          (:price is NULL or price = :price)) and\n "+
            "                          (:accept is NULL or accept = :accept) ", nativeQuery = true)
    List<Negotiation> findByTemplate(@Param("negotiation_id") Integer negotiation_id,
                                     @Param("buyer_id") String buyer_id,
                                     @Param("fk_transaction_id") String seller_id,
                                     @Param("quantity") Integer quantity,
                                     @Param("open") Boolean open,
                                     @Param("post_time") LocalDateTime post_time,
                                     @Param("close_time") LocalDateTime close_time,
                                     @Param("price") Integer price,
                                     @Param("accept") Boolean accept
    );
    //find by TransactionID
    @Query(value = "select * from negotiation where fk_transaction_id = :fk_transaction_id", nativeQuery = true)
    List<Negotiation> findByTransactionID(@Param("fk_transaction_id") Integer fk_transaction_id);
}
