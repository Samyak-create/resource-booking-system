package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;-
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.ResourceRepo;
import com.example.demo.service.ResourceServiceImpl;

public class ResourceServiceTest {

    @Mock
    private ResourceRepo repo;

    @InjectMocks
    private ResourceServiceImpl service;

    public ResourceServiceTest() {
        MockitoAnnotations.openMocks(this); // ✅ FIXED
    }

    @Test
    void shouldThrowExceptionWhenResourceNotFound() {

        int id = 1;

        // simulate DB returning empty
        when(repo.findById(id)).thenReturn(Optional.empty());

        // check exception
        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(id);
        });
    }
}