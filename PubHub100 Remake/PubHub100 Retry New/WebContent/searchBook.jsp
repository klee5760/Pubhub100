<!-- Header -->
<jsp:include page="header.jsp" />
	
<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 	Just some stuff you need -->
<header>
  <div class="container">

  
<c:choose>
<c:when test="${not empty message }">
  <p class="alert ${messageClass}">${message }</p>
<%
  session.setAttribute("message", null);
  session.setAttribute("messageClass", null);
%>
</c:when>
</c:choose>

<h1>PUBHUB <small>Search by Book Tag</small></h1>
		<hr class="book-primary">

        <FORM ACTION="SearchBook" METHOD="POST">
            <INPUT TYPE="TEXT" NAME="tag_name" required = "required" placeholder = "Enter Tag Name">
                </BR>
            <BR>
            <INPUT TYPE="SUBMIT" value="Search">
                </BR>
                </BR>
        </FORM>
           <table class="table table-striped table-hover table-responsive">
	
			<thead>
				<tr>
					<td> </td>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Author:</td>
					<td>Publish Date:</td>
					<td>Price:</td>
					<td></td>
					<td> </td>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${booklist}">
					<tr>
					<td></td>

						<td><c:out value="${book.getIsbn13()}" /></td>
						
						<td><c:out value="${book.getTitle()}" /></td>
						<td><c:out value="${book.getAuthor()}" /></td>
						<td><c:out value="${book.getPublishDate()}" /></td>
						<td><fmt:formatNumber value="${book.getPrice() }" type="CURRENCY"/></td>
						<td><form action="DownloadBook" method="get">
								<input type="hidden" name="isbn_13" value="${book.getIsbn13()}">
								<button class="btn btn-success">Download</button>
							</form></td>
						<td><form action="ViewBookDetails?isbn=${book.getIsbn13()}" method="get">
								<input type="hidden" name="isbn_13" value="${book.getIsbn13()}">
								<button class="btn btn-primary">Details</button>
							
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</header>



	<!-- Footer -->
	<jsp:include page="footer.jsp" />