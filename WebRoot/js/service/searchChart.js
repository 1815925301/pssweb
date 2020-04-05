/*
 * 公共的查询统计图js
 * 功能作用：该js一般情况下不用更新，只需要在前台调用方法，按要求传入所需的参数，然后在后台进行各自需求的处理，最后封装成chartList格式的数据，返回，图形显示完成。
 * 问题：饼状的显示可以讨论一下，有好的方法压缩代码;text，name的传递。
 */
var array_y = new Array();//图形的Y轴的数组值
var array_x = new Array();//图形的X轴的数组值
var arraypie = [];//饼状图的数值
var type_change;//定义统计图类型(line-0,column-1,pie-2)
var total;//图形中小计值
var text;//图形的标题
var name;//图形的名称
var countstr;//小计
var number;//数量(Y轴名称)

		/*
		 * 页面搜索按钮的共用方法,有定义的规范，传递参数，第一个是url,第二个是图形标题,第三个是名称，第四个是小计，第五个是数量（Y轴名称）
		 * 中间几个是不同页面传递的查询参数,最后一个是id,倒数第二个是类型,比较通用。结构如下：
		 * searchChart(url,text,name,?,?,?,?,?,chart_type,chart_id)
		 */
	function searchChart1(){
				var paramas = {};//存放需要传后台的 参数
				var newJson=[];//定义数组,存放前台传递的条件
				var url;//定义的 路径,指定对应的后台方法
				var chart_type;//统计图的类型(柱状，折线，饼状)
				var chart_id;//页面显示统计图的id
				for(var i = 0; i <arguments.length; i++) {
					if(i==0){
						url = arguments[i];
					}else if(i== 1){
						text=arguments[i];
					}else if(i== 2){
						name=arguments[i];
					}else if(i== 3){
						countstr=arguments[i];
					}else if(i== 4){
						number=arguments[i];
					}else if(i== arguments.length-1){
						chart_id=arguments[i];
					}else if(i== arguments.length-2){
						chart_type=$("#"+arguments[i]).val();
					}else{
						//alert(arguments[i]+"------"+$("#"+arguments[i]).val());
						paramas[arguments[i]]=$("#"+arguments[i]).val();
					}
			     }
				newJson.push(paramas);
				jQuery.ajax({
					type : 'POST',
				    url : ctx + url,
				    dataType : 'json',
				    data:{condition:JSON.stringify(newJson)},//后台的参数名称：condition
				    success : function(data) {
				    	total="";
				    	type_change="";
				    	array_x.length=0;
				    	array_y.length=0;
				    	arraypie.length=0;
				    	//折线图
				    	if(chart_type=="0"){
				    		type_change='line';
				    	}
				    	//柱状图
				    	if(chart_type=="1"){
				    		type_change='column';
				    	}
				    	//饼状图
				    	if(chart_type=="2"){
				    		type_change='pie';
				    	}
				    	total=data.counts;
			    		var chartList = data.chartList;
			    		if(chartList.length==0){
			    			layer.alert('没有数据！', {icon: 3, title:'提示'});
			    		}
			    		 for(var i = 0;i<chartList.length;i++){
			    			 	 if(type_change=='pie'){
			    			 		 arraypie.push([chartList[i].x,parseInt(chartList[i].y)]);
			    			 	 }else{
			    			 		 array_x.push(chartList[i].x);
				    				 array_y.push(parseInt(chartList[i].y)); 
			    			 	 }
			    		 }
			    		 if(type_change=='pie'){
			    			 lineChartShow_pie(type_change,chart_id); 
			    		 }else{
			    			 lineChartShow(type_change,chart_id); 
			    		 }
				    },
				    error:function(){
				    }
				});
		}
		/*
		 * 柱状图和折线图一起
		 */
	  function lineChartShow(type_change,chart_id){
			Highcharts.setOptions({
			    lang: {
			        printChart: '打印图表',
			        downloadJPEG: '下载 JPEG 文件',
			        downloadPDF: '下载 PDF  文件',
			        downloadPNG: '下载 PNG  文件',
			        downloadSVG: '下载 SVG  文件',
			        downloadCSV: '下载 CSV  文件',
			        downloadXLS: '下载 XLS  文件'
			    }
			});
			 $(chart_id).highcharts({
				 chart: {
			            type: type_change
			        },
			        credits: {
			            enabled: false
			        },
			        title: {
			            text: text,
			            x: -20 //center
			        },
			        subtitle: {
			            text: countstr+total,
			            x: -20
			        },
			        xAxis: {
			        	categories:array_x
			        },
			        yAxis: {
			            title: {
			                text: number
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
			            name: name,
			            data: array_y
			        }]
			    });
		}
	/*
	 * 饼状图显示
	 */
	function lineChartShow_pie(type_change,chart_id){
		 $(chart_id).highcharts({
			 chart: {
			            type: type_change
			        },
			    credits:{
			             enabled: false
			        },
		        title: {
		            text: text,
		            x: -20 //center
		        },
		        subtitle: {
		            text: countstr+total,
		            x: -20
		        },
		        yAxis: {
		            title: {
		                text: number
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
		            name: name,
		            data:arraypie
		        }]
		    });
	}
//-----------------------------统计数量结束-------------------
