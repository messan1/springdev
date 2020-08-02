package com.constelis.constelis.Api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.constelis.constelis.Model.Client;
import com.constelis.constelis.Model.Contact;
import com.constelis.constelis.Service.ClientService;
import com.constelis.constelis.Service.ContactService;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/client")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/name")
    public List<Client> findByName(@RequestParam(name = "name") String name) {
        return clientService.findByName(name);
    }

    @GetMapping("/startby")
    private List<Client> findByNameStartBy(@RequestParam(name = "name") String name){
        return clientService.findByNameStartBy(name);
    }


    @GetMapping("/startbyneed")
    private List<Client> findByNameStartByNeed(@RequestParam(name = "data") String data){
        return clientService.findByNeed(data);
    }
    @GetMapping("/startbypush")
    private List<Client> findByNameStartByPush(@RequestParam(name = "data") String data){
        return clientService.findByPush(data);
    }
    @GetMapping("/reminder")
    private List<Client> GtReminner(@RequestParam(name = "data") String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate date3 = LocalDate.parse(data, formatter);
        return clientService.FindByReminder(date3);
    }

    @GetMapping("/id")
    public Client findById(@RequestParam(name = "id") String id) {
        return clientService.findById(id);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }


    @PutMapping
    public UpdateResult updateClient(@RequestParam(name = "id") String id, @RequestBody Client client) {
        System.out.println(id);
        return clientService.updateClient(id, client);
    }
    @DeleteMapping
    public Client deleteById(@RequestParam(name = "id") String id) {
        return clientService.deleteById(id);
    }


    @PutMapping("/contact")
    public UpdateResult addContact(@RequestParam(name = "id") String id,@RequestParam(name = "contactId") String contactId){
        return clientService.addContact(id,contactId);
    }
}