package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;
import services.BookService;

@Component
@Transactional
public class StringToBookConverter implements Converter<String, Book> {

	@Autowired
	BookService bookService;

	@Override
	public Book convert(String text) {
		Book result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = bookService.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
