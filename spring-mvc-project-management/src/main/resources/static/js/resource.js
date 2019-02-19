$(document).ready(function(){
	
	$("#request-link").click(function(e){
		e.preventDefault();
		$.post(ctx + "/resource/request", $("form").serialize(), function(data){			
			alert(data);
		});
		return false;
	});
	
	
	$("#pricing-link").click(function(e){
		e.preventDefault();
		
		$.get(ctx + "/resource/pricing", function(data){
			$("#cost").val(data);
		});
		return false;
	});
	
});