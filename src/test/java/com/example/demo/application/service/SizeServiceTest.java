package com.example.demo.application.service;

import com.example.demo.domain.model.Size;
import com.example.demo.domain.repository.SizeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Gerardo De Las Cuevas
 */
@ExtendWith(MockitoExtension.class)
class SizeServiceTest {

    @Mock
    private SizeRepository repository;

    @InjectMocks
    private SizeService sizeService;

    private Size size;

    @BeforeEach
    void setUp() {
        size = new Size();
        size.setId(1L);
        size.setSizeName("Large");
        size.setAvailable(true);
    }

    // Test for getAll()
    @Test
    void getAll_Successful() {
        List<Size> sizes = List.of(size);
        when(repository.findAll()).thenReturn(sizes);

        List<Size> result = sizeService.getAll();

        assertEquals(sizes, result);
        verify(repository, times(1)).findAll();
    }

    // Test for update()
    @Test
    void update_Successful() {
        Size updatedSize = new Size();
        updatedSize.setSizeName("Medium");
        updatedSize.setAvailable(false);

        when(repository.findById(1L)).thenReturn(Optional.of(size));
        when(repository.save(any(Size.class))).thenReturn(size);

        Size result = sizeService.update(1L, updatedSize);

        assertEquals("Medium", result.getSizeName());
        assertFalse(result.isAvailable());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(size);
    }

    @Test
    void update_SizeNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sizeService.update(1L, size));

        assertEquals("Size not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any(Size.class));
    }

    // Test for store()
    @Test
    void store_Successful() {
        when(repository.existByName("Large")).thenReturn(false);
        when(repository.save(size)).thenReturn(size);

        Size result = sizeService.store(size);

        assertEquals(size, result);
        verify(repository, times(1)).existByName("Large");
        verify(repository, times(1)).save(size);
    }

    @Test
    void store_SizeNameIsNull() {
        size.setSizeName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sizeService.store(size));

        assertEquals("Size name is required", exception.getMessage());
        verify(repository, never()).existByName(anyString());
        verify(repository, never()).save(any(Size.class));
    }

    @Test
    void store_SizeAlreadyExists() {
        when(repository.existByName("Large")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sizeService.store(size));

        assertEquals("This size is already register", exception.getMessage());
        verify(repository, times(1)).existByName("Large");
        verify(repository, never()).save(any(Size.class));
    }

    // Test for destroy()
    @Test
    void destroy_Successful() {
        when(repository.existById(1L)).thenReturn(true);

        String result = sizeService.destroy(1L);

        assertEquals("Size removed completely", result);
        verify(repository, times(1)).existById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void destroy_SizeNotFound() {
        when(repository.existById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> sizeService.destroy(1L));

        assertEquals("Size not found with id: " + 1L, exception.getMessage());
        verify(repository, times(1)).existById(1L);
        verify(repository, never()).deleteById(1L);
    }

    @Test
    void destroy_DeletionFails() {
        when(repository.existById(1L)).thenReturn(true);
        doThrow(new RuntimeException("Error removing Size")).when(repository).deleteById(1L);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> sizeService.destroy(1L));

        assertEquals("Error removing Size: Error removing Size", exception.getMessage());
        verify(repository, times(1)).existById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}

