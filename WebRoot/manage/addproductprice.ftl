<#include "top.ftl">
<style>
.* {
    padding: 0;
    margin: 0;
}
 
a {
    text-decoration: none;
    color: black;
}
 
a:hover {
    color: red;
}
 
.wrap {
    width: 900px;
    height: 600px;
    overflow-y: auto;
    margin: auto;
}
 
table {
    width: 800px;
    border: 1px solid black;
    border-collapse: collapse;
}
 
th {
    background-color: orange;
}
 
td,th {
    border: 1px solid black;
    text-align: center;
}
 
table input[type=text] {
    width: 100px;
}
    </style>
    
    <form action="" method="POST">
    	<input type="hidden" id="productcate_" value='<@spring.message "product_type"/>' >
		<fieldset class="wrap">
        <legend><@spring.message "price_information"/></legend>
        <table width="100%" class="table2" border="0" cellspacing="0" cellpadding="0">
            <tr>
            	<th><@spring.message "producttype"/></th>
                <th><@spring.message "order.productlevel"/></th>
                <th><@spring.message "area"/></th>
                <th><@spring.message "price"/></th>
                
            </tr>
        </table>
            <table width="100%" class="table2" border="0" cellspacing="0" cellpadding="0" id="tabid">
		
			
			
        </table>
        	<@spring.message "versionnum"/>：<input type="text"  id="versionnum"/>
       			 <input type="button" value="<@spring.message 'adding'/>" id="btnAdd" class="btn2" />
				<input type="button" value="<@spring.message 'configsubmit'/>" onclick="addBatchMarker()"/>
			
    </fieldset>
    </form>
<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>    
<script src="js/service/addproductprice.js" type="text/javascript"></script>    
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>