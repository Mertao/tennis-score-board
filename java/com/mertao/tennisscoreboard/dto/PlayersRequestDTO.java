package com.mertao.tennisscoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayersRequestDTO {
	private String firstPlayerName;
	private String secondPlayerName;
}
