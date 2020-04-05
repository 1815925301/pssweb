<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										学生姓名
		                                <input type="text" value="<#if name??>${name}</#if>" id="userNameSearch" name="userNameSearch" class="form-control" >
			                           &nbsp; <input  type="button" class="btn btcms" id="searchMatch" value="搜索">
			                            <input  type="button" class="btn btcms" id="searchAll" value="搜索全部">
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
										<i class="fa fa-plus"></i>&nbsp;新增 
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center>序号</center></th>
			     				 <th><center>学生姓名</center></th>
							     <th><center>学生年龄 </center></th>
							     <th><center>操作</center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if studentList?? && (studentList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list studentList as studentInfo>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${num}</center></td>
								      <td>${studentInfo.name}</td>
								      <td>${studentInfo.age}</td>
								      <td>
								     	 <a class="btn btcms" onclick="showUser('${studentInfo.id}')"><i class="fa fa-train"></i>&nbsp;查看</a>&nbsp;
								     		 	<a class="btn btcms" onclick="updateUser('${studentInfo.id}')"><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;
								     			<a class="btn btcms" onclick="removeUser('${studentInfo.id}','${studentInfo.name}')" ><i class="fa fa-close"></i>&nbsp;删除</a>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center">暂无用户信息！</td>
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

<!-- 添加用户窗口 start -->
<div class="row-fluid"  id='addNewUserWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label">学生姓名</label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id='userName'  name='userName'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学生年龄</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='trueName'  name='trueName'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveUser();"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='cancleAdd'>取消</button>
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
								<label class="control-label">学生姓名</label>
								<label class="control-label" id='show_userName'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">学生年龄</label>
								<label class="control-label"  id='show_trueName'  ></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'>取消</button>
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
								<label class="control-label">学生姓名</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_userName'  name='userName'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学生年龄</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_trueName'  name='trueName'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChange();"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='changecancleAdd'>取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 用户信息修改窗口 end -->

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>


