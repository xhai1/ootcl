function cusTypeSearch(){	
	var typeid = $("select[name='typeid']").val(); 
	if(typeid == "typeidall" || typeid =="1")
		 $("select[name='typeid']").empty();
	cusType.submit();
}


function getCustype(path,cusid){
	
	var cusid = $("select[name='cusid']").val();
	$.ajax({  
                    type : "POST",  //提交方式  
                    url :  path+"/ComputeMoney/dynamicGetCustype.action",//路径    
                    data : {  
                        "cusid" : cusid  
                    },//数据，这里使用的是Json格式进行传输  
                    success : function(response) {//返回数据根据结果进行相应的处理  
                        if ( response != null ) {  
                           $("select[name='typeid']").empty();
                           $("select[name='typeid']").append(response); 
                           /* if( $("select[name='condition2'] option").length > 1)
                          $("select[name='condition2']")[0].selectedIndex = 1; */
                        } else {  
                            $("select[name='typeid']").append("<option selected>此公司无产品大类记录</option>"); 
                        }  
                    }  
       });  
}