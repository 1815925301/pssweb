<#include "top.ftl">


<div class="gr_w">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./pssindex.do"><@spring.message "HomePage"/></a>：<a href="./orderpayinfo.do">订单支付信息</a></div>
	</div>
	<div class="lcimg">
		 <!--新加的代码-->
            <div class="lcimg-row lcimg-row-bg">
                <div class="lcimg-col-3 lcimg-1">
                    <div><i>1</i></div>
                </div>
                <div class="lcimg-col-3 lcimg-2"><div><i>2</i></div></div>
                <div class="lcimg-col-3 lcimg-3"><div><i>3</i></div></div>
                <div class="lcimg-col-3 lcimg-4 active"><div><i>4</i></div></div>
            </div>
            <div class="lcimg-row lcimg-row-txt">
                <div class="lcimg-col-3">
                    <span><@spring.message "mycar"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "orderinfo"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "submitorder"/></span>
                </div>
                <div class="lcimg-col-3 active">
                    <span><@spring.message "ordersystem"/></span>
                </div>
            </div>
            <!--新加的代码END-->
            </div>
          
            
            </div>
            <div align="center">
            <form action="./saveOrderpay.do" method="post" enctype="multipart/form-data" onsubmit="return checkall()">
            	<table width="500" border="0" class="cp_table">
            		<input type="hidden" name="orderMainId" value="${orderMainId }">
            		<input type="hidden" name="collectid" value="${collectid }">
            		
            		 <#if collectInfo!="" && collectInfo != null>
             <tr height="40" align="center"><td>支付总价</td>
            		<td>${collectInfo.price }${collectInfo.unit }
            </td>
            </tr>
            </#if>
            <#if orderMain!="" && orderMain != null>
             <tr height="40" align="center"><td>支付总价</td>
            		<td>${orderMain.price }${orderMain.uuit }
            </td>
            </tr>
            </#if>
            		<tr height="40" align="center"><td>PSSUser银行</td>
            		<td><select name="pssuserbank">
            		<#if userbank?? && (userbank?size > 0)>
				    	<#list userbank as ubank>
							<option value="${ubank}">${ubank}</option>
				  		</#list>
				  	</#if>
		            	
            			</select>
            </td>
            </tr>
            <tr height="40" align="center"><td>ABAE银行</td>
            		<td><select name="abaesbank">
		            <#if abaebank?? && (abaebank?size > 0)>
				    	<#list abaebank as abank>
							<option value="${abank}">${abank}</option>
				  		</#list>
				  	</#if>
            			</select>
            </td>
            </tr>
            <tr height="40" align="center"><td>ABAE银行账号</td>
            		<td><input id="abaeaccount" type="text" name="abaeaccount" style="height: 30px;" onblur="checkcount()">
            		<span style="display: none;" id="abaeaccount_null"><font color="red">银行账号不能为空</font></span>
            		<span style="display: none;" id="abaeaccount_error"><font color="red">银行账号只能是16位数字</font></span>
            </td>
            </tr>
            <tr height="40" align="center"><td>Ticket's id</td>
            		<td><input type="text" name="ticketid" id="ticketid" style="height: 30px;" onblur="checkticketid()">
            		<span style="display: none;" id="ticketid_null"><font color="red">Ticket's id不能为空</font></span>
            		<span style="display: none;" id="ticketid_error"><font color="red">Ticket's id只能是10位数字</font></span>
            </td>
            </tr>
            <tr height="40" align="center"><td>支付方式</td>
            		<td><select name="trantype">
		            	<option value="Electronic">Electronic</option>
		            	<option value="In a bank">In a bank</option>
            			</select>
            </td>
            </tr>
             <tr height="40" align="center"><td>支付日期</td>
            		<td><input type="text" name="trandate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" style="height: 30px;">
            </td>
            </tr>
               
             <tr height="40" align="center"><td>支付总价</td>
            		<td><input type="text" name="amount" style="height: 30px;width: 100px;"><select name="unit" style="width: 100px;">
		            	<option value="¥">¥</option>
		            	<option value="$">$</option>
            			</select>
            </td>
            </tr>
              <tr height="40" align="center"><td>账单上传</td>
            		<td><input type="file" name="file" required="required">
            </td>
            </tr>
            
               <#if usercountry!=Venezuela>
             <tr height="40" align="center"><td>公司地址</td>
            		<td><input type="text" name="companyaddress" style="height: 30px;">
            </td>
            </tr>
            </#if>
            <tr height="40" align="center">
            		<td colspan="2"><input type="submit" value="提交">
            </td>
            </tr>
            	</table>
            </form>
            </div>
 </div>
<#include "foot.ftl">        
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>
<script src="js/service/SystemConfigAdd.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>  
<script type="text/javascript" src="js/service/orderpay.js"></script>            