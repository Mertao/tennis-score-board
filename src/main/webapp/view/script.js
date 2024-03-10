function searchByName() {
    var name = document.getElementById("playerName").value;
    if (name.trim() !== "") {
        var url = "/matches?filter_by_player_name=" + encodeURIComponent(name);
        window.location.href = url;
    } else {
        alert("Please enter a player name.")
    }
}

function namesValidation() {
    var firstName = document.getElementById("firstPlayerName").value;
    var secondName = document.getElementById("secondPlayerName").value;

    if (firstName.trim() === "" || secondName.trim() === "" || firstName === secondName) {
        alert("Please enter the correct names.")
        return false;
    }
    return true;
}

function prevPage() {
    var page = getPageNumber() - 1;
    if (page >= 1) {
        pagePaginationRedirect(page);
    }
}

function nextPage() {
    var page = getPageNumber() + 1;
    if (page <= getTotalPages()) {
        pagePaginationRedirect(page);
    }
    console.log(page);
}

function getPageNumber() {
    var page = document.getElementById("hiddenPage").value;
    return parseInt(page);
}

function getTotalPages() {
    var totalPages = document.getElementById("hiddenTotalPages").value;
    return parseInt(totalPages);
}

function getPlayerName() {
    var playerName = document.getElementById("hiddenPlayerName").value;
    if (playerName.trim() === '') {
        return null;
    }
    return playerName;
}

function pagePaginationRedirect(page) {
    var url = "/matches?page=" + page;

    if (getPlayerName() !== null) {
        url = url + "&filter_by_player_name=" + getPlayerName();
    }

    window.location.href = url;
}