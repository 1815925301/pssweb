/**
 * 功能管理JS代码
 * author: mrajian
 * createDate: 2013-10-17 19:24:45
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;
$(document).ready(function() {
	changeLang_js();
 $.jgrid.defaults.styleUI="Bootstrap";
	//菜单表
	$("#listResources").jqGrid({
	 	url:ctx+'/getResourceList.do',
		datatype: "json",
		postData:{
			pId :'0', //加载根节点下单位
			moduleId:'0'
		},
		mtype:"POST",
		autowidth:true,
	 	colNames:['',$.i18n.prop('gongnengcaidanname'),$.i18n.prop('gongnengcaidanurl') , $.i18n.prop('num'),$.i18n.prop('tubiap')],
	 	colModel:[	 		
            {name:'id',index:'id',hidden:true},
	 		{name:'resourceName',index:'resourceName',align:'left', width:'25%',sortable:false},
	 		{name:'resourceUrl',index:'resourceUrl',align:'left', width:'25%',sortable:false},
	 		{name:'sortNum',index:'sortNum',align:'left',width:'25%',sortable:false},
	 		{name:'icon',index:'icon',align:'left',width:'25%',sortable:false}
	 		//{name:'',index:'',formatter:formatAct,align:'center', sortable:false}
	 	],
	 	rowNum:15,
	 	height:'100%',//250,
	 	rownumbers:true,
	  //toolbar: [true,"top"],
	 	pager: '#plistResources',
	  //sortname: 'id',
	    viewrecords: true,
	  //sortorder: "desc",
	    multiselect: true,
	    loadui:"disable",
	    caption:'<table>' 
	        + '<tr><td><div class="widget_option"><div align="right"><a class="btn btcms btn-xs" id="showResourceInfo"><i class="fa fa-train"></i>&nbsp;'+$.i18n.prop('view')+'</a>&nbsp;'
			+'<a class="btn btcms btn-xs" id="addNewResourceInfo"><i class="fa fa-plus"></i>&nbsp;'+$.i18n.prop('add')+'</a>&nbsp;'
			+'<a class="btn btcms btn-xs" id="updateResourceInfo"><i class="fa fa-edit"></i>&nbsp;'+$.i18n.prop('modify')+'</a>&nbsp;'
			+'<a class="btn btcms btn-xs" id="removeResourceInfo"><i class="fa fa-close"></i>&nbsp;'+$.i18n.prop('delete')+'</a>&nbsp;<div></div></td></tr></table>'
	});
	
	//------------功能树开始--------------
	var tmpTreeNode;
	
	var setting = {
	   async: {
				    enable: true,//启用异步加载
				    url:ctx +'/showResourceTreeInfo.do', //异步请求地址
				    autoParam:["id"] //需要传递的参数,为你在ztree中定义的参数名称
	   },	
		view: {
		//	showIcon: showIconForTree
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	};

	function showIconForTree(treeId, treeNode) {
		return !treeNode.isParent;
	};
	function onClick(event, treeId, treeNode, clickFlag) {
		//单击展开节点
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		tmpTreeNode = treeNode;
		/*zTree.reAsyncChildNodes(null, "refresh");*/
		/*$('#select_title').html(treeNode.name + "下属机构");
		console.log(jQuery("#listOrgs"));*/
		jQuery("#listResources").setGridParam({url:ctx+'/getResourceList.do', postData:{pId : treeNode.id, moduleId : treeNode.id }}).trigger("reloadGrid");
	}	
	
	//加载菜单树
	jQuery.ajax({
		type : 'POST',
	    url :ctx +'/showResourceTreeInfo.do',
	    dataType : 'json',
	    data : {
	    },
	    success : function(data) {
	    	if (data && data.status == "success") {
	    		var zNodes = data.data;	    	
		    	$(document).ready(function(){
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					//zTree.reAsyncChildNodes(null, "refresh");
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
	

	if(tmpTreeNode == undefined){
		tmpTreeNode = {id:0,name:$.i18n.prop('odergenjiedian')};
	};
	
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
	
	
	//点击新增菜单
	$('#addNewResourceInfo').click(function() {
		$('#resource_name').val("");
		$('#presource_name').attr('value',tmpTreeNode.name);
		$('#p_id').attr('value',tmpTreeNode.id);
		$('#resource_url').val("");
		$('#sort_num').val("");
		 $('#icon').val("");
		 $("#resource_name_error").html("");
		$("#resource_url_error").html("");
		 $("#sort_num_error").html("");
		 $("#icon_error").html("");
		
		newIndex=layer.open({
			title:$.i18n.prop('addcaidan'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '350px'], //宽高
		    content: $('#addNewResourceWin').html()
		});
	});
	
	//保存新增加的功能菜单信息
	/*$('#save').live("click", function() {
		//setButtonStatus("save", true); //设置按钮不可用
		
	});*/
	
	/*$('#save1').live("click", function() {
		
	});*/
	
	//查看功能菜单信息
    $("a[id*='showResourceInfo']").on('click', function() {
		var selectedIds = $("#listResources").jqGrid("getGridParam", "selarrrow");
    	if(selectedIds.length>0){
    		if(selectedIds.length>1){
    			layer.msg($.i18n.prop('comeon'),{time:500});
		    	setTimeout(function(){
		    		layer.alert($.i18n.prop('chooseonedateatleast'), {
		    		    icon: 1,
		    		    skin: 'layer-ext-moon' 
		    		});
		    	} ,500);
    		}else{
    			var relrow = $("#listResources").jqGrid('getGridParam','selrow');
    	    	var rowDatas = $("#listResources").jqGrid('getRowData', relrow);
    	    	var resourceId = rowDatas["id"];
    			jQuery.ajax({
    				type : 'POST',
    			    url : ctx + '/showResourceInfo.do',
    			    dataType : 'json',
    			    data : {
    			    	resourceId : resourceId
    			    },
    			    success : function(data) {
    			    	if (data && data.status == "success") {
    			    		$('#resource_label_resource_name').html(data.data.resourceName);
				    		$('#resource_label_resource_pname').html(tmpTreeNode.name);
				    		$('#resource_label_create_time').html(data.data.createTime);
				    		$('#resource_label_create_userName').html(data.data.createUserName);
				    		$('#resource_label_resource_url').html(data.data.resourceUrl);
				    		$('#resource_label_sort_num').html(data.data.sortNum);
				    		showIndex=layer.open({
								title:$.i18n.prop('viewcaidan'),
							    type: 1,
							    //skin: 'layui-layer-rim', //加上边框
							    area: ['480px', '380px'], //宽高
							    content: $('#showResourceWin').html()
							});
    					} else {
    						layer.msg($.i18n.prop('comeon'),{time:500});
    				    	setTimeout(function(){
    				    		layer.alert($.i18n.prop('loadingwrong'), {
    				    		    icon: 1,
    				    		    skin: 'layer-ext-moon' 
    				    		});
    				    	} ,500);
    					}
    			    },
    			    error : function(data) {
    			    	layer.msg($.i18n.prop('comeon'),{time:500});
    			    	setTimeout(function(){
    			    		layer.alert($.i18n.prop('loadingwrong'), {
    			    		    icon: 1,
    			    		    skin: 'layer-ext-moon' 
    			    		});
    			    	} ,500);
    			    }
    			});
    		
    		}
    	}else{
    		layer.msg($.i18n.prop('comeon'),{time:500});
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('chooseviewdata'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
    	}
	});
	
	//修改功能菜单信息 将功能菜单信息铺回到页面
    $("a[id*='updateResourceInfo']").on('click', function() {

		var selectedIds = $("#listResources").jqGrid("getGridParam", "selarrrow");
    	if(selectedIds.length>0){
    		if(selectedIds.length>1){
    			layer.msg($.i18n.prop('comeon'),{time:500});
		    	setTimeout(function(){
		    		layer.alert($.i18n.prop('chooseonedateedite'), {
		    		    icon: 1,
		    		    skin: 'layer-ext-moon' 
		    		});
		    	} ,500);
    		}else{
    			var relrow = $("#listResources").jqGrid('getGridParam','selrow');
    	    	var rowDatas = $("#listResources").jqGrid('getRowData', relrow); 
    	    	var resourceId = rowDatas["id"];
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/showResourceInfo.do',
				    dataType : 'json',
				    data : {
				    	resourceId : resourceId
				    },
				    success : function(data) {
				    	if (data && data.status == "success") {
				    		console.log(data);
				    		$('#change_resource_id').attr('value',resourceId);
				    		//alert(data.data.resourceName);
				    		$('#change_resource_name').attr('value',data.data.resourceName);
				    		$('#change_presource_name').attr('value',tmpTreeNode.name);
				    		$('#change_resource_url').attr('value',data.data.resourceUrl);
				    		$('#change_sort_num').attr('value',data.data.sortNum);
				    		$('#change_icon').attr('value',data.data.icon);
				    		editIndex=layer.open({
								title:$.i18n.prop('moditycandaninformation'),
							    type: 1,
							    //skin: 'layui-layer-rim', //加上边框
							    area: ['600px', '350px'], //宽高
							    content: $('#changeResourceWin').html()
							});
				    	} else {
				    		layer.msg($.i18n.prop('comeon'),{time:500});
					    	setTimeout(function(){
					    		layer.alert($.i18n.prop('loadingwrong'), {
					    		    icon: 1,
					    		    skin: 'layer-ext-moon' 
					    		});
					    	} ,500);
						}
				    },
				    error : function(data) {
				    	layer.msg($.i18n.prop('comeon'),{time:500});
				    	setTimeout(function(){
				    		layer.alert($.i18n.prop('loadingwrong'), {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} ,500);
				    }
				});
    		}
    	}else{
    		layer.msg($.i18n.prop('comeon'),{time:500});
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('chooseonedateedite'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
    	}
	});
	
	//保存所修改的功能菜单信息
	//$('#saveChange')[0].onclick= function() {
	//	saveChangeResources();
	//};
	
	
	
	//删除
	 $("a[id*='removeResourceInfo']").click( function() {
		 var selectedIds = $("#listResources").jqGrid("getGridParam", "selarrrow");
				if(selectedIds.length == 1){
					var resourceId = "";
					var resourceName = "";
					var v = $("#listResources").jqGrid('getRowData', selectedIds[0]);
					resourceId = v["id"];
					resourceName = v["resourceName"];
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+resourceName+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeResourceInfo.do',
				    dataType : 'json',
				    data : {
				    	resourceId : resourceId
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
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
				}else{
					layer.alert($.i18n.prop('chooseonedatadelete'), {
		    		    icon: 1,
		    		    skin: 'layer-ext-moon' 
		    		});
					
					}
	 });
	 
	
	
	//功能菜单名称校验
	 $('#resource_name').live('blur',function() {
        checkResourceName(this);
    });
	 $('#resource_name').live('change',function() {
	        checkResourceName(this);
	    });
    

	//功能菜单地址校验
	 $('#resource_url').live('blur',function() {
        checkResourceUrl(this);
    });
	 $('#resource_url').live('change',function() {
        checkResourceUrl(this);
    });
    
    //功能菜单名称校验
	 $('#change_resource_name').live('blur',function() {
	        checkResourceName(this);
	    });
	 $('#change_resource_name').live('change',function() {
		        checkResourceName(this);
		    });
    
    //功能菜单名称校验
	 $('#change_resource_url').live('blur',function() {
	        checkResourceUrl(this);
	    });
	$('#change_resource_url').live('change',function() {
	        checkResourceUrl(this);
	    });
    
    //功能菜单备注校验
	$('#sort_num').live('change',function() {
        checkSortNum(this);
    });
	$('#sort_num').live('blur',function() {
        checkSortNum(this);
    });
    
    //功能菜单备注校验
	$('#change_sort_num').live('change',function() {
        checkSortNum(this);
    });
	$('#change_sort_num').live('blur',function() {
        checkSortNum(this);
    });
   //  console.log($('.zTreeDemoBackground').parent().parent().parent().parent().parent().outerHeight());
	//$('.zTreeDemoBackground').attr("style","OVERFLOW-Y:auto;OVERFLOW-X:hidden;min-height:"+$('.zTreeDemoBackground').parent().parent().parent().parent().parent().outerHeight()+"px");
	
});

//==========================================
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，功能菜单名称与功能菜单类型
//==========================================
function clearSearchInput() {
	$("#resourceName").val("");
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#resourceName").val(replaceSpecialStr(Trim($("#resourceName").val())));
}

//==========================================
//Purpose: 验证功能菜单名称
//==========================================
function checkResourceName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('entercandanname'));
        return false;
    } else if ($(obj).val().length > 15) {
        seterror(obj, $.i18n.prop('gongnengcaidanbuneng'));
        return false;
    } else {
    	seterror(obj, "");
    }
}

//==========================================
//Purpose: 验证功能菜单类型信息
//==========================================
function checkSortNum(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enterpaixu'));
        return false;
    } else if ($(obj).val().length > 4) {
        seterror(obj,$.i18n.prop('paixubunengchaoguo'));
        return false;
    } else {
    	seterror(obj, "");
    }
}

//==========================================
//Purpose: 验证备注的输入
//==========================================
function checkResourceUrl(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enterdizhi'));
        return false;
    } else if ($(obj).val().length > 15) {
        seterror(obj, $.i18n.prop('caidandizhibuneng'));
        return false;
    } else {
    	seterror(obj, "");
    }
}
function checkEmpty(obj){
	 var objId = $(obj).attr("id");
	var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
	   if ($(Objects).val() != null && $(obj).val() != "" ){
		   return true;
	   }else{
		   return false;
	   }
}
//设置错误信息样式
function seterror(obj,error){
	var objId = $(obj).attr("id");
	var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
	var errorObj=$('.layui-layer-content').find("span[id='"+objId+"_error']");
	if(error!=""){
		$(Objects).parent().parent().removeClass("control-group").addClass("control-group error");
		$(errorObj).html(error);
	}else{
		$(Objects).parent().parent().removeClass("control-group error").addClass("control-group");
		$(errorObj).html('');
	}
}
function saveChangeResources(){

	//setButtonStatus("saveChange", true); //设置按钮不可用
	$('.layui-layer-content').find("input[id='change_resource_name']").blur();
	//$('#change_resource_type').blur();
	$('.layui-layer-content').find("input[id='change_resource_url']").blur();
	$('.layui-layer-content').find("input[id='change_sort_num']").blur();
	$('.layui-layer-content').find("input[id='change_icon']").blur();
	var is_error = false;
	var _error = ["change_resource_name", "change_resource_url", "change_sort_num", "change_icon"];
	$.each(_error, function(key, val) {
		var objId = $('#' + val).attr("id");
		var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
    	var error_flag = $(Objects).parent().parent().attr('class');
        if (error_flag != undefined)
        	if (error_flag.indexOf("error") >= 0) {
            	is_error = true;
            	return false;
            }
    });
	if (!is_error) {
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/changeResourceInfo.do',
	    dataType : 'json',
	    data : {
	    	id : $('.layui-layer-content').find("input[id='change_resource_id']").val(),
	    	resourceName : $('.layui-layer-content').find("input[id='change_resource_name']").val(),
	    	sortNum : $('.layui-layer-content').find("input[id='change_sort_num']").val(),
	    	resourceUrl : $('.layui-layer-content').find("input[id='change_resource_url']").val(),
	    	icon:$('.layui-layer-content').find("input[id='change_icon']").val()
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert($.i18n.prop('addsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
	    				layer.close(index);
	    				layer.close(newIndex);
	    			});
	    			location.reload();
				} else {
					$.each(data.data, function(i, item) {   
	              		seterror(item.key, item.value);
	            	});
	            	setButtonStatus("saveChangeResourceInfo", false); //设置按钮可用
				}
	    	}
	 	},
	    error : function(data) {}
	});
	}
}

function saveResources(){
	$('.layui-layer-content').find("input[id='resource_name']").blur();
	$('.layui-layer-content').find("input[id='resource_url']").blur();
	$('.layui-layer-content').find("input[id='sort_num']").blur();
	var is_error = false;
	var _error = ["resource_name", "resource_url", "sort_num", "icon"];
	$.each(_error, function(key, val) {
		var objId = $('#' + val).attr("id");
		var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
    	var error_flag = $(Objects).parent().parent().attr('class');
        if (error_flag != undefined)
        	if (error_flag.indexOf("error") >= 0) {
            	is_error = true;
            	return false;
            }
    });
	if (!is_error) {
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/saveNewResourceInfo.do',
	    dataType : 'json',
	    data : {
	    	resourceName : $('.layui-layer-content').find("input[id='resource_name']").val(),
	    	moduleId :   $('.layui-layer-content').find("input[id='p_id']").val(),
	    	pId :   $('.layui-layer-content').find("input[id='p_id']").val(),
	    	sortNum :  $('.layui-layer-content').find("input[id='sort_num']").val(),
	    	resourceUrl : $('.layui-layer-content').find("input[id='resource_url']").val(),
	    	isRestricted : 1,
	    	isMenu : 1,
	    	icon:$('.layui-layer-content').find("input[id='icon']").val()
	    		
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			layer.close(newIndex);
	    			alert($.i18n.prop('addsuccess'));
	    			location.reload();
				} else {
					$.each(data.data, function(i, item) {   
	              		seterror(item.key, item.value);
	            	});
	            	//setButtonStatus("save", false); //设置按钮可用
				}
	    	}
	 	},
	    error : function(data) {}
	});
	}
}
function cancleAddWin(){
	layer.close(newIndex);
}
//取消

function showcancleAddWin(){
	layer.close(showIndex);
}
function changecancleAddWin(){
	layer.close(editIndex);
}