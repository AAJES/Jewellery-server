package com.ajes.controller;

import com.ajes.model.Cart;
import com.ajes.model.Customer;
import com.ajes.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<Cart> addCart(@RequestBody() Cart cart){


        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCart(cart));
    }

    @GetMapping("/cart/")
    public ResponseEntity<List<Cart>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(cartService.getAll());
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<Cart> getByCartId(@PathVariable() Integer cartId){
        return ResponseEntity.status(HttpStatus.FOUND).body(cartService.getCartById(cartId));
    }

    @PostMapping("/sound")
    public ResponseEntity<String> recognizeSoundCommand(@RequestBody byte[] audioData) {
        // Implement logic to process audio data and recognize sound commands
        String command = processAudio(audioData);
        return ResponseEntity.ok(command);
    }

    private String processAudio(byte[] audioData) {
        // Implement sound processing and command recognition here
        // You might use libraries like CMU Sphinx, Google Cloud Speech-to-Text, etc.
        // Return the recognized command as a string
        return "Recognized Command";
    }

    @PutMapping("/cart/{cartId}")
    public ResponseEntity<Cart> modifyCart(@PathVariable() Integer cartId,@RequestBody() Cart cart){
        cart.setCartId(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.modifyCart(cart));
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Cart> deleteCart(@PathVariable() Integer cartId){
        return ResponseEntity.status(HttpStatus.FOUND).body(cartService.deleteCart(cartId));
    }



//    @GetMapping("/cart/user/{userId}")
//    public ResponseEntity< List<Cart>> getCartByUserId(@PathVariable() Long userId ){
//
//
//       List<Cart> cart =cartService.getCartByUserId(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(cart);
//    }


    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        if (cart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or return an appropriate error message
        }
    }

}
