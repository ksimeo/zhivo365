<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
    <script>
        $(document).ready(function () {
            $('#yourphone').usPhoneFormat({
                format: '(xxx) xxx-xx-xx',
            });

            $('#yourphone2').usPhoneFormat();
        });
    </script>
</head>

<body class="custom">

<spring:url value="/admin/" var="urlHome" />
<spring:url value="/admin/calc" var="urlCalculation" />
<spring:url value="/admin/prods" var="urlProducts" />
<spring:url value="/admin/users" var="urlUsers" />
<spring:url value="/admin/users/1/pswrd" var="urlChangePassword" />
<spring:url value="/admin/logout" var="urlLogout" />
<spring:url value="username" var="userName" />



<title>Живорост365 - круглосуточная служба заказа биогумата</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
