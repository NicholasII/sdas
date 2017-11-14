/**
 * 离线数据导入的js
 */
$(function(){
$("#form1").submit(function(e) {
    $("#progress").css("display", "inline");
    $("#submit").attr("disabled", true);
});
$("#form2").submit(function() {
    $("#progress1").css("display", "inline");
    $("#submit1").attr("disabled", true);
});

});
function openIframe(type){
	top.$("#offline").attr('src',"/sdas/log/file/page?type="+type);
}
function file_upload(file){
	var ifupload=true;
	if(file.files.length>3){
		alert("超过上传限制，一次最多上传3个！");
		file.value="";
		ifupload=false;
		return;
	}else{
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
		var fileSize = 0;         
	    if (isIE && !file.files) {     
	      var filePath = file.value;
	      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
	      var file = fileSystem.GetFile (filePath); 
	      if(file.files.length>1){
	    		$.each(file.files,function(i,e){
	    			fileSize += e.size;
	    		});
	    	}else{
	    		fileSize = file.Size;  
	    	}
	    } else {
	    	if(file.files.length>1){
	    		$.each(file.files,function(i,e){
	    			fileSize += e.size;
	    		});
	    	}else{
	    		fileSize = file.files[0].size; 
	    	}
	     }   
	     var size = fileSize/(1024*1024*1024);    
	     if(size>4){
	      alert("上传文件总大小不能大于4G");
	      file.value="";
	      ifupload=false;
	      return
	     }
	}
}
function submit_upload(id,formid){
	if($(id).val()==""){
		 alert("请选择文件进行上传");
	}else{
		$(formid).submit();
	}
}