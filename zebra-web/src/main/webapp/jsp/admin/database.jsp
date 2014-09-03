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
                   <th>自动替换的数目(${model.database.replacedSingleDataSource })/所有的C3P0数目(${model.database.c3p0DataSource })</th>
                   <th>自动替换的数目(${model.database.replacedDpdlDataSource })/所有的DPDL数目(${model.database.dpdlDataSource })</th>
                   <th>升级Dal的数目(${model.database.groupDataSource })</th>
                   <th>总数据源(${model.database.totalDataSource })</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${model.database.apps}">
					<c:set var="app" value="${e.value}" />
					<tr id="app-info-${app.name}">
						<td><a href="?op=app&app=${app.name}">${app.name}</a></td>
						<td>${app.replacedSingleDataSource }/${app.c3p0DataSource}</td>
						<td>${app.replacedDpdlDataSource }/${app.dpdlDataSource }</td>
						<td>${app.groupDataSource }</td>
						<td>
							<c:choose>
								<c:when test="${(app.replacedSingleDataSource + app.replacedDpdlDataSource + app.groupDataSource) == app.totalDataSource}">
									<span class="badge badge-success">${app.totalDataSource }</span>
								</c:when>
								<c:when test="${app.replacedDpdlDataSource == 0 && app.groupDataSource == 0 }">
									<span class="badge badge-important">${app.totalDataSource }</span>
								</c:when>
								<c:otherwise><span class="badge badge-warning">${app.totalDataSource }</span></c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</a:layout>