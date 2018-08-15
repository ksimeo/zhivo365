<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Живорост365 - круглосуточная служба заказа биогумата</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="Круглосуточная служба заказа Биогумата" name="description" />
    <meta content="Заказать биогумат, купить биогумат on-line, биоудобрения, биогумат калия" name="keywords" />
    <spring:url value="/resources/styles/standard.css" var="coreCss" />
    <spring:url value="/resources/styles/bootstrap.min.css" var="bootstrapCss" />
    <link href="../../../resources/styles/standard.css" rel="stylesheet" />
    <link href="../../../resources/styles/bootstrap.min.css" rel="stylesheet" />
    <link rel="javascript" type="text/javascript" href="../../../resources/scripts/custom.js" />
    <link rel="javascript" type="text/javascript" href="../../../resources/scripts/bootstrap.min.js" />
    <link rel="javascript" type="text/javascript" href="../../../resources/scripts/jquery-3.2.1.min.js" />
    <link rel="shortcut icon" href="../../../resources/images/ico/favicon.ico">
    <link rel="javascript" type="text/javascript" href="../../../resources/scripts/calc.js" />
    <script type="text/javascript" src="../../../resources/scripts/move-top.js.download"></script>
    <script type="text/javascript" src="../../../resources/scripts/easing.js.download"></script>
    <script type="text/javascript" src="../../../resources/scripts/custom.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://unpkg.com/jquery-input-mask-phone-number@1.0.0/dist/jquery-input-mask-phone-number.js"></script>
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <script type="text/javascript">
        $(document).ready(function () {
            $('#yourphone').usPhoneFormat({
                format: '(xxx) xxx-xxxx',
            });

            $('#yourphone2').usPhoneFormat();
        });
    </script>
</head>

<spring:url value="/admin/" var="urlHome" />
<spring:url value="/admin/calc" var="urlCalculation" />
<spring:url value="/admin/prods" var="urlProducts" />
<spring:url value="/admin/users" var="urlUsers" />
<spring:url value="/admin/users/1/pswrd" var="urlChangePassword" />
<spring:url value="/admin/logout" var="urlLogout" />
<spring:url value="username" var="userName" />

<spring:message code="label_good_day" var="labelGoodDay"/>
<spring:message code="label_good_morning" var="labelGoodMorning"/>
<spring:message code="label_good_evening" var="labelGoodEvening"/>
<spring:message code="label_good_night" var="labelGoodNight"/>
<spring:message code="label_welcome" var="labelWelcome"/>
<spring:message code="label_round_the_clock" var="labelRoundTheClock"/>
<spring:message code="label_for_begining" var="labelForBegining"/>
<spring:message code="label_ask_a_question" var="labelAskAQuestion"/>
<spring:message code="label_ok" var="labelOk"/>

<body class="custom">
<div class="container">
    <jsp:include page="../fragments/lang.jsp"/>
    <br/>
    <br/>
    <div class="well">
        <h1>
            <%--Добрый день!--%>
            <script language="JavaScript"> day = new Date();
            hour = day.getHours();
            if (hour >= 5 && hour < 12) greeting = "${labelGoodMorning}";
            else {
                if (hour >= 12 && hour < 18) greeting = "${labelGoodDay}";
                else {
                    if (hour >= 18 && hour < 24) greeting = "${labelGoodEvening}";
                    else {
                        if (hour >= 0 && hour < 5) greeting = "${labelGoodNight}";
                    }
                }
            }
            document.write(greeting); </script>
        </h1>
        <h1>${labelWelcome}&nbsp;<img src="../../../resources/images/png/logo_mini.png"/></h1>
        <h2>- ${labelRoundTheClock}!</h2>
        <hr/>
        <h3>${labelForBegining}:</h3>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#yourphone').usPhoneFormat({
                    format: '(xxx) xxx-xxxx',
                });

                $('#yourphone2').usPhoneFormat();
            });
        </script>

        <div class="btn-group">
            <h3>
                <sf:form action="phone" method="POST" modelAttribute="phoneForm">
                    +38 &nbsp;
                    <sf:input type='text' id='yourphone' path="phoneNumber" placeholder="(0--) --- ----" maxlength="14"
                              autofocus="autofocus" style="width: 90px;" />
                    <button class="btn btn-md btn-success" type="submit">${labelOk}</button>
                    <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal1">
                        <img src="../../../resources/images/png/question.png">&nbsp;${labelAskAQuestion}</button>
                </sf:form>
            </h3>
            <br/>
            <br/>
        </div>
    </div>
</div>

<%--<jsp:include page="fragments/messageform.jsp"/>--%>
</body>

<%--<jsp:include page="fragments/footer.jspx" />--%>

</html>
