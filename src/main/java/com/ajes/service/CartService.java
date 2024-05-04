package com.ajes.service;

import com.ajes.model.Booking;
import com.ajes.model.Cart;
import com.ajes.model.Customer;
import com.ajes.model.User;
import com.ajes.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookingService bookingService;
    private Cart cart;


    @Autowired
   private   UserService userService;

    //It is post method(body)
    public Cart addCart(Cart cart) {
        cart.setTrDate(new Date());

        Booking booking = new Booking();

        booking.setUser(cart.getUser());
        booking.setLogin(cart.getLogin());
        Booking booking2 = bookingService.addBooking(booking);
//        cart.setBooking(booking2);

        return cartRepository.save(cart);

    }

    //It is get method(head)
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    //It is get method(head)
    public Cart getCartById(Integer cartId) {
        Optional<Cart> optional = cartRepository.findById(cartId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    //it is put method(head and body)
    public Cart modifyCart(Cart cart) {
        return cartRepository.save(cart);
    }

    //it is delete method(head)
    public Cart deleteCart(Integer cartId) {
        Cart cart = getCartById(cartId);
        cartRepository.deleteById(cartId);
        return cart;
    }

//    public List<Cart> getCartByUserId(Long userId) {
//        List<Cart> allcart = cartRepository.findAll();
//          List<Cart> cart1=new ArrayList<>();
//        User user= userService.getUserById(userId);
//
//        for (Cart cart : allcart) {
//            if (cart.getUser().equals(user)) {
//
//                cart1.add(cart);
//
//            }
//            else{
//                System.out.println("userId not valid");
//            }
//
//        }
//
//        return  cart1;
//
//    }


    public Cart getCartByUserId(Long userId) {
        List<Cart> allCarts = cartRepository.findAll();
        User user = userService.getUserById(userId);

        for (Cart cart : allCarts) {
            if (cart.getUser().equals(user)) {
                return cart; // Return the first cart found for the user
            }
        }

        // If no cart is found for the user, you may choose to return null or throw an exception
        // For demonstration purposes, let's return null
        return null;
    }

}
