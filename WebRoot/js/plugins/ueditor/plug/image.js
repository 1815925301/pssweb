//插入图片链接

UE.plugins['zimage'] = function() {
	var editor = this;
	var articleImageWhetherOriginal = $V("ArticleImageWhetherOriginal");
	var showImageName = $V("ShowImageName");
	editor.commands['zimage'] = {
		execCommand : function(cmd, name) {
			var url = CONTEXTPATH + "contentcore/resourceDialog.zhtml"
					+ "?ResourceType=image&ImageWidth="
					+ ($V("ArticleImageWidth") || 500) + "&ImageHeight="
					+ ($V("ArticleImageHeight") || 500)+"&DataType="+$V("ContentType");
			url += "&CatalogID=" + $V("CatalogID");
			var diag = new Dialog();
			diag.width = 800;
			diag.height = 450;
			diag.title = editor.getLang('zimage.title');
			diag.url = url;
			var templatetitle = '<p align="center">{Title}</p>';
			diag.onOk = function() {
				var template;
				if (showImageName == "N") {
					templatetitle = '';
				}
				if (articleImageWhetherOriginal == "N") {
					template = '<p align="center"><img imagerela="{Rela}" {ImageMessage} src="{ImageLink}" title="{Title}" alt="{Alt}" suffix="{Suffix}" />'
							+ templatetitle + '</p>';
				} else {
					template = '<p align="center"><a href="{SourceImageLink}" target="_blank"><img imagerela="{Rela}" {ImageMessage} src="{ImageLink}" title="{Title}" alt="{Alt}" suffix="{Suffix}" /></a>'
							+ templatetitle + '</p>';
				}
				var currentTab = $DW.TabPage.getCurrentTab();
				var tabWin = $DW;
				if (tabWin.Verify.hasError()) {
					return false;
				}
				if (currentTab.id == "Resource") { // 栏目图片资源
					var eles = tabWin.$N("ResourcePath"), html = '', imageLink, path;
					var setWidth = tabWin.$V("ImageWidth");
					var setHeight = tabWin.$V("ImageHeight");
					var dc=new DataCollection();
					dc.add("ImageWidth",setWidth);
					dc.add("ImageHeight",setHeight);
					var imagePaths=[];
					for ( var i = 0; eles && i < eles.length; i++) {
						if (eles[i].checked) {
							imagePaths.push(eles[i].getAttribute("rpath"));
						}
					}
					dc.add("imagePaths",imagePaths);
					Server.sendRequest("Article.getThumbImage",dc,function(response){
						var imageLinks=response.imageLinks;
						var j=0;
						for ( var i = 0; eles && i < eles.length; i++) {
							if (eles[i].checked) {
								html += template.tmpl( {
									ImageLink : eles[i].getAttribute("prefix")+imageLinks[j],
									SourceImageLink : eles[i].value,
									Title : $(eles[i]).attr("rname"),
									Alt : $(eles[i]).attr("rname"),
									Rela : $(eles[i]).attr("rid"),
									Suffix : $(eles[i]).attr("rpath").substring(
											$(eles[i]).attr("rpath").lastIndexOf(
													".") + 1)
								});
								j++;
							}
						}
						editor.execCommand("insertHTML", html);
						$D.close();
					});
				} else { // 直接上传到栏目图片资源库或者获取网路图片
					var source = tabWin.$NV("Source"), title = tabWin
							.$V("OldName"), imageLink = "", rela = "";
					var setWidth = tabWin.$V("Width");
					if (source == 'upload') {
						tabWin.uploadSave(function(response) {
							imageLink = response.PreviewPrefix
									+ response.ResourcePath;
							rela = response.ResourceID;
							editor.execCommand("insertHTML", template.tmpl( {
								ImageLink : imageLink,
								SourceImageLink : response.PreviewPrefix
										+ response.Path,
								Title : title,
								Alt : title,
								Rela : rela,
								Suffix : response.Path.substring(response.Path
										.lastIndexOf(".") + 1)
							}));
							$D.close();
						});
					} else {
						imageLink = tabWin.$V('#URL');
						var netTemplate = '<p align="center"><img src="{ImageLink}" {ImageMessage} title="{Title}" alt="{Alt}" suffix="{Suffix}" />'
								+ templatetitle + '</p>';
						editor.execCommand("insertHTML", netTemplate.tmpl( {
							ImageLink : imageLink,
							SourceImageLink : imageLink,
							Title : title,
							Alt : title,
							Suffix : imageLink.substring(imageLink
									.lastIndexOf(".") + 1)
						}));
						$D.close();
					}
				}
			};
			diag.show();
		}
	};

};
addStyle('.edui-default .edui-toolbar div.edui-for-zimage .edui-icon'
		+ '{background:url("'
		+ CONTEXTPATH
		+ 'contentcore/editorplugins/images/icon_image.gif") center no-repeat;}');
