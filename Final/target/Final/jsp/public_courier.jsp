
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>
    <fmt:message bundle="${loc}" key="local.courier.first_message" var="first_message"/>

    <fmt:message bundle="${loc}" key="local.courier.marks" var="marks"/>
    <fmt:message bundle="${loc}" key="local.courier.quality" var="quality"/>
    <fmt:message bundle="${loc}" key="local.courier.politeness" var="politeness"/>
    <fmt:message bundle="${loc}" key="local.courier.punctuality" var="punctuality"/>
    <fmt:message bundle="${loc}" key="local.courier.common" var="common"/>

    <fmt:message bundle="${loc}" key="local.courier.order.customer" var="customer"/>
    <fmt:message bundle="${loc}" key="local.courier.order.from" var="from"/>
    <fmt:message bundle="${loc}" key="local.courier.order.to" var="to"/>
    <fmt:message bundle="${loc}" key="local.courier.order.delivery_date" var="introduction_date"/>
    <fmt:message bundle="${loc}" key="local.courier.order.status" var="status"/>
    <fmt:message bundle="${loc}" key="local.courier.order.goods_description" var="goods_description"/>
    <fmt:message bundle="${loc}" key="local.courier.table_empty" var="table_empty"/>
    <fmt:message bundle="${loc}" key="local.courier.offer_a_job" var="offer_a_job"/>
    <fmt:message bundle="${loc}" key="local.courier.back" var="back"/>

    <fmt:message bundle="${loc}" key="local.admin.couriers_table.accept" var="accept"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers_table.reject" var="reject"/>

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

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">


    <!-- The Grid -->
    <div class="w3-row-padding">
        <br>
        <div class="w3-container w3-card w3-white w3-margin-bottom">
            <br>
             <div class = "w3-cell-row">
                 <br>
                 <div class="w3-cell w3-container" style="width:80%;">
                <label class="w3-xlarge">${sessionScope.courierRecord.courier.login}</label>
                 </div>
                <c:choose>
                    <c:when test="${sessionScope.user.role.getValue() eq 'admin'}">
                        <c:choose>
                            <c:when test="${sessionScope.courierRecord.status == 0}">
                                 <div class="w3-cell w3-container" style="width:7%;">
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="accept_courier"/>
                                        <button class="w3-block w3-cell-row w3-right w3-round w3-center w3-flat-nephritis
                                         w3-button" name="accept" value="${sessionScope.courierRecord.id}" style="max-width:150px">${accept}</button>
                                    </form>
                                 </div>
                                <div class="w3-cell w3-container" style="width:1%;"></div>
                                <div class="w3-cell w3-container" style="width:7%;">
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="reject_courier"/>
                                        <button class="w3-block w3-cell-row w3-right w3-round w3-center w3-flat-alizarin
                                         w3-button" name="reject" value="${sessionScope.courierRecord.id}" style="max-width:150px">${reject}</button>
                                    </form>
                                </div>
                            </c:when>
                            <c:when test="${sessionScope.courierRecord.status == 1}">
                                <div class="w3-cell w3-container" style="width:20%;"></div>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                                <div class="w3-cell w3-container" style="width:8%;"></div>
                                <div class="w3-cell w3-container" style="width:7%;">
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="open-new-order-form"/>
                                        <button class="w3-block w3-round w3-center w3-green w3-button w3-cell w3-right"
                                                name="courierId" value="${sessionScope.courierRecord.courier.id}" style="max-width:200px">${offer_a_job}</button>
                                    </form>
                                </div>
                    </c:otherwise>
                </c:choose>
             </div>
                <br>
            </div>

            <br>
            <!-- Left Column -->
            <div class="w3-third">
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
                </div><br>
                <!-- End Left Column -->
            </div>

            <!-- Right Column -->
            <div class="w3-twothird ">
                <div class="w3-container w3-card w3-white w3-margin-bottom w3-row-padding">
                    <div class="w3-container w3-half">
                        <p></p>
                        <h4 class="w3-opacity"><b>${transport}</b></h4>
                        <ul class=" w3-large">
                            <c:forEach var="elem" items="${sessionScope.listTransport}">
                                <li><c:out value="${elem}"/> </li>
                            </c:forEach>
                        </ul>

                    </div>
                    <div class="w3-container w3-half">
                        <p></p>
                        <h4 class="w3-opacity"><b>${goods}</b></h4>
                        <ul class=" w3-large">
                            <c:forEach var="elem" items="${sessionScope.listGoods}">
                                <li><c:out value="${elem}"/> </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="w3-container w3-margin-bottom">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="back_after_details"/>
                        <button class="w3-block w3-round w3-center w3-teal w3-button w3-cell w3-right"  name="back" value="afterDetails"
                                 style="max-width:100px">${back}</button>
                    </form>
                </div>
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
