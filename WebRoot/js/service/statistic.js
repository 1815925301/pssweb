//layer打开的顺序
var newIndex,showIndex,editIndex;
var arraycount = new Array();//图形的Y轴的数组值
var arraytm = new Array();//图形的X轴的数组值
var arraypie = [];//饼状图的数值

var total;//图形中小计值
var type_change;//图形中可变的类型
//页面初始化时直接加载
$(document).ready(function() {
	 var now = new Date();
	 var time_init = now.getFullYear()
           + "-"// "年"
           + ((now.getMonth() + 1) >= 10 ? (now.getMonth() + 1) : "0"
                   + (now.getMonth() + 1))
           + "-"// "月"
           + (now.getDate() < 10 ? "0" + now.getDate() : now.getDate());
	$('#starttime').attr('value',time_init);//对时间进行转换赋值
	$('#endtime').attr('value',time_init);
	showUserCount();//调用初始化页面图形显示的方法
});
//-----------------------------统计数量开始-------------------

function showUserCount(){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getUserCount.do',
	    dataType : 'json',
	    success : function(data) {
	    	type_change="";
	    	total="";
	    	arraycount.length=0;
	    	arraytm.length=0;
	    	total=data.usersCount;//用于显示小计总数
	    	arraycount.push(data.usersCount);//给Y轴赋值，总数量
	    	arraytm.push(data.usersCountTm);//X轴的值（时间）
	    	type_change='line';
	    	lineChartShow(type_change); 
	    },
	    error:function(){
	    }
	});
};
function lineChartShow_pie(){
	 $('#lineChart').highcharts({
		 chart: {
		            type: 'pie'
		        },
	        title: {
	            text: '访问量统计分析',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '小计:'+total,
	            x: -20
	        },
	       
	        yAxis: {
	            title: {
	                text: '数量'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'center',
	            //verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '用户访问量',
	            data:arraypie
	      
	        }]
	    });
	
}
function lineChartShow(type_change){
	Highcharts.setOptions({
	    lang: {
	        printChart: '打印图表',
	        downloadJPEG: '下载 JPEG 文件',
	        downloadPDF: '下载 PDF   文件',
	        downloadPNG: '下载 PNG  文件',
	        downloadSVG: '下载 SVG  文件',
	        downloadCSV: '下载 CSV  文件',
	        downloadXLS: '下载 XLS   文件'
	    },
	    navigation: {
	        menuItemStyle: {
	            padding: '6px 14px'
	        }
	    },
	    exporting: {
	        url: 'http://export.hcharts.cn'
	    },
	    credits: {
	        text: 'Highcharts中文网',
	        url: 'http://www.hcharts.cn'
	    }
	});
	 $('#lineChart').highcharts({
		 chart: {
	            type: type_change
	        },
	     
	        title: {
	            text: '访问量统计分析',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '小计:'+total,
	            x: -20
	        },
	        xAxis: {
	        	categories:arraytm
	        },
	        yAxis: {
	            title: {
	                text: '数量'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '用户访问量',
	            data: arraycount
	      
	        }]
	    });
}

//点击搜索，根据条件查询数量
$('#searchCount').click(function(){
	var stime = $("#starttime").val();//获取开始时间
	var etime = $("#endtime").val();//获取结束时间
	var unit = $("#report_unit").val();//获取时间单位
	var type = $("#report_type").val();//获取图形类型
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getCountByCondition.do',
	    dataType : 'json',
	    data:{stime:stime,etime:etime,unit:unit},
	    success : function(data) {
	    	total="";
	    	type_change="";
	    	arraycount.length=0;
	    	arraytm.length=0;
	    	arraypie.length=0;
	    	if(type=="折线图"){
	    		type_change='line';
	    	}
	    	if(type=="柱状图"){
	    		type_change='column';
	    	}
	    	
	    	if(unit == "日"){
	    		total=data.usersCount_day;
	    		var userslist = data.usersList_day;
	    		if(userslist.length==0){
	    			alert("没有数据！");
	    		}
	    		 for(var i = 0;i<userslist.length;i++){
	    			 if(type=="饼状图"){
	    				 arraypie.push([userslist[i].registerTime,parseInt(userslist[i].counts)]);
	    			 }else{
	    				 arraycount.push(parseInt(userslist[i].counts)); 
	    				 arraytm.push(userslist[i].registerTime);
	    			 }
	    		 }
	    		 
	    	}
	    	if(unit == "月"){
	    		total=data.usersCount_month;
	    		var userslist = data.usersList_month;
	    		if(userslist.length==0){
	    			alert("没有数据！");
	    		}
	    		 for(var i = 0;i<userslist.length;i++){
	    			 if(type=="饼状图"){
	    				 arraypie.push([userslist[i].registerTime,parseInt(userslist[i].counts)]);
	    			 }else{
	    				 arraycount.push(parseInt(userslist[i].counts)); 
	    				 arraytm.push(userslist[i].registerTime);
	    			 }
	    		 }
	    	}
	    	if(unit == "年"){
	    		total=data.usersCount_year;
	    		var userslist = data.usersList_year;
	    		if(userslist.length==0){
	    			alert("没有数据！");
	    		}
	    		 for(var i = 0;i<userslist.length;i++){
	    			 if(type=="饼状图"){
	    				 arraypie.push([userslist[i].registerTime,parseInt(userslist[i].counts)]);
	    			 }else{
	    				 arraycount.push(parseInt(userslist[i].counts)); 
	    				 arraytm.push(userslist[i].registerTime);
	    			 }
	    		 }
	    	}
	    	if(type=="饼状图"){
	    		lineChartShow_pie();
	    	}else{
	    		//alert(arraycount);
	    		lineChartShow(type_change); 
	    	}
	    },
	    error:function(){
	    }
	});
});
//-----------------------------统计数量结束-------------------
