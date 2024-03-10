package com.mertao.tennisscoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mertao.tennisscoreboard.dto.MatchRequestDTO;
import com.mertao.tennisscoreboard.dto.PlayersRequestDTO;
import com.mertao.tennisscoreboard.service.MatchService;
import com.mertao.tennisscoreboard.service.PlayerService;

@RestController
public class Controller {
	@Autowired
	PlayerService playerService;
	
	@Autowired
	MatchService matchService;
	
	@GetMapping("/")
	public ModelAndView getMainPage() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/new-match")
	public ModelAndView getNewMatch() {
		return new ModelAndView("new-match");
	}
	
	@PostMapping("/new-match")
	public ModelAndView createnewMatch(@ModelAttribute PlayersRequestDTO playersRequestDTO) {
		return matchService.createNewMatch(playersRequestDTO);
	}
	
	@GetMapping("/match-score")
	public ModelAndView getMatchStats(@RequestParam(name="uuid") int matchId) {
		return matchService.getMatchStats(matchId);
	}
	
	@PostMapping("/match-score")
	public ModelAndView updateMatchStats(@ModelAttribute MatchRequestDTO matchRequestDTO) {
		return matchService.updateMatchStats(matchRequestDTO);
	}
	
	@GetMapping("/matches")
	public ModelAndView getMatches(@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="filter_by_player_name", required=false) String playerName) {
		return matchService.getMatches(page, playerName);
	}
	
	
}
