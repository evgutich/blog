package com.leverx.blog.repository;

import com.leverx.blog.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByName(String tagName);
}
