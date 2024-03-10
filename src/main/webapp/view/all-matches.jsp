<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/styles/style.css">
<title>Matches</title>
</head>
<body>
	<header class="header">
		<div class="header__wrapper">
			<img class="header__logo" src="view/images/rackets.png" alt="rackets">
			<h1 class="header__title">Tennis score board</h1>
		</div>
	</header>
	<div class="match-table">
		<table>
			<thead class="match-table__head">
				<tr>
					<th>First Player</th>
					<th>Second Player</th>
					<th>Winner</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty currentMatches}">
					<tr>
						<td colspan="3">No matches found</td>
					</tr>
				</c:if>
				<c:forEach items="${currentMatches}" var="match">
					<tr>
						<td>${match.getPlayer1().getName()}</td>
						<td>${match.getPlayer2().getName()}</td>
						<td>${match.getWinner().getName()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<div class="match-table__seacrh">
				<button class="match-table__pagination-button-prev" onclick="prevPage()"><</button>
				<input type="text" id="playerName" placeholder="Player name"
					class="match-table__seacrh-input">
				<button onclick="searchByName()" class="match-table__seacrh-button">Search</button>
				<button class="match-table__pagination-button-next" onclick="nextPage()">></button>
			</div>
		</div>
	</div>
	<div class="hidden">
		<input type="hidden" id="hiddenPage" value="${hiddenPage}">
		<input type="hidden" id="hiddenPlayerName" value="${hiddenPlayerName}">
		<input type="hidden" id="hiddenTotalPages" value="${hiddenTotalPages}">
	</div>
	<div class="buttons">
		<a href="/new-match">New match</a>
		<a href="/">Back</a>
	</div>
	<script src="view/script.js"></script>
</body>
</html>
