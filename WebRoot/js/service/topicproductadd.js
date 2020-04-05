
$(document).ready(function() {
	changeLang_js();
	         
});
//点击查询条件，隐藏结果列表
function productquery() {
	var gpsdivs = document.getElementById("productquery");//获取要隐藏的div的ID
	gpsdivs.style.display = "";//给div的属性改变,显示
	var divID = document.getElementById("pssTasMetadataList");//获取要隐藏的div的ID
	divID.style.display = "none";//给div的属性改变,隐藏
};

// 点击结果列表，隐藏查询条件
function pssTasMetadataList() {
	var gpsdivs = document.getElementById("pssTasMetadataList");//获取要隐藏的div的ID
	gpsdivs.style.display = "";//给div的属性改变,显示
	var divID = document.getElementById("productquery");//获取要隐藏的div的ID
	divID.style.display = "none";//给div的属性改变,隐藏
};
function lonLatQuery() {
}

// 更改产品类型
function changeProductType() {
	// 产品类型
	var productType = $('#topicProductType').val();
	// 大气环境
	var atmosphere = document.getElementById("atmosphere");
	// 水环境
	var water = document.getElementById("water");
	// 生态环境
	var environment = document.getElementById("environment");
	// 灾害监测
	var damage = document.getElementById("damage");

	// 显示大气环境对应的各参数
	if (productType == "atmosphere") {
		atmosphere.style.display = "";
		water.style.display = "none";
		environment.style.display = "none";
		damage.style.display = "none";
	}
	// 显示水环境对应的各参数
	else if (productType == "water") {
		atmosphere.style.display = "none";
		water.style.display = "";
		environment.style.display = "none";
		damage.style.display = "none";
	}
	// 显示生态环境对应的各参数
	else if (productType == "environment") {
		atmosphere.style.display = "none";
		water.style.display = "none";
		environment.style.display = "";
		damage.style.display = "none";
	}
	// 显示灾难监测对应的各参数
	else {
		atmosphere.style.display = "none";
		water.style.display = "none";
		environment.style.display = "none";
		damage.style.display = "";
	}
};
// 点击拇指图，显示产品详情
function showProduct(id) {

	window.location.href = "../pssweb/showProduct.do?productid=" + id;
}
function topicProductsave(){
//	var queryGeometry; 
//	if(qryGeo!=null){
//		if (qryGeo.type == "extent") {
//			queryGeometry = qryGeo.xmin+","+qryGeo.ymax +","+qryGeo.xmax+","+qryGeo.ymax+","
//			 + qryGeo.xmax+","+qryGeo.ymin+","+qryGeo.xmin+","+qryGeo.ymin+","+qryGeo.xmin+","+qryGeo.ymax;
//			
//		}else{
//			queryGeometry = qryGeo.rings.toString() ;
//		}
//	}else
//		queryGeometry = "";
	
	var leftuplong = $('#leftuplong').val();
	var leftuplat = $('#leftuplat').val();
	var rightdownlong = $('#rightdownlong').val();
	var rightdownlat = $('#rightdownlat').val();
	if(leftuplong==''||leftuplat==''||rightdownlong==''||rightdownlat==''){
		alert("区域必选！");
		return;
	}
	var mingsd = $('#mingsd').val();
	var maxgsd = $('#maxgsd').val();
	if(mingsd==''||maxgsd==''){
		alert("分辨率必填");
	}
	
	var imagingstarttime = $('#imagingstarttime').val();
	var imagingstoptime = $('#imagingstoptime').val();
	if(imagingstarttime==''||imagingstoptime==''){
		alert("成像时间必填！");
	}
	var remark = $("[name='MSG']").val();
	
	var producttype=document.getElementsByName("producttype");
	 for(var i=0;i<producttype.length;i++)
	  {
	     if(producttype[i].checked)
	           var intHot = producttype[i].value;
	  }
//	 alert(intHot);
	 var worldName=$("#country").val();
	 var statesName=$("#states").val();
	 
	 var sateSensors=document.getElementsByName("sensorid");
	 for(var i=0;i<sateSensors.length;i++)
	  {
	     if(sateSensors[i].checked)
	           var sateSen = sateSensors[i].value;
	  }
	 if(intHot==undefined){
		 alert("专题产品类型必选！");
		 return;
	 }			  
	 if(sateSen==undefined){
		 alert("卫星-传感器必填！");
		 return;
	 }
	 
//	 alert(sateSen);
	 jQuery.ajax({
			type : 'POST',
		    url : ctx + '/saveTopicProduct.do',
		    data : {
		    	leftuplong:leftuplong,
		    	leftuplat:leftuplat,
		    	rightdownlong:rightdownlong,
		    	rightdownlat:rightdownlat,
		    	mingsd:mingsd,
		    	maxgsd:maxgsd,
		    	imagingstarttime:imagingstarttime,
		    	imagingstoptime:imagingstoptime,
		    	remark:remark,
		    	producttype:intHot, //专题产品类型
		    	sensorid:sateSen//卫星-传感器
		    },
		    success : function(data) {
		    	alert($.i18n.prop('ordersuccess'));
		    	jQuery.ajax({
		    		type : 'POST',
		    	    url : ctx + '/queryshopcarcontol.do',
		    	  
		    	    success : function(data) {
		    	    	$(".gwc_sl").text(data.carcontol);
		    	    },
		    	    error:function(){
		    	    }
		    	});
		    },
		    error:function(){
		    }
		});
	
}

