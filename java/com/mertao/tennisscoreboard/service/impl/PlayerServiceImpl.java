package com.mertao.tennisscoreboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mertao.tennisscoreboard.dto.PlayerDTO;
import com.mertao.tennisscoreboard.entity.Player;
import com.mertao.tennisscoreboard.entity.repository.PlayerRepository;
import com.mertao.tennisscoreboard.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerRepository playerRepository;
	
	@Override
	public PlayerDTO findPlayerByName(String name) {
		return PlayerDTO.toDTO(playerRepository.findByName(name));
	}
	
	@Override
	public Player savePlayer(Player player) {
		return playerRepository.save(player);
	}
}
