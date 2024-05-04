package com.ajes.service;

import com.ajes.model.Customer;
import com.ajes.model.Product;
import com.ajes.model.ProductPurchase;
import com.ajes.model.Stock;
import com.ajes.repository.ProductPurchaseRepository;
import com.ajes.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPurchaseService {


    @Autowired
    private ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    //It is post method(body)
    public ProductPurchase addProductPurchase(ProductPurchase productPurchase) {
        Stock stock = new Stock();
        ProductPurchase productPurchase1 = productPurchaseRepository.save(productPurchase);
        List<Stock> stockList = stockService.getAll();

        if (stockList.isEmpty()) {

            stock.setProductPurchase(productPurchase1);
            stock.setProduct(productPurchase1.getProduct());
            stock.setLogin(productPurchase1.getLogin());
            stock.setCount(1);
            stock.setMinQty( 5);
            stock.setStockQtyInHand(productPurchase1.getQuantity());
            stock.setTotalStock( productPurchase1.getQuantity());
            stockRepository.save(stock);
        } else {

            for (Stock existingStock : stockList) {
                if (existingStock.getProduct().getProductId().equals(productPurchase1.getProduct().getProductId())) {

                    existingStock.setTotalStock(existingStock.getTotalStock() + productPurchase1.getQuantity());
                    existingStock.setStockQtyInHand(existingStock.getStockQtyInHand() + productPurchase1.getQuantity());
                    existingStock.setCount(existingStock.getCount()+1);

                    stockRepository.save(existingStock);
                    return productPurchase1;
                }
            }

            stock.setProductPurchase(productPurchase1);
            stock.setProduct(productPurchase1.getProduct());
            stock.setLogin(productPurchase1.getLogin());
            stock.setCount(1);
            stock.setMinQty(5);
            stock.setStockQtyInHand(productPurchase1.getQuantity());
            stock.setTotalStock( productPurchase1.getQuantity());
            stockRepository.save(stock);
        }

        return productPurchase1;
    }


    //It is get method(head)
    public List<ProductPurchase> getAll() {
        return productPurchaseRepository.findAll();
    }

    public List<ProductPurchase> getAllOnTodayDate() {
        String date11 = LocalDateTime.now().toString().substring(0, 10);

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<ProductPurchase> list = getAll();
        List<ProductPurchase> list1 = new ArrayList<>();

        try {
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate.substring(0,10));
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);

            for (ProductPurchase productPurchase : list) {
                String date = productPurchase.getTrDate().toString().substring(0, 10);
                System.out.println(date);
                Date date3 = dateFormat.parse(date);
                System.out.println(date3);

//                int comparisonResult1 = date3.compareTo(date11);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date.equals(date11)) {
                    list1.add(productPurchase);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    public List<ProductPurchase> getFromDateAndToDate(String fromDate, String toDate) {
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<ProductPurchase> list = getAll();
        List<ProductPurchase> list1 = new ArrayList<>();

        try {
            Date date1 = dateFormat.parse(fromDate.substring(0, 10));
            Date date2 = dateFormat.parse(toDate.substring(0, 10));
            System.out.println("Converted Date: " + date1);
            System.out.println("Converted Date: " + date2);

            for (ProductPurchase productPurchase : list) {
                String date = productPurchase.getTrDate().toString();
                System.out.println(date);
                Date date3 = dateFormat.parse(date);
                System.out.println(date3);

                int comparisonResult1 = date3.compareTo(date1);
                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                    list1.add(productPurchase);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    //It is get method(head)
    public ProductPurchase getProductPurchaseById(Long productPurchaseId) {
        Optional<ProductPurchase> optional = productPurchaseRepository.findById(productPurchaseId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    //it is put method(head and body)
    public ProductPurchase modifyProductPurchase(ProductPurchase productPurchase) {;
        return productPurchaseRepository.save(productPurchase);
    }

    //it is delete method(head)
    public ProductPurchase deleteProductPurchase(Long productPurchaseId) {
        ProductPurchase productPurchase = getProductPurchaseById(productPurchaseId);
        productPurchaseRepository.deleteById(productPurchaseId);
        return productPurchase;
    }
}
