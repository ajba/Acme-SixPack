package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;
import domain.KeyWords;
import domain.Message;
import repositories.MessageRepository;
import security.LoginService;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private FolderService folderService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private KeyWordsService keyWordsService;

	public MessageService() {
		super();
	}

	// 24.2. Manage his or her folders and messages.
	public Message create() {

		Assert.assertNotNull(LoginService.getPrincipal());

		Message m = new Message();

		m.setSubject("");
		m.setBody("");
		m.setRecipient(null);
		m.setSender(null);
		m.setMomentSent(new Date(System.currentTimeMillis() - 1));
		m.setSpam(false);
		return m;
	}

	public Message create(Message mens) {

		Message m = new Message();

		m.setSubject(mens.getSubject());
		m.setBody(mens.getBody());
		m.setRecipient(mens.getRecipient());
		m.setSender(mens.getSender());
		m.setMomentSent(mens.getMomentSent());
		m.setSpam(mens.getSpam());

		return m;
	}

	public void save(Message m) {

		Assert.assertNotNull(LoginService.getPrincipal());

		m.setMomentSent(new Date(System.currentTimeMillis() - 1));
		m.setSpam(isSpam(m));
		Message mensaje = messageRepository.save(m);

		/*
		 * System.out.println("El mensaje se guarda"); System.out.println(
		 * "mesnaje que se guarda " + mensaje.getBody()); System.out.println(
		 * "mesnaje que se guarda sender  " + mensaje.getSender());
		 * System.out.println("mesnaje que se guarda recipent " +
		 * mensaje.getRecipient());
		 */

		Folder outbox = mensaje.getSender().getOutbox();
		Collection<Message> mOutbox = new HashSet<Message>();
		mOutbox.addAll(outbox.getMessages());
		mOutbox.add(mensaje);
		outbox.setMessages(mOutbox);
		folderService.save(outbox);

		Message mensLlegada = create(mensaje);
		mensaje = messageRepository.save(mensLlegada);
		Folder inbox;
		if (m.getSpam()) {
			inbox = mensaje.getRecipient().getSpambox();
		} else {
			inbox = mensaje.getRecipient().getInbox();
		}
		// System.out.println("guardamos en" + inbox.getName());
		Collection<Message> mInbox = new HashSet<Message>();
		mInbox.addAll(inbox.getMessages());
		mInbox.add(mensaje);
		inbox.setMessages(mInbox);
		folderService.save(inbox);
	}

	public void delete(Message m) {

		Assert.assertNotNull(LoginService.getPrincipal());
		Assert.assertNotNull(m);
		Collection<Message> mens = actorService.findByPrincipal().getTrashbox().getMessages();
		mens.remove(m);
		actorService.findByPrincipal().getTrashbox().setMessages(mens);
		messageRepository.delete(m);
	}

	public Message findOne(int messageId) {

		return messageRepository.findOne(messageId);
	}

	public boolean isSpam(Message m) {
		Collection<KeyWords> kw = keyWordsService.findAll();
		ArrayList<KeyWords> keyWords = new ArrayList<KeyWords>(kw);
		KeyWords k = keyWords.get(0);
		boolean res = false;
		for (String w : k.getSpamWords()) {
			if (m.getBody().contains(w)) {
				res = true;
			}
		}
		return res;

	}
	
	public Double averageMessagesActor(int id){
		
		return messageRepository.averageNumberMessagesInMessageBoxes(id);
	}
}
