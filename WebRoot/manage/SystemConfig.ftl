<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input  type="button" Style = "margin-left:965px; " class="open" id="sopen" value=  <@spring.message "configxianshi"/>></input>
										 <input  type="button" Style = "margin-left:965px;  DISPLAY: none;" class="sclostyh" id="sclostyh" value=<@spring.message "configclose"/>></input>
										<div id="searchdisable"  style="DISPLAY: none;">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										<@spring.message "configname"/>
		                                <input type="text" value="<#if name??>${name}</#if>" id="SystemConfigNameSearch" name="SystemConfigNameSearch" class="form-control" >
			                           &nbsp; 
			                    		<@spring.message "configtype"/>
			                           <select  class="form-control" id="systemconfigtype" name="systemconfigtype" >
			                           	<option value="-1" selected="selected"><@spring.message "configchoose"/></option>
			                           	<#if systemtypeList?? && (systemtypeList?size > 0)>
							                <#list systemtypeList as sysTypelist>
								                 <#if sysTypelist.configtype==0>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "conficonfi"/></option>
								                  </#if>
								                 <#if sysTypelist.configtype==1>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "configmanagepei"/></option>
								                 </#if>
								                 <#if sysTypelist.configtype==2>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "contentpeizhi"/></option>
										   		</#if>
										 	</#list>
										 </#if>
			                           </select>	
			                           &nbsp;
			                           	<@spring.message "configtime"/>
			                           <input type="text" id="starttime" name ="starttime" value="${Starttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                           <input type="text" id="endtime" name ="endtime"value="${enttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                           <input  type="button" class="btn btcms" id="searchMatch" value=<@spring.message "configsousuo"/>>
			                           <input  type="button" class="btn btcms" id="searchAll" value=<@spring.message "configsearchall"/>>
								</div>
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
										<button >
										<a href="${ctx}/SystemConfigAdd.do">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "configadd"/> 
										</a>
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center><@spring.message "configid"/></center></th>
			     				 <th><center><@spring.message "configname"/></center></th>
							     <th style="width: 20px;"><center><@spring.message "configvalue"/></center></th>
							     <th><center><@spring.message "configdiscrib"/></center></th>
							     <th><center><@spring.message "configtime"/></center></th>
							     <th><center><@spring.message "configtype"/></center></th>
							     <th><center><@spring.message "configoption"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if systemConfigList?? && (systemConfigList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list systemConfigList as systemConfigListInfo>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${systemConfigListInfo.configid}</center></td>
								      <td>${systemConfigListInfo.configkey}</td>
								      <td>${systemConfigListInfo.configvalue}</td>
								      <td>${systemConfigListInfo.configdes}</td>
								      <td>${systemConfigListInfo.createtime}</td>
								      <#if systemConfigListInfo.configtype==0>
								       <td><@spring.message "conficonfi"/></td>
								       </#if>
								        <#if systemConfigListInfo.configtype==1>
								       <td><@spring.message "configmanagepei"/></td>
								       </#if>
								        <#if systemConfigListInfo.configtype==2>
								       <td><@spring.message "contentpeizhi"/></td>
								       </#if>
								      <td>
								     	 <a class="btn btcms" onclick="showsystemConfig('${systemConfigListInfo.configid}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "configview"/></a>&nbsp;
								     		 	<a class="btn btcms" onclick="updatesystemConfig('${systemConfigListInfo.configid}')"><i class="fa fa-edit"></i>&nbsp;<@spring.message "configedit"/></a>&nbsp;
								     			<a class="btn btcms" onclick="removesystemConfig('${systemConfigListInfo.configid}','${systemConfigListInfo.name}')" ><i class="fa fa-close"></i>&nbsp;<@spring.message "configdelete"/></a>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center"><@spring.message "configinformation"/></td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value= <@spring.message "configup"/> >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value=<@spring.message "configdown"/> /></li>
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

<!-- 添加用户窗口 start -->
<div class="row-fluid"  id='addNewSystemConfig'  style="display:none">
			<div class="portlet-body form">
						<form action="#"  name="payForm"   class="form-horizontal" id='addSystemForm'>
							<input type="hidden"  id='config_id_add'  name='configid' >
							<div class="control-group">
								<label class="control-label"><@spring.message "configname"/></label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="config_key_add"  name="configkey"/>
									<span class="help-inline"  id='config_key_'></span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "configvalue"/></label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="config_value_add"  name="configvalue"/>
									<span class="help-inline"  id='config_value_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configdiscrib"/></label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="configd_ess_add"  name="configdes"/>
									<span class="help-inline"  id='configd_ess_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configtime"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id="create_time_add"  name="createtime"   onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									<span class="help-inline"  id='create_time_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configtype"/></label>
								<div class="controls">
									<select  class="form-control" id="configtype_add" name="configtype">
			                           	<option value="-1" selected="selected"><@spring.message "configchoose"/></option>
			                           	<#if systemtypeList?? && (systemtypeList?size > 0)>
							                <#list systemtypeList as sysTypelist>
								                 <#if sysTypelist.configtype==0>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "conficonfi"/></option>
								                  </#if>
								                 <#if sysTypelist.configtype==1>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "configmanagepei"/></option>
								                 </#if>
								                 <#if sysTypelist.configtype==2>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "contentpeizhi"/></option>
										   		</#if>
										 	</#list>
										 </#if>
			                           </select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "configimage"/>：</label>
									 <input type="file" name="photo" id="fileToUpload"></input> 
									 
							</div>
							
							
							<div class="form-actions">
							  	<button type="button" class="btn blue" onclick="saveSystemConfig();"><i class="icon-ok"></i> <@spring.message "configbaocun"/></button>
						<!--	<button type="button" class="btn blue" id="submit"><i class="icon-ok"></i> 保存</button>-->
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "configcancel"/></button>
							</div>
						</form>
	 		</div>
</div>
<!-- 添加用户窗口 end -->

<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showsystemconfig'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "configname"/></label>
								<label class="control-label"  id='config_key'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "configvalue"/></label>
								<label class="control-label"  id='config_value'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "configdiscrib"/></label>
								<label class="control-label"  id='configd_ess'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "configtime"/></label>
								<label class="control-label"  id='create_time'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "configtype"/></label>
								<label class="control-label"  id='config_type'></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "configcancel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户信息展示窗口 end -->

<!-- 用户信息修改窗口 start -->
<div class="row-fluid"  id='changeUserWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changeForm">
						<input type="hidden" value="" id='configid_modify'  name='configid' >
							<div class="control-group">
								<label class="control-label"><@spring.message "configname"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='configname_modify'  name='configkey'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configvalue"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configvalue_modify'  name='configvalue'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configdiscrib"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configdes_modify'  name='configdes'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configtime"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='createtime_modify'  name='createtime'  onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configtype"/></label>
								<div class="controls">
									<#--<input type="text" value=""  class="m-wrap medium"  id='configtype_modify'  name='configtype'  />
									<span class="help-inline"  id='trueName_error'></span>
									-->
									<select  class="form-control" id="configtype_add" name="configtype" >
			                           	<option value="-1" selected="selected"><@spring.message "configchoose"/></option>
			                           	<#if systemtypeList?? && (systemtypeList?size > 0)>
							                <#list systemtypeList as sysTypelist>
								                 <#if sysTypelist.configtype==0>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "conficonfi"/></option>
								                  </#if>
								                 <#if sysTypelist.configtype==1>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "configmanagepei"/></option>
								                 </#if>
								                 <#if sysTypelist.configtype==2>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>><@spring.message "contentpeizhi"/></option>
										   		</#if>
										 	</#list>
										 </#if>
			                           </select>
								</div>
							</div>
							
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChange();"><i class="icon-ok"></i><@spring.message "configbaocun"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "configcancel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 用户信息修改窗口 end -->
<script src="js/common.js" type="text/javascript"></script>
<#include "foot.ftl">
<#include "style.ftl">

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>


