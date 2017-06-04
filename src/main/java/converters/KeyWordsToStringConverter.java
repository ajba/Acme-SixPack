package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.KeyWords;

@Component
@Transactional
public class KeyWordsToStringConverter implements Converter<KeyWords, String> {

	@Override
	public String convert(KeyWords keyWords) {
		String result;

		if (keyWords == null)
			result = null;
		else
			result = String.valueOf(keyWords.getId());

		return result;
	}

}
