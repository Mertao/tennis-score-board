package com.mertao.tennisscoreboard.service;

import org.springframework.web.servlet.ModelAndView;

import com.mertao.tennisscoreboard.dto.MatchRequestDTO;
import com.mertao.tennisscoreboard.dto.PlayersRequestDTO;

public interface MatchService {
	public ModelAndView createNewMatch(PlayersRequestDTO playersRequestDTO);
	public ModelAndView getMatchStats(int matchId);
	public ModelAndView updateMatchStats(MatchRequestDTO matchRequestDTO);
	public ModelAndView getMatches(int page, String playerName);
}
