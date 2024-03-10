package com.mertao.tennisscoreboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mertao.tennisscoreboard.dto.MatchDTO;
import com.mertao.tennisscoreboard.dto.MatchRequestDTO;
import com.mertao.tennisscoreboard.dto.MatchStatsDTO;
import com.mertao.tennisscoreboard.dto.PlayerDTO;
import com.mertao.tennisscoreboard.dto.PlayersRequestDTO;
import com.mertao.tennisscoreboard.entity.Match;
import com.mertao.tennisscoreboard.entity.Player;
import com.mertao.tennisscoreboard.entity.repository.MatchRepository;
import com.mertao.tennisscoreboard.service.MatchService;
import com.mertao.tennisscoreboard.service.PlayerService;

@Service
public class MatchServiceImpl implements MatchService {

	private static final int PAGE_SIZE = 5;
	private static final int SETS_FOR_WIN = 6;
	private static final int TOTAL_GAMES_FOR_WIN = 2;
	private static final int DIFFERENCE_SETS_FOR_WIN = 2;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	PlayerService playerService;

	private final HashMap<Integer, MatchStatsDTO> matches = new HashMap<>();

	public ModelAndView getMatches(int page, String playerName) {
		ModelAndView modelAndView = new ModelAndView("all-matches");
		List<MatchDTO> allMatches = getMatchList(playerName);
		
		page = getPageNumber(page, allMatches.size());
		List<MatchDTO> currentMatches = getMatchesOnPage(page, allMatches);
		
		modelAndView.addObject("hiddenTotalPages", getTotalPages(allMatches.size()));
		modelAndView.addObject("hiddenPlayerName", playerName);
		modelAndView.addObject("hiddenPage", page);
		modelAndView.addObject("currentMatches", currentMatches);
		return modelAndView;
	}

	public ModelAndView getMatchStats(int matchId) {
		ModelAndView modelAndView = new ModelAndView("match-score");
		modelAndView.addObject(matches.get(matchId));
		return modelAndView;
	}

	public ModelAndView createNewMatch(PlayersRequestDTO playersRequestDTO) {
		ModelAndView modelAndView = new ModelAndView("redirect:/match-score");
		MatchStatsDTO matchStatsDTO = new MatchStatsDTO();
		int matchId = matches.size() + 1;

		matchStatsDTO.setId(matchId);
		matchStatsDTO.setFirstPlayer(new PlayerDTO(playersRequestDTO.getFirstPlayerName()));
		matchStatsDTO.setSecondPlayer(new PlayerDTO(playersRequestDTO.getSecondPlayerName()));

		matches.put(matchId, matchStatsDTO);
		modelAndView.addObject("uuid", matchId);
		return modelAndView;
	}

	public ModelAndView updateMatchStats(MatchRequestDTO matchRequestDTO) {
		ModelAndView modelAndView = new ModelAndView("match-score");
		MatchStatsDTO currentMatchStats = matches.get(matchRequestDTO.getMatchId());

		if (firstPlayerPoint(currentMatchStats, matchRequestDTO)) {
			currentMatchStats.setFirstPlayerSets(currentMatchStats.getFirstPlayerSets() + 1);
			if (setsForWin(currentMatchStats.getFirstPlayerSets(), currentMatchStats.getSecondPlayerSets())) {
				currentMatchStats.setFirstPlayerGames(currentMatchStats.getFirstPlayerGames() + 1);
				resetSets(currentMatchStats);
				if (gamesForWin(currentMatchStats.getFirstPlayerGames())) {
					// first player win
					currentMatchStats.setWinner(currentMatchStats.getFirstPlayer());
					saveGame(currentMatchStats);
				}
			} 
			
		} else {
			currentMatchStats.setSecondPlayerSets(currentMatchStats.getSecondPlayerSets() + 1);
			if (setsForWin(currentMatchStats.getSecondPlayerSets(), currentMatchStats.getFirstPlayerSets())) {
				currentMatchStats.setSecondPlayerGames(currentMatchStats.getSecondPlayerGames() + 1);
				resetSets(currentMatchStats);
				if (gamesForWin(currentMatchStats.getSecondPlayerGames())) {
					// second player win
					currentMatchStats.setWinner(currentMatchStats.getSecondPlayer());
					saveGame(currentMatchStats);
				}
			}
		}
		modelAndView.addObject("matchStats", currentMatchStats);
		return modelAndView;
	}

	private List<MatchDTO> getMatchList(String playerName) {
		if (playerName == null) {
			return matchRepository.findAll().stream().map(MatchDTO::toDTO).collect(Collectors.toList());
		} else {
			return matchRepository.findByPlayerName(playerName).stream().map(MatchDTO::toDTO)
					.collect(Collectors.toList());
		}
	}

	private List<MatchDTO> getMatchesOnPage(int page, List<MatchDTO> allMatches) {
		try {
			int start = (page - 1) * PAGE_SIZE;
			int end = Math.min(start + PAGE_SIZE, allMatches.size());

			return allMatches.subList(start, end);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private int getPageNumber(int page, int matchListSize) {
		int totalPages = getTotalPages(matchListSize);
		if (page < 1) {
			page = 1;
		} else if (page > totalPages) {
			page = totalPages;
		}
		return page;
	}
	
	private int getTotalPages(int matchListSize) {
		return (int) Math.ceil((double) matchListSize / PAGE_SIZE);
	}
	
	private boolean firstPlayerPoint(MatchStatsDTO currentMatchStats, MatchRequestDTO matchRequestDTO) {
		return currentMatchStats.getFirstPlayer().getName().equals(matchRequestDTO.getWinnerName());
	}
	
	private boolean setsForWin(int firstSets, int secondSets) {
		return (firstSets >= SETS_FOR_WIN && firstSets - secondSets >= DIFFERENCE_SETS_FOR_WIN);
	}
	
	private boolean gamesForWin(int games) {
		return games == TOTAL_GAMES_FOR_WIN;
	}
	
	private void resetSets(MatchStatsDTO currentMatchStats) {
		currentMatchStats.setFirstPlayerSets(0);
		currentMatchStats.setSecondPlayerSets(0);
	}
	
	private void saveGame(MatchStatsDTO currentMatchStats) {
		currentMatchStats.setFinishedGame(true);
		Player winner = PlayerDTO.fromDTO(currentMatchStats.getWinner());
		Player firstPlayer = playerService.savePlayer(PlayerDTO.fromDTO(currentMatchStats.getFirstPlayer()));
		Player secondPlayer = playerService.savePlayer(PlayerDTO.fromDTO(currentMatchStats.getSecondPlayer()));
		
		if (firstPlayer.getName().equals(winner.getName())) {
			winner = firstPlayer;
		} else {
			winner = secondPlayer;
		}
		
		Match match = Match.builder()
				.player1(firstPlayer)
				.player2(secondPlayer)
				.winner(winner)
				.build();

		matchRepository.save(match);
	}
}
