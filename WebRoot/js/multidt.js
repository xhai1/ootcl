function dtMultiVoiceSearch(){
	$(".jdt").css("display","");
	var cusid = $("select[name='cusid']").val();
	if(cusid == "")
		 $("select[name='cusid']").empty();
	dtMultiVoice.submit();
}

function dtMultiVoiceToNextPage(nextAction){	
	document.body.scrollTop = document.documentElement.scrollTop = 0;
 	$(".jdt").css("display","");
 	window.location.href = nextAction;
}