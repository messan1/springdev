package com.constelis.constelis.Service;

import com.constelis.constelis.Dao.ContactDao;
import com.constelis.constelis.Model.Client;
import com.constelis.constelis.Model.Contact;
import com.constelis.constelis.Model.ContactConversation;
import com.constelis.constelis.Model.ContactNeed;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactDao contactDao;

    public ContactService(@Qualifier("ContactDao") ContactDao contactDao ){
        this.contactDao=contactDao;
    }
    public List<Contact> findAll(){
        return contactDao.findAll();
    }

    public Contact addContact(Contact contact){
        return contactDao.addContact(contact);
    }

    public List<Contact> findByName(String firstName,String lastName){
        return contactDao.findByName(firstName,lastName);
    }

    public UpdateResult updateContact(String id , Contact contact){
        return contactDao.updateContact(id,contact);
    }

    public Contact deleteById(String id){
        return contactDao.deleteById(id);
    }


    public List<ContactConversation> findConversations(String id){
     return   contactDao.findConversations(id);
    }
    public List<ContactNeed> findNeed(String id){
        return contactDao.findNeed(id);
    }
    public UpdateResult addConversation(String id,ContactConversation conversation){
        return contactDao.addConversation(id,conversation);
    }
    public  UpdateResult addNeed(String id,ContactNeed contactNeed){
        return  contactDao.addNeed(id,contactNeed);
    }

    public Contact findContactById(String id){
        return contactDao.findContactById(id);
    }
    public UpdateResult updateCv(String id,String name,String needId) {
        return contactDao.addCv(id, name,needId);
    }
    public UpdateResult updateAo(String ao , String id,String name) {
        return contactDao.addAo(ao,id,name);
    }

    public List<Contact> reminder(LocalDate date){
        return contactDao.reminder(date);
    }
}
