$(function(){
	$('#close_im').bind('click',function(){
		$('#main-im').css("height","0");
		$('#im_main').hide();
		$('#open_im').show();
	});
	$('#open_im').bind('click',function(e){
		$('#main-im').css("height","272");
		$('#im_main').show();
		$(this).hide();
	});
	$('#close_im01').bind('click',function(){
	    $('#light').css("right", "-440px");
	    $(this).hide();
		$('#open_im01').show();
	});
	$('#open_im01').bind('click',function(e){
	    $('#light').css("right", "0px");
	    $(this).hide();
	    $('#close_im01').show();
	});
	var gjcs = $("#gjcs");
	var fade = $("#fade")
	$("body").on('click', '#button-gjcs', function () {
	    if (gjcs.is(":visible")) {
	        gjcs.hide();
	        fade.hide();
	    }
	    else {
	        gjcs.show();
	        fade.show();
	    }
	})
	
	
	$("body").on('click', '#button-cx', function () {
		
		 //传感器
		  var PAN1 = document.getElementById('PAN-1');
		  var PAN2 = document.getElementById('PAN-2');
		  var WMC1 = document.getElementById('WMC-1');
		  //传感器
		  var MSS1 = document.getElementById('MSS-1');
		  var MSS2 = document.getElementById('MSS-2');
		  var WMC2 = document.getElementById('WMC-2');
		  //传感器
		  var PANN = document.getElementById('PANNs');
		  var MSSS = document.getElementById('MSSSs');
		  var IRCC = document.getElementById('IRCCs');
		  //传感器
		  var PAN = document.getElementById('PAN');
		  var MSS = document.getElementById('MSS');
		  var WMC = document.getElementById('WMC');
		  //传感器
		  var PPAN = document.getElementById('PPAN');
		  var MMSS = document.getElementById('MMSS');
		  
	    if ($("#open_im01").is(":visible")) {
	    	
	        $('#light').css("right", "0px");
	        $("#close_im01").show();
	        $("#open_im01").hide();
	    	
	    }
	    else {
	        $('#light').css("right", "-340px");
	        $("#close_im01").hide();
	        $("#open_im01").show();
	    }
	    var dks =$("#selectdata").val();
	    if(dks==0 || dks.eql("")){
		    $('#light').css("right", "-500px");
		    $("#close_im01").hide();
		    $("#open_im01").show();
//		    alert("No Data");
	    }else{
		    $('#light').css("right", "0px");
		    $("#close_im01").show();
		    $("#open_im01").hide();
	    }
	    
	    
	})

	$('#close_im02').bind('click', function () {

	    if (gjcs.is(":visible")) {
	        gjcs.hide();
	        fade.hide();
	    }
	    else {
	        gjcs.show();
	        fade.show();
	    }
	});
	$('#open_im02').bind('click',function(e){
		$('#main-im02').css("height","272");
		$('#im_main02').show();
		$(this).hide();
	});
//	$('.go-top').bind('click',function(){
//		$(window).scrollTop(0);
//	});
//	$(".weixing-container").bind('mouseenter',function(){
//		$('.weixing-show').show();
//	})
//	$(".weixing-container").bind('mouseleave',function(){        
//		$('.weixing-show').hide();
//	});
});