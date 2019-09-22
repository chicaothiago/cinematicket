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
                <div class="panel-header">
                    <div class="row justify-between items-center">
                        <div>
                            <h4>List of Cinemas</h4>
                        </div>
                        <div>
                            <a href="<c:url value="/cinema/crud" />">
                                <button class="btn btn-blue">
                                    <strong>New</strong>
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="panel-content">
                    <table border
                           width="100%">
                        <thead>
                        <th>Cinema</th>
                        <th>Address</th>
                        <th class="table-action"></th>
                        </thead>
                        <tbody>
                        <c:if test="${fn:length(cinemas) > 0}">
                            <c:forEach var="cinema"
                                       items="${cinemas}">
                                <tr>
                                    <td>${cinema.name}</td>
                                    <td>${cinema.address.getFullAddress()}</td>
                                    <td>
                                        <a href="<c:url value="/cinema/crud?id=${cinema.id}"/>">
                                            <button class="btn btn-small btn-orange">
                                                Editar
                                            </button>
                                        </a>
                                        <form action="<c:url value="/cinema/crud"/>" method="DELETE">
                                            <input type="hidden" name="delete_id" value="${cinema.id}">
                                            <button type="submit" class="btn btn-small btn-red">
                                                Remover
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(cinemas) == 0}">
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