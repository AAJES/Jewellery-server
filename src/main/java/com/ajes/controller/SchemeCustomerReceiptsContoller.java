package com.ajes.controller;

import com.ajes.model.Scheme;
import com.ajes.model.SchemeCustomerReceipt;
import com.ajes.model.SchemeReceipts;
import com.ajes.service.SchemeCustomerReceiptService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class SchemeCustomerReceiptsContoller {

    @Autowired
    private SchemeCustomerReceiptService schemeCustomerReceiptService;

    @PostMapping("/schemeCustomerReceipt")
    public SchemeCustomerReceipt addSchemeCustomerReceipt(@RequestBody() SchemeCustomerReceipt schemeCustomerReceipt) {
        return schemeCustomerReceiptService.addSchemeCustomerReceipt(schemeCustomerReceipt);
    }

    @GetMapping("/schemeCustomerReceipt/")
    public List<SchemeCustomerReceipt> getAll() {
        return schemeCustomerReceiptService.getAll();
    }

    @GetMapping("/schemeCustomerReceipt/{schemeCustomerReceiptId}")
    public SchemeCustomerReceipt getById(@PathVariable() Integer schemeCustomerReceiptId) {
        return schemeCustomerReceiptService.getById(schemeCustomerReceiptId);
    }

    @DeleteMapping("/schemeCustomerReceipt/{schemeCustomerReceiptId}")
    public SchemeCustomerReceipt deleteById(@PathVariable() Integer schemeCustomerReceiptId) {
        return schemeCustomerReceiptService.deleteSchemeCustomerReceipt(schemeCustomerReceiptId);
    }

    @PutMapping("/schemeCustomerReceipt/{schemeCustomerReceiptId}")
    public SchemeCustomerReceipt updateById(@PathVariable() Integer schemeCustomerReceiptId, @RequestBody() SchemeCustomerReceipt schemeCustomerReceipt) {
        schemeCustomerReceipt.setSchemeCustomerRecId(schemeCustomerReceiptId);
        return schemeCustomerReceiptService.updateSchemeCustomerReceipt(schemeCustomerReceipt);
    }


    @GetMapping("/schemeCustomerReceipt/scheme/{schemeId}")
    public List<SchemeCustomerReceipt> getSchemeCustomerReceiptByschemeId(@PathVariable() Integer schemeId) {
        return schemeCustomerReceiptService.getSchemeCustomerReceiptByschemeId(schemeId);
    }


}
