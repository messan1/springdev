package com.constelis.constelis.Dao.Interface;


import com.constelis.constelis.Model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact,Integer> {
    
}