package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examples.pubhub.dao.*;
import examples.pubhub.model.*;
import examples.pubhub.utilities.*;
@WebServlet("/SearchBook")
public class SearchBookServlet extends HttpServlet {
    private static final long serialVersionUTD = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tag = request.getParameter("tag_name");

        System.out.println(tag);

        BookTagDAO dao = DAOUtilities.getBookTagDAO();
        ArrayList<Book> books = dao.getAllBooksByTag(tag);

        ArrayList<String> bookString = new ArrayList<String>();

        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle());
            bookString.add(books.get(i).getTitle());
        }
        System.out.println(bookString.size());

        session.setAttribute("booklist",books);

        request.getSession().setAttribute("message","Books successfully retrieved");
        request.getSession().setAttribute("messageClass","alert-success");
        response.sendRedirect("SearchBook?tag_name=" + tag);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/searchBook.jsp").forward(request, response);
        HttpSession session = request.getSession();
        session.invalidate();
    }

}

