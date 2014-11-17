
function showGameById(id)
{
    // Erase row for penalties and goals
    clearPenaltiesAndGoals();

    // Request for a match
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getMatch(id)
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            var obj = JSON.parse(responseJson);
            fillMatch(obj);
        }
    });
    
    // Request for penalties
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getPenalitesForAMatch(id)
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            if (responseJson != "emptyList")
            {
                var obj = JSON.parse(responseJson);
                fillPenalites(obj);
            }
        }
    });
    
    // Request for goals
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getCompteursForAMatch(id)
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            if (responseJson != "emptyList")
            {
                var obj = JSON.parse(responseJson);
                fillCompteurs(obj);
            }
        }
    });
}

function refreshTime(id)
{
    if (id != "")
    { 
        $.ajax({
            type: POST,
            url: URL,
            data: {
                request: getTimeForAMatch(id)
            },
            dataType: DATATYPE,
            success: function(responseJson) {
                var obj = JSON.parse(responseJson);
                updateTime(obj);
            }
        });
    }
}

function requestAllPenalites()
{
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getAllPenalites()
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            if (responseJson != "emptyList")
            {
                var obj = JSON.parse(responseJson);
                ShowPenalites(obj);
            }
        }
    });
}

function requestAllGoals()
{
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getAllCompteurs()
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            if (responseJson != "emptyList")
            {
                var obj = JSON.parse(responseJson);
                ShowGoals(obj);
            }
        }
    });
}

function requestSetPari(idMatch, idEquipe, montant)
{
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: setPari(ID_CLIENT, idMatch, idEquipe, montant)
        },
        dataType: DATATYPE,
        success: function(responseJson) {
                var obj = JSON.parse(responseJson);
                
                if (obj.matchPari_estEnregistrer)
                    $().toastmessage(TOAST_PARI, "Congratulations, your bet of " + obj.matchPari_montant + " $ have been registered !");
                else
                    alert("Your bet has not been registered");
        }
    });   
}

function requestGetPari(idMatch, idEquipe)
{
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getPari(ID_CLIENT, idMatch)
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            if (responseJson != "emptyList")
            {
                var obj = JSON.parse(responseJson);
                showPari(obj);               
            }
        }
    });   
}

function pageLoad()
{
    // On page load (show list of matchs)
    $.ajax({
        type: POST,
        url: URL,
        data: {
            request: getMatchs
        },
        dataType: DATATYPE,
        success: function(responseJson) {
            var obj = JSON.parse(responseJson);
            fillMatchsTable(obj);
        }
    });
    
    // Start timers here
    startTimers();
}

function startTimers()
{
    workerForTime();
    workerForMatch();
    workerForPenalties();
    workerForGoals();
}

// Make a request to the server with ajax
$(document).ready(function() {
    pageLoad();

    getButtonsClick();
});