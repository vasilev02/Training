package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Loader implements Buffer {

    private List<Entity> entities;

    public Loader() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        this.entities.add(entity);
    }

    @Override
    public Entity extract(int id) {

        Entity entityToReturn = null;

        for (Entity entity : entities) {
            if (entity.getId() == id) {
                entityToReturn = entity;
                this.entities.remove(entity);
                return entity;
            }
        }

        return entityToReturn;
    }

    @Override
    public Entity find(Entity entity) {

        int index = this.entities.indexOf(entity);
        if (index == -1) {
            return null;
        }
        return this.entities.get(index);
    }

    @Override
    public boolean contains(Entity entity) {

        int index = this.entities.indexOf(entity);
        if (index == -1) {
            return false;
        }
        return true;

    }

    @Override
    public int entitiesCount() {
        return this.entities.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {

        int index = this.entities.indexOf(oldEntity);

        if (index == -1) {
            throw new IllegalStateException("Entity not found");
        }

        this.entities.set(index, newEntity);

    }

    @Override
    public void swap(Entity first, Entity second) {

        int indexOne = this.entities.indexOf(first);
        int indexTwo = this.entities.indexOf(second);

        if (indexOne == -1 || indexTwo == -1) {
            throw new IllegalStateException("Entities not found");
        }

        this.entities.set(indexOne, second);
        this.entities.set(indexTwo, first);

    }

    @Override
    public void clear() {
        this.entities.clear();
    }

    @Override
    public Entity[] toArray() {
        Entity[] arr = new Entity[this.entities.size()];
        this.entities.toArray(arr);
        return arr;
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {

        List<Entity> collect = this.entities.stream().filter(e -> e.getStatus().ordinal() >= lowerBound.ordinal()
                && e.getStatus().ordinal() <= upperBound.ordinal()).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<Entity> getAll() {
        return Collections.unmodifiableList(this.entities);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {

        for (Entity entity : entities) {
            if (entity.getStatus().equals(oldStatus)) {
                entity.setStatus(newStatus);
            }
        }

    }

    @Override
    public void removeSold() {
        List<Entity> collected = this.entities.stream().filter(e -> !e.getStatus().equals(BaseEntity.Status.SOLD))
                .collect(Collectors.toList());

        this.entities = collected;
    }

    @Override
    public Iterator<Entity> iterator() {
        return this.entities.iterator();
    }
}
