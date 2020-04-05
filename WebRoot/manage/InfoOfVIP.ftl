<#include "top.ftl">
<div align="center">
<span>用户名:</span><span id="userName">&nbsp&nbsp${userName }</span>&nbsp&nbsp&nbsp&nbsp
<span>会员等级:</span><span id="orleNote">&nbsp&nbsp${orleNote }</span>&nbsp&nbsp&nbsp&nbsp
<span>开通时间:</span><span id="dredgeTime">&nbsp&nbsp${dredgeTime }</span>&nbsp&nbsp&nbsp&nbsp
<span>结束时间:</span><span id="endTime">&nbsp&nbsp${endTime }</span>&nbsp&nbsp&nbsp&nbsp
<span>开通方式:</span><span id="dredgeType">&nbsp&nbsp<#if dredgeType==1 >一个月</#if><#if dredgeType==2 >一季度</#if><#if dredgeType==3 >一年</#if></span>&nbsp&nbsp&nbsp&nbsp
<span>总价:</span><span id="price">&nbsp&nbsp${price }${unit }</span>&nbsp&nbsp&nbsp&nbsp
<span>会员权限:</span><span id="authority">&nbsp&nbsp${authority }</span>
</div>


<#include "foot.ftl">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
