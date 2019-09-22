<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<link rel="stylesheet" href="<c:url value="resources/css/show-movies.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/home.css"/>">

<div id="home-show-movies" class="row justify-center show-movies home-section ">
    <div class="movie-home column justify-center align-stretch items-stretch border-test">
        <div>
            movie
        </div>
    </div>
    <div class="movie-home column justify-center align-stretch items-stretch border-test">
        <div>
            movie
        </div>
    </div>
    <div class="movie-home column justify-center align-stretch items-stretch border-test">
        <div>
            movie
        </div>
    </div>
    <div class="movie-home column justify-center align-stretch items-stretch border-test">
        <div>
            movie
        </div>
    </div>
</div>

<div class="row home-section justify-center">
    <div class="border-test">
        cinemas
    </div>
</div>

<c:import url="WEB-INF/views/layout/footer.jsp"/>