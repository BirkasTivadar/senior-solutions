package com.tivadar.authorsjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ManyToOne;
import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a left join fetch a.books where a.name like :prefix%")
    List<Author> findAuthorsByPrefix(@Param("prefix") String prefix);

    List<Author> findAuthorByNameStartsWith(String prefix);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Author a set a.name=:name where a.id=:id")
    void updateAuthorNameById(@Param("name") String name, @Param("id") Long id);
}

