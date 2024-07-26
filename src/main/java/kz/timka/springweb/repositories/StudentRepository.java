package kz.timka.springweb.repositories;

import jakarta.annotation.PostConstruct;
import kz.timka.springweb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.score between ?1 and ?2")
    List<Student> findAllByScoreBetween(Integer min, Integer max);

    @Query("select s from Student s where s.name = :name")
    Optional<Student> findByName(String name);


    @Query("select s from Student s where s.score < 20")
    List<Student> findAllLowRating();

    @Query(value = "select score from students where name = :name", nativeQuery = true)
    Optional<Student> getScoreByNameWithNativeQuery(String name);

    // Query("select s from Student s join fetch s.books where s.id = :id);

}
