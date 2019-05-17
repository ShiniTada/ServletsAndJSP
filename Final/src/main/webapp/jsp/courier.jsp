
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
<%--    <link rel="stylesheet" href="../styles/main.css">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>

    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>
    <fmt:message bundle="${loc}" key="local.courier.first_message" var="first_message"/>

    <fmt:message bundle="${loc}" key="local.courier.order.customer" var="customer"/>
     <fmt:message bundle="${loc}" key="local.courier.order.from" var="from"/>
    <fmt:message bundle="${loc}" key="local.courier.order.to" var="to"/>
     <fmt:message bundle="${loc}" key="local.courier.order.introduction_date" var="introduction_date"/>
    <fmt:message bundle="${loc}" key="local.courier.order.status" var="status"/>
     <fmt:message bundle="${loc}" key="local.courier.order.goods_description" var="goods_description"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

</head>
<body>

<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-left"><img src="../img/logo.jpg" style="height:30px;"></a>
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>
    <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
    <my:headLanguage language="${language}"/>
</div>

    <!-- Page Container -->
    <div class="w3-content w3-margin-top" style="max-width:1400px;">


        <!-- The Grid -->
        <div class="w3-row-padding">
            <br>
            <!-- Left Column -->
            <div class="w3-third">

                <div class="w3-white w3-text-grey w3-card-4">
                    <div class="w3-container">
                        <p class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>Marks</b></p>
                        <p>Quality</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markQuality}%">
                                ${sessionScope.courierRecord.markQuality}%</div>
                        </div>
                        <p>Politeness</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPoliteness}%">
                                ${sessionScope.courierRecord.markPoliteness}%</div>
                        </div>
                        <p>Punctuality</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markPunctuality}%">
                                ${sessionScope.courierRecord.markPunctuality}%</div>
                        </div>
                        <hr>
                        <p>Common</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:${sessionScope.courierRecord.markCommon}%">
                                ${sessionScope.courierRecord.markCommon}%</div>
                        </div>
                        <br>

                    </div>
                </div><br>

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <div class="w3-container">
                        <p></p>
                        <h4 class="w3-opacity"><b>${transport}</b></h4>
                        <ul class=" w3-large">
                            <c:forEach var="elem" items="${sessionScope.listTransport}">
                                <li><c:out value="${elem}"/> </li>
                            </c:forEach>
                        </ul>

                    </div>
                    <div class="w3-container">
                        <p></p>
                        <h4 class="w3-opacity"><b>${goods}</b></h4>
                        <ul class=" w3-large">
                        <c:forEach var="elem" items="${sessionScope.listGoods}">
                            <li><c:out value="${elem}"/> </li>
                        </c:forEach>
                        </ul>

                    </div>
                </div>

                <!-- End Left Column -->
            </div>

            <!-- Right Column -->
            <div class="w3-twothird ">

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <div class="w3-container">
                        <c:choose>
                                <c:when test="${sessionScope.courierRecord.status eq 0}">
                                    <h3>${first_message}</h3>
                                </c:when>
                                <c:when test="${sessionScope.courierRecord.status eq 2}">
                                    <h3>You are deleted!</h3>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                            <c:when test="${sessionScope.listCustomerOrder eq null}">
                                                <h3>TABLE IS EMPTY</h3>
                                            </c:when>
                                            <c:otherwise>
                                                <table class="w3-table-all">
                                                    <thead>
                                                    <tr class="w3-teal">
                                                        <th>${customer}</th>
                                                        <th>${from}</th>
                                                        <th>${to}</th>
                                                        <th>${introduction_date}</th>
                                                        <th>${goods_description}</th>
                                                        <th>${status}</th>
                                                    </tr>
                                                    </thead>

                                                    <c:forEach var="elem" items="${sessionScope.listCustomerOrder}">
                                                        <tr class="w3-hover-light-blue">
                                                            <td><c:out value="${elem.customer.login}"/> </td>
                                                            <td><c:out value="${elem.from}"/> </td>
                                                            <td><c:out value="${elem.to}"/> </td>
                                                            <td><c:out value="${elem.introductionDate}"/> </td>
                                                            <td><c:out value="${elem.goodsDescription}"/> </td>
                                                            <td><c:out value="${elem.status.getValue()}"/> </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>


                    </div>

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
        background: url(../img/ground.png);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
