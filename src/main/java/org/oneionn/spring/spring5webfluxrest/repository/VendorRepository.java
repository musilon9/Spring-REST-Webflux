package org.oneionn.spring.spring5webfluxrest.repository;

import org.oneionn.spring.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ondrej Musil
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
