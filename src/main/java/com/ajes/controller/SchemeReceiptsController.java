package com.ajes.controller;

import com.ajes.model.SchemeCustomerReceipt;
import com.ajes.model.SchemeReceipts;
import com.ajes.service.SchemeReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")

public class SchemeReceiptsController {

    @Autowired
    private SchemeReceiptService schemeReceiptService;

    @PostMapping("/schemeReceipts")
    public SchemeReceipts addSchemeReceipts(@RequestBody() SchemeReceipts schemeReceipts) {


        return schemeReceiptService.addSchemeReceipts(schemeReceipts);
    }

    @GetMapping("/schemeReceipts/")
    public List<SchemeReceipts> getAll() {
        return schemeReceiptService.getAll();
    }

    @GetMapping("/schemeReceipts/customer/{customerName}")
    public List<SchemeReceipts> getByCustomerName(@PathVariable() String customerName) {
        return schemeReceiptService.getByCustomerName(customerName);
    }

    @GetMapping("/schemeReceipts/scheme/{schemeId}")
    public List<SchemeReceipts> getBySchemeId(@PathVariable() Integer schemeId) {
        return schemeReceiptService.getByScheme(schemeId);
    }

    @GetMapping("/schemeReceipts/{schemeReceiptsId}")
    public SchemeReceipts getById(@PathVariable() Integer schemeReceiptsId) {
        return schemeReceiptService.getById(schemeReceiptsId);
    }

    @DeleteMapping("/schemeReceipts/{schemeReceiptsId}")
    public SchemeReceipts deleteById(@PathVariable() Integer schemeReceiptsId) {
        return schemeReceiptService.deleteById(schemeReceiptsId);
    }

    @PutMapping("/schemeReceipts/{schemeReceiptsId}")
    public SchemeReceipts updateById(@PathVariable() Integer schemeReceiptsId, @RequestBody() SchemeReceipts schemeReceipts) {
        schemeReceipts.setSchemeReceiptId(schemeReceiptsId);
        return schemeReceiptService.updateSchemeReceipts(schemeReceipts);
    }


    @GetMapping("/schemeReceipts/todays/{modeOfPaymentId}")
    public List<SchemeReceipts> getTodaysSchemeReceipts(@PathVariable() Integer modeOfPaymentId) {
        List<SchemeReceipts> list = schemeReceiptService.getTodaysSchemeReceipts(modeOfPaymentId);
        return list;
    }



    @GetMapping("/schemeReceiptsPaidList/schemeCustomerRec/{schemeCustomerRecId}")
    public Map<String,Object> getSchemeReciptByschemePaidListCustomerRecId(@PathVariable() Integer schemeCustomerRecId) {

       Map<String,Object> map = schemeReceiptService.getSchemeReciptByschemePaidListCustomerRecId1(schemeCustomerRecId);
        return map;
    }



}
