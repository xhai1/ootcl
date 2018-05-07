function dtVoiceSearch(){
	$(".jdt").css("display","");
	var calltype = $("select[name='calltype']").val(); 
	if(calltype == "calltypeall")
		 $("select[name='calltype']").empty();
	var cusid = $("select[name='cusid']").val(); 
	if(cusid == "cusidall")
		 $("select[name='cusid']").empty();
	var typeid = $("select[name='typeid']").val(); 
	if(typeid == "typeidall")
		 $("select[name='typeid']").empty();	
	dtvoice.submit();
}

function dtVoiceToNextPage(nextAction){	
	document.body.scrollTop = document.documentElement.scrollTop = 0;
 	$(".jdt").css("display","");
 	window.location.href = nextAction;
}