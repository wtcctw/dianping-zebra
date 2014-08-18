<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
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

	<div class="container-fluid">
		<div>
			<br>
			<strong style="color: #08C;">数据库列表</strong>
			<table class="table table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th>数据库(${fn:length(model.report.databases) })</th>
						<th>自动替换的C3P0(${model.report.replacedSingleDataSource }/${model.report.c3p0DataSource })</th>
						<th>所有的DPDL(${model.report.dpdlDataSource })</th>
						<th>自动替换的DPDL(${model.report.replacedDpdlDataSource })</th>
						<th>升级Dal的数据源(${model.report.groupDataSource })</th>
						<th>总数据源(${model.report.totalDataSource })</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${model.report.databases}">
						<c:set var="database" value="${e.value}" />
						<tr id="database-info-${database.name}">
							<td><a href="?op=database&database=${database.name}">${database.name}</a></td>
							<td>${database.replacedSingleDataSource}/${database.c3p0DataSource}</td>
							<td>${database.dpdlDataSource}</td>
							<td>${database.replacedDpdlDataSource}</td>
							<td>${database.groupDataSource }</td>
							<td>
							<c:choose>
								<c:when test="${database.replacedDpdlDataSource == 0 && database.groupDataSource == 0 }">
									<span class="badge badge-important">${database.totalDataSource }</span>
								</c:when>
								<c:when test="${(database.replacedSingleDataSource + database.replacedDpdlDataSource + database.groupDataSource) == database.totalDataSource}">
									<span class="badge badge-success">${database.totalDataSource }</span>
								</c:when>
								<c:otherwise><span class="badge badge-warning">${database.totalDataSource }</span></c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</a:layout>

