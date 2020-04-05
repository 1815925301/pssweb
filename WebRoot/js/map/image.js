/**
 * 添加浏览图到地图
 * @author zc
 */
var productid = null;//产品id

function addImageToMap(id,urlimg1) {
	
	//var urlimg = "/pssweb/1.jpg";
	var aa="#span"+id;
	var urlimg =urlimg1 ;
	var rl = map.getLayer("resultsLayer");
	var graphicsArray = new Array();
	var graphicsArray = rl.graphics;
	var curclassname = jQuery(aa).attr('class');
	for(var i=0; i<graphicsArray.length; i++){
		var gh = graphicsArray[i];
		if (typeof(gh.attributes)!='undefined') {
			if (curclassname == "eye") {
				if(gh.attributes.id == id){
					var mapExtent = gh.geometry.getExtent();
					var point = gh.geometry.rings;
					var picheight = "";
					var picwidth = "";
					var angle = "";
					var point1 , point2, point3, point4 ;
					for (var i =0;i<point.length;i++) {
						var pointLAT = point[i] ;
						point1 = getPointByCoords(pointLAT[0][0], pointLAT[0][1]) ;
						point2 = getPointByCoords(pointLAT[1][0], pointLAT[1][1]) ;
						point3 = getPointByCoords(pointLAT[2][0], pointLAT[2][1]) ;
						point4 = getPointByCoords(pointLAT[3][0], pointLAT[3][1]) ;
						var p1 = toScreen(point1);
						var p2 = toScreen(point2);
						var p3 = toScreen(point3);
						var p4 = toScreen(point4);
						picwidth = getLength(p1,p2);
						picheight = getLength(p2,p3);
//						picwidth = (p2.x - p1.x);
//						picheight = (p3.y - p2.y);
						//角度偏移值计算
						angle = getAngle(p1.x, p1.y , p4.x, p4.y);
					}
					var picObj = getPicWidth(mapExtent);
					var picSymbol = new esri.symbol.PictureMarkerSymbol(urlimg, picwidth, picheight);
					//偏移角度
					picSymbol.setAngle(angle-90);
					picSymbol.setColor(new dojo.Color("red"));
					var mapPoint = getExtentCenter(mapExtent);
					var picGh = new esri.Graphic(mapPoint, picSymbol);
					picGh.setAttributes({picId:id});
					rl.add(picGh);
					ChangeEye(id);
					break;
				}
			}
			else{
				if(graphicsArray[i] != null && gh.attributes.picId == id){
					rl.remove(gh);
					ChangeEye(id);
				}
			}
		}
	}
}

function addAllImages2Map(){
	for(var j=0;j<distincts.length;j++){
		var id = distincts[j].productid;
		var urlimg = distincts[j].thumbname;
		addImageToMap(id,urlimg);
	}
}

function getAngle(px1, py1, px2, py2) {
	// 两点的x、y值
	x = px2 - px1;
	y = py2 - py1;
	hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	// 斜边长度
	cos = x / hypotenuse;
	radian = Math.acos(cos);
	// 求出弧
	angle = 180 / (Math.PI / radian);
	// 用弧度算出角度
	if (y < 0) {
		angle = -angle;
	} else if ((y == 0) && (x < 0)) {
		angle = 180;
	}
	return angle;
}

function getFillSymbol(color){
	if(color=='gray'){ //灰色
		lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([120, 129, 135,0.8]), 3);
		fillSymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, lineSymbol, new dojo.Color([120, 129, 135,0]));
	}else if(color=='blue'){ //蓝色
		lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0, 0, 255, 0.8]), 3);
		fillSymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, lineSymbol, new dojo.Color([41, 239, 246,0]));
	}else if(color=='green'){ //绿色
		lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0, 255, 0, 0.8]), 3);
		fillSymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, lineSymbol, new dojo.Color([41, 239, 246, 0]));
	}
	return fillSymbol;
} 

//改变贴浏览图标志的显示
function ChangeEye(productid) {
	var curclassname = jQuery('#span' + productid).attr('class');
	if (curclassname == "eye") {
		jQuery('#span' + productid).removeClass("eye").addClass("eye2");
	} else {

		jQuery('#span' + productid).removeClass("eye2").addClass("eye");
	}
}

//通过2点的屏幕坐标，返回一个长度
function getLength(p1,p2)
{
	var p3 = new Object();
	p3.x = p1.x;
	p3.y = p2.y;
	var length1 = Math.abs(p1.y-p2.y);
	var length2 = Math.abs(p2.x-p1.x);
	var length3z = length1*length1+length2*length2;
	var length3 = Math.pow(length3z,0.5);
	return length3;
}

function getPointByCoords(x, y){ // 通过坐标得到 地图点
	var mapPoint = new esri.geometry.Point(x, y, new esri.SpatialReference({ wkid:4326 }));
	return mapPoint;
}

function toScreen(mapPoint) {
	return map.toScreen(mapPoint);
}

function getPicWidth(extent){
	var x_min = extent.xmin;
	var x_max = extent.xmax;
	var y_min = extent.ymin;
	var y_max = extent.ymax;
	var p1 = map.toScreen(getPointByCoords(x_min, y_max));
	var p2 = map.toScreen(getPointByCoords(x_max, y_max));
	var p3 = map.toScreen(getPointByCoords(x_max, y_min));
	var width = p2.x - p1.x;
	var height = p3.y - p2.y;
	return {width:width, height:height}
}

function getExtentCenter(extent){
	var x_min = extent.xmin;
	var x_max = extent.xmax;
	var y_min = extent.ymin;
	var y_max = extent.ymax;
	return getPointByCoords((x_min+x_max)/2, (y_min+y_max)/2);
}

function expandPicture(extent, zoomFactor, anchor, level){
	var resultsLayer = map.getLayer("resultsLayer");
	for(var i=0; i<resultsLayer.graphics.length; i++){
		var gh = resultsLayer.graphics[i];
		if (typeof(gh.attributes)!='undefined' && gh.attributes.picId) {
			gh.symbol.setWidth(gh.symbol.width*zoomFactor);
			gh.symbol.setHeight(gh.symbol.height*zoomFactor);
		}
	}
}
