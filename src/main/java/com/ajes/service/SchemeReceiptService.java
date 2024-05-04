package com.ajes.service;

import com.ajes.model.ModeOfPayment;
import com.ajes.model.Scheme;
import com.ajes.model.SchemeCustomerReceipt;
import com.ajes.model.SchemeReceipts;
import com.ajes.repository.SchemeReceiptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;

@Service
public class SchemeReceiptService {

    @Autowired
    private SchemeReceiptsRepository schemeReceiptsRepository;

    @Autowired
    private SchemeCustomerReceiptService schemeCustomerReceiptService;

    @Autowired
    private SchemeService schemeService;

    @Autowired
    private ModeOfPaymentService modeOfPaymentService;


    public SchemeReceipts addSchemeReceipts(SchemeReceipts schemeReceipts) {
        Scheme scheme = schemeService.getSchemeById(schemeReceipts.getScheme().getSchemeId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date payementDate = scheme.getStartDate();
        Map<String, Object> map = getSchemeReciptByschemePaidListCustomerRecId1(schemeReceipts.getSchemeCustomerReceipt().getSchemeCustomerRecId());
        List<String> skipedDateList = (List<String>) map.get("skipedDate");

        List<SchemeReceipts> schemeReceiptsList = getAll();
        Integer totalAmount = schemeReceipts.getAmount();
        Integer amountPerReceipt = scheme.getAmountPerMonth();
        int numberOfReceipts = totalAmount / amountPerReceipt;
        int remainingAmount = totalAmount % amountPerReceipt;


//        for (SchemeReceipts schemeReceipts1 : schemeReceiptsList) {
//            if (schemeReceipts1.getScheme().equals(schemeReceipts.getScheme()) &&
//                    schemeReceipts1.getSchemeCustomerReceipt().equals(schemeReceipts.getSchemeCustomerReceipt())) {
//
//                LocalDate localDate = LocalDate.now();
//                Date currentdate = java.sql.Date.valueOf(localDate);
//              String  currentdate1=dateFormat.format(currentdate).substring(0,7);
//
//                if(schemeReceipts1.getSchemePaymentDate().equals(currentdate1)){
//                }
//            }
//        }

        if (totalAmount.equals(amountPerReceipt)) {
            String payementDate1 = dateFormat.format(payementDate).substring(0, 7);
            schemeReceipts.setSchemePaymentDate(payementDate1);

            schemeReceiptsRepository.save(schemeReceipts);
            return schemeReceipts;
        } else {
            for (int i = 0; i < numberOfReceipts; i++) {
                SchemeReceipts newReceipt = new SchemeReceipts();
                newReceipt.setAmount(amountPerReceipt);
                newReceipt.setScheme(schemeReceipts.getScheme());
                newReceipt.setPaymentDate(schemeReceipts.getPaymentDate());
                newReceipt.setScheme(schemeReceipts.getScheme());
                newReceipt.setSchemeCustomerReceipt(schemeReceipts.getSchemeCustomerReceipt());
                newReceipt.setModeOfPayment(schemeReceipts.getModeOfPayment());
                newReceipt.setSchemePaymentDate(skipedDateList.get(i));

                schemeReceiptsRepository.save(newReceipt);
            }

//            if (remainingAmount > 0) {
//                SchemeReceipts remainingReceipt = new SchemeReceipts();
//                remainingReceipt.setAmount(remainingAmount);
//                remainingReceipt.setScheme(schemeReceipts.getScheme());
//                remainingReceipt.setPaymentDate(schemeReceipts.getPaymentDate());
//
//                schemeReceiptsRepository.save(remainingReceipt);
//            }
        }
        return schemeReceipts;

    }


    public List<SchemeReceipts> getAll() {
        return schemeReceiptsRepository.findAll();
    }

    public List<SchemeReceipts> getByCustomerName(String customerName) {
        SchemeCustomerReceipt schemeCustomerReceipt = schemeCustomerReceiptService.getByCustomerName(customerName);

        List<SchemeReceipts> list = getAll();
        List<SchemeReceipts> list2 = new ArrayList<>();

        for (SchemeReceipts schemeReceipts : list) {
            if (schemeReceipts.getSchemeCustomerReceipt().getCustomerName().equals(customerName)) {
                list2.add(schemeReceipts);
            }
        }
        return list2;
    }

    public List<SchemeReceipts> getByScheme(Integer schemeId) {
        List<SchemeReceipts> list = getAll();

        List<SchemeReceipts> list2 = new ArrayList<>();

        Scheme scheme = schemeService.getSchemeById(schemeId);


        for (SchemeReceipts schemeReceipts : list) {
            if (schemeReceipts.getScheme().equals(scheme)) {
                list2.add(schemeReceipts);
            }
        }
        return list2;
    }

    public SchemeReceipts getById(Integer schemeReceiptsId) {
        return schemeReceiptsRepository.getById(schemeReceiptsId);
    }

    public SchemeReceipts deleteById(Integer schemeReceiptsId) {
        SchemeReceipts schemeReceipts = getById(schemeReceiptsId);
        schemeReceiptsRepository.deleteById(schemeReceiptsId);
        return schemeReceipts;
    }

    public SchemeReceipts updateSchemeReceipts(SchemeReceipts schemeReceipts) {
        return schemeReceiptsRepository.save(schemeReceipts);

    }


    public List<SchemeReceipts> getTodaysSchemeReceipts(Integer modeOfPaymentId) {
        List<SchemeReceipts> allSchemeReceipts = schemeReceiptsRepository.findAll();

        List<ModeOfPayment> allModeOfPayment = modeOfPaymentService.getAll();
        List<SchemeReceipts> todaysschemeReceipts = new ArrayList<>();
        LocalDate date = LocalDate.now();
        ModeOfPayment modeOfPayment = modeOfPaymentService.getModeOfPaymentById(modeOfPaymentId);


        for (SchemeReceipts schemeReceipts : allSchemeReceipts) {
            //  System.out.println(schemeReceipts.getPaymentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            LocalDate date1 = schemeReceipts.getPaymentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date1.equals(date) && schemeReceipts.getModeOfPayment().equals(modeOfPayment)) {

                todaysschemeReceipts.add(schemeReceipts);
            }
        }

        return todaysschemeReceipts;
    }


    public Integer calculateTotalSchemePeriod(Date SchemeStartdate, Date SchemeEnddate) {

        Date SchemeStartdate1 = SchemeStartdate;
        Date SchemeEnddate1 = SchemeEnddate;
        Integer startyear = Integer.parseInt(SchemeStartdate1.toString().substring(0, 4));
        Integer startMonth = Integer.parseInt(SchemeStartdate1.toString().substring(5, 7));
        Integer endyear = Integer.parseInt(SchemeEnddate1.toString().substring(0, 4));
        Integer endMonth = Integer.parseInt(SchemeEnddate1.toString().substring(5, 7));
        Integer schemePeriod = (((endyear - startyear) * 12) + (endMonth - startMonth));
        return schemePeriod + 1;
    }


    public Integer calculateSchemePeriodTillCurrentDate(Date SchemeStartdate, Date currentdate1) {

        Date SchemeStartdate1 = SchemeStartdate;
        Date SchemeEnddate1 = currentdate1;
        Integer startyear = Integer.parseInt(SchemeStartdate1.toString().substring(0, 4));
        Integer startMonth = Integer.parseInt(SchemeStartdate1.toString().substring(5, 7));
        Integer endyear = Integer.parseInt(SchemeEnddate1.toString().substring(0, 4));
        Integer endMonth = Integer.parseInt(SchemeEnddate1.toString().substring(5, 7));
        Integer schemePeriod = (((endyear - startyear) * 12) + (endMonth - startMonth));
        return schemePeriod + 1;
    }


//    public Map<String, Object> getSchemeReciptByschemePaidListCustomerRecId1(Integer schemeCustomerRecId) {
//        List<SchemeReceipts> schemeReceiptsList = schemeReceiptsRepository.findAll();
//        List<SchemeReceipts> ListSchemeReceiptByCustomerpaid = new ArrayList<>();
//        List<String> skipedDate = new ArrayList<>();
//        List<String> paidDate = new ArrayList<>();
//
//        SchemeCustomerReceipt schemeCustomerReceipt = schemeCustomerReceiptService.getById(schemeCustomerRecId);
//        Scheme scheme = schemeService.getSchemeById(schemeCustomerReceipt.getScheme().getSchemeId());
//        int countPaid = 0;
//        int countPay = 0;
//        Date SchemeStartdate = scheme.getStartDate();
//        String SchemeStartdate1 = SchemeStartdate.toString().substring(0, 7);
//        Date SchemeEnddate = scheme.getEndDate();
//        String SchemeEnddate1 = SchemeEnddate.toString().substring(0, 7);
//        Integer totalschemePeriod = calculateTotalSchemePeriod(SchemeStartdate, SchemeEnddate);
//        Double dueamount;
//
//
//        LocalDate localDate = LocalDate.now();
//        Date currentdate1 = java.sql.Date.valueOf(localDate);
//        Integer tillCurrentschemePeriod = calculateSchemePeriodTillCurrentDate(SchemeStartdate, currentdate1);
//        Double  tilldateSchemeAmount= (double) (tillCurrentschemePeriod*scheme.getAmountPerMonth());
//        for (SchemeReceipts schemeReceipts : schemeReceiptsList) {
//            if (schemeReceipts.getScheme().equals(scheme) &&
//                    schemeReceipts.getSchemeCustomerReceipt().equals(schemeCustomerReceipt)) {
//                Date payementDate = schemeReceipts.getPaymentDate();
//                String payementDate1 = payementDate.toString().substring(0, 7);
//
//                String payementDate2 = schemeReceipts.getSchemePaymentDate();
//
//
//                if ((payementDate.after(SchemeStartdate) || payementDate.equals(SchemeStartdate)) &&
//                        (payementDate.before(SchemeEnddate) || payementDate.equals(SchemeEnddate)) || payementDate.after(SchemeEnddate)) {
//                    countPaid++;
//                    paidDate.add(payementDate.toString().substring(0, 7));
//                    ListSchemeReceiptByCustomerpaid.add(schemeReceipts);
//                }
//            }
//
//        }
//
//        String notpaidDate = localDate.toString().substring(0, 7);
//        Date currentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//
////        for (String date : paidDate) {
////            System.out.println("paid" + date + "....." + "not paid" + notpaidDate);
////            if (!date.equals(notpaidDate)) {
////                countPay++;
////                skipedDate.add(notpaidDate);
////                calendar.setTime(currentDate);
////                calendar.add(Calendar.MONTH, -1);
////                Date reducedDate = calendar.getTime();
////                currentDate = reducedDate;
////                notpaidDate = dateFormat.format(reducedDate).substring(0, 7);
////            }
////        }
//        int countnotpaid = tillCurrentschemePeriod - countPaid;
//
//
//        for (int i = 0; i <= countnotpaid; i++) {
//
////            System.out.println("paid" + paidDate + "....." + "not paid" + notpaidDate);
//            if (paidDate.isEmpty()) {
//                System.out.println("array size is zero");
//                if (!SchemeStartdate1.equals(notpaidDate)) {
//                    countPay++;
//                    skipedDate.add(notpaidDate);
//                    calendar.setTime(currentDate);
//                    calendar.add(Calendar.MONTH, -1);
//                    Date reducedDate = calendar.getTime();
//                    currentDate = reducedDate;
//                    notpaidDate = dateFormat.format(reducedDate).substring(0, 7);
//                }
//
//            } else {
//                String lastdate = paidDate.get(paidDate.size() - 1);
//                if (!lastdate.equals(notpaidDate)) {
//                    countPay++;
//                    skipedDate.add(notpaidDate);
//                    calendar.setTime(currentDate);
//                    calendar.add(Calendar.MONTH, -1);
//                    Date reducedDate = calendar.getTime();
//                    currentDate = reducedDate;
//                    notpaidDate = dateFormat.format(reducedDate).substring(0, 7);
//                }
//            }
//
//        }
//
//        List<String> reversedSkipedDate = new ArrayList<>();
//        for (int i = skipedDate.size() - 1; i >= 0; i--) {
//            reversedSkipedDate.add(skipedDate.get(i));
//        }
//
//        dueamount = (double) (scheme.getAmountPerMonth() * countPay);
//        System.out.println("paidcount" + countPaid);
//        System.out.println(" not paid count" + countPay);
//        System.out.println("dueamount" + dueamount);
//        Map<String, Object> map = new HashMap<>();
//        //map.put("SchemeReceipts",ListSchemeReceiptByCustomerpaid);
//        map.put("skipedDate", reversedSkipedDate);
//        map.put("dueamount", dueamount);

//        return map;
//    }


    public Map<String, Object> getSchemeReciptByschemePaidListCustomerRecId1(Integer schemeCustomerRecId) {
        List<SchemeReceipts> schemeReceiptsList = schemeReceiptsRepository.findAll();
        List<SchemeReceipts> ListSchemeReceiptByCustomerpaid = new ArrayList<>();
        List<String> skipedDate = new ArrayList<>();
        List<String> paidDate = new ArrayList<>();

        SchemeCustomerReceipt schemeCustomerReceipt = schemeCustomerReceiptService.getById(schemeCustomerRecId);
        Scheme scheme = schemeService.getSchemeById(schemeCustomerReceipt.getScheme().getSchemeId());
        int countPaid = 0;
        int countPay = 0;
        Date SchemeStartdate = scheme.getStartDate();
        String SchemeStartdate1 = SchemeStartdate.toString().substring(0, 7);
        Date SchemeEnddate = scheme.getEndDate();
        String SchemeEnddate1 = SchemeEnddate.toString().substring(0, 7);
        Integer totalschemePeriod = calculateTotalSchemePeriod(SchemeStartdate, SchemeEnddate);
        int totalSchemeAmount = totalschemePeriod * scheme.getAmountPerMonth();
        int dueamount;


        LocalDate localDate = LocalDate.now();
        Date currentdate1 = java.sql.Date.valueOf(localDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate2 = dateFormat.format(currentdate1).substring(0, 7);
        Integer tillCurrentschemePeriod = calculateSchemePeriodTillCurrentDate(SchemeStartdate, currentdate1);
        int tilldateSchemeAmount = (tillCurrentschemePeriod * scheme.getAmountPerMonth());

        String notpaidDate = localDate.toString().substring(0, 7);
        Integer tilldatePaidAmount = 0;
        for (SchemeReceipts schemeReceipts : schemeReceiptsList) {


            if (schemeReceipts.getScheme().equals(scheme) &&
                    schemeReceipts.getSchemeCustomerReceipt().equals(schemeCustomerReceipt)) {
                String paidDate1 = schemeReceipts.getSchemePaymentDate();
                if (paidDate1.compareTo(currentDate2) <= 0) {

                    Integer paidAmount = schemeReceipts.getAmount();
                    tilldatePaidAmount += paidAmount;
                    countPaid++;
                    paidDate.add(paidDate1);
                }


            }
        }
        System.out.println(countPaid);

        for (String paidDate1 : paidDate) {

            if (!paidDate1.equals(notpaidDate)) {
                countPay++;
                skipedDate.add(notpaidDate);

                Date currentDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.MONTH, -1);
                Date reducedDate = calendar.getTime();
                currentDate = reducedDate;
                notpaidDate = dateFormat.format(reducedDate).substring(0, 7);
            }
        }
        dueamount = (countPay * scheme.getAmountPerMonth());
        System.out.println("Total Scheme..." + totalschemePeriod + "..." + totalSchemeAmount);
        System.out.println("till date scheme...." + tillCurrentschemePeriod + ".." + tilldateSchemeAmount);
        System.out.println("till date paid..." + countPaid + "..." + tilldatePaidAmount);
        System.out.println("due" + countPay + "......" + dueamount);


        Map<String, Object> map = new HashMap<>();
        List<String> reversedSkipedDate = new ArrayList<>();
        for (int i = skipedDate.size() - 1; i >= 0; i--) {
            reversedSkipedDate.add(skipedDate.get(i));
        }
        map.put("skipedDate", reversedSkipedDate);
        map.put("dueamount", dueamount);


        return map;
    }


//    public Map<String, Object> getSchemeReciptByschemePaidListCustomerRecId1(Integer schemeCustomerRecId) {
//        System.out.println("...............");
//        List<SchemeReceipts> schemeReceiptsList = schemeReceiptsRepository.findAll();
//        List<SchemeReceipts> ListSchemeReceiptByCustomerpaid = new ArrayList<>();
//        List<String> skipedDate = new ArrayList<>();
//        List<String> paidDate = new ArrayList<>();
//
//        SchemeCustomerReceipt schemeCustomerReceipt = schemeCustomerReceiptService.getById(schemeCustomerRecId);
//        Scheme scheme = schemeService.getSchemeById(schemeCustomerReceipt.getScheme().getSchemeId());
//        int countPaid = 0;
//        int countPay = 0;
//        Date SchemeStartdate = scheme.getStartDate();
//        String SchemeStartdate1 = SchemeStartdate.toString().substring(0, 7);
//        Date SchemeEnddate = scheme.getEndDate();
//        String SchemeEnddate1 = SchemeEnddate.toString().substring(0, 7);
//        Integer totalschemePeriod = calculateTotalSchemePeriod(SchemeStartdate, SchemeEnddate);
//        int totalSchemeAmount = totalschemePeriod * scheme.getAmountPerMonth();
//        int dueamount;
//        LocalDate localDate = LocalDate.now();
//        Date currentdate1 = java.sql.Date.valueOf(localDate);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDate2 = dateFormat.format(currentdate1).substring(0, 7);
//        Integer tillCurrentschemePeriod = calculateSchemePeriodTillCurrentDate(SchemeStartdate, currentdate1);
//        int tilldateSchemeAmount = (tillCurrentschemePeriod * scheme.getAmountPerMonth());
//        String notpaidDate = localDate.toString().substring(0, 7);
//        Integer tilldatePaidAmount = 0;
//
//        for (SchemeReceipts schemeReceipts : schemeReceiptsList) {
//            if (schemeReceipts.getScheme().equals(scheme) &&
//                    schemeReceipts.getSchemeCustomerReceipt().equals(schemeCustomerReceipt)) {
//                String paidDate1 = schemeReceipts.getSchemePaymentDate();
//                if (paidDate1.compareTo(currentDate2) <= 0) {
//                    Integer paidAmount = schemeReceipts.getAmount();
//                    tilldatePaidAmount += paidAmount;
//                    countPaid++;
//                    paidDate.add(paidDate1);
//                }
//            }
//        }
//        for (String paidDate1:paidDate){
//            System.out.println();
//        }
//
//        return null;
//    }
//
//


}
