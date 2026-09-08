package dev.zen.inventory.domain.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends DomainException {
    private final String entity;
    private final Object id;

    public EntityNotFoundException(String entity, Object id) {
        this.entity = entity;
        this.id = id;

        super(String.format("%s with ID '%s' was not found.", entity, id));
    }
}
