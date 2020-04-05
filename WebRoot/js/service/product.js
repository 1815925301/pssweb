$(document).ready(function() {
	changeLang_js();
	         
});

/**
 * 产品管理
 */
var newIndex,showIndex,editIndex;
/*$(document).ready(function() {
	alert('${test}');
	if('${test}'==1){
		var gpsdivs= document.getElementById("productquery");//获取要隐藏的div的ID
	    gpsdivs.style.display="none";//给div的属性改变,显示
	    var gpsdivs1= document.getElementById("productList");//获取要隐藏的div的ID
	    gpsdivs1.style.display="";//给div的属性改变,显示
	}else{
		var gpsdivs= document.getElementById("productList");//获取要隐藏的div的ID
	    gpsdivs.style.display="none";//给div的属性改变,显示
	}
	
});*/
function productquery() {
	var gpsdivs= document.getElementById("productquery");//获取要隐藏的div的ID
    gpsdivs.style.display="";//给div的属性改变,显示
    var divID= document.getElementById("productList");//获取要隐藏的div的ID
   divID.style.display="none";//给div的属性改变,隐藏
	};
function productList() {
	var gpsdivs= document.getElementById("productList");//获取要隐藏的div的ID
    gpsdivs.style.display="";//给div的属性改变,显示
    var divID= document.getElementById("productquery");//获取要隐藏的div的ID
     divID.style.display="none";//给div的属性改变,隐藏
	};
	
function productidTab(){
	   var productdivs = document.getElementById("productidTab");
	   productdivs.style.display="";
	   var productdivs1= document.getElementById("sceneidTab");//获取要隐藏的div的ID
	   productdivs1.style.display="none";//给div的属性改变,隐藏
	   document.getElementById("sceneidStrs").value = "";
	   
 }

function sceneidTab(){
	var scenedivs = document.getElementById("sceneidTab");//获取要隐藏的div的ID
	scenedivs.style.display="";//给div的属性改变,显示
    var scenedivs1= document.getElementById("productidTab");//获取要隐藏的div的ID
    scenedivs1.style.display="none";//给div的属性改变,隐藏
    document.getElementById("productidStrs").value = "";
 }	

function lonLatQuery(){
	   var lonLatdivs = document.getElementById("tbc_01_1");
	   lonLatdivs.style.display="";
	   var lonLatdivs1= document.getElementById("tbc_02_1");//获取要隐藏的div的ID
	   lonLatdivs1.style.display="none";//给div的属性改变,隐藏
	}

	function adminAreaQuery(){
		var adminAreadivs = document.getElementById("tbc_02_1");//获取要隐藏的div的ID
		adminAreadivs.style.display="";//给div的属性改变,显示
		var adminAreadivs1= document.getElementById("tbc_01_1");//获取要隐藏的div的ID
		adminAreadivs1.style.display="none";//给div的属性改变,隐藏
		executeCountry(); //查询国家
	}	

function showProduct(id){
	
    window.location.href=ctx+"/showProduct.do?productid="+id;
}

var distincts;

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

//产品查询
function productSearch(){
	
	var sensorid = true;
	  var PAN11 = document.getElementById("PAN-1");
	  var PAN22 = document.getElementById('PAN-2');
	  var WMC11 = document.getElementById('WMC-1');
	  var MSS11 = document.getElementById('MSS-1');
	  var MSS22 = document.getElementById('MSS-2');
	  var WMC22 = document.getElementById('WMC-2');
	  //传感器
	  var PAN = document.getElementById('PAN');
	  var MSS = document.getElementById('MSS');
	  var IRC = document.getElementById('IRC');
	  //传感器
	  var PAN1 = document.getElementById('PAN1');
	  var MSS1 = document.getElementById('MSS1');
	  var WMC = document.getElementById('WMC');
	  //传感器
	  var PAN2 = document.getElementById('PAN2');
	  var MSS2 = document.getElementById('MSS2');
	  if( PAN.checked || MSS.checked || IRC.checked || PAN1.checked || MSS1.checked || WMC.checked ||PAN2.checked || MSS2.checked || PAN11.checked || PAN22.checked || WMC11.checked || MSS11.checked || MSS22.checked || WMC22.checked ){
		  sensorid = true;
	  }else{
		  sensorid = false;
	  }
	  showMaskForImportTif();
	  jQuery("#map").hide();
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
	
	var productids = $("#productidStrs").val(); //产品号
	var sceneids = $("#sceneidStrs").val(); //景号
	var orbitids = $("#orbitid").val(); //轨道号
	var minpath = $("#minPath").val(); 
	var maxpath = $("#maxPath").val();
	var minrow = $("#minRow").val();
	var maxrow = $("#maxRow").val();
	var shpName = $("#shpid").val(); //shp文件名
	var sceneStarttime=$("#sceneStarttime").val(); //采集开始时间
	 var sceneEndtime=$("#sceneEndtime").val();  //采集结束时间
	 var mincloudCoverage = $("#mincloudCoverage").val(); //最小云盖
	 var maxcloudCoverage = $("#maxcloudCoverage").val(); //最大云盖
	 var productlevel = $("#productlevel").val(); //产品级别
	 if((productlevel == "LEVEL1") || (productlevel == "LEVEL0")){  //1级、0级无分辨率
		 var mingsd = "";
		 var maxgsd = "";
	 }
	 else {
		 var mingsd=$("#mingsd").val(); //最小分辨率
		 var maxgsd=$("#maxgsd").val(); //最大分辨率
	 }
	 
	 var productStarttime = $("#productStarttime").val(); //生产开始时间
	 var productEndtime = $("#productEndtime").val();  //生产结束时间
	 var quality = $("#quality").val(); //数据质量
	 var scenepath = $("#scenepath").val(); //子path
	 var scenerow = $("#scenerow").val(); //子row
	 
	if(sensorid){
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
		 var a = 0;
		$.ajax({
			type : 'POST',
		    url : ctx + '/productSearch.do',
		    dataType : 'json',
		    data : {
		    	productlevel:productlevel,//产品级别
		    	productids: productids, //产品号
		    	sceneids: sceneids, //景号
		    	orbitids:orbitids, //轨道号
		    	geometryQuery:queryGeometry,//矩形，多边形，圆形空间范围
		    	sensorid:sateSensors,//卫星-传感器
		    	minPath:minpath,//最小景path
		    	maxPath:maxpath,//最大景path
		    	minRow:minrow,//最小景row
		    	maxRow:maxrow,//最大景row
		    	country:worldName, //国家
		    	states:statesName, //州
		    	shpName:shpName,//shape名字
		    	sceneStarttime:sceneStarttime,//采集开始时间
		    	sceneEndtime:sceneEndtime,//采集结束时间
		    	mincloudCoverage:mincloudCoverage,//最小云覆盖
		    	maxcloudCoverage:maxcloudCoverage,//最大云覆盖
		    	mingsd:mingsd,//最小分辨率
		    	maxgsd:maxgsd,//最大分辨率
		    	productStarttime:productStarttime,//生产开始时间
		    	productEndtime:productEndtime,//生产结束时间
		    	quality:quality,//质量
		    	scenepath:scenepath,//子path
		    	scenerow:scenerow//子row
		    },
		    success : function(data) {
		    	
		    	hideMaskForImportTif();
		    	jQuery("#map").show();
		    	if ($("#open_im01").is(":visible")) {
			        $('#light').css("right", "0px");
			        $("#close_im01").show();
			        $("#open_im01").hide();
			    }
			    else {
			        $('#light').css("right", "-550px");
			        $("#close_im01").hide();
			        $("#open_im01").show();
			    }
		    	dataQueryHandler(data)
		    },
		    error:function(){
		    }
		});
	  }else{
		  alert($.i18n.prop('chooseweixingandchuanganqi'));
		  if(a>=1){
			  location.reload();
		  }else{
			  hideMaskForImportTif();
			  jQuery("#map").show();
			  tableHead();
		  }
	  }
}
//结果集列表显示
function dataQueryHandler(data){
	var html='<table class="cxtable" width="500px;" border="0">';
	  html=html+'<thead><tr class="cxtr"><td bgcolor="#1d53a1" align="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="orderAllCheck2D(this,\'productid\');"></td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('map')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('thumbnail')+'</td> <td bgcolor="#1d53a1" align="center">'+$.i18n.prop('satellite')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('sensorid')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('productlevel')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlongitude')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlatitude')+'</td></tr>';
	  html=html+'</thead><tbody>';
	distincts = [];
	
	for ( var i = 0; i < data.productList.length; i++) {
		
		var obj = data.productList[i];
		distincts.push(obj);
		$("#shop_productlevel").val(obj.productlevel);
		var metadataid = obj.metadataid;
		var productlevel=obj.productlevel;
		var lonlat = obj.topleftlongitude +","+obj.topleftlatitude+","
					+ obj.toprightlongitude +","+obj.toprightlatitude+","
					+ obj.bottomrightlongitude +","+obj.bottomrightlatitude+","
					+ obj.bottomleftlongitude +","+obj.bottomleftlatitude;
					
					html=html+'<tr onmouseout="heigthLightCrush()"  onmouseover = "heigthLight('+lonlat+')" >';
		html+= '<td ><span id="td'+ obj.productid +'"><input name="productid" align="center" type="checkbox" value="' + metadataid +"@"+productlevel+  '" onclick="selectProduct(this);"/></span></td>'
		var imgurl = obj.thumbname.replace('\\','/');
		html+= '<td align="center"><span id="td'+ obj.productid +'"></span><a href="javascript:addImageToMap(\'' + obj.productid + '\',\''+imgurl+'\');"><span class="eye" id="span'+ obj.productid +'"></span></a></td>';
//		html=html+' <td class="botValue"><img src="/pssweb/'+obj.thumbname+'" width="25px;"/></td>';
		html=html+' <td bgcolor="#f3f3f3" align="center"><a href="'+ctx+'/showProduct.do?productid='+obj.productid+'&productlevel='+productlevel+'" target="_blank"><img src='+imgurl+' align="center" width="25" height="25"/></a></td>';
		html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.satelliteid+'</td>';
    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.sensorid+'</td>';
    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.productlevel+'</td>';
    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.centerlong+'</td>';
    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.centerlat+'</td>';
    	
    	html=html+'</tr>';
	}
	html=html+'</tbody></table>';
	// 切换到结果列表tab
	backDataDeal(data,flag); //结果数据地图显示onclick="addProduct();
	
	var but="<div class='cxtb'><div class='cxdg'><input class='btn blue' type='button' value="+$.i18n.prop('ordernow')+" onclick='addProduct();'/></div><div class='cxdg'><input class='btn blue' type='button' value="+$.i18n.prop('duibi')+" onclick='contproduct();'/></div><ul id='sddm'><li class='bg'> <a href='#'  onmouseover=\"mopen('m4')\"; onmouseout='mclosetime();'>"+$.i18n.prop('more')+"</a>"
	+"<div id='m4' onmouseover='mcancelclosetime();' onmouseout='mclosetime();'>"
	+"<a href='#'  onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('pagemaps')+"</a>"
	+"<a href='#' onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('canclemaps')+"</a>"
	+"<a href='#' onclick='queryLevel0();'>"+$.i18n.prop('level0data')+"</a>"	
	+"</div>"
	+"</li>"
	+"</ul></div>";
	$("#im_main01").html(but+html);
}

function tableHead(){
	var html='<table class="cxtable" width="500px;" border="0">';
	  html=html+'<thead><tr class="cxtr"><td bgcolor="#1d53a1" align="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="orderAllCheck2D(this,\'productid\');"></td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('map')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('thumbnail')+'</td> <td bgcolor="#1d53a1" align="center">'+$.i18n.prop('satellite')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('sensorid')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('productlevel')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlongitude')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlatitude')+'</td></tr>';
	  html=html+'</thead><tbody></tbody></table>';

	var but="<div class='cxtb'><div class='cxdg'><input class='btn blue' type='button' value="+$.i18n.prop('ordernow')+" onclick='addProduct();'/></div><div class='cxdg'><input class='btn blue' type='button' value='对比' onclick='contproduct();'/></div><ul id='sddm'><li class='bg'> <a href='#'  onmouseover=\"mopen('m4')\"; onmouseout='mclosetime();'>"+$.i18n.prop('more')+"</a>"
	+"<div id='m4' onmouseover='mcancelclosetime();' onmouseout='mclosetime();'>"
	+"<a href='#'  onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('pagemaps')+"</a>"
	+"<a href='#' onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('canclemaps')+"</a>"
	+"<a href='#' onclick='queryLevel0();'>"+$.i18n.prop('level0data')+"</a>"	
	+"</div>"
	+"</li>"
	+"</ul></div>";
	$("#im_main01").html(but+html);
}

//查询0级景
function queryLevel0(){
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
	
	var productids = $("#productidStrs").val(); //产品号
	var sceneids = $("#sceneidStrs").val(); //景号
	var orbitids = $("#orbitid").val(); //轨道号
	var minpath = $("#minPath").val(); 
	var maxpath = $("#maxPath").val();
	var minrow = $("#minRow").val();
	var maxrow = $("#maxRow").val();
	var shpName = $("#shpid").val();//shp文件名
	
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
	 
	 var sceneStarttime=$("#sceneStarttime").val(); //采集开始时间
	 var sceneEndtime=$("#sceneEndtime").val();  //采集结束时间
	 var mincloudCoverage = $("#mincloudCoverage").val(); //最小云盖
	 var maxcloudCoverage = $("#maxcloudCoverage").val(); //最大云盖
	 var productlevel = "LEVEL0_SCENE"; //产品级别
	 var mingsd=$("#mingsd").val(); //最小分辨率 (0级景无分辨率)
	 var maxgsd=$("#maxgsd").val(); //最大分辨率  (0级景无分辨率)
	 var productStarttime = $("#productStarttime").val(); //生产开始时间  (0级景无生产时间)
	 var productEndtime = $("#productEndtime").val();  //生产结束时间 (0级景无生产时间)
	 var quality = $("#quality").val(); //数据质量  (0级景无数据质量)
	 var scenepath = $("#scenepath").val(); //子path
	 var scenerow = $("#scenerow").val(); //子row
	 var html='<table class="cxtable" width="500px" border="0">';
			  html=html+'<thead><tr class="cxtr"><td bgcolor="#1d53a1" align="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="orderAllCheck2D(this,\'productid\');"></td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('map')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('thumbnail')+'</td> <td bgcolor="#1d53a1" align="center">'+$.i18n.prop('satellite')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('sensorid')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('productlevel')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlongitude')+'</td><td bgcolor="#1d53a1" align="center">'+$.i18n.prop('centerlatitude')+'</td></tr>';
			  html=html+'</thead><tbody>';
	$.ajax({
		type : 'POST',
	    url : ctx + '/productSearch.do',
	    dataType : 'json',
	    data : {
	    	productlevel:productlevel,//产品级别
	    	productids: productids, //产品号
	    	sceneids: sceneids, //景号
	    	orbitids:orbitids, //轨道号
	    	geometryQuery:queryGeometry,//矩形，多边形，圆形空间范围
	    	sensorid:sateSensors,//卫星-传感器
	    	minPath:minpath,//最小景path
	    	maxPath:maxpath,//最大景path
	    	minRow:minrow,//最小景row
	    	maxRow:maxrow,//最大景row
	    	country:worldName, //国家
	    	states:statesName, //州
	    	shpName:shpName,//shape名字
	    	sceneStarttime:sceneStarttime,//采集开始时间
	    	sceneEndtime:sceneEndtime,//采集结束时间
	    	mincloudCoverage:mincloudCoverage,//最小云覆盖
	    	maxcloudCoverage:maxcloudCoverage,//最大云覆盖
	    	scenepath:scenepath,//子path
	    	scenerow:scenerow//子row
	    },
	    success : function(data) {
	    	if(data.productList.length>0){
	    	distincts = [];
	    	$("#im_main01").html("");
	    	for ( var i = 0; i < data.productList.length; i++) {
	    		var obj = data.productList[i];
	    		distincts.push(obj);
	    		$("#shop_productlevel").val("LEVEL0_SCENE");
	    		var metadataid = obj.metadataid;
	    		var lonlat = obj.topleftlongitude +","+obj.topleftlatitude+","
							+ obj.toprightlongitude +","+obj.toprightlatitude+","
							+ obj.bottomrightlongitude +","+obj.bottomrightlatitude+","
							+ obj.bottomleftlongitude +","+obj.bottomleftlatitude
					
							html=html+'<tr onmouseout="heigthLightCrush()"  onmouseover = "heigthLight('+lonlat+')" >';
	    		html+= '<td ><span id="td'+ obj.productid +'"><input name="productid" align="center" type="checkbox" value="' + metadataid + '" onclick="selectProduct(this);"/></span></td>'
	    		var imgurl = obj.thumbname.replace('\\','/');
	    		html+= '<td align="center"><span id="td'+ obj.sceneid +'"></span><a href="javascript:addImageToMap(\'' + obj.sceneid + '\',\''+imgurl+'\');"><span class="eye" align="center" id="span'+ obj.sceneid +'"></span></a></td>';
//	    		html=html+' <td class="botValue"><img src="/pssweb/'+obj.thumbname+'" width="25px;"/></td>';
	    		//html=html+' <td bgcolor="#f3f3f3" align="center"><a href="'+ctx+'/showPssMetadata0scene.do?productid='+obj.productid+'&productlevel='+productlevel+'" target="_blank"><img src="/pssweb/1.jpg" align="center"/></a></td>';
	    		html=html+' <td bgcolor="#f3f3f3" align="center"><a href="'+ctx+'/showPssMetadata0scene.do?sceneid='+obj.sceneid+'&productlevel='+productlevel+'" target="_blank"><img src='+imgurl+' align="center" width="25" height="25"/></a></td>';
	    		html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.satelliteid+'</td>';
		    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.sensorid+'</td>';
		    	html=html+' <td bgcolor="#f3f3f3" align="center">LEVEL0_SCENE</td>';
		    	html=html+' <td bgcolor="#f3f3f3" align="center">'+obj.centerlong+'</td>';
		    	html=html+' <td bgcolor="#f3f3f3" align="center" width="3%">'+obj.centerlat+'</td>';
		    	
		    	html=html+'</tr>';
			}
	    	html=html+'</tbody></table>';
	    	// 切换到结果列表tab
	    	
	    	backDataDeal(data); //结果数据地图显示
	    	var but="<div class='cxtb'><div class='cxdg'><input class='btn blue' type='button' value='"+$.i18n.prop('ordernow')+"' onclick='addProductScene();'/></div><ul id='sddm'><li class='bg'> <a href='#' onmouseover=\"mopen('m4')\"; onmouseout='mclosetime();'>"+$.i18n.prop('more')+"</a>"
		    	+"<div id='m4' onmouseover='mcancelclosetime();' onmouseout='mclosetime();'>"
		    	+"<a href='#'  onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('pagemaps')+"</a>"
		    	+"<a href='#' onclick='javascript:addAllImages2Map()' >"+$.i18n.prop('canclemaps')+"</a>"
		    	+"<a href='#' onclick='queryLevel0();'>"+$.i18n.prop('level0data')+"</a>"
		    	+"</div>"
		    	+"</li>"
		    	+"</ul></div>";
	    	$("#im_main01").html(but+html);
	    	}else{
		    	alert($.i18n.prop('nozerodate'));
		    	window.location.href=ctx+"/pssCollectInfomanage.do";
		    }
	    },
	    error:function(){
	    }
	    
	});
}


function addProductScene(){
	
	 	var id="";
	    jQuery("input:checkbox[name='productid']:checked").each(function() { // 遍历name=test的多选框
		           id=id+$(this).val()+",";
			});
	    if(id.length<=0 || id=='undefined'){
	    	alert($.i18n.prop('chooseproduct'));
	    	return;
	    }
		var productlevel = $("#productlevel").val(); //产品级别
		if(productlevel=="LEVEL3A" || productlevel=="LEVEL3B" || productlevel=="LEVEL4"){
			newIndex=layer.open({
				title:'是否GCP过滤',
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['200px', '200px'], //宽高
			    content: $('#showgcp').html()
			   
			});
			
		}else{
			 jQuery.ajax({
					type : 'POST',
				    url : ctx + '/addProductScene.do',
				    data : {
				    	id : id,
				    	productlevel:productlevel//产品级别
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
		
		
}
function addshopcarscens(){
	var chkobjs="";
	var id="";
    jQuery("input:checkbox[name='productid']:checked").each(function() { // 遍历name=test的多选框
	           id=id+$(this).val()+",";
		});

    var productlevel = $("#productlevel").val(); //产品级别
    var gcp=document.getElementsByName("gcp");
    for(var i=0;i<gcp.length;i++){
    	if(gcp[i].checked){
    		chkobjs=gcp[i].value;
    	}
    }
    jQuery.ajax({
		type : 'POST',
	    url : ctx + '/addProductScene.do',
	    data : {
	    	id : id,
	    	productlevel:productlevel,//产品级别
	    	gcp:chkobjs
	    },
	    success : function(data) {
	    	
	    	var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
				layer.closeAll();
	    	});
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
//对比
function contproduct(){
    var contid="";
    jQuery("input:checkbox[name='productid']:checked").each(function() { // 遍历name=test的多选框
    	
    	contid=contid+$(this).val()+",";
		});
    var checks=document.getElementsByName("productid");
    n=0;
    for(i=0;i<checks.length;i++){
    	if(checks[i].checked){
    		n++;
    	}
    }
    if(contid.length<=0 || contid=='undefined'){
    	alert($.i18n.prop('chooseproduct'));
    	return;
    }else if(n>3){
    	alert($.i18n.prop('threemore'));
    	return;
    }
    else{
    	   window.location.href=ctx+"/contProduct.do?pid="+contid;
    }
   

}
//订购
function addProduct(){
	
    var id="";
    jQuery("input:checkbox[name='productid']:checked").each(function() { // 遍历name=test的多选框
	           id=id+$(this).val()+",";
		});
    var productlevel=$("#shop_productlevel").val();
    if(id.length<=0 || id=='undefined'){
    	alert($.i18n.prop('chooseproduct'));
    	return;
    }
    
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/addProduct.do',
	    data : {
	    	productid : id
	    },
	    success : function(data) {
	    	var str=id.split(",");
	    	for ( var i = 0; i < str.length; i++) {
	    		console.log(str[i]);
	    		jQuery("#"+str[i]).attr("checked",false);  
	    		jQuery("#"+str[i]).attr("disabled",true);
			}
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
function hidenProduct(){
	layer.close(showIndex);
}

function setHMS(f){
	//度
	var h = parseInt(f);
	//分
	var m = parseInt((parseFloat(f)-h)*60);
	//秒
	var s = changeFourDecimal(((parseFloat(f)-parseInt(h))*60-m)*60);
	
	if(m.toString().length==1){
		m = '0' + m.toString();
	}
	if(s.toString().length==1){
		s = '0'+ s.toString();
	}
	var hms = h+'°'+m+'′'+s+'″';
	return hms;
}

function setLong(f){
	var du = [];
	du= f.split("°");
	var dusz = du[0]-0;
	
	var fen = [];
	fen = du[1].split("′");
	var fensz = (fen[0]-0)/60;
	
	var miao = [];
	miao =  fen[1].split("″");
	var miaosz = (miao[0]-0)/3600;
	return changeFourDecimal(dusz)+changeFourDecimal(fensz)+changeFourDecimal(miaosz);
}

//度分秒转换
function changeHMS(){
	jQuery("#dfm").replaceWith("<a href=\"#\" id=\"dfm\" onclick=\"changeLong()\" ><@spring.message \"Transfer\"/></a>");
	var ltl = jQuery("#leftuplong").val();
	var rtb = jQuery("#rightdownlat").val();
	var rbl = jQuery("#rightdownlong").val();
	var ltlat = jQuery("#leftuplat").val();
	
	var l1 = setHMS(ltl-0);
	var l2 = setHMS(rtb-0);
	var l3 = setHMS(rbl-0);
	var l4 = setHMS(ltlat-0);
	
	jQuery("#leftuplong").val(l1);
	jQuery("#rightdownlat").val(l2);
	jQuery("#rightdownlong").val(l3);
	jQuery("#leftuplat").val(l4);
}

function changeLong(){
	jQuery("#dfm").replaceWith("<a href=\"#\" id=\"dfm\" onclick=\"changeHMS()\" ><@spring.message \"Transfer\"/></a>");
	var ltl = jQuery("#leftuplong").val();
	var rtb = jQuery("#rightdownlat").val();
	var rbl = jQuery("#rightdownlong").val();
	var ltlat = jQuery("#leftuplat").val();
	
	jQuery("#leftuplong").val(setLong(ltl));
	jQuery("#rightdownlat").val(setLong(rtb));
	jQuery("#rightdownlong").val(setLong(rbl));
	jQuery("#leftuplat").val(setLong(ltlat));
}

//保存两位小数
function changeFourDecimal(x){
	var f_x = parseFloat(x);
	if(isNaN(f_x)){
		alert($.i18n.prop('numwrong'));
	return false;
	}
	var f_x = Math.round(x*10000)/10000
	return f_x;
}


//读取产品号文本
function readProductidFile(){
	//支持chrome IE10
	if (window.FileReader) {
		var file = myfile.files[0];
		var filename = file.name.split(".")[0];
		var reader = new FileReader();
		reader.onload = function() {
			console.log(this.result)
			var ss = this.result;
			$("#productidStrs").val(ss);
		}
		reader.readAsText(file);
		
	} 
	//支持IE 7 8 9 10
	else if (typeof window.ActiveXObject != 'undefined'){
		var xmlDoc; 
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM"); 
		xmlDoc.async = false; 
		xmlDoc.load(myfile.value); 
		$("#productidStrs").val(xmlDoc.xml);
	} 
	//支持FF
	else if (document.implementation && document.implementation.createDocument) { 
		var xmlDoc; 
		xmlDoc = document.implementation.createDocument("", "", null); 
		xmlDoc.async = false; 
		xmlDoc.load(myfile.value); 
		$("#productidStrs").val(xmlDoc.xml);
	} else { 
		alert('error'); 
	} 
}

//读取景号文本
function readSceneidFile(){
	//支持chrome IE10
	if (window.FileReader) {
		var file = myfiles.files[0];
		var filename = file.name.split(".")[0];
		var reader = new FileReader();
		reader.onload = function() {
			console.log(this.result)
			var ss = this.result;
			$("#sceneidStrs").val(ss);
		}
		reader.readAsText(file);
		
	} 
	//支持IE 7 8 9 10
	else if (typeof window.ActiveXObject != 'undefined'){
		var xmlDoc; 
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM"); 
		xmlDoc.async = false; 
		xmlDoc.load(myfiles.value); 
		$("#sceneidStrs").val(xmlDoc.xml);
	} 
	//支持FF
	else if (document.implementation && document.implementation.createDocument) { 
		var xmlDoc; 
		xmlDoc = document.implementation.createDocument("", "", null); 
		xmlDoc.async = false; 
		xmlDoc.load(myfiles.value); 
		$("#sceneidStrs").val(xmlDoc.xml);
	} else { 
		alert('error'); 
	} 
}

//导入shp文件
function importShapeFiles(){
	var filename=$("#shpfile").val();
	var startindex=filename.lastIndexOf("\\")+1;
	var endindex=filename.length;
	var s=filename.substring(startindex,endindex);
	var shpName = s.split(".")[0];
	$("#shpid").val(shpName);
	$.ajaxFileUpload( {
		type : "POST",
		// 处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
		url : ctx + '/importShape.do',
		secureuri : false, // 是否启用安全提交,默认为false
		fileElementId : 'shpfile', // 文件选择框的id属性
		dataType : 'json', 
		cache : false,
		success : function(data) { // 服务器响应成功时的处理函数
			var _poly = data.poly;
			var _point = data.point;
			var _line = data.line;
			if (_poly != null && _poly != undefined) {
				var regions = _poly.split("@");
				var polygon = new esri.geometry.Polygon(
					new esri.SpatialReference( {
						wkid : 4326
					}));
				for ( var i = 0; i < regions.length; i++) {
					var points = [];
					var latlons = regions[i].split(",");
					for ( var j = 0; j < latlons.length; j++) {
						var xy = jQuery.trim(latlons[j]).split(" ");
						var point = new esri.geometry.Point(xy[0], xy[1], {
							wkid : 4326
						});
						points.push(point);
					}
					polygon.addRing(points);
				}
				var sfs = new esri.symbol.SimpleFillSymbol(
					esri.symbol.SimpleFillSymbol.STYLE_SOLID,
					new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
							new dojo.Color( [ 255, 0, 0 ]), 2),
							new dojo.Color( [ 255, 255, 0, 0.25 ]));
				var ss = polygon.getExtent().getCenter();
				var centerPoint = new esri.geometry.Point(ss.x, ss.y);
				var graphic = new esri.Graphic(polygon, sfs);
				importShpLayer.add(graphic);
				map.centerAt(centerPoint);
			}
			if (_line != null && _line != undefined) {
				var regions = _line.split("@");
				var polyLine = new esri.geometry.Polyline(
					new esri.SpatialReference( {
						wkid : 4326
					}));
				for ( var i = 0; i < regions.length; i++) {
					var points = [];
					var latlons = regions[i].split(",");
					for ( var j = 0; j < latlons.length; j++) {
						var xy = jQuery.trim(latlons[j]).split(" ");
						var point = new esri.geometry.Point(xy[0], xy[1], {
							wkid : 4326
						});
						points.push(point);
					}
					polyLine.addPath(points);
				}
				var sls = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASH,
				new dojo.Color( [ 255, 0, 0 ]), 3);
				var graphic = new esri.Graphic(polyLine, sls);
				importShpLayer.add(graphic);
			}

			if (_point != null && _point != undefined) {
				var regions = _point.split(",");
				var graphic;var point;
				for ( var i = 0; i < regions.length; i++) {
					var points = [];
					var xy = jQuery.trim(regions[i]).split(" ");
					point = new esri.geometry.Point(xy[0], xy[1], {
						wkid : 4326
					});
					var sfs = new esri.symbol.SimpleMarkerSymbol(
						esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
						new esri.symbol.SimpleLineSymbol(
							esri.symbol.SimpleLineSymbol.STYLE_SOLID,
								new dojo.Color( [ 255, 0, 0 ]), 1),
								new dojo.Color( [ 0, 255, 0, 0.25 ]));
					
					graphic = new esri.Graphic(point);
					importShpLayer.add(graphic);
				}
				alert($.i18n.prop('loadingsuccess'));
			}
		},
		error : function(data, status, e) {
			alert(data+"-----"+status+"---------"+e);
		}
	});
}

var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;
// open hidden layer
function mopen(id) {
    // cancel close timer
    mcancelclosetime();
    // close old layer
    if (ddmenuitem) ddmenuitem.style.visibility = 'hidden';
    // get new layer and show it
    ddmenuitem = document.getElementById(id);
    ddmenuitem.style.visibility = 'visible';
}
// close showed layer
function mclose() {
    if (ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}
// go close timer
function mclosetime() {
    closetimer = window.setTimeout(mclose, timeout);
}
// cancel close timer
function mcancelclosetime() {
    if (closetimer) {
        window.clearTimeout(closetimer);
        closetimer = null;
    }
}
// close layer when click-out
document.onclick = mclose; 