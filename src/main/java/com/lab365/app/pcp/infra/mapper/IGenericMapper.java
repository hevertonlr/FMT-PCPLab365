package com.lab365.app.pcp.infra.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.infra.exception.MappingException;

import org.mapstruct.MappingTarget;

/**
 * Generic mapper interface for converting between entities and DTOs with
 * support for single objects
 * and collections.
 *
 * @param <E> the entity type, must implement {@link IGenericEntity}
 * @param <D> the DTO type
 */
public interface IGenericMapper<E extends IGenericEntity<E>, D> {

    /**
     * Maps a source object to the specified target class.
     *
     * @param source      the source object to map (can be null, single object, or
     *                    collection)
     * @param targetClass the target class to map to
     * @param <T>         the target type
     * @return the mapped object or null if source was null
     * @throws MappingException if mapping fails due to type mismatch or unsupported
     *                          mapping
     */
    default <T> T map(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof Collection<?> collection) {
            return mapCollection(collection, targetClass);
        }

        return mapSingle(source, targetClass);
    }

    /**
     * Maps a single source object to the target class.
     */
    private <T> T mapSingle(Object source, Class<T> targetClass) {
        Class<E> entityClass = getEntityClass();
        Class<D> dtoClass = getDestinationClass();

        try {
            if (targetClass.equals(dtoClass)) {
                return targetClass.cast(toDto(castToEntity(source, entityClass)));
            }

            if (targetClass.equals(entityClass)) {
                return targetClass.cast(toEntity(castToDto(source, dtoClass)));
            }

            throw new MappingException(String.format(
                    "Unsupported mapping from %s to %s",
                    source.getClass().getName(),
                    targetClass.getName()), null);
        } catch (ClassCastException e) {
            throw new MappingException("Type mismatch during mapping", e);
        }
    }

    /**
     * Maps a collection of objects to the target collection type.
     */
    @SuppressWarnings("unchecked")
    private <T> T mapCollection(Collection<?> source, Class<T> targetClass) {
        if (!List.class.isAssignableFrom(targetClass)) {
            throw new MappingException("Collection mapping only supports List target type", null);
        }

        if (source.isEmpty()) {
            return (T) Collections.emptyList();
        }

        Class<E> entityClass = getEntityClass();
        Class<D> dtoClass = getDestinationClass();
        Object first = source.iterator().next();

        try {
            if (entityClass.isInstance(first)) {
                return (T) mapToDtoList((Collection<E>) source);
            }

            if (dtoClass.isAssignableFrom(first.getClass())) {
                return (T) mapToEntityList((Collection<D>) source);
            }

            throw new MappingException(String.format(
                    "Unsupported collection element type: %s",
                    first.getClass().getName()), null);
        } catch (ClassCastException e) {
            throw new MappingException("Failed to map collection elements", e);
        }
    }

    /**
     * Maps a collection of entities to DTOs.
     *
     * @param entities the collection of entities to map
     * @return list of DTOs or null if input was null
     */
    default List<D> mapToDtoList(Collection<E> entities) {
        return entities == null ? null
                : entities.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
    }

    /**
     * Maps a collection of DTOs to entities.
     *
     * @param dtos the collection of DTOs to map
     * @return list of entities or null if input was null
     */
    default List<E> mapToEntityList(Collection<D> dtos) {
        return dtos == null ? null
                : dtos.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }

    /**
     * Converts an entity to its DTO representation.
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    D toDto(E entity);

    /**
     * Converts a DTO to its entity representation.
     *
     * @param dto the DTO to convert
     * @return the entity
     */
    E toEntity(D dto);

    /**
     * Gets the entity class.
     *
     * @return the entity class
     */
    Class<E> getEntityClass();

    /**
     * Gets the DTO class.
     *
     * @return the DTO class
     */
    Class<D> getDestinationClass();

    /**
     * Updates an existing entity with values from a DTO.
     *
     * @param entity the entity to update
     * @param dto    the DTO containing new values
     * @throws MappingException if the update fails
     */
    void updateEntity(@MappingTarget E entity, D dto);

    /**
     * Safely casts an object to the entity type.
     */
    private E castToEntity(Object source, Class<E> entityClass) {
        try {
            return entityClass.cast(source);
        } catch (ClassCastException e) {
            throw new MappingException(String.format(
                    "Cannot cast %s to entity type %s",
                    source.getClass().getName(),
                    entityClass.getName()), e);
        }
    }

    /**
     * Safely casts an object to the DTO type.
     */
    private D castToDto(Object source, Class<D> dtoClass) {
        try {
            return dtoClass.cast(source);
        } catch (ClassCastException e) {
            throw new MappingException(String.format(
                    "Cannot cast %s to DTO type %s",
                    source.getClass().getName(),
                    dtoClass.getName()), e);
        }
    }
}