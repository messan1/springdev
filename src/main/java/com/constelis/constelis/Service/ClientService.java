package com.constelis.constelis.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.constelis.constelis.Dao.ClientDao;
import com.constelis.constelis.Model.Client;
import com.constelis.constelis.Model.Contact;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ClientService {
    @Autowired
    private final ClientDao clientDao;

    public ClientService(@Qualifier("ClientDao") ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> findAll(){
        return clientDao.findAll();
    }

    public Client addClient(Client client){
        return clientDao.addClient(client);
    }

    public List<Client> findByName(String name){
        return clientDao.findByName(name);
    } 

    public UpdateResult updateClient(String id , Client client){
        return clientDao.updateClient(id,client);
    }

    public Client deleteById(String id){
        return clientDao.deleteById(id);
    }

    public Client findById(String id){
        return clientDao.findById(id);
    }

    public UpdateResult addContact(String id , String contactId){
        return  clientDao.addContact(id,contactId);
    }
    public List<Client> findByNameStartBy(String name){
        return clientDao.findByNameStartBy(name);
    }
    public List<Client> findByNeed(String name){
        return  clientDao.findByNeed(name);
    }
    public List<Client> findByPush(String name){
        return  clientDao.findByPush(name);
    }
    public List<Client> FindByReminder(LocalDate date){
return  clientDao.findByReminder(date);
    }
}