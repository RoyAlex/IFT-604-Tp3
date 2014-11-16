
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
    workerForTime();
    workerForMatch();
}

// Make a request to the server with ajax
$(document).ready(function() {
    pageLoad();

    // Retrieve information for a match
    $("#matchsTable").on('click', 'tr', function(event) {
        // Get the id of the row
        var id = $(this).find("td").eq(0).html();
        
        showGameById(id);
    });
});