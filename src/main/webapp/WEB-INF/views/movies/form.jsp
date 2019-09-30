<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/layout"
          prefix="layout" %>


<layout:body_default>
    <jsp:body>
        <div class="panel">
            <div class="panel-content">
                <form action="<c:url value="/movies/crud" />"
                      method="post">
                    <c:if test="${movie.id != null}">
                        <input type="text"
                               name="id"
                               hidden
                               value="${movie.id}">
                    </c:if>
                    <fieldset>
                        <div class="column">
                            <label for="form-movie-name">Nome: </label>
                            <input id="form-movie-name"
                                   name="name"
                                   type="text"
                                   required
                            <c:if test="${not empty movie.name}">
                                   value=${movie.name}</c:if>
                            >
                            <label for="form-movie-image">Image: </label>
                            <input id="form-movie-image"
                                   name="url_image"
                                   type="text"
                                   required
                            <c:if test="${not empty movie.url_image}">
                                   value=${movie.url_image}</c:if>
                            >
                            <p style="margin-top: 20px;">Cinemas that movie will be shown</p>
                            <div class="row">
                                <c:forEach var="cinema"
                                           items="${cinemas}">
                                    <div>
                                        <input type="checkbox"
                                               class="checkbox"
                                               id="form-check-${cinema.id}-${cinema.name}"
                                               value="${cinema.id}"
                                               name="cinemas[]"
                                        <c:if test="${movie.cinemas.containsKey(cinema.id.toString())}">
                                               checked
                                        </c:if>
                                        >
                                        <label for="form-check-${cinema.id}-${cinema.name}">${cinema.name}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </fieldset>
                    <button type="submit"
                            class="btn btn-large btn-green"
                            style="margin-top: 10px;">Salvar
                    </button>
                </form>
            </div>
        </div>
    </jsp:body>
</layout:body_default>