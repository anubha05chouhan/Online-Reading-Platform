package dao;

import model.Author;
import model.Book;

public interface BookDAO extends DAO<Book> {
	 int insertBooks(Book book, Author author);
}
