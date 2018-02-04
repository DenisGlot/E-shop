
<%@page import="java.util.Calendar"%>
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="Home" name="title"/>
   <jsp:param value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" name="js"/>
   <jsp:param value="css/carousel.css" name="css"/>
</jsp:include>


      <script type="text/javascript" src="js/removeImageOnBackground.js"></script>
      <!-- Carousel
      ================================================== -->
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
       <!-- Indicators -->
       <ol class="carousel-indicators">
         <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
         <li data-target="#myCarousel" data-slide-to="1"></li>
         <li data-target="#myCarousel" data-slide-to="2"></li>
       </ol>
       <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img class="first-slide" src="imgs/slide_1.jpg" style="position: relative; width: 900px; height:900px; top:-200px;" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>New Era of Nikon</h1>
              <p>Only today! <code>-40%</code> on all cameras!</p>
              <p><a class="btn btn-lg btn-primary" href="/item?product=1" role="button">Buy it now!</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="imgs/slide_2.jpg" style="position: relative; width: 900px; height:900px; top:-200px;" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Make youself happy</h1>
              <p>Acquire a heater with new technologty which keeps you warm a whole winter</p>
              <p><a class="btn btn-lg btn-primary" href="/item?product=1" role="button">Acquire it now!</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="third-slide" src="imgs/slide_3.jpg" style="position: relative; width: 900px; height:900px; top:-200px;" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Printer which you've never seen</h1>
              <p>With colored glass </p>
              <p><a class="btn btn-lg btn-primary" href="/item?product=1" role="button">What is it?</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->

      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; <%=Calendar.getInstance().get(Calendar.YEAR)%> Was stolen from bootstrap. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
      </footer>

    </div><!-- /.container -->

    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="js/holder.min.js"></script>
    <jsp:include page="/pages/layout/footer.jsp"/>
