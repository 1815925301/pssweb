<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">				
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
			                    		<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
			                    		<@spring.message "logtype"/>：
			                           <select  class="form-control" id="systemconfigtype" name="systemconfigtype" >
			                           	<option value="-1" selected="selected"><@spring.message "logchoose"/></option>
			                           	<#if gettype?? && (gettype?size > 0)>
							                <#list gettype as gettypes>
							                 <#if gettypes.logtype== 'error'>
								               <option value="${gettypes.logtype}" <#if type?? && type=gettypes.logtype>selected</#if>><@spring.message "logerror"/></option>
								             </#if>
								               <#if gettypes.logtype== 'info'> 
								               	<option value="${gettypes.logtype}" <#if type?? && type=gettypes.logtype>selected</#if>><@spring.message "loginfo"/></option>
								                </#if>
										 	</#list>
										 </#if>
			                           </select>
			                           &nbsp;
			                           	<@spring.message "logtime"/>：
			                           <input type="text" id="starttime" name ="starttime" value="${starttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                           <input type="text" id="endtime" name ="endtime"value="${endtime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                           <input  type="button" class="btn btcms" id="searchMatch" value=<@spring.message "logsearch"/>>
			                           <input  type="button" class="btn btcms" id="searchAll" value=<@spring.message "syssearchall"/>>
				</form>
			</div>		
				<!-- BEGIN PAGE CONTENT-->
			<table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center><@spring.message "logid"/></center></th>
			     				 <th><center><@spring.message "logtypee"/></center></th>
							     <th><center><@spring.message "logdetail"/></th>
							      <th><center><@spring.message "logcreattime"/></center></th>
							     <th><center><@spring.message "logleavel"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if getloglist?? && (getloglist?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list getloglist as log>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${num}</center></td>
								      <td>${log.logtype}</td>
								      <td>${log.description}</td>
								      <td>${log.createtime}</td>
								      <td>${log.loglevel}</td>
								     	 <#--<a class="btn blue" id="showLog_${log.id}"><i class="fa fa-train"></i>&nbsp;<@spring.message "logview"/></a>&nbsp;
											-->
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center"><@spring.message "lognoinformation"/></td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value=<@spring.message "logup"/> >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value=<@spring.message "logdown"/>></li>
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

<!-- 添加配置窗口 end -->

<!-- 配置信息展示窗口 end -->

<#include "foot.ftl">

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
