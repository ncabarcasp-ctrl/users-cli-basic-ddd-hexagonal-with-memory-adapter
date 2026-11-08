package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramaValueObjectsTest {

    @Test
    void generaIdentificadoresDistintosParaProgramaYPersona() {
        assertNotEquals(ProgramaId.generar(), ProgramaId.generar());
        assertNotEquals(PersonaId.generar(), PersonaId.generar());
    }

    @Test
    void rechazaIdentificadoresVacios() {
        assertThrows(DatoInvalidoException.class, () -> new ProgramaId(" "));
        assertThrows(DatoInvalidoException.class, () -> new PersonaId(null));
    }

    @Test
    void rechazaUnNombreDeProgramaVacioODemasiadoLargo() {
        assertThrows(DatoInvalidoException.class, () -> new NombrePrograma(""));
        assertThrows(DatoInvalidoException.class, () -> new NombrePrograma("A".repeat(101)));
    }

    @Test
    void aceptaUnNombreDeProgramaValidoYLoRecorta() {
        assertEquals("Noticias del Caribe", new NombrePrograma("  Noticias del Caribe ").valor());
    }

    @Test
    void laAsignacionNecesitaPersonaYRol() {
        assertThrows(DatoInvalidoException.class, () -> new Asignacion(null, RolPersonal.CONDUCTOR));
        assertThrows(DatoInvalidoException.class, () -> new Asignacion(PersonaId.generar(), null));
    }

    @Test
    void dosAsignacionesConLaMismaPersonaYRolSonIgualesYNoSeRepitenEnUnConjunto() {
        PersonaId persona = PersonaId.generar();
        Set<Asignacion> conjunto = new HashSet<>();

        conjunto.add(new Asignacion(persona, RolPersonal.OPERADOR));
        conjunto.add(new Asignacion(persona, RolPersonal.OPERADOR));

        assertEquals(1, conjunto.size());
    }

    @Test
    void laMismaPersonaConOtroRolEsOtraAsignacion() {
        PersonaId persona = PersonaId.generar();

        assertNotEquals(new Asignacion(persona, RolPersonal.CONDUCTOR), new Asignacion(persona, RolPersonal.PRODUCTOR));
    }

    @Test
    void elCatalogoDeGenerosYRolesEsElDelCea() {
        assertEquals(5, Genero.values().length);
        assertEquals(3, RolPersonal.values().length);
        assertTrue(Genero.valueOf("VARIEDADES") == Genero.VARIEDADES);
    }
}
