<#include "top.ftl">


<!-- BEGIN PAGE CONTAINER-->  
<script src="js/service/userMessage.js" type="text/javascript"></script>
<div class="gr_w">
<div class=" clear"></div>

<div class="xw_mbx" style="margin-right: 20px;"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./PersonalCenter.do"><@spring.message "personal_center"/></a></div>




<div class="gr_t"><span><img src="imgnew/icon10.png" width="32" height="32" /></span><@spring.message "orderreminder"/>

<span style="float: right;color:${color};" id="vip" onclick="getVip('${color }')"><img src="imgnew/gold.png" width="32" height="32">

	<#if user.roleName=="System">DiamondMember

<#else>${user.roleName }</#if></span>
<span style="float: right"><@spring.message "isdownloads"/>:${cont}/${user.downloads }</span>
</div>
<div class="clear"></div>
<div class="ddtx">
<ul>
<li class="wc"><a href="${ctx }/orderMain.do?methodmove=1&_orderState=1"><@spring.message "CompletedOrders"/>（${num1}）</a></li>
<li class="cl"><a href="${ctx }/orderMain.do?methodmove=1&_orderState=5"><@spring.message "OrderCanceled"/>（${num5}）</a></li>
<li class="qx"><a href="${ctx }/orderMain.do?methodmove=1&_orderState=4"><@spring.message "PendingOrder"/>（${num4}）</a></li>
<li class="dcl"><a href="${ctx }/orderMain.do?methodmove=1&_orderState=3"><@spring.message "ProcessingOrder"/>（${num3}）</a></li>
<li class="sb"><a href="${ctx }/orderMain.do?methodmove=1&_orderState=2"><@spring.message "FailedOrders"/>（${num2}）</a></li>
</ul>
</div>
<div class="clear"></div>

<div>

<div class="gr_t"><span><img src="imgnew/icon11.png" width="32" height="32" /></span><@spring.message "userMessageTitle"/></div>

<form id="userForm" action="${ctx}${currentPageUrl}" method="post">
				<div class="jbxxf"><span>*</span><@spring.message "userName"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.userName}"  name="" type="text" /></div>
				<div class="jbxxf"><span>*</span><@spring.message "trueName"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.trueName}"  name="trueName" type="text" />
				<span id="trueName_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "trueName_error"/></span>
				</div>	
				<div class="jbxxf"><span></span><@spring.message "userTitle"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.userTitle}"  name="userTitle" type="text" />
				<span id="userTitle_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "userTitle_error"/></span>
				</div>
				
				<div class="clear"></div>
				<div class="jbxxf"><span>*</span><@spring.message "password"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.password}"  name="password" type="password" />
				<span id="password_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "password_error"/></span>
				</div>
				<div class="jbxxf"><span>*</span><@spring.message "againPassword"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.password}"  name="againpassword" type="password" />
				<span id="apassword_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "apassword_error"/></span>
				</div>
				<div class="jbxxf"><span></span><@spring.message "passprompt"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.passprompt}"  name="passprompt" type="text" /></div>
				
				<div class="clear"></div>
				<div class="jbxxf"><span></span><@spring.message "passanswer"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.passanswer}"  name="passanswer" type="text" /></div>
				<div class="jbxxf"><span>*</span><@spring.message "userSex"/></div>
				<div class="jbxxinput">
					<select id="usersex" name="userSex">
						<option value="1"<#if user.userSex?? &&
							user.userSex=1>selected</#if>><@spring.message "man"/></option>
						<option value="2"<#if user.userSex?? &&
							user.userSex=2>selected</#if>><@spring.message "woman"/></option>
					</select>
				</div>
				<div class="jbxxf"><span>*</span><@spring.message "identityCard"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.identityCard}" id="identityCard" name="identityCard" type="text" />
					<span id="identitycard_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "identitycard_tip"/></span>
					<span id="identitycard_error"Style="color: red; display:none; font-size: 12pt;"><@spring.message "identitycard_error"/></span>
				</div>
				
				<div class="clear"></div>
				<div class="jbxxf"><span>*</span><@spring.message "postcode"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.postcode}"  id = "postcode" name="postcode" type="text" />
					<span id="postcode_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_tip"/></span>
					<span id="postcode_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_error"/></span>
				</div>
				<div class="jbxxf"><span>*</span><@spring.message "userPhone"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.userPhone}""  id ="userPhone" name="userPhone" type="text" />
					<span id="phone_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_tip"/></span>
					<span id="phone_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_error"/></span>
				</div>
				<div class="jbxxf"><span>*</span><@spring.message "userEmail"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.userEmail}""  name="userEmail" type="text" />
					<span id="email_tip"  Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_tip"/></span>
					<span id="email_error"  Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_error"/></span>
				</div>
				
				<div class="clear"></div>
				<div class="jbxxf"><span>*</span><@spring.message "fax"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.fax}"  id="faxfax" name="fax" type="text" />
					<span id="fax_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_tip"/></span>
					<span id="fax_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_error"/></span>
				</div>
				<div class="jbxxf"><span>*</span><@spring.message "telephone"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.userMobile}"  id="userMobile" name="userMobile" type="text" />
					<span id="mobile_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "mobile_error"/></span>
				</div>
				<div class="jbxxf"><span></span><@spring.message "address"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.address}"  name="address" type="text" />
					<span id="address_tip" Style="color: red; display:none; font-size: 12pt;"><@spring.message "address_tip"/></span>
				</div>
				<div class="clear"></div>
				<div class="jbxxf"><span></span><@spring.message "ipAddress"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.ipAddress}"  name="ipAddress" type="text" />
					<span id="ipAddress_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "ipAddress_error"/></span>
				</div>
		<div class="clear"></div>		
<div class="gr_t"><span><img src="imgnew/icon12.png" width="32" height="32" /></span><@spring.message "companyMessage"/></div>
<div class="clear"></div>
				<div class="jbxxf"><span></span><@spring.message "company"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.company}"  name="company" type="text" />
					<span id="company_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "company_error"/></span>
				</div>
				<div class="jbxxf"><span></span><@spring.message "companyAddress"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.companyAddress}"  name="companyAddress" type="text" /></div>
				<div class="jbxxf"><span></span><@spring.message "lawyerName"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.lawyerName}"  name="lawyerName" type="text" /></div>
				<div class="clear"></div>
				
				<div class="jbxxf"><span></span><@spring.message "lawyerPhone"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.lawyerPhone}"  name="lawyerPhone" type="text" />
					<span id="lawyerPhone_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "phone_error"/></span>
				</div>
				<div class="jbxxf"><span></span><@spring.message "lawyerMail"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.lawyerMail}"  name="lawyerMail" type="text" />
					<span id="lawyerMail_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "email_error"/></span>
				</div>
				<div class="jbxxf"><span></span><@spring.message "companyCode"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.companyCode}"  name="companyCode" type="text" />
					<span id="companyCode_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "postcode_error"/></span>
				</div>	
				<div class="clear"></div>
				
				<div class="jbxxf"><span></span><@spring.message "companyFax"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.companyFax}"  name="companyFax" type="text" />
					<span id="companyFax_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "fax_error"/></span>
				</div>	
				<div class="jbxxf"><span></span><@spring.message "companyIp"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.companyIp}"  name="companyIp" type="text" />
					<span id="companyIp_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "ipAddress_error"/></span>
				</div>	
				<div class="jbxxf"><span></span><@spring.message "orgId"/></div>
				<div class="jbxxinput"><input style="height: 30px;" value="${user.orgId}"  name="orgId" type="text" />
					<span id="orgId_error" Style="color: red; display:none; font-size: 12pt;"><@spring.message "orgId_error"/></span>
				</div>	
				<div class="clear"></div>
				
				
<div class="gr_t"><span><img src="imgnew/icon18.png" width="30" height="30" /></span><@spring.message "otherMessage"/></div>
<div class="clear"></div>
<div class="clear"></div>
				<div class="jbxxf"></div>
				<div class="jbxxinput">
					<span class="label"><@spring.message "country"/></span> 
					<select id="country" class="info" name="country" onChange="changeCountry();">
						<option value="-1">--请选择--</option>
							 <#if countryList?? &&(countryList?size > 0)> 
							    <#list countryList as countryInfo>
							    <option value="${countryInfo.gid}"<#if user.country?? &&
								user.country=countryInfo.gid>selected</#if>>${countryInfo.name}</option>
							    </#list>
							 </#if>
					</select> 
					<span class="label"><@spring.message "province"/></span> 
						<select id="province" name="province" class="info" onChange="changeProvince();" value="${user.province}">
						</select> 
						<span class="label"><@spring.message "city"/></span> 
						<select id="city" name="city" class="info" onChange="changeCity();"> 
						</select> 
					<span class="label"><@spring.message "county"/></span> 
					<select id="county" name="county" class="info" onChange=""> 
					</select>
				</div>
	<div class="clear"></div>
				<div class="bcqx">
						<a onclick="saveUserMessage()"><@spring.message "save"/></a>
						<a onclick="lockCancle()" class="qxbc"><@spring.message "cancle"/></a>
				</div>
	<div class="clear"></div>
<div class="clear"></div>
</form>
</div>

<!-- 配置信息修改窗口 end -->
<#include "foot.ftl">

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>



<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>