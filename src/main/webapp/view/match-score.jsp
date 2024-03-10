<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/styles/style.css">
<title>Match score</title>
</head>
<body>
	<header class="header">
		<div class="header__wrapper">
			<img class="header__logo" src="view/images/rackets.png" alt="rackets">
			<h1 class="header__title">Tennis score board</h1>
		</div>
	</header>
	<h2 class="match-stats-header" >Match №${matchStatsDTO.getId() } score</h2>

	<div class="match-stats-grid-container">
		<div class="grid-item match-stats__header">Players</div>
		<div class="grid-item match-stats__player-name" id="first-player-name">${matchStatsDTO.getFirstPlayer().getName() }</div>
		<div class="grid-item match-stats__player-name" id="second-player-name">${matchStatsDTO.getSecondPlayer().getName() }</div>
		<div class="grid-item match-stats__header">Sets</div>
		<div class="grid-item">${matchStatsDTO.getFirstPlayerSets() }</div>
		<div class="grid-item">${matchStatsDTO.getSecondPlayerSets() }</div>
		<div class="grid-item match-stats__header">Games</div>
		<div class="grid-item">${matchStatsDTO.getFirstPlayerGames() }</div>
		<div class="grid-item">${matchStatsDTO.getSecondPlayerGames() }</div>
		<c:if test="${matchStatsDTO.isFinishedGame() }">
			<div class="grid-item winner-header match-stats__header">Winner</div>
			<div class="grid-item winner-name">${matchStatsDTO.getWinner().getName() }</div>
		</c:if>
		<c:if test="${!matchStatsDTO.isFinishedGame() }">
			<div class="grid-item match-stats__header">Points</div>
			<div class="grid-item">
				<button id="firstPlayerPoint">0</button>
			</div>
			<div class="grid-item">
				<button id="secondPlayerPoint">0</button>
			</div>
		</c:if>
	</div>
	<div class="hidden">
		<input id="matchId" value="${matchStatsDTO.getId() }">
	</div>
	<div class="buttons">
		<a href="/">Back</a>
	</div>
	<script src="view/script.js"></script>
	<script src="view/match-logic.js"></script>
</body>
</html>
