
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>
    <title>Good-Couriers</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">
<%--    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">--%>


    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.admin.courier_applications" var="courier_applications"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers" var="couriers"/>
    <fmt:message bundle="${loc}" key="local.admin.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>

    <fmt:message bundle="${loc}" key="local.admin.transports_or_goods_table.first" var="Type"/>
    <fmt:message bundle="${loc}" key="local.admin.transports_or_goods_table.second" var="Courier"/>
    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
</head>
<body>

<div>
    <div class="w3-container w3-teal main-panel-header ">
        <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-left"><img src="../img/logo.jpg" style="height:30px;"></a>
        <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
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
                <button class="w3-bar-item w3-button w3-padding-large btn-lg btn-block">${couriers}</button>
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
                <button class="w3-bar-item w3-button w3-teal btn-lg btn-block w3-padding-large">${transport}</button>
            </form>
        </div>
    </div>


    <!-- TABLE-->
    <div style="margin-left:20%; margin-right:15%;">
        <div class="w3-container w3-center">
            <h2 class="title w3-text-teal" style="margin-top:100px;">${transport}</h2>
            <table class="w3-table-all">
                <thead>
                <tr class="w3-teal">
                    <th>${Type}</th>
                    <th>${Courier}</th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${requestScope.pagination eq 2}">
                        <c:forEach var="elem" items="${sessionScope.listTransports}"  begin="5" end="9">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.typeTransport.getValue()}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${requestScope.pagination eq 3}">
                        <c:forEach var="elem" items="${sessionScope.listTransports}"  begin="10" end="14">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.typeTransport.getValue()}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${requestScope.pagination eq 4}">
                        <c:forEach var="elem" items="${sessionScope.listTransports}"  begin="15" end="19">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.typeTransport.getValue()}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="elem" items="${sessionScope.listTransports}"  begin="0" end="4">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.typeTransport.getValue()}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
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
                        <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${orders}</button>
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
        background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
