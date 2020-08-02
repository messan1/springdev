package com.constelis.constelis.Api;

import com.constelis.constelis.Model.Contact;
import com.constelis.constelis.Model.ContactConversation;
import com.constelis.constelis.Model.ContactNeed;
import com.constelis.constelis.Service.ContactService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RequestMapping("api/v1/contact")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ContactController {

    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("./src/main/resources/static");

    @Autowired
    private ContactService contactService;

    @GetMapping
    private List<Contact> findAll(){
        return contactService.findAll();
    }

    @GetMapping("/name")
    private List<Contact> findByName(@RequestParam(name="firstName") String firstName,@RequestParam(name = "lastName") String lastName){
        return contactService.findByName(firstName,lastName);
    }
    @GetMapping("/conversation")
    private List<ContactConversation> findConversations(@RequestParam(name="id") String id){
        return contactService.findConversations(id);
    }
    @GetMapping("/need")
    private List<ContactNeed> findNeed(@RequestParam(name="id") String id){
        return contactService.findNeed(id);
    }

    @GetMapping("/startby")
    private List<Contact> findByNameStartBy(@RequestParam(name = "name") String name){
        return null;
    }

    @GetMapping("/id")
    private Contact findById(@RequestParam(name = "id") String id){
        return contactService.findContactById(id);
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @PostMapping("/conversation")
    public UpdateResult addConversation(@RequestParam(name="id") String id,@RequestBody ContactConversation conversation) {
        System.out.println(conversation.getConversation());
        return contactService.addConversation(id,conversation);
    }
    @PostMapping("/need")
    public UpdateResult addNeed(@RequestParam(name="id") String id,@RequestBody ContactNeed contactNeed) {
        return contactService.addNeed(id,contactNeed);
    }

    @PutMapping
    public UpdateResult updateContact(@RequestParam(name = "id") String id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @PutMapping("/ao")
    public UpdateResult updateAo(@RequestParam(name = "ao") String ao,@RequestParam(name = "id") String id,@RequestParam(name = "name") String name) {
       System.out.println("ao");
        return contactService.updateAo(id,ao,name);
    }

    @PutMapping("/cv")
    public UpdateResult updateCv(@RequestParam(name = "needId") String needId,@RequestParam(name = "id") String id,@RequestParam(name = "name") String name) {
        System.out.println(needId);
        return contactService.updateCv(id, name,needId);
    }

    @GetMapping("/test")
    public List<Contact> bind(@RequestParam(name = "date") String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate date3 = LocalDate.parse(date2, formatter);
        return contactService.reminder(date3);
    }

    @DeleteMapping
    public Contact deleteById(@RequestParam(name = "id") String id) {
        return contactService.deleteById(id);
    }

    @PostMapping("/savefile")
    public Integer handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam(name = "name") String name) {

        String data = name+"."+getExtensionByStringHandling(file.getOriginalFilename()).get();
        try {
            try {
                Files.copy(file.getInputStream(),
                        this.rootLocation.resolve(data));
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());
            return 1;
        } catch (Exception e) {
            return null;
        }
    }
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
