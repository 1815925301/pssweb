<#include "top.ftl">
            <!--tab content 开始 --> 
		<!--	<div class="wrapper wrapper-content animated fadeInRight">
		        <div class="">
		            <div class="col-sm-12">
		                <div class="ibox float-e-margins">
							<div id="layout_count">
					            <div position="left">
					            	<div class="zTreeDemoBackground left" style="overflow:auto;">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
					            </div>
					            <div position="center" title="标题">
						            <h4>
						            
						            </h4>
						            <!-- jgrid 菜单列表  -->
					           <!-- jgrid 功能列表  -->
				<!--		<div class="" style="padding:10px 15px;width:100%">
							<table id="listResources"></table>
							<div id="plistResources"></div>
						</div>
					            </div>  
					        </div> 
					</div>
				</div>
			</div>
		</div> -->
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
						<table id="listResources"></table>
							<div id="plistResources"></div>
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
    <script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
  
     <script type="text/javascript">
                $(function (){
                    $("#layout_count").ligerLayout({ leftWidth: 200});
                });
         </script>     
<!-- 添加菜单窗口 start -->
<div class="row-fluid"  id='addNewResourceWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group">
								<label class="control-label">功能名称</label>
								<div class="controls">
									<input type="text" value="" class="m-wrap medium"  id="resource_name" name="resource_name" value="" />
									<span class="help-inline" id='resource_name_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">上级功能名称：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium" id="presource_name" name="presource_name" value="" readOnly="true"  />
									<input type="hidden" value=""  class="m-wrap medium" id="p_id" name="p_id" value="" />
									<span class="help-inline"  id='presource_name_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">功能地址：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id="resource_url" name="resource_url" value="" />
									<span class="help-inline"  id='resource_url_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">排序：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium" id="sort_num" name="sort_num" value="" />
									<span class="help-inline"  id='sort_num_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">图标：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id="icon" name="icon" value="" />
									<span class="help-inline"  id='icon_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="save1" onclick="saveResources();"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn" id='cancleAdd' onclick="cancleAddWin();">取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 添加菜单窗口 end -->         
<!-- 菜单信息展示窗口 start -->
<div class="row-fluid"  id='showResourceWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group">
								<label class="control-label">功能名称：</label>
								<label class="control-label" id="resource_label_resource_name"></label>
							</div>
							<div class="control-group">
								<label class="control-label">上级功能名称：</label>
								<label class="control-label"  id="resource_label_resource_pname"  ></label>
							</div>
							<div class="control-group">
								<label class="control-label">功能地址：</label>
								<label class="control-label"  id='resource_label_resource_url' '></label>
							</div>
							<div class="control-group">
								<label class="control-label">排序：</label>
								<label class="control-label"  id='resource_label_sort_num' ></label>
							</div>
							<div class="control-group">
								<label class="control-label">创建时间：</label>
								<label class="control-label"  id='resource_label_create_time' ></label>
							</div>
							<div class="control-group">
									<label class="control-label">创建用户：</label>
									<label class="control-label"  id='resource_label_create_userName' ></label>
							</div>
							<div class="form-actions">
								<button type="button" class="btn" id='showcancleAdd' onclick="showcancleAddWin();">取消</button>
							</div>
						</form>
				</div>
</div>
<!-- 菜单信息展示窗口 end -->

<!-- 菜单信息修改窗口 start -->
<div class="row-fluid"  id='changeResourceWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group">
								<label class="control-label">功能名称：</label>
								<div class="controls">
								<input type="hidden" value="" id="change_resource_id" />
									<input type="text"  class="m-wrap medium"  id="change_resource_name" name="change_resource_name" value=""  />
									<span class="help-inline" id='change_resource_name_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">上级功能名称：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id="change_presource_name" name="change_presource_name" value="" readOnly="true" />
									<span class="help-inline"  id='change_presource_name_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">功能地址：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id="change_resource_url" name="change_resource_url" value="" />
									<span class="help-inline"  id='change_resource_url_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">排序：</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium" id="change_sort_num" name="change_sort_num" value="" />
									<span class="help-inline"  id='change_sort_num_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">图标</label>
								<div class="controls">
									<input type="text" value=""  class="m-wrap medium"  id="change_icon" name="change_icon" value=""/>
									<span class="help-inline"  id='change_icon_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onClick="saveChangeResources()" id="saveChange"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn" id='changecancleAdd' onclick="changecancleAddWin();">取消</button>
							</div>
						</form>
				</div>
	 		</div>
</div>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

<!-- 菜单信息修改窗口 end -->
