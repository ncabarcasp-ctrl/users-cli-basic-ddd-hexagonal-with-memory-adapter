package com.jcaa.udec.collections.domain.core.specification;

import com.jcaa.udec.collections.domain.core.factory.FabricaProgramas;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Genero;
import com.jcaa.udec.collections.domain.core.valueobject.NombrePrograma;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EspecificacionesTest {

    private final Especificacion<Programa> equipoCompleto = new ProgramaConEquipoCompleto();
    private final Especificacion<Programa> resumenConOrigen = new ProgramaResumenConOrigen();

    private Programa programaNormal() {
        return FabricaProgramas.crearNormal(new NombrePrograma("Noticias"), Genero.PERIODISTICO,
                PersonaId.generar(), PersonaId.generar(), PersonaId.generar());
    }

    private Programa programaResumen() {
        return FabricaProgramas.crearResumen(new NombrePrograma("Resumen"), Genero.VARIEDADES,
                PersonaId.generar(), PersonaId.generar(), PersonaId.generar(), Set.of(ProgramaId.generar()));
    }

    @Test
    void todoProgramaCreadoCorrectamenteTieneElEquipoCompleto() {
        assertTrue(equipoCompleto.esSatisfechaPor(programaNormal()));
        assertTrue(equipoCompleto.esSatisfechaPor(programaResumen()));
    }

    @Test
    void soloLosProgramasDeResumenCumplenLaReglaDeResumenConOrigen() {
        assertTrue(resumenConOrigen.esSatisfechaPor(programaResumen()));
        assertFalse(resumenConOrigen.esSatisfechaPor(programaNormal()));
    }

    @Test
    void laConjuncionYExigeQueSeCumplanLasDosReglas() {
        Especificacion<Programa> resumenesCompletos = equipoCompleto.y(resumenConOrigen);

        assertTrue(resumenesCompletos.esSatisfechaPor(programaResumen()));
        assertFalse(resumenesCompletos.esSatisfechaPor(programaNormal()));
    }

    @Test
    void laDisyuncionOBastaConQueSeCumplaUna() {
        Especificacion<Programa> cualquiera = resumenConOrigen.o(equipoCompleto);

        assertTrue(cualquiera.esSatisfechaPor(programaNormal()));
    }

    @Test
    void laNegacionNoInvierteLaRegla() {
        Especificacion<Programa> noEsResumen = resumenConOrigen.no();

        assertTrue(noEsResumen.esSatisfechaPor(programaNormal()));
        assertFalse(noEsResumen.esSatisfechaPor(programaResumen()));
    }
}
