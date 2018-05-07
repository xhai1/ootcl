function uChgOrderSearch(){
	$(".jdt").css("display","");
	var cusid = $("select[name='cusid']").val(); 
	if(cusid == "cusidall")
		 $("select[name='cusid']").empty();
	var typeid = $("select[name='typeid']").val(); 
	if(typeid == "all")
		 $("select[name='typeid']").empty();
	uChgOrder.submit();
}

function uChgOrderToNextPage(nextAction){	
	document.body.scrollTop = document.documentElement.scrollTop = 0;
 	$(".jdt").css("display","");
 	window.location.href = nextAction;
}