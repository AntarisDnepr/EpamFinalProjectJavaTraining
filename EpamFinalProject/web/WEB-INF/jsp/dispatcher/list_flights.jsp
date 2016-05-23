<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="All flights" scope="page"/>
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
                <b><fmt:message key="titleFlights"/></b>
            </div>

            <c:choose>
                <c:when test="${fn:length(allFlights) == 0}">
                    <div style="margin-bottom: 15px; margin-top: 10px; color: rgb(204, 69, 0);">
                        <fmt:message key="noSuchFlights"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <table id="flights-table" class="table table-hover">
                        <thead>
                        <tr>
                            <td class="text-center"><fmt:message key="listFlightsNumber"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeNumberFlight"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="listFlightsDateCreation"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeDateCreation"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="listFlightsDateDeparture"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeDateDeparture"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="listFlightsDestination"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeDestination"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="listFlightsDistance"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeDistance"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="listFlightsStatus"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeStatus"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="userFirstName"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeFirstName"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="userLastName"/>
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="sortFlights"/>
                                    <button class="btn btn-default" type="submit" name="sortFlightsType"
                                            value="sortFlightsTypeLastName"><span class="caret"/>
                                    </button>
                                </form>
                            </td>
                            <td class="text-center"><fmt:message key="userDriverPhoto"/></td>
                            <td class="text-center"><fmt:message key="listFlightsTruck"/></td>
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
                                <td class="text-center">${bean.beanStatusName.name}</td>
                                <td class="text-center">${bean.beanDriverFirstName}</td>
                                <td class="text-center">${bean.beanDriverLastName}</td>
                                <td class="text-center">
                                    <img src="Resources/photo/users/${bean.beanDriverPhotoLink}"
                                         width="70" height="70" class="img-rounded">
                                </td>
                                <td class="text-center">
                                    <img src="Resources/photo/trucks/${bean.beanTruckPhotoLink}"
                                         width="100" height="70" class="img-rounded">
                                </td>
                                <td class="text-center">
                                    <form name="send-request" action="controller" method="post">

                                        <input type="hidden" name="command" value="changeFlightStatus"/>

                                        <button class="btn btn-default" type="submit"
                                                onclick="return confirm('<fmt:message
                                                        key="listFlightsButtonCancelMessage"/>')"
                                                name="changeStatusFlightId" value=${bean.id}><fmt:message
                                                key="listFlightsButtonCancel"/>
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