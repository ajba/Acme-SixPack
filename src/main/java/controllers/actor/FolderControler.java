package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder/actor")
public class FolderControler extends AbstractController {

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;

	public FolderControler() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView index(@Valid Folder folder) {
		
		ModelAndView m;
		Actor owner = actorService.findByPrincipal();
		folderService.createForActor(folder.getName(), owner);
		m = new ModelAndView("redirect:../../message/actor/list.do?folderId=0");

		return m;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam int folderId) {
		
		ModelAndView m;
		Folder folder = folderService.findOne(folderId);
		folderService.delete(folder);
		m = new ModelAndView("redirect:../../message/actor/list.do?folderId=0");
		return 	m ;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int folderId, @RequestParam String name) {
		
		ModelAndView m;
		Folder f = folderService.findOne(folderId);
		f.setName(name);
		folderService.save(f);
		m = new ModelAndView("redirect:../../message/actor/list.do?folderId=0");
		return 	m ;
	}

}