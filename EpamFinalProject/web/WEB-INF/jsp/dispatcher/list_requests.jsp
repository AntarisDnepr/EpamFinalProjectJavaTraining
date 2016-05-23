<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="All requests" scope="page"/>
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
                <b><fmt:message key="titleRequests"/></b>
            </div>

            <c:choose>
                <c:when test="${fn:length(allRequests) == 0}">
                    <div style="margin-bottom: 15px; margin-top: 10px; color: rgb(204, 69, 0);">
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
                            <td class="text-center"><fmt:message key="userLastName"/></td>
                            <td class="text-center"><fmt:message key="userFirstName"/></td>
                            <td class="text-center"><fmt:message key="userDriverPhoto"/></td>
                            <td/>
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
                                <td class="text-center">${bean.beanDriverLastName}</td>
                                <td class="text-center">${bean.beanDriverFirstName}</td>
                                <td class="text-center">
                                    <img src="Resources/photo/users/${bean.beanDriverPhotoLink}"
                                         width="70" height="70" class="img-rounded">
                                </td>
                                <td class="text-center">
                                    <form name="approve-request" action="controller" method="get">

                                        <input type="hidden" name="command" value="approveRequest"/>

                                        <button class="btn btn-default" type="submit"
                                                name="approveRequestId" value=${bean.id}>
                                            <fmt:message key="listRequestsApply"/>
                                        </button>
                                    </form>
                                </td>
                                <td class="text-center">
                                    <form name="send-request" action="controller" method="post">

                                        <input type="hidden" name="command" value="deleteRequest"/>

                                        <button class="btn btn-default" type="submit"
                                                onclick="return confirm('<fmt:message
                                                        key="listRequestButtonCancelMessage"/>')"
                                                name="deleteRequestId" value=${bean.id}>
                                            <fmt:message key="listRequestButtonCancel"/>
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