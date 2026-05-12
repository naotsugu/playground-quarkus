package com.mammb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Person {
    @Id
    @GeneratedValue
    public UUID id;
    public String name;
    public LocalDate birth;
    public Status status;
}
