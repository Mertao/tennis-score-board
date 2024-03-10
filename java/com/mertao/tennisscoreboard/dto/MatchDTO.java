package com.mertao.tennisscoreboard.dto;

import com.mertao.tennisscoreboard.entity.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MatchDTO {
	private long id;
	private PlayerDTO player1;
	private PlayerDTO player2;
	private PlayerDTO winner;
	
	public static Match fromDTO(MatchDTO matchDTO) {
		return Match.builder()
				.id(matchDTO.getId())
				.player1(PlayerDTO.fromDTO(matchDTO.getPlayer1()))
				.player2(PlayerDTO.fromDTO(matchDTO.getPlayer2()))
				.winner(PlayerDTO.fromDTO(matchDTO.getWinner()))
				.build();
	}
	
	public static MatchDTO toDTO(Match match) {
		return MatchDTO.builder()
				.id(match.getId())
				.player1(PlayerDTO.toDTO(match.getPlayer1()))
				.player2(PlayerDTO.toDTO(match.getPlayer2()))
				.winner(PlayerDTO.toDTO(match.getWinner()))
				.build();
	}
}
