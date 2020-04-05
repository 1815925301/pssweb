<#include "top.ftl">
 

<!-- BEGIN PAGE CONTAINER-->        
	
			<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body">
								<div class="clearfix">
									<div class="btn-group">
										<button  class="btn btcms" id="addMemberprice">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "add"/> 
										</button>
									</div>
									
								</div>
				<!-- BEGIN PAGE CONTENT-->
				
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
	                                <td><@spring.message "membertype"/></td>
							        <td><@spring.message "onemonth"/></td>
							        <td><@spring.message "threemonth"/></td>
							        <td><@spring.message "sixmonth"/></td>
							        <td><@spring.message "year"/></td>
							        <td><@spring.message "operation"/></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if mlist?? && (mlist?size > 0)>
				             	
				                <#list mlist as m>
				               	
				                <tr> 
							     	<#if m.membertype ==1 >
							 		<td class="botValue"><@spring.message "Ordinary_member"/></td>
							 		</#if>
							 		<#if m.membertype ==2 >
							 		<td class="botValue"><@spring.message "Silver_member"/></td>
							 		</#if>
							 		<#if m.membertype ==3 >
							 		<td class="botValue"><@spring.message "Gold_member"/></td>
							 		</#if>
							 		<#if m.membertype ==4 >
							 		<td class="botValue"><@spring.message "Diamond_member"/></td>
							 		</#if>
							 		<#if m.membertype ==5 >
							 		<td class="botValue"><@spring.message "VIP_member"/></td>
							 		</#if>
							 		<#if m.membertype =="" >
							 		<td class="botValue">--</td>
							 		</#if>
							 		
							 		
							 		
							 		
							 		<td class="botValue">${m.onemonth }</td>
							 		
							 		
							 		<td class="botValue">${m.treemonths }</td>
							 		
							 		
							 		
							 		<td class="botValue">${m.sixmonths }</td>
							 		
							 		
							 		<td class="botValue">${m.year }</td>
							 		
							 		
							 		
							 	
							 		
							      <td class="botValue">
							     
							      <a class="btn btcms" onclick="updatemember('${m.id}')" id="checkprice"><i class="fa fa-train"></i>&nbsp;修改</a>&nbsp;
							     
							      </td>
							    </tr>
							    </#list>
				              <#else>
				                <tr>  
							      <td class="botValue" colspan="6" style="padding:10px 0;"><@spring.message "nomore"/></td>
							    </tr>
				              </#if>
						</tbody>
						<!--table 表格结束-->
							
						</table>
                    </div>
                </div>
            </div>
        </div>
        



<!--添加常量表窗口 end -->



<!-- 添加常量数据窗口 end -->
<ul id="showMemberprice" class="msg_Tip_box width500" style="display:none">
	<div class="tab-content">
						<form action="#" class="form-horizontal" id="addpriceForm">
							<div class="control-group form_lab_div">
								<label class="control-label">会员类型</label>
								<select type="text" name="membertype" id="">
								<option value="1"><@spring.message "Ordinary_member"/></option>
								<option value="2"><@spring.message "Silver_member"/></option>
								<option value="3"><@spring.message "Gold_member"/></option>
								<option value="4"><@spring.message "Diamond_member"/></option>
								<option value="5"><@spring.message "VIP_member"/></option>
								</select>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "onemonth"/></label>
								
								<input type="text" name="onemonth" id="">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "threemonth"/></label>
								
								<input type="text" name="treemonths" id="">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "sixmonth"/></label>
								
								<input type="text" name="sixmonths" id="">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "year"/></label>
								
								<input type="text" name="year" id="">
							</div>
							
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='savememberprice()'><@spring.message "Confirm"/></button>
								<button type="button" class="btn blue" onclick='Canclesave()'><@spring.message "Cancel"/></button>
							</div>
						</form>
				</div>
	 
</ul>
<!-- 常量信息修改窗口 end -->


<!-- 常量信息修改窗口 start -->
<ul id="changememberprice" class="msg_Tip_box width500" style="display:none">
	 <div class="tab-content">
						<form action="#" class="form-horizontal" id="memeberpriceForm">
							<div class="control-group form_lab_div">
								<input type="hidden" name="id" id="member_id">
								<label class="control-label"><@spring.message "membertype"/></label>
								<input type="hidden" value="<@spring.message 'Ordinary_member'/>" id="Ordinary_member">
								<input type="hidden" value="<@spring.message 'Silver_member'/>" id="Silver_member">
								<input type="hidden" value="<@spring.message 'Gold_member'/>" id="Gold_member">
								<input type="hidden" value="<@spring.message 'Diamond_member'/>" id="Diamond_member">
								<input type="hidden" value="<@spring.message 'VIP_member'/>" id="VIP_member">
								<input type="text" name="" id="membertype_id" readonly="readonly">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "onemonth"/></label>
								
								<input type="text" name="onemonth" id="onemonth_id">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "threemonth"/></label>
								
								<input type="text" name="treemonths" id="treemonths_id">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "sixmonth"/></label>
								
								<input type="text" name="sixmonths" id="sixmonths_id">
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "year"/></label>
								
								<input type="text" name="year" id="year_id">
							</div>
							
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='updatememberprice()'><@spring.message "Confirm"/></button>
								<button type="button" class="btn blue" onclick='Cancle()'><@spring.message "Cancel"/></button>
							</div>
						</form>
				</div>
</ul>
<!-- 常量信息修改窗口 end -->

<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
