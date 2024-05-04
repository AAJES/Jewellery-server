package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerService customerService;


    @Autowired
    private StockService stockService;


    @Autowired
    private  ProductService productService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;



    //It is post method(body)
    public Bill addBill(Bill bill) {


        bill.setGst(3.0f);
        bill.setActualAmount(bill.getActualAmount() - bill.getDiscountAmount());
        Float amountPaid = (bill.getAmountPaid() + bill.getAmountPaid() * 0.03f);
        bill.setAmountPaid(amountPaid);
        Bill bill1 = billRepository.save(bill);
        Integer year = Year.now().getValue();
        bill1.setBillNumber(year + "_" + bill1.getBillId());
        Bill bill2 = billRepository.save(bill1);


        for (int i = 0; i < bill2.getProducts().size(); i++) {

            Long id=bill2.getProducts().get(i).getProductId();
            Stock stock= stockService.getStockByProduct(id);
            stockService.modifyStock(stock,bill2.getProducts().get(i).getQuantity().getQuantity());
//            productService.

        }
//        TransactionHistory transactionHistory =new TransactionHistory();
//        transactionHistory.setBill(bill2);
//        transactionHistory.setPayment(bill2.getPayment());
//        transactionHistory.setTransactionAmount(bill.getAmountPaid());
//        transactionHistoryService.addTransaction(transactionHistory);

        return bill2;
    }

    //It is get method(head)
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    //It is get method(head)
    public Bill getBillById(Long billId) {
        Optional<Bill> optional = billRepository.findById(billId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    //it is put method(head and body)
    public Bill modifyBill(Bill bill) {
        billRepository.save(bill);


        return bill;
    }

    //it is delete method(head)
    public Bill deleteBill(Long billId) {
        Bill bill = getBillById(billId);
        billRepository.deleteById(billId);



        return bill;

    }


    public List<Bill> getAllBillsByCustomerIdAndFromandToDate(Integer customerId, String formDate, String toDate) {

        List<Bill> allBills = billRepository.findAll();

        List<Bill> getAllBillsByFromandToDate = new ArrayList<>();
        List<Customer> allCustomer = customerService.getAll();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        if (customerId == 0 && formDate != null && toDate != null && formDate.length() > 0 && toDate.length() > 0) {

            {
                try {
                    Date date1 = simpleDateFormat.parse(formDate);
                    Date date2 = simpleDateFormat.parse(toDate);


                    for (Bill bill : allBills) {
                        String trDate = bill.getTrdate().toString();
                        Date date3 = simpleDateFormat.parse(trDate);


//                        if (bill.getTrdate().after(date2) && bill.getTrdate().before(date1)) {
//                            System.out.println(">>>>>>>>>>>>>>>>>>");
//                            getAllBillsByFromandToDate.add(bill);
//                        }

                        if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {

                            getAllBillsByFromandToDate.add(bill);
                        }

                    }


                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (customerId != 0 && formDate != null && toDate != null && formDate.length() >= 0 && toDate.length() >= 0) {

            Customer customer = customerService.getCustomerById(customerId);
            {
                try {
                    Date date1 = simpleDateFormat.parse(formDate);
                    Date date2 = simpleDateFormat.parse(toDate);

                    for (Bill bill : allBills) {
                        String trDate = bill.getTrdate().toString();
                        Date date3 = simpleDateFormat.parse(trDate);
                        if (bill.getCustomer().equals(customer)) {

                            if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                                getAllBillsByFromandToDate.add(bill);
                            }
                        }

                    }


                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }


        }


        return getAllBillsByFromandToDate;
    }

    public List<Bill> getDailyBillReport() {
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        List<Bill> billList = billRepository.findAll();

        List<Bill> todaysBill = new ArrayList<>();
        for (Bill bill : billList) {
            LocalDate trDate = bill.getTrdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (trDate.equals(date)) {

                todaysBill.add(bill);
            }
        }

        return todaysBill;

    }


    public Bill getBillByPaymentId( Long paymentId){

        List <Bill> billList=billRepository.findAll();

        for(Bill bill:billList){
            if(bill.getPayment().getPaymentId().equals(paymentId)){
                return bill;
            }
        }
        return null;
    }


}
