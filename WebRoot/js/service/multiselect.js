var grow = document.getElementById("selectDistributorProv").getElementsByTagName("option").length; //组数
var showGrow = 0;//已打开组
var selectCount = 0; //已选数量 
var carTypeItems = document.getElementById("selectCarType").getElementsByTagName("input");
var carTypeSureItems = document.getElementById("makeSureCarTypeItem").getElementsByTagName("input");
var distributorItems = document.getElementById("selectDistributor").getElementsByTagName("input");
var distributorCheckedItems = document.getElementById("previewDistributorItem").getElementsByTagName("input");
var distributorSureItems = document.getElementById("makeSureDistributorItem").getElementsByTagName("input");

function openBg(state){ //遮照打开关闭控制
	if(state == 1) {
		document.getElementById("bg").style.display = "block";
		var h = document.body.offsetHeight > document.documentElement.offsetHeight ? document.body.offsetHeight : document.documentElement.offsetHeight;
		document.getElementById("bg").style.height = h + "px";
	} else {
		document.getElementById("bg").style.display = "none";
	}
}

function openSelect(state, name){ //选择城市层关闭打开控制
	if(state == 1) {
		document.getElementById(name).className = "show";
		if (name == "selectCarTypeItem") {
			for(var i = 0 ; i < carTypeItems.length ; i++) {
				carTypeItems[i].checked = false;
			}
			addPreItem(name);
			for(var i = 0 ; i < carTypeSureItems.length ; i++) {
				if (carTypeSureItems[i].checked == true) {
					for(var j = 0 ; j < carTypeItems.length ; j++) {
						if(carTypeSureItems[i].value == carTypeItems[j].value)
							carTypeItems[j].checked = true;
					}
				}
			}
			addPreItem(name);
		} else if (name == "selectDistributorItem") {
			for(var i = 0 ; i < distributorItems.length ; i++) {
				distributorItems[i].checked = false;
			}
			addPreItem(name);
			for(var i = 0 ; i < distributorSureItems.length ; i++) {
				if (distributorSureItems[i].checked == true) {
					for(var j = 0 ; j < distributorItems.length; j++) {
						if(distributorSureItems[i].value == distributorItems[j].value)
							distributorItems[j].checked = true;
					}
				}
			}
			addPreItem(name);
		}
	} else {
		document.getElementById(name).className = "hidden";
		if (name == "selectCarTypeItem") {
			checkCarType($('#carTypeSelect'));
		} else if (name == "selectDistributorItem") {
			checkDistributor($('#distributorSelect'));
		}
	}
}

function checkAll(obj, name) {
	if (name == "distributor") {
		var el = document.getElementById("distributorItemInputDiv").getElementsByTagName("input");
		if (obj.checked == true) {
			for (var i=0; i < el.length; i++) {
				if (el[i].type == "checkbox") {
				//	if ($(el[i]).parent("span").css("display") == "inline")
						el[i].checked = true;
				//	else continue;
				}
			}
		} else {
			for (var i=0; i < el.length; i++) {
				if (el[i].type == "checkbox") {
				 //  if ($(el[i]).parent("span").css("display") == "inline")
						el[i].checked = false;
				//	else continue;
				}
			}
		}
	} else {
		var el = document.getElementsByName(name);
		if (obj.checked == true) {
			for (var i=0; i < el.length; i++) {
				if (el[i].type == "checkbox") {
				   el[i].checked = true;
				}
			}
		} else {
			for (var i=0; i < el.length; i++) {
				if (el[i].type == "checkbox") {
				   el[i].checked = false;         
				}
			}
		}
	}
	addPreItem(name);
}

function open(id,state){ //显示隐藏控制
	if(state == 1)
	document.getElementById(id).style.display = "block";
	document.getElementById(id).style.diaplay = "none";
}

function addPreItem(name){
	var objID, spanObjName, items, type;
	var idArray = new Array();
	var valueArray = new Array();
	if (name == "selectCarTypeItem" || name == "carType") {
		objID = "previewCarTypeItem";
		items = carTypeItems;
		type = "carType";
	} else if (name == "selectDistributorItem" || name == "distributor") {
		objID = "previewDistributorItem";
		items = distributorItems;
		type = "distributor";
		var inputs = document.getElementById("previewDistributorItem").getElementsByTagName("input");
		for(var i = 0 ; i < inputs.length ; i++) {
			if(inputs[i].checked == true) {
				valueArray.push(inputs[i].value + "");
				idArray.push(inputs[i].id + "");
			}
		}
	}
	document.getElementById(objID).innerHTML = "";
	var len　= 0 ;
	for(var i = 0 ; i < items.length ; i++) {
		if(items[i].checked == true) {
			var mes	 = '';
			if(name == "selectDistributorItem"||name == "distributor" || name == "selectCarTypeItem" || name == "carType"){
				mes = "<input type='checkbox' checked='true' value='"+ items[i].value +"' id='"+ items[i].id +"' onclick='copyItem(\""+objID+"\",\""+objID+"\");same(this, \""+type+"\");'>" + items[i].id+"<br>";
			}else{
				mes = "<input type='checkbox' checked='true' value='"+ items[i].value +"' id='"+ items[i].id +"' onclick='copyItem(\""+objID+"\",\""+objID+"\");same(this, \""+type+"\");'>" + items[i].id;
			}
			document.getElementById(objID).innerHTML += mes;
		} else {
			if ($.inArray(items[i].value + "", valueArray) != -1) {
				valueArray.splice($.inArray(items[i].value + "", valueArray), 1);
				idArray.splice($.inArray(items[i].id + "", idArray), 1);
			}
		}
	}
	
	if(name == "selectDistributorItem" || name == "distributor") {
		inputs = document.getElementById("previewDistributorItem").getElementsByTagName("input");
		for(var i = 0 ; i < inputs.length ; i++) {
			if(inputs[i].checked == true) {
				if ($.inArray(inputs[i].value + "", valueArray) != -1) {
					valueArray.splice($.inArray(inputs[i].value + "", valueArray), 1);
					idArray.splice($.inArray(inputs[i].id + "", idArray), 1);
				}
			} else {
				var checkedItems = document.getElementById("distributorCheckedItems").getElementsByTagName("input");
				for(var j = 0 ; j < checkedItems.length ; j++) {
					if(checkedItems[j].checked == false && checkedItems[j].value == inputs[i].value) {
						if ($.inArray(inputs[i].value + "", valueArray) != -1) {
							valueArray.splice($.inArray(inputs[i].value + "", valueArray), 1);
							idArray.splice($.inArray(inputs[i].id + "", idArray), 1);
						}
					}
				}
			}
		}
		
		$.each(valueArray, function(i, value) {
           mes = "<input type='checkbox' checked='true' value='"+ value +"' id='"+ idArray[i] +"' onclick='copyItem(\""+objID+"\",\""+objID+"\");same(this, \""+type+"\");'>" + idArray[i];
           if(name == "selectDistributorItem"||name == "distributor" || name == "selectCarTypeItem" || name == "carType"){
   		  	mes +="<br>";
   		   }
           document.getElementById(objID).innerHTML += mes;
        });
	}
}

function carTypeOrderChange() {
	var items = document.getElementById("previewCarTypeItem").getElementsByTagName("input");
	for(var i = 0 ; i < items.length ; i++) {
		if(items[i].checked == true) {
			if($("#carType_" + items[i].value).length == 0) {
				addOrderRowByCarType(items[i].value, items[i].id);
			}
		}
	}
	
	var cartypeIdArray = new Array();
	$("tr[id*='carType_']").each(function() {
		if ($(this).attr("id") != "carType_other") {
			var isHas = false;
			for(var i = 0 ; i < items.length ; i++) {
				if ($(this).attr("id") == "carType_" + items[i].value) {
					isHas = true; break;
				}
			}
			if (isHas == false) {
				cartypeIdArray[cartypeIdArray.length] = $(this).attr("id")
			}
		}
	});
	
	for (var i = 0; i < cartypeIdArray.length; i++) {
		var carTypeTrId = cartypeIdArray[i];
		if ($("tr[id*='carType_']").length == 2) {
			$("#orderFirstRow").parent().attr("id", "carType_other");
			$("#orderFirstRowName").html("其他");
			$("#orderFirstRowInput").html("<input id=\"orderTargetNum_other\" name=\"orderTargetNum_other\" type=\"text\" value=\""+$("#orderTargetNum_other").val()+"\" maxlength=\"8\" class=\"width50\" /><label><span class=\"red hidden\" id=\"order_num_error\">请输入订单数量</span></label>");
			delOrderRow($("#orderFirstRow").parent().next());
		} else {
			if ($("#" + carTypeTrId).find("td[id='orderFirstRow']").html() != null) {
				var trObj = $("#" + carTypeTrId);
				$("#orderFirstRowName").html(trObj.next().find('.typeName').html());
				trObj.find("input[id='orderTargetNum']").val(trObj.next().find("input[id='orderTargetNum']").val());
				trObj.attr("id", trObj.next().attr("id"));
				delOrderRow(trObj.next());
			} else {
				delOrderRow($("#" + carTypeTrId));
			}
		}
	}
}

function addOrderRowByCarType(value, name) {
	var rowspan = $("#orderFirstRow").attr("rowspan");
	var tr = $("#orderTemplate").clone(true);
	tr.removeAttr("class");
	tr.removeAttr("id");
	tr.find('.u_select').attr("class", "u_select sepcialSelect");
	if ($("#orderFirstRowName").html() == "其他") {
		tr.attr("id", "carType_other");
		tr.find('.typeName').html("其他");
		tr.find("input[id='orderTargetNum']").val($("#orderTargetNum_other").val());
		tr.find("input[id='orderTargetNum']").attr("id", "orderTargetNum_other");
		tr.find("input[name='orderTargetNum']").attr("name", "orderTargetNum_other");
		$("#orderFirstRow").parent().attr("id", "carType_" + value);
		$("#orderFirstRowName").html(name);
		$("#orderFirstRowInput").html("<input id=\"orderTargetNum\" name=\"orderTargetNum\" type=\"text\" value=\"\" maxlength=\"8\" class=\"width50\" /><label><span class=\"red hidden\" id=\"order_num_error\">请输入订单数量</span></label>");
		tr.insertBefore("#totalOrder");
	} else {
		if (parseInt($("#orderFirstRow").parent().attr("id").replace("carType_","")) > value) {
			tr.attr("id", $("#orderFirstRow").parent().attr("id"));
			tr.find('.typeName').html($("#orderFirstRowName").html());
			tr.find("input[id='orderTargetNum']").val($("#orderFirstRowInput").find("input[name='orderTargetNum']").val());
			$("#orderFirstRow").parent().attr("id", "carType_" + value);
			$("#orderFirstRowName").html(name);
			$("#orderFirstRowInput").html("<input id=\"orderTargetNum\" name=\"orderTargetNum\" type=\"text\" value=\"\" maxlength=\"8\" class=\"width50\" /><label><span class=\"red hidden\" id=\"order_num_error\">请输入订单数量</span></label>");
			tr.insertBefore("#" + $("#orderFirstRow").parent().next().attr("id"));
		} else {
			tr.attr("id", "carType_" + value);
			tr.find('.typeName').html(name);
			var isHas = false;
			if ($("tr[id*='carType_']").length > 0) {
				$("tr[id*='carType_']").each(function() {
					if (parseInt($(this).attr("id").replace("carType_","")) > value) {
						tr.insertBefore("#" + $(this).attr("id"));
						isHas = true;
						//跳出each循环使用 return false 不可使用break
						//停止当前each循环，使用return true，不可使用continue
						return false;
					}
				});
			}
			if (isHas == false) {
				tr.insertBefore("#carType_other");
			}
		}
		
	}
	$("#orderFirstRow").attr("rowspan", parseInt(rowspan)+1);
}

function makeSure(name){
	openBg(0);
	openSelect(0, name,true);
	var isHas = false;
	var items,id1,id2,result;
	var arrayObj = new Array();
	if (name == "selectCarTypeItem") {
		items = carTypeItems;
		id1 = "previewCarTypeItem";
		id2 = "makeSureCarTypeItem";
		result = "carTypeResult";
		
		var inputs = document.getElementById("previewCarTypeItem").getElementsByTagName("input");
		for(var i = 0 ; i < inputs.length ; i++) {
			if(inputs[i].checked == true) {
				arrayObj.push(inputs[i].value);
			}
		}
		$("#carTypeSelect").val(arrayObj.join(","));
		checkCarType($('#carTypeSelect'));
		//用于对"邀约/订单"模块中车型进行联动
		carTypeOrderChange();
		for(var i = 0 ; i < items.length ; i++) {
			if (items[i].checked == true) {
				isHas = true; break;
			}
		}
	} else if (name == "selectDistributorItem") {
		items = distributorItems;
		id1 = "previewDistributorItem";
		id2 = "makeSureDistributorItem";
		result = "distributorResult";
		
		var inputs = document.getElementById("previewDistributorItem").getElementsByTagName("input");
		for(var i = 0 ; i < inputs.length ; i++) {
			if(inputs[i].checked == true) {
				arrayObj.push(inputs[i].value);
			}
		}
		$("#distributorSelect").val(arrayObj.join(","));
		checkDistributor($('#distributorSelect'));
		for(var i = 0 ; i < distributorCheckedItems.length ; i++) {
			if (distributorCheckedItems[i].checked == true) {
				isHas = true; break;
			}
		}
	}
	
	copyItem(id1,id2,true);
	if (isHas) {
		document.getElementById(result).className = "show";
	} else {
		document.getElementById(result).className = "hidden";
	}
	
}
function copyHTML(id1,id2){
	document.getElementById(id2).innerHTML = document.getElementById("id1").innerHTML;
}
function copyItem(id1, id2,flag){
	var type;
	if (id1 == "previewDistributorItem") {
		type = "distributor";
	} else if (id1 == "previewCarTypeItem") {
		type = "carType";
	}
	var mes = "";
	var items2 = document.getElementById(id1).getElementsByTagName("input");
	for(var i = 0 ; i < items2.length ; i++) {
		if(items2[i].checked == true) {
			mes += "<input type='checkbox' checked id='" + items2[i].id + "' value='"+ items2[i].value +"' onclick='copyItem(\""+id2+"\",\""+id1+"\");same(this, \""+type+"\");'>" + items2[i].id;
			if(!flag){
				if(type == "distributor"||type == "carType"){
					mes+="<br>";
				}
			}
		}
	}
	document.getElementById(id2).innerHTML = "";
	document.getElementById(id2).innerHTML += mes;
}

function same(ck, type){
	if (type == "carType") {
		for(var i = 0 ; i < carTypeItems.length ; i++) {
			if(ck.value == carTypeItems[i].value) {
				carTypeItems[i].checked = ck.checked;
			}
		}
		if ($("#selectCarTypeItem").attr("class") != "show") {
			var arrayObj = new Array();
			var items = document.getElementById("makeSureCarTypeItem").getElementsByTagName("input");
			for(var i = 0 ; i < items.length ; i++) {
				if (items[i].checked == true) {
					arrayObj.push(items[i].value);
				}
			}
			$("#carTypeSelect").val(arrayObj.join(","));
			checkCarType($('#carTypeSelect'));
		}
	} else if (type == "distributor") {
		for(var i = 0 ; i < distributorItems.length ; i++) {
			if(ck.value == distributorItems[i].value) {
				distributorItems[i].checked = ck.checked;
			}
		}
		if ($("#selectDistributorItem").attr("class") != "show") {
			var arrayObj = new Array();
			var items = document.getElementById("makeSureDistributorItem").getElementsByTagName("input");
			for(var i = 0 ; i < items.length ; i++) {
				if (items[i].checked == true) {
					arrayObj.push(items[i].value);
				}
			}
			$("#distributorSelect").val(arrayObj.join(","));
			checkDistributor($('#distributorSelect'));
		}
	}
}
/* 鼠标拖动 */
var oDrag = "";
var ox,oy,nx,ny,dy,dx;
function drag(e,o){
	var e = e ? e : event;
	var mouseD = document.all ? 1 : 0;
	if(e.button == mouseD) {
		oDrag = o.parentNode;
		ox = e.clientX;
		oy = e.clientY;
	}
}
function dragPro(e){
	if(oDrag != "") {
		var e = e ? e : event;
		dx = parseInt(document.getElementById(oDrag).style.left);
		dy = parseInt(document.getElementById(oDrag).style.top);
		nx = e.clientX;
		ny = e.clientY;
		document.getElementById(oDrag).style.left = (dx + ( nx - ox )) + "px";
		document.getElementById(oDrag).style.top = (dy + ( ny - oy )) + "px";
		ox = nx;
		oy = ny;
	}
}
document.onmouseup = function(){oDrag = "";}

function delOrderRow(rows) {
	var rowspan = $("#orderFirstRow").attr("rowspan");
    $(rows).remove();
	$("#orderFirstRow").attr("rowspan", parseInt(rowspan)-1);
	var total = 0;
	$("input[id*='orderTargetNum']").each(function(){
		if (Trim($(this).val())) {
			total = parseInt(total) + parseInt(Trim($(this).val()));
		}
	});
	$("#orderTargetTotalNum").val(total);
	
	total = 0;
	$("input[id*='orderFactNum']").each(function(){
		if (Trim($(this).val())) {
			total = parseInt(total) + parseInt(Trim($(this).val()));
		}
	});
	$("#orderFactTotalNum").val(total);
}

$("document").ready(function(){
	//监控车型选择结果框中 车型选框的变化
	$("#carTypeResult input").live('click', function() {
		if ($(this).attr("checked")) {
			//订单列表中查看是否有该车型，没有就添加
			if ($("#carType_" + $(this).val()).length == 0) {
				addOrderRowByCarType($(this).val(), $(this).attr("id"));
			}
		} else {
			//订单列表中查看是否有该车型，有就删除
			var carTypeTrId = "carType_" + $(this).val();
			if ($("tr[id*='carType_']").length == 2) {
				$("#orderFirstRow").parent().attr("id", "carType_other");
				$("#orderFirstRowName").html("其他");
				$("#orderFirstRowInput").html("<input id=\"orderTargetNum_other\" name=\"orderTargetNum_other\" type=\"text\" value=\""+$("#orderTargetNum_other").val()+"\" maxlength=\"8\" class=\"width50\" /><label><span class=\"red hidden\" id=\"order_num_error\">请输入订单数量</span></label>");
				delOrderRow($("#orderFirstRow").parent().next());
			} else {
				if ($("#" + carTypeTrId).find("td[id='orderFirstRow']").html() != null) {
					var trObj = $("#" + carTypeTrId);
					$("#orderFirstRowName").html(trObj.next().find('.typeName').html());
					trObj.find("input[id='orderTargetNum']").val(trObj.next().find("input[id='orderTargetNum']").val());
					trObj.attr("id", trObj.next().attr("id"));
					delOrderRow(trObj.next());
				} else {
					delOrderRow($("#" + carTypeTrId));
				}
			}
		}
	});
	
	$('#selectDistributorProv').on('change', function() {
		$("#selectDistributorCity").get(0).selectedIndex = 0;
		$("#selectDistributorCity").prev().html($("#selectDistributorCity").get(0).options[0].text);
	});

	$("input[name='distributor']").live('click', function() {
		addPreItem('selectDistributorItem');
	});
});