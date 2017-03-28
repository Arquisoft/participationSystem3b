package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.producers.KafkaProducer;

public abstract class SuperService {
	
	@Autowired
	protected KafkaProducer kafkaProducer;
}
