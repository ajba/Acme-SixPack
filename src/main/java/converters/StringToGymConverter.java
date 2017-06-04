package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Gym;
import services.GymService;

@Component
@Transactional
public class StringToGymConverter implements Converter<String, Gym> {

	@Autowired
	GymService gymService;

	@Override
	public Gym convert(String text) {
		Gym result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = gymService.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
