package com.mertao.tennisscoreboard.service;

import com.mertao.tennisscoreboard.dto.PlayerDTO;
import com.mertao.tennisscoreboard.entity.Player;

public interface PlayerService {
	public PlayerDTO findPlayerByName(String name);
	public Player savePlayer(Player player);
}
