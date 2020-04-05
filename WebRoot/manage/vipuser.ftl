<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
									<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
									 <@spring.message "FTPusername"/>:
		                                <input type="text" value="<#if cusernameInput??>${cusernameInput}</#if>" id="cusernameInput"  name="cusernameInput" >
		                              <@spring.message "customer_type"/>:
		                               <input type="text" value="<#if transtypeInput??>${transtypeInput}</#if>" id="transtypeInput"  name="transtypeInput" >
		                              	<@spring.message "Unit_name"/>:
		                               <input type="text" value="<#if companyInput??>${companyInput}</#if>" id="companyInput"  name="companyInput" >
		                                        
		                                
		                                
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
										<button  class="btn btcms" id="addvipuser">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "newadd"/>
										</button>
									</div>
									
								</div>
				<!-- BEGIN PAGE CONTENT-->
				
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
	                                <td width="4%"><@spring.message "logid"/></td>
							        <td width="8%"><@spring.message "Unit_name"/></td>
							        <td width="10%"><@spring.message "ipAddress"/></td>
							        <td width="5%"><@spring.message "Port_number"/></td>
							        <td width="8%"><@spring.message "FTPusername"/></td>
							        <td width="10%"><@spring.message "FTPpassword"/></td>
							        <td width="12%"><@spring.message "logcreattime"/></td>
							        <td width="6%"><@spring.message "customer_type"/></td>
							        <td><@spring.message "targetlocalpath"/></td>
							        <td width="7%"><@spring.message "newoption"/></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if ftpuserList?? && (ftpuserList?size > 0)>
				             	<#assign num = totalInfo.startNum>
				                <#list ftpuserList as ftpuser>
				               	<#assign num = num + 1>
				                <tr> 
							      <td><center>${num}</center></td>
							 		<td class="botValue">${ftpuser.company}</td>
							 		<td class="botValue">${ftpuser.ipaddress}</td>
							 		<td class="botValue">${ftpuser.port}</td>
							 		<td class="botValue">${ftpuser.cusername}</td>
							 		<td class="botValue">${ftpuser.cpassword}</td>
							 		<td class="botValue">${ftpuser.createtime}</td>
							 		<td class="botValue">${ftpuser.transtype}</td>
							 		<td class="botValue">${ftpuser.targetlocalpath}</td>
							 		<td class="botValue">
							       <a class="btn btcms" onclick="deleteVipuser('${ftpuser.id}','${ftpuser.company}')"><i class="fa fa-train"></i>&nbsp;删除</a>&nbsp;
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
        
<!--添加常量表窗口 start -->
<div class="row-fluid"  id='addNewvipuser' style="display:none">
		<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="" id='addNewvipuser'>
							<div class="">
								
								<div class="controls">
	&nbsp;&nbsp;&nbsp;<@spring.message "main_usertype"/>:  <input type="text" value=""  class=""  id='transtype_add'  name='transtype'  />
	&nbsp;&nbsp;<@spring.message "main_company"/>: <input type="text" value=""  class=""  id='company_add'  name='company'  />
								</div>
							</div>
							
							<div class="control-group">
								
								<div class="controls">
		<@spring.message "main_ipaddress"/>: <input type="text" value=""  class=""  id='ipaddress_add'  name='ipaddress'  />   
					<@spring.message "main_username"/>: <input type="text" value=""  class=""  id='cusername_add'  name='cusername'  />      
								</div>
							</div>
							<div class="control-group">
								
								<div class="controls">
				&nbsp;&nbsp;&nbsp;&nbsp;<@spring.message "port"/>:<input type="text" value=""  class=""  id='port_add'  name='port'  />
						&nbsp;<@spring.message "main_password"/>：<input type="text" value=""  class=""  id='cpassword_add'  name='cpassword'  />	
									
								</div>
							</div>
						
							<div class="control-group">
							
								<div class="controls">
						<@spring.message "targetlocalpath"/> ：<select class="" id="path1" name="path1" style="width: 170px;" >
									      	<option value="" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="satelliteid"><@spring.message "satellite"/></option>
										   	 <option value="sensorid"><@spring.message "sensorid"/></option>
										   	 <option value="scenedate"><@spring.message "collectTime"/></option>
										   	 <option value="productlevel"><@spring.message "productlevel"/></option>
										   	 <option value="producttype"><@spring.message "producttype"/></option>
										</select> 
								<select class="" id="path2" name="path2" style="width: 170px;" >
									      	<option value="" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="satelliteid"><@spring.message "satellite"/></option>
										   	 <option value="sensorid"><@spring.message "sensorid"/></option>
										   	 <option value="scenedate"><@spring.message "collectTime"/></option>
										   	 <option value="productlevel"><@spring.message "productlevel"/></option>
										   	 <option value="producttype"><@spring.message "producttype"/></option>
										</select>
								<select class="" id="path3" name="path3" style="width: 170px;" >
									      	<option value="" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="satelliteid"><@spring.message "satellite"/></option>
										   	 <option value="sensorid"><@spring.message "sensorid"/></option>
										   	 <option value="scenedate"><@spring.message "collectTime"/></option>
										   	 <option value="productlevel"><@spring.message "productlevel"/></option>
										   	 <option value="producttype"><@spring.message "producttype"/></option>
										</select>
								<select class="" id="path4" name="path4" style="width: 170px;" >
									      	<option value="" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="satelliteid"><@spring.message "satellite"/></option>
										   	 <option value="sensorid"><@spring.message "sensorid"/></option>
										   	 <option value="scenedate"><@spring.message "collectTime"/></option>
										   	 <option value="productlevel"><@spring.message "productlevel"/></option>
										   	 <option value="producttype"><@spring.message "producttype"/></option>
										</select>
								<select class="" id="path5" name="path5" style="width: 170px;" >
									      	<option value="" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="satelliteid"><@spring.message "satellite"/></option>
										   	 <option value="sensorid"><@spring.message "sensorid"/></option>
										   	 <option value="scenedate"><@spring.message "collectTime"/></option>
										   	 <option value="productlevel"><@spring.message "productlevel"/></option>
										   	 <option value="producttype"><@spring.message "producttype"/></option>
										</select>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="savevipuser();"><i class="icon-ok"></i><@spring.message "configbaocun"/></button>
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "configcancel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>

<!--添加常量表窗口 end -->
<!-- 常量信息修改窗口 start -->
<ul id="showProduct" class="msg_Tip_box width500" style="display:none">
	 
</ul>
<!-- 常量信息修改窗口 end -->

<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

