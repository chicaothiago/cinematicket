<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<link rel="stylesheet"
      href="<c:url value="resources/css/show-movies.css"/>">
<link rel="stylesheet"
      href="<c:url value="resources/css/home.css"/>">

<div id="home-show-movies"
     class="row justify-center home-section">
    <div class="row justify-center">
        <div class="movie-home-box-title">
            <h3>Last 4 movies</h3>
        </div>
    </div>
    <c:forEach items="${movies}"
               var="movie">
        <div class="movie-home movie-box-shadow column justify-center align-stretch items-stretch">
            <div class="movie-home-img">
                <img src="${movie.url_image}"
                     alt="Movie's Image">
            </div>
            <div class="movie-home-title">
                    ${movie.name}
            </div>
        </div>
    </c:forEach>
</div>

<div class="row home-section justify-center">
    <div class="row cinema-section">
        <div class="row justify-center">
            <div class="movie-home-box-title">
                <h3>Cinemas</h3>
            </div>
        </div>

        <c:forEach items="${cinemas}"
                   var="cinema">
            <div class="cinema-home">
                <div class="cinema-content column justify-center align-stretch items-stretch">
                    <div class="cinema-title">
                        <h3>${cinema.name}</h3>
                    </div>
                    <div class="cinema-address">
                            ${cinema.address.getFullAddress()}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:import url="WEB-INF/views/layout/footer.jsp"/>