/**
 * 
 */
function delRecord(delAction,hotLine){
	if(confirm("确定删除落地号为"+hotLine+"的记录吗？")){
		window.location = delAction;
	}	
}

function checkForm(){
	// 校验用户名:
	// 获得用户名文本框的值:
	var business = $("input[name='business']").val();
	if(business == null || business == ''){
		alert("业务不能为空!");
		return false;
	}
	// 校验密码:
	// 获得密码框的值:
	var cusTelephone = $("input[name='cusTelephone']").val();
	if(cusTelephone == null || cusTelephone == ''){
		alert("热线号不能为空!");
		return false;
	}
	// 校验确认密码:
	var ivr = $("input[name='ivr']").val();
	if(ivr == null || ivr == ''){
		alert("IVR不能为空");
		return false;
	}
	
	var telephone = $("input[name='telephone']").val();
	if(telephone == null || telephone == ''){
		alert("落地号不能为空");
		return false;
	}
	
	var itSystem = $("input[name='itSystem']").val();
	if(itSystem == null || itSystem == ''){
		alert("所属系统不能为空");
		return false;
	}
	
	var bigType = $("input[name='bigType']").val();
	if(bigType == null || bigType == ''){
		alert("计时产品大类不能为空");
		return false;
	}
	
	var cusId = $("select[name='cusId']").val();
	if(cusId == null || cusId == ''){
		alert("请选择计时所属公司"+cusId);
		return false;
	}
	
	var chaSystem = $("input[name='chaSystem']").val();
	if(chaSystem == null || chaSystem == ''){
		alert("话务系统不能为空");
		return false;
	}
	return  true;
}