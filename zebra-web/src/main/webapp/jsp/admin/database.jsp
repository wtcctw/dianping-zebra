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
	Database: ${model.database.name }
	<div>
		<table class="table table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>App</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="app" items="${model.database.apps}">
					<tr id="app-info-${app.key}">
						<td><a href="?op=app&app=${app.key}">${app.key}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</a:layout>