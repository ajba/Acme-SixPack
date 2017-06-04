package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ServiceGym;

@Component
@Transactional
public class ServiceGymToStringConverter implements Converter<ServiceGym, String> {

	@Override
	public String convert(ServiceGym serviceGym) {
		String result;

		if (serviceGym == null)
			result = null;
		else
			result = String.valueOf(serviceGym.getId());

		return result;
	}

}
