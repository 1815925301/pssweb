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
            <form action="./saveOrderpay.do" method="post" enctype="multipart/form-data">
            	<table width="500" border="0" class="cp_table">
            		<tr height="40" align="center"><td>PSSUser银行</td>
            		<td>${pssOrderPay.pssuserbank }
            </td>
            </tr>
            <tr height="40" align="center"><td>ABAE银行</td>
            		<td>${pssOrderPay.abaesbank }
            			
            </td>
            </tr>
            <tr height="40" align="center"><td>ABAE银行账号</td>
            		<td>${pssOrderPay.abaeaccount }
            </td>
            </tr>
            <tr height="40" align="center"><td>Ticket's id</td>
            		<td>${pssOrderPay.ticketid }
            </td>
            </tr>
            <tr height="40" align="center"><td>支付方式</td>
            		<td>${pssOrderPay.trantype }
            </td>
            </tr>
             <tr height="40" align="center"><td>支付日期</td>
            		<td>${pssOrderPay.trandate }
            </td>
            </tr>
               
             <tr height="40" align="center"><td>支付总价</td>
            		<td>${pssOrderPay.amount }${pssOrderPay.unit }
            </td>
            </tr>
              <tr align="center"><td>账单上传</td>
            		<td><img src="${pssOrderPay.imageaddress }"/>
            </td>
            </tr>
            
               <#if pssOrderPay.companyaddress!="">
             <tr height="40" align="center"><td>公司地址</td>
            		<td>${pssOrderPay.companyaddress }
            </td>
            </tr>
            </#if>
            <tr height="40" align="center">
            		<td colspan="2"><input type="button" value="返回">
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