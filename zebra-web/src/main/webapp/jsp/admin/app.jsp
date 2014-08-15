<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="ctx"
	type="com.dianping.zebra.admin.admin.page.index.Context"
	scope="request" />
<jsp:useBean id="payload"
	type="com.dianping.zebra.admin.admin.page.index.Payload"
	scope="request" />
<jsp:useBean id="model"
	type="com.dianping.zebra.admin.admin.page.index.Model" scope="request" />

<a:layout>
<link href="${model.webapp}/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${model.webapp}/js/jquery.treetable.js"></script>
	<br>
	<strong style="color: #08C;">App: ${model.app.name }</strong>
	<br>
	<table class="table table-bordered table-striped table-condensed">
		<thead>
			<tr>
				<th>GroupDataSource</th>
				<th>DpdlDataSource</th>
				<th>C3P0DataSource</th>
				<th>SingleDataSource</th>
				<th>OtherDataSource</th>
				<th>自动替换的C3P0数据源</th>
				<th>自动替换的DPDL数据源</th>
				<th>总数据源</th>
			</tr>
		</thead>
		<tbody>
				<tr id="machine-info-${machine.key}">
					<td>${model.app.groupDataSource}</td>
					<td>${model.app.dpdlDataSource}</td>
					<td>${model.app.c3p0DataSource}</td>
					<td>${model.app.singleDataSource}</td>
					<td>${model.app.otherDataSource}</td>
					<td>${model.app.replacedSingleDataSource}</td>
					<td>${model.app.replacedDpdlDataSource}</td>
					<td>${model.app.totalDataSource}</td>
				</tr>
		</tbody>
	</table>
	
	<div>
		<table class="table table-bordered table-striped table-condensed" id="example">
			<thead>
				<tr>
					<th>IP</th>
					<th>DALVersion</th>
					<th>SpringBeanName</th>
					<th>JDBC</th>
					<th>Username</th>
					<th>Type</th>
					<th>initPoolSize</th>
					<th>maxPoolSize</th>
					<th>minPoolSize</th>
					<th>Update Dal</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="machine" items="${model.app.machines}">
					<tr data-tt-id="machine-info-${machine.key}">
						<td>${machine.key}</td>
						<td>${machine.value.version }</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach var="datasource" items="${machine.value.datasources }">
						<tr data-tt-id="datasource-info-${datasource.key}" data-tt-parent-id="machine-info-${machine.key}">
							<td></td>
							<td></td>
							<td>${datasource.key }</td>
							<td>${datasource.value.jdbcUrl }</td>
							<td>${datasource.value.username }</td>
							<td>${datasource.value.type }</td>
							<td>${datasource.value.initPoolSize }</td>
							<td>${datasource.value.maxPoolSize }</td>
							<td>${datasource.value.minPoolSize }</td>
							<td>
							<c:choose>
								<c:when test="${datasource.value.type eq 'com.dianping.zebra.group.jdbc.GroupDataSource' || datasource.value.replaced == true }">
									<span class="badge badge-success">是</span>
								</c:when>
								<c:otherwise><span class="badge badge-important">否</span></c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
					
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script>
	$("#example").treetable();
	
	// Highlight selected row
	$("#example tbody").on("mousedown", "tr", function() {
	  $(".selected").not(this).removeClass("selected");
	  $(this).toggleClass("selected");
	});
	</script>
</a:layout>