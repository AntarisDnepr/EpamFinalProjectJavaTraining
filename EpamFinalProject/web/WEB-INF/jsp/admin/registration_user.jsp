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
                <b><fmt:message key="titleRegistrationUser"/></b>
            </div>

            <div class="col-sm-4 col-sm-offset-4">
                <form name="registration-user-form" style="width:225px; margin: 0 auto" action="controller"
                      method="post">
                    <input type="text" class="form-control" placeholder="<fmt:message key="userLogin"/>*"
                           maxlength="10" required pattern="^[\w\u0430-\u044F\u0410-\u042F]{1,10}$" name="login">

                    <input type="text" class="form-control" placeholder="<fmt:message key="userPassword"/>*"
                           maxlength="10" required pattern="^[\w\u0430-\u044F\u0410-\u042F]{1,10}$" name="password">

                    <input type="text" class="form-control" placeholder="<fmt:message key="userFirstName"/>*"
                           maxlength="20" required pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F]{1,25}$"
                           name="firstName">

                    <input type="text" class="form-control" placeholder="<fmt:message key="userLastName"/>*"
                           maxlength="20" required pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F]{1,25}$"
                           name="lastName">

                    <input type="text" class="form-control" placeholder="<fmt:message key="userMail"/>"
                           maxlength="35" name="mail"
                           pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" >

                    <input type="text" class="form-control" placeholder="<fmt:message key="userPhotoFile"/>"
                           maxlength="25" pattern="^\w{0,20}\.\w{2,4}$" name="photoLink">

                    <select name="roleId" class="form-control">
                        <option value="2" selected><fmt:message key="userRoleDriver"/></option>
                        <option value="3"><fmt:message key="userRoleDispatcher"/></option>
                    </select>

                    <input type="hidden" name="command" value="registrationUser"/>

                    <button class="btn btn-default" type="submit" name="insertToBD" value="insertToBD"
                            style="width:225px"><fmt:message key="buttonAddStaff"/>
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