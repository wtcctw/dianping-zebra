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
					<c:forEach var="database" items="${model.report.databases}">
						<tr id="database-info-${database.key}">
							<td><a href="?op=database&database=${database.key}">${database.key}</a></td>
							<td>${database.value.replacedSingleDataSource}</td>
							<td>${database.value.dpdlDataSource}</td>
							<td>${database.value.replacedDpdlDataSource}</td>
							<td>${database.value.groupDataSource }</td>
							<td>
							<c:choose>
								<c:when test="${database.value.replacedDpdlDataSource == 0 && database.value.groupDataSource == 0 }">
									<span class="badge badge-important">${database.value.totalDataSource }</span>
								</c:when>
								<c:when test="${(model.report.replacedSingleDataSource + database.value.replacedDpdlDataSource + database.value.groupDataSource) == database.value.totalDataSource}">
									<span class="badge badge-success">${database.value.totalDataSource }</span>
								</c:when>
								<c:otherwise><span class="badge badge-warning">${database.value.totalDataSource }</span></c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</a:layout>

