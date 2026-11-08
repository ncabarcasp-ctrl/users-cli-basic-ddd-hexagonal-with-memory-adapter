package com.jcaa.udec.collections.domain.core.factory;

import com.jcaa.udec.collections.domain.core.exception.ProgramaResumenInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Asignacion;
import com.jcaa.udec.collections.domain.core.valueobject.Genero;
import com.jcaa.udec.collections.domain.core.valueobject.NombrePrograma;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersonal;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Fábrica de programas (patrón Factory).
 * Centraliza las dos formas de crear un programa del CEA (normal y de resumen)
 * y se asegura de que nazca válido, con su equipo completo y un id nuevo.
 */
public final class FabricaProgramas {

    private FabricaProgramas() {
    }

    public static Programa crearNormal(NombrePrograma nombre, Genero genero,
                                       PersonaId conductor, PersonaId operador, PersonaId productor) {
        return new Programa(ProgramaId.generar(), nombre, genero, false,
                Set.of(), equipo(conductor, operador, productor));
    }

    public static Programa crearResumen(NombrePrograma nombre, Genero genero,
                                        PersonaId conductor, PersonaId operador, PersonaId productor,
                                        Set<ProgramaId> origenes) {
        if (origenes == null || origenes.isEmpty()) {
            throw new ProgramaResumenInvalidoException(
                    "Un programa de resumen necesita al menos un programa de origen.");
        }
        return new Programa(ProgramaId.generar(), nombre, genero, true,
                origenes, equipo(conductor, operador, productor));
    }

    private static Set<Asignacion> equipo(PersonaId conductor, PersonaId operador, PersonaId productor) {
        Set<Asignacion> equipo = new LinkedHashSet<>();
        equipo.add(new Asignacion(conductor, RolPersonal.CONDUCTOR));
        equipo.add(new Asignacion(operador, RolPersonal.OPERADOR));
        equipo.add(new Asignacion(productor, RolPersonal.PRODUCTOR));
        return equipo;
    }
}
