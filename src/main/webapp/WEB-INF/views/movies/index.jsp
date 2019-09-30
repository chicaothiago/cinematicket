<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags/layout"
          prefix="layout" %>


<layout:body_default>
    <jsp:body>
        <div class="row justify-center">
            <div class="row panel">
                <div class="panel-content">
                    <div class="row justify-between items-center">
                        <div></div>
                        <div>
                            <a href="<c:url value="/movies/crud" />">
                                <button class="btn btn-blue">
                                    <strong>New</strong>
                                </button>
                            </a>
                        </div>
                    </div>
                    <table class="table"
                           width="100%">
                        <thead>
                        <th>Movie</th>
                        <th>Image</th>
                        <th>Cinemas</th>
                        <th class="table-action"></th>
                        </thead>
                        <tbody>
                        <c:if test="${fn:length(movies) > 0}">
                            <c:forEach var="movie"
                                       items="${movies}">
                                <tr>
                                    <td>${movie.name}</td>
                                    <td class="table-img">
                                        <a href="${movie.url_image}"
                                           target="_blank">
                                            <img src="${movie.url_image}"
                                                 alt="" style="max-height: 5cm;">
                                        </a>
                                    </td>

                                    <td>
                                        <c:forEach items="${movie.cinemas.values()}" var="cinema">
                                            <p> ${cinema.name}</p>
                                        </c:forEach>
                                    </td>

                                    <td class="table-action">
                                        <a href="<c:url value="/movies/crud?id=${movie.id}"/>">
                                            <button class="btn btn-small btn-orange">
                                                Edit
                                            </button>
                                        </a>
                                        <form action="<c:url value="/movies/crud"/>" method="post">
                                            <input type="hidden" name="delete_id" value="${movie.id}">
                                            <button type="submit" class="btn btn-small btn-red">
                                                Remove
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(movies) == 0}">
                            <tr>
                                <td colspan="4">Cinemas is empty</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:body_default>