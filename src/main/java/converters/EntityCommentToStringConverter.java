package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EntityComment;

@Component
@Transactional
public class EntityCommentToStringConverter implements Converter<EntityComment, String> {

	@Override
	public String convert(EntityComment entityComment) {
		String result;

		if (entityComment == null)
			result = null;
		else
			result = String.valueOf(entityComment.getId());

		return result;
	}

}
