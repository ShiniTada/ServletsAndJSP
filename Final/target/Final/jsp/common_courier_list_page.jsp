
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>

<html>
<head>
    <title>Good-Couriers</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">
    <%--    <link rel="stylesheet" href="../styles/main.css">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers" var="couriers"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.first" var="Id"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.second" var="Name"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.third" var="Mark"/>
    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
    <fmt:message bundle="${loc}" key="local.courier.order.goods_description" var="goods_description"/>
    <fmt:message bundle="${loc}" key="local.customer.all_couriers.info_header" var="info_header"/>
    <fmt:message bundle="${loc}" key="local.customer.all_couriers.info" var="info"/>
    <fmt:message bundle="${loc}" key="local.courier.back" var="back"/>


    <fmt:message bundle="${loc}" key="local.admin.orders_table.more_details" var="more_details"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

</head>
<body>

<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-left"><img src="../img/logo.jpg" style="height:30px;"></a>
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>
    <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
    <my:headLanguage/>
</div>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">
    <br><br><br>
        <div class="w3-panel w3-pale-blue w3-border w3-margin-bottom">
            <h3>${info_header}</h3>
            <p>${info}</p>
        </div>
    <br>

    <div class="w3-container w3-margin-bottom">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="back_after_details"/>
            <button class="w3-block w3-round w3-center w3-red w3-button w3-cell w3-right" name="back" value="afterAllCouriers"
                    style="max-width:100px">${back}</button>
        </form>
    </div>
    <br><br>

    <!-- TABLE-->

        <div class="w3-container w3-center">
            <h2 class="title w3-text-black" style="margin-top:100px;">${couriers}</h2>

            <table class="w3-table-all">
                <thead>
                <tr class="w3-teal">
                    <th>${Id}</th>
                    <th>${Name}</th>
                    <th>${Mark}</th>
                    <td>${more_details}</td>
                </tr>
                </thead>

                <c:choose>
                    <c:when test="${requestScope.pagination eq 2}">
                        <c:forEach var="elem" items="${sessionScope.listCourierRecords}"
                                   begin="5" end="9" >
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <td><c:out value="${elem.markCommon}"/> </td>
                                <td>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="more_details"/>
                                        <button class="w3-block w3-round w3-center w3-teal w3-button" name="courierRecordId" value="${elem.id}" style="max-width:150px">${more_details}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${requestScope.pagination eq 3}">
                        <c:forEach var="elem" items="${sessionScope.listCourierRecords}"
                                   begin="10" end="14" >
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <td><c:out value="${elem.markCommon}"/> </td>
                                <td>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="more_details"/>
                                        <button class="w3-block w3-round w3-center w3-teal w3-button" name="courierRecordId" value="${elem.id}" style="max-width:150px">${more_details}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${requestScope.pagination eq 4}">
                        <c:forEach var="elem" items="${sessionScope.listCourierRecords}"
                                   begin="15" end="19" >
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <td><c:out value="${elem.markCommon}"/> </td>
                                <td>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="more_details"/>
                                        <button class="w3-block w3-round w3-center w3-teal w3-button" name="courierRecordId" value="${elem.id}" style="max-width:150px">${more_details}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="elem" items="${sessionScope.listCourierRecords}"
                                   begin="0" end="4" >
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <td><c:out value="${elem.markCommon}"/> </td>
                                <td>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="more_details"/>
                                        <button class="w3-block w3-round w3-center w3-teal w3-button" name="courierRecordId" value="${elem.id}" style="max-width:150px">${more_details}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
            <br>
            <c:choose>
                <c:when test="${requestScope.pagination eq 2}">
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="table-pagination"/>
                        <div class="w3-bar">
                            <label>«</label>
                            <button name= "pagination" value = "1" class="w3-button w3-round">1</button>
                            <button name= "pagination" value = "2" class="w3-button w3-teal w3-round">2</button>
                            <button name= "pagination" value = "3" class="w3-button w3-round">3</button>
                            <button name= "pagination" value = "4" class="w3-button w3-round">4</button>
                            <label>»</label>
                        </div>
                    </form>
                </c:when>
                <c:when test="${requestScope.pagination eq 3}">
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="table-pagination"/>
                        <div class="w3-bar">
                            <label>«</label>
                            <button name= "pagination" value = "1" class="w3-button w3-round">1</button>
                            <button name= "pagination" value = "2" class="w3-button w3-round">2</button>
                            <button name= "pagination" value = "3" class="w3-button w3-teal w3-round">3</button>
                            <button name= "pagination" value = "4" class="w3-button w3-round">4</button>
                            <label>»</label>
                        </div>
                    </form>
                </c:when>
                <c:when test="${requestScope.pagination eq 4}">
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="table-pagination"/>
                        <div class="w3-bar">
                            <label>«</label>
                            <button name= "pagination" value = "1" class="w3-button w3-round">1</button>
                            <button name= "pagination" value = "2" class="w3-button w3-round">2</button>
                            <button name= "pagination" value = "3" class="w3-button w3-round">3</button>
                            <button name= "pagination" value = "4" class="w3-button w3-teal w3-round">4</button>
                            <label>»</label>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="table-pagination"/>
                        <div class="w3-bar">
                            <label>«</label>
                            <button name= "pagination" value = "1" class="w3-button w3-teal w3-round">1</button>
                            <button name= "pagination" value = "2" class="w3-button w3-round">2</button>
                            <button name= "pagination" value = "3" class="w3-button w3-round">3</button>
                            <button name= "pagination" value = "4" class="w3-button w3-round">4</button>
                            <label>»</label>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>


<!-- End Page Container -->
</div>


<footer class="main-footer">
    ${main_footer}
</footer>

</body>
<style>
    .main-footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: teal;
        color: white;
        text-align: center;
    }
    body {
        background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
