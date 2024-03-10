<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="view/styles/style.css">
<meta charset="UTF-8">
<title>New match</title>
</head>
<body>
	<header class="header">
		<div class="header__wrapper">
			<img class="header__logo" src="view/images/rackets.png" alt="rackets">
			<h1 class="header__title">Tennis score board</h1>
		</div>
	</header>
	<form action="/new-match" method="post" onsubmit="return namesValidation()"class="new-match-form buttons">
		<label for="firstPlayerName" class="new-match-form__label">Player 1</label>
		<input type="text" id="firstPlayerName" name="firstPlayerName" class="new-match-form__input">
		
		<label for="secondPlayerName" class="new-match-form__label">Player 2</label>
		<input type="text" id="secondPlayerName" name="secondPlayerName" class="new-match-form__input">
		
		<button type="submit" class="new-match-form__submit button">Game</button>
	</form>
	<div class="buttons">
		<a href="/">Back</a>
	</div>
	<script src="view/script.js"></script>
</body>
</html>
