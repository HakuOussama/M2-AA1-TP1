package fac.luminy.m2.aa1.tp1.model.dto;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe representant une personne, utilisée pour la communication avec l'extérieur.
 * @author matmiche
 */
@Getter
@Setter
public class PersonneDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String email;

    private List<VoitureDTO> voituresPossedees;
    private List <VoitureDTO> voituresLouees;

    /**
     * Construit un nouveau PersonneDTO à partir d'une entité Personne donnée.
     *
     * @param personne l'entité {@link Personne} à convertir en PersonneDTO
     */
    public PersonneDTO(Personne personne){
        if (personne == null) {
            return;
        }

        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenom = personne.getPrenom();
        this.adresse = personne.getAdresse();
        this.codePostal = personne.getCodePostal();
        this.email = personne.getEmail();

        if(personne.getVoituresLouees()!= null) this.voituresLouees = personne.getVoituresLouees().stream().map(VoitureDTO::new).collect(Collectors.toList());
      if(personne.getVoituresPossedees()!= null)  this.voituresPossedees = personne.getVoituresPossedees().stream().map(VoitureDTO::new).collect(Collectors.toList());

    }
}
