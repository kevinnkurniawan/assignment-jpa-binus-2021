//package com.blibli.training.springframework.controller;
//
//import com.blibli.training.springframework.entity.Cart;
//import com.blibli.training.springframework.service.CartService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import org.junit.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @author Sebastian Bagya G. on 3/9/2021
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CartControllerTest {
//
//  private static final String USER_NAME = "username";
//  private static final String ITEM_NAME = "item";
//  private static final String OTHER_ITEM_NAME = "otherItem";
//  private static final long ID = 1L;
//
//  ObjectMapper objectMapper = new ObjectMapper();
//
//  @MockBean
//  private CartService cartService;
//
//  @LocalServerPort
//  private int serverPort;
//
//  @BeforeEach
//  void setUp() {
//    RestAssured.port = serverPort;
//  }
//
//  @AfterEach
//  void tearDown() {
//    Mockito.verifyNoMoreInteractions(cartService);
//  }
//
//  @Test
//  void findByCustomerName() {
//    Cart expected = Cart.builder()
//        .itemName(ITEM_NAME)
//        .quantity(1)
//        .build();
//
//    Mockito.when(cartService.findByUsername(USER_NAME)).thenReturn(expected);
//
//    Cart result = RestAssured.given()
//        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//        .param("customerName", USER_NAME)
//        .when()
//        .get("/carts")
//        .then()
//        .statusCode(HttpStatus.OK.value())
//        .extract()
//        .body()
//        .jsonPath()
//        .getObject(".", Cart.class);
//
//    Mockito.verify(cartService).findByUsername(USER_NAME);
//  }
//
//  @Test
//  void save() throws Exception {
//    Cart expected = Cart.builder().id(1L)
//        .itemName(ITEM_NAME)
//        .quantity(1)
//        .build();
//
//    Mockito.when(cartService.save(expected, USER_NAME)).thenReturn(expected);
//
//    Cart result = RestAssured.given()
//        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//        .body(objectMapper.writeValueAsString(expected))
//        .when()
//        .post("/carts")
//        .then()
//        .statusCode(HttpStatus.OK.value())
//        .extract()
//        .body()
//        .jsonPath()
//        .getObject(".", Cart.class);
//
//    Assert.assertEquals(expected.getQuantity(), result.getQuantity());
//
//    Mockito.verify(cartService).save(expected, USER_NAME);
//  }
//
//  @Test
//  void update() throws Exception {
//    Cart expected = Cart.builder()
//        .itemName(ITEM_NAME)
//        .quantity(1)
//        .build();
//
//    Mockito.when(cartService.update(expected, USER_NAME)).thenReturn(expected);
//
//    Cart result = RestAssured.given()
//        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//        .body(objectMapper.writeValueAsString(expected))
//        .when()
//        .put("/carts/1")
//        .then()
//        .statusCode(HttpStatus.OK.value())
//        .extract()
//        .body()
//        .jsonPath()
//        .getObject(".", Cart.class);
//
//    Assert.assertEquals(expected.getQuantity(), result.getQuantity());
//
//    Mockito.verify(cartService).update(expected, USER_NAME);
//  }
//
//  @Test
//  void delete() {
//    Mockito.when(cartService.delete(ID)).thenReturn(true);
//
//    Boolean result = RestAssured.given()
//        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//        .param("itemName", ITEM_NAME)
//        .param("customerName", USER_NAME)
//        .when()
//        .delete("/carts/1")
//        .then()
//        .statusCode(HttpStatus.OK.value())
//        .extract()
//        .body()
//        .as(Boolean.class);
//
//    Assert.assertTrue(result);
//
//    Mockito.verify(cartService).delete(ID);
//  }
//}