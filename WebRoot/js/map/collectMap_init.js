$(document).ready(function() {
	changeLang_js();
	
});
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("esri.map");

dojo.require("esri.toolbars.navigation");
dojo.require("dijit.form.Button");
dojo.require("dijit.Toolbar");
dojo.require("esri.toolbars.draw");

dojo.require("esri.tasks.query");
dojo.require("esri.tasks.identify");
dojo.require("dijit.layout.TabContainer");

dojo.require("esri.geometry");
dojo.require("esri.tasks.geometry");

var map, resultsLayer, highlightLayer,importShpLayer,imageLayer;
var navToolbar;
var drawToolbar;

var onLoadHandler = null;
var onDrawHandler = null;
var onDragHandler = null, onDragStartHandler = null, onDragEndHandler = null, onQueryCompleteHandler, onGraphicAddHandler = null;
var onKeyDownHandler = null;// 鼠标按下事件
var qryGeo = null;
var selectedProducts = new Array();// 选择的产品集合

var queryTaskCountries = null; //查询国家
var queryTaskStates = null;  //查询州
var countryGraphics = new Array();// 国家范围集合
var statesGraphics = new Array();// 省范围集合

var identifyTaskVRSS_1 = null;
var identifyTaskVRSS_2 = null;
var identifyTaskGF_1 = null;
var identifyTaskGF_2 = null;

var countrydistincts = new Array();// 存储国家
var statesdistincts = new Array();// 存储州省

var symbolResult = new esri.symbol.SimpleFillSymbol(
		esri.symbol.SimpleFillSymbol.STYLE_SOLID,
		new esri.symbol.SimpleLineSymbol(
				esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([
						0, 0, 0 ]),1), new dojo.Color([ 255, 255, 255,
				0.25 ]));
var geometryService = null;

var MapOprEnum = {
	// 矩形查询
	RectangleQuery : 1,
	PolygonGraphic : 2,
	CircleGraphic : 3,
	PointGraphic: 4,
	// 未知
	Unknown : -1
};
var currentMapOpr = MapOprEnum.Unknown;

var drawRectangle = {
	oldGraphic : null,
	startPoint : null,
	endPoint : null
};
function init() {
	// 设置jQuery ajax的同步
	jQuery.ajaxSetup({
		async : false
	});
	map = new esri.Map("map", {
		logo : false
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map/MapServer", {
		id : "worldLayer" //底图
	});
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	imageLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/world_image/MapServer", {
		id : "imageLayer", //底图
		visible:false
	});
	
	map.addLayer(worldlayer);
	map.addLayer(imageLayer);
	map.addLayer(highlightLayer);
	drawToolbar = new esri.toolbars.Draw(map);
	onDragHandler = dojo.connect(map, "onMouseDrag", onMouseDragHandler);
	onDragStartHandler = dojo.connect(map, "onMouseDragStart",
			onMouseDragStartHandler);
	onDragEndHandler = dojo.connect(map, "onMouseDragEnd",
			onMouseDragEndHandler);
	navToolbar = new esri.toolbars.Navigation(map);
	dojo.connect(drawToolbar, "onDrawEnd", onDrawEndHandler);
	
	onLoadHandler = dojo.connect(map, 'onLoad', function(map) {
		map.infoWindow.resize(250, 100);
		navToolbar = new esri.toolbars.Navigation(map);
		dojo.connect(dijit.byId('map'), 'resize', map, map.resize);
	});
	
	queryTaskCountries = new esri.tasks.QueryTask(mapurl
			+ "/arcgis/rest/services/vrss_map/MapServer/0");
	queryTaskStates = new esri.tasks.QueryTask(mapurl
			+ "/arcgis/rest/services/vrss_map/MapServer/1");
	
	identifyTaskVRSS_1 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_VRSS_1/MapServer");
	identifyTaskVRSS_2 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_VRSS_2/MapServer");
	identifyTaskGF_1 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_GF_1/MapServer");
	identifyTaskGF_2 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_GF_2/MapServer");
	
	geometryService = new esri.tasks.GeometryService(mapurl+"/arcgis/rest/services/Utilities/Geometry/GeometryServer");
	
	esri.config.defaults.io.alwaysUseProxy = false;
	
	dojo.connect(identifyTaskVRSS_1, "onComplete",
			onidentifyTaskCompleteHandler);
	dojo.connect(identifyTaskVRSS_2, "onComplete",
			onidentifyTaskCompleteHandler);
	dojo.connect(identifyTaskGF_1, "onComplete",
			onidentifyTaskCompleteHandler);
	dojo.connect(identifyTaskGF_2, "onComplete",
			onidentifyTaskCompleteHandler);
	dojo.connect(queryTaskCountries, "onComplete",
			onqueryTaskCountriesCompleteHandler);
	dojo.connect(queryTaskStates, "onComplete",
			onqueryTaskStatesCompleteHandler);
	dojo.connect(map, "onZoomEnd", expandPicture); //地图缩放随之改变拇指图大小
	
	dojo.connect(geometryService,"onAreasAndLengthsComplete",outputAreaAndLength);
	
}

function topitMapInit() {
	// 初始化地图
	init();
	// 加载国家列表
	executeCountry();
}
var showPt;
function onDrawEndHandler(geometry) {
	var sfs = null;
	if (geometry.type == "point") {
		map.graphics.clear();
//		addPointXY(geometry);
		var identifyParams = new esri.tasks.IdentifyParameters();
        identifyParams.tolerance = 3;
        identifyParams.returnGeometry = true;
        identifyParams.mapExtent = map.extent; 
		identifyParams.geometry = geometry; 
        identifyParams.layerIds = [0];
        identifyParams.width = map.width;
        identifyParams.height = map.height;
        switch (sateName) {
        case "VRSS_1":
        	identifyTaskVRSS_1.execute(identifyParams);
            break;
        case "VRSS_2":
        	identifyTaskVRSS_2.execute(identifyParams);
            break;
        case "GF_1":
        	identifyTaskGF_1.execute(identifyParams);
            break;
        case "GF_2":
        	identifyTaskGF_2.execute(identifyParams);
            break;
       }
	} else if (geometry.type == "polygon") {
		qryGeo = geometry;
		sfs = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
						new dojo.Color([ 255, 0, 0 ]), 2), new dojo.Color([
						255, 255, 0, 0.25 ]));
		map.graphics.clear();
		map.enablePan();
		currentMapOpr = MapOprEnum.Unknown;
		var graphic = new esri.Graphic(geometry, sfs);
		map.graphics.add(graphic);
		showPt = new esri.geometry.Point(geometry.rings[0][0],map.spatialReference);
		areasAndLengthsHandler(geometry);
	}
}

function areasAndLengthsHandler(geometry){
	// setup the parameters for the areas and lengths operation
	var areasAndLengthParams = new esri.tasks.AreasAndLengthsParameters();
	areasAndLengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_FOOT;
	areasAndLengthParams.areaUnit = esri.tasks.GeometryService.UNIT_SQUARE_METERS;
	this.outSR = new esri.SpatialReference({ wkid: 102113 });
	geometryService.project([geometry], this.outSR, function (geometry) {
            geometryService.simplify(geometry, function (simplifiedGeometries) {
                areasAndLengthParams.polygons = simplifiedGeometries;
                areasAndLengthParams.spatialReference = new esri.SpatialReference(102113);
                geometryService.areasAndLengths(areasAndLengthParams);
            });
        });
}
var a="";
function outputAreaAndLength(result) {
	a = result.areas[0].toFixed(3) / 1000 / 1000;
	if(a>areaMax){
		alert(" 测 量 面 积 为：" + a.toFixed(2)+ "平方千米,所选面积太大，请缩小范围！");
	}
	
//	map.infoWindow.setTitle("面积测量");
//	map.infoWindow.setContent(" 测 量 面 积 ： <strong>" + area.toFixed(2)
//			+ "平方千米</strong>");
//	map.infoWindow.show(showPt);
}

function saveCollectInfo(){
	if(a==""){
		alert("区域信息必填！");
		return;
	}
			var area=a.toFixed(2);
			
        	var upperleftlong=document.getElementById("leftuplong").value;
        	var upperleftlat=document.getElementById("leftuplat").value;
        	var lowerrightlong=document.getElementById("rightdownlong").value;
        	var lowerrightlat=document.getElementById("rightdownlat").value;
        	var productlevel=document.getElementById("productlevel").value;
        	var satelliteidOBJ=document.getElementsByName('satelliteid'); 
        	var stationid=document.getElementById("stationid").value;
        	var satelliteid='';
        	for(var i=0; i<satelliteidOBJ.length; i++){ 
				if(satelliteidOBJ[i].checked)
					satelliteid+=satelliteidOBJ[i].value+' ';
			}
        	var sensoridOBJ=document.getElementsByName('sensorid'); 
        	var sensorid='';
        	for(var i=0; i<sensoridOBJ.length; i++){ 
					if(sensoridOBJ[i].checked)
					sensorid+=sensoridOBJ[i].value+' ';
				} 
       // 	var createtime=document.getElementById("createtime").value;
        	var begintime=document.getElementById("begintime").value;
        	var endtime=document.getElementById("endtime").value;
        	var cloudcover=document.getElementById("cloudcover").value;
        	var sideangle=document.getElementById("sideangle").value;
        	var earthsurfacename=document.getElementById("earthsurfacename").value;
        	/*判断必填项*/
        	
        	if(upperleftlong==null||upperleftlong==""
        		||upperleftlat==null||upperleftlat==""
    			||lowerrightlong==null||lowerrightlong==""
				||lowerrightlat==null||lowerrightlat==""){
        		
        		alert(
        				$.i18n.prop('jingweidu'));
        		return false;
        	}
        	if(satelliteid==null||satelliteid==""){
        		alert(
        				$.i18n.prop('satiectmust'));
        		return false;
        	}
        	if(sensorid==""){
        		alert("相机不能为空！");
        		return false;
        	}
        	if(begintime==null||begintime==""){
        		alert(
        				$.i18n.prop('shishiearly'));
        		return false;
        	}
        	if(endtime==null||endtime==""){
        		alert(
        				$.i18n.prop('shishioldtime'));
        		return false;
        	}
        	
        	if(cloudcover==null||cloudcover==""){		//平均云量
        		alert("平均云量不能为空");
        		return false;
        	}
        	if(sideangle==null||sideangle==""){			//侧摆角
        		alert("测摆角不能为空");
        		return false;
        	}
        	if(earthsurfacename==-1){
        		alert($.i18n.prop('earthshuxing'));
        		return false;
        	}
        	
        	
        	/*end*/
        	
        	var data={upperleftlong:upperleftlong,
        			upperleftlat:upperleftlat,
        			lowerrightlong:lowerrightlong,
        			lowerrightlat:lowerrightlat,
        			sensorid:sensorid,
        			productlevel:productlevel,
        			satelliteid:satelliteid,
//        			createtime:createtime,
        			begintime:begintime,
        			endtime:endtime,
        			cloudcover:cloudcover,
        			sideangle:sideangle,
        			earthsurfacename:earthsurfacename,
        			area:area,
        			stationid:stationid,
        			};
        	 
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveCollectInfo.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
			    				layer.closeAll();
			    				
			    				
			    			});
			    			window.location.href="pssCollectInfomanage.do";
						} else {
							$.each(data.data, function(i, item) {   
			              		seterror(item.key, item.value);
			            	});
						}
			    	}
			 	},
			    error : function(data) {}
        	});
        
	};
function onidentifyTaskCompleteHandler(result){
	if(result.length>1){
		alert("你选中了多个图幅，请先放大地图在做点选操作.");	
	}else{
		var feature = result[0].feature;
		qryGeo = feature.geometry;
		var qryGeoxmin = qryGeo.getExtent().xmin;
		var qryGeoymax = qryGeo.getExtent().ymax;
		var qryGeoxmax = qryGeo.getExtent().xmax;
		var qryGeoymin = qryGeo.getExtent().ymin;
		var xminstr = formatLongLat(qryGeoxmin);
		var ymaxstr = formatLongLat(qryGeoymax);
		var xmaxstr = formatLongLat(qryGeoxmax);
		var yminstr = formatLongLat(qryGeoymin);
		if(dojo.byId("leftuplong")!=null){
			dojo.byId("leftuplong").value = xminstr;
			dojo.byId("leftuplat").value = ymaxstr;
			dojo.byId("rightdownlong").value = xmaxstr;
			dojo.byId("rightdownlat").value = yminstr;
		}
		var sfs = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
						new dojo.Color([ 255, 0, 0 ]), 2), new dojo.Color([
						255, 255, 0, 0.25 ]));
		var graphic = new esri.Graphic(qryGeo, sfs);
		map.graphics.add(graphic);
		map.enablePan();
		var productlevel = $("#productlevel").val(); //产品级别
		$.ajax({
			type : 'POST',
		    url : ctx + '/productSearch.do',
		    dataType : 'json',
		    data : {
		    	productlevel:productlevel,//产品级别
		    	geometryQuery:qryGeo.rings.toString()
		    },
		    success : function(data) {
		    	if ($("#open_im01").is(":visible")) {
			        $('#light').css("right", "0px");
			        $("#close_im01").show();
			        $("#open_im01").hide();
			    }
			    else {
			        $('#light').css("right", "-500px");
			        $("#close_im01").hide();
			        $("#open_im01").show();
			    }
		    	dataQueryHandler(data)
		    },
		    error:function(){
		    }
		});
	}
		
}

function onMouseDragStartHandler(evt) {
	if (currentMapOpr == MapOprEnum.RectangleQuery) {
		map.disablePan();
		drawRectangle.startPoint = evt.mapPoint;
		dragSfs = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([ 200, 0, 0 ]), 2), new dojo.Color([
						255, 255, 0, 0.25 ]));
		map.graphics.clear();
	}
}

function onMouseDragHandler(evt) {
	map.reposition();
	if (map.isPan) {
		map.disablePan();
	}
	if (currentMapOpr == MapOprEnum.RectangleQuery) {
		if (drawRectangle == null || drawRectangle.startPoint == null) {
			drawRectangle.startPoint = evt.mapPoint;
			return;
		}
		drawRectangle.endPoint = evt.mapPoint;
		dragMinX = drawRectangle.endPoint.x < drawRectangle.startPoint.x ? drawRectangle.endPoint.x
				: drawRectangle.startPoint.x;
		dragMinY = drawRectangle.endPoint.y < drawRectangle.startPoint.y ? drawRectangle.endPoint.y
				: drawRectangle.startPoint.y;
		dragMaxX = drawRectangle.endPoint.x > drawRectangle.startPoint.x ? drawRectangle.endPoint.x
				: drawRectangle.startPoint.x;
		dragMaxY = drawRectangle.endPoint.y > drawRectangle.startPoint.y ? drawRectangle.endPoint.y
				: drawRectangle.startPoint.y;
		dragExtent = new esri.geometry.Extent(dragMinX, dragMinY, dragMaxX,
				dragMaxY, map.spatialReference);

		if (dragSfs == null) {
			dragSfs = new esri.symbol.SimpleFillSymbol(
					esri.symbol.SimpleFillSymbol.STYLE_SOLID,
					new esri.symbol.SimpleLineSymbol(
							esri.symbol.SimpleLineSymbol.STYLE_SOLID,
							new dojo.Color([ 255, 0, 0 ]), 5), new dojo.Color([
							255, 255, 0, 0.25 ]));
		}
		if (drawRectangle.oldGraphic != null) {
			map.graphics.remove(drawRectangle.oldGraphic);
		}
		var graphic = new esri.Graphic(dragExtent, dragSfs);
		map.graphics.add(graphic);
		drawRectangle.oldGraphic = graphic;
	}
}

// 框选结束方法
function onMouseDragEndHandler(evt) {
	var mp = evt.mapPoint;
	if (map.isPan) {
		map.disablePan();
	}
	if (currentMapOpr == MapOprEnum.RectangleQuery) {
		// task查询
		if (drawRectangle.oldGraphic == null) {
			return null;
		}
		qryGeo = drawRectangle.oldGraphic.geometry;
		if (qryGeo == null) {
			return;
		}

		var qryGeoxmin = qryGeo.xmin;
		var qryGeoymax = qryGeo.ymax;
		var qryGeoxmax = qryGeo.xmax;
		var qryGeoymin = qryGeo.ymin;
		var xminstr = formatLongLat(qryGeoxmin);
		var ymaxstr = formatLongLat(qryGeoymax);
		var xmaxstr = formatLongLat(qryGeoxmax);
		var yminstr = formatLongLat(qryGeoymin);
		if(dojo.byId("leftuplong")!=null){
			dojo.byId("leftuplong").value = xminstr;
			dojo.byId("leftuplat").value = ymaxstr;
			dojo.byId("rightdownlong").value = xmaxstr;
			dojo.byId("rightdownlat").value = yminstr;
		}
		var polygonJson = {
				"rings" : [ [ [ Number(qryGeoxmin), Number(qryGeoymax) ],
						[ Number(qryGeoxmax), Number(qryGeoymax) ],
						[ Number(qryGeoxmax), Number(qryGeoymin) ],
						[ Number(qryGeoxmin), Number(qryGeoymin) ],
						[ Number(qryGeoxmin), Number(qryGeoymax) ] ] ],
				"spatialReference" : {
					"wkid" : 4326
				}
		};

		var polygon = new esri.geometry.Polygon(polygonJson);
		
		areasAndLengthsHandler(polygon);
	}
	if (!map.isPan) {
		map.enablePan();
		currentMapOpr = MapOprEnum.Unknown;
	}
}

/*
 * 格式化景中心经纬度，小数点后保留两位有效数字
 */
function formatLongLat(str) {
	var tempArray = str.toString().split(".");
	var suffx = tempArray[1];
	var subStr = suffx.substr(0, 2);
	return tempArray[0] + "." + subStr;
}

//平移
function pan_method() {
	drawToolbar.deactivate();
	navToolbar.activate(esri.toolbars.Navigation.PAN);
	currentMapOpr = MapOprEnum.Unknown;
	var connection = new Array();

}

// 放大
function zoomin_method() {
	map.reposition();
	map.disablePan();
	navToolbar.activate(esri.toolbars.Navigation.ZOOM_IN);
	currentMapOpr = MapOprEnum.Unknown;
	var connection = new Array();
	connection[0] = dojo.connect(map, "onMouseDragStart", function(feature) {
		map.disablePan();
	});

	connection[1] = dojo.connect(map, "onMouseDragEnd", function(feature) {
		map.disablePan();
	});
	connection[2] = dojo.connect(map, "onUpdateStart", function(feature) {
		dojo.disconnect(connection[0]);
		dojo.disconnect(connection[1]);
		dojo.disconnect(connection[2]);
		map.enablePan();
		navToolbar.activate(esri.toolbars.Navigation.PAN);
	});
}

// 缩小
function zoomout_method() {
	map.reposition();
	map.disablePan();
	navToolbar.activate(esri.toolbars.Navigation.ZOOM_OUT);
	currentMapOpr = MapOprEnum.Unknown;
	var connection = new Array();
	connection[0] = dojo.connect(map, "onMouseDragStart", function(feature) {
		map.disablePan();
	});

	connection[1] = dojo.connect(map, "onMouseDragEnd", function(feature) {
		map.disablePan();
	});
	connection[2] = dojo.connect(map, "onUpdateStart", function(feature) {
		dojo.disconnect(connection[0]);
		dojo.disconnect(connection[1]);
		dojo.disconnect(connection[2]);
		map.enablePan();
		navToolbar.activate(esri.toolbars.Navigation.PAN);
	});
}

// 全图
function zoomtofull_method() {
	navToolbar.zoomToFullExtent();
}


//点选
function point_method(){
	map.reposition();
	deactivate_method();
	map.disablePan();
	navToolbar.deactivate();
	map.hideZoomSlider();
	drawToolbar.activate(esri.toolbars.Draw.POINT);
	currentMapOpr = MapOprEnum.PointGraphic;
}

// 多边形
function polygon_method() {
	map.reposition();
	deactivate_method();
	map.disablePan();
	navToolbar.deactivate();
	map.hideZoomSlider();
	drawToolbar.activate(esri.toolbars.Draw.POLYGON);
	currentMapOpr = MapOprEnum.PolygonGraphic;
}

// 矩形框选择工具
function rectangle_method() {
	map.reposition();
	currentMapOpr = MapOprEnum.RectangleQuery;
	navToolbar.deactivate();
	drawRectangle = {
		startPoint : null,
		endPoint : null
	};
	map.disablePan();
}

// 圆形工具
function circle_method() {
	map.reposition();
	// deactivate_method();
	// map.disablePan();
	// navToolbar.deactivate();
	// drawToolbar.activate(esri.toolbars.Draw.CIRCLE);
	currentMapOpr = MapOprEnum.CircleGraphic;
	map.disablePan();
	map.hideZoomSlider();
	drawToolbar.activate(esri.toolbars.Draw.MULTI_POINT, {
		showTooltips : false
	});
	var connection = new Array();
	connection[0] = dojo.connect(map, "onMouseDragStart", function(feature) {
		mapEvent = feature;
	});
	var circleFlag = 1;
	connection[1] = dojo
			.connect(
					map,
					"onMouseDrag",
					function(feature) {
						map.graphics.clear();
						if (mapEvent.mapPoint) {
							var arrx;
							var arry;
							var arbentab = Math.PI / 18;
							arrx = feature.mapPoint.x - mapEvent.mapPoint.x;
							arry = feature.mapPoint.y - mapEvent.mapPoint.y;
							var radus = Math.sqrt(arrx * arrx + arry * arry);
							movex = mapEvent.mapPoint.x;
							movey = mapEvent.mapPoint.y;
							var rings = new Array();
							rings[0] = [ radus + movex, movey ];
							circle = new esri.geometry.Polygon(
									map.spatialReference);
							for ( var i = 1; i < 36; i++) {
								j = i - 1;
								k = i;
								arrx = radus * Math.cos(arbentab * k) + movex;
								arry = radus * Math.sin(arbentab * k) + movey;
								rings[i] = [ arrx, arry ];
							}
							rings[36] = rings[0];
							circle.addRing(rings);
							var symbol = new esri.symbol.SimpleFillSymbol(
									esri.symbol.SimpleFillSymbol.STYLE_SOLID,
									new esri.symbol.SimpleLineSymbol(
											esri.symbol.SimpleLineSymbol.STYLE_SOLID,
											new dojo.Color([ 255, 0, 0 ]), 2),
									new dojo.Color([ 0, 0, 0, 0.25 ]));
							var graphic = new esri.Graphic(circle, symbol);
							map.graphics.add(graphic);
							if (circleFlag != 1) {
								map.graphics
										.remove(map.graphics.graphics[map.graphics.graphics.length - 2]);
							}
							circleFlag++;
						}
					});
	connection[2] = dojo
			.connect(
					map,
					"onMouseDragEnd",
					function(feature) {
						map.graphics
								.remove(map.graphics.graphics[map.graphics.graphics.length - 1]);
						addToMap(circle);
//						areasAndLengthsHandler(circle);
						dojo.disconnect(connection[0]);
						dojo.disconnect(connection[1]);
						dojo.disconnect(connection[2]);
						drawToolbar.deactivate();
						map.showZoomSlider();
						map.enablePan();
						currentMapOpr = MapOprEnum.Unknown;
					});
	connection[3] = dojo.connect(map, "onClick", function(feature) {
		map.graphics.clear();
		drawToolbar.deactivate();
		map.showZoomSlider();
		map.enablePan();
		currentMapOpr = MapOprEnum.Unknown;
		dojo.disconnect(connection[0]);
		dojo.disconnect(connection[1]);
		dojo.disconnect(connection[2]);
		dojo.disconnect(connection[3]);
	});
}
function addToMap(geometry) {

	switch (geometry.type) {
	case "point":
		var symbol = new esri.symbol.SimpleMarkerSymbol(
				esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([ 255, 0, 0 ]), 1), new dojo.Color([ 0,
						255, 0, 0.25 ]));
		break;
	case "polyline":
		var symbol = new esri.symbol.SimpleLineSymbol(
				esri.symbol.SimpleLineSymbol.STYLE_DASH, new dojo.Color([ 255,
						0, 0 ]), 1);
		break;
	case "polygon":
		var symbol = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_NONE,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
						new dojo.Color([ 255, 0, 0 ]), 2), new dojo.Color([
						255, 255, 0, 0.25 ]));
		break;
	case "extent":
		var symbol = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_NONE,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
						new dojo.Color([ 255, 0, 0 ]), 2), new dojo.Color([
						255, 255, 0, 0.25 ]));
		break;
	case "multipoint":
		var symbol = new esri.symbol.SimpleMarkerSymbol(
				esri.symbol.SimpleMarkerSymbol.STYLE_DIAMOND, 20,
				new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([ 0, 0, 0 ]), 1), new dojo.Color([ 255,
						255, 0, 0.5 ]));
		break;
	}
	qryGeo = geometry;
	var graphic = new esri.Graphic(geometry, symbol);
	map.graphics.add(graphic);
}

//清除方法
function deactivate_method() {
	// navToolbar.activate(esri.toolbars.Navigation.PAN);
	if(map.graphics != null){
	map.graphics.clear();
	qryGeo = null;
	// 清空四点坐标
	if(dojo.byId("leftuplong")!=null){
		dojo.byId("leftuplong").value = '';
		dojo.byId("leftuplat").value = '';
		dojo.byId("rightdownlong").value = '';
		dojo.byId("rightdownlat").value = '';
	}
	
	drawToolbar.deactivate();
	navToolbar.deactivate();
	currentMapOpr = MapOprEnum.Unknown;
	}
}

function heigthLightCrush(){
	
	highlightLayer.clear();
}

function heigthLight(leftupperlong,leftupperlat,rightupperlong,rightupperlat,rightlowerlong,rightlowerlat,leftlowerlong,leftlowerlat){
	var polygonJson = {
			"rings" : [ [ [ Number(leftupperlong), Number(leftupperlat) ],
					[ Number(rightupperlong), Number(rightupperlat) ],
					[ Number(rightlowerlong), Number(rightlowerlat) ],
					[ Number(leftlowerlong), Number(leftlowerlat) ],
					[ Number(leftupperlong), Number(leftupperlat) ] ] ],
			"spatialReference" : {
				"wkid" : 4326
			}
	};

	var polygon = new esri.geometry.Polygon(polygonJson);
	var symbol1 = new esri.symbol.SimpleFillSymbol(
			esri.symbol.SimpleFillSymbol.STYLE_SOLID,
			new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([
							200, 0, 0 ]), 2), new dojo.Color([ 255, 255, 0,
					0.25 ]));
	var graphic = new esri.Graphic(polygon, symbol1);
	highlightLayer.add(graphic);
}


//国家查询方法
function executeCountry() {
	deactivate_method();
	var arealevel = 'country';
	var countrywheresql = "1=1";
	var query = new esri.tasks.Query();
	query.returnGeometry = true;
	query.outFields =  ["*"];
	query.where = countrywheresql;
	query.spatialRelationship = esri.tasks.Query.SPATIAL_REL_INTERSECTS;
	query.outSpatialReference = {
		"wkid" : 4326
	};

	queryTaskCountries.execute(query);
}

//查询国家回调函数
function onqueryTaskCountriesCompleteHandler(fset) {
	var resultFeatures = fset.features;
	for ( var i = 0, il = resultFeatures.length; i < il; i++) {
		var graphic = resultFeatures[i];
		countryGraphics.push(graphic);
		var featureAttributes = resultFeatures[i].attributes;
		var obj = new Object();
		obj.FID = featureAttributes['FID'];
		obj.name = featureAttributes['LONG_NAME'];
		obj.en_name = featureAttributes['CNTRY_NAME'];
		countrydistincts.push(obj);
	}
	// 填充国家信息
	fillToCountryBox();
}

var worldName =""; //国家名称中文
var statesName = ""; //州名称

//获取国家数据填充到国家下拉框
function fillToCountryBox()
{
	if(countrydistincts.length>0)
	{
		for(var i=0;i<countrydistincts.length;i++)
		{
			var item = countrydistincts[i];
			jQuery("#country").append("<option id='old' value='"+ item.en_name +"'>" + item.en_name + "</option>"); 
		}
	}
}

function selectCountry(opt){
	if (countryGraphics.length > 0) {
		map.graphics.clear();
		var symbol = new esri.symbol.SimpleMarkerSymbol();
		symbol.style = esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE;
		symbol.setSize(8);
		symbol.setColor(new dojo.Color([ 255, 255, 0, 0.25]));

		for ( var i = 0; i < countryGraphics.length; i++) {
			var graphic = countryGraphics[i];
			var featureAttributes = graphic.attributes;
			var name = featureAttributes['CNTRY_NAME'];
			var selectname = opt.options[opt.selectedIndex].value;
			worldName = selectname;
			if (name == selectname) {
//				qryGeo = graphic.geometry;
				graphic.setSymbol(symbol);
				map.graphics.add(graphic);
				// 加载州/省
				loadStates(selectname);
				break;
			}
		}
	}
}

//触发加载州/省信息
function loadStates(en_name) {
	var arealevel = 'countries';
//	var countrywheresql = "INCOUNTRY='" + en_name + "'";
	var countrywheresql = "INCOUNTRY='Venezuela'";
	var query = new esri.tasks.Query();
	query.returnGeometry = true;
	query.outFields = ["*"];
	query.where = countrywheresql;
	query.spatialRelationship = esri.tasks.Query.SPATIAL_REL_INTERSECTS;
	query.outSpatialReference = {
		"wkid" : 4326
	};
	queryTaskStates.execute(query);
}

function onqueryTaskStatesCompleteHandler(fset){
	statesdistincts = new Array();
	var resultFeatures = fset.features;
	for ( var i = 0, il = resultFeatures.length; i < il; i++) {
		var graphic = resultFeatures[i];
		statesGraphics.push(graphic);
		var featureAttributes = resultFeatures[i].attributes;
		var obj = new Object();
		obj.FID = featureAttributes['FID'];
		obj.name = featureAttributes['NAME'];
//		obj.name1 = featureAttributes['NAME_1'];
		statesdistincts.push(obj);
	}
	// 填充州/省信息
	fillToStatesBox();
}

function fillToStatesBox(){
	jQuery("#states option[id='old']").remove();
	if(statesdistincts.length>0)
	{
		for(var i=0;i<statesdistincts.length;i++)
		{
			var item = statesdistincts[i];
			jQuery("#states").append("<option id='old' value='"+ item.name +"'>" + item.name + "</option>"); 
		}
	}else{
//		var selecttext = document.getElementById("commonselect").value;
//		jQuery("#countries").append("<option>" + selecttext + "</option>");
	}
	
}

//选择州/省
function selectState(opt) {
	if (statesGraphics.length > 0) {
		map.graphics.clear();
		var symbol = new esri.symbol.SimpleMarkerSymbol();
		symbol.style = esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE;
		symbol.setSize(8);
		symbol.setColor(new dojo.Color([ 255, 255, 0, 0.25 ]));
		for ( var i = 0; i < statesGraphics.length; i++) {
			var graphic = statesGraphics[i];
			var featureAttributes = graphic.attributes;
			var name = featureAttributes['NAME'];
			var selectid = opt.options[opt.selectedIndex].value;
			statesName = selectid;
			if (name == selectid) {
				graphic.setSymbol(symbol);
				map.graphics.add(graphic);
				break;
			}
		}
	}
}


//采集单查看一天轨迹
function viewTrack(){
	var trackLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/subsp1/MapServer", {
		id : "trackLayer" // 轨迹
	});
	map.addLayer(trackLayer);
	var newExtent = new esri.geometry.Extent(-180, -90, 180,
			90, map.spatialReference);
	map.setExtent(newExtent);
}

//查看轨迹
function viewSateTrail(){
	var mapType = $('#trail').val();
	var subsp = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/subsp"+mapType+"/MapServer", {
		id : "subsp" 
	});
	
	if(mapType == "-1"){
		map.removeLayer(map.getLayer("subsp"));
	}
	else{
		for(var i=0;i<map.layerIds.length;i++){
			if(map.layerIds[i]=="subsp"){
				map.removeLayer(map.getLayer(map.layerIds[i]));
			}
		}
		map.addLayer(subsp);
		var newExtent = new esri.geometry.Extent(-400, -120, 200,
				120, map.spatialReference);
		map.setExtent(newExtent);
	}
}

//采集单详情地图
function collectMapInit(){
	// 设置jQuery ajax的同步
	jQuery.ajaxSetup({
		async : false
	});
	var map = new esri.Map("collectMap", {
		logo : false
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map/MapServer", {
		id : "worldLayer" //底图
	});
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	map.addLayer(worldlayer);
	map.addLayer(highlightLayer);
	
	var leftuplong = document.getElementById("upperleftlon").value;
	var leftuplat = document.getElementById("upperleftlatitude").value;
	var lowerrightlong = document.getElementById("lowerrightlon").value;
	var lowerrightlat = document.getElementById("lowerrightlatitude").value;

	var extentP = new esri.geometry.Extent(leftuplong, lowerrightlat, lowerrightlong,
			leftuplat, map.spatialReference);
	
	var symbol1 = new esri.symbol.SimpleFillSymbol(
			esri.symbol.SimpleFillSymbol.STYLE_SOLID,
			new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([
							200, 0, 0 ]), 2), new dojo.Color([ 255, 255, 0,
					0.25 ]));
	var graphic = new esri.Graphic(extentP, symbol1);
	highlightLayer.add(graphic);
	
}


	


