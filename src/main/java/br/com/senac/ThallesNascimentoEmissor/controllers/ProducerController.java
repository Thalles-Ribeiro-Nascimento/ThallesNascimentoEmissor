package br.com.senac.ThallesNascimentoEmissor.controllers;


import br.com.senac.ThallesNascimentoEmissor.entities.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissionamento")
public class ProducerController {
    @Autowired
    private RabbitTemplate rbTemplate;

    @PostMapping("/mensagem")
    public void enviarMensagem(@RequestBody Producer producer){
        rbTemplate.convertAndSend("banco","permissoes", producer);
        System.out.println("Mensagem enviada!");
    }
}
