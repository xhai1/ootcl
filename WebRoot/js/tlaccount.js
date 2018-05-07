function search(showAction){
	var condition1 = new Array();
    $("input[name='checkbox']:checked").each(function() {
    	condition1.push($(this).val());
    });
    var condition2 = $("input[name='condition2']").val();
    var condition3 = $("input[name='condition3']").val();
    window.location.href = showAction + "?condition1="+condition1.toString()+""
}