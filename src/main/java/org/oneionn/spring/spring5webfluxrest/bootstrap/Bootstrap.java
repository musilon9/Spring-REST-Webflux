package org.oneionn.spring.spring5webfluxrest.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.oneionn.spring.spring5webfluxrest.domain.Category;
import org.oneionn.spring.spring5webfluxrest.domain.Vendor;
import org.oneionn.spring.spring5webfluxrest.repository.CategoryRepository;
import org.oneionn.spring.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Ondrej Musil
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count().block() == 0) {
            // populate data
            log.info("Populating data from Bootstrap class...");

            categoryRepository.save(Category.builder()
                    .description("Fruits").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Vegetables").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Meat").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Dairy").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Drinks").build()).block();
            log.info("Loaded {} categories", categoryRepository.count().block());

            vendorRepository.save(Vendor.builder()
                    .firstName("Karel").lastName("Novak").build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstName("Ladislav").lastName("Novak").build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstName("Karel").lastName("Novotny").build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstName("Ladislav").lastName("Novotny").build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstName("Frantisek").lastName("Dobrota").build()).block();
            log.info("Loaded {} vendors", vendorRepository.count().block());
        }
    }
}
