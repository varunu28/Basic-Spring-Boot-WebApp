package com.varun.spring5webapp.repositories;

import com.varun.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
