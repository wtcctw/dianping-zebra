<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="ctx"
	type="com.dianping.zebra.admin.admin.page.index.Context"
	scope="request" />
<jsp:useBean id="payload"
	type="com.dianping.zebra.admin.admin.page.index.Payload"
	scope="request" />
<jsp:useBean id="model"
	type="com.dianping.zebra.admin.admin.page.index.Model" scope="request" />

<a:layout>
	<br>
	<strong style="color: #08C;">Database: ${model.database.name }</strong>
	<div>
		<br>
		<strong style="color: #08C;">应用列表</strong>
		<table class="table table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>App(${fn:length(model.database.apps) })</th>
					<th>自动替换的C3P0(${model.database.replacedSingleDataSource }/${model.database.c3p0DataSource })</th>
					<th>所有的DPDL(${model.database.dpdlDataSource })</th>
					<th>自动替换的DPDL(${model.database.replacedDpdlDataSource })</th>
					<th>升级Dal的数据源(${model.database.groupDataSource })</th>
					<th>总数据源(${model.database.totalDataSource })</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="app" items="${model.database.apps}">
					<tr id="app-info-${app.key}">
						<td><a href="?op=app&app=${app.key}">${app.key}</a></td>
						<td>${app.value.replacedSingleDataSource }/${app.value.c3p0DataSource}</td>
						<td>${app.value.dpdlDataSource }</td>
						<td>${app.value.replacedDpdlDataSource }</td>
						<td>${app.value.groupDataSource }</td>
						<td>${app.value.totalDataSource }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</a:layout>