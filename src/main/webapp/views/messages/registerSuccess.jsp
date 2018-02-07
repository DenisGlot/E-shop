<jsp:include page="/pages/layout/header.jsp">
    <jsp:param value="Thank you!" name="title"/>
    <jsp:param value="" name="js"/>
    <jsp:param value="css/message.css" name="css"/>
</jsp:include>
<div class="container" style="width: 30%; background: white; border-radius: 6px;">
    <h1 style = "text-align: center;">You were successfully signed up!</h1>
    <p style = "text-align: center;">
        <a href="/" class="btn btn-lg btn-secondary">Go to main page</a>
    </p>
</div>
<jsp:include page="/pages/layout/footer.jsp"/>
