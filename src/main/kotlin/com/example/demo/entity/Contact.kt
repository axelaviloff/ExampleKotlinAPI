package com.example.demo.entity

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "contacts")
class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    @Size(min = 5, max = 50)
    var name: String,

    @NotNull
    @Email
    var email: String,

    var registerDate: LocalDateTime = LocalDateTime.now()
)