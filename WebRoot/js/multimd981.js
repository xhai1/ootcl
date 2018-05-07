function del981Record(delAction,extNumber){
	if(confirm("确定删除分机号为"+extNumber+"的记录吗？")){
		window.location = delAction;
	}	
}