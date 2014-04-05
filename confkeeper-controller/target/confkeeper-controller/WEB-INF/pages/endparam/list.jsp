<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="yate" />
<title>Configution Keeper</title>
	<!-- The styles -->
	<link id="bs-css" href="${pageContext.request.contextPath}/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/charisma-app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='${pageContext.request.contextPath}/css/fullcalendar.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='${pageContext.request.contextPath}/css/chosen.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/uniform.default.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/colorbox.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/jquery.noty.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/noty_theme_default.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/elfinder.min.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/elfinder.theme.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/opa-icons.css' rel='stylesheet'>
	<link href='${pageContext.request.contextPath}/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
		
</head>

<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="javascript:void(0);"><span>Confkeeper Manager</span></a>
				
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
						<i class="icon-tint"></i><span class="hidden-phone"> 修改主题</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="javascript:void(0);"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="javascript:void(0);"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="javascript:void(0);"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="javascript:void(0);"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="journal" href="javascript:void(0);"><i class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="javascript:void(0);"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="javascript:void(0);"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="javascript:void(0);"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="javascript:void(0);"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
						<i class="icon-user"></i><span class="hidden-phone"> ${auth_user.userName}</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">个人信息</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/logot">退出</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
	
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li><a class="ajax-link" href=""><i class="icon-home"></i><span class="hidden-tablet"> 首页</span></a></li>
						<li class="nav-header hidden-tablet">基础功能</li>
						<li><a class="ajax-link" href="${pageContext.request.contextPath}/app"><i class="icon-th-large"></i><span class="hidden-tablet"> 应用</span></a></li>
						<li><a class="ajax-link" href="${pageContext.request.contextPath}/endpoint"><i class="icon-leaf"></i><span class="hidden-tablet"> 版本</span></a></li>
						<li><a class="ajax-link" href="${pageContext.request.contextPath}/endparam"><i class="icon-list"></i><span class="hidden-tablet"> 参数</span></a></li>
						
						<li class="nav-header hidden-tablet">权限管理</li>
						<li><a class="ajax-link" href="index.html"><i class="icon-globe"></i><span class="hidden-tablet"> 模块</span></a></li>
						<li><a class="ajax-link" href="index.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> 权限</span></a></li>
						<li><a class="ajax-link" href="index.html"><i class="icon-filter"></i><span class="hidden-tablet"> 角色</span></a></li>
						
						<li class="nav-header hidden-tablet">系统管理</li>
						<li><a class="ajax-link" href="index.html"><i class="icon-cog"></i><span class="hidden-tablet"> 控制台</span></a></li>
						<li><a class="ajax-link" href="index.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> 日志</span></a></li>
						<li><a class="ajax-link" href="index.html"><i class="icon-asterisk"></i><span class="hidden-tablet"> 修改密码</span></a></li>
					</ul>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="javascript:void(0);">首页</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="javascript:void(0);">参数列表</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-list"></i> 参数列表</h2>
						<!-- <div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div> -->
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="8%">编号</th>
								  <th width="12%">应用名称</th>
								  <th width="10%">版本</th>
								  <th width="5%">类型</th>
								  <th width="40%">键值</th>
								  <th width="25%">操作</th>
							  </tr>
						  </thead>   
						  <tbody>
						  	<c:forEach items="${datas}" var="obj">
							<tr>
								<td>${obj.id}</td>
								<td>${obj.endpoint.app.appName}</td>
								<td>${obj.endpoint.trunk}</td>
								<td>${obj.type}</td>
								<td>${obj.key}=${obj.value}</td>
								<td class="center">
									<a class="btn btn-success" href="#">
										<i class="icon-zoom-in icon-white"></i>  
										详细                                            
									</a>
									<a class="btn btn-info" href="#">
										<i class="icon-edit icon-white"></i>  
										修改                                            
									</a>
									<a class="btn btn-danger" href="#">
										<i class="icon-trash icon-white"></i> 
										删除
									</a>
								</td>
							</tr>
							</c:forEach>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
    
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://www.qianwang365.com" target="_blank">www.qianwang365.com</a> 2014</p>
			<p class="pull-right">Powered by: <a href="mailto:40055595@qq.com">Yate</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='${pageContext.request.contextPath}/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='${pageContext.request.contextPath}/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="${pageContext.request.contextPath}/js/excanvas.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.flot.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.flot.pie.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.flot.stack.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="${pageContext.request.contextPath}/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="${pageContext.request.contextPath}/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="${pageContext.request.contextPath}/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="${pageContext.request.contextPath}/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="${pageContext.request.contextPath}/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="${pageContext.request.contextPath}/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="${pageContext.request.contextPath}/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="${pageContext.request.contextPath}/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="${pageContext.request.contextPath}/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="${pageContext.request.contextPath}/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="${pageContext.request.contextPath}/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="${pageContext.request.contextPath}/js/charisma.js"></script>
</body>
</html>
