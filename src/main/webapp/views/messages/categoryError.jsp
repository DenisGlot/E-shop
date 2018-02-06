<jsp:include page="/pages/layout/header.jsp">
    <jsp:param value="Sorry!" name="title"/>
    <jsp:param value="" name="js"/>
    <jsp:param value="css/message.css" name="css"/>
</jsp:include>
<div class="container" style="width: 30%; background: white; border-radius: 6px;">
    <h1 style = "text-align: center;">Sorry!</h1>
    <p style = "text-align: center;">There is no such category!</p>
    <p style = "text-align: center;">
        <a href="/" class="btn btn-lg btn-secondary">Go to main page</a>
    </p>
</div>
<jsp:include page="/pages/layout/footer.jsp"/>