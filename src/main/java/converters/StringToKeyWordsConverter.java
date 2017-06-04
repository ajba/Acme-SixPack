package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.KeyWords;
import services.KeyWordsService;

@Component
@Transactional
public class StringToKeyWordsConverter implements Converter<String, KeyWords> {

	@Autowired
	KeyWordsService keyWordsService;

	@Override
	public KeyWords convert(String text) {
		KeyWords result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = keyWordsService.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
