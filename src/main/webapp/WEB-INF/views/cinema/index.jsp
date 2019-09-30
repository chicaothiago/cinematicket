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
                        <div>
                        </div>
                        <div>
                            <a href="<c:url value="/cinema/crud" />">
                                <button class="btn btn-blue">
                                    <strong>New</strong>
                                </button>
                            </a>
                        </div>
                    </div>
                    <table class="table"
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
                                    <td class="table-action">
                                        <a href="<c:url value="/cinema/crud?id=${cinema.id}"/>">
                                            <button class="btn btn-small btn-orange">
                                                Edit
                                            </button>
                                        </a>
                                        <form action="<c:url value="/cinema/crud"/>" method="DELETE">
                                            <input type="hidden" name="delete_id" value="${cinema.id}">
                                            <button type="submit" class="btn btn-small btn-red">
                                                Remove
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