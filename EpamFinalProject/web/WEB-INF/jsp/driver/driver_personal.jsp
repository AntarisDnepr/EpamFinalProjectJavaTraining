<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Personal office" scope="page"/>
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
                <b><fmt:message key="titlePersonalOffice"/></b>
            </div>

            <div style="margin-bottom: 15px; margin-top: 10px; color: grey">
                <i><fmt:message key="driverPersonalAllFlights"/>:</i>
            </div>

            <c:choose>
                <c:when test="${fn:length(allFlights) == 0}">
                    <div style="margin-bottom: 10px; margin-top: 10px; color: rgb(204, 69, 0);">
                        <fmt:message key="noSuchFlights"/>
                    </div>
                </c:when>
                <c:otherwise>

                    <table id="list-flights-driver-table" class="table table-hover">
                        <thead>
                        <tr>
                            <td class="text-center"><fmt:message key="listFlightsNumber"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDateCreation"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDateDeparture"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDestination"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDistance"/></td>
                            <td class="text-center"><fmt:message key="driverPersonalTruckPhoto"/></td>
                            <td class="text-center"><fmt:message key="driverPersonalTruckServiceable"/></td>
                            <td/>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="bean" items="${allFlights}">
                            <tr>
                                <td class="text-center">${bean.beanFlightNumber}</td>
                                <td class="text-center">${bean.beanDateCreation}</td>
                                <td class="text-center">${bean.beanDateDeparture}</td>
                                <td class="text-center">${bean.beanDestination}</td>
                                <td class="text-center">${bean.beanDistance}</td>
                                <td class="text-center">
                                    <img src="Resources/photo/trucks/${bean.beanTruckPhotoLink}"
                                         width="100" height="70" class="img-rounded">
                                </td>

                                <form name="send-request" action="controller" method="post">
                                    <td class="text-center">

                                        <input type="hidden" name="command" value="flightCompleted"/>
                                        <input type="hidden" name="insertToBD" value="insertToBD"/>
                                        <input type="hidden" name="truckId" value="${bean.beanTruckId}"/>

                                        <select name="isTruckServiceable" class="form-control">
                                            <option value="true" selected><fmt:message
                                                    key="truckServiceableInputForm"/></option>
                                            <option value="false"><fmt:message key="truckNotServiceable"/></option>
                                        </select>
                                    </td>

                                    <td class="text-center">
                                        <button class="btn btn-default" type="submit"
                                                name="flightId" value=${bean.id}>
                                            <fmt:message key="buttonFlightComplete"/>
                                        </button>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

            <div style="margin-bottom: 15px; margin-top: 10px; color: grey">
                <i> <fmt:message key="driverPersonalAllRequests"/>:</i>
            </div>

            <c:choose>
                <c:when test="${fn:length(allRequests) == 0}">
                    <div style="margin-bottom: 5px; color: rgb(204, 69, 0);">
                        <fmt:message key="noSuchRequests"/>
                    </div>
                </c:when>
                <c:otherwise>

                    <table id="list-requests-table" class="table table-hover">
                        <thead>
                        <tr>
                            <td class="text-center"><fmt:message key="listFlightsNumber"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDateDeparture"/></td>
                            <td class="text-center"><fmt:message key="listFlightsDestination"/></td>
                            <td class="text-center"><fmt:message key="truckCarrying"/></td>
                            <td class="text-center"><fmt:message key="truckCapacity"/></td>
                            <td class="text-center"><fmt:message key="truckLength"/></td>
                            <td class="text-center"><fmt:message key="truckLorryWithSides"/></td>
                            <td class="text-center"><fmt:message key="truckRefrigerator"/></td>
                            <td/>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="bean" items="${allRequests}">
                            <tr>
                                <td class="text-center">${bean.beanFlightNumber}</td>
                                <td class="text-center">${bean.beanDateDeparture}</td>
                                <td class="text-center">${bean.beanDestination}</td>
                                <td class="text-center">${bean.beanCarrying}</td>
                                <td class="text-center">${bean.beanCapacity}</td>
                                <td class="text-center">${bean.beanLength}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${bean.beanLorryWithSides == true}">
                                            <img src="Resources/photo/check.png" width="15" height="15"
                                                 class="img-rounded">
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${bean.beanRefrigerator == true}">
                                            <img src="Resources/photo/check.png" width="15" height="15"
                                                 class="img-rounded">
                                        </c:when>
                                    </c:choose>
                                </td>

                                <td class="text-center">
                                    <form name="send-request" action="controller" method="post">

                                        <input type="hidden" name="command" value="deleteRequest"/>
                                        <input type="hidden" name="flightId" value="${bean.beanFlightId}"/>

                                        <button class="btn btn-default" type="submit"
                                                onclick="return confirm('<fmt:message
                                                        key="listRequestButtonCancelMessage"/>')"
                                                name="deleteRequestId" value=${bean.id}>
                                            <fmt:message key="listFlightsButtonCancel"/>
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