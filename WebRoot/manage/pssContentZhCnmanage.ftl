<#include "top.ftl">
<#include "style.ftl">	
<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
										 <input  type="button" Style = "margin-left:890px; " class="open" id="sopen" value=<@spring.message "newopen"/>></input>
										 <input  type="button" Style = "margin-left:890px;  DISPLAY: none;" class="sclostyh" id="sclostyh" value=<@spring.message "newclose"/>></input>
										 <br> 
										<div id="searchdisable"  style="DISPLAY: none;">
										<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
										<@spring.message "newtitle"/>：
		                                <input type="text" value="<#if name??>${name}</#if>" id="ContentNameSearch" name="ContentSearch" class="form-control" >
			                           &nbsp; 
			                           	<@spring.message "newcontent"/>：
			                             <select class="form-control" id="contentTypeSearch" name="contentTypeSearch"  >
									      <option value="-1" selected="selected"><@spring.message "newchoose"/></option>
									      	<#if getcontentType?? && (getcontentType?size > 0)>
							                <#list getcontentType as contentType>
								                 <#if contentType.contenttype==1>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "news"/></option>
								                  </#if>
								                 <#if contentType.contenttype==2>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "newproduct"/></option>
								                 </#if>
										 	</#list>
										 </#if>
										</select>
										 &nbsp; 
										<@spring.message "newisfabu"/>：
										<select class="form-control"  id="contentisreleaseSearch" name="contentisreleaseSearch" >
										 <option value="-1" selected="selected"><@spring.message "newchoose"/></option>
											 <#if getisreleaseget?? && (getisreleaseget?size > 0)>
								                <#list getisreleaseget as getisreleaseget>
									                 <#if getisreleaseget.isrelease==1>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newyes"/></option>
									                  </#if>
									                 <#if getisreleaseget.isrelease==2>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newno"/></option>
									                 </#if>
											 	</#list>
											 </#if>
										</select>
										 &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;
										 <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
										<input type="text" id="starttime" name ="starttime" value="${Starttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                            <input type="text" id="endtime" name ="endtime"value="${enttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                        
			                          <input  type="button" class="btn btcms" id="searchMatch" value=<@spring.message "newsearch"/>>
			                          <input  type="button" class="btn btcms" id="searchAll" value=<@spring.message "newsearchall"/>>
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
										<button  class="btn btcms" id="addContent">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "newadd"/> 
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                <th><center><@spring.message "newid"/></center></th>
			     				 <th><center><@spring.message "newtitle"/></center></th>
							     <th><center><@spring.message "newcontents"/></center></th>
							     <th><center><@spring.message "newisfabu"/></center></th>
							      <th><center><@spring.message "newcontent"/></center></th>
							     <th><center><@spring.message "newpushtime"/></center></th>
							     <th><center><@spring.message "newimage"/></center></th>
							     <th><center><@spring.message "newoption"/></center></th>
							     <th><center></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if contentList?? && (contentList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list contentList as contentList>
								    <#assign num = num + 1>
								    <tr class="gradeA">
								      <td><center>${contentList.contentid}</center></td>
								      <td>${contentList.contenttitle}</td>
								      <td>${contentList.content}</td>
								       <#if contentList.isrelease==1>
								       <td><@spring.message "newyes"/> </td>
								       </#if>
								        <#if contentList.isrelease==2>
								       <td><@spring.message "newno"/></td>
								       </#if>
								       
								       <#if contentList.contenttype==1>
								       <td><@spring.message "news"/> </td>
								       </#if>
								        <#if contentList.contenttype==2>
								       <td><@spring.message "newproduct"/> </td>
								       </#if>
								      <td>${contentList.createtime}</td>
								      <td>${contentList.image}</td>
								      <td>
								     	 <a class="btn btcms" onclick="showcontent('${contentList.contentid}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "newview"/></a>&nbsp;
								     		 	<a class="btn btcms" onclick="updatecontent('${contentList.contentid}')"><i class="fa fa-edit"></i>&nbsp;<@spring.message "newedit"/></a>&nbsp;
								     			<a class="btn btcms" onclick="removecontent('${contentList.contentid}','${contentList.name}')" ><i class="fa fa-close"></i>&nbsp;<@spring.message "newdelete"/></a>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center"><@spring.message "newnoinfermation"/></td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value=<@spring.message "newup"/> >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value=<@spring.message "newdown"/> /></li>
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
<div class="row-fluid"  id='addNewContent'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id='addContentForm'>
							<input type="hidden" value="" id='contentid_add'  name='contentid' >
							<div class="control-group">
								<label class="control-label"><@spring.message "newtitles"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='contenttitle_add'  name='contenttitle'  />
									<span class="help-inline"  id='config_key_'></span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "newcontentss"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='content_add'  name='content'  />
									<span class="help-inline"  id='config_value_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newisfabu"/></label>
								<div class="controls">
									 <select class="form-control"  id="isrelease_add" name="isrelease" >
										 <option value="0" selected="selected"><@spring.message "newchoose"/></option>
											 <#if getisreleaseget?? && (getisreleaseget?size > 0)>
								                <#list getisreleaseget as getisreleaseget>
									                 <#if getisreleaseget.isrelease==1>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newyes"/></option>
									                  </#if>
									                 <#if getisreleaseget.isrelease==2>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newno"/></option>
									                 </#if>
											 	</#list>
											 </#if>
										</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newcontent"/></label>
								<div class="controls">
									<select class="form-control" id="contenttype_add" name="contenttype"  >
									      <option value="0" selected="selected"><@spring.message "newchoose"/></option>
									      	<#if getcontentType?? && (getcontentType?size > 0)>
							                <#list getcontentType as contentType>
								                 <#if contentType.contenttype==1>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "news"/></option>
								                  </#if>
								                 <#if contentType.contenttype==2>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "newproduct"/></option>
								                 </#if>
										 	</#list>
										 </#if>
										</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newcreatetime"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='createtime_add'  name='createtime'  onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									<span class="help-inline"  id='config_type_'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveContent();"><i class="icon-ok"></i> <@spring.message "newbaocun"/></button>
								<button type="button" class="btn blue" id='cancleAdd'><@spring.message "newcancel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加用户窗口 end -->

<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showcontent'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newtitle"/></label>
								<label class="control-label"  id='content_title'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newcontents"/></label>
								<label class="control-label"  id='content_content'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newisfabu"/></label>
								<label class="control-label"  id='isrelease'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newcontent"/></label>
								<label class="control-label"  id='contenttype'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newcreatetime"/></label>
								<label class="control-label"  id='create_time'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "newimage"/></label>
								<label class="control-label"  id='image_type'></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "newcancel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户信息展示窗口 end -->

<!-- 用户信息修改窗口 start -->
<div class="row-fluid"  id='changeContent'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changeFormcontent">
						<input type="hidden" value="" id='contentid_modify'  name='contentid' />
							<div class="control-group">
								<label class="control-label"><@spring.message "newtitle"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='contenttitle_modify'  name='contenttitle'  />
									<span class="help-inline" id='userName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newcontents"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='content_modify'  name='content'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newisfabu"/></label>
								<div class="controls">
									<select class="form-control"  id="isrelease_add" name="isrelease" >
										 <option value="0" selected="selected"><@spring.message "newchoose"/></option>
											 <#if getisreleaseget?? && (getisreleaseget?size > 0)>
								                <#list getisreleaseget as getisreleaseget>
									                 <#if getisreleaseget.isrelease==1>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newyes"/></option>
									                  </#if>
									                 <#if getisreleaseget.isrelease==2>
									                 <option value="${getisreleaseget.isrelease}" <#if getisreleaselist?? && getisreleaselist=getisreleaseget.isrelease>selected</#if>><@spring.message "newno"/></option>
									                 </#if>
											 	</#list>
											 </#if>
										</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newchoose"/></label>
								<div class="controls">
									<select class="form-control" id="contenttype_add" name="contenttype"  >
									      <option value="0" selected="selected"><@spring.message "newchoose"/></option>
									      	<#if getcontentType?? && (getcontentType?size > 0)>
							                <#list getcontentType as contentType>
								                 <#if contentType.contenttype==1>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "news"/></option>
								                  </#if>
								                 <#if contentType.contenttype==2>
								                 <option value="${contentType.contenttype}" <#if contentTypee?? && contentTypee=contentType.contenttype>selected</#if>><@spring.message "newproduct"/></option>
								                 </#if>
										 	</#list>
										 </#if>
										</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newcreatetime"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='createtime_modify'  name='createtime'  onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "newimage"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='image_modify'  name='image'  />
									<span class="help-inline"  id='trueName_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChangecontent();"><i class="icon-ok"></i> <@spring.message "newbaocun"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "newcancel"/></button>
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
