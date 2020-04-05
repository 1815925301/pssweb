<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd" style="padding-top:10px;padding-left:100px;">
				
										<span>访问日期：
		                                <input type="text"  id="starttime"  value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
			                             -
			                             <input type="text"  id="endtime" value="" style="width:112px" class="form-control Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" >
			                            </span>
			                            <span style="padding-left:50px;">报表单位：
			                            <select style="width:60px" id="report_unit">
			                            <option>日</option>
			                            <option>月</option>
			                            <option>年</option>
			                            </select>
			                            </span>
			                            <span style="padding-left:12px;">报表类型：
			                            <select style="width:90px" id="report_type">
			                            <option>折线图</option>
			                            <option>柱状图</option>
			                            <option>饼状图</option>
			                            </select>
			                            </span>
			                           <span style="padding-left:30px;">
			                           <input  type="button" class="btn btcms" id="searchCount" value="搜索">
									   </span>
									
			</div>
				
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body" id="lineChart"></div>
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
<script src="js/service/statistic.js" type="text/javascript" ></script> 



