<#include "top.ftl">
	
<!-- BEGIN PAGE CONTAINER-->
       
			<div class=" clear"></div>
<div class="gr_w">


<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./pssCollectInfomanage.do"><@spring.message "Collection"/></a></div>


<div class="clear"></div> 
<div class="gr_t"><span><img src="imgnew/icon19.png" width="33" height="30" /></span><@spring.message "query"/></div>
<div class="clear"></div>


<div class="cxtj" style="width:800;">
					<form id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
					<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >	
						<div class="cxtjf" style="margin-right: 10px;"><@spring.message "satellite"/></div>
						<div class="ddcxinput">
						<!-- 
						<select id="satelliteidSearch" name="satelliteidSearch"  >
									      <option value="-1" selected="selected"><@spring.message "select"/></option>
	 										<option value="VRSS-1" <#if satelliteidSearchhuixian='VRSS-1'>selected</#if>>VRSS-1</option>
									      	<option value="VRSS-2" <#if satelliteidSearchhuixian='VRSS-2'>selected</#if>>VRSS-2</option>
	 					</select>
	 					 -->
	 					<select id="_satelliteId" name="_satelliteId" onchange="sensorSelcect(this)">
							<option value="-1" selected="selected"><@spring.message "choose"/></option>
								<#if collectSatellite?? && (collectSatellite?size > 0)>
							    	<#list collectSatellite as satellite>
										<option value="${satellite}" <#if _satelliteId=='${satellite}'>selected</#if>>${satellite}</option>
							  		</#list>
							  	</#if>
				       </select>
	 					</div>
						<div class="cxtjf" style="margin-right: 10px;"><@spring.message "sensorid"/></div>
						<div class="ddcxinput">
						<!-- 
						<select id="sensoridSearch" name="sensoridSearch"  >
									      <option value="-1" selected="selected"><@spring.message "select"/></option>
									      <option value="PMC" <#if sensoridSearchhuixian='PMC'>selected</#if>>PMC</option>
									      <option value="WMC" <#if sensoridSearchhuixian='WMC'>selected</#if>>WMC</option>							
						</select>
							 -->
						<select id="_sensorIdswhat" name="_sensorIdswhat">
							<option value="-1" selected="selected"><@spring.message "choose"/></option>
							<#if sensorIdS?? && (sensorIdS?size > 0)>
						    	<#list sensorIdS as sensor>
							<option value="${sensor}" <#if _sensorId=='${sensor}'>selected</#if>>${sensor}</option>
								</#list>
						  	</#if>
						</select>
						
						</div>
						
						
						
						<div class="cxtjf" style="margin-right: 10px;"><@spring.message "taskid"/></div>
						<div class="cxtjinput"><input type="text" value="<#if taskid??>${taskid}</#if>"  id="taskidSearch" name="taskidSearch"></div>
						
						<div class="cxtjf"><@spring.message "status"/></div>
						<div class="ddcxinput"><select  id="statusSearch" name="statusSearch" >
									      <<option value="-1" selected="selected"><@spring.message "select"/></option>
									      <option value="处理中" <#if statusSearchhuixian='处理中'>selected</#if>><@spring.message "report.order.orderstate.chuling"/></option>
									      <option value="等待中" <#if statusSearchhuixian='等待中'>selected</#if>><@spring.message "reprot.order.orderstate.loading"/></option>
									      <option value="完成" <#if statusSearchhuixian='完成'>selected</#if>><@spring.message "reprot.order.orderstate.ok"/></option>
									      <option value="失败" <#if statusSearchhuixian='失败'>selected</#if>><@spring.message "reprot.order.orderstate.error"/></option>
									      <option value="取消" <#if statusSearchhuixian='取消'>selected</#if>><@spring.message "reprot.order.orderstate.esc"/></option>						
										</select></div>
						<div class="cxtjf"><@spring.message "createtime"/></div>
						<div class="cxtjinput01"><input id="createStarttime" name="createStarttime" value="${createStarttime}" type="text" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});"/></div>
										<div class="cxtjicon"><img src="imgnew/icon20.png" width="17" height="17" /></div>
										<div class="cxtjicon"><@spring.message "until"/></div>
										<div class="cxtjinput01"><input id="createEndtime" name="createEndtime" value="${createEndtime}" type="text" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});"/></div>
										<div class="cxtjicon"><img src="imgnew/icon20.png" width="17" height="17" /></div>
						
						<div class="cxtjf"><@spring.message "begintime"/></div>
						<div class="cxtjinput01"><input id="beginStarttime" name ="beginStarttime" value="${beginStarttime}" type="text" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});"/></div>
						<div class="cxtjicon"><img src="imgnew/icon20.png" width="17" height="17" /></div>
						<div class="cxtjf"><@spring.message "until"/></div>
						<div class="cxtjinput01"><input id="beginEndtime" name ="beginEndtime"value="${beginEndtime}" type="text" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});"/></div>
						<div class="cxtjicon"><img src="imgnew/icon20.png" width="17" height="17" /></div>
						<div class="clear"></div>
						<div class="bcqx"><a id="searchMatch"><@spring.message "search"/></a><a id="searchAll"><@spring.message "reset"/></a></div>
						</form>
						</div>
				
				
				<!-- BEGIN PAGE CONTENT-->
				<div class="Sonwidth">
				
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						
								
									<div class="bcqx" style="float: left">
									<a href="${ctx}/pssCollectInfomanageAdd.do">
										
										<@spring.message "add"/>
										
										</a>
									</div>
									
								
                       <table width="1099" border="0" class="cxtjtable">
                            <thead>
                                <tr class="cxtjtr" align="center" style="height: 40px;">
                                
			     				
							     <th bgcolor="#1d53a1"><center><@spring.message "taskid"/></center></th>
							      <th bgcolor="#1d53a1"><center><@spring.message "FriendlyTips"/></center></th>
							    
							     <th bgcolor="#1d53a1"><center><@spring.message "price"/></center></th>
							      <th bgcolor="#1d53a1"><center><@spring.message "unit"/></center></th>
							      <th bgcolor="#1d53a1"><center><@spring.message "Audit_status"/></center></th>
							     <th bgcolor="#1d53a1"><center><@spring.message "status"/></center></th>
							     <th bgcolor="#1d53a1"><center><@spring.message "createtime"/></center></th>
							     <th bgcolor="#1d53a1"><center><@spring.message "operation"/></center></th>
                                </tr>
                            </thead>
                            <tbody>
                               <#if collectInfoList?? && (collectInfoList?size > 0)>
								    <#assign num = totalInfo.startNum>
								    <#list collectInfoList as collectInfo>
								    <#assign num = num + 1>
								    <tr class="gradeA" align="center">
								      
								     
								      <td height="40">${collectInfo.taskid}</td>
								      <td height="40">${collectInfo.friendlyprompt}</td>
								      
								      <td height="40">${collectInfo.price}</td>
								       <td height="40">${collectInfo.unit}</td>
								      <#if collectInfo.checkstate==1||collectInfo.checkstate=="">
								      <td height="40"><@spring.message "Waitfor_audit"/></td>
								      </#if>
								      <#if collectInfo.checkstate==2>
								      <td height="40"><@spring.message "Audit_pass"/></td>
								      </#if>
								      <#if collectInfo.checkstate==3>
								      <td height="40"><@spring.message "Audit_failure"/></td>
								      </#if>
								      <td height="40">${collectInfo.status}</td>
								      <td height="40">${collectInfo.createtime}</td>
								      <td height="40">
								     	 <a href="${ctx}/showCollectInfo.do?id=${collectInfo.id}" target="view_window"><@spring.message "detail"/></a>
								     		 <#if collectInfo.ispay==1>
								     		 <a onclick="queryPayInfo('${collectInfo.taskid}')" ><@spring.message "queryPrice"/></a>
								     		  <#else>
								     		  <a onclick="addcollectIspay('${collectInfo.taskid}')" ><@spring.message "insertPrice"/></a>
								     		 </#if>
								     		 <#if collectInfo.isrefund==1>
								     			<a onclick="showCollectrefund('${collectInfo.taskid}')">查看退款信息</a>
								     		</#if>
								     			<a onclick="removeCollectInfo('${collectInfo.id}')" ><@spring.message "remove"/></a>
								      </td>
								    </tr>
								    </#list>
								  <#else>
								  <tr class="gradeA">  
			    				 	  <td  colspan="8" style="padding:10px 0;algin:center"><@spring.message "No information"/></td>
			   					  </tr>
              					</#if>
						</tbody>
						<!--table 表格结束-->
						<!-- 列表分页模块开始 start -->
				           <tfoot align="left">
                                <tr>
                                    <td colspan="10">
                                   		 <div class="span6"><div class="dataTables_info" id="sample_1_info"></div></div>
                                   		 <div class="span6">
										<div class="dataTables_paginate paging_bootstrap pagination">
                                     	<ul>
											     <#if (totalInfo.totalCount > 0)>
											      <#if (totalInfo.currentPage > 1)>
											      	<li class="prev disabled">
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="<@spring.message "previous"/>" >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="<@spring.message "next"/>" /></li>
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
                
           

<!-- 用户信息修改窗口 start -->
<div class="row-fluid"  id='changeCollectInfoWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changeForm">
						<input type="hidden" value="" id='change_id'  name='id'>
							<div class="control-group">
								<label class="control-label"><@spring.message "satellite"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='change_satelliteid'  name='satelliteid' style="height: 35px;"/>
									<span class="help-inline" id='satelliteid_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "sensorid"/></label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id='change_sensorid'  name='sensorid' style="height: 35px;" />
									<span class="help-inline"  id='sensorid_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Upperleftlongitude"/></label>
								<div class="controls">
								<input type="text" value=""  class="m-wrap medium"  id='change_upperleftlong'  name='upperleftlong' style="height: 35px;" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Topleftlatitude"/></label>
								<div class="controls">
								<input type="text" value=""  class="m-wrap medium"  id='change_upperleftlat'  name='upperleftlat' style="height: 35px;" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Lowerrightlongitude"/></label>
								<div class="controls">
								<input type="text" value=""  class="m-wrap medium"  id='change_lowerrightlong'  name='lowerrightlong' style="height: 35px;" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "Bottomrightlatitude"/></label>
								<div class="controls">
								<input type="text" value=""  class="m-wrap medium"  id='change_lowerrightlat'  name='lowerrightlat' style="height: 35px;" />
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="saveChange();"><@spring.message "save"/></button>
								<button type="button" class="btn blue" id='changecancleAdd'><@spring.message "Cancel"/></button>
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

