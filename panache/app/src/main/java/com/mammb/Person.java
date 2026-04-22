package com.mammb;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
}
