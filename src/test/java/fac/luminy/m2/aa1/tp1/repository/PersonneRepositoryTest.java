package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonneRepositoryTest {
    @Autowired
    private PersonneRepository personneRepository;

    @Test
    void getAllVoituresPossedee() {
        // Arrange
        Personne personne = new Personne();
        personne.setNom("oussama");
        personneRepository.save(personne);

        Voiture voiture1 = new Voiture();
        voiture1.setMarque("BMW");
        voiture1.setProprietaire(personne);

        Voiture voiture2 = new Voiture();
        voiture2.setMarque("Audi");
        voiture2.setProprietaire(personne);

        // Act
        List<Voiture> result = personneRepository.getAllvoituresPossedees(personne);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(v -> v.getProprietaire().equals(personne)));
    }


}