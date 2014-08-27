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
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>

	<br>
	<strong style="color: #08C;">Database: ${payload.database }</strong>
	<br>
	<c:choose>
		<c:when test="${model.connectionStatus.connected}">
			Status:<span class="badge badge-success">Connected</span><br>
		</c:when>
		<c:otherwise>Status:<span class="badge badge-success">DisConnected</span><br></c:otherwise>
	</c:choose>
	Config:
	<pre class="prettyprint">
	<code class="language-xml">
	<c:out value="${model.connectionStatus.config }"/>
	</code>
	</pre>
</a:layout>