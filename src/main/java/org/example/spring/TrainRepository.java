package org.example.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, String> {

    @Query("SELECT t FROM Train t ORDER BY t.id")
    List<Train> findAllSortedByTrainStart();
}
