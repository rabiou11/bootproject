package com.polarbookshop.catalogservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) throws BookNotFoundException {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) throws BookAlreadyExistsException {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(existingBook.getId(),existingBook.getIsbn(),book.getTitle(),book.getAuthor(),book.getPrice(), existingBook.getPublisher(),
                            existingBook.getCreatedDate(), existingBook.getLastModifiedDate(), existingBook.getVersion());
                    return bookRepository.save(bookToUpdate);
                })
                .orElseGet(() -> {
                    try {
                        return addBookToCatalog(book);
                    } catch (BookAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
