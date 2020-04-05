<#include "top.ftl">
 

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
									<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
									 <@spring.message "versionnum"/>:
		                                <input type="text" value="<#if versionnumInput??>${versionnumInput}</#if>" id="versionnumInput"  name="versionnumInput" >
			                           &nbsp; <input  type="button" class="btn btcms" id="searchMatch" value="<@spring.message 'logsearch'/>">
			                            <input  type="button" class="btn btcms" id="searchAll" value="<@spring.message 'syssearchall'/>">
									</form>
			</div>
			<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body">
								<div class="clearfix">
									<div class="btn-group">
										<button  class="btn btcms" id="addNewProductprice">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "newadd"/> 
										</button>
									</div>
									
								</div>
				<!-- BEGIN PAGE CONTENT-->
				
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
	                                <td width="5%"><@spring.message "logid"/></td>
							        <td width="10%"><@spring.message "versionnum"/></td>
							        <td width="10%"><@spring.message "Audit_status"/></td>
							        <td width="10%"><@spring.message "isornoteffect"/></td>
							        <td width="10%"><@spring.message "checktime"/></td>
							        <td width="10%"><@spring.message "Audit_person"/></td> 
							        <td width="10%"><@spring.message "checknote"/></td>
							         <td width="10%"><@spring.message "configoption"/></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if vlist?? && (vlist?size > 0)>
				             	<#assign num = totalInfo.startNum>
				                <#list vlist as v>
				               	<#assign num = num + 1>
				                <tr> 
							      <td><center>${num}</center></td>
							 		<td class="botValue">${v.versionnum}</td>
							 		
							 		<#if v.isaudit ==1 >
							 		<td class="botValue"><@spring.message "Audit_failure"/></td>
							 		</#if>
							 		<#if v.isaudit ==2 >
							 		<td class="botValue"><@spring.message "Audit_pass"/></td>
							 		</#if>
							 		<#if v.isaudit ==3 >
							 		<td class="botValue"><@spring.message "Waitfor_audit"/></td>
							 		</#if>
							 		
							 		<#if v.isornoteffect ==1 >
							 		<td class="botValue"><@spring.message "newyes"/></td>
							 		</#if>
							 		<#if v.isornoteffect ==2 >
							 		<td class="botValue"><@spring.message "newno"/></td>
							 		</#if>
							 		
							 		
							 	
							 		<td class="botValue">${v.checktime}</td>
							 		<td class="botValue">${v.checkname}</td>
							 		<td class="botValue">${v.note}</td>
							      <td class="botValue">
							      <#if v.isaudit !=2>
							      <a class="btn btcms" onclick="checkProductprice('${v.versionnum}')" id="checkprice"><i class="fa fa-train"></i>&nbsp;<@spring.message "audit"/></a>&nbsp;
							      </#if>
							       <a class="btn btcms" onclick="showProductprice('${v.versionnum}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "logview"/></a>&nbsp;
							       <#if v.isaudit ==2 && v.isornoteffect==2>
							       <a class="btn btcms" onclick="releaseprice('${v.versionnum}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "releaseprice"/></a>&nbsp;
							       </#if>
							       <#if v.isaudit ==2 && v.isornoteffect==1>
							       <a class="btn btcms" onclick="cancelrelease('${v.versionnum}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "cancelrelease"/></a>&nbsp;
							       </#if>
							      </td>
							    </tr>
							    </#list>
				              <#else>
				                <tr>  
							      <td class="botValue" colspan="6" style="padding:10px 0;"><@spring.message "nomore"/></td>
							    </tr>
				              </#if>
						</tbody>
						<!--table 表格结束-->
							<!-- 列表分页模块开始 start -->
				           <tfoot>
                                <tr>
                                    <td colspan="8">
                                   		 <div class="span6"><div class="dataTables_info" id="sample_1_info"></div></div>
                                   		 <div class="span6">
										<div class="dataTables_paginate paging_bootstrap pagination">
                                     	<ul>
											     <#if (totalInfo.totalCount > 0)>
											      <#if (totalInfo.currentPage > 1)>
											      	<li class="prev disabled">
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="<@spring.message 'configup'/>" >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="<@spring.message 'configdown'/>" /></li>
											      </#if>
											      <input type="hidden" id="pageTotal" value="${totalInfo.pageTotal}">
											     </#if>
											    </div>
											  </div>
											</ul>
                                    </td>
                                </tr>
                            </tfoot>
						<!-- 列表分页模块结束 end -->
						</table>
                    </div>
                </div>
            </div>
        </div>

<ul id="showProduct" class="msg_Tip_box width500" style="display:none">
	 
</ul>
<!-- 常量信息修改窗口 end -->


<!-- 常量信息修改窗口 start -->
<ul id="checkProduct" class="msg_Tip_box width500" style="display:none">
	 <div class="tab-content">
						<form action="#" class="form-horizontal" id="priceForm">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "priceinfo"/></label>
								<label class="control-label" id='versionnum_ve'></label>
								<input type="hidden" name="versionnum" id="versionnum_id">
							</div>
								<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "checknote"/></label>
								
								<input type="text" name="note" id="checknote">
							</div>
							
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='updateCheck("2")'><@spring.message "Audit_pass"/></button>
								<button type="button" class="btn blue" onclick='updateCheck("1")'><@spring.message "Audit_failure"/></button>
								<button type="button" class="btn blue" onclick='stateCancle()'><@spring.message "configcancel"/></button>
							</div>
						</form>
				</div>
</ul>
<!-- 常量信息修改窗口 end -->

<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
