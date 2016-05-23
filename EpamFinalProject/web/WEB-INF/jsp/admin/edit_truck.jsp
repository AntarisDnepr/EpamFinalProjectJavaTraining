<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Edit truck" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div style="margin-bottom: 15px; margin-top: 10px; color: grey; font-size: medium">
                <b><fmt:message key="titleEditTruck"/></b>
            </div>

            <table id="trucks-table" class="table table-striped">
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
                    <td/>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td class="text-center">${editTruck.truckName}</td>
                    <td class="text-center">${editTruck.carrying}</td>
                    <td class="text-center">${editTruck.capacity}</td>
                    <td class="text-center">${editTruck.length}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${editTruck.lorryWithSides == true}">
                                <img src="Resources/photo/check.png" width="15" height="15"
                                     class="img-rounded">
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${editTruck.refrigerator == true}">
                                <img src="Resources/photo/check.png" width="15" height="15"
                                     class="img-rounded">
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${editTruck.serviceable == true}">
                                <img src="Resources/photo/check.png" width="15" height="15"
                                     class="img-rounded">
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <img src="Resources/photo/trucks/${editTruck.photoLink}"
                             width="100" height="70" class="img-rounded"></td>
                    <td class="text-center">

                        <form name="send-request" action="controller" method="post">
                            <input type="hidden" name="command" value="deleteTruck"/>
                            <button class="btn btn-default" type="submit" style="width:175px"
                                    onclick="return confirm('<fmt:message
                                            key="listTrucksButtonCancelMessage"/>')"
                                    name="deleteTruckId" value=${editTruck.id}>
                                <fmt:message key="buttonDelete"/>
                            </button>
                        </form>
                    </td>
                </tr>
                <tr/>
                <form name="update-truck-form" action="controller" method="post">
                    <tr>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editTruck.truckName}"
                                   maxlength="25" style="width:150px"
                                   pattern="^[\u0430-\u044F\u0410-\u042F|\w| ]{1,25}$" name="truckName">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editTruck.carrying}"
                                   maxlength="5" style="width:100px"
                                   pattern="^\d{0,2}\.?\d{0,2}$" name="carrying">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editTruck.capacity}"
                                   maxlength="5" style="width:100px"
                                   pattern="^\d{0,2}\.?\d{0,2}$" name="capacity">
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editTruck.length}"
                                   maxlength="5" style="width:100px"
                                   pattern="^\d{0,2}\.?\d{0,2}$" name="length">
                        </td>
                        <td class="text-center">
                            <select name="lorryWithSides" class="form-control">
                                <option value="true"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.lorryWithSides, 'true')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="yes"/></option>
                                <option value="false"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.lorryWithSides, 'false')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="no"/></option>
                            </select>
                        </td>
                        <td class="text-center">
                            <select name="refrigerator" class="form-control">
                                <option value="false"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.refrigerator, 'false')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="no"/></option>
                                <option value="true"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.refrigerator, 'true')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="yes"/></option>
                            </select>
                        </td>
                        <td class="text-center">
                            <select name="serviceable" class="form-control">
                                <option value="true"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.serviceable, 'true')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="yes"/></option>
                                <option value="false"
                                        <c:choose>
                                            <c:when test="${fn:containsIgnoreCase(editTruck.serviceable, 'false')}">
                                                selected
                                            </c:when>
                                        </c:choose>
                                        ><fmt:message key="no"/></option>
                            </select>
                        </td>
                        <td class="text-center">
                            <input type="text" class="form-control" placeholder="${editTruck.photoLink}"
                                   maxlength="25" style="width:150px"
                                   pattern="^\w{0,20}\.\w{2,4}$" name="photoLink">
                        </td>
                        <td class="text-center">
                            <input type="hidden" name="command" value="editTruck"/>
                            <button class="btn btn-default" type="submit" name="insertToBD" value="insertToBD"
                                    style="width:175px">
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