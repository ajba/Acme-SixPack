package controllers.actor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;
import services.ActorService;
import services.FolderService;
import services.MessageService;

@Controller
@RequestMapping("/message/actor")
public class MessageControler extends AbstractController {

	@Autowired
	private FolderService folderService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	public MessageControler() {
		super();
	}
	
	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "0") String folderId) {

		ModelAndView m;
		Actor a = actorService.findByPrincipal();
		Folder carpeta;

		Folder folder = folderService.create();

		if (new Integer(folderId) == 0) {
			carpeta = a.getInbox();
			if (carpeta.getAdditionalFolderOwner() == null) {
				a = actorService.asignarCarpetas(a);
			}
		} else {
			carpeta = folderService.findOne(new Integer(folderId));
		}

		m = new ModelAndView("message/list");
		m.addObject("carpeta", carpeta);
		m.addObject("actor", a);
		m.addObject("folder", folder);
		m.addObject("requestURI", "message/list.do");

		return m;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView m;

		Message mens = messageService.create();
		mens.setSender(actorService.findByPrincipal());

		m = createEditModelAndView(mens);

		return m;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Message mens, BindingResult binding) {

		ModelAndView m;
		mens.setSender(actorService.findByPrincipal());
		messageService.save(mens);
		m = new ModelAndView("redirect:list.do?folderId=0");

		return m;
	}

	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam int folderOrigen,
			@RequestParam int folderDestino, @RequestParam int mens) {

		ModelAndView m;
		Message mensaje = messageService.findOne(mens);
		Folder origen = folderService.findOne(folderOrigen);
		Folder destino = folderService.findOne(folderDestino);
		Set<Message> mOrigen = new HashSet<Message>(origen.getMessages());
		mOrigen.remove(mensaje);
		Set<Message> mDestino = new HashSet<Message>(destino.getMessages());
		mDestino.add(mensaje);
		origen.setMessages(mOrigen);
		destino.setMessages(mDestino);
		folderService.save(origen);
		folderService.save(destino);
		m = new ModelAndView("redirect:list.do?folderId=" + folderOrigen);
		return m;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam int mens) {

		ModelAndView m;
		int id = actorService.findByPrincipal().getTrashbox().getId();
		Message mensaje = messageService.findOne(mens);
		messageService.delete(mensaje);
		m = new ModelAndView("redirect:list.do?folderId=" + id);
		return m;
	}

	protected ModelAndView createEditModelAndView(Message mens) {

		ModelAndView m;

		m = createEditModelAndView(mens, null);

		return m;
	}

	protected ModelAndView createEditModelAndView(Message mens, String message) {

		ModelAndView m;
		List<Actor> actors = actorService.findAll();
		m = new ModelAndView("message/edit");
		m.addObject("mens", mens);
		m.addObject("message", message);
		m.addObject("actors", actors);

		return m;
	}

}