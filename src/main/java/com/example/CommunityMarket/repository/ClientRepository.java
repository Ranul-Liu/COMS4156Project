package com.example.CommunityMarket.repository;
import com.example.CommunityMarket.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    //get by template
    @Query(value = """
            select * from client where ((:client_id is NULL or client_id = :client_id) and
                                      (:email is NULL or email = email) and
                                      (:client_name is NULL or client_name = :client_name) and
                                      (:company_name is NULL or company_name = :company_name))""", nativeQuery = true)
    List<Client> findByTemplate(@Param("client_id") Integer client_id,
                              @Param("client_name") String client_name,
                              @Param("company_name") String company_name,
                              @Param("email") String email
    );
}
