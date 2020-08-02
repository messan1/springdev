package com.constelis.constelis.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.constelis.constelis.Dao.Interface.ClientRepository;
import com.constelis.constelis.Model.Client;
import com.constelis.constelis.Model.Contact;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Repository("ClientDao")
public class ClientDao {
    @Autowired
    private ClientRepository repository;

    private final MongoTemplate mongoTemplate;

    public ClientDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public List<Client> findByName(String name) {
        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Client.class);
    }

    public Client addClient(Client client) {
        List<Contact> contacts = new ArrayList<>(){};
        client.setContacts(contacts);
        return repository.save(client);
    }

    public UpdateResult updateClient(String id, Client client) {
        Query query = new Query().addCriteria(new Criteria().andOperator(Criteria.where("_id").is(id)));
        Update update = new Update();
        client.Data().forEach(update::set);
        return mongoTemplate.updateFirst(query, update, Client.class);
    }

    public Client findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Client.class);
    }
    public List<Client> findByNeed(String name){
        List<Client> clients=new ArrayList<>();
        Query query = new Query().addCriteria(Criteria.where("contactNeed")
                .elemMatch((Criteria.where("needs")
                                .regex("(?i).*" + name + ".*"))));
        List<Contact> contacts = mongoTemplate.find(query,Contact.class);
        System.out.println(contacts.size());
        for(Contact contact : contacts ){
            Query query2 = new Query().addCriteria(Criteria.where("contacts")
                    .elemMatch((Criteria.where("_id").is(contact.getId()))));
            Client cli = mongoTemplate.findOne(query2,Client.class);
            if(cli!=null){
                System.out.println(cli.getId());
                clients.add(cli);
            }
        }
        System.out.println(clients.size());
        return clients;
    }
    public List<Client> findByPush(String name){
        Query query = new Query().addCriteria(Criteria.where("contacts")
                .elemMatch(Criteria.where("contactPushs").in(name)));
        return mongoTemplate.find(query,Client.class);
    }
    public List<Client> findByReminder(LocalDate date){
        ContactDao contactDao = new ContactDao(mongoTemplate);
        List<Client> clients = new ArrayList<>();
        List<Contact> contacts = contactDao.reminder(date);
        for(Contact contact:contacts){
            Query query = new Query().addCriteria(Criteria.where("contacts")

                    .elemMatch(Criteria.where("firstName").is(contact.getFirstName()))
            );
            Client client = mongoTemplate.findOne(query,Client.class);
            clients.add(client);
        }
        return clients;
    }



    public List<Client> findByNameStartBy(String name){
        if(name.isEmpty()){
            return new ArrayList<>();
        }
        Query query = new Query().addCriteria(Criteria.where("contacts")
                .elemMatch(
                        Criteria.where("firstName").regex("(?i).*"+name+".*")
                ));
        return mongoTemplate.find(query,Client.class);
    }

    public Client deleteById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findAndRemove(query, Client.class);
    }

    public UpdateResult addContact(String clientId,String contactId){
        Update update = new Update();
        List<Contact>  contacts;
        Query contactQuery = new Query().addCriteria(Criteria.where("_id").is(contactId));
        Query clientQuery = new Query().addCriteria(Criteria.where("_id").is(clientId));
        Contact contact= mongoTemplate.findOne(contactQuery, Contact.class);
        Client client = mongoTemplate.findOne(clientQuery,Client.class);
        if(client.getContacts()==null){
        contacts = new ArrayList<>();
        }else{
            contacts = client.getContacts();
        }
        contacts.add(contact);
        update.set("contacts",contacts);

        return  mongoTemplate.updateFirst(clientQuery, update, Client.class);
    }

}
