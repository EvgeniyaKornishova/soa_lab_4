package ru.ifmo.soa.lab4.primary_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.soa.lab4.primary_back.entities.DBLocation;

public interface LocationRepository extends JpaRepository<DBLocation, Long> {

}
