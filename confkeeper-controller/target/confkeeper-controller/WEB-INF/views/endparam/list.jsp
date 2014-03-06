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

<!-- Bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style type="text/css">
body {
	padding-top: 65px;
}

#content {
	padding: 0 35px;
}

.btn-group {
	margin-bottom: 10px;
}
</style>
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.endparam.js"></script>
<script type="text/javascript">
	function go(rows,idx) {
		$("#datas").endpoint('load',{uri:"${pageContext.request.contextPath}/endparam",idx:idx,rows:rows});
	}

	function info(id) {
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/endparam/" + id,
			dataType : 'json',
			success : function(data) {
				if(data){
					$("#d_id").val(data.id);
					$("#d_trunk").val(data.trunk);
					$("#d_state").val(data.state);
					$("#modify")
				}
			}
		});
	}

	$(document).ready(function() {
		$("#datas").endparam('init',{uri:"${pageContext.request.contextPath}/endparam"}).endparam('load');
		
		$("#addBut").click(function() {
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/endparam/add",
				traditional : true,
				data : {
					"app.id":$("#appId").val(),
					"trunk":$("#trunk").val(),
					"state":$("#state").val()
				},
				dataType : 'json',
				success : function(data) {
					$("#datas").endparam('load',{uri:"${pageContext.request.contextPath}/endparam",idx:1,rows:10});
				}
			});
		});

		$("#delBut").click(function() {
			var arr = new Array();
			$("input[name='ids']:checked").each(function(idx, obj) {
				arr.push(obj.value);
			});
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/endparam/removes",
				traditional : true,
				data : {
					ids : arr
				},
				dataType : 'json',
				success : function(data) {
					$("#datas").endparam('load',{uri:"${pageContext.request.contextPath}/endparam",idx:1,rows:10});
				}
			});
		});

		$("#queryBut").click(function() {
			var param = {
					"app.id":$("#q_appId").val(),
					"trunk":$("#q_trunk").val(),
					"state":$("#q_state").val()
			};
			$("#datas").endparam('load',{uri:"${pageContext.request.contextPath}/endparam",idx:1,rows:10,param:param});
		});
		
		$("#modBut").click(function() {
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/endparam/"+ $("#d_id").val(),
				data : {
					"id":$("#d_id").val(),
					"trunk":$("#d_trunk").val(),
					"state":$("#d_state").val()
				},
				dataType : 'json',
				success : function(data) {
					$("#datas").endparam('load',{uri:"${pageContext.request.contextPath}/endparam",idx:1,rows:10});
				}
			});
		});
	});
</script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="nav">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<p class="navbar-text navbar-center">Configution Keeper</p>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown active"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">服务管理<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/app">应用</a></li>
								<li><a href="${pageContext.request.contextPath}/endpoint">版本</a></li>
								<li><a href="${pageContext.request.contextPath}/endparam">参数</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">连接状态<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">服务器状态</a></li>
								<li><a href="#">客户端状态</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">配置清单<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">日志</a></li>
								<li><a href="#">事件</a></li>
							</ul></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a
							href="${pageContext.request.contextPath}/logout" class="dropdown-toggle"
							data-toggle="dropdown"><span class="glyphicon glyphicon-off"></span>注销</a>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="get" class="dropdown-toggle"
							data-toggle="dropdown"> <span class="glyphicon glyphicon-cog"></span>设置<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">推送设置</a></li>
								<li><a href="#">用户中心</a></li>
								<li><a href="#">修改密码</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<div id="body">

		<!-- Add Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<label class="control-label">应用名</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.rows}" var="obj">
								<option value="${obj.id}">${obj.appName}</option>
							</c:forEach>
						</select>
						<label class="control-label">版本</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.varsion}" var="obj">
								<option value="${obj.id}">${obj.trunk}</option>
							</c:forEach>
						</select>
						<label class="control-label">Key</label>
						<input class="form-control" id="key" name="key">
						<label class="control-label">Value</label>
						<input class="form-control" id="value" name="value">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="addBut" type="submit" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Query Modal -->
		<div class="modal fade" id="query" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<label class="control-label">应用名</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.rows}" var="obj">
								<option value="${obj.id}">${obj.appName}</option>
							</c:forEach>
						</select>
						<label class="control-label">版本</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.varsion}" var="obj">
								<option value="${obj.id}">${obj.trunk}</option>
							</c:forEach>
						</select>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="queryBut" type="button" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modify Modal -->
		<div class="modal fade" id="modify" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<label class="control-label">应用名</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.rows}" var="obj">
								<option value="${obj.id}">${obj.appName}</option>
							</c:forEach>
						</select>
						<label class="control-label">版本</label>
						<select class="form-control" id="appId" name="appId">
							<c:forEach items="${apps.varsion}" var="obj">
								<option value="${obj.id}">${obj.trunk}</option>
							</c:forEach>
						</select>
						<label class="control-label">Key</label>
						<input class="form-control" id="key" name="key">
						<label class="control-label">Value</label>
						<input class="form-control" id="value" name="value">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button id="modBut" type="button" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- CRUD -->
		<div style="float: right;">
			<button type="button" class="btn btn-default glyphicon glyphicon-plus-sign"
				data-toggle="modal" data-target="#add">添加</button>
			<button type="button" class="btn btn-default glyphicon glyphicon-remove-sign"
				id="delBut">删除</button>
			<button type="button" class="btn btn-default glyphicon glyphicon-search"
				data-toggle="modal" data-target="#query">查询</button>
		</div>

		<div>
			<table id="datas" class="table table-hover">
				<thead>
					<tr>
						<th width="3%">#</th>
						<th width="8%">编号</th>
						<th width="12%">应用名</th>
						<th width="12%">版本</th>
						<th width="25%">键</th>
						<th width="25%">值</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>