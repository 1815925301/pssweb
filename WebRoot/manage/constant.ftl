<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
			
			
			<div class="main-hd">
				 <form class="form-horizontal" method="post"  id="searchForm">
				       常量表： <select class="u_select"  id="constantInfoSearch" name="constantInfoSearch">
			               <option value="-1">请选择常量表</option>
				           <#if constantList?? && (constantList?size > 0)>
	                		<#list constantList as constantInfo>
				               <option value="${constantInfo.id}" >${constantInfo.name}(${constantInfo.description})</option>
							</#list>
						   </#if>
					     </select>
				  &nbsp; <input class="btn btcms" type="button" id="searchMatch_role" value="搜索" />
                         <input class="btn btcms" type="button" id="searchAll" value="搜索全部" />
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
										<button  class="btn btcms" id="addNewConstant">
										<i class="fa fa-plus"></i>&nbsp;新增 
										</button>
									</div>
									
								</div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
	                                <td width="6%">序号</td>
							        <td width="6%">ID</td>
							        <td width="20%">表名</td>
							        <td width="20%">描述</td>
							        <td width="15%">最后修改时间</td>
							        <td width="30%">动作</td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if constantList?? && (constantList?size > 0)>
				                <#assign num = 0>
				                <#list constantList as constant>
				                <#assign num = num + 1>
				                <tr>  
							      <td class="botValue">${num}</td>
							      <td class="botValue"><label class="columnLable" id="show_org_name_${constant.id}">${constant.id}</label></td>
							      <td class="botValue">${constant.name}</td>
							      <td class="botValue">${constant.description}</td>
							      <td class="botValue">${constant.updateTime}</td>
							      <td class="botValue">
							        <a class="btn btcms" id="addListInfo_${constant.id}@${constant.name}"><i class="icon-edit"></i>添加数据</a>
							        <a class="btn btcms" id="showListInfo_${constant.id}@${constant.name}@${constant.description}"><i class="icon-eye-open"></i>查看数据</a>
							       <!--<a class="btn btcms" id="removeListInfo_${constant.id}@${constant.name}@${constant.description}"><i class="icon-trash"></i>删除</a> --> 
							      </td>
							    </tr>
							    </#list>
				              <#else>
				                <tr>  
							      <td class="botValue" colspan="6" style="padding:10px 0;">暂无常量信息！</td>
							    </tr>
				              </#if>
						</tbody>
						<!--table 表格结束-->
						<!-- 列表分页模块开始 start -->
						<!-- 列表分页模块结束 end -->
						</table>
                    </div>
                </div>
            </div>
        </div>
        
<!--添加常量表窗口 start -->
<div class="row-fluid"  id='addNewConstantWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" method="post" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label">表名：</label>
								<div class="controls">
									<input type="text" maxlength="40" class="width220" id="add_name" name="name" value="constant_" />
	  	                            <span id="add_name_error" class="red"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">描述</label>
								<div class="controls">
									<input type="text" class="width220" maxlength="100" id="add_description" name="description" value="" />
		                            <span id="add_description_error" class="red"></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="saveNewConstant"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue" id='cancleConstantAdd'>取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>

<!--添加常量表窗口 end -->

<!-- 添加常量数据窗口 start -->
<div class="row-fluid"  id='addNewListWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" method="post" class="form-horizontal">
						<input type="hidden" value="" id="add_constant_id" />
						<input type="hidden" value="" id="add_constant_tableName" />
							<div class="control-group">
								<label class="control-label">编码：</label>
								<div class="controls">
								    <input type="text" maxlength="15" class="width220" id="add_code" name="code" value="" />
	  	                            <span id="add_code_error" class="red"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">常量值：</label>
								<div class="controls">
									 <input type="text" class="width220" maxlength="120" id="add_value" name="value" value="" />
		                             <span id="add_value_error" class="red"></span>
								</div>
							</div>
							<div class="form-actions">
								 <input type="button" class="btn blue" id="saveNewList" value="  确  定  " />
	                             <input type="button" class="btn blue" id="cancleListAdd" value="  取  消  " />
							</div>
						</form>
				</div>
	 		</div>
</div>

<!-- 添加常量数据窗口 end -->

<!-- 常量信息展示窗口 start -->
<ul id="constInfoWin" class="msg_Tip_box width600" style="display:none">
	<a class="mask_close pr10" href="javascript:$.unblockUI()"></a>
	<div id="constantValue" class="center tabDivInfo"  style="max-height:500px;overflow-y:auto;">
	  
	</div>
</ul>
<!-- 常量信息展示窗口 end -->

<!-- 常量信息修改窗口 start -->
<ul id="editListWin" class="msg_Tip_box width500" style="display:none">
	<a class="mask_close pr10" href="javascript:$.unblockUI()"></a>
	<div class="center tabDivInfo">
	  <table class="tableWin" border="1">
	  	<tr><td colspan='2' class="top">修 改 常 量 信 息<input type="hidden" value="" id="edit_id" />
	  	<input type="hidden" value="" id="edit_code_old" /></td></tr>
	  	 <tr>
	        <td class='leftTd' width='25%'><span class="red">*</span>常量表：</td>
	        <td>
     		    <select class="u_select" id="edit_constant_id" name="edit_constant_id">
	               <option  value="-1">请选择常量表</option>
		           <#if constantList?? && (constantList?size > 0)>
            		<#list constantList as constantInfo>
		               <option value="${constantInfo.id}" >${constantInfo.name}</option>
					</#list>
				   </#if>
			     </select>
			     <span id="edit_constant_id_error" class="red"></span>
	        </td>
	     </tr>
	    <tr>
	  	  <td class='leftTd' width='25%'><span class="red">*</span>编码：</td>
	  	  <td width='75%'>
	  	    <input type="text" maxlength="15" class="width220" id="edit_code" name="edit_code" value="" />
	  	    <span id="edit_code_error" class="red"></span>
	  	  </td>
	  	</tr>
		<tr>
		  <td class='leftTd'><span class="red">*</span>常量值：</td>
		  <td>
		    <input type="text" class="width220" maxlength="120" id="edit_value" name="edit_value" value="" />
		    <span id="edit_value_error" class="red"></span>
		  </td>
		</tr>
	  </table>
	  <input type="button" class="botton" id="saveEditRole" value="  确  定  " />
	  <input type="button" class="botton" id="cancle" value="  取  消  " />
	</div>
</ul>
<!-- 常量信息修改窗口 end -->
<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

