<#include "top.ftl">
<div class=""><@spring.message "userMessageTitle"/></div>
<form id="userForm" action="${ctx}${currentPageUrl}" method="post">
	<input type="hidden" name="id" value="1" /> <input type="hidden"
		name="ordercountperday" value="500" />
	<table width="" border="0" cellspacing="0" cellpadding="0"
		class=" tab9">
		<tr>
			<td width="145" align="right"><span class="col4 sp12">*</span>
				<@spring.message "userName"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="userName" name="userName" class="in2"
				disabled="disabled" type="text" value="${user.userName}" /></td>

			<td width="145" align="right"><span class="col4 sp12">*</span>
				<@spring.message "trueName"/>
				</td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="trueName" name="trueName" class="in2"
				type="text" value="${user.trueName}" />
				<span id="trueName_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "trueName_error"/></span>
			</td>
			<td width="145" align="right"><span class="col4 sp12">*</span>
				<@spring.message "userTitle"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="userTitle" name="userTitle"
				class="in2" type="text" value="${user.userTitle}" />
				<span id="userTitle_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "userTitle_error"/></span>
				
			</td>  
		</tr>
		<tr>
			<td align="right"><span class="col4 sp12">*</span>
			<@spring.message "password"/></td>
			<td>&nbsp;</td>
			<td><input id="password" type="password" name="password" class="in2"
				value="${user.password}" />
			<span id="password_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "password_error"/></span>	
			</td>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "againPassword"/></td>
			<td>&nbsp;</td>
			<td><input type="password" name="againpassword"
				id="againpassword" value="${user.password}" class="in2" />
				<span id="apassword_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "apassword_error"/></span>
			</td>
		</tr>
		<tr>
			<td align="right"><span class="col4 sp12"></span> <@spring.message "passprompt"/></td>
			<td>&nbsp;</td>
			<td><input id="passprompt" name="passprompt" class="in2"
				type="text" value="${user.passprompt}" /></td>
			<td align="right"><span class="col4 sp12"></span> <@spring.message "passanswer"/></td>
			<td>&nbsp;</td>
			<td><input id="passanswer" name="passanswer" class="in2"
				type="text" value="${user.passanswer}" /></td>
		</tr>
		<tr>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "userSex"/></td>
			<td>&nbsp;</td>
			<td><select id="usersex" name="userSex">
					<option value="1"<#if user.userSex?? &&
						user.userSex=1>selected</#if>><@spring.message "man"/></option>
					<option value="2"<#if user.userSex?? &&
						user.userSex=2>selected</#if>><@spring.message "woman"/></option>
			</select></td>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "identityCard"/></td>
			<td>&nbsp;</td>
			<td><input id="identitycard" name="identityCard" class="in2"
				type="text" value="${user.identityCard}" />
				<span id="identitycard_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "identitycard_tip"/></span>
				<span id="identitycard_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "identitycard_error"/></span>
			</td>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "postcode"/></td>
			<td>&nbsp;</td>
			<td><input id="postcode" name="postcode" class="in2" type="text"
				value="${user.postcode}" />
				<span id="postcode_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_tip"/></span>
				<span id="postcode_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_error"/></span>
			</td>
		</tr>
		<tr>

			<td align="right"><span class="col4 sp12">*</span> <@spring.message "userPhone"/></td>
			<td>&nbsp;</td>
			<td><input id="phone" name="userPhone" class="in2" type="text"
				value="${user.userPhone}" />
				<span id="phone_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_tip"/></span>
				<span id="phone_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_error"/></span>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "userEmail"/></td>
			<td>&nbsp;</td>
			<td><input id="email" name="userEmail" class="in2" type="text"
				value="${user.userEmail}"/>
				<span id="email_tip"  Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_tip"/></span>
				<span id="email_error"  Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_error"/></span>
				</td>
			<td align="right"><span class="col4 sp12">*</span><@spring.message "fax"/></td>
			<td>&nbsp;</td>
			<td><input id="fax" name="fax" class="in2" type="text"
				value="${user.fax}" />
				<span id="fax_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_tip"/></span>
				<span id="fax_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_error"/></span>
			</td>
		</tr>
		<tr>
			<td align="right"><@spring.message "telephone"/></td>
			<td>&nbsp;</td>
			<td><input id="mobile" name="userMobile" class="in2"
				type="text" value="${user.userMobile}" />
				<span id="mobile_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "mobile_error"/></span>
			</td>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "address"/></td>
			<td>&nbsp;</td>
			<td><input id="address" name="address" class="in2" type="text"
				value="${user.address}" />
				<span id="address_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "address_tip"/></span>
			</td>
			<td align="right"><@spring.message "ipAddress"/>
			
			</td>
			<td>&nbsp;</td>
			<td><input id="ipAddress" name="ipAddress" class="in2"
				type="text" value="${user.ipAddress}" />
				<span id="ipAddress_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "ipAddress_error"/></span>
			</td>
			<td colspan="3">&nbsp;</td>
		</tr>
	</table>
	<div class="tt col1 f16 ti15"><@spring.message "companyMessage"/></div>
	<table width="" border="0" cellspacing="0" cellpadding="0"
		class=" tab9">
		<tr>
			<td width="145" align="right"><span class="col4 sp12">*</span>
				<@spring.message "company"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="company" name="company" class="in2"
				type="text" value="${user.company}" />
			<span id="company_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "company_error"/></span>	
			</td>
				
			<td width="145" align="right"><@spring.message "companyAddress"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="companyAddress" name="companyAddress"
				class="in2" type="text" value="${user.companyAddress}" /></td>
				
			<td width="145"align="right"><@spring.message "lawyerName"/></td>
			<td width="18">&nbsp;</td>
			<td width="195"><input id="lawyerName" name="lawyerName" class="in2"
				type="text" value="${user.lawyerName}" />
			</td>
		</tr>
		<tr>

			<td align="right"><@spring.message "lawyerPhone"/></td>
			<td>&nbsp;</td>
			<td><input id="lawyerPhone" name="lawyerPhone" class="in2"
				type="text" value="${user.lawyerPhone}" />
				<span id="lawyerPhone_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_error"/></span>	
			</td>
			<td align="right"><@spring.message "lawyerMail"/></td>
			<td>&nbsp;</td>
			<td><input id="lawyerMail" name="lawyerMail" class="in2"
				type="text" value="${user.lawyerMail}" />
				<span id="lawyerMail_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_error"/></span>	
			</td>
			<td align="right"><@spring.message "companyCode"/></td>
			<td>&nbsp;</td>
			<td><input id="companyCode" name="companyCode" class="in2"
				type="text" value="${user.companyCode}" />
				<span id="companyCode_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_error"/></span>	
			</td>
		</tr>
		<tr>
			<td align="right"><@spring.message "companyFax"/></td>
			<td>&nbsp;</td>
			<td><input id="companyFax" name="companyFax" class="in2"
				type="text" value="${user.companyFax}" />
				<span id="companyFax_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_error"/></span>	
			</td>
			<td align="right"><@spring.message "companyIp"/></td>
			<td>&nbsp;&nbsp;</td>
			<td><input id="companyIp" name="companyIp" class="in2"
				type="text" value="${user.companyIp}" />
			<span id="companyIp_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "ipAddress_error"/></span>	
			</td>
			<td align="right"><span class="col4 sp12">*</span> <@spring.message "orgId"/></td>
			<td>&nbsp;</td>
			<td><input id="orgId" name="orgId" class="in2" type="text"
				value="${user.orgId}" />
			<span id="orgId_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "orgId_error"/></span>		
			</td>
			<td colspan="3">&nbsp;</td>
		</tr>
	</table>
	<div class="tt col1 f16 ti15"><@spring.message "otherMessage"/></div>
	<div class="in-box">
		<span class="label"><@spring.message "country"/></span> <select id="country" class="info"
			name="country" onChange="changeCountry();">
			<option value="-1">--请选择--</option>
			 <#if countryList?? &&(countryList?size > 0)> 
			    <#list countryList as countryInfo>
			    <option value="${countryInfo.gid}"<#if user.country?? &&
				user.country=countryInfo.gid>selected</#if>>${countryInfo.name}</option>
			    </#list>
			 </#if>
		</select> <span class="label"><@spring.message "province"/></span> <select id="province" name="province"
			class="info" onChange="changeProvince();" value="${user.province}">
			
		</select> <span class="label"><@spring.message "city"/></span> <select id="city" name="city"
			class="info" onChange="changeCity();"> 
		</select> <span class="label"><@spring.message "county"/></span> <select id="county" name="county"
			class="info" onChange=""> 
		</select>
	</div>

	<div class="form-actions">
		<button type="button" class="btn blue" onclick="saveUserMessage()"><@spring.message "save"/></button>
		<button type="button" class="btn blue" onclick="lockCancle()"><@spring.message "cancle"/></button>
	</div>
	<div class="clear"></div>

</form>
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/service/userMessage.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
