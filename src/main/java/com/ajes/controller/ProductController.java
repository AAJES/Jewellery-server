package com.ajes.controller;

import com.ajes.model.*;
import com.ajes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody() Product product) {


        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }
    @PostMapping("/api/translate")
    public ResponseEntity<String> translate(@RequestBody TranslationRequest request) {
        String translatedText = productService.translateText(request.getText(), request.getTargetLanguage());
        if (translatedText != null) {
            return ResponseEntity.ok(translatedText);
        } else {
            return ResponseEntity.badRequest().body("Translation failed.");
        }
    }

    @GetMapping("/product/")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getAll());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getByProductId(@PathVariable() Long productId) {


        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductById(productId));
    }

    @GetMapping("/filter")
    public List<Product> filterAndSortProducts(
            @RequestParam(name = "category", required = false) Category category,
            @RequestParam(name = "metal", required = false) Metal metal,
            @RequestParam(name = "purity", required = false) Purity purity) {
        return productService.filterAndSortProducts(category, metal, purity);
    }
    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable() Integer categoryId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductByCategory(categoryId));
    }
    @GetMapping("/product/metal/{metalId}")
    public List<Product> getProductByMetal(@PathVariable() Integer metalId) {
        return productService.getProductByMetal(metalId);
    }
    @GetMapping("/product/purity/{purityId}")
    public List<Product> getProductByPurity(@PathVariable() Integer purityId) {
        return productService.getProductByPurity(purityId);
    }
    @GetMapping("/product/category1/{categoryId}/metal1/{metalId}")
    public List<Product> getProductByCategoryByMetal(@PathVariable() Integer categoryId, @PathVariable() Integer metalId) {
        return productService.getProductByCategoryByMetal(categoryId, metalId);
    }

    @GetMapping("/product/metal1/{metalId}/purity/{purityId}")
    public List<Product> getProductByMetalByPurity(@PathVariable() Integer metalId, @PathVariable() Integer purityId) {
        return productService.getProductByMetalByPurity(metalId, purityId);
    }

    @GetMapping("/product/category2/{categoryId}/purity2/{purityId}")
    public List<Product> getProductByCategoryByPurity(@PathVariable() Integer categoryId, @PathVariable() Integer purityId) {
        return productService.getProductByCategoryByPurity(categoryId, purityId);
    }

    @GetMapping("/product/category/{categoryId}/metal/{metalId}/purity/{purityId}")
    public List<Product> getProductByCategoryByMetalByPurity(@PathVariable() Integer categoryId, @PathVariable() Integer metalId, @PathVariable() Integer purityId) {

        return productService.getProductByCategoryByMetalByPurity(categoryId, metalId, purityId);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> modifyProduct(@PathVariable() Long productId, @RequestBody() Product product) {
        product.setProductId(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.modifyProduct(product));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable() Long productId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.deleteProduct(productId));
    }

    @PostMapping("/product/booking/{bookingId}")
    public Product addProductByBooking(@RequestBody() Product product, @PathVariable Integer bookingId) {
        Product product1 = productService.addProductByBooking(product, bookingId);
        return product1;
    }

        @GetMapping("/product/booking/{bookingId}")
    public Product getProductByBooking( @PathVariable Integer bookingId) {
        Product product1 = productService.getProductByBookinId(bookingId);
        return product1;
    }
}
