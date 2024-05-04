package com.ajes.service;

import com.ajes.model.Scheme;
import com.ajes.model.SchemeCustomerReceipt;
import com.ajes.repository.SchemeCustomerReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchemeCustomerReceiptService {

    @Autowired
    private SchemeCustomerReceiptRepository schemeCustomerReceiptRepository;

    @Autowired
    private SchemeService schemeService;

    public SchemeCustomerReceipt addSchemeCustomerReceipt(SchemeCustomerReceipt schemeCustomerReceipt) {
        Scheme scheme = schemeService.getSchemeById(schemeCustomerReceipt.getScheme().getSchemeId());
        int totalSizeCustomer = scheme.getTotalSizeCustomer();
        List<SchemeCustomerReceipt> schemeCustomerReceiptList = getSchemeCustomerReceiptByschemeId(schemeCustomerReceipt.getScheme().getSchemeId());

        if (schemeCustomerReceiptList.isEmpty()) {
            return schemeCustomerReceiptRepository.save(schemeCustomerReceipt);
        } else {
            System.out.println("1111111111111111111111111111111111");
            int luckyNumberToCheck = schemeCustomerReceipt.getCustomerLuckyNo();
            boolean isLuckyNumberPresent = false;


            for (SchemeCustomerReceipt receipt : schemeCustomerReceiptList) {
                System.out.println("2222222222222222222222222222");
                if (receipt.getCustomerLuckyNo() == luckyNumberToCheck) {
                    System.out.println("333333333333333333333333333");
                    isLuckyNumberPresent = true;
                    break;

                }
                System.out.println("444444444444444444444444444444444");
            }


            if (!isLuckyNumberPresent && luckyNumberToCheck <= totalSizeCustomer) {
                System.out.println("5555555555555555555555555555555555555");
                return schemeCustomerReceiptRepository.save(schemeCustomerReceipt);
            }
        }

        return null;
    }

    public List<SchemeCustomerReceipt> getAll() {
        return schemeCustomerReceiptRepository.findAll();
    }

    public SchemeCustomerReceipt getById(Integer schemeCustomerReceiptId) {
        return schemeCustomerReceiptRepository.findById(schemeCustomerReceiptId).get();
    }

    public SchemeCustomerReceipt getByCustomerName(String customerName) {

        List<SchemeCustomerReceipt> list = getAll();

        for (SchemeCustomerReceipt schemeCustomerReceipt : list) {
            if (schemeCustomerReceipt.getCustomerName().equals(customerName)) {
                return schemeCustomerReceipt;
            }
        }
        return null;
    }

    public SchemeCustomerReceipt updateSchemeCustomerReceipt(SchemeCustomerReceipt schemeCustomerReceipt) {
        return schemeCustomerReceiptRepository.save(schemeCustomerReceipt);
    }

    public SchemeCustomerReceipt deleteSchemeCustomerReceipt(Integer schemeCustomerReceiptId) {
        SchemeCustomerReceipt schemeCustomerReceipt = getById(schemeCustomerReceiptId);
        schemeCustomerReceiptRepository.deleteById(schemeCustomerReceiptId);
        return schemeCustomerReceipt;
    }


    public List<SchemeCustomerReceipt> getSchemeCustomerReceiptByschemeId(Integer schemeId) {
        Scheme scheme = schemeService.getSchemeById(schemeId);

        List<SchemeCustomerReceipt> schemeCustomerReceiptList = schemeCustomerReceiptRepository.findAll();
        List<SchemeCustomerReceipt> schemeCustomerReceiptByschemeList = new ArrayList<>();
        for (SchemeCustomerReceipt schemeCustomerReceipt : schemeCustomerReceiptList) {
            if (schemeCustomerReceipt.getScheme().equals(scheme)) {
                schemeCustomerReceiptByschemeList.add(schemeCustomerReceipt);
            }
        }
        return schemeCustomerReceiptByschemeList;
    }

}
