<#include "top.ftl">

<script type="text/javascript">
//页面初始化时直接加载
$(document).ready(function() {
	var title = "<@spring.message "menu.reportcount.r4"/>";
	var name = "<@spring.message "count.visit.num"/>";
	var countstr = "<@spring.message "report.countstr"/>";
	var number  = "<@spring.message "number"/>";
	var nodata  = "<@spring.message "nodata"/>";
	 var now = new Date();
	 var time_init = now.getFullYear()
           + "-"// "年"
           + ((now.getMonth() + 1) >= 10 ? (now.getMonth() + 1) : "0"
                   + (now.getMonth() + 1))
           + "-"// "月"
           + (now.getDate() < 10 ? "0" + now.getDate() : now.getDate());
	$('#starttime').attr('value',time_init);//对时间进行转换赋值
	$('#endtime').attr('value',time_init);
	searchChart1('/accessCountByCondition.do',title,name,countstr,number,'starttime','endtime','timeType','chart_type','#lineChart_access');
	//transmitValue('','');//参数传递
});

</script>
<!-- BEGIN PAGE CONTAINER-->        
			
			<div class="main-hd" style="padding-top:10px;padding-left:100px;">
				
										<span><@spring.message "access.date"/>：
			                                <input type="text"  id="starttime" value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
				                             -
				                            <input type="text"  id="endtime" value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
			                            </span>
			                            <span style="padding-left:50px;"><@spring.message "time.type"/>：
			                            <select style="width:60px" id="timeType">
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
			                           <span style="padding-left:30px;"> <!-- id="searchCount" -->
			                         	  <input  type="button" class="btn btcms"  value=<@spring.message "Search"/>  onclick="searchChart1('/accessCountByCondition.do','<@spring.message "menu.reportcount.r4"/>','<@spring.message "count.visit.num"/>','<@spring.message "report.countstr"/>','<@spring.message "number"/>','starttime','endtime','timeType','chart_type','#lineChart_access');"/>
									   </span>
									
			</div>
				
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body" id="lineChart_access"></div>
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



