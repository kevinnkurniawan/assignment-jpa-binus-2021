//package com.blibli.training.springframework.service.impl;
//
//import com.blibli.training.springframework.entity.User;
//import com.blibli.training.springframework.repository.UserRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class UserServiceImplTest {
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @After
//    public void tearDown() {
//        Mockito.verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    public void findAll() {
//        User firstUser = User.builder().firstName("First1").lastName("Last1").age(25).build();
//        User secondUser = User.builder().firstName("First2").lastName("Last2").age(60).build();
//        List<User> expected = new ArrayList<>(Arrays.asList(firstUser, secondUser));
//        Mockito.when(userRepository.findAll()).thenReturn(expected);
//        List<User> result = userService.findAll();
//        Assert.assertEquals(expected.size(), result.size());
//        Assert.assertEquals(expected.get(0), result.get(0));
//        Mockito.verify(userRepository).findAll();
//    }
//
//}