<#include "top.ftl">
	
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >	
		                               <@spring.message "noteinformation"/>：&nbsp;&nbsp;&nbsp;
		                                <input type="text" value="<#if des??>${des}</#if>"  id="desSearch" name="desSearch" class="form-control">
			                           &nbsp; <input  type="button" class="btn btcms" id="searchMatch" value=<@spring.message "search"/>>
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
										<button  class="btn btcms" id="addNewPrompt">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "add"/>
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center><@spring.message "key"/></center></th>
			     				 <th><center><@spring.message "value"/></center></th>
							     <th><center><@spring.message "noteinformation"/></center></th>
							     <th><center><@spring.message "option"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if promptList?? && (promptList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list promptList as prompt>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${prompt.key}</center></td>
								      <td><center>${prompt.value}</center></td>
								     <td><center>${prompt.des}</center></td>
								      <td><center>
								     	 <a class="btn btcms" onclick="showPrompt('${prompt.id}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "view"/></a>&nbsp;
								     		 	<a class="btn btcms" onclick="updatePrompt('${prompt.id}')"><i class="fa fa-edit"></i>&nbsp;<@spring.message "modify"/></a>&nbsp;
								     		 	</center>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center"><@spring.message "nouserinformation"/></td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value=<@spring.message "useruppage"/> >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value=<@spring.message "userdownpage"/> /></li>
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
<div class="row-fluid"  id='addNewPromptWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addPromptForm'>
							<input type="hidden" value="" id='id_add'  name='id' >
							<div class="control-group">
								<label class="control-label"><@spring.message "key"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='key_add'  name='key'  />
									<span class="help-inline"  id='config_key_'></span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "value"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='value_add'  name='value'  />
									<span class="help-inline"  id='config_value_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "describ"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='des_add'  name='des'  />
									<span class="help-inline"  id='config_des_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "creattime"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='createtime_add'  name='createtime'  onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									<span class="help-inline"  id='config_type_'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="savePrompt();"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加用户窗口 end -->


<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showPromptWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
						<input type="hidden" value="" id='show_id'  name='id' >
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "key"/></label>
								<label class="control-label"  id='show_key' '></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "value"/></label>
								<label class="control-label" id='show_value'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "noteinformation"/></label>
								<label class="control-label"  id='show_des'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "creattime"/></label>
								<label class="control-label" id='show_createtime'></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户信息展示窗口 end -->

<!-- 用户信息修改窗口 start -->
<div class="row-fluid"  id='changePromptWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changepaForm">
						<input type="hidden" value="" id='change_id'  name='id' >
							<div class="control-group">
								<label class="control-label"><@spring.message "key"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_key'  name='key'  />
									<span class="help-inline" id='key_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "value"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_value'  name='value'  />
									<span class="help-inline"  id='value_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "noteinformation"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_des' name='des' />
									<span class="help-inline"  id='des_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChange();"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 用户信息修改窗口 end -->

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>


