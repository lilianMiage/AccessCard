package fr.miage.lroux.accesscard.service;

import fr.miage.lroux.accesscard.entity.AccessCard;
import fr.miage.lroux.accesscard.repository.RepoAccessCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccessCardControllerTests {

    @Mock
    private RepoAccessCard repoAccessCard;

    @InjectMocks
    private ServiceAccessCard serviceAccessCard;

    private AccessCard accessCard;

    @BeforeEach
    public void setUp() {
        accessCard = new AccessCard();
        accessCard.setAccessCardId(1L);
    }

    @Test
    public void testCreateAccessCard_WhenCardDoesNotExist_ShouldSaveAndReturnCard() {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.empty());
        when(repoAccessCard.save(accessCard)).thenReturn(accessCard);

        // Act
        AccessCard result = serviceAccessCard.createAccessCard(accessCard);

        // Assert
        assertNotNull(result);
        assertEquals(accessCard.getAccessCardId(), result.getAccessCardId());
        verify(repoAccessCard).findById(1L);
        verify(repoAccessCard).save(accessCard);
    }


    @Test
    public void testCreateAccessCard_WhenCardExists_ShouldThrowException() {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.of(accessCard));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceAccessCard.createAccessCard(accessCard);
        });

        assertEquals("An access card with this ID already exist 1", exception.getMessage());
        verify(repoAccessCard).findById(1L);
        verify(repoAccessCard, never()).save(any());
    }

    @Test
    public void testGetAccessCardById_WhenCardExists_ShouldReturnCard() throws Exception {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.of(accessCard));

        // Act
        AccessCard result = serviceAccessCard.getAccessCardById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(accessCard.getAccessCardId(), result.getAccessCardId());
        verify(repoAccessCard).findById(1L);
    }

    @Test
    public void testGetAccessCardById_WhenCardDoesNotExist_ShouldThrowException() {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            serviceAccessCard.getAccessCardById(1L);
        });

        assertEquals("No access card exist with this ID 1", exception.getMessage());
        verify(repoAccessCard).findById(1L);
    }

    @Test
    public void testDeleteAccessCardById_WhenCardExists_ShouldDeleteCard() throws Exception {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.of(accessCard));

        // Act
        serviceAccessCard.deleteAccessCardById(1L);

        // Assert
        verify(repoAccessCard).findById(1L);
        verify(repoAccessCard).deleteById(1L);
    }

    @Test
    public void testDeleteAccessCardById_WhenCardDoesNotExist_ShouldThrowException() {
        // Arrange
        when(repoAccessCard.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            serviceAccessCard.deleteAccessCardById(1L);
        });

        assertEquals("No access card exist with this ID 1", exception.getMessage());
        verify(repoAccessCard).findById(1L);
        verify(repoAccessCard, never()).deleteById(anyLong());
    }

    @Test
    public void testGetAccessCardByUserId_WhenCardExists_ShouldReturnCard() throws Exception {
        // Arrange
        accessCard.setUserId(2L);
        when(repoAccessCard.findByUserId(2L)).thenReturn(Optional.of(accessCard));

        // Act
        AccessCard result = serviceAccessCard.getByUserid(2L);

        // Assert
        assertNotNull(result);
        assertEquals(2L, result.getUserId());
        verify(repoAccessCard).findByUserId(2L);
    }

    @Test
    public void testGetAccessCardByUserId_WhenCardDoesNotExist_ShouldThrowException() {
        // Arrange
        when(repoAccessCard.findByUserId(2L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            serviceAccessCard.getByUserid(2L);
        });

        assertEquals("No user associate with this access Card", exception.getMessage());
        verify(repoAccessCard).findByUserId(2L);
    }

}
