package io.javaweb.iplfever.repository;

import org.springframework.data.repository.CrudRepository;
import io.javaweb.iplfever.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    
    Team findByTeamName(String teamName);
}
