package fac.luminy.m2.aa1.tp1.archunit;

import fac.luminy.m2.aa1.tp1.controller.VoitureController;
import fac.luminy.m2.aa1.tp1.repository.VoitureRepository;
import fac.luminy.m2.aa1.tp1.service.VoitureService;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

public class VoitureControllerTest {
    @Test
    void controllerShouldUseServiceNotRepository() {
        // 🔧 Mock du service et du repository
        VoitureService voitureService = mock(VoitureService.class);
        VoitureRepository voitureRepository = mock(VoitureRepository.class);

        // 🔧 Création du controller avec le service mocké
        VoitureController controller = new VoitureController(voitureService);

        // 👇 Appel d'une méthode du controller
        controller.getVoitures("RED");

        // ✅ Vérifie que le service est bien utilisé
        verify(voitureService, atLeastOnce()).recupererVoituresProprietaire("RED");

        // ❌ Vérifie que le repository N’A PAS été utilisé directement
        verifyNoInteractions(voitureRepository);
    }

}
