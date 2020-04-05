<#include "top.ftl">
<div class=" clear"></div>

<script>
$(function () {  
    var tr = $(".gwc_table tr");  
    tr.mouseover(function () {  
        $(this).css("background-color", "#ffffff");  
        $(this).children("td").css("background-color", "#dfebfd");  
    }).mouseout(function () {  
        $(this).css("background-color", "#f6f6f6");  
        $(this).children("td").css("background-color", "#ffffff");  
    });  
    var tr = $(".gwc_table tr.gwc_tr");  
    tr.mouseover(function () {  
        $(this).css("background-color", "#f6f6f6");  
        $(this).children("td").css("background-color", "#f6f6f6");  
    }).mouseout(function () {  
        $(this).css("background-color", "#f6f6f6");  
        $(this).children("td").css("background-color", "#f6f6f6");  
    });  
    var tr = $(".gwc_table tr.rq");  
    tr.mouseover(function () {  
        $(this).css("background-color", "#ffffff");  
        $(this).children("td").css("background-color", "#ffffff");  
    }).mouseout(function () {  
        $(this).css("background-color", "#ffffff");  
        $(this).children("td").css("background-color", "#ffffff");  
    });  
});  
</script>

<div class="Sonwidth">
<div class="xw_w">
<div class="xw_bor">

<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./pssshopcar.do"><@spring.message "Shopcar"/></a></div>
</div>
	<div class="lcimg">
		  <!--新加的代码-->
            <div class="lcimg-row lcimg-row-bg">
                <div class="lcimg-col-3 lcimg-1 active">
                    <div><i>1</i></div>
                </div>
                <div class="lcimg-col-3 lcimg-2"><div><i>2</i></div></div>
                <div class="lcimg-col-3 lcimg-3"><div><i>3</i></div></div>
                <div class="lcimg-col-3 lcimg-4"><div><i>4</i></div></div>
            </div>
            <div class="lcimg-row lcimg-row-txt">
                <div class="lcimg-col-3 active">
                    <span><@spring.message "mycar"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "orderinfo"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "submitorder"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "ordersystem"/></span>
                </div>
            </div>
            <!--新加的代码END-->
	</div>
	
		<div class="ggcicon"><@spring.message "Shopcar"/>（<a href="#">${totalInfo.totalCount}</a>）</div>

		<div class="ggcanniu">
	<ul>
	<li><a href="${ctx}/product.do"><@spring.message "research"/></a></li>
	<li><a onclick="clickAllIdall()"><@spring.message "empty_cart"/></a></li>
	<li><a onclick="shopCarCoverage()" target="Blank"><@spring.message "Coverage"/></a></li>
	<li><a onclick="orderExcel()"><@spring.message "Export_excel"/></a></li>
	<li><a onclick = "order(this)"><@spring.message "genorder"/></a></li>
	</ul>
	</div>
	<div class="clear"></div>
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
				
				
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
				<input type="hidden" value="" id="pageNumInput"  name='pageNumInput' >
				
									</form>
				 
			    </div>
			
				
				
				<!-- BEGIN PAGE CONTENT-->
				
                      <table width="1099" border="0" class="gwc_table">
  <tr class="gwc_tr">
    <td width="158" height="0" bgcolor="#f6f6f6">
      <label for="checkbox"></label>
      <input type="checkbox" id="chkAll" name="chkAll" onclick="checkAll()" /><@spring.message "Selectall"/></td>
    <td width="726" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "comminfomation"/></td>
     <td width="130" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "price"/></td>
      <td width="130" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "unit"/></td>
    <td width="200" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "useroperation"/></td>
  </tr>
     <tr class="rq">
    
    </tr>
    <input type="hidden" id="idlist" name="idlist" />
	<input type="hidden" id="idnodate" name="idnodate" />	
     
				  
				                
			<#if  dateofonetime ?? && (dateofonetime ?size > 0)>
			<#list dateofonetime?keys as testKey>
			
			<td colspan="2" style="font-size:12px; "><input type="checkbox" name="checkboxdate" value="${testKey}" id="${testKey}" onclick="chkdateClick('${testKey}');"/><@spring.message "orderDate"/>：${testKey}</td>
			
			 <#assign shopcarlist = dateofonetime[testKey]>
			     <#list shopcarlist as key> 
					
    	     
    <tr >
    <td colspan="2">
      <div class="gwc_input"><input type="checkbox" id="${testKey}_${key.orderid}" name="sonChecklist" value="${testKey}_${key.orderid}"  onclick="chkworongClick('${testKey}'+'_'+${key.orderid});"/></div>
      <div class="gwc_img" style="float: left;"><img src="${key.browsefilelocation}" width="119" height="87" /></div> 
      <div class="gwc_f">
      	<@spring.message "productcate"/> : <#if key.productcate==1><@spring.message "dataproduct"/></#if><#if key.productcate==2><@spring.message "metaproduct"/></#if></br>
        <@spring.message "order_form"/>：<#if key.ordertype==1><@spring.message "ProductionOrder"/><#else><@spring.message "ProductOrder"/></#if><br />
        <@spring.message "sceneid"/>： ${key.sceneid}<br />
        <@spring.message "producttype"/>： ${key.producttype}
        </div>
      <div class="gwc_f">
         <@spring.message "productlevel"/>： ${key.productlevel}<br />
        <@spring.message "satellite"/>： ${key.satelliteid}  <br />
       <@spring.message "sensorid"/>： ${key.sensorid}
  	 </div>
      <div class="gwc_f">
        <@spring.message "productselect"/>：<br />
        <@spring.message "productnum"/>: ${key.productid}<br /> 
        <@spring.message "Order_time"/>:
        </div>
       </td>
     <td style="padding-left: 60px;">

      <span id="aloneprive_${key.orderid}" >${key.price}</span>

      
     </td>
     <td style="padding-left: 60px;" id="uuits">${key.uuit}</td>
    <td align="center"><div class="cz_font"><a href="${ctx }/getPssShopCarinfo.do?orderid=${key.orderid}" target="_Blank"><@spring.message "Check"/></a><a onclick="removeShopCar(${key.orderid});"><@spring.message "remove"/></a></div></td>
  	</td>
  </tr>
 
      </#list>              		
      </#list>                         		
       </#if>  
	                       		
	                              
						<!--table 表格结束-->
							<!-- 列表分页模块开始 start -->
				          
                                <tr>
                                    <td colspan="8">
                                   		 <div class="span6"><div class="dataTables_info" id="sample_1_info"></div></div>
                                   		 <div class="span6">
										<div class="dataTables_paginate paging_bootstrap pagination">
                                     	<ul>
											     <#if (totalInfo.totalCount > 0)>
											      <#if (totalInfo.currentPage > 1)>
											      	<li class="prev disabled">
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="<@spring.message 'previous'/>" >
											      	</li>
											        <li class="active">
											       	 <input class="btn blue" type="button" id="pageTurn1" value="1" /></li>
											      </#if>
											      
											      <#if (totalInfo.currentPage - 2 > 1)>
											        <#assign minPage = totalInfo.currentPage - 2>
											      <#else>
											        <#if (totalInfo.currentPage == 1)>
											          <#assign minPage = 1>
											        <#else>
											          <#assign minPage = 2>
											        </#if>
											      </#if>
											      
											      <#if (minPage - 1 > 1)>
											        ...
											      </#if>
											      
											      <#if (totalInfo.pageTotal - 2 > totalInfo.currentPage)>
											        <#assign maxPage = totalInfo.currentPage + 2>
											      <#else>
											        <#if (totalInfo.currentPage == totalInfo.pageTotal) || (totalInfo.pageTotal - totalInfo.currentPage == 1)>
											          <#assign maxPage = totalInfo.currentPage>
											        <#else>
											          <#assign maxPage = totalInfo.currentPage + 1>
											        </#if>
											      </#if>
											      
											      <#list minPage..maxPage as t>
											        <#if (totalInfo.currentPage == t)>
												     <li class="footable-page active">
												      <input class="btn blue " type="button" id="pageTurn${t}" value="${t}" /></li>
												    <#else> 
												    <li class="footable-page">
												      <input class="btn blue" type="button" id="pageTurn${t}" value="${t}" /></li>
												    </#if>
												  </#list>
											      
											      
											      <#if (totalInfo.pageTotal - maxPage > 1)>
											        ...
											      </#if>
											      
											      <#if (totalInfo.currentPage < totalInfo.pageTotal)>
											       <li class="footable-page">
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.pageTotal}" value="${totalInfo.pageTotal}" /></li>
											        <li class="footable-page-arrow">
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="<@spring.message 'next'/>" /></li>
											      </#if>
											      <input type="hidden" id="pageTotal" value="${totalInfo.pageTotal}">
											     </#if>
											    </div>
											  </div>
											</ul>
                                    </td>
                                </tr>
                            
						<!-- 列表分页模块结束 end -->
						</table>
                    </div>
  <div class="gccb">
	<div class="gccbqx">
		<a onclick="batchDeletion();"><@spring.message "batchdelete"/></a>
	</div>
	<div class="gccyx"><@spring.message "selectedgoods"/> <span id="Itemcounts">0</span> <@spring.message "piece"/></div>
	<div class="gccyx" ><@spring.message "Total"/>：<span id="pricecounts">0</span></div>
	</div>
        </div>
       
<!--查询详情表窗口 start -->
 <div class="row-fluid"  id='showShopCarWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
						<input type="hidden" value="" id='show_id'  name='id' >
							<div class="control-group form_lab_div">
								<label class="control-label">景序列号：</label>
								<label class="control-label" style="width: 6%" id='show_sceneid'></label>
								<label class="control-label">订购单类型：</label>
								<label class="control-label" style="width: 6%" id='show_ordertype'></label>
							</div>
							 
							<div class="control-group form_lab_div">
								<label class="control-label">产品级别：</label>
								<label class="control-label" style="width: 6%" id='show_productlevel'></label>
								<label class="control-label">产品号：</label>
								<label class="control-label" style="width: 6%" id='show_productid'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">卫星：</label>
								<label class="control-label" style="width: 6%"  id='show_satelliteid'></label>
									<label class="control-label">传感器：</label>
								<label class="control-label" style="width: 6%"  id='show_sensorid'></label>
							</div>
							 
							<div class="control-group form_lab_div">
								<label class="control-label">产品类型：</label>
								<label class="control-label" style="width: 6%"  id='show_producttype'></label>
								<label class="control-label">订购面积：</label>
								<label class="control-label" style="width: 6%"  id='show_area'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">产品价格：</label>
								<label class="control-label" style="width: 6%"  id='show_price'></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue"  onclick="hidenShopCar();">取消</button>
							</div>
						</form>
				</div>
</div>

<!-- 查询详情表窗口 end -->

<!-- 常量信息展示窗口 start -->
<ul id="constInfoWin" class="msg_Tip_box width600" style="display:none">
	<a class="mask_close pr10" href="javascript:$.unblockUI()"></a>
	<div id="constantValue" class="center tabDivInfo"  style="max-height:500px;overflow-y:auto;">
	  
	</div>
</ul>
<!-- 常量信息展示窗口 end -->

<!-- 常量信息修改窗口 start -->
 <div class="row-fluid"  id='showUpdateShopCarWin'  style="display: none;">
					<div class="">
						<form action="#" id="changeForm">
						<table width="500" border="0" class="cp_table">
  <tr>
    <td align="center"><B>景序列号：</B></td>
    <td align="center"><input type="text" style="height: 30px;"  id='update_sceneid'  name='sceneid'  /></td>
    </tr><tr>
    <td align="center"><B>订购单类型：</B></td>
    <td align="center"><input type="text" style="height: 30px;" id='update_ordertype'  name='ordertype'  /></td>
    </tr><tr>
    <td align="center"><B>产品级别：</B></td>
    <td align="center"><input type="text"  style="height: 30px;" id='update_productlevel'  name='productlevel'  /></td>
    </tr><tr>
     <td align="center"><B>产品号：</B></td>
    <td align="center"><input type="text"  style="height: 30px;" id='update_productid'  name='productid'  /></td>
    </tr><tr>
     <td align="center"><B>卫星：</B></td>
    <td align="center"><input type="text"  style="height: 30px;" id='update_satelliteid'  name='satelliteid'  /></td>
    </tr><tr>
     <td align="center"><B>传感器：</B></td>
    <td align="center"><input type="text" style="height: 30px;" id='update_sensorid'  name='sensorid'  /></td>
    </tr><tr>
     <td align="center"><B>产品类型：</B></td>
    <td align="center"><input type="text"  style="height: 30px;"  id='update_producttype'  name='producttype'  /></td>
    </tr><tr>
    <td align="center"><B>订购面积：</B></td>
    <td align="center"><input type="text" style="height: 30px;" id='update_area'  name='area'  /></td>
    </tr><tr>
    <td align="center"><B>产品价格：</B></td>
    <td align="center"><input type="text" style="height: 30px;"   id='update_price'  name='price'  /></td>
  </tr>
 <tr>
    <td align="center" colspan="2">
    <button type="button" class="btn blue" onclick="saveChange();">保存</button>
	<button type="button" class="btn blue"  onclick="hidenUpdateShopCar();">取消</button>
    </td>
  </tr>
</table>
						  
							
							 
						</form>
				</div>
</div>
<!-- 常量信息修改窗口 end -->
<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/service/pssshopcar.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

