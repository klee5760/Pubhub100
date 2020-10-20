package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

public class BookTagDAOImpl implements BookTagDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;

    @Override
    public ArrayList<BookTag> getAllBookTags(Book book) {

        ArrayList<BookTag> bookTagList = new ArrayList<>();

        try{
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM Book_Tag WHERE isbn_13=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getIsbn13());
			ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                BookTag bookT = new BookTag();
                bookT.setIsbn13(rs.getString("isbn_13"));
                bookT.setTagName(rs.getString("tag_name"));

                bookTagList.add(bookT);

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResourses();
        }
        return bookTagList;
    }

            
    public ArrayList<Book> getAllBooksByTag(String tag) {

        ArrayList<Book> book = new ArrayList<>();
        try {
            connection = DAOUtilities.getConnection();

            String sql = "SELECT * FROM Books WHERE isbn_13 IN (SELECT isbn_13 from WHERE tag_name ILIKE?)";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, tag);

            ResultSet rs = stmt.executeQuery();
            Book book1 = null;

            while(rs.next()){
                book1 = new Book();
                book1.setIsbn13(rs.getString("isbn_13"));
                book1.setTitle(rs.getString("title"));
                book1.setAuthor(rs.getString("author"));
                book1.setPublishDate(LocalDate.now());
                book1.setPrice(Double.parseDouble(rs.getString("price")));
                book.add(book1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResourses();
        }

        return book;
    }

    @Override
    public boolean addBookTag(BookTag bTag) {
        try{
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO Book_Tags(isbn_13, tag_name) VALUES (?, ?)";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, bTag.getIsbn13());
            stmt.setString(2, bTag.getTagName());

            if (stmt.executeUpdate() != 0)
            return true;
            else 
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResourses();
        }

    }

    @Override
    public boolean removeBookTag(BookTag bTag) {
        try {
            connection = DAOUtilities.getConnection();
            String sql = "DELETE FROM Book_Tags WHERE tag_anme=? AND isbn_13 =?";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, bTag.getTagName());
            stmt.setString(2, bTag.getIsbn13());

            if (stmt.executeUpdate() != 0)
            return true;
            else 
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResourses();
    }

}    

private void closeResourses() {
    try{
        if (stmt != null)
    stmt.close();
} catch (SQLException e) {
    System.out.println("Could not close statement!");
    e.printStackTrace();
}

try {

    if (connection != null)
    connection.close();

} catch (SQLException e) {
    System.out.println("Could no close connection!");
    e.printStackTrace();
}

}
}





