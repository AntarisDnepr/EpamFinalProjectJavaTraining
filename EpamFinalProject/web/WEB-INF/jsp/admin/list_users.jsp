<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="All users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <c:if test="${not empty okMessage}">
                <div style="margin-bottom: 15px; margin-top: 10px; color: green;">
                    <fmt:message key="okMessage"/>
                    <br><br>
                    <strong> <fmt:message key="${okMessage}"/></strong>
                </div>
            </c:if>

            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleUsers"/></b>
            </div>

            <c:choose>
                <c:when test="${fn:length(allUsers) == 0}">
                    <div style="margin-bottom: 15px; margin-top: 10px; color: rgb(204, 69, 0);">
                        <fmt:message key="noSuchUsers"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <table id="users-table" class="table table-hover">
                        <thead>
                        <tr>
                            <td class="text-center"><fmt:message key="userLogin"/></td>
                            <td class="text-center"><fmt:message key="userPassword"/></td>
                            <td class="text-center"><fmt:message key="userFirstName"/></td>
                            <td class="text-center"><fmt:message key="userLastName"/></td>
                            <td class="text-center"><fmt:message key="userMail"/></td>
                            <td class="text-center"><fmt:message key="userPhoto"/></td>
                            <td class="text-center"><fmt:message key="userRole"/></td>
                            <td/>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="bean" items="${allUsers}">
                            <tr>
                                <td class="text-center">${bean.login}</td>
                                <td class="text-center">${bean.password}</td>
                                <td class="text-center">${bean.firstName}</td>
                                <td class="text-center">${bean.lastName}</td>
                                <td class="text-center">${bean.mail}</td>
                                <td class="text-center">
                                    <img src="Resources/photo/users/${bean.photoLink}"
                                         width="70" height="70" class="img-rounded"></td>
                                <td class="text-center">${bean.roleName.name}</td>
                                <td class="text-center">
                                    <form name="send-request" action="controller" method="get">
                                        <input type="hidden" name="command" value="editUser"/>
                                        <button class="btn btn-default" type="submit" style="width:125px"
                                                name="editUserId" value=${bean.id}><fmt:message key="buttonEdit"/>
                                        </button>
                                    </form>

                                    <form name="send-request" action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteUser"/>
                                        <button class="btn btn-default" type="submit" style="width:125px"
                                                onclick="return confirm('<fmt:message
                                                        key="listUserButtonDeleteMessage"/>')"
                                                name="deleteUserId" value=${bean.id}><fmt:message key="buttonDelete"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>