<#include "top.ftl">


<div class="gr_w">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./pssindex.do"><@spring.message "HomePage"/></a>：<a href="./orderpayinfo.do">订单支付信息</a></div>
	</div>
	
          
            
            </div>
            <div align="center">
            <form action="./saveRefund.do" method="post" enctype="multipart/form-data">
            	<table width="500" border="0" class="cp_table">
            		<input type="hidden" name="ordermainid" value="${orderMainid}">
            		<input type="hidden" name="collectid" value="${collectid }">
            	
           
          
            <tr height="40" align="center"><td>Ticket's id</td>
            		<td>${refund.ticketid }
            		
            </td>
            </tr>
         
             <tr height="40" align="center"><td>退款日期</td>
            		<td>${refund.trandate }
            </td>
            </tr>
               
             <tr height="40" align="center"><td>支付总价</td>
            		<td>${refund.amount }${unit }
            </td>
            </tr>
              <tr height="40" align="center"><td>账单</td>
            		<td><img  src="${refund.imageaddress }">
            </td>
            </tr>
            
              <tr height="40" align="center"><td>管理员</td>
            		<td><img  src="${refund.sysuser }">
            </td>
            </tr>
            
           <tr height="40" align="center">
            <#if refund.orderMainid != "">
            		<td colspan="2"><a href="./orderMain.do">返回</a>
            		</#if>
            		   <#if refund.collectid != "">
            		<td colspan="2"><a href="./pssCollectInfomanage.do">返回</a>
            		</#if>
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