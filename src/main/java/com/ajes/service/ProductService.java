package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.ProductRepository;
import com.ajes.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MetalService metalService;

    @Autowired
    private PurityService purityService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private BookingService bookingService;


    @Autowired
    private QuantityService quantityService;

    @Autowired
    @Lazy
    private ProductPurchaseService productPurchaseService;


    private final Map<String, Map<String, String>> translations = new HashMap<>();

    //It is post method(body)
    public Product addProduct(Product product) {


//        Product product1 = productRepository.save(product);
//        Float amount = product1.getRate().getRatePerGram()*product1.getWeightage();
//        product1.setTotalRate(amount);
        if (product.getMetal().getMetalName().equals("Silver")) {
            product.setPurity(null);
        }
//        if (product.getBooking().getBookingId() == null) {
//            Booking booking = new Booking();
//
//        }


        Quantity quantity = new Quantity();
        quantity.setQuantity(1);
        quantityService.addQuantity(quantity);

        product.setQuantity(quantity);
        product.setBooking(null);
        productRepository.save(product);


        return product;


    }

    //It is get method(head)
    public List<Product> getAll() {

//        List<Product> list = productRepository.findAll();
//
////        List<Product> list1 = new ArrayList<>();
////
////        for(Product p : list){
////            Comparator<Product> nameComparator = (p1, p2) -> p1.getProductName().compareTo(p2.getProductName());
////
////            // Sort the list of people using the custom Comparator
////            Collections.sort(list, nameComparator);
////        }
////
////        // Create a custom Comparator to sort by name
////
////        //return productRepository.findAll();
////        return list;
//
//        List<Product> list1 =  list.stream()
//                .sorted()
//                .collect(Collectors.toList());
//
//        for(Product p : list1){
//            System.out.println(p);
//        }
//
//        return list1;

        Comparator<Product> productComparator = (p1, p2) -> p1.getProductName().compareTo(p2.getProductName());

        List<Product> list = productRepository.findAll();

        List<Product> list1 = list.stream()
                .sorted(productComparator)
                .collect(Collectors.toList());

//        for (Product p : list1) {
//            System.out.println(p);
//        }

        return list1;
    }

    //It is get method(head)
    public Product getProductById(Long productId) {


        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    public List<Product> sortProducts(List<Product> list) {
        Comparator<Product> productComparator = (p1, p2) -> p1.getProductName().compareTo(p2.getProductName());

        //list = productRepository.findAll();

        List<Product> list1 = list.stream()
                .sorted(productComparator)
                .collect(Collectors.toList());


        Comparator<Product> productComparator1 = (p1, p2) -> (p1.getMetal().getMetalName()).compareTo(p2.getMetal().getMetalName());

        List<Product> list2 = list1.stream()
                .sorted(productComparator1)
                .collect(Collectors.toList());

        for (Product p : list2) {

        }

        return list2;
    }

        public List<Product> getProductByCategory(Integer categoryId) {

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getCategory().getCategoryId().equals(categoryId)) {
                list1.add(p);
                list2 = sortProducts(list1);
            }
        }

        return list2;
    }

    public List<Product> getProductByMetal(Integer metalId) {
        Metal metal = metalService.getMetalById(metalId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getMetal().equals(metal)) {
                list1.add(p);
                list2 = sortProducts(list1);
            }
        }

        return list2;
    }

    public List<Product> getProductByPurity(Integer purityId) {
        Purity purity = purityService.getPurityById(purityId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getPurity().equals(purity)) {
                list1.add(p);
                list2 = sortProducts(list1);
            }
        }
        return list2;
    }

    public List<Product> getProductByCategoryByMetalByPurity(Integer categoryId, Integer metalId, Integer purityId) {
        Category category = categoryService.getCategoryById(categoryId);

        Metal metal = metalService.getMetalById(metalId);

        Purity purity = purityService.getPurityById(purityId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getCategory().equals(category)) {
                if (p.getMetal().equals(metal)) {
                    if (p.getPurity().equals(purity)) {
                        list1.add(p);
                        list2 = sortProducts(list1);
                    }
                }
            }
        }
        return list2;
    }

    public List<Product> getProductByCategoryByMetal(Integer categoryId, Integer metalId) {
        Category category = categoryService.getCategoryById(categoryId);

        Metal metal = metalService.getMetalById(metalId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getCategory().equals(category)) {
                if (p.getMetal().equals(metal)) {
                    list1.add(p);
                    list2 = sortProducts(list1);
                }
            }
        }
        return list2;
    }

    public List<Product> getProductByMetalByPurity(Integer metalId, Integer purityId) {
        Metal metal = metalService.getMetalById(metalId);

        Purity purity = purityService.getPurityById(purityId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getMetal().equals(metal)) {
                if (p.getPurity().equals(purity)) {
                    list1.add(p);
                    list2 = sortProducts(list1);
                }
            }
        }
        return list2;
    }

    public List<Product> getProductByCategoryByPurity(Integer categoryId, Integer purityId) {
        Category category = categoryService.getCategoryById(categoryId);

        Purity purity = purityService.getPurityById(purityId);

        List<Product> list = getAll();

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();

        for (Product p : list) {
            if (p.getCategory().equals(category)) {
                if (p.getPurity().equals(purity)) {
                    list1.add(p);
                    list2 = sortProducts(list1);
                }
            }
        }
        return list2;
    }

//    public List<Product> filterAndSortProducts(String category, String metal, String purity) {
//        Specification<Product> spec = (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (!StringUtils.isEmpty(category)) {
//                predicates.add(cb.equal(root.get("category").get("name"), category));
//            }
//
//            if (!StringUtils.isEmpty(metal)) {
//                predicates.add(cb.equal(root.get("metal").get("name"), metal));
//            }
//
//            if (!StringUtils.isEmpty(purity)) {
//                predicates.add(cb.equal(root.get("purity").get("name"), purity));
//            }
//
//            return cb.and((javax.persistence.criteria.Predicate) predicates.toArray(new Predicate[0]));
//        };
//
//        return productRepository.findAll(spec);
//    }

    public List<Product> filterAndSortProducts(Category category, Metal metal, Purity purity) {
        return productRepository.filterAndSortProducts(category, metal, purity);
    }


    //it is put method(head and body)
    public Product modifyProduct(Product product) {

        System.out.println("modifyProduct.......................................");

        return productRepository.save(product);
    }

    //it is delete method(head)
    public Product deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.deleteById(productId);
        return product;
    }

    @Value("${google.api.key}") // Load API key from configuration
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    public ProductService() {
        this.initializeTranslations();
    }


    private void initializeTranslations() {
        // Sample translations stored in a Map
        //Map<String, String> frenchTranslations = new HashMap<>();
        Map<String, String> kannadaTranslations = new HashMap<>();
        kannadaTranslations.put("Hello", "ಹಲೋ");
        kannadaTranslations.put("How are you?", "ನೀವು ಹೇಗಿದ್ದೀರಿ?");
        translations.put("kn", kannadaTranslations);

        // Add more language translations as needed
    }

    public String translateText(String text, String targetLanguage) {
        Map<String, String> languageTranslations = translations.get(targetLanguage);
        if (languageTranslations != null) {
            String translatedText = languageTranslations.get(text);
            if (translatedText != null) {
                return translatedText;
            }
        }
        return "Translation not available.";
    }


    public Product addProductByBooking(Product product, Integer bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        Booking booking1 = new Booking();

        System.out.println(booking.toString());
        Product product1 = new Product();
        Quantity quantity = new Quantity();
        System.out.println(product1.toString());
        if (booking.getStatus().equals("Booked")) {
            booking1 = bookingService.modifyBookingtToReady(bookingId);
            quantity.setQuantity(booking1.getQuantity());
            Quantity quantity1 = quantityService.addQuantity(quantity);
            System.out.println(quantity.toString());

            product1.setMetal(booking1.getMetal());
            product1.setPurity(booking1.getPurity());
            product1.setCategory(booking1.getCategory());
            product1.setWastage(product.getWastage());
            product1.setProductName(product.getProductName());
            product1.setDescription(product.getDescription());
            product1.setProductImage(product.getProductImage());
            product1.setWeightage(booking1.getWeight());
            product1.setBooking(booking1);

            product1.setQuantity(quantity1);
            productRepository.save(product1);

        }

/// if booking is ready then product and product purchase and stock will add below automatically


//        if (booking.getStatus().equals("Ready")) {
//            System.out.println("Readyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
//            ProductPurchase productPurchase = new ProductPurchase();
//            productPurchase.setEmployee(null);
//            productPurchase.setLogin(null);
//            productPurchase.setUser(null);
//            productPurchase.setTotalWeight(product1.getWeightage());
//            productPurchase.setAmountPaid(0.0f);
//            productPurchase.setStatus("Booked");
//            productPurchase.setQuantity(quantity.getQuantity());
//            productPurchase.setProduct(product1);
//            System.out.println("productPurchaseService......................................................");
//            productPurchaseService.addProductPurchase(productPurchase);
//            System.out.println("addProductPurchase" + productPurchase.toString());
//
//        }
        BookingPayment bookingPayment = new BookingPayment();
        bookingPayment.setBooking(booking1);
        bookingPayment.setProduct(product1);
        bookingPayment.setCustomer(booking1.getCustomer());


        return product1;
    }


    public Product getProductByBookinId(Integer bookingId) {
        List<Product> productList = getAll();
        for (Product product : productList) {
            if (product.getBooking().getBookingId().equals(bookingId)) {
                return product;
            }
        }
        return null;
    }





}
