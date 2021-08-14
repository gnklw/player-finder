package com.example.football.repository;

import com.example.football.models.dto.BestPlayerDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerByEmail(String email);

//    @Query(value = "select p " +
//            "from Player p " +
//            "join Stat s on p.id = s.id " +
//            "where p.birthDate > :startDate and p.birthDate < :endDate " +
//            "order by s.shooting desc, s.passing desc, s.endurance desc, p.lastName")
    @Query("SELECT new com.example.football.models.dto.BestPlayerDto(" +
            "p.firstName, p.lastName, p.position, p.team.name, p.team.stadiumName) " +
            "FROM Player p " +
            "WHERE p.birthDate between :startDate and :endDate " +
            "ORDER BY p.stat.shooting DESC, p.stat.passing DESC, p.stat.endurance DESC, p.lastName ASC")
    Collection<BestPlayerDto> findPlayersOrderByShootingPassingEnduranceAndLastName(
            @Param(value = "startDate") LocalDate startDate,
            @Param(value = "endDate") LocalDate endDate
    );
}
