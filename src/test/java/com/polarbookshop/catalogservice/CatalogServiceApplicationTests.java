//package com.polarbookshop.catalogservice;
//
//import com.polarbookshop.catalogservice.domain.Book;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import java.time.Instant;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("integration")
//class CatalogServiceApplicationTests {
//
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    void whenPostRequestThenBookCreated() {
//        var expectedBook = new Book(1L,"1231231231", "Title", "Author", 9.90, "INDRAP", Instant.now(), Instant.now(), 2);
//        webTestClient
//                .post()
//                .uri("/books")
//                .bodyValue(expectedBook)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Book.class).value(actualBook -> {
//                    assertThat(actualBook).isNotNull();
//                    assertThat(actualBook.getIsbn())
//                            .isEqualTo(expectedBook.getIsbn());
//                });
//    }
//}
