<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Truck add" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleAddTruck"/></b>
            </div>

            <div class="col-sm-4 col-sm-offset-4">
                <form name="truckId-add-form" style="width:225px; margin: 0 auto" action="controller" method="post">

                    <input type="text" class="form-control" placeholder="<fmt:message key="truckTruckName"/>*"
                           maxlength="25" required pattern="^[\u0430-\u044F\u0410-\u042F|\w| ]{1,25}$" name="truckName">

                    <input type="text" class="form-control" placeholder="<fmt:message key="truckCarrying"/>"
                           maxlength="5"
                           pattern="^\d{0,2}\.?\d{0,2}$" name="carrying">

                    <input type="text" class="form-control" placeholder="<fmt:message key="truckCapacity"/>"
                           maxlength="5" pattern="^\d{0,2}\.?\d{0,2}$" name="capacity">

                    <input type="text" class="form-control" placeholder="<fmt:message key="truckLength"/>"
                           maxlength="5" pattern="^\d{0,2}\.?\d{0,2}$" name="length">

                    <input type="text" class="form-control" placeholder="<fmt:message key="truckPhotoFile"/>"
                           maxlength="25" pattern="^\w{0,20}\.\w{2,4}$" name="photoLink">

                    <select name="lorryWithSides" class="form-control">
                        <option value="true" selected><fmt:message key="truckLorryWithSidesInputForm"/></option>
                        <option value="false"><fmt:message key="truckLorryWithoutSides"/></option>
                    </select>

                    <select name="refrigerator" class="form-control">
                        <option value="false" selected><fmt:message key="truckNotRefrigerator"/></option>
                        <option value="true"><fmt:message key="truckRefrigerator"/></option>
                    </select>

                    <select name="serviceable" class="form-control">
                        <option value="true" selected><fmt:message key="truckServiceableInputForm"/></option>
                        <option value="false"><fmt:message key="truckNotServiceable"/></option>
                    </select>

                    <input type="hidden" name="command" value="truckAdd"/>

                    <button class="btn btn-default" type="submit" name="insertToBD" value="insertToBD"
                            style="width:225px"><fmt:message key="buttonAddTruck"/>
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