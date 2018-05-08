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
    <!--<link rel="stylesheet" href="/DigitalKeyboard">-->
</head>
<body>
<#include "header.ftl">
<#nested>
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
</body>
</html>
</#macro>