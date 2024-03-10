package com.mertao.tennisscoreboard.dto;

import com.mertao.tennisscoreboard.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerDTO {
	private long id;
	private String name;
	
	public PlayerDTO(String name) {
		this.name = name;
	}
	
	public static Player fromDTO(PlayerDTO playerDTO) {
		return Player.builder()
				.id(playerDTO.getId())
				.name(playerDTO.getName())
				.build();
	}
	
	public static PlayerDTO toDTO(Player player) {
		return PlayerDTO.builder()
				.id(player.getId())
				.name(player.getName())
				.build();
	}
}
