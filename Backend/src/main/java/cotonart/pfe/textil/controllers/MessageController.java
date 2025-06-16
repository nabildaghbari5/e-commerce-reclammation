package cotonart.pfe.textil.controllers;


import cotonart.pfe.textil.entities.Message;
import cotonart.pfe.textil.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/message")

public class MessageController {

    @Autowired
    IMessageService iMessageService;

    @GetMapping("/retrieve")
    public List<Message> getAllMessage(){
        return iMessageService.getAllMessage();
    }

    @PostMapping("/save")
    public Message save(@RequestBody Message message){
        return iMessageService.save(message);
    }

    @GetMapping("/findById/{id}")
    public Message findById(@PathVariable Integer id){
        return iMessageService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        iMessageService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Message update(@PathVariable Integer id, @RequestBody Message message){
        Message msg = iMessageService.update(id , message);
        return msg;
    }
}
