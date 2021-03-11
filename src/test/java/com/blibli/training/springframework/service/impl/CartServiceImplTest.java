//package com.blibli.training.springframework.service.impl;
//
//import com.blibli.training.springframework.entity.Cart;
//import com.blibli.training.springframework.repository.CartRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
///**
// * @author Sebastian Bagya G. on 3/9/2021
// */
//public class CartServiceImplTest {
//
//    private static final String USER_NAME = "cust";
//    private static final String ITEM_NAME = "item";
//    private static final Long ID = 1L;
//
//    @InjectMocks
//    private CartServiceImpl cartService;
//
//    @Mock
//    private CartRepository cartRepository;
//
//    private Cart expected;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        expected = Cart.builder().id(ID)
//            .itemName(ITEM_NAME)
//            .quantity(1)
//            .build();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Mockito.verifyNoMoreInteractions(cartRepository);
//    }
//
//    @Test
//    public void findByUsername() {
//        Mockito.when(cartRepository.findByUser_Username(USER_NAME)).thenReturn(expected);
//        Cart cart = cartService.findByUsername(USER_NAME);
//        Mockito.verify(cartRepository).findByUser_Username(USER_NAME);
//    }
//
//    @Test
//    public void save() {
//        Mockito.when(cartRepository.save(expected)).thenReturn(expected);
//        Cart cart = cartService.save(expected, USER_NAME);
//        Assert.assertEquals(expected, cart);
//        Mockito.verify(cartRepository).save(expected);
//    }
//
//    @Test
//    public void update() {
//        Optional<Cart> expected = Optional.of(Cart.builder()
//            .id(ID)
//            .itemName(ITEM_NAME)
//            .quantity(10)
//            .build());
//        Mockito.when(cartRepository.findById(ID)).thenReturn(expected);
//        Cart cart = cartService.update(expected.get(), USER_NAME);
//        Assert.assertEquals(expected.get(), cart);
//        Mockito.verify(cartRepository).findById(ID);
//        Mockito.verify(cartRepository).save(expected.get());
//    }
//
//    @Test
//    public void update_notFound() {
//        Optional<Cart> expected = Optional.empty();
//        Mockito.when(cartRepository.findById(ID)).thenReturn(expected);
//        Cart cart = cartService.update(new Cart(), USER_NAME);
//        Assert.assertNull(cart);
//        Mockito.verify(cartRepository).findById(ID);
//    }
//
//    @Test
//    public void delete() {
//        Optional<Cart> expected = Optional.of(Cart.builder()
//            .id(ID)
//            .itemName(ITEM_NAME)
//            .quantity(10)
//            .build());
//        Mockito.when(cartRepository.findById(ID)).thenReturn(expected);
//        boolean result = cartService.delete(ID);
//        Assert.assertTrue(result);
//        Mockito.verify(cartRepository).findById(ID);
//        Mockito.verify(cartRepository).delete(expected.get());
//    }
//
//    @Test
//    public void deleteCart_notFound() {
//        Optional<Cart> expected = Optional.empty();
//        Mockito.when(cartRepository.findById(ID)).thenReturn(expected);
//        boolean result = cartService.delete(ID);
//        Assert.assertFalse(result);
//        Mockito.verify(cartRepository).findById(ID);
//    }
//}