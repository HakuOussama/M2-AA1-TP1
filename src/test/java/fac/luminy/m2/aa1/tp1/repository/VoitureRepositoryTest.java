package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VoitureRepositoryTest {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testFindByProprietaireNom() {
        // Arrange
        Voiture voiture = new Voiture();

        Personne proprietaire = new Personne();
        proprietaire.setNom("Greenwood");

        personneRepository.save(proprietaire);

        voiture.setProprietaire(proprietaire);
        voiture.setMarque("Ferrari");
        voitureRepository.save(voiture);

        // Act
        List<Voiture> result = voitureRepository.findByProprietaireNom("Greenwood");

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Greenwood", result.get(0).getProprietaire().getNom());
    }

    @Test
    public void testFindByProprietaireNom_NotFound() {
        // Act
        List<Voiture> result = voitureRepository.findByProprietaireNom("NonExistent");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    public void testFindByLocatairePreference(){
        Voiture v = new Voiture();
        v.setModele("Clio");
        v.setMarque("Renault");
        v.setAnnee(2022);
        v.setType(TypeVoiture.CITADINE);
        v.setChevauxFiscaux(5);
        v.setPrix(18000.0);
        v.setConsommation(5.2);
        v.setCouleur("Bleu");

        // Propriétaire et locataire explicitement à null
        v.setProprietaire(null);
        v.setLocataire(null);


        voitureRepository.save(v);

        List<Voiture> voitureList1= voitureRepository.searchVoitures(TypeVoiture.CITADINE,null,null,null,null);
        assertNotNull(voitureList1);
        List<Voiture> voitureList2= voitureRepository.searchVoitures(TypeVoiture.CITADINE,2022,"Clio",null,null);
        assertNotNull(voitureList2);
        assertEquals(voitureList2.size(), 1);
        List<Voiture> voitureList3= voitureRepository.searchVoitures(TypeVoiture.CITADINE,2022,"Clio",25000,27000);
        assertTrue(voitureList3.isEmpty());
    }
}
