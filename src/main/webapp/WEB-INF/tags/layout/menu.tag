<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row justify-center menu-header-box">
    <div class="col menu">
        <a class="menu-header" href="<c:url value="/home" />">Home</a>
    </div>
    <div class="col menu">
        <a class="menu-header" href="<c:url value="/cinemas" />">Cinemas</a>
    </div>
    <div class="col menu">
        <a class="menu-header" href="<c:url value="/movies" />">Movies</a>
    </div>
</div>
<script>
    // Const are not using just to avoid problems with ECMA :)
    var currentHref = window.location.href
    var elements = document.getElementsByClassName("menu-header")
    for (var e in elements) {
        if (currentHref.toLocaleLowerCase().indexOf(elements[e].innerHTML.toLocaleLowerCase()) > 0) {
            elements[e].classList.add("menu-current-header")
        }
    }
</script>
