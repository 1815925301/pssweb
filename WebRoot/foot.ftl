

	<div class="copybg">2015 Agencia Bolivariana para Actividades Espaciales - All Rights Reserved.</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->
	<script src="js/common.js" type="text/javascript"></script>
	<script src="js/menu/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="js/menu/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="js/menu/bootstrap.min.js" type="text/javascript"></script>
	
	<!--[if lte IE 6]>
	
  			<script type="text/javascript" src="js/menu/bootstrap-ie.js"></script>
  
 	 <![endif]-->   
	

	<!--[if lt IE 9]>

	<script src="js/menu/excanvas.min.js"></script>

	<script src="js/menu/respond.min.js"></script>  

	<![endif]-->   


	<script src="js/menu/contabs.min.js?v=1.0" type="text/javascript"></script>

	<script src="js/menu/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="js/menu/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="js/menu/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="js/cookie.js" type="text/javascript"></script>

	<script src="js/menu/jquery.uniform.min.js" type="text/javascript" ></script>
	
	<script src="js/common/jquery.livequery.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->

	<script src="js/menu/app.js"></script>   


    <#if pageName??><script type="text/javascript" src="${ctx}/js/service/${pageName}.js"></script></#if>   
	<script src="js/service/main.js"></script>

	<script>

		jQuery(document).ready(function() {    

		   App.init();

		});
		window.onload = function(){
			
			if (window.innerHeight) {
				$(".page-content").height(window.innerHeight);
				if($(".cms-tree-div-t")){
					$(".cms-tree-div-t").height(window.innerHeight-20);
				}
				if($(".cms-tree-div-b")){
					$(".cms-tree-div-b").height(window.innerHeight-20);
				}
			}else if((document.body) && (document.body.clientHeight)){
			  $(".page-content").height(document.body.clientHeight);
			  if($(".cms-tree-div-t")){
				$(".cms-tree-div-t").height(document.body.clientHeight-20);
			  }
			  if($(".cms-tree-div-b")){
				$(".cms-tree-div-b").height(document.body.clientHeight-20);
			  }
	        }
		}
		
	</script>

	<!-- END JAVASCRIPTS -->
<!-- END BODY -->
</BODY>

</html>

