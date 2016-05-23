<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Settings" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
                <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="rightHeaderSettings"/></b>
            </div>

            <form id="settings_form" class="form-inline" action="controller" method="get">
                <fmt:message key="settingsSelectLanguage"/>:

                <select name="locale" class="form-control" style="width:175px">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                        <option value="${locale.key}" ${selected}>${locale.value}</option>
                    </c:forEach>
                </select>

                <input type="hidden" name="command" value="changeLocale"/>

                <button class="btn btn-default" type="submit" value="Update"
                        style="width:175px"><fmt:message key='settingsSubmitSaveLocale'/>
                </button>

            </form>
            <br>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>