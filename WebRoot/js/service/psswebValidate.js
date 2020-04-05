jQuery().ready(function() {
		jQuery("#abook").validate({
			rules : {
				name : {
					required : true,
					rangelength : [ 1, 50 ]
				},
				age : {
					required : true,
					integerNo : true
				},
				positionLevel : {
					required : true,
					rangelength : [ 1, 50 ]
				},
				xzzw : {
					required : true,
					rangelength : [ 1, 50 ]
				},
				officePhone : {
					required : true,
					phoneNo : true
				},
				fax : {
					required : true,
					phoneNo : true
				},
				email : {
					required : true,
					emailNo : true
				}

			},
			messages : {
				name : {
					required : "姓名不能为空",
					rangelength : "请输入姓名"
				},
				age : {
					required : "年龄不能为空",
					integerNo : "请输入整形的年龄"
				},
				
				positionLevel : {
					required : "职务级别不能为空",
					rangelength : "请输入职务级别"
				},
				xzzw : {
					required : "行政职务不能为空",
					rangelength : "请输入行政职务"
				},
				officePhone : {
					required : "办公电话不能为空",
					phoneNo : "请输入正确的办公电话"
				},
				fax : {
					required : "传真不能为空",
					phoneNo : "请输入正确的传真"
				},
				email : {
					required : "邮箱不能为空",
					emailNo : "请输入正确的邮箱"
				}
			}
		});
	});




	
