package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Resource;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.ResourceRepo;

public class BookingServiceTest {

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private ResourceRepo resourceRepo;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Employee employee;
    private Resource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1);
        employee.setEmail("test@example.com");

        resource = new Resource();
        resource.setId(1);
        resource.setName("Test Resource");
        resource.setAvailableQuantity(10);
    }

    @Test
    void testAddBooking_Success() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        when(resourceRepo.findByIdForUpdate(1)).thenReturn(Optional.of(resource));
        when(bookingRepo.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Booking booking = bookingService.addBooking(1, 1, 3);

        assertNotNull(booking);
        assertEquals(7, resource.getAvailableQuantity());
        verify(bookingRepo, times(1)).save(any(Booking.class));
    }

    @Test
    void testAddBooking_InsufficientQuantity() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        when(resourceRepo.findByIdForUpdate(1)).thenReturn(Optional.of(resource));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.addBooking(1, 1, 15);
        });

        assertEquals("Not available ", exception.getMessage());
        assertEquals(10, resource.getAvailableQuantity()); // Should not change
    }
}
