<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										父ID
		                                <input type="text" value="<#if fid??>${fid}</#if>" id="fidSearch" name="fidSearch" class="form-control" >
			                           &nbsp; 
			                    		中文描述
		                                <input type="text" value="<#if nameCn??>${nameCn}</#if>" id="nameCnSearch" name="nameCnSearch" class="form-control" >
			                           &nbsp;
			                    		英文描述
		                                <input type="text" value="<#if nameEn??>${nameEn}</#if>" id="nameEnSearch" name="nameEnSearch" class="form-control" >
			                           &nbsp;
			                    		URL
		                                <input type="text" value="<#if url??>${url}</#if>" id="urlSearch" name="urlSearch" class="form-control" >
			                           &nbsp;
			                           <input  type="button" class="btn btcms" id="searchMatchs" value="搜索">
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
										<button  class="btn btcms" id="addSysUrlManage">
										<i class="fa fa-plus"></i>&nbsp;新增 
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center>id</center></th>
			     				 <th><center>父ID</center></th>
							     <th><center>中文描述</center></th>
							     <th><center>英文描述</center></th>
							     <th><center>URL</center></th>
							     <th><center>备注</center></th>
							     <#--<th><center>操作</center></th>-->
                                </tr>
                            </thead>
                            <tbody>
                               <#if urlList?? && (urlList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list urlList as urlList>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${urlList.id}</center></td>
								      <td>${urlList.fid}</td>
								      <#--<td>${urlList.nameCn}</td>-->
								   
								       
								       <#if urlList.nameCn == "系统管理">
								       <td><a href="${ctx}${urlList.url}">系统管理</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "日志管理">
								       <td><a href="${ctx}${urlList.url}">日志管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "用户管理">
								       <td><a href="${ctx}${urlList.url}">用户管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "角色管理">
								       <td><a href="${ctx}${urlList.url}">角色管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "配置管理">
								       <td><a href="${ctx}${urlList.url}">配置管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "资源管理">
								       <td><a href="${ctx}${urlList.url}">资源管理</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "产品管理">
								       <td><a href="${ctx}${urlList.url}">产品管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "二维查询">
								       <td><a href="${ctx}${urlList.url}">二维查询</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "采集单">
								       <td><a href="${ctx}${urlList.url}">采集单</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "采集单管理">
								       <td><a href="${ctx}${urlList.url}">采集单管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "购物车">
								       <td><a href="${ctx}${urlList.url}">购物车</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "购物车管理">
								       <td><a href="${ctx}${urlList.url}">购物车管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "订单">
								       <td><a href="${ctx}${urlList.url}">订单</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "订单管理">
								       <td><a href="${ctx}${urlList.url}">订单管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "统计分析">
								       <td><a href="${ctx}${urlList.url}">统计分析</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "用户分类统计">
								       <td><a href="${ctx}${urlList.url}">用户分类统计</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "订单分类统计">
								       <td><a href="${ctx}${urlList.url}">订单分类统计</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "数据下载量统计">
								       <td><a href="${ctx}${urlList.url}">数据下载量统计</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "订单付费统计">
								       <td><a href="${ctx}${urlList.url}">订单付费统计</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "会员">
								       <td><a href="${ctx}${urlList.url}">会员</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "会员管理">
								       <td><a href="${ctx}${urlList.url}">会员管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "产品价格">
								       <td><a href="${ctx}${urlList.url}">产品价格</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "产品价格管理">
								       <td><a href="${ctx}${urlList.url}">产品价格管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "版本更新">
								       <td><a href="${ctx}${urlList.url}">版本更新</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "版本更新管理">
								       <td><a href="${ctx}${urlList.url}">版本更新管理</a></td>
								       </#if>
								       
								       <#if urlList.nameCn == "系统监控">
								       <td><a href="${ctx}${urlList.url}">系统监控</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "订单跟踪管理">
								       <td><a href="${ctx}${urlList.url}">订单跟踪管理</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "用户更新设置">
								       <td><a href="${ctx}${urlList.url}">用户更新设置</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "VIP用户管理">
								       <td><a href="${ctx}${urlList.url}">VIP用户管理</a></td>
								       </#if>
								       
								        <#if urlList.nameCn == "新闻管理">
								       <td><a href="${ctx}${urlList.url}">新闻管理</a></td>
								       </#if>
								       
									  <#if urlList.nameCn == "友好提示">
								       <td><a href="${ctx}${urlList.url}">友好提示</a></td>
								       </#if>
								       
								       
								      <td>${urlList.nameEn}</td>
								      <td>${urlList.url}</td>
								      <td>${urlList.remark}</td>
								      <#--<td>
								     	 <a class="btn btcms" onclick="showsystemConfig('${urlList.id}')"><i class="fa fa-train"></i>&nbsp;查看</a>&nbsp;
								     		 	<a class="btn btcms" onclick="updatesystemConfig('${urlList.id}')"><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;
								     			<a class="btn btcms" onclick="removesystemConfig('${urlList.id}')" ><i class="fa fa-close"></i>&nbsp;删除</a>
								      </td>-->
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+2}" value="下一页" /></li>
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
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addSystemForm'>
							<input type="hidden" value="" id='config_id_add'  name='configid' >
							<div class="control-group">
								<label class="control-label">配置名称</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='config_key_add'  name='configkey'  />
									<span class="help-inline"  id='config_key_'></span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">配置值</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='config_value_add'  name='configvalue'  />
									<span class="help-inline"  id='config_value_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配置DES</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configd_ess_add'  name='configdes'  />
									<span class="help-inline"  id='configd_ess_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">创建时间</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='create_time_add'  name='createtime'  />
									<span class="help-inline"  id='create_time_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配置类型</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='config_type_add'  name='configtype'  />
									<span class="help-inline"  id='config_type_'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveSystemConfig();"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='cancleAdd'>取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加用户窗口 end -->

<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showsystemconfig'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label">配置名字</label>
								<label class="control-label"  id='config_key'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label">配置值</label>
								<label class="control-label"  id='config_value'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label">配置DES</label>
								<label class="control-label"  id='configd_ess'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label">创建时间</label>
								<label class="control-label"  id='create_time'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label">配置类型</label>
								<label class="control-label"  id='config_type'></label>
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
						<input type="hidden" value="" id='configid_modify'  name='configid' >
							<div class="control-group">
								<label class="control-label">配置名字</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='configname_modify'  name='configkey'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配置值</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configvalue_modify'  name='configvalue'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配置DES</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configdes_modify'  name='configdes'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">创建时间</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='createtime_modify'  name='createtime'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配置类型</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='configtype_modify'  name='configtype'  />
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
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
