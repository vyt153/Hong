$(function() {
	$("a[href='#']").click(function(){
		return false;
	})
		 $("ul.nav").mouseenter(function () {
		        $(this).find("li").stop().slideDown();
		    });
		    $("ul.nav").mouseleave(function () {
		        $(this).find("li").stop().slideUp(100);
		    });
		setInterval(function() {
			$("div#slideFrame").delay(2000);
			$("div#slideFrame").animate({ "margin-left": "-1200px" }, 2000,
				function() {
					$("div#slideFrame").children("a").eq(0).insertAfter("div#slideFrame a:last-child");
					$(this).css({ "margin-left": "0" })
				});
		});
		
		$("#layerTd").click(function(){
			$("#layer").css({"display":"block"})			
		});
		$("#btnLayer").click(function(){
			$("#layer").css({"display":"none"})
		});
	});