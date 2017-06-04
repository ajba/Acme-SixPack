package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EntityComment;
import services.EntityCommentService;

@Component
@Transactional
public class StringToEntityCommentConverter implements Converter<String, EntityComment> {

	@Autowired
	EntityCommentService entityCommentService;

	@Override
	public EntityComment convert(String text) {
		EntityComment result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = entityCommentService.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
