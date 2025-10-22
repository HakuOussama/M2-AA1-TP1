package fac.luminy.m2.aa1.tp1.archunit;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Vérifie que toutes les classes du package "controller"
 * se terminent par le suffixe "Controller".
 */
public class CheckControllers {

    @Test
    void allControllerClassesShouldEndWithControllerSuffix() {
        // 👇 Nom complet du package à scanner
        Reflections reflections = new Reflections("src/main/java/fac/luminy/m2/aa1/tp1/controller");

        // 🔍 Récupère toutes les classes dans ce package
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        // ✅ Vérifie le suffixe pour chaque classe
        for (Class<?> clazz : classes) {
            String className = clazz.getSimpleName();
            assertTrue(
                    className.endsWith("Controller"),
                    "❌ La classe " + clazz.getName() + " ne se termine pas par 'Controller'"
            );
        }
    }
}
