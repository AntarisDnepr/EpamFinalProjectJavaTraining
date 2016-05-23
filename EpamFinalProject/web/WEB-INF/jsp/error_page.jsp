<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 150px; color: #CC4500;">
                <strong>
                    The following error occurred
                </strong>
            </div>

            <c:if test="${not empty errorMessage}">
                <div style="margin-bottom: 15px; margin-top: 15px; color: #CC4500;">
                        ${errorMessage}
                </div>
            </c:if>

            <%-- this way we obtain an information about an exception (if it has been occurred) --%>
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

            <c:if test="${not empty code}">
                Error code: ${code}
            </c:if>

            <c:if test="${not empty message}">
                ${message}
            </c:if>

            <c:if test="${not empty exception}">
                <% exception.printStackTrace(new PrintWriter(out)); %>
            </c:if>

            <c:if test="${empty user}">
                <form action="controller" method="get">
                    <button class="btn btn-default" name="command" value="login" style="width:225px">Login</button>
                </form>
            </c:if>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>