
// Format the time to be more beautiful
function cleanTime(minutes, seconds)
{
   var sec = seconds;
   
   if (seconds < 10)
       sec = "0" + seconds;  
       
   return minutes + ":" + sec;
}

// Clear both tables at the bottom
function clearPenaltiesAndGoals()
{
    $("#matchPenalites .rowPenalite").remove();
    $("#matchCompteurs .rowCompteur").remove();
}

// Generate a guid
var guid = (function() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
               .toString(16)
               .substring(1);
    }
    return function() {
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    };
})();

// Refresh Time
function workerForTime() 
{
    var id = $("#idMatch").html();
    refreshTime(id);
    setTimeout(workerForTime, TIME_REFRESH);
}

// Refresh Match
function workerForMatch() 
{
    var id = $("#idMatch").html();
    showGameById(id);
    setTimeout(workerForMatch, MATCH_REFRESH);
}

// Show all Penalties
function workerForPenalties()
{
    requestAllPenalites();
    setTimeout(workerForPenalties, PENALTIES_REFRESH);
}

// Show all goals
function workerForGoals()
{
    requestAllGoals();
    setTimeout(workerForGoals, GOALS_REFRESH);
}