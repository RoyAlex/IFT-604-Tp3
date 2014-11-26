
var POST = "POST";
var URL = "http://127.0.0.1:9090";
var DATATYPE = "text";

// variables for timeout
var TIME_REFRESH = 10 * 1000;
var MATCH_REFRESH = 30 * 1000;
var PENALTIES_REFRESH = 10 * 1000;
var GOALS_REFRESH = 10 * 1000;

// Show toasts
var TOAST_PENALTY = "showWarningToast";
var TOAST_GOAL = "showNoticeToast";
var TOAST_PARI = "showSuccessToast";

if (localStorage) {
    // retrieve 
	if (typeof localStorage.getItem('GUID') !== 'undefined' && localStorage.getItem('GUID') !== null){
		document.getElementById("result").innerHTML = localStorage.getItem("GUID");
	}
	//store
	else{
		var ID_CLIENT = guid();
		localStorage.setItem("GUID", ID_CLIENT);
	}
	//ne supporte pas html 5
} else {
    document.getElementById("result").innerHTML = "Desole votre naviguateur ne supporte pas HTML5"
}