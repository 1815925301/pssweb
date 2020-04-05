var selectCheckbox = new Array();
$(document).ready(function() {
	$("#allcheck").on("click",selectAll);
	
});

function clickCheckbox(obj){
	if (obj.checked) {
		selectCheckbox.push(Number(obj.id));
	} else {
		selectCheckbox.splice($.inArray(Number(obj.id),selectCheckbox),1);
	}
	setSelectAll(obj);
}
//全选与反选
function selectAll(){  
    if ($("#allcheck").attr("checked")) {  
        $(":checkbox").attr("checked", true);
        $("input[name='subcheck']").each(function(i,item){
        	selectCheckbox.push(Number(item.id));
        });
    } else {  
        $(":checkbox").attr("checked", false);
        $("input[name='subcheck']").each(function(i,item){
        	selectCheckbox.splice($.inArray(Number(item.id),selectCheckbox),1);
        });
    }  
} 
function setSelectAll(obj){ //当没有选中某个子复选框时，SelectAll取消选中
    if (!$(obj).checked) {  
        $("#allcheck").attr("checked", false);  
    }  
    var chsub = $("input[type='checkbox'][name='subcheck']").length; //获取subcheck的个数  
    var checkedsub = $("input[type='checkbox'][name='subcheck']:checked").length; //获取选中的subcheck的个数  
    if (checkedsub == chsub) {  
        $("#allcheck").attr("checked", true);  
    }  
} 