//package com.blibli.training.springframework.controller;
//
//import com.blibli.training.springframework.entity.User;
//import com.blibli.training.springframework.service.UserService;
//import io.restassured.RestAssured;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerTest {
//
//    @Value("${local.server.port}")
//    private int serverPort;
//
//    @MockBean
//    private UserService userService;
//
//    @Before
//    public void setUp() throws Exception {
//        RestAssured.port = serverPort;
//    }
//
//    @After
//    public void tearDown() {
//        Mockito.verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void findAll() {
//        List<User> expected = Arrays.asList(
//                User.builder().firstName("Joko").lastName("Chiki").age(20).build(),
//                User.builder().firstName("Chiko").lastName("Chiki").age(20).build());
//        Mockito.when(userService.findAll()).thenReturn(expected);
//        List<User> result =
//                RestAssured.given()
//                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//                        .when()
//                        .get("/users")
//                        .then()
//                        .statusCode(HttpStatus.OK.value())
//                        .extract().body().jsonPath().getList(".", User.class);
//        Mockito.verify(userService).findAll();
//        Assert.assertEquals(expected.size(), result.size());
//        Assert.assertEquals(expected.get(0).getFirstName(), result.get(0).getFirstName());
//    }
//
//}