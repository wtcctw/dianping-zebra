<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>

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
						<th>数据库</th>
						<th>自动替换的数据源</th>
						<th>升级Dal的数据源</th>
						<th>总数据源</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="database" items="${model.report.databases}">
						<tr id="database-info-${database.key}">
							<td><a href="?op=database&database=${database.key}">${database.key}</a></td>
							<td>${database.value.replacedDataSource}</td>
							<td>${database.value.groupDataSource }</td>
							<td>
							<c:choose>
								<c:when test="${database.value.replacedDataSource == 0 && database.value.groupDataSource == 0 }">
									<span class="badge badge-important">${database.value.totalDataSource }</span>
								</c:when>
								<c:when test="${(database.value.replacedDataSource + database.value.groupDataSource) == database.value.totalDataSource}">
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

