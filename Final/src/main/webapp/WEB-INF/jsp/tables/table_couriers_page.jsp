
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>
    <title>Good-Couriers</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../../../img/greenlogo.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.admin.courier_applications" var="courier_applications"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers" var="couriers"/>
    <fmt:message bundle="${loc}" key="local.admin.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>

    <fmt:message bundle="${loc}" key="local.admin.couriers_table.first" var="Id"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.second" var="Name"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.third" var="Mark"/>
    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

    <fmt:message bundle="${loc}" key="local.admin.orders_table.more_details" var="more_details"/>
</head>
<body>
<c:if test="${not sessionScope.user.role.getValue().equals('admin')}">
    <c:redirect url="../error/error404.jsp"/>
</c:if>
    <div>
        <div class="w3-container w3-teal main-panel-header ">
            <a class="w3-bar-item w3-button w3-padding-large w3-hide-small">
                Good-Couriers.com
            </a>

            <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
            <my:headLanguage/>

        </div>

        <!-- Sidebar -->
        <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
            <h5 class="w3-bar-item">Menu</h5>

            <!-- Command 1-->
            <div class="sign-in">
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="get-new-couriers-records"/>
                    <button class="w3-button w3-bar-item w3-padding-large btn-lg btn-block " type="submit">${courier_applications}</button>
                </form>
            </div>


            <!-- Command 2-->
            <div class="sign-in">
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="get-all-couriers"/>
                    <button class="w3-bar-item w3-button w3-padding-large btn-lg w3-teal btn-block">${couriers}</button>
                </form>
            </div>

            <!-- Command 3-->
            <div class="sign-in">
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="get-all-orders"/>
                    <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${orders}</button>
                </form>
            </div>

            <!-- Command 4-->
            <div class="sign-in">
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="get-all-goods"/>
                    <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${goods}</button>
                </form>
            </div>

            <!-- Command 5-->
            <div class="sign-in">
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="get-all-transport"/>
                    <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${transport}</button>
                </form>
            </div>
        </div>

        <!-- TABLE-->
        <div style="margin-left:20%; margin-right:15%;">
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
                <br>
                <div class="pagination">
                    <my:paginator limit="${sessionScope.limitCount}" total="${sessionScope.totalCount}" command="get-all-couriers"/>
                </div>
            </div>
        </div>


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
        background:	url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
