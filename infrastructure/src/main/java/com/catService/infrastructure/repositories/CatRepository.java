package com.catService.infrastructure.repositories;


import com.catService.infrastructure.entities.Cat;
import com.catService.infrastructure.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByOwner(Owner owner);
}
