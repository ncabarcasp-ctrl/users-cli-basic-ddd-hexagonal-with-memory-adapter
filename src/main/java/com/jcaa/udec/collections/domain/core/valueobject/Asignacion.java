package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;

/**
 * Value Object que representa la asignación de una persona a un programa con un rol.
 * Dos asignaciones son iguales si tienen la misma persona y el mismo rol,
 * por eso un programa las guarda en un conjunto (no se pueden repetir).
 */
public final class Asignacion {

    private final PersonaId personaId;
    private final RolPersonal rol;

    public Asignacion(PersonaId personaId, RolPersonal rol) {
        if (personaId == null) {
            throw new DatoInvalidoException("La asignación necesita una persona.");
        }
        if (rol == null) {
            throw new DatoInvalidoException("La asignación necesita un rol.");
        }
        this.personaId = personaId;
        this.rol = rol;
    }

    public PersonaId personaId() {
        return personaId;
    }

    public RolPersonal rol() {
        return rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignacion)) return false;
        Asignacion that = (Asignacion) o;
        return personaId.equals(that.personaId) && rol == that.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, rol);
    }

    @Override
    public String toString() {
        return "Asignacion{personaId=" + personaId + ", rol=" + rol + "}";
    }
}
