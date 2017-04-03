package es.uniovi.asw.webService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import es.uniovi.asw.producers.KafkaProducer;

@Controller
@Scope("session")
public class AdminController {
	
	@Autowired
	 private KafkaProducer kafkaProducer;

}
