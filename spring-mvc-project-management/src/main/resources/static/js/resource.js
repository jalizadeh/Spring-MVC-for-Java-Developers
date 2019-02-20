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
		
		var sse = new EventSource("/app/resource/pricing");
		sse.onmessage = function(event){
			$("#cost").val(event.data);
		};
		
		/*
		$.get(ctx + "/resource/pricing", function(data){
			$("#cost").val(data);
		});
		*/
		
		return false;
	});
	
});