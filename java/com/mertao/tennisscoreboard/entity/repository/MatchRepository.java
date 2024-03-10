package com.mertao.tennisscoreboard.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mertao.tennisscoreboard.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	@Query("SELECT match FROM Match match WHERE player1.name = :playerName OR player2.name = :playerName")
	public List<Match> findByPlayerName(@Param("playerName") String playerName);
}
