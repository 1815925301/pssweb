function checkcount(){
	var count=document.getElementById("abaeaccount").value;
	var i=/^\d{16}$/;
	if(count==""){
		document.getElementById('abaeaccount_null').style.display="block";
		return false;
	}else if(!i.test(count)){
		document.getElementById('abaeaccount_null').style.display="none";
		document.getElementById('abaeaccount_error').style.display="block";
		return false;
	}else{
		document.getElementById('abaeaccount_error').style.display="none";
		document.getElementById('abaeaccount_null').style.display="none";
		return true;
	}
}
function checkticketid(){
	var ticketid=document.getElementById("ticketid").value;
	var i=/^\d{10}$/;
	if(ticketid==""){
		document.getElementById('ticketid_null').style.display="block";
		return false;
	}else if(!i.test(ticketid)){
		document.getElementById('ticketid_null').style.display="none";
		document.getElementById('ticketid_error').style.display="block";
		return false;
	}else{
		document.getElementById('ticketid_null').style.display="none";
		document.getElementById('ticketid_error').style.display="none";
		return true;
	}
}
function checkall(){
	if(!checkcount()){
		document.getElementById('abaeaccount').focus();
		return false;
	}
	if(!checkticketid()){
		document.getElementById('ticketid').focus();
		return false;
	}
	
}