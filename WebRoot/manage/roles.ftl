<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										 <@spring.message "rolename"/>
		                                <input type="text" value="<#if roleName??>${roleName}</#if>" id="roleNameSearch" name="roleNameSearch" class="form-control" >
			                           &nbsp; <input  type="button" class="btn btcms" id="searchMatch" value=<@spring.message "search"/>>
			                            <input  type="button" class="btn btcms" id="searchAll" value=<@spring.message "searchAll"/>>
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
										<button  class="btn btcms" id="addNewRole">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "add"/>
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center><@spring.message "num"/></center></th>
			     				 <th><center><@spring.message "rolename"/></center></th>
							     <th><center><@spring.message "creattime"/></center></th>
							      <th><center><@spring.message "lastmodifytime"/></center></th>
							     <th><center><@spring.message "active"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if rolesList?? && (rolesList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list rolesList as role>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${num}</center></td>
								      <td>${role.roleName}</td>
					
								      <td>${role.createTime}</td>
								      <td>${role.updateTime}</td>
								      <td>
								     	       <a class="btn btcms" id="showRole_${role.id}"><i class="fa fa-train"></i>&nbsp;<@spring.message "view"/></a>&nbsp;
								     		 	<a class="btn btcms" id="updateRole_${role.id}"><i class="fa fa-edit"></i>&nbsp;<@spring.message "edit"/></a>&nbsp;
								     			<a class="btn btcms" id="removeRole_${role.id}" uname='${role.roleName}'><i class="fa fa-close"></i>&nbsp;<@spring.message "userdelete"/></a>
								     			<a class="btn btcms" id="assignAuth_${role.id}"><i class="fa fa-edit"></i>&nbsp;<@spring.message "assignPermissions"/></a>
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



<!--添加角色窗口 start -->
<div class="row-fluid"  id='addNewRoleWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label"><@spring.message "rolename"/></label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='roleName'  name='roleName'  />
									<span class="help-inline" id='roleName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "rolesnote"/></label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='note'  name='note'  />
									<span class="help-inline" id='note_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="save"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加角色窗口 end -->

<!-- 角色信息展示窗口 start -->
<div class="row-fluid"  id='showRoleWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<table class="tableWin" border="1">
								<tr>
									<td class='leftTd' width='40%'><@spring.message "rolename"/>：</td><td width='60%'><label id="show_roleName"></label></td></tr>
									<tr><td class='leftTd'><@spring.message "creattime"/>：</td><td><label id="show_createTime"></label></td></tr>
									<tr><td class='leftTd'><@spring.message "rolescreateuser"/>：</td><td><label id="show_createUserName"></label></td></tr>
									<tr><td class='leftTd'><@spring.message "modifytime"/>：</td><td><label id="show_updateTime"></label></td></tr>
									<tr><td class='leftTd'><@spring.message "modifyuser"/>：</td><td><label id="show_updateUserName"></label></td></tr>
									<tr><td class='leftTd'><@spring.message "rolesnote"/>：</td><td><label id="show_note"></label></td></tr>
							  </table>
						</form>
				</div>
</div>
<!-- 角色信息展示窗口 end -->

<!-- 角色信息修改窗口 start -->
<div class="row-fluid"  id='changeRoleWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group">
								<label class="control-label"><@spring.message "rolename"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_roleName'  name='change_roleName'  />
									<input type="hidden"  class="m-wrap medium"  id='change_roleId'  name='change_roleId'  />
									<span class="help-inline" id='roleName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "rolesnote"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_note'  name='change_note'  />
									<span class="help-inline"  id='note_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="saveChange"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 修改角色权限窗口 end -->
<div class="row-fluid"  id='changeRoleAuthWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<table class="tableWin" border="1" id="changeRoleAuthTable">
	  	 <tr><td colspan="5" class="top"><input type="hidden" value="" id="change_auth_role_id" /></td></tr>
	                  </table>
							<div class="form-actions">
								<button type="button" class="btn blue" id="saveChangeRoleAuth"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='cancleAuthAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 修改角色权限窗口 end -->
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

