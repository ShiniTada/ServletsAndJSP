<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" uri="mine" %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="../../img/greenlogo.png" type="image/png">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.header_order_form" var="header_order_form"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.what_deliver" var="what_deliver"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.task" var="task"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.address" var="address"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.date" var="date"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.payment" var="payment"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.less_three" var="less_three"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.three_ten" var="three_ten"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.more_ten" var="more_ten"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.weight" var="weight"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.kg" var="kg"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.add" var="add"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.count_goods" var="count_goods"/>
    <fmt:message bundle="${loc}" key="local.back" var="back"/>
    <fmt:message bundle="${loc}" key="local.courier.order.from" var="from"/>
    <fmt:message bundle="${loc}" key="local.courier.order.to" var="to"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.help_price" var="help_price"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.enter_what_deliver" var="enter_what_deliver"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.enter_from" var="enter_from"/>
    <fmt:message bundle="${loc}" key="local.customer.order_form.enter_to" var="enter_to"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

    <title>
        ${header_order_form}
    </title>
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

<div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
    <h2 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">
        <span>${header_order_form}</span></h2>
</div>

<div class="w3-container w3-round w3-card-4 w3-border w3-border-black w3-teal" style="margin-left:32%; margin-right:32%; margin-top:140px;">
    <form name="courier-registration-form" method="post" action="Controller">
        <input type="hidden" name="command" value="add-filled-customer-order"/>
        <br>
        <h3 class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${task}</b></h3>
        <div class="w3-cell-row" style="margin-top:20px;">
            <div class="w3-container w3-cell" style="width:35%">
                ${what_deliver}
            </div>
            <div class="w3-container w3-cell" style="width:65%">
                <input class="w3-input w3-border" type="text" name="whatDeliver" required placeholder="${enter_what_deliver}">
            </div>
        </div>
        <br>

        <h3 class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${address}</b></h3>
        <div class="w3-cell-row" style="margin-top:20px;">
            <div class="w3-container w3-cell" style="width:35%">
                ${from}
            </div>
            <div class="w3-container w3-cell" style="width:65%">
                <input class="w3-input w3-border" type="text"  name="from" required placeholder="${enter_from}">
            </div>
        </div>
        <div class="w3-cell-row" style="margin-top:20px;">
            <div class="w3-container w3-cell" style="width:35%">
                ${to}
            </div>
            <div class="w3-container w3-cell" style="width:65%">
                <input class="w3-input w3-border" type="text"  name="to" required  placeholder="${enter_to}">
            </div>
        </div>
        <br>

        <h3 class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${date}</b></h3>
        <div class="w3-cell-row" style="margin-top:20px;">
            <div class="w3-container w3-cell" style="width:35%">
                <i class="fa fa-calendar" style="font-size:25px"></i>
            </div>
            <div class="w3-container w3-cell" style="width:65%">
                <input type="date" name="deliveryDate" required autofocus>
            </div>
        </div>
        <br>

        <h3 class="w3-large"><b><i class="w3-margin-right w3-text-teal"></i>${payment}</b></h3>
        <div class="w3-cell-row" style="margin-top:20px;">
            <div class="w3-container w3-cell" style="width:15%">
                ${count_goods}
            </div>
            <div class="w3-container w3-cell" style="width:35%">
                <input type="radio" name="numberOfGoods" value="lessThree"> ${less_three}<br>
                <input type="radio" name="numberOfGoods" value="threeTen"> ${three_ten}<br>
                <input type="radio" name="numberOfGoods" value="moreTen"> ${more_ten}<br>
            </div>
            <div class="w3-container w3-cell" style="width:15%">
                ${weight}
            </div>
            <div class="w3-container w3-cell" style="width:35%">
                <input type="radio" name="weight" value="lessThreeKg"> ${less_three} ${kg}<br>
                <input type="radio" name="weight" value="threeTenKg"> ${three_ten} ${kg}<br>
                <input type="radio" name="weight" value="moreTenKg"> ${more_ten} ${kg}<br>
            </div>
        </div>
        <p>${help_price}</p>
        <br>

        <div class="w3-cell-row ">
            <div class="w3-container w3-cell" style="width:35%;"> </div>
            <div class="w3-container w3-center w3-cell" style="width:30%;">
                <button class="w3-button w3-center w3-round btn-block w3-green" type="submit">${add}</button>
            </div>
            <div class="w3-container  w3-cell" style="width:35%;"> </div>
        </div>
    </form>
</div>
<br><br><br><br>


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
    input:required:valid {
        border-color: green;
    }
    input:required:invalid {
        border-color: red;
    }
</style>
</html>
