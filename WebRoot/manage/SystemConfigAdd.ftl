<#include "top.ftl">
<!-- BEGIN PAGE CONTAINER-->        
<#include "style.ftl">			
			
			
			
				
				
				
<br><br><br><br>

<div class="row-fluid"  id='addNewSystemConfigg' style="margin-left:180px;">
			<div class="portlet-body form">
						<form action="${ctx}/saveNewSystemConfig.do"  name="payForm"  method="POST" class="form-horizontal" id='addSystemForm' enctype="multipart/form-data">
							<input type="hidden"  id='config_id_add'  name='configid' >
							<div class="control-group">
								<label class="control-label"><@spring.message "configname"/>*</label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="config_key_add"  name="configkey"/>
									<span id="inputName" Style="color: red; display:none; font-size: 12pt;"><@spring.message "shurupeizhi"/></span>
									<span class="help-inline"  id='config_key_'></span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "configvalue"/>*</label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="config_value_add"  name="configvalue"/>
									<span id="inputValue"Style="color: red; display:none; font-size: 12pt;"><@spring.message "shurupeizhizhi"/></span>
									<span class="help-inline"  id='config_value_'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "configdiscrib"/>*</label>
								<div class="controls">
									<input type="text"   class="m-wrap medium"  id="configd_ess_add"   name="configdes"/>
									<span id="inputDes"Style="color: red; display:none; font-size: 12pt;"><@spring.message "shurupeizhimiaoshu"/></span>
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
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>>系统配置</option>
								                  </#if>
								                 <#if sysTypelist.configtype==1>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>>管理配置</option>
								                 </#if>
								                 <#if sysTypelist.configtype==2>
								                 <option value="${sysTypelist.configtype}" <#if type?? && type=sysTypelist.configtype>selected</#if>>内容配置</option>
										   		</#if>
										 	</#list>
										 </#if>
			                           </select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><@spring.message "configimage"/></label>
									 <input type="file" name="file" required="required"></input>  
							</div>
							
							<div style="background-color: #F5F5F5;  height: 80px; ">
							  	<input type="submit" style="margin-left:370px; margin-top:30px;" value= <@spring.message "configsubmit"/> class="btn blue"><i class="icon-ok"></i> </input>
							<#--  <button type="button" class="btn blue" id="submit"><i class="icon-ok"></i> 保存</button>-->
								<button type="button" class="btn blue" style=" margin-top:30px;" id='cancleAdd'><@spring.message "configcancel"/></button>
							</div>
							
						</form>
	 		</div>
</div>

<!-- 添加用户窗口 end -->



<!-- 用户信息修改窗口 end -->
<#include "foot.ftl">

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>
<script src="js/service/SystemConfigAdd.js" type="text/javascript"></script>