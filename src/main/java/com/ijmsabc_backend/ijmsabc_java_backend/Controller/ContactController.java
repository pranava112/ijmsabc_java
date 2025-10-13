package com.ijmsabc_backend.ijmsabc_java_backend.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Contact;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.ContactRepository;


@RestController
@RequestMapping("/api/ijmsabc/contact")
@CrossOrigin(origins = {
    "https://www.ijmsabc.org",  
    "http://localhost:5173"     
})

public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // ✅ Save a new contact
    @PostMapping
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return ResponseEntity.ok(savedContact);
    }

    // ✅ Get all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    // ✅ Delete contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return ResponseEntity.ok("Contact with id " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Contact not found with id " + id);
        }
    }
}
