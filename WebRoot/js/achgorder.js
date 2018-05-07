function aChgOrderSearch(){
	$(".jdt").css("display","");
/*	var cusid = $("select[name='cusid']").val(); 
	if(cusid == "")
		 $("select[name='cusid']").empty();*/
	var typeid = $("select[name='typeid']").val(); 
	if(typeid == "all")
		 $("select[name='typeid']").empty();
	aChgOrder.submit();
}

function aChgOrderToNextPage(nextAction){	
	document.body.scrollTop = document.documentElement.scrollTop = 0;
 	$(".jdt").css("display","");
 	window.location.href = nextAction;
}
