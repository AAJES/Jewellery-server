package com.ajes.service;

import com.ajes.model.Booking;
import com.ajes.model.Customer;
import com.ajes.model.Product;
import com.ajes.model.Stock;
import com.ajes.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductService productService;

    //It is post method(body)
    public Stock addStock(Stock stock) {
        stock.setTotalStock(stock.getCount() - stock.getStockQtyInHand());


        return stockRepository.save(stock);
    }

    //It is get method(head)
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    public Stock getStockByProduct(Long productId) {
        List<Stock> list = getAll();
         Product product = productService.getProductById(productId);

        for (Stock s : list) {
            if (s.getProductPurchase().getProduct().equals(product)) {

                return s;
            }
        }
        return null;
    }


    //It is get method(head)
    public Stock getStockById(Long stockId) {
        Optional<Stock> optional = stockRepository.findById(stockId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    public List<Stock> getStockFromDateToDate(String fromDate, String toDate) {
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Stock> list = getAll();
        List<Stock> list1 = new ArrayList<>();

        try {
            Date date1 = dateFormat.parse(fromDate.substring(0, 10));
            Date date2 = dateFormat.parse(toDate.substring(0, 10));


            for (Stock stock : list) {
                String date = stock.getTrDate().toString();

                Date date3 = dateFormat.parse(date);


                int comparisonResult1 = date3.compareTo(date1);
                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                    list1.add(stock);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    //it is put method(head and body)
    public Stock modifyStock(Stock stock,int q) {
        Stock stock1=stockRepository.findById(stock.getStockId()).get();



        stock1.setStockQtyInHand(stock1.getStockQtyInHand()-q);
       stockRepository.save(stock1);

        return stock1;
    }

    //it is delete method(head)
    public Stock deleteStock(Long stockId) {
        Stock stock = getStockById(stockId);
        stockRepository.deleteById(stockId);
        return stock;
    }



}
