package fac.luminy.m2.aa1.tp1.configuration;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import fac.luminy.m2.aa1.tp1.model.entity.Location;
import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.repository.PersonneRepository;
import fac.luminy.m2.aa1.tp1.repository.VoitureRepository;
import fac.luminy.m2.aa1.tp1.repository.LocationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseInitializer {

    private final PersonneRepository personneRepository;
    private final VoitureRepository voitureRepository;
    private final LocationRepository locationRepository;

    public DatabaseInitializer(PersonneRepository personneRepository,
                               VoitureRepository voitureRepository,
                               LocationRepository locationRepository) {
        this.personneRepository = personneRepository;
        this.voitureRepository = voitureRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void initDatabase() {
        // ⚠️ Évite de dupliquer les données si elles existent déjà
        if (personneRepository.count() > 0) {
            System.out.println("✅ Données déjà présentes dans la base, initialisation ignorée.");
            return;
        }

        // 👤 Création de personnes
        Personne alice = new Personne();
        alice.setNom("Dupont");
        alice.setPrenom("Alice");
        alice.setAdresse("12 rue du Port");
        alice.setCodePostal("13001");
        alice.setEmail("alice.dupont@mail.com");

        Personne bob = new Personne();
        bob.setNom("Martin");
        bob.setPrenom("Bob");
        bob.setAdresse("8 avenue des Lilas");
        bob.setCodePostal("13005");
        bob.setEmail("bob.martin@mail.com");

        personneRepository.saveAll(List.of(alice, bob));

        // 🚗 Création de voitures
        Voiture v1 = new Voiture();
        v1.setMarque("Peugeot");
        v1.setModele("208");
        v1.setAnnee(2022);
        v1.setType(TypeVoiture.CITADINE);
        v1.setChevauxFiscaux(5);
        v1.setPrix(60.0);
        v1.setConsommation(4.5);
        v1.setCouleur("Bleu");
        v1.setProprietaire(alice);

        Voiture v2 = new Voiture();
        v2.setMarque("Tesla");
        v2.setModele("Model 3");
        v2.setAnnee(2023);
        v2.setType(TypeVoiture.BERLINE);
        v2.setChevauxFiscaux(10);
        v2.setPrix(120.0);
        v2.setConsommation(0);
        v2.setCouleur("Noir");
        v2.setProprietaire(bob);

        voitureRepository.saveAll(List.of(v1, v2));

        // 📅 Création de locations
        Location l1 = new Location(v1, bob,
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 10, 10),
                600.0);

        Location l2 = new Location(v2, alice,
                LocalDate.of(2025, 10, 5),
                LocalDate.of(2025, 10, 12),
                840.0);

        locationRepository.saveAll(List.of(l1, l2));

        System.out.println("✅ Base de données initialisée avec succès !");
    }
}
