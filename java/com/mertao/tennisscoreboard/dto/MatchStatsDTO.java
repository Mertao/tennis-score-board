package com.mertao.tennisscoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MatchStatsDTO {
	private long id;
	private PlayerDTO firstPlayer;
	private PlayerDTO secondPlayer;
	private int firstPlayerSets;
	private int secondPlayerSets;
	private int firstPlayerGames;
	private int secondPlayerGames;
	private PlayerDTO winner;
	private boolean finishedGame;
}
