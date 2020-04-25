package core;

import interfaces.Entity;
import interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class Data implements Repository {

    private Entity root;
    private Queue<Entity> queue;

    public Data() {
        this.queue = new PriorityQueue<>();
        this.root = null;
    }

    public Data(Data other) {

        this.root = other.root;
        this.queue = new ArrayDeque<>(other.queue);

    }

    @Override
    public void add(Entity entity) {

        if (this.root == null) {
            this.root = entity;
        } else {

            this.getById(entity.getParentId()).addChild(entity);

        }

        this.queue.offer(entity);
    }

    @Override
    public Entity getById(int id) {

        if (this.root == null) {
            return null;
        }

        ArrayDeque<Entity> queue = new ArrayDeque<>();

        queue.offer(this.root);

        while (!queue.isEmpty()) {

            Entity current = queue.poll();

            if (current.getId() == id) {
                return current;
            }

            for (Entity child : current.getChildren()) {
                queue.offer(child);
            }

        }

        return null;
    }

    @Override
    public List<Entity> getByParentId(int id) {

        Entity parent = this.getById(id);

        return parent == null ? new ArrayList<>() : parent.getChildren();
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.queue);
    }

    @Override
    public Repository copy() {
        return new Data(this);
    }

    @Override
    public List<Entity> getAllByType(String type) {

        if (!type.equals("User") && !type.equals("Invoice") && !type.equals("StoreClient")) {
            throw new IllegalArgumentException("Illegal type: " + type);
        }

        return this.queue.stream().filter(e -> e.getClass().getSimpleName().equals(type))
                .collect(Collectors.toList());

    }

    @Override
    public Entity peekMostRecent() {

        if (this.queue.isEmpty()) {
            throw new IllegalStateException("Operation on empty Data");
        }

        return this.queue.peek();
    }

    @Override
    public Entity pollMostRecent() {

        if (this.queue.isEmpty()) {
            throw new IllegalStateException("Operation on empty Data");
        }

        Entity result = this.queue.poll();

        this.getById(result.getParentId()).getChildren().remove(result);

        return result;
    }

    @Override
    public int size() {
        return this.queue.size();
    }
}
