$(document).ready(function() {
	changeLang_js();
	  $('#cc').combotree({
	        
	         onlyLeafCheck:true,
	       
	      }); 
});
// 点击查询条件，隐藏结果列表
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
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/selectbytype.do',
	    dataType : 'json',
	    data : {
	    	type:productType
	    }
});	
	
	

	
};
// 点击拇指图，显示产品详情
function showProduct(id) {

	window.location.href = "../pssweb/showProduct.do?productid=" + id;
}

//显示遮罩层
function showMaskForImportTif() {
	jQuery("#jqmetercontainer").css("height", jQuery(document).height());
	jQuery("#jqmetercontainer").css("width", jQuery(document).width());

	jQuery("#jqmetercontainer").show();
}
//隐藏遮罩层  
function hideMaskForImportTif() {
	jQuery("#jqmetercontainer").hide();
}
var a = 0;

//专题产品查询
function topicProductSearch() {
	showMaskForImportTif();
	jQuery("#map").hide();
	flag = 2;
	var sensorid = true;

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
	 
		//空间范围 查询通过sde空间函数实现
	var queryGeometry; 
	if(qryGeo!=null){
		if (qryGeo.type == "extent") {
			queryGeometry = qryGeo.xmin+","+qryGeo.ymax +","+qryGeo.xmax+","+qryGeo.ymax+","
			 + qryGeo.xmax+","+qryGeo.ymin+","+qryGeo.xmin+","+qryGeo.ymin+","+qryGeo.xmin+","+qryGeo.ymax;
			
		}else{
			queryGeometry = qryGeo.rings.toString() ;
		}
	}else
		queryGeometry = "";
	

	var sensorid=[];
	jQuery("input:checkbox[name='sensorid']:checked").each(function() {
		 sensorid.push($(this).val());
     });
	if(sensorid.length>0){
		var sateSensors = sensorid[0] ;
	}
	for(var i = 1; i < sensorid.length; i++){
		sateSensors = sateSensors + "," + sensorid[i];
	}
	var producttypes= $('#cc').val();
	
	
	var shpName = $("#shpid").val(); //shp文件名
	var html='<table class="cxtable"  border="0" style="width:500px;">';
	  html=html+'<thead><tr class="cxtr" ><td bgcolor="#1d53a1" align="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="orderAllCheck2D(this,\'productid\');"></td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('map')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('thumbnail')+'</td> <td bgcolor="#1d53a1" align="center">'+$.i18n.prop('satellite')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('sensorid')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlongitude')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlatitude')+'</td></tr>';
	  html=html+'</thead><tbody>';
	$.ajax({
				type : 'POST',
				url : ctx + '/pssTasMetadataSearch.do',
				dataType : 'json',
				data : {
					producttype:producttypes, //专题产品类型
					geometryQuery:queryGeometry,//矩形，多边形，圆形空间范围
					sensorid:sateSensors,//卫星-传感器
					country:worldName, //国家
			    	states:statesName, //州
			    	shpName:shpName//shape名字
				},
				success : function(data) {
					a+=1;
					//如果查不到数据，给一个标识，查询结果的栏就不显示
					if(0==data.productList.length){
					document.getElementById("selectdata").value=1;
					}else{
					document.getElementById("selectdata").value=1;
					hideMaskForImportTif();
			    	jQuery("#map").show();
					}
					distincts = [];
					for (var i = 0; i < data.productList.length; i++) {
						var obj = data.productList[i];
						var metadataid = obj.satelliteid + "_" + obj.sensorid
								+ "_" + obj.productid;
						var lonlat = obj.topleftlongitude + ","
								+ obj.topleftlatitude + ","
								+ obj.toprightlongitude + ","
								+ obj.toprightlatitude + ","
								+ obj.bottomrightlongitude + ","
								+ obj.bottomrightlatitude + ","
								+ obj.bottomleftlongitude + ","
								+ obj.bottomleftlatitude;

								html=html+'<tr onmouseout="heigthLightCrush()"  onmouseover = "heigthLight('+lonlat+')" >';
								
			    		html+= '<td ><span id="td'+ obj.productid +'"><input name="productid" align="center" type="checkbox" value="' + metadataid + '" onclick="selectProduct(this);"/></span></td>'
			    		var imgurl = obj.thumbname.replace('\\','/');
			    		html+= '<td align="center"><span id="td'+ obj.productid +'"></span><a href="javascript:addImageToMap(\'' + obj.productid + '\',\''+imgurl+'\');"><span class="eye" id="span'+ obj.productid +'"></span></a></td>';
//			    		html=html+' <td class="botValue"><img src="/pssweb/'+obj.thumbname+'" width="25px;"/></td>';
			    		html=html+' <td bgcolor="#f3f3f3" align="center"><a href="'+ctx+'/showPssTasMetadata.do?productid='+obj.productid+'" target="_blank"><img src='+imgurl+' align="center" width="25" height="25"/></a></td>';
			    		html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.satelliteid+'</td>';
				    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.sensorid+'</td>';
				    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.centerlong+'</td>';
				    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.centerlat+'</td>';
				    	
				    	html=html+'</tr>';
					}
			    	html=html+'</tbody></table>';
			    	
					// 切换到结果列表tab
					backDataDeal(data,flag); //结果数据地图显示
					var but = "<div class='cxtb'><div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('ordernow')+"' onclick='addProduct();'/></div>"
							+ "<div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('orders')+"' onclick='addtopicProduct();'/></div>"
							+ "<div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('exportout')+"' onclick='exportoutexcel();'/></div></div>";
					$("#im_main01").html(but+ html);

				},
				error : function() {
					alert("未查询到信息！");
					if(a>=1){
						 location.reload();
					}else{
						hideMaskForImportTif();
						jQuery("#map").show();
						tableHead();
					}
				}
			});
	
	
}


/**
 * 下载函数
 * @param flag xls下载excel文件，shp下载shape文件
 * @returns {Boolean}
 * @author Dylan
 * @date 16-09-01 15:56:30
 */
function exportoutexcel(flag){
	var sonOrderIds = ""; 
	$('input[name="productid"]:checked').each(function(){ 
		sonOrderIds = sonOrderIds + $(this).val() + ","; 
	}); 
	var productidsss = sonOrderIds.split("_")[0];
	if(productidsss == ""){
		var index=layer.alert($.i18n.prop('chooseonedata'),{title:$.i18n.prop('title')}, function(){
			layer.closeAll();
		});
		return false;
	}
	window.location.href=ctx + "/exporXlsproduct.do?flag=1&sonOrderIds="+productidsss;
//	if(productidsss.length>0){
//		sonOrderIds = sonOrderIds.substring(0, sonOrderIds.length-1);//截取字符串
//	}
//	if("xls"==flag){//xls下载excel文件
//	}else if("shp" == flag){//shp下载shape文件
//		window.location.href=ctx + "/exportShp.do?sonOrderIds="+sonOrderIds;
//	}
}
function addProduct(){
	
    var id="";
    jQuery("input:checkbox[name='productid']:checked").each(function() { // 遍历name=productid的多选框
	           id=id+$(this).val()+",";
	           
		});
    if(id.length<=0 || id=='undefined'){
    	alert($.i18n.prop('chooseproduct'));
    	return;
    }
    
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/addtopicProduct.do',
	    data : {
	    	productid : id
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

function addtopicProduct(){
	window.location.href=ctx+"/topicAdd.do";
}

function tableHead(){
	var html='<table class="cxtable"  border="0" style="width:500px;">';
	  html=html+'<thead><tr class="cxtr" ><td bgcolor="#1d53a1" align="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="orderAllCheck2D(this,\'productid\');"></td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('map')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('thumbnail')+'</td> <td bgcolor="#1d53a1" align="center">'+$.i18n.prop('satellite')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('sensorid')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlongitude')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlatitude')+'</td></tr>';
	  html=html+'</thead><tbody></tbody></table>';

	var but = "<div class='cxtb'><div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('ordernow')+"' onclick='addProduct();'/></div>"
					+ "<div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('orders')+"' onclick='addtopicProduct();'/></div>"
					+ "<div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('exportout')+"' onclick='exportoutexcel();'/></div></div>";
					$("#im_main01").html(but+ html);
}