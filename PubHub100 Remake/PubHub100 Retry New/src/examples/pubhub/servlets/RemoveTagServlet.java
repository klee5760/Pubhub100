package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.*;
import examples.pubhub.model.*;
import examples.pubhub.utilities.*;

@WebServlet("/RemoveTag")
public class RemoveTagServlet extends HttpServlet {
    private static final long serialVersionUTD =1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        boolean isSuccess = false;
        String isbn13 = request.getParameter("isbn13");
        String tag = request.getParameter("tag_name");
        BookTag bTag = new BookTag(isbn13,tag);

        BookTagDAO tDao = DAOUtilities.getBookTagDAO();

        isSuccess = tDao.removeBookTag(bTag);
        System.out.println(isSuccess);
        request.setAttributes("book_tags", bTag);

        request.getSession().setAttribute("message", "Removing book tags if it exists.");
        request.getSession().setAttribute("messageClass", "alert-success");
        response.sendRedirect("ViewBookDeatils?isbn13=" + isbn13);

    }
}