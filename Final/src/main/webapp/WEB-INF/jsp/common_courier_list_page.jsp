
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>

<html>
<head>
    <title>Good-Couriers</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../../img/greenlogo.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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
<c:if test="${not sessionScope.user.role.getValue().equals('customer')}">
    <c:redirect url="error/error404.jsp"/>
</c:if>
<div class="w3-container w3-teal main-panel-header ">
    <a class="w3-bar-item w3-button w3-padding-large w3-hide-small">
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
            <div class="w3-container w3-center">
                <h2 class="title w3-text-black" style="margin-top: 40px;">${couriers}</h2>

                <table class="w3-table-all">
                    <thead>
                    <tr class="w3-teal">
                        <th>${Id}</th>
                        <th>${Name}</th>
                        <th>${Mark}</th>
                        <td>${more_details}</td>
                    </tr>
                    </thead>
                    <c:forEach var="elem" items="${sessionScope.listCourierRecords}" >
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
                </table>
                <br><br><br>
            </div>
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
