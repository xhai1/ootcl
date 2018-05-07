function dtMessageSearch(){
	$(".jdt").css("display","");
	var cusid = $("select[name='cusid']").val(); 
	if(cusid == "")
		 $("select[name='cusid']").empty();
	dtMessage.submit();
}

function dtMessageToNextPage(nextAction){	
	document.body.scrollTop = document.documentElement.scrollTop = 0;
 	$(".jdt").css("display","");
 	window.location.href = nextAction;
}