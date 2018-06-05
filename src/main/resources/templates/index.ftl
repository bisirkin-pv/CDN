<#macro page>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>GitHubCDN</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--<link rel="stylesheet" href="/DigitalKeyboard">-->
</head>
<body>
<#include "header.ftl">
<#nested>
<#if auth>
<script>
    /*
    set class active page
    */
    function activePage() {
        var elem = document.querySelector(".navbar-nav");
        elem.querySelectorAll("a").forEach(function(currentValue, currentIndex, listObj) {
            currentValue.parentElement.className = window.location.href === currentValue.toString() ? "active" : "";
        })
    }
    activePage();
</script>
</#if>
</body>
</html>
</#macro>