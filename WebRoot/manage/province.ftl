<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>CMS</title>

    <meta name="keywords" content="H+åå°ä¸»é¢,åå°bootstrapæ¡æ¶,ä¼åä¸­å¿ä¸»é¢,åå°HTML,ååºå¼åå°">
    <meta name="description" content="H+æ¯ä¸ä¸ªå®å¨ååºå¼ï¼åºäºBootstrap3ææ°çæ¬å¼åçæå¹³åä¸»é¢ï¼å¥¹éç¨äºä¸»æµçå·¦å³ä¸¤æ å¼å¸å±ï¼ä½¿ç¨äºHtml5+CSS3ç­ç°ä»£ææ¯">

    <!--[if lt IE 8]>
    <script>
        alert('H+å·²ä¸æ¯æIE6-8ï¼è¯·ä½¿ç¨è°·æ­ãç«çç­æµè§å¨\næ360ãQQç­å½äº§æµè§å¨çæéæ¨¡å¼æµè§æ¬é¡µé¢ï¼');
    </script>
    <![endif]-->

    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=3.2.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
        <!--å·¦ä¾§å¯¼èªå¼å§-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
                                <span class="text-muted text-xs block">è¶çº§ç®¡çå<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="form_avatar.html">ä¿®æ¹å¤´å</a>
                                </li>
                                <li><a class="J_menuItem" href="profile.html">ä¸ªäººèµæ</a>
                                </li>
                                <li><a class="J_menuItem" href="contacts.html">èç³»æä»¬</a>
                                </li>
                                <li><a class="J_menuItem" href="mailbox.html">ä¿¡ç®±</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="login.html">å®å¨éåº</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">H+
                        </div>
                    </li>
                    <li>
                        <a href="index.html#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">ä¸»é¡µ</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="index_v1.html" data-index="0">ä¸»é¡µç¤ºä¾ä¸</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="index_v2.html">ä¸»é¡µç¤ºä¾äº</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="index_v3.html">ä¸»é¡µç¤ºä¾ä¸</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="index_v4.html">ä¸»é¡µç¤ºä¾å</a>
                            </li>
                            <li>
                                <a href="index_v5.html" target="_blank">ä¸»é¡µç¤ºä¾äº</a>
                            </li>
                        </ul>

                    </li>
                    <li>
                        <a class="J_menuItem" href="layouts.html"><i class="fa fa-columns"></i> <span class="nav-label">å¸å±</span></a>
                    </li>
                    <li>
                        <a href="index.html#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">ç»è®¡å¾è¡¨</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="graph_echarts.html">ç¾åº¦ECharts</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_flot.html">Flot</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_morris.html">Morris.js</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_rickshaw.html">Rickshaw</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_peity.html">Peity</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_sparkline.html">Sparkline</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="graph_metrics.html">å¾è¡¨ç»å</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="mailbox.html"><i class="fa fa-envelope"></i> <span class="nav-label">ä¿¡ç®± </span><span class="label label-warning pull-right">16</span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="mailbox.html">æ¶ä»¶ç®±</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_detail.html">æ¥çé®ä»¶</a>
                            </li>
                            <li><a class="J_menuItem" href="mail_compose.html">åä¿¡</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">è¡¨å</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="form_basic.html">åºæ¬è¡¨å</a>
                            </li>
                            <li><a class="J_menuItem" href="form_validate.html">è¡¨åéªè¯</a>
                            </li>
                            <li><a class="J_menuItem" href="form_advanced.html">é«çº§æä»¶</a>
                            </li>
                            <li><a class="J_menuItem" href="form_wizard.html">è¡¨ååå¯¼</a>
                            </li>
                            <li>
                                <a href="index.html#">æä»¶ä¸ä¼  <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="form_webuploader.html">ç¾åº¦WebUploader</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_file_upload.html">DropzoneJS</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_avatar.html">å¤´åè£åªä¸ä¼ </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">ç¼è¾å¨ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="form_editors.html">å¯ææ¬ç¼è¾å¨</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_simditor.html">simditor</a>
                                    </li>
                                    <li><a class="J_menuItem" href="form_markdown.html">MarkDownç¼è¾å¨</a>
                                    </li>
                                    <li><a class="J_menuItem" href="code_editor.html">ä»£ç ç¼è¾å¨</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="suggest.html">æç´¢èªå¨è¡¥å¨</a>
                            </li>
                            <li><a class="J_menuItem" href="layerdate.html">æ¥æéæ©å¨layerDate</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-desktop"></i> <span class="nav-label">é¡µé¢</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="contacts.html">èç³»äºº</a>
                            </li>
                            <li><a class="J_menuItem" href="profile.html">ä¸ªäººèµæ</a>
                            </li>
                            <li>
                                <a href="index.html#">é¡¹ç®ç®¡ç <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="projects.html">é¡¹ç®</a>
                                    </li>
                                    <li><a class="J_menuItem" href="project_detail.html">é¡¹ç®è¯¦æ</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="teams_board.html">å¢éç®¡ç</a>
                            </li>
                            <li><a class="J_menuItem" href="social_feed.html">ä¿¡æ¯æµ</a>
                            </li>
                            <li><a class="J_menuItem" href="clients.html">å®¢æ·ç®¡ç</a>
                            </li>
                            <li><a class="J_menuItem" href="file_manager.html">æä»¶ç®¡çå¨</a>
                            </li>
                            <li><a class="J_menuItem" href="calendar.html">æ¥å</a>
                            </li>
                            <li>
                                <a href="index.html#">åå®¢ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="blog.html">æç« åè¡¨</a>
                                    </li>
                                    <li><a class="J_menuItem" href="article.html">æç« è¯¦æ</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="faq.html">FAQ</a>
                            </li>
                            <li>
                                <a href="index.html#">æ¶é´è½´ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="timeline.html">æ¶é´è½´</a>
                                    </li>
                                    <li><a class="J_menuItem" href="timeline_v2.html">æ¶é´è½´v2</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="pin_board.html">æ ç­¾å¢</a>
                            </li>
                            <li>
                                <a href="index.html#">åæ® <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="invoice.html">åæ®</a>
                                    </li>
                                    <li><a class="J_menuItem" href="invoice_print.html">åæ®æå°</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="search_results.html">æç´¢ç»æ</a>
                            </li>
                            <li><a class="J_menuItem" href="forum_main.html">è®ºå</a>
                            </li>
                            <li>
                                <a href="index.html#">å³æ¶éè®¯ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="chat_view.html">èå¤©çªå£</a>
                                    </li>
                                    <li><a class="J_menuItem" href="webim.html">layIM</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">ç»å½æ³¨åç¸å³ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a href="login.html" target="_blank">ç»å½é¡µé¢</a>
                                    </li>
                                    <li><a href="login_v2.html" target="_blank">ç»å½é¡µé¢v2</a>
                                    </li>
                                    <li><a href="register.html" target="_blank">æ³¨åé¡µé¢</a>
                                    </li>
                                    <li><a href="lockscreen.html" target="_blank">ç»å½è¶æ¶</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="404.html">404é¡µé¢</a>
                            </li>
                            <li><a class="J_menuItem" href="500.html">500é¡µé¢</a>
                            </li>
                            <li><a class="J_menuItem" href="empty_page.html">ç©ºç½é¡µ</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-flask"></i> <span class="nav-label">UIåç´ </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="typography.html">æç</a>
                            </li>
                            <li>
                                <a href="index.html#">å­ä½å¾æ  <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a class="J_menuItem" href="icons.html">Font Awesome</a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="iconfont.html">é¿éå·´å·´ç¢éå¾æ åº</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">æå¨æåº <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="draggable_panels.html">æå¨é¢æ¿</a>
                                    </li>
                                    <li><a class="J_menuItem" href="agile_board.html">ä»»å¡æ¸å</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="buttons.html">æé®</a>
                            </li>
                            <li><a class="J_menuItem" href="tabs_panels.html">éé¡¹å¡ &amp; é¢æ¿</a>
                            </li>
                            <li><a class="J_menuItem" href="notifications.html">éç¥ &amp; æç¤º</a>
                            </li>
                            <li><a class="J_menuItem" href="badges_labels.html">å¾½ç« ï¼æ ç­¾ï¼è¿åº¦æ¡</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="grid_options.html">æ æ ¼</a>
                            </li>
                            <li>
                                <a href="index.html#">å¼¹æ¡æä»¶ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="layer.html">Webå¼¹å±ç»ä»¶layer</a>
                                    </li>
                                    <li><a class="J_menuItem" href="modal_window.html">æ¨¡æçªå£</a>
                                    </li>
                                    <li><a class="J_menuItem" href="sweetalert.html">SweetAlert</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="index.html#">æ å½¢è§å¾ <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="jstree.html">jsTree</a>
                                    </li>
                                    <li><a class="J_menuItem" href="tree_view.html">Bootstrap Tree View</a>
                                    </li>
                                    <li><a class="J_menuItem" href="nestable_list.html">nestable</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="toastr_notifications.html">Toastréç¥</a>
                            </li>
                            <li><a class="J_menuItem" href="diff.html">ææ¬å¯¹æ¯</a>
                            </li>
                            <li><a class="J_menuItem" href="spinners.html">å è½½å¨ç»</a>
                            </li>
                            <li><a class="J_menuItem" href="widgets.html">å°é¨ä»¶</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-table"></i> <span class="nav-label">è¡¨æ ¼</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="table_basic.html">åºæ¬è¡¨æ ¼</a>
                            </li>
                            <li><a class="J_menuItem" href="table_data_tables.html">DataTables</a>
                            </li>
                            <li><a class="J_menuItem" href="table_jqgrid.html">jqGrid</a>
                            </li>
                            <li><a class="J_menuItem" href="table_foo_table.html">Foo Tables</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-picture-o"></i> <span class="nav-label">ç¸å</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="basic_gallery.html">åºæ¬å¾åº</a>
                            </li>
                            <li><a class="J_menuItem" href="carousel.html">å¾çåæ¢</a>
                            </li>
                            <li><a class="J_menuItem" href="layerphotos.html">layerç¸å</a>
                            </li>
                            <li><a class="J_menuItem" href="blueimp.html">Blueimpç¸å</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="J_menuItem" href="css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSSå¨ç»</span></a>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-cutlery"></i> <span class="nav-label">å·¥å· </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="form_builder.html">è¡¨åæå»ºå¨</a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
        <!--å·¦ä¾§å¯¼èªç»æ-->
        <!--å³ä¾§é¨åå¼å§-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="index.html#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="è¯·è¾å¥æ¨éè¦æ¥æ¾çåå®¹ â¦" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                            </a>
                            <ul class="dropdown-menu dropdown-messages">
                                <li class="m-t-xs">
                                    <div class="dropdown-messages-box">
                                        <a href="profile.html" class="pull-left">
                                            <img alt="image" class="img-circle" src="img/a7.jpg">
                                        </a>
                                        <div class="media-body">
                                            <small class="pull-right">46å°æ¶å</small>
                                            <strong>å°å</strong> è¿ä¸ªå¨æ¥æ¬æéä¹¦ä¸ç­¾å­çåå®ï¼å»ºå½åä¸å®æ¯ä¸ªä¸å°çå¹²é¨å§ï¼
                                            <br>
                                            <small class="text-muted">3å¤©å 2014.11.8</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="dropdown-messages-box">
                                        <a href="profile.html" class="pull-left">
                                            <img alt="image" class="img-circle" src="img/a4.jpg">
                                        </a>
                                        <div class="media-body ">
                                            <small class="pull-right text-navy">25å°æ¶å</small>
                                            <strong>å½æ°å²³ç¶</strong> å¦ä½çå¾âç·å­ä¸æ»¡èªå·±ç±ç¬è¢«ç§°ä¸ºçï¼åºä¼¤è·¯äººâï¼ââè¿äººæ¯ç¬è¿å¶
                                            <br>
                                            <small class="text-muted">æ¨å¤©</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="mailbox.html">
                                            <i class="fa fa-envelope"></i> <strong> æ¥çæææ¶æ¯</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="mailbox.html">
                                        <div>
                                            <i class="fa fa-envelope fa-fw"></i> æ¨æ16æ¡æªè¯»æ¶æ¯
                                            <span class="pull-right text-muted small">4åéå</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="profile.html">
                                        <div>
                                            <i class="fa fa-qq fa-fw"></i> 3æ¡æ°åå¤
                                            <span class="pull-right text-muted small">12åéé±</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="notifications.html">
                                            <strong>æ¥çææ </strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="hidden-xs">
                            <a href="index_v1.html" class="J_menuItem" data-index="0"><i class="fa fa-cart-arrow-down"></i> è´­ä¹°</a>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">é¦é¡µ</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">å³é­æä½<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>å®ä½å½åéé¡¹å¡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>å³é­å¨é¨éé¡¹å¡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>å³é­å¶ä»éé¡¹å¡</a>
                        </li>
                    </ul>
                </div>
                <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> éåº</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="index_v1.html" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="../../index.html" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
        <!--å³ä¾§é¨åç»æ-->
        <!--å³ä¾§è¾¹æ å¼å§-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active"><a data-toggle="tab" href="index.html#tab-1">
                        éç¥
                    </a>
                    </li>
                    <li><a data-toggle="tab" href="index.html#tab-2">
                        é¡¹ç®è¿åº¦
                    </a>
                    </li>
                    <li class="">
                        <a data-toggle="tab" href="index.html#tab-3">
                            <i class="fa fa-gear"></i>
                        </a>
                    </li>
                </ul>

                <div class="tab-content">


                    <div id="tab-1" class="tab-pane active">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> ææ°éç¥</h3>
                            <small><i class="fa fa-tim"></i> æ¨å½åæ10æ¡æªè¯»ä¿¡æ¯</small>
                        </div>

                        <div>

                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a1.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">

                                        æ®å¤©æ´¥æ¥æ¥æ¥éï¼çæµ·å¬å¸è£äºé¿äºå­¦ä¼ï¼å¯è£äºé¿è£ç¤¾è½©ç­10äººå¨13æ¥ä¸åå·²è¢«æ§å¶ã
                                        <br>
                                        <small class="text-muted">ä»å¤© 4:21</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a2.jpg">
                                    </div>
                                    <div class="media-body">
                                        HCY48ä¹é³ä¹å¤§é­çä¼åä¸å±ç®è¤å·²ä¸çº¿ï¼å¿«æ¥ä¸é®æ¢è£æ¥æä»ï¼å®£åä½ å¯¹åæ¨å®çç±å§ï¼
                                        <br>
                                        <small class="text-muted">æ¨å¤© 2:45</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        åçå¥½ï¼ä¸æ¨åäº«
                                        <br>
                                        <small class="text-muted">æ¨å¤© 1:10</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>

                                    <div class="media-body">
                                        å½å¤æéå°å­çç¼æï¼è¿è¿æ¯äº²ççåï¼ï¼
                                        <br>
                                        <small class="text-muted">æ¨å¤© 8:37</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a8.jpg">
                                    </div>
                                    <div class="media-body">

                                        ä¸åªæµæµªçè¢«æ¶çåï¼ä¸ºäºåè½»ä¸»äººçè´æï¼åæèªå·±è§é£ï¼çè³......æäºä¸è¥¿ï¼å¯è½å¥¹æ¯æä»¬æ´æã
                                        <br>
                                        <small class="text-muted">ä»å¤© 4:21</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a7.jpg">
                                    </div>
                                    <div class="media-body">
                                        è¿å¥ä»¬çæ°è§é¢åæ¥äºï¼åææ æ æ»´ï¼å¸ç¸äºï¼
                                        <br>
                                        <small class="text-muted">æ¨å¤© 2:45</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        æè¿å¨è¡¥è¿½æ­¤å§ï¼ç¹å«åæ¬¢è¿æ®µè¡¨ç½ã
                                        <br>
                                        <small class="text-muted">æ¨å¤© 1:10</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="index.html#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>
                                    <div class="media-body">
                                        æåèµ·äºä¸ä¸ªæç¥¨ ãä½ è®¤ä¸ºä¸åå¤§çä¼ç¿»çº¢åï¼ã
                                        <br>
                                        <small class="text-muted">ææä¸ 8:37</small>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div id="tab-2" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-cube"></i> ææ°ä»»å¡</h3>
                            <small><i class="fa fa-tim"></i> æ¨å½åæ14ä¸ªä»»å¡ï¼10ä¸ªå·²å®æ</small>
                        </div>

                        <ul class="sidebar-list">
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>å¸åºè°ç </h4> æè¦æ±æ¥æ¶ææï¼

                                    <div class="small">å·²å®æï¼ 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">é¡¹ç®æªæ­¢ï¼ 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>å¯è¡æ§æ¥åç ç©¶æ¥ä¸çº§æ¹å </h4> ç¼åç®çç¼åæ¬é¡¹ç®è¿åº¦æ¥åçç®çå¨äºæ´å¥½çæ§å¶è½¯ä»¶å¼åçæ¶é´,å¯¹å¢éæåç å¼åè¿åº¦ä½åºä¸ä¸ªåççæ¯å¯¹

                                    <div class="small">å·²å®æï¼ 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>ç«é¡¹é¶æ®µ</h4> ä¸é£åç¨è½¦å¬å¸ éè´­ç»¼åç»¼åæ¥è¯¢åæç³»ç»é¡¹ç®è¿åº¦é¶æ®µæ§æ¥åæ­¦æ±æ¯è¿ªåç§ææéå¬å¸

                                    <div class="small">å·²å®æï¼ 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>è®¾è®¡é¶æ®µ</h4>
                                    <!--<div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>-->
                                    é¡¹ç®è¿åº¦æ¥å(Project Progress Report)
                                    <div class="small">å·²å®æï¼ 22%</div>
                                    <div class="small text-muted m-t-xs">é¡¹ç®æªæ­¢ï¼ 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>æè¿é¶æ®µ</h4> ç§ç é¡¹ç®ç ç©¶è¿å±æ¥å é¡¹ç®ç¼å·: é¡¹ç®åç§°: é¡¹ç®è´è´£äºº:

                                    <div class="small">å·²å®æï¼ 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">é¡¹ç®æªæ­¢ï¼ 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>å»ºè®¾é¶æ®µ </h4> ç¼åç®çç¼åæ¬é¡¹ç®è¿åº¦æ¥åçç®çå¨äºæ´å¥½çæ§å¶è½¯ä»¶å¼åçæ¶é´,å¯¹å¢éæåç å¼åè¿åº¦ä½åºä¸ä¸ªåççæ¯å¯¹

                                    <div class="small">å·²å®æï¼ 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="index.html#">
                                    <div class="small pull-right m-t-xs">9å°æ¶ä»¥å</div>
                                    <h4>è·è¯å¼ç</h4> ç¼åç®çç¼åæ¬é¡¹ç®è¿åº¦æ¥åçç®çå¨äºæ´å¥½çæ§å¶è½¯ä»¶å¼åçæ¶é´,å¯¹å¢éæåç å¼åè¿åº¦ä½åºä¸ä¸ªåççæ¯å¯¹

                                    <div class="small">å·²å®æï¼ 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>

                        </ul>

                    </div>

                    <div id="tab-3" class="tab-pane">

                        <div class="sidebar-title">
                            <h3><i class="fa fa-gears"></i> è®¾ç½®</h3>
                        </div>

                        <div class="setings-item">
                            <span>
                        æ¾ç¤ºéç¥
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example">
                                    <label class="onoffswitch-label" for="example">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        éèèå¤©çªå£
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" checked class="onoffswitch-checkbox" id="example2">
                                    <label class="onoffswitch-label" for="example2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        æ¸ç©ºåå²è®°å½
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example3">
                                    <label class="onoffswitch-label" for="example3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        æ¾ç¤ºèå¤©çªå£
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example4">
                                    <label class="onoffswitch-label" for="example4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        æ¾ç¤ºå¨çº¿ç¨æ·
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example5">
                                    <label class="onoffswitch-label" for="example5">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        å¨å±æç´¢
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example6">
                                    <label class="onoffswitch-label" for="example6">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>
                        æ¯æ¥æ´æ°
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example7">
                                    <label class="onoffswitch-label" for="example7">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="sidebar-content">
                            <h4>è®¾ç½®</h4>
                            <div class="small">
                                ä½ å¯ä»¥ä»è¿éè®¾ç½®ä¸äºå¸¸ç¨éé¡¹ï¼å½ç¶å¦ï¼è¿ä¸ªåªæ¯ä¸ªæ¼ç¤ºçç¤ºä¾ã
                            </div>
                        </div>

                    </div>
                </div>

            </div>



        </div>
        <!--å³ä¾§è¾¹æ ç»æ-->
        <!--minièå¤©çªå£å¼å§-->
        <div class="small-chat-box fadeInRight animated">

            <div class="heading" draggable="true">
                <small class="chat-date pull-right">
                    2015.9.1
                </small> ä¸ Beau-zihan èå¤©ä¸­
            </div>

            <div class="content">

                <div class="left">
                    <div class="author-name">
                        Beau-zihan <small class="chat-date">
                        10:02
                    </small>
                    </div>
                    <div class="chat-message active">
                        ä½ å¥½
                    </div>

                </div>
                <div class="right">
                    <div class="author-name">
                        æ¸¸å®¢
                        <small class="chat-date">
                            11:24
                        </small>
                    </div>
                    <div class="chat-message">
                        ä½ å¥½ï¼è¯·é®H+æå¸®å©ææ¡£åï¼
                    </div>
                </div>
                <div class="left">
                    <div class="author-name">
                        Beau-zihan
                        <small class="chat-date">
                            08:45
                        </small>
                    </div>
                    <div class="chat-message active">
                        æï¼è´­ä¹°çH+æºç åä¸­æå¸®å©ææ¡£ï¼ä½äºdocsæä»¶å¤¹ä¸
                    </div>
                </div>
                <div class="right">
                    <div class="author-name">
                        æ¸¸å®¢
                        <small class="chat-date">
                            11:24
                        </small>
                    </div>
                    <div class="chat-message">
                        é£é¤äºå¸®å©ææ¡£è¿æä¾ä»ä¹æ ·çæå¡ï¼
                    </div>
                </div>
                <div class="left">
                    <div class="author-name">
                        Beau-zihan
                        <small class="chat-date">
                            08:45
                        </small>
                    </div>
                    <div class="chat-message active">
                        1.æææºç (æªåç¼©ãå¸¦æ³¨éçæ¬)ï¼
                        <br> 2.è¯´æææ¡£ï¼
                        <br> 3.ç»èº«åè´¹åçº§æå¡ï¼
                        <br> 4.å¿è¦çææ¯æ¯æï¼
                        <br> 5.ä»è´¹äºæ¬¡å¼åæå¡ï¼
                        <br> 6.ææè®¸å¯ï¼
                        <br> â¦â¦
                        <br>
                    </div>
                </div>


            </div>
            <div class="form-chat">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control"> <span class="input-group-btn"> <button
                        class="btn btn-primary" type="button">åé
                </button> </span>
                </div>
            </div>

        </div>
        <div id="small-chat">
            <span class="badge badge-warning pull-right">5</span>
            <a class="open-small-chat">
                <i class="fa fa-comments"></i>

            </a>
        </div>
        <!--minièå¤©çªå£ç»æ-->
    </div>
    <script id="welcome-template" type="text/x-handlebars-template">
        <div class="border-bottom white-bg page-heading clearfix">
            <h2>æ´æ°æ¥å¿ï¼</h2>
            <div>ä»å¤©æ¯æäººèï¼H+ç»äºè·¨å°äºv3.0ï¼å°±ç®æ¯æäººèç¤¼ç©å§ï¼æè°¢ä½ ä»¬çä¸ç¦»ä¸å¼ï¼ä¸è·¯ç¸ä¼´ï¼</div>
            <div class="pull-right">ââBeau-zihan / 2015.8.20</div>
        </div>
        <div class="m">
            <div class="tabs-container">
                <div class="tabs-left">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a data-toggle="tab" href="index.html#layouts"><i class="fa fa-columns"></i> å¸å±
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="index.html#new"><i class="fa fa-plus-square"></i> æ°å¢
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="index.html#update"><i class="fa fa-arrow-circle-o-up"></i> åçº§
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="index.html#revise"><i class="fa fa-pencil"></i> ä¿®æ­£
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="index.html#optimize"><i class="fa fa-magic"></i> ä¼å
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content" style="line-height:1.8em;">
                        <div id="layouts" class="tab-pane active">
                            <div class="panel-body">
                                <ol class="no-left-padding">
                                    <li class="text-danger"><b>æ¨èï¼</b>æå¾å·²ä¹çcontentTabsææï¼æ¯æå³é­ãåå»å·æ°ãå·¦å³æ»å¨ç­ï¼</li>
                                    <li>åºå®å·¦ä¾§ä¸»èåæ ï¼å¹¶å¯¹èåé¡¹åäºæ°çè°æ´ï¼</li>
                                    <li>å¢å å³ä¾§é¢æ¿åèå¤©çªå£ç­ã</li>
                                </ol>

                                <p style="margin-left:25px;">
                                    <hr><span class="label label-danger">ç¹å«è´è°¢</span> æè°¢[å­Â·æ¢¦]åå­¦æä¾çcontentTabsä¼åæ¹æ¡åä»£ç ï¼</p>
                            </div>
                        </div>
                        <div id="new" class="tab-pane">
                            <div class="panel-body">
                                <ol class="no-left-padding">
                                    <li>è¡¨åï¼æç´¢èªå¨è¡¥å¨æä»¶suggestãé«çº§è¡¨åæä»¶ï¼æ¶é´éæ©ï¼åæ¢æé®ï¼å¾åè£åªä¸ä¼ ï¼åéå¤éæ¡ç¾åï¼æä»¶åç¾åç­)ç­ï¼</li>
                                    <li>å¾è¡¨ï¼å¾è¡¨ç»åé¡µé¢ç­ï¼</li>
                                    <li>é¡µé¢ï¼å¢éãç¤¾äº¤ãå®¢æ·ç®¡çãæç« åè¡¨ãæç« è¯¦æãæ°ç»å½é¡µé¢ç­ï¼</li>
                                    <li>UIåç´ ï¼ç«åéé¡¹å¡ãæå¨é¢æ¿ãææ¬å¯¹æ¯ãå è½½å¨ç»ãSweetAlertç­ï¼</li>
                                    <li>ç¸åï¼layerç¸åãBlueimpç¸åç­ï¼</li>
                                    <li>è¡¨æ ¼ï¼FooTablesç­ã</li>
                                </ol>
                            </div>
                        </div>
                        <div id="update" class="tab-pane">
                            <div class="panel-body">
                                <ol>
                                    <li>é¡µé¢å¼¹å±æä»¶layeråçº§è³1.9.3ï¼</li>
                                    <li>æ´æ°jqgridï¼æ¯ææ å½¢è¡¨æ ¼ï¼</li>
                                    <li>æ´æ°å¸®å©ææ¡£ã</li>
                                </ol>
                            </div>
                        </div>
                        <div id="revise" class="tab-pane">
                            <div class="panel-body">
                                <ol>
                                    <li>jstreeãSimditorç­å¤å¤éè¯¯ï¼</li>
                                    <li>é¡µé¢å è½½è¿åº¦æç¤ºï¼</li>
                                    <li>Glyphiconå­ä½å¾æ ä¸æ¾ç¤ºçé®é¢ï¼</li>
                                    <li>éæ°æ´çå¼åææ¡£ï¼</li>
                                </ol>
                            </div>
                        </div>
                        <div id="optimize" class="tab-pane">
                            <div class="panel-body">
                                <ol>
                                    <li>H+æ´ä½è§è§ææï¼</li>
                                    <li>jstreeé»è®¤ä¸»é¢æ¾ç¤ºææï¼</li>
                                    <li>è¡¨åéªè¯æ¾ç¤ºææï¼</li>
                                    <li>iCheckæ¾ç¤ºææï¼</li>
                                    <li>Tabsæ¾ç¤ºææã</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="alert alert-warning alert-dismissable m-t-sm">
                <button aria-hidden="true" data-dismiss="alert" class="close" type="button">Ã</button>
                åæ¶è¿ä¹æ¯ä¸ä¸ªç¤ºä¾ï¼æ¼ç¤ºäºå¦ä½ä»iframeä¸­å¼¹åºä¸ä¸ªè¦çç¶é¡µé¢çå±ã
            </div>
        </div>
    </script>

    <!-- å¨å±js 
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/bootstrap.min.js?v=3.4.0"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>-->

    <!-- èªå®ä¹js 
    <script src="js/hplus.min.js?v=3.2.0"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>-->

    <!-- ç¬¬ä¸æ¹æä»¶ 
    <script src="js/plugins/pace/pace.min.js"></script>-->

</body>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

</html>