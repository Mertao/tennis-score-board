package com.mertao.tennisscoreboard.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mertao.tennisscoreboard.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	Player findByName(String name);
}
