<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Menu" name="title"/>
   <jsp:param value="" name="js"/>
   <jsp:param value="css/category.css" name="css"/>
</jsp:include>

    <script type="text/javascript" src="js/removeImageOnBackground.js"></script>
     <!-- Page Content -->
    <div class="container">

      <!-- Page Heading -->
      <h1 class="my-4"><%=request.getAttribute("categoryName") %>
        <small></small>
      </h1>

      <div class="row">
          <c:forEach items="${products}" var="product">
              <div class="col-lg-4 col-sm-6 portfolio-item">
                  <div class="card h-100">
                      <a href="#"><img width="50%" height="35%" class="card-img-top" src="${product.getUrlofimg()}" alt=""></a>
                      <div class="card-body">
                          <h4 class="card-title">
                              <a class="btn btn-lg btn-secondary" href="/product?productId=${product.getProductid()}">${product.getName()}</a>
                          </h4>
                          <p class="card-text">${product.getDescription()}</p>
                      </div>
                  </div>
                  <hr style=" border: none; background-color: black; color: black;height: 2px;">
              </div>
          </c:forEach>
      </div>
      <!-- /.row -->

      <!-- Pagination -->
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">1</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">3</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </div>

<jsp:include page="/pages/layout/footer.jsp"/>