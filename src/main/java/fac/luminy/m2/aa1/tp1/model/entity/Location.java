package fac.luminy.m2.aa1.tp1.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "LOCATION")
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locataire_id", nullable = false)
    private Personne locataire;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prixTotal;
    private boolean enCours;


    public Location(Voiture voiture, Personne personne, LocalDate dateDebut, LocalDate dateFin, double prixTotal) {
        this.voiture = voiture;
        this.locataire = personne;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
    }
}

