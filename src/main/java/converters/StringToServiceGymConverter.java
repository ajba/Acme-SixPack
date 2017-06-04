package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ServiceGym;
import services.ServiceGymService;

@Component
@Transactional
public class StringToServiceGymConverter implements Converter<String, ServiceGym> {

	@Autowired
	ServiceGymService serviceGymService;

	@Override
	public ServiceGym convert(String text) {
		ServiceGym result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = serviceGymService.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
