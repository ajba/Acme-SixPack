package controllers.administrator;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.KeyWords;
import services.KeyWordsService;

@Controller
@RequestMapping("/spamWords/administrator")
public class KeyWordsAdministratorController {

	@Autowired
	private KeyWordsService keyWordsService;

	public KeyWordsAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView m = new ModelAndView("spamWords/administrator/list");

		ArrayList<KeyWords> keyWords = new ArrayList<KeyWords>(keyWordsService.findAll());

		m.addObject("spamWords", keyWords.get(0).getSpamWords());
		m.addObject("requestURI", "spamWords/administrator/list.do");

		return m;
	}

	@RequestMapping(value = "/addWord", method = RequestMethod.GET)
	public ModelAndView addWord(@RequestParam(required = false, defaultValue = "") String word) {

		ModelAndView m = new ModelAndView();
		word = word.trim();
		
		if (!word.equals("")) {
			
			ArrayList<KeyWords> keyWords = new ArrayList<KeyWords>(keyWordsService.findAll());
			KeyWords k = keyWords.get(0);
			
			ArrayList<String> sw = new ArrayList<String>(k.getSpamWords());
			sw.add(word);
			k.setSpamWords(sw);
			
			keyWordsService.save(k);// Nunca debe petar, solo petará cuando un
									// customer gracioso llame al save
		}

		m.setViewName("redirect:list.do");

		return m;
	}

	@RequestMapping(value = "/deleteWord", method = RequestMethod.GET)
	public ModelAndView deleteWord(@RequestParam int id) {

		ModelAndView m = new ModelAndView();

		ArrayList<KeyWords> keyWords = new ArrayList<KeyWords>(keyWordsService.findAll());
		KeyWords k = keyWords.get(0);

		if (!k.getSpamWords().isEmpty()) {

			if (k.getSpamWords().size() > id) {

				ArrayList<String> sw = new ArrayList<String>(k.getSpamWords());
				sw.remove(id);
				k.setSpamWords(sw);

				keyWordsService.save(k);// Nunca debe petar, solo petará cuando
										// un
										// customer gracioso llame al save
			}
		}

		keyWords = new ArrayList<KeyWords>(keyWordsService.findAll());

		m.setViewName("redirect:list.do");
		return m;
	}
}
