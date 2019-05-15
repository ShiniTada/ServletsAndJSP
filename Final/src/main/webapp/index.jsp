
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>
    <title>
        Good-Couriers
    </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/img/greenlogo.png" type="image/png">

    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">


    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.sign_in" var="header_sign_in"/>
    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>


    <fmt:message bundle="${loc}" key="local.home.title" var="title"/>

    <fmt:message bundle="${loc}" key="local.home.to_couriers" var="to_couriers"/>
    <fmt:message bundle="${loc}" key="local.home.to_couriers.first" var="to_couriers_first"/>
    <fmt:message bundle="${loc}" key="local.home.to_couriers.second" var="to_couriers_second"/>
    <fmt:message bundle="${loc}" key="local.home.to_couriers.third" var="to_couriers_third"/>
    <fmt:message bundle="${loc}" key="local.home.to_couriers.button" var="to_couriers_button"/>

    <fmt:message bundle="${loc}" key="local.home.to_customers" var="to_customers"/>
    <fmt:message bundle="${loc}" key="local.home.to_customers.first" var="to_customers_first"/>
    <fmt:message bundle="${loc}" key="local.home.to_customers.second" var="to_customers_second"/>
    <fmt:message bundle="${loc}" key="local.home.to_customers.third" var="to_customers_third"/>
    <fmt:message bundle="${loc}" key="local.home.to_customers.button" var="to_customers_button"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

</head>




<body>

<div class="main-page">
    <div class="w3-container w3-teal main-panel-header ">
        <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
            Good-Couriers.com
        </a>


        <my:headLanguage/>
        <div class="w3-right">
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="open_sign_in"/>
                <button class="w3-btn w3-right w3-bar-item w3-button w3-teal btn-block w3-padding-large">${header_sign_in}</button>
            </form>
        </div>

        <div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
            <h1 class="title w3-animate-top w3-text-teal w3-hover-text-black" style="text-shadow:1px 1px 0 #444">
                <span>${title}</span></h1>
        </div>
    </div>
    <!-- The Grid -->
    <div class="w3-content w3-row w3-row-padding w3-center " style="max-width:900px; margin-top:220px ">
        <div class="w3-col" style="width:20%"><p></p></div>
        <div class="w3-container w3-center">
            <!-- Left Column -->
            <div class="w3-col m3" style="width:40%">
                <div class="w3-center w3-container w3-round w3-transparent w3-hover-teal
                                    w3-border w3-border-teal w3-hover-border-black w3-animate-left" >
                    <h4>${to_couriers}</h4>
                    <hr>
                    <p>${to_couriers_first}</p>
                    <p>${to_couriers_second}</p>
                    <p>${to_couriers_third}</p>
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="open_courier_registration"/>
                        <button class="w3-round w3-text-black w3-border w3-boarder-white w3-teal w3-section w3-padding">${to_couriers_button}</button>
                    </form>
<%--                    <a class="w3-button w3-round w3-text-black w3-border w3-boarder-white--%>
<%--                                    w3-teal w3-section w3-padding" href="${pageContext.request.contextPath}--%>
<%--                                    /jsp/courier_sign_up.jsp">${to_couriers_button}</a>--%>
                </div>
            </div>
            <!-- End Left Column -->
            <div class="w3-col" style="width:20%"><p></p></div>
            <!-- Right Column -->
            <div class="w3-col m3"  style="width:40%">
                <div class="w3-card w3-container w3-round w3-transparent w3-hover-teal
                                w3-border w3-border-teal w3-hover-border-black  w3-animate-right">
                    <h4>${to_customers}</h4>
                    <hr>
                    <p>${to_customers_first}</p>
                    <p>${to_customers_second}</p>
                    <p>${to_customers_third}</p>
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="open_customer_registration"/>
                        <button class="w3-round w3-text-black w3-border w3-boarder-white w3-teal w3-section w3-padding">${to_customers_button}</button>
                    </form>
                </div>
            </div>
            <!-- End Right Column -->
        </div>
        <div class="w3-col" style="width:20%"><p></p></div>
    </div>
    <!-- End Grid -->
</div>

<footer class="main-footer">
    ${main_footer}

        ${pageContext.request.characterEncoding}
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
        /*background: url(img/ground.png);*/
        background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>

</html>
