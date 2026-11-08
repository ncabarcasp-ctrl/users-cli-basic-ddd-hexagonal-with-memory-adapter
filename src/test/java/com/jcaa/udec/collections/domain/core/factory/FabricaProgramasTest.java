package com.jcaa.udec.collections.domain.core.factory;

import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Genero;
import com.jcaa.udec.collections.domain.core.valueobject.NombrePrograma;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersonal;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FabricaProgramasTest {

    private final PersonaId conductor = PersonaId.generar();
    private final PersonaId operador = PersonaId.generar();
    private final PersonaId productor = PersonaId.generar();

    @Test
    void crearNormalDevuelveUnProgramaConSuEquipoCompletoYSinOrigenes() {
        Programa programa = FabricaProgramas.crearNormal(
                new NombrePrograma("Noticias del Caribe"), Genero.PERIODISTICO, conductor, operador, productor);

        assertFalse(programa.esResumen());
        assertTrue(programa.origenes().isEmpty());
        assertTrue(programa.tieneRol(RolPersonal.CONDUCTOR));
        assertTrue(programa.tieneRol(RolPersonal.OPERADOR));
        assertTrue(programa.tieneRol(RolPersonal.PRODUCTOR));
    }

    @Test
    void crearResumenDevuelveUnProgramaDeResumenConSusOrigenes() {
        ProgramaId origen = ProgramaId.generar();

        Programa resumen = FabricaProgramas.crearResumen(
                new NombrePrograma("Lo Mejor de la Semana"), Genero.VARIEDADES,
                conductor, operador, productor, Set.of(origen));

        assertTrue(resumen.esResumen());
        assertEquals(Set.of(origen), resumen.origenes());
    }

    @Test
    void cadaProgramaCreadoRecibeUnIdDistinto() {
        Programa uno = FabricaProgramas.crearNormal(new NombrePrograma("Uno"), Genero.MUSICAL, conductor, operador, productor);
        Programa dos = FabricaProgramas.crearNormal(new NombrePrograma("Uno"), Genero.MUSICAL, conductor, operador, productor);

        assertNotEquals(uno.id(), dos.id());
    }
}
