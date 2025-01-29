package com.competitivearmylists.storageservice.repositories;
import com.competitivearmylists.storageservice.entities.CompetitorEventResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorEventResultRepository extends JpaRepository<CompetitorEventResult, Long>{

}

