package cotonart.pfe.textil.services;


import cotonart.pfe.textil.entities.Message;
import cotonart.pfe.textil.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    MessageRepository messageRepo;

    @Override
    public List<Message> getAllMessage(){
        return messageRepo.findAll();
    }

    @Override
    public Message save(Message message){
        return messageRepo.save(message);
    }

    @Override
    public Message findById(Integer id){
        return messageRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id){
        messageRepo.deleteById(id);
    }

    @Override
    public Message update(Integer id, Message message){
        Message msg = messageRepo.findById(id).get();
        if(msg != null){
            msg.setMessage(message.getMessage());
            msg.setDate(message.getDate());
        }
        return messageRepo.save(msg);
    }
}
