package org.oneionn.spring.spring5webfluxrest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.oneionn.spring.spring5webfluxrest.domain.Category;
import org.oneionn.spring.spring5webfluxrest.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Ondrej Musil
 */
public class CategoryControllerTest {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void testList() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(
                        Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()
                ));

        webTestClient.get().uri("api/v1/categories")
                .exchange()
                .expectBodyList(Category.class).hasSize(2);
    }

    @Test
    public void testCreate() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Category.builder().description("ddd").build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("SomeCat").build());

        webTestClient.post().uri("api/v1/categories")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testGetById() {
        BDDMockito.given(categoryRepository.findById("someid"))
                .willReturn(Mono.just(
                        Category.builder().description("Cat1").build()
                ));

        webTestClient.get().uri("api/v1/categories/someid")
                .exchange()
                .expectBody(Category.class);
    }
}