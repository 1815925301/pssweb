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


var map, resultsLayer, highlightLayer,importShpLayer,imageLayer,measuregeometry;
var navToolbar;
var drawToolbar,drawToolMeasure;

var onLoadHandler = null;
var onDrawHandler = null;
var onDragHandler = null, onDragStartHandler = null, onDragEndHandler = null, onQueryCompleteHandler, onGraphicAddHandler = null;
var onKeyDownHandler = null;// 鼠标按下事件
var qryGeo = null;
var selectedProducts = new Array();// 选择的产品集合

var geometryService = null;
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
var flag = 1; //产品查询与专题产品查询结果集弹框标识

var symbolResult = new esri.symbol.SimpleFillSymbol(
		esri.symbol.SimpleFillSymbol.STYLE_SOLID,
		new esri.symbol.SimpleLineSymbol(
				esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([
						0, 0, 0 ]),1), new dojo.Color([ 255, 255, 255,
				0.25 ]));


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
		logo : false,
		spatialReference: new esri.SpatialReference({wkid : 4326}),
		
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer", {
		id : "worldLayer" //底图
	});
	resultsLayer = esri.layers.GraphicsLayer({
		id : "resultsLayer"
	});// 结果集图层
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	importShpLayer = esri.layers.GraphicsLayer({
		id : "importShpLayer"
	});// 导入shp图层
	
	imageLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/world_image/MapServer", {
		id : "imageLayer", //底图
		visible:false
	});
	
	map.addLayer(worldlayer);
	map.addLayer(imageLayer);
	map.addLayer(resultsLayer);
	map.addLayer(highlightLayer);
	map.addLayer(importShpLayer);
	drawToolbar = new esri.toolbars.Draw(map);
	drawToolMeasure = new esri.toolbars.Draw(map);
	onDragHandler = dojo.connect(map, "onMouseDrag", onMouseDragHandler);
	onDragStartHandler = dojo.connect(map, "onMouseDragStart",
			onMouseDragStartHandler);
	onDragEndHandler = dojo.connect(map, "onMouseDragEnd",
			onMouseDragEndHandler);
	navToolbar = new esri.toolbars.Navigation(map);
	dojo.connect(drawToolbar, "onDrawEnd", onDrawEndHandler);
	dojo.connect(drawToolMeasure, "onDrawEnd", doMeasure);
	
	onLoadHandler = dojo.connect(map, 'onLoad', function(map) {
		navToolbar = new esri.toolbars.Navigation(map);
		dojo.connect(dijit.byId('map'), 'resize', map, map.resize);
	});
	
	queryTaskCountries = new esri.tasks.QueryTask(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer/0");
	queryTaskStates = new esri.tasks.QueryTask(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer/2");
	
	identifyTaskVRSS_1 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_VRSS_1/MapServer");
	identifyTaskVRSS_2 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_VRSS_2/MapServer");
	identifyTaskGF_1 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_GF_1/MapServer");
	identifyTaskGF_2 = new esri.tasks.IdentifyTask(mapurl
			+ "/arcgis/rest/services/pathRow_GF_2/MapServer");
	
	geometryService = new esri.tasks.GeometryService(mapurl+"/arcgis/rest/services/Utilities/Geometry/GeometryServer");
	
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

}

function topitMapInit() {
	// 初始化地图
	init();
	// 加载国家列表
	executeCountry();
}


function onDrawEndHandler(geometry) {
	var sfs = null;
	if ((geometry.type == "point")&&(!(sateName =="-1"))) {
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
    }if( (geometry.type == "point") && (sateName =="-1") ) {
    	qryGeo = geometry;
		sfs = new esri.symbol.SimpleMarkerSymbol(
		esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
		new esri.symbol.SimpleLineSymbol(
				esri.symbol.SimpleLineSymbol.STYLE_SOLID,
				new dojo.Color([ 255, 0, 0 ]), 1), new dojo.Color([ 0,
				255, 0, 0.25 ]));
		map.graphics.clear();
		map.enablePan();
		currentMapOpr = MapOprEnum.Unknown;
		var graphic = new esri.Graphic(geometry, sfs);
		map.graphics.add(graphic);
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
		//新增开始 -----
		var extent = geometry.getExtent();
		var qryGeoxmin = extent.xmin;
		var qryGeoymax = extent.ymax;
		var qryGeoxmax = extent.xmax;
		var qryGeoymin = extent.ymin;
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
		//新增结束------
		var graphic = new esri.Graphic(geometry, sfs);
		map.graphics.add(graphic);
	}
}

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

//画线测量距离
function lengthMeasure_method(){
	drawToolMeasure.activate(esri.toolbars.Draw.POLYLINE);
}
//画多边形测量面积
function areaMeasure_method(){
	drawToolMeasure.activate(esri.toolbars.Draw.POLYGON);
}


function doMeasure(geometry) {
    //更加类型设置显示样式
    measuregeometry = geometry; 
    drawToolMeasure.deactivate();
    switch (geometry.type) {
        case "polyline":
            var symbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0, 0, 0]), 2);
            break;
        case "polygon":
            var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_NONE, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([255, 0, 0]), 2), new dojo.Color([255, 255, 0, 0.25]));
            break;
    }
    var graphic = new esri.Graphic(geometry,symbol);
    map.graphics.clear();
    map.graphics.add(graphic);
    MeasureGeometry(geometry);
}

function MeasureGeometry(geometry) {
    //如果为线类型就进行lengths距离测算
    if (geometry.type == "polyline") {
    	var length = geometry.paths[0].length;  
         showPt = new esri.geometry.Point(geometry.paths[0][length-1],map.spatialReference);  
         var lengthParams = new esri.tasks.LengthsParameters();  
         lengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;  
         lengthParams.polylines = [geometry];  
         geometryService.lengths(lengthParams);
         dojo.connect(geometryService, "onLengthsComplete", outputDistance);
    }
    //如果为面类型需要先进行simplify操作在进行面积测算
    else if (geometry.type == "polygon") {
        var areasAndLengthParams = new esri.tasks.AreasAndLengthsParameters();
        areasAndLengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_METER;
        areasAndLengthParams.areaUnit = esri.tasks.GeometryService.UNIT_SQUARE_METERS;
        this.outSR = new esri.SpatialReference({ wkid: 102113 });
        geometryService.project([geometry], this.outSR, function (geometry) {
            geometryService.simplify(geometry, function (simplifiedGeometries) {
                areasAndLengthParams.polygons = simplifiedGeometries;
                areasAndLengthParams.spatialReference = new esri.SpatialReference(102113);
                geometryService.areasAndLengths(areasAndLengthParams);
            });
        });
        dojo.connect(geometryService, "onAreasAndLengthsComplete", outputAreaAndLength);
    }
}

//显示测量距离
function outputDistance(result) {
    var CurX = measuregeometry.paths[0][measuregeometry.paths[0].length - 1][0];
    var CurY = measuregeometry.paths[0][measuregeometry.paths[0].length - 1][1];
    var  CurPos  =  new  esri.geometry.Point(CurX,  CurY, map.spatialReference);
    map.infoWindow.setTitle("距离测量");
    map.infoWindow.setContent(" 测 量 长 度 ： <strong>" + parseInt(String(result.lengths[0])) + "千米</strong>");
    map.infoWindow.show(CurPos);
}

//显示测量面积
function outputAreaAndLength(result) {
    var CurX = (measuregeometry._extent.xmax + measuregeometry._extent.xmin) / 2;
    var CurY = (measuregeometry._extent.ymax + measuregeometry._extent.ymin) / 2
    var CurPos = new esri.geometry.Point(CurX, CurY, map.spatialReference);
    map.infoWindow.setTitle("面积测量");
    map.infoWindow.setContent(" 面积 ： <strong>" + parseInt(String(result.areas[0].toFixed(3) / 1000 / 1000  )) + "平方千米</strong>");
    map.infoWindow.show(CurPos);
}

//添加path/row
function addPathRow_method(){
	var pathrowLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/path_row/MapServer", {
		id : "pathrowLayer" //path_row 图层
	});
	
	if(map.getLayer("pathrowLayer")!=null){
		map.removeLayer(map.getLayer("pathrowLayer"));
	}else
		map.addLayer(pathrowLayer);
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
	map.infoWindow.hide();
	drawToolbar.activate(esri.toolbars.Draw.POLYGON);
	currentMapOpr = MapOprEnum.PolygonGraphic;
}

// 矩形框选择工具
function rectangle_method() {
	map.reposition();
	currentMapOpr = MapOprEnum.RectangleQuery;
	navToolbar.deactivate();
	map.infoWindow.hide();
	drawRectangle = {
		startPoint : null,
		endPoint : null
	};
	map.disablePan();
	lonLatQuery(); //经纬度面板显示
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
	map.infoWindow.hide();
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
	map.infoWindow.hide();
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

function backDataDeal(data,flag){
	drawToolbar.deactivate();
	var polygonJson,polygon,graphic;
	var symbol = new esri.symbol.SimpleFillSymbol(
			esri.symbol.SimpleFillSymbol.STYLE_SOLID,
			new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([
							0, 0, 0 ]),1.5), new dojo.Color([ 255, 255, 255,
					0.25 ]));
	var product = data.productList;
	resultsLayer.clear();
	for(i=0;i<product.length;i++){
		//将数据添加到地图上
		polygonJson = {
				"rings" : [ [ [ Number(product[i].topleftlongitude), Number(product[i].topleftlatitude) ],
						[ Number(product[i].toprightlongitude), Number(product[i].toprightlatitude) ],
						[ Number(product[i].bottomrightlongitude), Number(product[i].bottomrightlatitude) ],
						[ Number(product[i].bottomleftlongitude), Number(product[i].bottomleftlatitude) ],
						[ Number(product[i].topleftlongitude), Number(product[i].topleftlatitude) ] ] ],
				"spatialReference" : {
					"wkid" : 4326
				}
		};
		polygon = new esri.geometry.Polygon(polygonJson);
		graphic = new esri.Graphic(polygon, symbol);
		
		//wanglg  if else  level0  level1 level2
		if(product[i].productid==null){
			graphic.setAttributes({id:product[i].sceneid});
		}else{
			graphic.setAttributes({id:product[i].productid});
		}
		
		var ss = polygon.getExtent().getCenter();
		var centerPoint = new esri.geometry.Point(ss.x, ss.y);
		resultsLayer.add(graphic);
		map.centerAt(centerPoint);
	}
	if(flag == 2){
		map.reorderLayer(resultsLayer,0);
		dojo.connect(resultsLayer, "onClick", showInfowindow); 
		
	}
}

function showInfowindow(evt){
	alert(11);
	jQuery.ajax({
		type : 'POST',
		url : ctx + '/getOrderinfoByid.do',
		dataType : 'json',
		success : function(data) {
			var str = "<span>";
			if (undefined != data) {
				var area = data.area;
				
			} 
		}
	});
	//var infoHtml = "<div>";
//	infoHtml += "<table style='font-size:12px;color:#8a8a8b;'>";
//	infoHtml += "<tr ><td style='width:150px;'>rrrrr</td></tr>" ;
//	infoHtml += "<tr><td colspan='2' align='center'><input type='button' value='关闭' onClick='closeInfoW()'/></td></tr></table>";
	//infoHtml +="</div>";
	//var template = new esri.InfoTemplate();
   // template.setTitle("<b>详细信息</b>");
   // template.setContent(infoHtml);
	//evt.graphic.setInfoTemplate(template);
//	map.infoWindow.setTitle("详细信息"); 
//	map.infoWindow.setContent(infoHtml); 
//	map.infoWindow.resize(290,300);
//	var polygon = new esri.geometry.Polygon(evt.graphic.geometry);
//	var pPoint = new esri.geometry.Point(evt.graphic.geometry.rings[0][0][0], evt.graphic.geometry.rings[0][0][1]);
//	map.infoWindow.show(polygon.getExtent().getCenter());
}

function closeInfoW(){
	map.infoWindow.hide();
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

//选择产品
function selectProduct(evt)
{
	if(evt.checked)//选中产品
	{
		selectedProducts.push(evt.value);
	}
	else//去除选中产品
	{
		//jQuery删除数组存在的值
		selectedProducts = jQuery.grep(selectedProducts,function(n,i){
			return n!=evt.value;
		});
	}
}
//全选
function orderAllCheck2D(opt,select){
	o = document.getElementsByName(select);
	var productid = "";
	for(i = 0;i<o.length;i++){
		if(!o[i].disabled){
			if(opt.checked){
				o[i].checked = "checked";
				selectProduct(o[i]);
			}else{
				o[i].checked = "";
				selectProduct(o[i]);
			}
		}
	}
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

		for ( var i = 0; i < countryGraphics.length; i++) {
			var graphic = countryGraphics[i];
			var featureAttributes = graphic.attributes;
			var name = featureAttributes['CNTRY_NAME'];
			var selectname = opt.options[opt.selectedIndex].value;
			worldName = selectname;
			if (name == selectname) {
//				qryGeo = graphic.geometry;
				graphic.setSymbol(symbolResult);
				map.graphics.add(graphic);
				map.centerAt(graphic.geometry.getExtent().getCenter());
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
		obj.name = featureAttributes['municipio'];
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
		for ( var i = 0; i < statesGraphics.length; i++) {
			var graphic = statesGraphics[i];
			var featureAttributes = graphic.attributes;
			var name = featureAttributes['municipio'];
			var selectid = opt.options[opt.selectedIndex].value;
			statesName = selectid;
			if (name == selectid) {
				graphic.setSymbol(symbolResult);
				map.graphics.add(graphic);
				map.centerAt(graphic.geometry.getExtent().getCenter());
				break;
			}
		}
	}
}



// 查看详情嵌入地图
function productDetailInit(){
	// 设置jQuery ajax的同步
	jQuery.ajaxSetup({
		async : false
	});
	var map = new esri.Map("productMap", {
		logo : false
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer", {
		id : "worldLayer" //底图
	});
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	map.addLayer(worldlayer);
	map.addLayer(highlightLayer);
	
	var leftuplonglat= document.getElementById("leftuplonglat").innerHTML;
	var objleftuplonglat = leftuplonglat.split("/");
	var rightuplonglat = document.getElementById("rightuplonglat").innerHTML;
	var objrightuplonglat = rightuplonglat.split("/");
	var rightdownlonglat = document.getElementById("rightdownlonglat").innerHTML;
	var objrightdownlonglat = rightdownlonglat.split("/");
	var leftdownlonglat = document.getElementById("leftdownlonglat").innerHTML;
	var objleftdownlonglat = leftdownlonglat.split("/");
	
	heigthLight(objleftuplonglat[0],objleftuplonglat[1],objrightuplonglat[0],objrightuplonglat[1],
			objrightdownlonglat[0],objrightdownlonglat[1],objleftdownlonglat[0],objleftdownlonglat[1]);
	
}


//订单覆盖显示
function coverMapInit(){
	// 设置jQuery ajax的同步
	jQuery.ajaxSetup({
		async : false
	});
	var map = new esri.Map("coverMap", {
		logo : false
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer", {
		id : "worldLayer" //底图
	});
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	map.addLayer(worldlayer);
	map.addLayer(highlightLayer);
	
	//订单地图显示覆盖范围
	var pointsLonlat = document.getElementById("pointStrs").value;
	var lonLat = pointsLonlat.split(";");
	for(var i=0;i<lonLat.length;i++){
		var ss = lonLat[i].split(",");
		heigthLight(ss[1],ss[0],ss[3],ss[2],ss[5],ss[4],ss[7],ss[6]);
	}
}

//购物车覆盖显示
function shopcarCoverMapInit(){
	// 设置jQuery ajax的同步
	jQuery.ajaxSetup({ 
		async : false
	});
	var map = new esri.Map("shopcarCoverMap", {
		logo : false
	});
	var worldlayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/vrss_map2/MapServer", {
		id : "worldLayer" //底图
	});
	
	highlightLayer = esri.layers.GraphicsLayer({
		id : "highlightLayer"
	});// 高亮显示图层
	
	map.addLayer(worldlayer);
	map.addLayer(highlightLayer);
	
	//地图显示覆盖范围
	var strs = document.getElementById("orderLists").value;
	var lonLat = strs.split(";");
	for(var i=0;i<lonLat.length;i++){
		var ss = lonLat[i].split(",");
		heigthLight(ss[0],ss[1],ss[2],ss[3],ss[4],ss[5],ss[6],ss[7]);
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

//选择卫星的path/row
var sateName = "-1";
function changePathRow(){
	sateName = $('#pathRow').val();
	var pathrowLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl
			+ "/arcgis/rest/services/pathRow_"+sateName+"/MapServer", {
		id : "pathrow" 
	});
	
	if(sateName == "-1"){
		map.removeLayer(map.getLayer("pathrow"));
	}
	else{
		for(var i=0;i<map.layerIds.length;i++){
			if(map.layerIds[i]=="pathrow"){
				map.removeLayer(map.getLayer(map.layerIds[i]));
			}
		}
		map.addLayer(pathrowLayer);
	}
	
}

//矢量图/影像图切换
function changeMapType(){
	var mapType = $('#mapType').val();
	if (mapType == "vector") {
		map.getLayer("worldLayer").setVisibility(true);
		map.getLayer("imageLayer").setVisibility(false);
	}
	if (mapType == "image") {
		map.getLayer("worldLayer").setVisibility(false);
		map.getLayer("imageLayer").setVisibility(true);
	}
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
			+ "/arcgis/rest/services/vrss_map2/MapServer", {
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


	


