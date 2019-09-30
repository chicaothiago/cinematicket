<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/layout"
          prefix="layout" %>


<layout:body_default>
    <jsp:body>
        <div class="panel">
            <div class="panel-content">
                <form action="<c:url value="/cinema/crud" />"
                      method="post">
                    <c:if test="${cinema.id != null}">
                    <input type="text"
                           hidden
                           name="id"
                           value="${cinema.id}">
                    </c:if>
                    <fieldset>
                        <div class="column">
                            <label for="form-cinema-name">Nome: </label>
                            <input id="form-cinema-name"
                                   name="name"
                                   type="text"
                                   <c:if test="${not empty cinema.name}">value=${cinema.name}</c:if>
                                       required>
                            <label
                                for="form-cinema-address-street">Street: </label>
                            <input id="form-cinema-address-street"
                                   name="street"
                                   type="text"
                                   <c:if
                                       test="${not empty cinema.address.street}">value=${cinema.address.street}</c:if>

                                       required>
                            <label
                                for="form-cinema-address-number">Number: </label>
                            <input id="form-cinema-address-number"
                                   name="number"
                                   type="number"
                                   <c:if
                                       test="${not empty cinema.address.number}">value=${cinema.address.number}</c:if>

                                       required>
                            <label
                                for="form-cinema-address-zipcode">Zipcode: </label>
                            <input id="form-cinema-address-zipcode"
                                   name="zipcode"
                                   type="text"
                                   <c:if
                                       test="${not empty cinema.address.zipcode}">value=${cinema.address.zipcode}</c:if>

                                       required>
                            <label
                                for="form-cinema-address-city">City: </label>
                            <input id="form-cinema-address-city"
                                   name="city"
                                   type="text"
                                   <c:if test="${not empty cinema.address.city}">value=${cinema.address.city}</c:if>

                                       required>
                            <label
                                for="form-cinema-address-state">State: </label>
                            <input id="form-cinema-address-state"
                                   name="state"
                                   type="text"
                                   <c:if
                                       test="${not empty cinema.address.state}">value=${cinema.address.state}</c:if>

                                       required>
                            <label
                                for="form-cinema-address-country">Country: </label>
                            <input id="form-cinema-address-country"
                                   name="country"
                                   type="text"
                            <c:if test="${not empty cinema.address.country}">
                                   value=${cinema.address.country}
                                   </c:if>
                                       required>
                            <c:if test="${not empty cinema.address.id}">
                                <input type="hidden"
                                       name="address_id"
                                       value="${cinema.address.id}">
                            </c:if>
                            <c:if test="${not empty cinema.id}">
                                <input type="hidden"
                                       name="id"
                                       value="${cinema.id}">
                            </c:if>
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