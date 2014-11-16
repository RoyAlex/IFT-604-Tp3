
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