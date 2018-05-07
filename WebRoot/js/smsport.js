function delSmsRecord(delAction,port){
	if(confirm("确定删除端口号为"+port+"的记录吗？")){
		window.location = delAction;
	}	
}