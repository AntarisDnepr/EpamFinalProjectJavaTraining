<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%--=========================================================================== 
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>
<table id="main-container">
    <%--===========================================================================
    This is the HEADER, containing a top menu.
    header.jspf contains all necessary functionality for it.
    Just included it in this JSP document.
    ===========================================================================--%>
    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>
    <%--===========================================================================
    This is the CONTENT, containing the main part of the page.
    ===========================================================================--%>
    <tr>
        <td class="content center" align="center">
            <%-- CONTENT --%>
            <c:if test="${empty user}">
                <c:if test="${not empty errorMessage}">
                    <div style="margin-bottom: 15px; margin-top: 10px; color: #CC4500;">
                            ${errorMessage}
                    </div>
                </c:if>
                <%--===========================================================================
                Defines the web form.
                ===========================================================================--%>
                <form id="loginForm" class="form-inline" action="controller" method="post">
                        <%--===========================================================================
                        Hidden field. In the query it will act as command=login.
                        The purpose of this to define the command name, which have to be executed
                        after you submit current form.
                        ===========================================================================--%>
                    <input type="hidden" name="command" value="login"/>

                    <input type="text" class="form-control" placeholder="Login" maxlength="10"
                           required pattern="^{0,10}$"
                           name="login" style="width:175px">

                    <input type="password" class="form-control" placeholder="Password" maxlength="10"
                           required pattern="^{0,10}$"
                           name="password" style="width:175px">

                    <button class="btn btn-default" type="submit" value="Login"
                            style="width:175px">authorization
                    </button>
                </form>
            </c:if>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>