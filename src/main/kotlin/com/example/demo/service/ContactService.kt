package com.example.demo.service

import com.example.demo.entity.Contact
import com.example.demo.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@Service
class ContactService(
        var contactRepository: ContactRepository,
) {
    @Autowired

    fun getAllContacts(): ResponseEntity<List<Contact>> {
        val contacts: List<Contact> = contactRepository.findAll()

        if (contacts.isEmpty()) {
            return ResponseEntity.notFound().build()
        }


        return ResponseEntity.ok(contacts)


    }

    fun create(contact: Contact): ResponseEntity<Contact> {
        val contactResponse: Contact = contactRepository.save(contact)

        val local: URI = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("{id}")
                        .buildAndExpand(contactResponse.id).toUri()

        return ResponseEntity.created(local).body(contactResponse)
    }

    fun update(id: Long, newContact: Contact): ResponseEntity<Contact> {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        val contact = contactRepository.findById(id).get()
        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }

        return ResponseEntity.ok(contactRepository.save(contact))
    }

    fun delete(id: Long): ResponseEntity<Void> {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        contactRepository.deleteById(id)

        return ResponseEntity.noContent().build()
    }
}