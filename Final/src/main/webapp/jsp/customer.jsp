
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>
    <title>Good-Couriers</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">

    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.courier.order.id" var="id"/>
    <fmt:message bundle="${loc}" key="local.courier.order.courier" var="courier"/>
    <fmt:message bundle="${loc}" key="local.courier.order.from" var="from"/>
    <fmt:message bundle="${loc}" key="local.courier.order.to" var="to"/>
    <fmt:message bundle="${loc}" key="local.courier.order.delivery_date" var="introduction_date"/>
    <fmt:message bundle="${loc}" key="local.courier.order.status" var="status"/>
    <fmt:message bundle="${loc}" key="local.courier.order.goods_description" var="goods_description"/>
    <fmt:message bundle="${loc}" key="local.customer.words.completed_orders" var="words_completed_orders"/>
    <fmt:message bundle="${loc}" key="local.customer.words.existed_orders" var="words_existed_orders"/>
    <fmt:message bundle="${loc}" key="local.customer.words.empty_table" var="words_empty_table"/>
    <fmt:message bundle="${loc}" key="local.customer.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="local.customer.add_order" var="add_order"/>
    <fmt:message bundle="${loc}" key="local.customer.evaluate" var="evaluate"/>

    <fmt:message bundle="${loc}" key="local.customer.status.posted" var="posted"/>
    <fmt:message bundle="${loc}" key="local.customer.status.delivered" var="delivered"/>
    <fmt:message bundle="${loc}" key="local.customer.status.completed" var="completed"/>
    <fmt:message bundle="${loc}" key="local.customer.status.denied" var="denied"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
</head>
<body>
<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>

    <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
    <my:headLanguage/>
</div>

<div class="w3-content w3-margin-top" style="max-width:1400px;">


    <!-- The Grid -->
    <div class="w3-row-padding">
    <br>
    <div class="w3-container w3-card w3-white w3-margin-bottom">
        <c:choose>
            <c:when test="${sessionScope.existedOrders eq null}">
                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                <h5>${words_empty_table}</h5>
            </c:when>
            <c:when test="${sessionScope.existedOrders.size() == 0}">
                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                <h5>${words_empty_table}</h5>
            </c:when>
            <c:otherwise>
                <div class="w3-container">
                    <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                    <table class="w3-table-all">
                        <thead>
                        <tr class="w3-teal">
                            <th>${id}</th>
                            <th>${from}</th>
                            <th>${to}</th>
                            <th>${introduction_date}</th>
                            <th>${goods_description}</th>
                            <th>${courier}</th>
                            <th>${status}</th>
                            <th>${evaluate}</th>
                        </tr>
                        </thead>

                        <c:forEach var="elem" items="${sessionScope.existedOrders}">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.from}"/> </td>
                                <td><c:out value="${elem.to}"/> </td>
                                <td><c:out value="${elem.introductionDate}"/> </td>
                                <td><c:out value="${elem.goodsDescription}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <c:choose>
                                    <c:when test="${elem.status.getValue() eq 'posted'}">
                                        <td class="w3-light-blue"><c:out value="${posted}"/> </td>
                                        <td>
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="set-mark"/>
                                                <button class="w3-block w3-round  w3-disabled w3-center w3-khaki
                                         w3-button" name="courierLogin" value="${elem.courier.login}" style="  max-width:150px">${evaluate}</button>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:when test="${elem.status.getValue() eq 'delivered'}">
                                        <td class="w3-teal"><c:out value="${delivered}"/> </td>
                                        <td>
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="set-marks"/>
                                                <button class="w3-block w3-round w3-center w3-khaki
                                         w3-button" name="courierLogin" value="${elem.courier.login}" style="  max-width:150px">${evaluate}</button>
                                            </form>
                                        </td>
                                    </c:when>
                                </c:choose>

                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <br>
            </c:otherwise>
        </c:choose>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="get-all-couriers"/>
            <button class="w3-block w3-round w3-center w3-teal w3-button" style="max-width:150px">${add_order}</button>
        </form>

        <br>
     </div>
    <br>
    <div class="w3-container w3-card w3-white w3-margin-bottom">
        <c:choose>
            <c:when test="${sessionScope.completedOrders eq null}">
                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_completed_orders}</b></p>
                <h5>${words_empty_table}</h5>
            </c:when>
            <c:when test="${sessionScope.completedOrders.size() == 0}">
                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_completed_orders}</b></p>
                <h5>${words_empty_table}</h5>
            </c:when>
            <c:otherwise>
                <div class="w3-container">
                    <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_completed_orders}</b></p>
                    <table class="w3-table-all">
                        <thead>
                        <tr class="w3-teal">
                            <th>${id}</th>
                            <th>${from}</th>
                            <th>${to}</th>
                            <th>${introduction_date}</th>
                            <th>${goods_description}</th>
                            <th>${courier}</th>
                            <th>${status}</th>
                            <th>${delete}</th>
                        </tr>
                        </thead>

                        <c:forEach var="elem" items="${sessionScope.completedOrders}">
                            <tr class="w3-hover-light-blue">
                                <td><c:out value="${elem.id}"/> </td>
                                <td><c:out value="${elem.from}"/> </td>
                                <td><c:out value="${elem.to}"/> </td>
                                <td><c:out value="${elem.introductionDate}"/> </td>
                                <td><c:out value="${elem.goodsDescription}"/> </td>
                                <td><c:out value="${elem.courier.login}"/> </td>
                                <c:choose>
                                    <c:when test="${elem.status.getValue() eq 'completed'}">
                                        <td class="w3-green"><c:out value="${completed}"/> </td>
                                    </c:when>
                                    <c:when test="${elem.status.getValue() eq 'denied'}">
                                        <td class="w3-light-tomato"><c:out value="${denied}"/> </td>
                                    </c:when>
                                </c:choose>
                                <td>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="delete-order"/>
                                        <button class="w3-block w3-round w3-center w3-red
                                         w3-button" name="deletedOrderId" value="${elem.id}" style="  max-width:150px">${delete}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <br>
            </c:otherwise>
        </c:choose>
    </div>

    <br>


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
        background:  url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
