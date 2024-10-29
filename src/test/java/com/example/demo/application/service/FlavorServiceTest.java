package com.example.demo.application.service;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.repository.FlavorRepository;
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
class FlavorServiceTest {

    @Mock
    private FlavorRepository repository;

    @InjectMocks
    private FlavorService flavorService;

    private Flavor flavor;

    @BeforeEach
    void setUp() {
        flavor = new Flavor();
        flavor.setId(1L);
        flavor.setName("Vanilla");
    }

    @Test
    void getAll_ReturnsListOfFlavors() {
        when(repository.findAll()).thenReturn(List.of(flavor));

        List<Flavor> flavors = flavorService.getAll();

        assertEquals(1, flavors.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void create_ThrowsException_WhenNameIsNullOrEmpty() {
        flavor.setName(null);
        assertThrows(IllegalArgumentException.class, () -> flavorService.create(flavor), "Flavor name cannot be null or empty");

        flavor.setName("");
        assertThrows(IllegalArgumentException.class, () -> flavorService.create(flavor), "Flavor name cannot be null or empty");
    }

    @Test
    void create_ThrowsException_WhenFlavorAlreadyExists() {
        when(repository.existsByName("Vanilla")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> flavorService.create(flavor), "Flavor with this name already exists");
        verify(repository, times(1)).existsByName("Vanilla");
    }

    @Test
    void create_SuccessfullySavesFlavor_WhenValidFlavor() {
        when(repository.existsByName("Vanilla")).thenReturn(false);
        when(repository.save(flavor)).thenReturn(flavor);

        Flavor savedFlavor = flavorService.create(flavor);

        assertEquals("Vanilla", savedFlavor.getName());
        verify(repository, times(1)).existsByName("Vanilla");
        verify(repository, times(1)).save(flavor);
    }

    @Test
    void update_ThrowsException_WhenFlavorNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> flavorService.update(1L, "New Name"), "Flavor not found");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void update_SuccessfullyUpdatesFlavor_WhenFlavorExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(flavor));

        String result = flavorService.update(1L, "New Name");

        assertEquals("Flavor updated", result);
        assertEquals("New Name", flavor.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void destroy_ThrowsException_WhenFlavorNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> flavorService.destroy(1L), "Flavor not found with id: 1");
        verify(repository, times(1)).existsById(1L);
    }

    @Test
    void destroy_SuccessfullyDeletesFlavor_WhenFlavorExists() {
        when(repository.existsById(1L)).thenReturn(true);

        String result = flavorService.destroy(1L);

        assertEquals("Flavor removed completely", result);
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}

