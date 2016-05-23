<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="All trucks" scope="page"/>
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
                <b><fmt:message key="titleTrucks"/></b>
            </div>

            <c:choose>
                <c:when test="${fn:length(allTrucks) == 0}">
                    <div style="margin-bottom: 15px; margin-top: 10px; color: rgb(204, 69, 0);">
                        <fmt:message key="noSuchTrucks"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <table id="trucks-table" class="table table-hover">
                        <thead>
                        <tr>
                            <td class="text-center"><fmt:message key="truckTruckName"/></td>
                            <td class="text-center"><fmt:message key="truckCarrying"/></td>
                            <td class="text-center"><fmt:message key="truckCapacity"/></td>
                            <td class="text-center"><fmt:message key="truckLength"/></td>
                            <td class="text-center"><fmt:message key="truckLorryWithSides"/></td>
                            <td class="text-center"><fmt:message key="truckRefrigerator"/></td>
                            <td class="text-center"><fmt:message key="truckServiceable"/></td>
                            <td class="text-center"><fmt:message key="truckPhoto"/></td>
                            <td class="text-center"><fmt:message key="truckCountFlights"/></td>
                            <td/>
                        </tr>
                        </thead>

                        <tbody>

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
                                    <c:choose>
                                        <c:when test="${bean.serviceable == true}">
                                            <img src="Resources/photo/check.png" width="15" height="15"
                                                 class="img-rounded">
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <img src="Resources/photo/trucks/${bean.photoLink}"
                                         width="100" height="70" class="img-rounded"></td>
                                <td class="text-center">
                                    ${bean.truckCountFlights}
                                </td>
                                <td class="text-center">
                                    <form name="send-request" action="controller" method="get">
                                        <input type="hidden" name="command" value="editTruck"/>
                                        <button class="btn btn-default" type="submit" style="width:125px"
                                                name="editTruckId" value=${bean.id}>
                                            <fmt:message key="buttonEdit"/>
                                        </button>
                                    </form>

                                    <form name="send-request" action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteTruck"/>
                                        <button class="btn btn-default" type="submit" style="width:125px"
                                                onclick="return confirm('<fmt:message
                                                        key="listTrucksButtonCancelMessage"/>')"
                                                name="deleteTruckId" value=${bean.id}>
                                            <fmt:message key="buttonDelete"/>
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