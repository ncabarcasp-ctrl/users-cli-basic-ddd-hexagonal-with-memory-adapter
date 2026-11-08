package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.ProgramaIncompletoException;
import com.jcaa.udec.collections.domain.core.exception.ProgramaResumenInvalidoException;
import com.jcaa.udec.collections.domain.core.factory.FabricaProgramas;
import com.jcaa.udec.collections.domain.core.valueobject.Asignacion;
import com.jcaa.udec.collections.domain.core.valueobject.Genero;
import com.jcaa.udec.collections.domain.core.valueobject.NombrePrograma;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersonal;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramaTest {

    private final PersonaId conductor = PersonaId.generar();
    private final PersonaId operador = PersonaId.generar();
    private final PersonaId productor = PersonaId.generar();

    private Programa programaNormal() {
        return FabricaProgramas.crearNormal(
                new NombrePrograma("La Mañana"), Genero.VARIEDADES, conductor, operador, productor);
    }

    @Test
    void noPermiteRetirarAlUnicoConductorDelPrograma() {
        Programa programa = programaNormal();

        assertThrows(ProgramaIncompletoException.class,
                () -> programa.retirarPersonal(conductor, RolPersonal.CONDUCTOR));
    }

    @Test
    void siSeRechazaElRetiroElProgramaConservaSuEquipoCompleto() {
        Programa programa = programaNormal();

        assertThrows(ProgramaIncompletoException.class,
                () -> programa.retirarPersonal(operador, RolPersonal.OPERADOR));

        assertTrue(programa.tieneRol(RolPersonal.OPERADOR));
        assertEquals(3, programa.asignaciones().size());
    }

    @Test
    void permiteRetirarAUnConductorSiQuedaOtroConductor() {
        Programa programa = programaNormal();
        PersonaId segundoConductor = PersonaId.generar();
        programa.asignarPersonal(segundoConductor, RolPersonal.CONDUCTOR);

        programa.retirarPersonal(conductor, RolPersonal.CONDUCTOR);

        assertTrue(programa.tieneRol(RolPersonal.CONDUCTOR));
        assertFalse(programa.asignaciones().contains(new Asignacion(conductor, RolPersonal.CONDUCTOR)));
    }

    @Test
    void asignarLaMismaPersonaConElMismoRolDosVecesNoLaDuplica() {
        Programa programa = programaNormal();

        programa.asignarPersonal(conductor, RolPersonal.CONDUCTOR);

        assertEquals(3, programa.asignaciones().size());
    }

    @Test
    void asignarPersonalAgregaUnaNuevaAsignacion() {
        Programa programa = programaNormal();

        programa.asignarPersonal(PersonaId.generar(), RolPersonal.OPERADOR);

        assertEquals(4, programa.asignaciones().size());
    }

    @Test
    void noPermiteCrearUnProgramaSinEquipoCompleto() {
        Set<Asignacion> soloConductor = new LinkedHashSet<>();
        soloConductor.add(new Asignacion(conductor, RolPersonal.CONDUCTOR));

        assertThrows(ProgramaIncompletoException.class, () -> new Programa(
                ProgramaId.generar(), new NombrePrograma("Deportes al Día"), Genero.DEPORTIVO,
                false, Set.of(), soloConductor));
    }

    @Test
    void unProgramaDeResumenNecesitaAlMenosUnProgramaDeOrigen() {
        assertThrows(ProgramaResumenInvalidoException.class, () -> FabricaProgramas.crearResumen(
                new NombrePrograma("Resumen Semanal"), Genero.PERIODISTICO,
                conductor, operador, productor, Set.of()));
    }

    @Test
    void unProgramaNormalNoPuedeTenerProgramasDeOrigen() {
        Set<Asignacion> equipo = new LinkedHashSet<>();
        equipo.add(new Asignacion(conductor, RolPersonal.CONDUCTOR));
        equipo.add(new Asignacion(operador, RolPersonal.OPERADOR));
        equipo.add(new Asignacion(productor, RolPersonal.PRODUCTOR));

        assertThrows(ProgramaResumenInvalidoException.class, () -> new Programa(
                ProgramaId.generar(), new NombrePrograma("Con origen"), Genero.MUSICAL,
                false, Set.of(ProgramaId.generar()), equipo));
    }

    @Test
    void unProgramaNoPuedeSerSuPropioOrigen() {
        ProgramaId id = ProgramaId.generar();
        Set<Asignacion> equipo = new LinkedHashSet<>();
        equipo.add(new Asignacion(conductor, RolPersonal.CONDUCTOR));
        equipo.add(new Asignacion(operador, RolPersonal.OPERADOR));
        equipo.add(new Asignacion(productor, RolPersonal.PRODUCTOR));

        assertThrows(ProgramaResumenInvalidoException.class, () -> new Programa(
                id, new NombrePrograma("Circular"), Genero.CULTURAL, true, Set.of(id), equipo));
    }

    @Test
    void laColeccionDeAsignacionesQueSeEntregaEsDeSoloLectura() {
        Programa programa = programaNormal();

        assertThrows(UnsupportedOperationException.class,
                () -> programa.asignaciones().add(new Asignacion(PersonaId.generar(), RolPersonal.OPERADOR)));
    }

    @Test
    void elProgramaNoSeEntregaSinSusDatosObligatorios() {
        assertThrows(DatoInvalidoException.class, () -> new Programa(
                null, new NombrePrograma("X"), Genero.MUSICAL, false, Set.of(), Set.of()));
    }
}
