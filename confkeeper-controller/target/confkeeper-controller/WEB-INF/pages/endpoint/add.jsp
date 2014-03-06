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
						<a href="${pageContext.request.contextPath}/app">应用列表</a> <span class="divider">/</span> 
					</li>
					<li>
						<a href="javascript:void(0);">添加列表</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 添加新应用</h2>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/app/add" method="post">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="typeahead">应用名称 </label>
							  <div class="controls">
								<input type="text" class="span6 typeahead" id="appName" name="appName">
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="date01">应用备注 </label>
							  <div class="controls">
								<textarea class="textarea" id="appNote" name="appNote" rows="3" style="width: 402px; height: 197px;"></textarea>
							  </div>
							</div>
							
							<div class="form-actions">
							  <button type="submit" class="btn btn-primary">确认</button>
							  <button type="reset" class="btn">重置</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->


			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> Form Elements</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">Focused input</label>
								<div class="controls">
								  <input class="input-xlarge focused" id="focusedInput" type="text" value="This is focused…">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">Uneditable input</label>
								<div class="controls">
								  <span class="input-xlarge uneditable-input">Some value here</span>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="disabledInput">Disabled input</label>
								<div class="controls">
								  <input class="input-xlarge disabled" id="disabledInput" type="text" placeholder="Disabled input here…" disabled="">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="optionsCheckbox2">Disabled checkbox</label>
								<div class="controls">
								  <label class="checkbox">
									<input type="checkbox" id="optionsCheckbox2" value="option1" disabled="">
									This is a disabled checkbox
								  </label>
								</div>
							  </div>
							  <div class="control-group warning">
								<label class="control-label" for="inputWarning">Input with warning</label>
								<div class="controls">
								  <input type="text" id="inputWarning">
								  <span class="help-inline">Something may have gone wrong</span>
								</div>
							  </div>
							  <div class="control-group error">
								<label class="control-label" for="inputError">Input with error</label>
								<div class="controls">
								  <input type="text" id="inputError">
								  <span class="help-inline">Please correct the error</span>
								</div>
							  </div>
							  <div class="control-group success">
								<label class="control-label" for="inputSuccess">Input with success</label>
								<div class="controls">
								  <input type="text" id="inputSuccess">
								  <span class="help-inline">Woohoo!</span>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="selectError3">Plain Select</label>
								<div class="controls">
								  <select id="selectError3">
									<option>Option 1</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								  </select>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="selectError">Modern Select</label>
								<div class="controls">
								  <select id="selectError" data-rel="chosen">
									<option>Option 1</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								  </select>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="selectError1">Multiple Select / Tags</label>
								<div class="controls">
								  <select id="selectError1" multiple data-rel="chosen">
									<option>Option 1</option>
									<option selected>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								  </select>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="selectError2">Group Select</label>
								<div class="controls">
									<select data-placeholder="Your Favorite Football Team" id="selectError2" data-rel="chosen">
										<option value=""></option>
										<optgroup label="NFC EAST">
										  <option>Dallas Cowboys</option>
										  <option>New York Giants</option>
										  <option>Philadelphia Eagles</option>
										  <option>Washington Redskins</option>
										</optgroup>
										<optgroup label="NFC NORTH">
										  <option>Chicago Bears</option>
										  <option>Detroit Lions</option>
										  <option>Green Bay Packers</option>
										  <option>Minnesota Vikings</option>
										</optgroup>
										<optgroup label="NFC SOUTH">
										  <option>Atlanta Falcons</option>
										  <option>Carolina Panthers</option>
										  <option>New Orleans Saints</option>
										  <option>Tampa Bay Buccaneers</option>
										</optgroup>
										<optgroup label="NFC WEST">
										  <option>Arizona Cardinals</option>
										  <option>St. Louis Rams</option>
										  <option>San Francisco 49ers</option>
										  <option>Seattle Seahawks</option>
										</optgroup>
										<optgroup label="AFC EAST">
										  <option>Buffalo Bills</option>
										  <option>Miami Dolphins</option>
										  <option>New England Patriots</option>
										  <option>New York Jets</option>
										</optgroup>
										<optgroup label="AFC NORTH">
										  <option>Baltimore Ravens</option>
										  <option>Cincinnati Bengals</option>
										  <option>Cleveland Browns</option>
										  <option>Pittsburgh Steelers</option>
										</optgroup>
										<optgroup label="AFC SOUTH">
										  <option>Houston Texans</option>
										  <option>Indianapolis Colts</option>
										  <option>Jacksonville Jaguars</option>
										  <option>Tennessee Titans</option>
										</optgroup>
										<optgroup label="AFC WEST">
										  <option>Denver Broncos</option>
										  <option>Kansas City Chiefs</option>
										  <option>Oakland Raiders</option>
										  <option>San Diego Chargers</option>
										</optgroup>
								  </select>
								</div>
							  </div>
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">Save changes</button>
								<button class="btn">Cancel</button>
							  </div>
							</fieldset>
						  </form>
					
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> Form Elements</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="prependedInput">Prepended text</label>
								<div class="controls">
								  <div class="input-prepend">
									<span class="add-on">@</span><input id="prependedInput" size="16" type="text">
								  </div>
								  <p class="help-block">Here's some help text</p>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInput">Appended text</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInput" size="16" type="text"><span class="add-on">.00</span>
								  </div>
								  <span class="help-inline">Here's more help text</span>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedPrependedInput">Append and prepend</label>
								<div class="controls">
								  <div class="input-prepend input-append">
									<span class="add-on">$</span><input id="appendedPrependedInput" size="16" type="text"><span class="add-on">.00</span>
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInputButton">Append with button</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInputButton" size="16" type="text"><button class="btn" type="button">Go!</button>
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInputButtons">Two-button append</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInputButtons" size="16" type="text"><button class="btn" type="button">Search</button><button class="btn" type="button">Options</button>
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">Checkboxes</label>
								<div class="controls">
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox1" value="option1"> Option 1
								  </label>
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox2" value="option2"> Option 2
								  </label>
								  <label class="checkbox inline">
									<input type="checkbox" id="inlineCheckbox3" value="option3"> Option 3
								  </label>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">File Upload</label>
								<div class="controls">
								  <input type="file">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">Radio buttons</label>
								<div class="controls">
								  <label class="radio">
									<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
									Option one is this and that—be sure to include why it's great
								  </label>
								  <div style="clear:both"></div>
								  <label class="radio">
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
									Option two can be something else and selecting it will deselect option one
								  </label>
								</div>
							  </div>
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">Save changes</button>
								<button class="btn">Cancel</button>
							  </div>
							</fieldset>
						</form>
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