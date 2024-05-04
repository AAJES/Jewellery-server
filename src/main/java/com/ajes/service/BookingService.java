package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private IdFormService idFormService;

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;

    //It is post method(body)
    public Booking addBooking(Booking booking) {

        String currentDate = LocalDate.now().toString();

        booking.setBookingDate(new Date());
        List<Rate> rateList = rateService.getAll();
        for (Rate rate : rateList) {

            Date toDate = rate.getTrDate();
            String toDateStr = toDate.toString().substring(0, 10);
            if (toDateStr.equals(currentDate)) {

                if (rate.getPurity().equals(booking.getPurity())) {

                    booking.setRate(rate);
                }

            }

        }


        booking.setStatus("Booked");
        List<IdForm> list = idFormService.getAllIdForm();
//        System.out.println(booking.getUser().getUserId());
//         User user = userService.getUserById(booking.getUser().getUserId());
//        booking.setUser(null);
//          booking.setLogin(null);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllTodayAndTomorrowBookings() {
        String date11 = LocalDateTime.now().toString().substring(0, 10);


        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        List<Booking> list = getAll();
        List<Booking> list1 = new ArrayList<>();

        try {

            for (Booking booking : list) {
                if (booking.getDeliveryDate() == null) {
                    continue;
                } else {
                    String date = booking.getDeliveryDate().toString().substring(0, 10);

                    Date date3 = dateFormat.parse(date);


//                int comparisonResult1 = date3.compareTo(date11);
//                System.out.println(comparisonResult1);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                    if (date.equals(date11)) {
                        list1.add(booking);
                    }
                }
            }

            return list1;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate.substring(0,10));
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);


    }

    //It is get method(head)
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> getAllActiveBooking() {
        List<Booking> list = getAll();

        List<Booking> list2 = new ArrayList<>();

        for (Booking booking : list) {
            if ((booking.getStatus().equals("cancelled"))) {
                continue;
            } else {
                list2.add(booking);
            }
        }
        return list2;
    }

    public List<Booking> getAllOnTodayDate() {
        String date11 = LocalDateTime.now().toString().substring(0, 10);
        System.out.println("Today date:" + date11);

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Booking> list = getAll();
        List<Booking> list1 = new ArrayList<>();

        try {

            for (Booking booking : list) {
                String date = booking.getBookingDate().toString().substring(0, 10);

                Date date3 = dateFormat.parse(date);


//                int comparisonResult1 = date3.compareTo(date11);
//                System.out.println(comparisonResult1);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date.equals(date11)) {
                    list1.add(booking);
                }

            }

            return list1;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate.substring(0,10));
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);


    }

    //It is get method(head)
    public Booking getBookingById(Integer bookingId) {
        Optional<Booking> optional = bookingRepository.findById(bookingId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    public List<Booking> getBookingFromdateToDate(String fromDate, String toDate) {
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Booking> list = getAll();
        List<Booking> list1 = new ArrayList<>();

        try {
            Date date1 = dateFormat.parse(fromDate.substring(0, 10));
            Date date2 = dateFormat.parse(toDate.substring(0, 10));


            for (Booking booking : list) {
                String date = booking.getBookingDate().toString();

                Date date3 = dateFormat.parse(date);


                int comparisonResult1 = date3.compareTo(date1);
                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                    list1.add(booking);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    //it is put method(head and body)
    public Booking modifyBooking(Booking booking) {


        String st = "Booked";
        booking.setCancelDate(new Date());
        booking.setStatus(st);
        Booking booking1 = bookingRepository.save(booking);
        bookingRepository.save(booking);
        return booking1;
    }

    //it is delete method(head)
    public Booking deleteBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        bookingRepository.deleteById(bookingId);
        return booking;
    }


    public List<Booking> getByBookingStatusBooked() {
        List<Booking> allBooked = new ArrayList<>();
        List<Booking> list = getAll();
        for (Booking booking : list) {
            if (booking.getStatus().equals("Booked")) {
                allBooked.add(booking);

            }

        }
        return allBooked;

    }

    public List<Booking> getByBookingStatuscancelled() {
        List<Booking> allCancelled = new ArrayList<>();
        List<Booking> list = getAll();
        for (Booking booking : list) {
            if (booking.getStatus().equals("cancelled")) {
                allCancelled.add(booking);

            }

        }
        return allCancelled;

    }


    public Booking modifyBookingtToReady(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus("Ready");
        return booking;

    }


}

