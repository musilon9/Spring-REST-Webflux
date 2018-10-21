package org.oneionn.spring.spring5webfluxrest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.oneionn.spring.spring5webfluxrest.domain.Vendor;
import org.oneionn.spring.spring5webfluxrest.repository.VendorRepository;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

/**
 * @author Ondrej Musil
 */
public class VendorControllerTest {

    VendorRepository vendorRepository;
    VendorController vendorController;
    WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void testList() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(
                        Vendor.builder().firstName("Name").lastName("Surname").build(),
                        Vendor.builder().firstName("Namee").lastName("Surnamee").build()
                ));

        webTestClient.get().uri("api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void testGetById() {
        BDDMockito.given(vendorRepository.findById("someid"))
                .willReturn(Mono.just(
                        Vendor.builder().firstName("Name").lastName("Surname").build()
                ));

        webTestClient.get().uri("api/v1/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }
}