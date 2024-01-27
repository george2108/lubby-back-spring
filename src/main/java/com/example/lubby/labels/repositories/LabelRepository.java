package com.example.lubby.labels.repositories;

import com.example.lubby.labels.LabelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends CrudRepository<LabelEntity, Long> {
}
