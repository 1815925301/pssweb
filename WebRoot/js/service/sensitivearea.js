$(document).ready(function() {
	changeLang_js();
	         
});

//导入敏感区域shp
function importSensitiveShape(){
	var filename=$("#shpfile").val();
	var startindex=filename.lastIndexOf("\\")+1;
	var endindex=filename.length;
	var s=filename.substring(startindex,endindex);
	var shpName = s.split(".")[0];
	$("#shpid").val(shpName);
     html='<table class="table table-striped table-bordered table-hover dataTables-example">';
	 html=html+'<thead><tr><td >'+$.i18n.prop('name')+'</td><td >'+$.i18n.prop('times')+'</td></tr>';
	 html=html+'</thead><tbody>';
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
				alert($.i18n.prop('loadsuccess'));
			}
			var myDate = new Date();
			var dds = myDate.toLocaleString(); 
			html=html+'<tr>';
			html=html+' <td class="botValue">'+shpName+'</td>';
	    	html=html+' <td class="botValue">'+dds+'</td>';
	    	html=html+'</tr>';
	    	html=html+'</tbody></table>';
	    	$("#sensitiveList").html(html);
		},
		error : function(data, status, e) {
			alert(data+"-----"+status+"---------"+e);
		}
	});
}