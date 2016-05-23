<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Approve request" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleApproveRequest"/></b>
            </div>

            <table id="list-requests-table" class="table table-striped">
                <thead>
                <tr>
                    <td class="text-center"><fmt:message key="listRequestsFlight"/></td>
                    <td class="text-center"><fmt:message key="truckCarrying"/></td>
                    <td class="text-center"><fmt:message key="truckCapacity"/></td>
                    <td class="text-center"><fmt:message key="truckLength"/></td>
                    <td class="text-center"><fmt:message key="truckLorryWithSides"/></td>
                    <td class="text-center"><fmt:message key="truckRefrigerator"/></td>
                    <td class="text-center"><fmt:message key="userLastName"/></td>
                    <td class="text-center"><fmt:message key="userFirstName"/></td>
                    <td class="text-center"><fmt:message key="userDriverPhoto"/></td>
                    <td class="text-center"></td>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td class="text-center">${approveRequest.beanFlightId}</td>
                    <td class="text-center">${approveRequest.beanCarrying}</td>
                    <td class="text-center">${approveRequest.beanCapacity}</td>
                    <td class="text-center">${approveRequest.beanLength}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${approveRequest.beanLorryWithSides == true}">
                                <img src="Resources/photo/check.png" width="15" height="15"
                                     class="img-rounded">
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${approveRequest.beanRefrigerator == true}">
                                <img src="Resources/photo/check.png" width="15" height="15"
                                     class="img-rounded">
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="text-center">${approveRequest.beanDriverLastName}</td>
                    <td class="text-center">${approveRequest.beanDriverFirstName}</td>
                    <td class="text-center">
                        <img src="Resources/photo/users/${approveRequest.beanDriverPhotoLink}"
                             width="70" height="70" class="img-rounded">
                    </td>
                    <td class="text-center">
                        <form name="send-request" action="controller" method="post">
                            <input type="hidden" name="command" value="deleteRequest"/>
                            <button class="btn btn-default" type="submit"
                                    onclick="return confirm('<fmt:message key="listRequestButtonCancelMessage"/>')"
                                    name="deleteRequestId" value=${approveRequest.id}>
                                <fmt:message key="listRequestButtonCancel"/>
                            </button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>

            <div style="margin-bottom: 15px; margin-top: 10px">
                <i><fmt:message key="trucksFromRequest"/>:</i>
            </div>

            <table id="trucks-table" class="table table-hover">
                <thead>
                <tr>
                    <td class="text-center"><fmt:message key="truckTruckName"/></td>
                    <td class="text-center"><fmt:message key="truckCarrying"/></td>
                    <td class="text-center"><fmt:message key="truckCapacity"/></td>
                    <td class="text-center"><fmt:message key="truckLength"/></td>
                    <td class="text-center"><fmt:message key="truckLorryWithSides"/></td>
                    <td class="text-center"><fmt:message key="truckRefrigerator"/></td>
                    <td class="text-center"><fmt:message key="truckPhoto"/></td>
                    <td/>
                </tr>
                </thead>

                <tbody>
                <c:choose>
                    <c:when test="${fn:length(allTrucks) == 0}">
                        <div style="margin-bottom: 15px; margin-top: 10px; color: rgb(204, 69, 0);">
                            <fmt:message key="noSuchTrucks"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="bean" items="${allTrucks}">
                            <tr>
                                <td class="text-center">${bean.truckName}</td>
                                <td class="text-center">${bean.carrying}</td>
                                <td class="text-center">${bean.capacity}</td>
                                <td class="text-center">${bean.length}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${bean.lorryWithSides == true}">
                                            <img src="Resources/photo/check.png" width="15" height="15"
                                                 class="img-rounded">
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${bean.refrigerator == true}">
                                            <img src="Resources/photo/check.png" width="15" height="15"
                                                 class="img-rounded">
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <img src="Resources/photo/trucks/${bean.photoLink}"
                                         width="100" height="70" class="img-rounded"></td>
                                <td class="text-center">
                                    <form name="send-request" action="controller" method="post">

                                        <input type="hidden" name="command" value="approveRequest"/>

                                        <input type="hidden" name="insertToBD" value="insertToBD"/>

                                        <button class="btn btn-default" type="submit"
                                                onclick="return confirm('<fmt:message key="approveCarMessage"/>')"
                                                name="approveRequestTruckId" value=${bean.id}>
                                            <fmt:message key="approveCarButton"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>