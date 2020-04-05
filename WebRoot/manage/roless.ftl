<#include "top.ftl">
	
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							${pageHanName}管理 <small></small>
						</h3>
						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="index.html">Home</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">Extra</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">Inbox</a></li>

						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box light-grey">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>${pageHanName}信息</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<!--搜索-->
							<div class="portlet-body">
								<div class="clearfix">
									<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										 角色名
		                                <input type="text" value="<#if roleName??>${roleName}</#if>" id="roleNameSearch" name="roleNameSearch" class="form-control" >
		                               <input  type="button" class="btn blue" id="searchMatch" value="搜索">
			                            <input  type="button" class="btn blue" id="searchAll" value="搜索全部">
									</form>
								</div>
								<!--搜索-->
							<div class="portlet-body">
								<div class="clearfix">
									<div class="btn-group">
										<button  class="btn blue" id="addNewRole">
										<i class="fa fa-plus"></i>&nbsp;新增 
										</button>
									</div>
									<div class="btn-group pull-right">
										<button class="btn blue" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>
										</button>
										<ul class="dropdown-menu pull-right">
											<li><a href="#">操作1</a></li>
											<li><a href="#">操作2</a></li>
											<li><a href="#">操作3</a></li>
										</ul>
									</div>
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center>序号</center></th>
			     				 <th><center>角色名称</center></th>
							     <th><center>创建时间</center></th>
							      <th><center>最后修改时间</center></th>
							     <th><center>动作</center></th>
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
								     	 <a class="btn blue" id="showRole_${role.id}"><i class="fa fa-train"></i>&nbsp;查看</a>&nbsp;
								     	 <#if role.editable == 1>
								     		 	<a class="btn blue" id="updateRole_${role.id}"><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;
								     			<a class="btn blue" id="removeRole_${role.id}" uname='${role.roleName}'><i class="fa fa-close"></i>&nbsp;删除</a>
								     			<a class="btn blue" id="assignAuth_${role.id}"><i class="fa fa-edit"></i>&nbsp;分配权限</a>
								     	 </#if>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center">暂无角色信息！</td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="上一页" >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="下一页" /></li>
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
</div>

<!--添加角色窗口 start -->
<div class="row-fluid"  id='addNewRoleWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label">角 色 名</label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='roleName'  name='roleName'  />
									<span class="help-inline" id='roleName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备 注</label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='note'  name='note'  />
									<span class="help-inline" id='note_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="save"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='cancleAdd'>取消</button>
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
							<div class="control-group form_lab_div">
								<label class="control-label">角 色 名</label>
								<label class="control-label" id='show_roleName'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">创建时间</label>
								<label class="control-label"  id='show_createTime'  ></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">创建用户</label>
								<label class="control-label"  id='show_createUserName' '></label>
							</div>
							<div class="control-group  form_lab_div">
								<label class="control-label">修改时间</label>
								<label class="control-label"  id='show_updateTime' ></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">修改用户</label>
								<label class="control-label"  id='show_updateUserName' ></label>
							</div>
							<div class="control-group form_lab_div">
									<label class="control-label">备注</label>
									<label class="control-label"  id='show_note' ></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'>取消</button>
							</div>
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
								<label class="control-label">角 色 名</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_roleName'  name='change_roleName'  />
									<input type="hidden"  class="m-wrap medium"  id='change_roleId'  name='change_roleId'  />
									<span class="help-inline" id='roleName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备 注</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_note'  name='change_note'  />
									<span class="help-inline"  id='note_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="saveChange"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='changecancleAdd'>取消</button>
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
								<button type="button" class="btn blue" id="saveChangeRoleAuth"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='cancleAuthAdd'>取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 修改角色权限窗口 end -->
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
