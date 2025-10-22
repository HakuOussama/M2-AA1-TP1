package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

    @Query("SELECT p.voituresPossedees FROM PERSONNE p WHERE p = :personne")
    List<Voiture> getAllvoituresPossedees(@Param("personne") Personne personne);


    @Query("select VOITURE from VOITURE ")
    List<Voiture> getAllVoitures();



    private double getStatistique(Personne personne) {
        List<Voiture> voituresPossedees = getAllvoituresPossedees(personne);
        return 0;
    }

}
