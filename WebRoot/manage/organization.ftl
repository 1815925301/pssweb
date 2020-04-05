<#include "top.ftl">
<div>
		<div class="cms-tree-div-t">
		<!--	<div class="container-fluid cms-tree-div-tback">
					<div class="main-hd">
					111111
					</div>
			 </div>-->
			 <div class="row-fluid">
					<div class="span12">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
			</div>
		</div> 
		<div  class="cms-tree-div-b">
			<!--<div class="container-fluid cms-tree-div-bback">
					<div class="main-hd">
					333333
					</div>
			 </div>-->
			<!-- <div class="row-fluid" style="min-height:600px;">-->
						<div class="" style="padding:10px 15px;width:100%">
							<table id="listOrg"></table>
					      <div id="plistOrgs"></div>
						</div>
			<!-- </div>-->
		</div> 
</div>

<link href="css/plugins/ligerUI/skins/Aqua/css/ligerui-layout.css" rel="stylesheet" type="text/css" />
   <link href="css/plugins/zTreeStyle/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">
   	<!-- jqGrid -->
     <link href="css/plugins/jqgrid/ui.jqgrid.css@0820" rel="stylesheet" type="text/css">
<#include "foot.ftl">
   
   
  <script src="js/plugins/ligerUI/js/core/base.js" type="text/javascript"></script>
  <script src="js/plugins/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
  <script src="js/plugins/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
  

    <script src="js/plugins/jqgrid/i18n/grid.locale-cn.js@0820"></script>
    <script src="js/plugins/jqgrid/jquery.jqGrid.min.js@0820"></script>
  
     <script type="text/javascript">
                $(function (){
                    $("#layout_count").ligerLayout({ leftWidth: 200});
                });
         </script>     
         
<!-- 添加功能窗口 start -->
<div class="row-fluid"  id='newOrgWin'  style="display:none">
			<div class="tab-content">
				<form action="#" class="form-horizontal">
					<table class="tableWin" border="1">
						<tr>
							<td colspan='2' class="top" align ='center'><label id="orgl_add_name"></label></td>
						</tr>
						<tr>
						  <td class='leftTd' width='40%'>机构名称：</td>
						  <td width='60%'><input type="text" value=""  class="m-wrap medium"  id='new_orgl_name'  /></td>
						</tr>
						<tr>
						  <td class='leftTd'>备注：</td>
						  <td><input type="text" value=""  class="m-wrap medium"  id='new_orgl_note'  /></td>
						</tr>
					</table>
					<div class="form-actions ">
						<button type="button" class="btn blue" id='saveNewOrg' onClick="SaveNewOrg()">保存</button>
						<button type="button" class="btn blue" id='showcancleClose' onClick="ShowAddClose()">取消</button>
					</div>
				</form>
		</div>
</div>
<!-- 添加功能窗口 end -->

<!-- 功能信息修改窗口 start -->
<div class="row-fluid"  id='editOrgWin'  style="display:none">
			<div class="tab-content">
				<form action="#" class="form-horizontal">
					<table class="tableWin" border="1">
						<tr>
							<td colspan='2' class="top" align ='center'><label id="orgl_edit_name"></label></td>
						</tr>
						<tr>
						  <td class='leftTd' width='40%'>机构名称：</td>
						  <td width='60%'><input type="text"  class="m-wrap medium"  id='edit_orgl_name'  /></td>
						</tr>
						<tr>
						  <td class='leftTd'>备注：</td>
						  <td><input type="text"   class="m-wrap medium"  id='edit_orgl_note'  /></td>
						</tr>
					</table>
					<div class="form-actions ">
						<button type="button" class="btn blue" id='saveEditOrg' onClick="SaveEditOrg()">保存</button>
						<button type="button" class="btn blue" id='showcancleClose' onClick="ShowEditClose()">取消</button>
					</div>
				</form>
		</div>
</div>
<!-- 功能信息修改窗口 end -->


<!-- 站点信息展示窗口 start -->
<div class="row-fluid"  id='showOrgWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<table class="tableWin" border="1">
								<tr>
								  <td class='leftTd' width='40%'>机构名称：</td><td width='60%'><label id="orgl_name"></label></td>
								</tr>
								<tr>
								  <td class='leftTd'>机构创建人：</td><td><label id="orgl_creatuser"></label></td>
								</tr>
								<tr>
								  <td class='leftTd'>创建时间：</td><td><label id="orgl_createdate"></label></td>
								</tr>
								<tr>
								  <td class='leftTd'>机构现有用户：</td><td><label id="orgl_usercount"></label></td>
								</tr>
								<tr>
								  <td class='leftTd'>备注：</td><td><label id="orgl_note"></label></td>
								</tr>
								<tr>
								</table>
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleClose' onClick="ShowCancleClose()">取消</button>
							</div>
						</form>
				</div>
</div>
<!-- 站点信息展示窗口 end -->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

