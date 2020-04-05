<#include "top.ftl"> <#--<#include "iframetop.ftl"> -->
<!-- BEGIN PAGE CONTAINER-->

<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">

<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>

<script src="js/map/collectMap_init.js" type="text/javascript"></script>
<script src="js/map/image.js" type="text/javascript"></script>

<div style="float: right; ">
	<div class="main-im">
		<div>
			<a href="javascript:pan_method();"><img src="img/map/nav_pan.png" />
			</a> <a href="javascript:void(0);" onclick="javascript:zoomin_method();"><img
				src="img/map/nav_zoomin.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:zoomout_method();"><img
				src="img/map/nav_zoomout.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:zoomtofull_method();"><img
				src="img/map/nav_fullextent.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:rectangle_method();"><img
				src="img/map/polygn_up.png" /> </a> <a
				href="javascript:circle_method();"><img
				src="img/map/polygn_yuan.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:polygon_method();"><img
				src="img/map/polygn.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:deactivate_method();"><img
				src="img/map/delete_up.png" /> </a>
		</div>
		<div id="open_im" class="open-im">&nbsp;</div>
		<div class="im_main" id="im_main">
			<div id="close_im" class="close-im">
				<a href="javascript:void(0);" title="ç¹å»å³é­">&nbsp;</a>
			</div>
			<div class="Cst"><@spring.message "basicParameter"/></div>
		
			<div class=" clear"></div>
			<script type="text/javascript" language="javascript">
				//<!CDATA[ 
				function g(o) {
					return document.getElementById(o);
				}
				function HoverLi(n, m, o) {
					//å¦ææNä¸ªæ ç­¾,å°±å°i<=N;  
					//æ¬åè½éå¸¸OK,å¼å®¹IE7,FF,IE6 
					for ( var i = 1; i <= o; i++) {
						g('tb_' + i + '_' + m).className = 'normaltab';
						g('tbc_0' + i + '_' + m).className = 'undis';
						//g('m01_'+i+'_'+m).className='undis';
					}
					g('tb_' + n + '_' + m).className = 'hovertab';
					g('tbc_0' + n + '_' + m).className = 'dis';
					//g('m01_'+n+'_'+m).className='dis';
				}
				//
			</script>
			<div class="w936">
			
				<div id="tb_" class="tb_">
				<!--  
					<ul>
						<li id="tb_1_1" class="hovertab"
							onclick="x:HoverLi(1,1,2);lonLatQuery();"><@spring.message "Coordinates"/></li>
					</ul>-->
				</div>
				<form action="#"  id='addForm'>
				<div class="ctt5">
					<div class="dis" id="tbc_01_1">
						<div class="wdf"><@spring.message "LongitudeLeft"/></div>
						<div class="wdinput">
							<input name="" id="leftuplong" type="text" />
						</div>
						<div class="wdf"><@spring.message "LatitudeLeft"/></div>
						<div class="wdinput">
							<input name="" id="leftuplat" type="text" />
						</div>
						<DIV class="clear"></DIV>
						<div class="wdf"><@spring.message "LowerRightLongitude"/></div>
						<div class="wdinput">
							<input name="" id="rightdownlong" type="text" />
						</div>
						<div class="wdf"><@spring.message "LowerRightLatitude"/></div>
						<div class="wdinput">
							<input name="" id="rightdownlat" type="text" />
						</div>
						<div class="clear"></div>
						<!--  
						<div class="xzanniu">
							<a href="#"><@spring.message "Transfer"/></a>
						</div>-->



					</div>
					<div class="undis" id="tbc_02_1">
						
						
						
						
						<DIV class="clear"></DIV>


					</div>
				</div>
			</div>

			
			
			
			
			
			<div class="clear"></div>
		
			<div class="clear"></div>
			<div class="jcwz"><@spring.message "begintime"/></div>
			<div class="cxtjinput03">
				<input type="text" id="begintime" name="begintime"
					value="${begintime}" style="width:95px"
					class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />
			</div>
			<div class="clear"></div>
			<div class="jcwz"><@spring.message "endtime"/></div>
			<div class="cxtjinput03">
				<input type="text" id="endtime" name="endtime"
					value="${endtime}" style="width:95px"
					class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />
			</div>
			<#--
			<div class="cxtjicon03">
				<img src="imgnew/icon20.png" width="17" height="17" />
			</div>
			-->
			
			<#--
			<div class="cxtjicon03">
				<img src="imgnew/icon20.png" width="17" height="17" />
			</div>
			-->
			<div class="clear"></div>
			<div class="line01"></div>

			<div class="jcwz"><@spring.message "productlevel"/></div>
			<div class="wdinput01">
				<select id="productlevel" name="productLevel">
					<option value="LEVEL1">LEVEL1</option>
					<option value="LEVEL2A">LEVEL2A</option>
					<option value="LEVEL2B">LEVEL2B</option>
					<option value="LEVEL3A">LEVEL3A</option>
					<option value="LEVEL3B">LEVEL3B</option>
					<option value="LEVEL4">LEVEL4</option>
					<option value="LEVEL0">LEVEL0</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="line01"></div>

			<div class="jcwz"><@spring.message "earthsurfacename"/></div>
			<div class="wdinput01">
				<select id="earthsurfacename" name="earthsurfacename" style="width: 150px;">
					<option value="-1"><@spring.message "select"/></option>
							<option value="neve">neve</option>
							<option value="snowfield">snowfield</option>
							<option value="gobi desert">gobi desert</option>
							<option value="water surface">water surface</option>
							<option value="forest">forest</option>
							<option value="city">city</option>
							<option value="farmland">farmland</option>
							<option value="other">other</option>
				</select>
			</div>
			
			
			
				<div class="jcwz"><@spring.message "cloudcover"/></div>
						<div class="">
							<input id="cloudcover" name="cloudcover" type="text" size="7" maxlength="5" placeholder="0-100" style="width: 70px;height: 33px;margin-top: 10px;">
						</div>
						<div class="clear"></div>
							<div class="jcwz"><@spring.message "order.receivingstation"/></div>
			<div class="wdinput01">
				<select id="stationid" name="stationid" style="width: 150px;">
					
							<option value="SiRGIS" selected="selected">SiRGIS</option>
							<option value="FGS-1">FGS-1</option>
							<option value="Xian">Xian</option>
							
				</select>
			</div>
			<div class="jcwz"><@spring.message "sideangle"/></div>
						<div class="wdinput">
							<input id="sideangle" name="sideangle" type="text" size="7" maxlength="5" placeholder="0-31">
							<!--  <span id="inputSideangle" Style="color: red; display:none; font-size: 12pt;">*<@spring.message "Correct_value"/></span>-->
						</div>			
		
			<div class="clear"></div>
			<div class="line"></div>
			<div class="jcwz"><@spring.message "satelliteChoose"/></div>
			<div class="clear"></div>
			<div class="wxxzbox">
				<div><input id="satelliteid" type="radio" name="satelliteid" value="VRSS-1">VRSS-1</div>
				<div class="wxf01"><@spring.message "caname"/></div>
				<div class="wxfx">
					<div class="wxfxk">
						<input name="sensorid" type="checkbox"
							value="PMC" />PMC
					</div>
					<div class="wxfxk">
						<input name="sensorid" type="checkbox"
							value="WMC" />WMC
					</div>
					

				</div>
				<div class="clear"></div>
				<div class="line"></div>

				<div><input id="satelliteid" type="radio" name="satelliteid" value="VRSS-2">VRSS-2</div>
				<div class="wxf01"><@spring.message "caname"/></div>
				<div class="wxfx">
					<div class="wxfxk">
						<input name="sensorid" type="checkbox" value="PAN" />PAN
					</div>
					<div class="wxfxk">
						<input name="sensorid" type="checkbox" value="MSS" />MSS
					</div>
					<div class="wxfxk">
						<input name="sensorid" type="checkbox" value="IRC" />IRC
					</div>
				</div>
				<div class="clear"></div>
				<div class="line"></div>

				<div><input id="satelliteid" type="radio" name="satelliteid" value="GF-1">GF-1</div>
				<div class="wxf01"><@spring.message "caname"/></div>
				<div class="wxfx">
					<div class="wxfxk">
						<input type="checkbox" name="sensorid" value="PAN">PAN
					</div>
					<div class="wxfxk">
						<input type="checkbox" name="sensorid" value="MSS">MSS
					</div>
					<div class="wxfxk">
						<input type="checkbox" name="sensorid" value="WMC">WMC
					</div>
				</div>
				<div class="clear"></div>
				<div class="line"></div>

				<div><input id="satelliteid" type="radio" name="satelliteid" value="GF-2">GF-2</div>
				<div class="wxf01"><@spring.message "caname"/></div>
				<div class="wxfx">
					<div class="wxfxk">
						<input type="checkbox" name="sensorid" value="PAN">PAN
					</div>
					<div class="wxfxk">
						<input type="checkbox" name="sensorid" value="MSS">MSS
					</div>
				</div>
				<div class="clear"></div>
				<div class="line"></div>
			</div>
			<div class="jcwz"><@spring.message "Locus"/></div>
			<div >
				<select id="trail" style="width:82px;" name="sateTrail;" onChange="viewSateTrail();">
				    <option value="-1"><@spring.message "select"/></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
				</select>
			</div>


			<div class="clear"></div>
			<div class="ccanniutc">
			    <!--<button type="button" class="btn blue"  onclick="viewTrack();">æ¥çè½¨è¿¹</button>-->
				<button type="button" class="btn blue" onclick="saveCollectInfo();"><@spring.message "save"/></button>
			</div>
			</form>
		</div>

		<!--ç¹å»é«çº§åæ°å¼¹åº-->
		<div id="gjcs" class="white_content01">
			<div class="main-im02">
				<div id="open_im02" class="open-im02">&nbsp;</div>
				<div class="im_main02" id="im_main02">
					<div id="close_im02" class="close-im02">
						<a href="javascript:void(0);" title="ç¹å»å³é­"></a>
					</div>
					<!--é«çº§åæ°åå®¹-->
					<div class="jcwz">çäº§æ¶é´</div>
					<div class="cxtjinput03">
						<input type="text" id="productStarttime" name="productStarttime"
							value="${productStarttime}" style="width:95px"
							class="form-control Wdate"
							onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />
					</div>
					<#--
					<div class="cxtjicon03">
						<img src="imgnew/icon20.png" width="17" height="17" />
					</div>
					-->
					<div class="cxtjicon03">è³</div>
					<div class="cxtjinput03">
						<input type="text" id="productEndtime" name="productEndtime"
							value="${productEndtime}" style="width:95px"
							class="form-control Wdate"
							onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />
					</div>
					<#--
					<div class="cxtjicon03">
						<img src="imgnew/icon20.png" width="17" height="17" />
					</div>
					-->
					<div class="clear"></div>
					<#--
					<div class="jcwz">æ¥æ¶ç«</div>
					<div class="wxfx01">
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />å¯äº
						</div>
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />ä¹é²æ¨é½
						</div>
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />å¹¿å·
						</div>
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />åä»
						</div>
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />ç¡ä¸¹æ±
						</div>
						<div class="wxfxk01">
							<input name="" type="checkbox" value="" />ä¸äº
						</div>
					</div>
					-->
					<div class="clear"></div>
					<div class="line"></div>
					<div class="jcwz">è½¨éå·ï¼åå·ï¼</div>
					<div class="wdinput02">
						<input id="orbitid" name="orbitid" type="text" />
					</div>
					<div class="clear"></div>
					<div class="wdf01">Pathï¼</div>
					<div class="cxtjinput03">
						<input id="minPath" type="text" />
					</div>
					<div class="cxtjicon03">-</div>
					<div class="cxtjinput03">
						<input id="maxPath" type="text" />
					</div>
					<div class="clear"></div>
					<div class="wdf01">Powï¼</div>
					<div class="cxtjinput03">
						<input id="minRow" type="text" />
					</div>
					<div class="cxtjicon03">-</div>
					<div class="cxtjinput03">
						<input id="maxRow" type="text" />
					</div>
					<div class="clear"></div>
					<div class="line"></div>

					<div class="jcwz">æ°æ®è´¨é</div>
					<div class="wdinput01">
						<select id="quality" name="quality">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</div>
					<div class="clear"></div>
					<#--
					<div class="jcwz">è¿æ»¤</div>
					<div class="wdinput01">
						<select name=""></select>
					</div>
					<div class="clear"></div>
					-->

					<div class="jcwz">å­Pathï¼</div>
					<div class="cxtjinput03">
						<input id="scenepath" name="scenePath" type="text" />
					</div>
					<div class="clear"></div>
					<div class="jcwz">å­Powï¼</div>
					<div class="cxtjinput03">
						<input id="scenerow" name="sceneRow" type="text" />
					</div>
					<div class="clear"></div>
					<div class="line"></div>

					<div class="w936">
						<div id="tb_" class="tb_1">
							<div class="jcwz1">SHPå¯¼å¥</div>
						</div>
						<div class="ctt5">
							<div class="dis" id="tbc_01_2">

								<div class="wdf">æä»¶åï¼</div>
								<div class="wdinput02">
									<input type="file" id="shpfile" name="file">
								</div>
								<div class="wdf" onclick="importShapeFiles()">
									<img src="imgnew/icon26.gif" width="72" height="30" />
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>


					<div class="clear"></div>
					<div class="w936" style="margin-top:20px;">
						<div id="tb_" class="tc_">
							<ul>
								<li id="tb_1_3" class="hovertab" onclick="x:HoverLi(1,3,2);">äº§åå·</li>
								<li id="tb_2_3" class="normaltab" onclick="i:HoverLi(2,3,2);">æ¯å·</li>
							</ul>
						</div>
						<div class="ctt5">
							<div class="dis" id="tbc_01_3">
								<div class="wdf">æä»¶åï¼</div>
								<div class="wdinput02">
									<input type="file" id="myfile" />
								</div>
								<div class="wdf" onclick="readProductidFile()">
									<img src="imgnew/icon26.gif" width="72" height="30" />
								</div>
								<div class="clear"></div>
								<div class="wdf">äº§åå·ï¼</div>
								<div class="wdinput02">
									<input id="productidStrs" name="productStrs" type="text" />
								</div>

							</div>
							<div class="undis" id="tbc_02_3">
								<div class="wdf">æä»¶åï¼</div>
								<div class="wdinput02">
									<input type="file" id="myfiles" />
								</div>
								<div class="wdf" onclick="readSceneidFile()">
									<img src="imgnew/icon26.gif" width="72" height="30" />
								</div>
								<div class="clear"></div>
								<div class="wdf">æ¯å·ï¼</div>
								<div class="wdinput02">
									<input id="sceneidStrs" name="sceneidStrs" type="text" />
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--ç»æ-->
	</div>
</div>
<div id="map" class="claro"
	style="width:100%; min-width:1140px; height:827px; position:relative; margin-top:20px;"
	frameborder="0"></div>





















</div>
</div>
</div>
</div>









<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="js/service/pssCollectInfomanageAdd.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>


<script type="text/javascript">
  jQuery(function() {
	dojo.addOnLoad(init);
});

</script>
