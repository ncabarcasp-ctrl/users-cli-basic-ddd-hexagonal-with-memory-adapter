package com.jcaa.udec.collections.domain.core.specification;

import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersonal;

/**
 * Regla: el programa tiene al menos un conductor, un operador y un productor.
 */
public class ProgramaConEquipoCompleto implements Especificacion<Programa> {

    @Override
    public boolean esSatisfechaPor(Programa programa) {
        return programa.tieneRol(RolPersonal.CONDUCTOR)
                && programa.tieneRol(RolPersonal.OPERADOR)
                && programa.tieneRol(RolPersonal.PRODUCTOR);
    }
}
