package com.example.demo.controller

import com.example.demo.entity.Contact
import com.example.demo.service.ContactService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("contacts")
class ContactController(
        var contactService: ContactService
) {

    @GetMapping
    fun getAllContacts(): ResponseEntity<List<Contact>> {
        return contactService.getAllContacts()
    }

    @PostMapping
    fun create(@Valid @RequestBody contact: Contact): ResponseEntity<Contact> {
        return contactService.create(contact)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody newContact: Contact): ResponseEntity<Contact> {
        return contactService.update(id, newContact)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return contactService.delete(id)
    }


}