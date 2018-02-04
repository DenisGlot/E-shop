
<jsp:include page="/pages/layout/header.jsp">
   <jsp:param value="FeedBack" name="title"/>
   <jsp:param value="" name="js"/>
   <jsp:param value="" name="css"/>
</jsp:include>
   <div class="container" style="width: 60%; background: #e2c0c7; border-radius: 6px;">
	   <section id="contact" class="content-section text-center">
	       <div class="contact-section">
	           <div class="container" style ="width: 100%">
	             <h2>Contact Us</h2>
	             <p>Feel free to shout us by feeling the contact form or visiting our social network sites like Fackebook, Whatsapp, Twitter.</p>
	             <div class="row">
	               <div class="col-md-8 col-md-offset-2">
	                 <form class="form-horizontal">
	                   <div class="form-group">
	                     <label for="exampleInputName2">Topic</label>
	                     <input name="subject" type="text" class="form-control" id="exampleInputName2" placeholder="Subject">
	                   </div>
	                   <div class="form-group ">
	                     <label for="exampleInputText">Your Message</label>
	                     <textarea  name = "message" class="form-control" rows="10" placeholder="Description"></textarea> 
	                   </div>
	                   <button type="submit" class="btn btn-default">Send Message</button>
	                 </form>
	               </div>
	             </div>
	           </div>
	       </div>
	   </section>
	 </div>
   <jsp:include page="/pages/layout/footer.jsp"/>