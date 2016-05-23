<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Registration user" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleButtonFindUser"/></b>
            </div>

            <div style="margin-bottom: 15px; margin-top: 10px; color: grey;">
                <i><fmt:message key="findUserMessage"/><br>
                    <fmt:message key="findUserMessageParameters"/></i>
            </div>

            <div class="col-sm-4 col-sm-offset-4">
                <form name="find-user-form" style="width:225px; margin: 0 auto" action="controller" method="get">

                    <input type="text" class="form-control" placeholder="<fmt:message key="findUserInputParameters"/>*"
                           maxlength="35" required pattern=".{1,35}$" name="searchParameter">

                    <input type="hidden" name="command" value="findUser"/>

                    <button class="btn btn-default" type="submit" name="findUsers" value="findUsers"
                            style="width:225px"><fmt:message key="titleButtonFindUser"/>
                    </button>

                </form>
            </div>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>