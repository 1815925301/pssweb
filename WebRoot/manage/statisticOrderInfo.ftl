<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
<script type="text/javascript">
//页面初始化时直接加载
$(document).ready(function() {
	var title = "<@spring.message "report.order.count"/>";
	var name = "<@spring.message "report.order.ordernum"/>";
	var countstr = "<@spring.message "report.countstr"/>";
	var number  = "<@spring.message "number"/>";
	 var now = new Date();
	 var time_init = now.getFullYear()
           + "-"// "年"
           + ((now.getMonth() + 1) >= 10 ? (now.getMonth() + 1) : "0"
                   + (now.getMonth() + 1))
           + "-"// "月"
           + (now.getDate() < 10 ? "0" + now.getDate() : now.getDate());
	$('#starttime').attr('value',time_init);//对时间进行转换赋值
	$('#endtime').attr('value',time_init);
	searchChart1('/orderInfoByCondition.do',title,name,countstr,number,'starttime','endtime','reportUnit','timeType','chart_type','#lineChart_order');
	//transmitValue('','');//参数传递
});

</script>			
			
			<div class="main-hd" style="padding-top:10px;padding-left:100px;">
				
										<span><@spring.message "orderDate"/>：
			                                <input type="text"  id="starttime"  value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
				                             -
				                             <input type="text"  id="endtime" value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
			                            </span>
			                            <span style="padding-left:50px;"><@spring.message "report.unit"/>：
				                            <select style="width:120px" id="reportUnit">
						                            <option><@spring.message "user"/></option>
						                          <!--  <option><@spring.message "company"/></option>-->
						                            <option><@spring.message "order.satelliteid"/></option>
						                            <option><@spring.message "order.sensor"/></option>
						                            <option><@spring.message "order.receivingstation"/></option>
						                            <option><@spring.message "order.orderstate"/></option>
						                    <!--    <option>按订单覆盖度</option>-->
				                            </select>
			                            </span>
			                            <span style="padding-left:30px;"><@spring.message "time.type"/>：
				                            <select style="width:80px" id="timeType">
						                             <option><@spring.message "day"/></option>
				                            		 <option><@spring.message "month"/></option>
				                           			 <option><@spring.message "year"/></option>
				                            </select>
			                            </span>
			                            <span style="padding-left:12px;"><@spring.message "chart.type"/>：
				                            <select style="width:90px" id="chart_type">
						                            <option value="0"><@spring.message "chart.line"/></option>
						                            <option value="1"><@spring.message "chart.column"/></option>
						                            <option value="2"><@spring.message "chart.pie"/></option>
				                            </select>
			                            </span>
			                           <span style="padding-left:30px;"><!-- id="searchDownloadCount" -->
			                          	    <input  type="button" class="btn btcms"  value=<@spring.message "Search"/> onclick="searchChart1('/orderInfoByCondition.do','<@spring.message "report.order.count"/>','<@spring.message "report.order.ordernum"/>','<@spring.message "report.countstr"/>','<@spring.message "number"/>','starttime','endtime','reportUnit','timeType','chart_type','#lineChart_order');"/>
									   </span>
									
			</div>
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body" id="lineChart_order"></div>
							<div class="portlet-body" id="report_condition" style="display:none;"></div>
           			   </div>
                   </div>
			   </div>

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/jquery1.js" type="text/javascript" ></script> 
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/highcharts/highcharts.js" type="text/javascript" ></script> 
<script src="js/highcharts/exporting.js" type="text/javascript" ></script> 
<script src="js/highcharts/export-csv.js" type="text/javascript" ></script> 
<script src="js/service/searchChart.js" type="text/javascript" ></script> 



