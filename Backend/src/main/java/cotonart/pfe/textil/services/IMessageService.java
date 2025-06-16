package cotonart.pfe.textil.services;

import cotonart.pfe.textil.entities.Message;

import java.util.List;

public interface IMessageService {
    public List<Message> getAllMessage();
    public Message save(Message message);
    public Message findById(Integer id);
    public void delete(Integer id);
    public Message update(Integer id , Message message);
}
