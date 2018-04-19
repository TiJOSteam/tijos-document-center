$( document ).ready(function() {
    hljs.initHighlightingOnLoad();
    $('table').addClass('table table-striped table-hover');
	if($(window).width() >= 992){
		   document.getElementsByClassName("well")[0].style["max-height"]= $(window).height() - 217 +'px';
	}
	window.onresize = function(){
		if($(window).width() >= 992){
		   document.getElementsByClassName("well")[0].style["max-height"]= $(window).height() - 217 +'px';
		}else{
		   document.getElementsByClassName("well")[0].style["max-height"]= '';
		}
	}
	if(localStorage.customMenuDisplay){
		$("#current-page:not(.dropdown)").parent().clone().appendTo('#custom-menu');
		$('#custom-menu ul:not(":eq(0)")').remove();
		$('#custom-menu').css('display',localStorage.customMenuDisplay);
		localStorage.customMenuDisplay = $('#custom-menu').css('display');
	}
	$('#show-custom-meau').click(function(){
		if(localStorage.customMenuDisplay == 'block'){
			$('#custom-menu').css('display','none');
			localStorage.customMenuDisplay = 'none';
		}else{
			if(!localStorage.customMenuDisplay){
			$("#current-page:not(.dropdown)").parent().clone().appendTo('#custom-menu');
			$('#custom-menu ul:not(":eq(0)")').remove();
			$('#custom-menu').css('display',localStorage.customMenuDisplay);
			localStorage.customMenuDisplay = $('#custom-menu').css('display');
	        }
			$('#custom-menu').css('display','block');
			localStorage.customMenuDisplay = 'block';
		}
	})
});

/* Highlight */
$('body').scrollspy({
    target: '.bs-sidebar',
});


/* Prevent disabled links from causing a page reload */
$("li.disabled a").click(function() {
    event.preventDefault();
});

    
	
	//if(html){
	//	$(html)clone
		//html = html.parentElement.innerHTML;
		//var count = html.split("ul").length-1;
		//html = html.replace(/\/ul/g, '--');
		//html = html.replace(/ul/g, '!--');
		//html = html.replace(/dropdown-submenu/g, '');
	//	$("body").append('<div id="custom-meau" style="width:120px;position:fixed;right:65px;bottom:30px;"><ul>' + html +'</ul></div>');
		//$("#hellox>ul> li").each(function(){
		//	if($(this).hasClass("dropdown-submenu")){
		//		console.info($($(this).find("a")[0]).attr("href",$($(this).find("ul>li>a")[0]).attr("href")));
		//	}
		//});	
	//}
	



