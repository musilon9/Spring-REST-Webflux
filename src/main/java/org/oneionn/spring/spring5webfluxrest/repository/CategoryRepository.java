package org.oneionn.spring.spring5webfluxrest.repository;

import org.oneionn.spring.spring5webfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ondrej Musil
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
