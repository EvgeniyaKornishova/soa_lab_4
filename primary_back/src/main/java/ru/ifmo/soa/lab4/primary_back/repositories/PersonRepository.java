package ru.ifmo.soa.lab4.primary_back.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ifmo.soa.lab4.primary_back.entities.DBPerson;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends PagingAndSortingRepository<DBPerson, Long>, JpaSpecificationExecutor<DBPerson> {

   List<DBPerson> findByNameContains(String substring);
   Optional<DBPerson> findById(Long id);

   @Query(value="SELECT SUM(height) FROM person", nativeQuery = true)
   Float calcSumHeight();
}
