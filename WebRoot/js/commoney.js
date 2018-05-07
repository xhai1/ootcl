/**
 * 动态获取某公司产品大类
 */
function dynamicGetCusType(){
	var cusid = $("select[name='condition1']").val(); 
	var path = $(" #PageContext ").val()
	var name =  $("select[name='condition1']").attr("name"); 
//	alert(path);
	
	$.ajax({  
                    type : "POST",  //提交方式  
                    url : path+"/ComputeMoney/dynamicGetCustype.action",//路径  
                    data : {  
                        "cusid" : cusid  
                    },//数据，这里使用的是Json格式进行传输  
                    success : function(response) {//返回数据根据结果进行相应的处理  
                        if ( response != null ) {  
                           $("select[name='condition2']").empty();
                           $("select[name='condition2']").append(response); 
                           /* if( $("select[name='condition2'] option").length > 1)
                          $("select[name='condition2']")[0].selectedIndex = 1; */
                        } else {  
                            $("select[name='condition2']").append("<option selected>此公司无产品大类记录</option>"); 
                        }  
                    }  
       });  
	
}

/*function exportExcel(expAction){	
	  var cusid = $("select[name='condition1']").val(); 
		var typetimeing = $("select[name='condition2']").val(); 
		var condition3 = $("input[name='condition3']").val(); 
		var condition4 = $("input[name='condition4']").val(); 
//		搜索全部公司，查总计
		if(cusid == 1 && typetimeing == 1){
			    $(".jdtEx").css("display","");
			    
			    
			    $.ajax({  
                    type : "GET",  //提交方式  
                    url : expAction,//路径  
                    data : {  
                        "cusid" : cusid,
                        "condition2" : typetimeing,
                        "condition3" : condition3,
                        "condition4" : condition4
                    },//数据，这里使用的是Json格式进行传输  
                    success : function(response) {//返回数据根据结果进行相应的处理  
                    	
                        if ( response != null ) {  
                        	$(".jdtEx").css("display","none");
                           
                        } else {  
                           
                        }  
                    }  
       });				  
			  
		}else if(cusid != 1 && typetimeing == 1){//查公司全部
			 $(".jdtEx").css("display","");
			 document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/exportByCondition.action";
			  myform.submit();	 
			  window.onload=function(){
				  $(".jdtEx").css("display","none");
			  };
		}else if(cusid == 1 && typetimeing != 1 ){
			alert("错误操作，产品类别错误");
		}	
		else if(cusid != 1 && typetimeing != 1){
			$(".jdtEx").css("display","");
			 document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/exportByCondition.action";
			  myform.submit();	
			  window.onload=function(){
				  $(".jdtEx").css("display","none");
			  };
		}
	  
}*/

function exportExcel(){	
	  var cusid = $("select[name='condition1']").val(); 
		var typetimeing = $("select[name='condition2']").val(); 
//		搜索全部公司，查总计
		if(cusid == 1 && typetimeing == 1){
			    $(".jdtEx").css("display","");
				document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/exportStaCustemer.action";
			  myform.submit();	  			  
		}else if(cusid != 1 && typetimeing == 1){//查公司全部
			 $(".jdtEx").css("display","");
			 document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/exportByCondition.action";
			  myform.submit();	 			  
		}else if(cusid == 1 && typetimeing != 1 ){
			alert("错误操作，产品类别错误");
		}	
		else if(cusid != 1 && typetimeing != 1){
			$(".jdtEx").css("display","");
			 document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/exportByCondition.action";
			  myform.submit();				  
		}
	  
}

function search(){	
	var cusid = $("select[name='condition1']").val(); 
	var typetimeing = $("select[name='condition2']").val(); 
//	搜索全部公司，查总计
	if(cusid == 1 && typetimeing == 1){
		$(".jdt").css("display","");
		document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/staAllCompanyForSearch.action";
		myform.submit();	
	}else if(cusid != 1 && typetimeing == 1){//查公司全部
		$(".jdt").css("display","");
		document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/SearchByCondition.action";
		myform.submit();	 
	}else if(cusid == 1 && typetimeing != 1 ){
		alert("错误操作，产品类别错误");
	}	
	else if(cusid != 1 && typetimeing != 1){
		$(".jdt").css("display","");
		document.getElementById("myform").action=$(" #PageContext ").val()+"/ComputeMoney/SearchByCondition.action";
		myform.submit();	  
	}

}
