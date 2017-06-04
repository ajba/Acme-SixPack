package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Folder;
import domain.Message;
import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageService messageService;

	public FolderService() {
		super();
	}

	public Folder create() {

		Folder f = new Folder();

		Collection<Message> m = new HashSet<Message>();

		f.setName("");
		f.setMessages(m);
		f.setAdditionalFolderOwner(actorService.findByPrincipal());

		return f;
	}

	public Folder create(String name, Actor c) {

		Folder f = new Folder();

		Collection<Message> m = new HashSet<Message>();

		f.setName(name);
		f.setMessages(m);
		f.setAdditionalFolderOwner(actorService.findByPrincipal());

		return f;
	}

	public Map<Folder, Collection<Message>> listFolderMessage() {

		UserAccount ua = LoginService.getPrincipal();
		Assert.assertNotNull(ua);

		Map<Folder, Collection<Message>> m = null;
		Actor a = actorService.findByPrincipal();

		m = new LinkedHashMap<Folder, Collection<Message>>();
		m.put(a.getInbox(), a.getInbox().getMessages());
		m.put(a.getOutbox(), a.getOutbox().getMessages());
		m.put(a.getTrashbox(), a.getTrashbox().getMessages());
		m.put(a.getSpambox(), a.getSpambox().getMessages());

		if (!a.getAdditionalFolders().equals(null)) {
			for (Folder f : a.getAdditionalFolders()) {

				m.put(f, f.getMessages());
			}
		}

		return m;
	}

	public void delete(Folder f) {

		Assert.assertNotNull(f);
		Assert.assertNotNull(LoginService.getPrincipal());

		for (Message m : f.getMessages()) {
			messageService.delete(m);
		}

		folderRepository.delete(f);
	}

	public Folder findOne(int id) {

		Folder f = folderRepository.findOne(id);
		Assert.assertSame(actorService.findByPrincipal(), f.getAdditionalFolderOwner());

		return f;
	}

	public Collection<Folder> findAll() {

		return folderRepository.findAll();
	}

	public Folder createForActor(String name, Actor owner) {

		Folder folder = new Folder();
		folder.setName(name);
		folder.setAdditionalFolderOwner(owner);
		folder.setMessages(new HashSet<Message>());

		return folderRepository.save(folder);
	}
	
	public Folder save(Folder f){
		return folderRepository.save(f);
	}
}
