package com.example.CommunityMarket.Repository;

import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    // get method
    // post method


}
