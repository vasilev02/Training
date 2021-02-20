package com.example.andreys.repository;

import com.example.andreys.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findItemByName(String name);

    @Query(value = "SELECT i from Item AS i ORDER BY i.price ASC")
    List<Item> getItems();

    Item getItemById(String id);

}
