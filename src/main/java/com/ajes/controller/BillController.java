package com.ajes.controller;

import com.ajes.model.Bill;
import com.ajes.model.Customer;
import com.ajes.service.BillService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill")
    public ResponseEntity<Bill> addBill(@RequestBody() Bill bill) {

        return ResponseEntity.status(HttpStatus.CREATED).body(billService.addBill(bill));
    }

    @GetMapping("/bill/")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(billService.getAll());
    }

    @GetMapping("/bill/{billId}")
    public ResponseEntity<Bill> getByBillId(@PathVariable() Long billId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(billService.getBillById(billId));
    }

    @PutMapping("/bill/{billId}")
    public ResponseEntity<Bill> modifyBill(@PathVariable() Long billId, @RequestBody() Bill bill) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,");
        bill.setBillId(billId);
        return ResponseEntity.status(HttpStatus.CREATED).body(billService.modifyBill(bill));
    }

    @DeleteMapping("/bill/{billId}")
    public ResponseEntity<Bill> deleteBill(@PathVariable() Long billId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(billService.deleteBill(billId));
    }


    @GetMapping("/bill/dates/{customerId}/{formDate}/{toDate}")
    public ResponseEntity<List<Bill>> getAllBillsByCustomerIdAndFromandToDate(
            @PathVariable() Integer customerId,
            @PathVariable() String formDate,
            @PathVariable() String toDate
    ) {

//Integer customerId =Integer.parseInt(customerID);

        List<Bill> list = billService.getAllBillsByCustomerIdAndFromandToDate(customerId, formDate, toDate);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }




    @GetMapping("/bill/dailyBilling")
    public ResponseEntity< List<Bill>> getDailyBillReport(){
        List<Bill> list=billService.getDailyBillReport();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
