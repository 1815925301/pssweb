<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										<@spring.message "usernames"/>
		                                <input type="text" value="<#if userName??>${userName}</#if>" id="userNameSearch" name="userNameSearch" class="form-control" >
		                              	<@spring.message "userjigou"/>
		                               <select class="form-control" id="orgNameSearch" name="orgNameSearch"  >
									      <option value="-1" selected="selected"><@spring.message "choosejigou"/></option>
										  <#if orgList?? && (orgList?size > 0)>
							                <#list orgList as orgInfo>
										   	 <option value="${orgInfo.id}" <#if orgId?? && orgId=orgInfo.id>selected</#if>>${orgInfo.orgName}</option>
											</#list>
										  </#if>
										</select>
	                                    <input type="hidden" value="" id='orgIdSearch'  name='orgIdSearch' class="form-control">
		                               <@spring.message "email"/>
		                                <input type="text" value="<#if userEmail??>${userEmail}</#if>"  id="userEmailSearch" name="userEmailSearch" class="form-control">
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
										<button  class="btn btcms" id="addNewUser">
										<i class="fa fa-plus"></i>&nbsp; <@spring.message "add"/>
										</button>
										&nbsp;
										<button  class="btn btcms" id="checkUserisLock">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "ChEck"/>
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th width="3%"><input type="checkbox" id="allcheck"></th>
                                <th><center><@spring.message "num"/></center></th>
			     				 <th><center><@spring.message "username"/></center></th>
							     <th><center><@spring.message "email"/></center></th>
							      <th><center><@spring.message "realname"/></center></th>
							     <th><center><@spring.message "Affiliation"/></center></th>
							     <th><center><@spring.message "userstate"/></center></th>
							      <th><center><@spring.message "userrole"/></center></th>
							      <th><center><@spring.message "AuditUnit"/></center></th>
							     <th><center><@spring.message "active"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if usersList?? && (usersList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list usersList as userInfo>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								     <td class="botValue"><input type="checkbox" name="subcheck" value="${userInfo.userName}" id="${userInfo.id}"></td> 
								      <td><center>${num}</center></td>
								      <td>${userInfo.userName}</td>
								      <td>${userInfo.userEmail}</td>
								      <td>${userInfo.trueName}</td>
								      <td>${userInfo.orgName}</td>
								      <td>${userInfo.lockStr} </td>
								      <td>${userInfo.roleName}</td>
								      <#if userInfo.isLock ==0>
								      <td><@spring.message "ChEckpass"/></td>
								      </#if>
								      <#if userInfo.isLock ==2>
								      	<td><@spring.message "checknopass"/></td>
								      	</#if>
								       <#if userInfo.isLock ==1>
								      	<td><@spring.message "waitcheck"/></td>
								      	</#if>
								      <td>
								     	 <a class="btn btcms" onclick="showUser('${userInfo.id}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "view"/></a>&nbsp;
								     	 <#if userInfo.editable == 1>
								     		 	<a class="btn btcms" onclick="updateUser('${userInfo.id}')"><i class="fa fa-edit"></i>&nbsp;<@spring.message "edit"/></a>&nbsp;
								     			<a class="btn btcms" onclick="removeUser('${userInfo.id}','${userInfo.userName}')" ><i class="fa fa-close"></i>&nbsp;<@spring.message "userdelete"/></a>
								     	 </#if>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value=<@spring.message "useruppage"/>>
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
<div class="row-fluid"  id='addNewUserWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label"><@spring.message "usernames"/></label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='userName'  name='userName'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "realname"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='trueName'  name='trueName'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userlogincode"/></label>
								<div class="controls">
									<input type="password" value=""  class="m-wrap medium"  id='password' name='password' />
									<span class="help-inline"  id='password_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userconfirmcode"/></label>
								<div class="controls">
									<input type="password" value=""  class="m-wrap medium"  id='surepassword'  name='surepassword' />
									<span class="help-inline"  id='surepassword_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "email"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='userEmail' name='userEmail'/>
									<span class="help-inline"  id='userEmail_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Affiliation"/></label>
								<div class="controls">
									<select class="medium m-wrap" tabindex="1"  id='orgId'  name='orgId'>
											<option value="-1"  selected="selected"><@spring.message "choosejigou"/></option>
											<#if orgList?? && (orgList?size > 0)>
												<#list orgList as orgInfo>
													<option value="${orgInfo.id}" >${orgInfo.orgName}</option>
												</#list>
											</#if>
									</select>
									<span class="help-inline"  id='orgId_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userstate"/></label>
								<div class="controls">
									<select class="medium m-wrap" tabindex="1"  id='lockStr'  name='isLock'>
											<option value="0" selected="selected"><@spring.message "normal"/></option>
											<option value="0" ><@spring.message "userlock"/></option>
									</select>
								</div>
							</div>
							<div class="control-group">
									<label class="control-label"><@spring.message "userroles"/></label>
									<div class="controls">
										<select class="medium m-wrap" tabindex="1" id='roleId' name='roleId'>
												<option value="-1" selected="selected"><@spring.message "chooseroles"/></option>
													<#if rolesList?? && (rolesList?size > 0)>
														<#list rolesList as roleInfo>
															<option value="${roleInfo.id}">${roleInfo.roleName}</option>
														</#list>
													</#if>
										</select>
									</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveUser();"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加用户窗口 end -->

<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showUserWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "usernames"/></label>
								<label class="control-label" id='show_userName'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "realname"/></label>
								<label class="control-label"  id='show_trueName'  ></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "email"/></label>
								<label class="control-label"  id='show_userEmail' '></label>
							</div>
							<div class="control-group  form_lab_div">
								<label class="control-label"><@spring.message "Affiliation"/></label>
								<label class="control-label"  id='show_orgName' ></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "userstate"/></label>
								<label class="control-label"  id='show_lockStr' ></label>
							</div>
							<div class="control-group form_lab_div">
									<label class="control-label"><@spring.message "userroles"/></label>
									<label class="control-label"  id='show_roleName' ></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "usercanel"/></button>
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
						<input type="hidden" value="" id='change_id'  name='id' >
							<div class="control-group">
								<label class="control-label"><@spring.message "usernames"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_userName'  name='userName'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "realname"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_trueName'  name='trueName'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userlogincode"/></label>
								<div class="controls">
									<input type="password" value=""  class="m-wrap medium"  id='change_password' name='password' />
									<span class="help-inline"  id='password_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userconfirmcode"/></label>
								<div class="controls">
									<input type="password" value=""  class="m-wrap medium"  id='change_surepassword'  name='surepassword' />
									<span class="help-inline"  id='surepassword_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "email"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_userEmail' name='userEmail'/>
									<span class="help-inline"  id='userEmail_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Affiliation"/></label>
								<div class="controls">
									<select class="medium m-wrap" tabindex="1"  id='change_orgId'  name='orgId'>
											<#if orgList?? && (orgList?size > 0)>
												<#list orgList as orgInfo>
													<option value="${orgInfo.id}" >${orgInfo.orgName}</option>
												</#list>
											</#if>
									</select>
									<span class="help-inline"  id='orgId_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "userstate"/></label>
								<div class="controls">
									<select class="medium m-wrap" tabindex="1"  id='change_lockStr'  name='isLock'>
											<option value="0" selected="selected"><@spring.message "normal"/></option>
											<option value="1" ><@spring.message "userlock"/></option>
									</select>
								</div>
							</div>
							<div class="control-group">
									<label class="control-label"><@spring.message "userroles"/></label>
									<div class="controls">
										<select class="medium m-wrap" tabindex="1" id='change_roleId' name='roleId'>
													<#if rolesList?? && (rolesList?size > 0)>
														<#list rolesList as roleInfo>
															<option value="${roleInfo.id}">${roleInfo.roleName}</option>
														</#list>
													</#if>
										</select>
									</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChange();"><i class="icon-ok"></i><@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 用户信息修改窗口 end -->
<!-- 用户审核窗口 and -->
<div class="row-fluid"  id='userisLockWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="userForm">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "usernames"/></label>
								<label class="control-label" id='lockuserName'></label>
								<input type="hidden" id='lock_userName' name='userName'>
							</div>
							<div class="control-group form_lab_div">
									<label class="control-label"><@spring.message "checknote"/></label>
									<input type="text" id="remark" name='remark' />
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='updatelockUser("0")'><@spring.message "ChEckpass"/></button>
								<button type="button" class="btn blue" onclick='updatelockUser("2")'><@spring.message "checkfaile"/></button>
								<button type="button" class="btn blue" onclick='lockCancle()'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户审核窗口 end -->

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>



