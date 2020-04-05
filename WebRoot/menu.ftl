<div style="width:100%;height:80px;">
<#if allowList?? && (allowList?size > 0) >
	<#list allowList as allow>
		<input type='button' id="allow_${allow.newstatus}"  value="${allow.newstatustext}"/> 
	</#list>
</#if>
<div>