package com.example.CommunityMarket.repository;
import com.example.CommunityMarket.model.Negotiation;
import org.springframework.data.repository.CrudRepository;

public interface NegotiationRepository extends CrudRepository<Negotiation, Integer>{
    //get by template needs to be add
}
