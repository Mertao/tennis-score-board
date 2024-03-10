var firstPlayerButton = document.getElementById('firstPlayerPoint');
var secondPlayerButton = document.getElementById('secondPlayerPoint');

var firstPlayerScore = 0;
var secondPlayerScore = 0;

var firstPlayerName = document.getElementById("first-player-name").textContent;
var secondPlayerName = document.getElementById("second-player-name").textContent;


firstPlayerButton.addEventListener("click", function() {
    updateMatchScore(firstPlayerName);
});


secondPlayerButton.addEventListener("click", function() {
    updateMatchScore(secondPlayerName);
});


function updateMatchScore(player) {
    if (player === firstPlayerName) {
        if (firstPlayerScore === 0) {
            firstPlayerScore = 15;
        } else if (firstPlayerScore === 15) {
            firstPlayerScore = 30;
        } else if (firstPlayerScore === 30) {
            if (secondPlayerScore === 40) {
                firstPlayerScore = "deuce";
                secondPlayerScore = "deuce";
            } else {
                firstPlayerScore = 40;
            }
        } else if (firstPlayerScore === 40 && secondPlayerScore < 40) {
            // first player win
            matchScorePostRequest(firstPlayerName);
            firstPlayerScore = 0;
            secondPlayerScore = 0;
        } else if (firstPlayerScore === "deuce") {
            firstPlayerScore = "with Advantage";
            secondPlayerScore = "without Advantage";
        } else if (firstPlayerScore == "with Advantage") {
            // first player win
            matchScorePostRequest(firstPlayerName);
            firstPlayerScore = 0;
            secondPlayerScore = 0;
        } else if (firstPlayerScore === "without Advantage") {
            firstPlayerScore = "deuce";
            secondPlayerScore = "deuce";
        }
    }
    else if (player === secondPlayerName) {
        if (secondPlayerScore === 0) {
            secondPlayerScore = 15;
        } else if (secondPlayerScore === 15) {
            secondPlayerScore = 30;
        } else if (secondPlayerScore === 30) {
            if (firstPlayerScore === 40) {
                firstPlayerScore = "deuce";
                secondPlayerScore = "deuce";
            } else {
                secondPlayerScore = 40;
            }
        } else if (secondPlayerScore === 40 && firstPlayerScore < 40) {
            // second player win
            matchScorePostRequest(secondPlayerName);
            firstPlayerScore = 0;
            secondPlayerScore = 0;
        } else if (secondPlayerScore === "deuce") {
            secondPlayerScore = "with Advantage";
            firstPlayerScore = "without Advantage";
        } else if (secondPlayerScore === "with Advantage") {
            // second player win
            matchScorePostRequest(secondPlayerName);
            firstPlayerScore = 0;
            secondPlayerScore = 0;
        } else if (secondPlayerScore === "without Advantage") {
            firstPlayerScore = "deuce";
            secondPlayerScore = "deuce";
        }
    }

    displayScore(firstPlayerButton, firstPlayerScore);
    displayScore(secondPlayerButton, secondPlayerScore);
};


function displayScore(displayElement, score) {
    displayElement.textContent = score;
};

function matchScorePostRequest(winner) {
    var matchId = document.getElementById("matchId").value;
    var formData = new FormData();
    formData.append("matchId", matchId);
    formData.append("winnerName", winner);
    
    var options = {
        method: 'POST',
        body: formData
    };
    
    fetch("/match-score?uuid=" + matchId, options)
    .then(response => {
        if (response.ok) {
            window.location.reload();
        }
    });
}