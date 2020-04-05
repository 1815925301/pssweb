<#include "top.ftl">


<div class="gr_w">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	</div>
          
            </div>
            <div align="center">
            <form action="./saveRolePay.do" method="post" enctype="multipart/form-data" onsubmit="return checkall()">
            	<table width="500" border="0" class="cp_table">
            		
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