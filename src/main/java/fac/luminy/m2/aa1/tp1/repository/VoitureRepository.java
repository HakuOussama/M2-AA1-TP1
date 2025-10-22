package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    @Query("""
       SELECT v FROM VOITURE v
       WHERE (:type IS NULL OR v.type = :type)
         AND (:annee IS NULL OR v.annee = :annee)
         AND (:modele IS NULL OR v.modele = :modele)
         AND (:prixMin IS NULL OR v.prix >= :prixMin)
         AND (:prixMax IS NULL OR v.prix <= :prixMax)
       """)
    List<Voiture> searchVoitures(@Param("type") TypeVoiture type,
                                 @Param("annee") Integer annee,
                                 @Param("modele") String modele,
                                 @Param("prixMin") Integer prixMin,
                                 @Param("prixMax") Integer prixMax);

    List<Voiture> findByProprietaireNom(@Param("nom") String nom);
}
