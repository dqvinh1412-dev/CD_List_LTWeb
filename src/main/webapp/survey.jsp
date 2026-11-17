<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.html" %>

<main>
    <h1>Thanks for taking our survey!</h1>
    <p>Here is the information that you entered:</p>

    <p>
        <strong>Email:</strong>
        <span class="data">${surveyDTO.user.email}</span><br>

        <strong>First Name:</strong>
        <span class="data">${surveyDTO.user.firstName}</span><br>

        <strong>Last Name:</strong>
        <span class="data">${surveyDTO.user.lastName}</span><br>

        <strong>Heard From:</strong>
        <span class="data">${surveyDTO.survey.hearAbout}</span><br>

        <strong>Updates:</strong>
        <span class="data">${surveyDTO.survey.wantsAnnouncements ? 'Yes' : 'No'}</span><br>

        <c:if test="${surveyDTO.survey.wantsAnnouncements}">
            <strong>Contact Via:</strong>
            <span class="data">${surveyDTO.survey.contactVia}</span>
        </c:if>
    </p>
</main>

<%@ include file="/includes/footer.jsp" %>
