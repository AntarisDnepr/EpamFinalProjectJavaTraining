<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Edit user" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleEditUser"/></b>
            </div>

            <table id="user-table" class="table table-striped">
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
                <tr>
                    <td class="text-center">${editUser.login}</td>
                    <td class="text-center">${editUser.password}</td>
                    <td class="text-center">${editUser.firstName}</td>
                    <td class="text-center">${editUser.lastName}</td>
                    <td class="text-center">${editUser.mail}</td>
                    <td class="text-center">
                        <img src="Resources/photo/users/${editUser.photoLink}"
                             width="70" height="70" class="img-rounded"></td>
                    <td class="text-center">${editUser.roleName.name}</td>
                    <td class="text-center">
                        <form name="send-request" action="controller" method="post">
                            <input type="hidden" name="command" value="deleteUser"/>
                            <button class="btn btn-default" type="submit"
                                    onclick="return confirm('<fmt:message
                                            key="listUserButtonDeleteMessage"/>')"
                                    name="deleteUserId" value=${editUser.id}><fmt:message key="buttonDelete"/>
                            </button>
                        </form>
                    </td>
                </tr>
                <tr/>
                <form name="update-user-form" action="controller" method="post">
                    <tr>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.login}" style="width:100px"
                                   maxlength="10" pattern="^[\w\u0430-\u044F\u0410-\u042F]{1,10}$" name="login">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.password}"
                                   style="width:125px" maxlength="10" pattern="^[\w\u0430-\u044F\u0410-\u042F]{1,10}$"
                                   name="password">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.firstName}"
                                   style="width:125px" maxlength="20"
                                   pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F]{1,25}$" name="firstName">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.lastName}"
                                   style="width:125px" maxlength="20"
                                   pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F]{1,25}$" name="lastName">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.mail}"
                                   style="width:150px" maxlength="35" name="mail"
                                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editUser.photoLink}"
                                   style="width:100px" maxlength="25" pattern="^\w{0,20}\.\w{2,4}$" name="photoLink">
                        </td>
                        <td class="text-center">
                            <select name="roleId" class="form-control">
                                <option value="1"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editUser.roleName.name, 'ADMIN')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="userRoleAdmin"/></option>
                                <option value="2"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editUser.roleName.name, 'DRIVER')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="userRoleDriver"/></option>
                                <option value="3"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editUser.roleName.name, 'DISPATCHER')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="userRoleDispatcher"/></option>
                            </select>
                        </td>
                        <td class="text-center">
                            <input type="hidden" name="command" value="editUser"/>
                            <button class="btn btn-default" type="submit" name="insertToBD" value="insertToBD">
                                <fmt:message key="buttonUpdate"/>
                            </button>
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
            <br>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>