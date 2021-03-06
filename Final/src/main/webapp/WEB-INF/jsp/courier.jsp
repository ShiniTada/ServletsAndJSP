
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
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>
    <fmt:message bundle="${loc}" key="local.courier.first_message" var="first_message"/>
    <fmt:message bundle="${loc}" key="local.courier.last_message" var="last_message"/>
    <fmt:message bundle="${loc}" key="local.courier.block_message" var="block_message"/>

    <fmt:message bundle="${loc}" key="local.courier.marks" var="marks"/>
    <fmt:message bundle="${loc}" key="local.courier.quality" var="quality"/>
    <fmt:message bundle="${loc}" key="local.courier.politeness" var="politeness"/>
    <fmt:message bundle="${loc}" key="local.courier.punctuality" var="punctuality"/>
    <fmt:message bundle="${loc}" key="local.courier.common" var="common"/>
    <fmt:message bundle="${loc}" key="local.courier.orders" var="orders"/>

    <fmt:message bundle="${loc}" key="local.courier.order.customer" var="customer"/>
    <fmt:message bundle="${loc}" key="local.courier.order.price" var="price"/>
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
     <fmt:message bundle="${loc}" key="local.courier.table_empty" var="table_empty"/>

    <fmt:message bundle="${loc}" key="local.customer.status.posted" var="posted"/>
    <fmt:message bundle="${loc}" key="local.customer.status.delivered" var="delivered"/>
    <fmt:message bundle="${loc}" key="local.customer.status.completed" var="completed"/>
    <fmt:message bundle="${loc}" key="local.customer.status.denied" var="denied"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.accept" var="accept"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.reject" var="reject"/>

    <fmt:message bundle="${loc}" key="local.registration_form.cycle" var="cycle"/>
    <fmt:message bundle="${loc}" key="local.registration_form.motorcycle" var="motorcycle" />
    <fmt:message bundle="${loc}" key="local.registration_form.car" var="car" />
    <fmt:message bundle="${loc}" key="local.registration_form.truck" var="truck"/>
    <fmt:message bundle="${loc}" key="local.registration_form.food" var="food" />
    <fmt:message bundle="${loc}" key="local.registration_form.tech" var="tech"/>
    <fmt:message bundle="${loc}" key="local.registration_form.furniture" var="furniture"/>
    <fmt:message bundle="${loc}" key="local.registration_form.easy_to_beat" var="easy_to_beat"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

</head>
<body>
<c:if test="${not sessionScope.user.role.getValue().equals('courier')}">
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
    <div class="w3-content w3-margin-top" style="max-width:1500px;">


        <!-- The Grid -->
        <div class="w3-row-padding">
            <br>
            <!-- Left Column -->
            <c:choose>
                <c:when test="${sessionScope.courierRecord.status == 0}">
            <div class="w3-col" style="width:20%">

                <div class="w3-white w3-text-grey w3-card-4">
                    <div class="w3-container">
                        <p class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${marks}</b></p>
                        <p>${quality}</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markQuality}%">
                                    ${sessionScope.courierRecord.markQuality}%</div>
                        </div>
                        <p>${politeness}</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPoliteness}%">
                                    ${sessionScope.courierRecord.markPoliteness}%</div>
                        </div>
                        <p>${punctuality}</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPunctuality}%">
                                    ${sessionScope.courierRecord.markPunctuality}%</div>
                        </div>
                        <hr>
                        <p>${common}</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markCommon}%">
                                    ${sessionScope.courierRecord.markCommon}%</div>
                        </div>
                        <br>

                    </div>
                </div>
                <br>

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <div class="w3-container">
                        <p></p>
                        <h4 class="w3-opacity"><b>${transport}</b></h4>
                        <ul class=" w3-large">
                            <c:forEach var="elem" items="${sessionScope.listTransport}">
                                <c:choose>
                                    <c:when test="${elem eq 'cycle'}"> <li><c:out value="${cycle}"/> </li></c:when>
                                    <c:when test="${elem eq 'motorcycle'}"> <li><c:out value="${motorcycle}"/> </li></c:when>
                                    <c:when test="${elem eq 'car'}"> <li><c:out value="${car}"/> </li></c:when>
                                    <c:when test="${elem eq 'truck'}"> <li><c:out value="${truck}"/> </li></c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>

                    </div>
                    <div class="w3-container">
                        <p></p>
                        <h4 class="w3-opacity"><b>${goods}</b></h4>
                        <ul class=" w3-large">
                            <c:forEach var="elem" items="${sessionScope.listGoods}">
                                <c:choose>
                                    <c:when test="${elem eq 'food'}"><li><c:out value="${food}"/> </li></c:when>
                                    <c:when test="${elem eq 'tech'}"><li><c:out value="${tech}"/> </li></c:when>
                                    <c:when test="${elem eq 'furniture'}"><li><c:out value="${furniture}"/> </li></c:when>
                                    <c:when test="${elem eq 'easy-to-beat'}"><li><c:out value="${easy_to_beat}"/> </li></c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>

                    </div>
                </div>

                <!-- End Left Column -->
            </div>
            <div class="w3-col" style="width:80%">

                </c:when>
                <c:when test="${sessionScope.courierRecord.status == 2}">
                    <div class="w3-col" style="width:100%">
                </c:when>
                <c:otherwise>
                    <div class="w3-col" style="width:20%">

                        <div class="w3-white w3-text-grey w3-card-4">
                            <div class="w3-container">
                                <p class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${marks}</b></p>
                                <p>${quality}</p>
                                <div class="w3-light-grey w3-round-xlarge w3-small">
                                    <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markQuality}%">
                                        ${sessionScope.courierRecord.markQuality}%</div>
                                </div>
                                <p>${politeness}</p>
                                <div class="w3-light-grey w3-round-xlarge w3-small">
                                    <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPoliteness}%">
                                        ${sessionScope.courierRecord.markPoliteness}%</div>
                                </div>
                                <p>${punctuality}</p>
                                <div class="w3-light-grey w3-round-xlarge w3-small">
                                    <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPunctuality}%">
                                        ${sessionScope.courierRecord.markPunctuality}%</div>
                                </div>
                                <hr>
                                <p>${common}</p>
                                <div class="w3-light-grey w3-round-xlarge w3-small">
                                    <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markCommon}%">
                                        ${sessionScope.courierRecord.markCommon}%</div>
                                </div>
                                <br>

                            </div>
                        </div>
                        <br>

                        <div class="w3-container w3-card w3-white w3-margin-bottom">
                            <div class="w3-container">
                                <p></p>
                                <h4 class="w3-opacity"><b>${transport}</b></h4>
                                <ul class=" w3-large">
                                    <c:forEach var="elem" items="${sessionScope.listTransport}">
                                        <c:choose>
                                            <c:when test="${elem eq 'cycle'}"> <li><c:out value="${cycle}"/> </li></c:when>
                                            <c:when test="${elem eq 'motorcycle'}"> <li><c:out value="${motorcycle}"/> </li></c:when>
                                            <c:when test="${elem eq 'car'}"> <li><c:out value="${car}"/> </li></c:when>
                                            <c:when test="${elem eq 'truck'}"> <li><c:out value="${truck}"/> </li></c:when>
                                        </c:choose>
                                    </c:forEach>
                                </ul>

                            </div>
                            <div class="w3-container">
                                <p></p>
                                <h4 class="w3-opacity"><b>${goods}</b></h4>
                                <ul class=" w3-large">
                                <c:forEach var="elem" items="${sessionScope.listGoods}">
                                    <c:choose>
                                        <c:when test="${elem eq 'food'}"><li><c:out value="${food}"/> </li></c:when>
                                        <c:when test="${elem eq 'tech'}"><li><c:out value="${tech}"/> </li></c:when>
                                        <c:when test="${elem eq 'furniture'}"><li><c:out value="${furniture}"/> </li></c:when>
                                        <c:when test="${elem eq 'easy-to-beat'}"><li><c:out value="${easy_to_beat}"/> </li></c:when>
                                    </c:choose>
                                </c:forEach>
                                </ul>

                            </div>
                        </div>

                        <!-- End Left Column -->
                    </div>
                    <div class="w3-col" style="width:80%">

                    </c:otherwise>
            </c:choose>


            <!-- Right Column -->
                <div class="w3-container w3-card w3-white w3-margin-bottom">
                        <c:choose>
                            <c:when test="${sessionScope.courierRecord.status == 0}">
                                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${first_message}</b></p>
                            </c:when>
                            <c:when test="${sessionScope.courierRecord.status == 2}">
                                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${last_message}</b></p>
                            </c:when>
                            <c:when test="${sessionScope.courierRecord.status == 3}">
                                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${block_message}</b></p>
                            </c:when>
                            <c:when test="${sessionScope.existedOrders eq null}">
                                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                                <h5>${table_empty}</h5>
                            </c:when>
                            <c:when test="${sessionScope.existedOrders.size() == 0}">
                                <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                                <h5>${table_empty}</h5>
                            </c:when>
                            <c:otherwise>
                                <div class="w3-container">
                                    <p class="w3-large"><b><i class="w3-margin-right w3-opacity w3-text-teal"></i>${words_existed_orders}</b></p>
                                    <table class="w3-table-all"  >
                                        <thead>
                                        <tr class="w3-teal">
                                            <th>${id}</th>
                                            <th>${from}</th>
                                            <th>${to}</th>
                                            <th>${introduction_date}</th>
                                            <th>${goods_description}</th>
                                            <th>${customer}</th>
                                            <th>${price}</th>
                                            <th>${status}</th>
                                            <th>${accept}</th>
                                            <th>${reject}</th>
                                        </tr>
                                        </thead>

                                        <c:forEach var="elem" items="${sessionScope.existedOrders}">
                                            <tr class="w3-hover-light-blue">
                                                <td><c:out value="${elem.id}"/> </td>
                                                <td><c:out value="${elem.from}"/> </td>
                                                <td><c:out value="${elem.to}"/> </td>
                                                <td><c:out value="${elem.introductionDate}"/> </td>
                                                <td><c:out value="${elem.goodsDescription}"/> </td>
                                                <td><c:out value="${elem.customer.login}"/> </td>
                                                <td><c:out value="${elem.price}$"/> </td>
                                                <c:choose>
                                                    <c:when test="${elem.status.getValue() eq 'posted'}">
                                                        <td class="w3-light-blue"><c:out value="${posted}"/> </td>
                                                        <td>
                                                            <form action="Controller" method="post">
                                                                <input type="hidden" name="command" value="accept-order"/>
                                                                <button class="w3-block w3-round w3-center w3-teal
                                         w3-button" name="accept" value="${elem.id}" style="  max-width:150px">${accept}</button>
                                                            </form>
                                                        </td>
                                                        <td>
                                                            <form action="Controller" method="post">
                                                                <input type="hidden" name="command" value="accept-order"/>
                                                                <button class="w3-block w3-round w3-center w3-flat-alizarin
                                         w3-button" name="reject" value="${elem.id}" style=" max-width:150px">${reject}</button>
                                                            </form>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${elem.status.getValue() eq 'delivered'}">
                                                        <td class="w3-khaki"><c:out value="${delivered}"/> </td>
                                                        <td> - </td>
                                                        <td> - </td>
                                                    </c:when>
                                                </c:choose>

                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                                <br>
                            </c:otherwise>
                        </c:choose>
                        <br>
                    </div>
                    <br>
                 <c:choose>
            <c:when test="${sessionScope.courierRecord.status == 0}">
            </c:when>
            <c:when test="${sessionScope.courierRecord.status == 2}">
            </c:when>
            <c:when test="${sessionScope.courierRecord.status == 3}">
            </c:when>
            <c:otherwise>
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
                                            <th>${customer}</th>
                                            <th>${price}</th>
                                            <th>${status}</th>
                                        </tr>
                                        </thead>

                                        <c:forEach var="elem" items="${sessionScope.completedOrders}">
                                            <tr class="w3-hover-light-blue">
                                                <td><c:out value="${elem.id}"/> </td>
                                                <td><c:out value="${elem.from}"/> </td>
                                                <td><c:out value="${elem.to}"/> </td>
                                                <td><c:out value="${elem.introductionDate}"/> </td>
                                                <td><c:out value="${elem.goodsDescription}"/> </td>
                                                <td><c:out value="${elem.customer.login}"/> </td>
                                                <td><c:out value="${elem.price}$"/> </td>
                                                <c:choose>
                                                    <c:when test="${elem.status.getValue() eq 'completed'}">
                                                        <td class="w3-green"><c:out value="${completed}"/> </td>
                                                    </c:when>
                                                    <c:when test="${elem.status.getValue() eq 'denied'}">
                                                        <td class="w3-flat-alizarin"><c:out value="${denied}"/> </td>
                                                    </c:when>
                                                </c:choose>

                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                                <br>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br>
        </c:otherwise>
    </c:choose>

                <!-- End Right Column -->
            </div>

            <!-- End Grid -->
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
