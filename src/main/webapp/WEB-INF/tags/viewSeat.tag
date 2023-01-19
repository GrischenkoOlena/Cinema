<%@ attribute name="placeAttr" type="com.hryshchenko.cinema.dto.SeatDTO" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>

<c:set var="hrefPlace" value="controller?action=basket&placeId=${placeAttr.id}"/>
<c:choose>
    <c:when test="${placeAttr.state.id == 1}">
        <c:choose>
            <c:when test="${placeAttr.category.category == 'premium'}">
                <p class="p-3 mb-2 bg-primary bg-gradient text-white">
                    <a class="page-link" href="${hrefPlace}">${placeAttr.place} </a>
                </p>
            </c:when>
            <c:when test="${placeAttr.category.category == 'classic'}">
                <p class="p-3 mb-2 bg-info bg-gradient text-white">
                    <a class="page-link" href="${hrefPlace}">${placeAttr.place} </a>
                </p>
            </c:when>
        </c:choose>
    </c:when>
    <c:when test="${placeAttr.state.id == 2}">
        <p class="p-3 mb-2 bg-danger bg-gradient text-white">
            ${placeAttr.place}
        </p>
    </c:when>
</c:choose>
