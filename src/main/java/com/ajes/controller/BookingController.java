package com.ajes.controller;


import com.ajes.model.Customer;
import com.ajes.model.Booking;
import com.ajes.model.Payment;
import com.ajes.service.BookingService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Booking> addBooking(@RequestBody() Booking booking) {
        System.out.println(".........................");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(booking));
    }


    @GetMapping("/booking/")
    public ResponseEntity<List<Booking>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getAllOnTodayDate());
    }

    @GetMapping("/booking/todayAndTomorrowBookings/")
    public List<Booking> getAllTodayAndTomorrowBookings() {
        return bookingService.getAllTodayAndTomorrowBookings();
    }

    @GetMapping("/booking/getall/")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getAllActiveBooking());
    }

    @GetMapping("/booking/FromDate/{fromDate}/ToDate/{toDate}")
    public ResponseEntity<List<Booking>> getBookingFromDateToDate(@PathVariable() String fromDate, @PathVariable() String toDate) {
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getBookingFromdateToDate(fromDate, toDate));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> getByBookingId(@PathVariable() Integer bookingId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.getBookingById(bookingId));
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> modifyBooking(@PathVariable() Integer bookingId, @RequestBody() Booking booking) {
        booking.setBookingId(bookingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.modifyBooking(booking));
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable() Integer bookingId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.deleteBooking(bookingId));
    }

    @GetMapping("/booking/cancelled/")
    public ResponseEntity<List<Booking>> getByBookingSatatuscancelled() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getByBookingStatuscancelled());
    }

    @GetMapping("/booking/booked/")
    public ResponseEntity<List<Booking>> getByBookingSatatusBooked() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getByBookingStatusBooked());
    }

}
