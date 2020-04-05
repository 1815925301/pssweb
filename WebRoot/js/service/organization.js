/**
 * 组织机构管理
 */
var  currentTreeNode,currentNodeName;
var addindex,showindex,editindex;
$(document).ready(function() {
	changeLang_js();
    $.jgrid.defaults.styleUI="Bootstrap";
	//菜单表
	$("#listOrg").jqGrid({
	 	url:ctx+'/getOrganization.do',
		datatype: "json",
		postData:{
			orgId:currentTreeNode
		},
		mtype:"POST",
		autowidth:true,
	 	colNames:['',$.i18n.prop('jigouname'), $.i18n.prop('creattime'),$.i18n.prop('creatpeople'),$.i18n.prop('jigouuser')],
	 	colModel:[	 		
            {name:'id',index:'id',hidden:true},
	 		{name:'orgName',index:'orgName',formatter:formatAct,align:'center', width:110,sortable:false},
	 		{name:'createTime',index:'createTime',align:'center',width:70,sortable:false},
	 		{name:'createUserName',index:'createUserName',align:'center', sortable:false},
	 		{name:'',index:'',align:'center', sortable:false}
	 	],
	 	rowNum:100,
	 	height:'100%',//250,
	 	rownumbers:true,
	 	pager: '#plistOrgs',
	    viewrecords: true,
	    multiselect: true,
//	    onSelectRow: function (rowId, status, e) {       
//	        var rowIds = jQuery("#table1").jqGrid('getGridParam', 'selarrrow');    
//	    },
	    loadui:"disable",
	    caption: '<div class="widget_option"><div align="">'
	    	+'<button type="button" class="btn blue" id="showOrgInfo"><i class="fa fa-train"></i>&nbsp;'+$.i18n.prop('view')+'</a></button>&nbsp;'
			+'<button type="button" class="btn blue" id="addOrgInfo"><i class="fa fa-plus"></i>&nbsp;'+$.i18n.prop('add')+'</a></button>&nbsp;'
			+'<button type="button" class="btn blue" id="updateOrgInfo"><i class="fa fa-edit"></i>&nbsp;'+$.i18n.prop('modify')+'</a></button>&nbsp;'
			+'<button type="button" class="btn blue" id="removeOrgInfos"><i class="fa fa-close"></i>&nbsp;'+$.i18n.prop('delete')+'</a></button>&nbsp;'
			+'<div></div>'
	});
	
	function formatAct(cellvalue, options, rowObject){
		var html='<a id="orgsInfo_'+rowObject.id+'" onclick="showOrgInfo('+rowObject.id+')">'+rowObject.orgName+'</a>';
		return html;
	}
	
	//------------功能树开始--------------
	var setting = {
	   async: {
			autoParam:["id","pid","name"], //需要传递的参数,为你在ztree中定义的参数名称
	   },	
		view: {
			dblClickExpand: false,
			showLine: false,
			expandSpeed: "slow"
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: 0
			}
		},
		callback: {
			onClick: treeNodeClick
		}
	};

	function showIconForTree(treeId, treeNode) {
		return !treeNode.isParent;
	};
	function treeNodeClick(event, treeId, treeNode, clickFlag) {
		currentTreeNode=treeNode.id;
		currentNodeName=treeNode.name;
		jQuery("#listOrg").setGridParam({
			url:ctx+'/getOrganization.do', 
			postData:{orgId:treeNode.id }
		}).trigger("reloadGrid");
	}	;
	
	//加载菜单树
	jQuery.ajax({
		type : 'POST',
	    url :ctx +'/getOrganizationTree.do',
	    dataType : 'json',
	    data : {
	    },
	    success : function(data) {
	    	if (data && data.status == "success") {
	    		var zNodes = data.data;	    	
		    	$(document).ready(function(){
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					//expandNode();
				});
			} else {
				$('#treeDemo').html(data.data);
			}
	    },
	    error : function(data) {
	    	alert("error");
	    }
	});
	
	//展开树
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
     	var nodes = zTree.getNodes();
     	var childNodes;
		for (var i=0, l=nodes.length; i<l; i++) {
			zTree.expandNode(nodes[i]);
			childNodes = nodes[i].children;
			if(childNodes != undefined){
				for (var j=0, m=childNodes.length; j<m; j++) {
					zTree.expandNode(childNodes[j]);
				}
			}
		}
	};
	
	//查看按钮
	$('#showOrgInfo').click(function(){
		var orgId;
		var selectedIds = $("#listOrg").jqGrid("getGridParam", "selarrrow");
		if(currentTreeNode==null&&selectedIds.length==0){
			layer.alert($.i18n.prop('chooseoneview'));
			return false;
		}else if(selectedIds.length>1){
			layer.alert($.i18n.prop('onlychooseoneview'));
			return false;
		}else if(selectedIds.length==1){
			var relrow = $("#listOrg").jqGrid('getGridParam','selrow');
	    	var rowDatas = $("#listOrg").jqGrid('getRowData', relrow);
	    	orgId = rowDatas["id"];
		}else{
			orgId=currentTreeNode;
		}
		showOrgInfo(orgId);
	});
	
	//新增按钮
	$('#addOrgInfo').click(function(){
		if(currentNodeName==null){
			layer.alert($.i18n.prop('chooseoneadd'));
			return false;
		}else{
			$('#orgl_add_name').html($.i18n.prop('wei')+currentNodeName+$.i18n.prop('addjigou'));
			addindex=layer.open({
				title:$.i18n.prop('addjigou'),
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['480px', '320px'], //宽高
			    content: $('#newOrgWin').html()
			});
		}
	});
	
	
	//修改按钮
	$('#updateOrgInfo').click(function(){
		var orgId;
		var selectedIds = $("#listOrg").jqGrid("getGridParam", "selarrrow");
		if(currentTreeNode==null&&selectedIds.length==0){
			layer.alert($.i18n.prop('selectonedateoption'));
			return false;
		}else if(selectedIds.length>1){
			layer.alert($.i18n.prop('onlychooseonedateoption'));
			return false;
		}else if(selectedIds.length==1){
			var relrow = $("#listOrg").jqGrid('getGridParam','selrow');
	    	var rowDatas = $("#listOrg").jqGrid('getRowData', relrow);
	    	orgId = rowDatas["id"];
	    	currentNodeName=rowDatas["orgName"];
		}else{
			orgId=currentTreeNode;
		}
		jQuery.ajax({
				type : 'POST',
			    url : ctx + '/showOrgInfo.do',
			    dataType : 'json',
			    data : {
			    	orgId:orgId
			    },
			    success : function(data) {
			    	if(data.status=='success'){
			    		$('#orgl_edit_name').html($.i18n.prop('edit')+currentNodeName+$.i18n.prop('information'));
			    		$('#edit_orgl_name').attr('value',data.data.orgName);
			    		$('#edit_orgl_note').attr('value',data.data.note);
						editindex=layer.open({
							title:$.i18n.prop('editjigou'),
						    type: 1,
						    area: ['480px', '320px'], //宽高
						    content: $('#editOrgWin').html()
						});
			    	}
			    },
			    error:function(){
			    }
		});
	});
	
	//删除按钮  
	$('#removeOrgInfos').click(function(){
		var orgId;
		var delId="";
		var delName="";
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var selectedIds = $("#listOrg").jqGrid("getGridParam", "selarrrow");
		if(currentTreeNode==null&&selectedIds.length==0){
			layer.alert($.i18n.prop('selectonedateoption'));
			return false;
		}else if(selectedIds.length){
			var rowData = jQuery('#listOrg').jqGrid('getGridParam','selarrrow');
		    if(rowData.length) {
		        for(var i=0;i<rowData.length;i++) {
		           var id= jQuery('#listOrg').jqGrid('getCell',rowData[i],'id');
		           var n= jQuery('#listOrg').jqGrid('getCell',rowData[i],'orgName');
		           var node = treeObj.getNodesByParam("id", id, null);
		           if(node!=null){
		   				if(node.children!=null){
		   					if(node.children.length>0){
		   						layer.alert($.i18n.prop('deletedom'));
		   						return false;
		   					}
		   		         }else{
		   		        	delId=id+","+delId;
		   		        	delName=n+","+delName;
		   		         }
		   			}
		        }
		        if(delId.length){
		        	 delId=delId.substring(0,delId.length-1);  
		        	 delName=delName.substring(0,delName.length-1);  
		        }
		    }
		}else{
			orgId=currentTreeNode;
			var srcNode = treeObj.getSelectedNodes();
			var isNode = srcNode[0];
			if(isNode!=null){
				if(isNode.children!=null){
					if(isNode.children.length>0){
			        	 layer.alert($.i18n.prop('deletedom'));
			        	 return false;
			         }
				}else{
					delId=currentTreeNode;
					delName=currentNodeName;
				}
			}
	}
		
		var delIndex=layer.confirm($.i18n.prop('confirmdeletejigou')+delName+'？', {
		    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle'] //按钮
		}, function(){
			layer.msg($.i18n.prop('deleting'),{time:500});
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/removeOrgInfo.do',
			    dataType : 'json',
			    data : {
			    	orgId:delId
			    },
			    success : function(data) {
			    		layer.alert(data.data, {
			    		    icon: 1,
			    		    skin: 'layer-ext-moon' 
			    		});
			    },
			    error:function(){
			    		layer.alert($.i18n.prop('deletefaile'), {
			    		    icon: 1,
			    		    skin: 'layer-ext-moon' 
			    		});
			    	} 
			});
		}, function(){
		    layer.close(delIndex);
		});
	});
});


//保存新机构
function SaveNewOrg(){
	//if(!checkOrgName($('.layui-layer-content').find("input[id='new_orgl_name']"))){
		$.ajax({
			type : 'POST',
		    url :ctx +'/saveNewOrgInfo.do',
		    dataType : 'json',
		    data : {
		    	pid:currentTreeNode,
		    	orgName:$('.layui-layer-content').find("input[id='new_orgl_name']").val(),
		    	note:$('.layui-layer-content').find("input[id='new_orgl_note']").val()
		    },
		    success : function(data) {
		    	if(data.status=='success'||data.status=='error'){
		    		layer.alert(data.data);
		    		ShowAddClose();
		    	}else{
		    		layer.alert($.i18n.prop('addfaile'));
		    	}
		    },
		    error:function(){
		    	layer.alert($.i18n.prop('loadingwrong'));
		    }
		});
//	}
}

//编辑保存按钮
function SaveEditOrg(){
	if(!checkOrgName($('.layui-layer-content').find("input[id='edit_orgl_name']"))){
		$.ajax({
			type : 'POST',
		    url :ctx +'/changeOrgInfo.do',
		    dataType : 'json',
		    data : {
		    	id:currentTreeNode,
		    	orgName:$('.layui-layer-content').find("input[id='edit_orgl_name']").val(),
		    	note:$('.layui-layer-content').find("input[id='edit_orgl_note']").val()
		    },
		    success : function(data) {
		    	if(data.status=='success'||data.status=='error'){
		    		layer.alert(data.data);
		    	}else{
		    		layer.alert($.i18n.prop('addfaile'));
		    	}
		    },
		    error:function(){
		    	layer.alert($.i18n.prop('loadingwrong'));
		    }
		});
	}
}

//关闭详细信息展示框
function ShowCancleClose(){
	layer.close(showindex);
}
function ShowAddClose(){
	layer.close(addindex);
}
function ShowEditClose(){
	layer.close(editindex);
}
//公共展示页
function showOrgInfo(orgId){
	$.ajax({
		type : 'POST',
	    url :ctx +'/showOrgInfo.do',
	    dataType : 'json',
	    data : {
	    	orgId:orgId
	    },
	    success : function(data) {
	    	$('#orgl_name').html(data.data.orgName);
	    	$('#orgl_creatuser').html(data.data.createUserName);
	    	$('#orgl_createdate').html(data.data.createTime);
	    	$('#orgl_usercount').html($.i18n.prop('worki'));
	    	$('#orgl_note').html(data.data.note);
	    	showindex=layer.open({
				title:$.i18n.prop('viewjigou'),
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['480px', '380px'], //宽高
			    content: $('#showOrgWin').html()
			});
	    },
	    error:function(){
	    	layer.alert($.i18n.prop('loadingwrong'));
	    }
	});
}

//检查机构名
function checkOrgName(obj){
	if($(obj).val()==''){
		layer.tips($.i18n.prop('jigounonull'),$(obj) );
		return false;
	}
	return true;
}
//检查备注
function checkNote(obj){
	if($(obj).val().length > 120) {
		layer.tips($.i18n.prop('notelength'), obj);
        return false;
	}
	return true;
}