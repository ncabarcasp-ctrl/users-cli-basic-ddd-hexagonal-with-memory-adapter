package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.PersonalEnVariasProductorasException;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaId;
import com.jcaa.udec.collections.domain.core.valueobject.ProductoraId;

import java.util.List;

/**
 * Servicio de dominio: hace cumplir la regla del CEA de que una persona no puede
 * estar asignada a programas de más de una compañía productora.
 * La regla cruza varios agregados (persona, programas y productoras), por eso
 * no pertenece a ninguno de ellos. Recibe los datos ya cargados.
 */
public class VerificadorExclusividadPersonal {

    public void verificar(PersonaId persona, ProductoraId productoraDelPrograma,
                          List<ProductoraId> productorasDondeYaTrabaja) {
        if (persona == null || productoraDelPrograma == null || productorasDondeYaTrabaja == null) {
            throw new DatoInvalidoException("Se necesitan la persona, la productora y sus productoras actuales.");
        }
        for (ProductoraId otra : productorasDondeYaTrabaja) {
            if (!otra.equals(productoraDelPrograma)) {
                throw new PersonalEnVariasProductorasException(
                        "La persona " + persona + " ya trabaja para otra compañía productora "
                                + "y no puede asignarse a este programa.");
            }
        }
    }
}
