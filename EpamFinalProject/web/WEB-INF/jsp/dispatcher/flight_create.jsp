<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Flight create" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleCreateFlight"/></b>
            </div>

            <div class="col-sm-4 col-sm-offset-4">
                <form name="flight-create-input-form" style="width:225px; margin: 0 auto" action="controller"
                      method="post">
                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="createFlightFormNumber"/>" maxlength="5"
                           required pattern="^\d+$" name="flightNumber">

                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="createFlightFormDateDeparture"/>" maxlength="10"
                           name="dateDeparture" required
                           pattern="^(20)(1[6-9]|[2-9]\d)-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$">

                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="createFlightFormDestination"/>" maxlength="35"
                           required pattern="^.{0,35}$" name="destination">

                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="createFlightFormDistance"/>" maxlength="7"
                           required pattern="^\d*\.?\d{0,2}$" name="distance">

                    <select name="sendMail" class="form-control">
                        <option value="false" selected><fmt:message key="createFlightNotSendDrivers"/></option>
                        <option value="true"><fmt:message key="createFlightSendDrivers"/></option>
                    </select>

                    <input type="hidden" name="command" value="flightCreate"/>

                    <button class="btn btn-default" type="submit" name="insertToBD" value="insertToBD"
                            style="width:225px"><fmt:message key="titleCreateFlight"/>
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