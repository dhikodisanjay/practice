package com.controller;


import com.com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {


    @Autowired
    @Qualifier(value = "kafkaTemplate1")
    KafkaTemplate<String,Users> kafkaTemplate2;


  

    @PostMapping("/postmessage")
    public void  post(@RequestBody Users users) {

        if (kafkaTemplate2 != null) {
            System.out.println("*************************************kafkaTemplate is  not Null***************************");
        }
        
        while (1==1) {
            kafkaTemplate2.send("mytopic", users);
System.out.println("data produced")

		}
        

        //return "published successfully";
    }


    
	/*
	 * @KafkaListener(topics = "mytopic") public void getFromKafka(Users users) {
	 * 
	 * 
	 * System.out.println("\n"+users);
	 * 
	 * 
	 * 
	 * }
	 */
    



}
