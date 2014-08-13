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
	Database: ${model.app.name }</br>
	
	<h5>Spring DataSource Bean</h5>
	GroupDataSource: ${model.app.groupDataSource} </br>
	DpdlDataSource: ${model.app.dpdlDataSource } </br>
	SingleDataSource: ${model.app.singleDataSource } </br>
	C3PODataSource: ${model.app.c3p0DataSource } </br>
	OtherDataSource: ${model.app.otherDataSource } </br>
	
	<h5>Replaced Num: ${model.app.replacedDataSource }</h5>
	<h5>Total DataSource Num: ${model.app.totalDataSource }</h5>
	
	<div>
		<table class="table table-bordered table-striped table-condensed">
			<thead>
				<tr>
					<th>Machine IP</th>
					<th>DAL Version</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="machine" items="${model.app.machines}">
					<tr id="machine-info-${machine.key}">
						<td>${machine.key}</td>
						<td>${machine.value.version }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</a:layout>