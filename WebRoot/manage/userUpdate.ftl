<#include "top.ftl">
<div>
 <form id="updateForm">
 <table width="" border="0" cellspacing="0" cellpadding="0"
		class=" tab9">
		<tr>
		<td width="18">&nbsp;</td>
		</tr>
		<tr>
		    <td width="18">&nbsp;</td>
			<td width="145" align="right"><span class="col4 sp12">*</span>
				<@spring.message "userName"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="username" name="username" class="in2"
				type="text" value="${user.userName}" /></td>

			<td width="145" align="right"><span class="col4 sp12">*</span>
				更新频率：
				</td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="frequency" name="frequency" class="in2"
				type="text" value="" />
			</td> 
		</tr>
		<tr>
		   <td width="18">&nbsp;</td>
			<td width="145" align="right"><span class="col4 sp12">*</span>
				更新字段：</td>
			<td width="18">&nbsp;</td>
			<td width="195">
				<select id="frequcyColumn" name="frequcyColumn">
					<option value="emai">邮箱</option>
					<option value="pass">密码</option>
					<option value="trueName">真实姓名</option>
				</select>
				</td>
		</tr>
	</table>
	<div class="form-actions" style="text-align:center">
		<button type="button" class="btn blue" onclick="saveUpdate()"><@spring.message "save"/></button>
		<button type="button" class="btn blue" onclick="lockCancle()"><@spring.message "cancle"/></button>
	</div>
 </form>
</div>

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

