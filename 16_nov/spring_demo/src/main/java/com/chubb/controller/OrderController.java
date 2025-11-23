package com.chubb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chubb.request.Order1;
import com.chubb.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController { 
	@Autowired
	OrderService service;
	
	@GetMapping("/order") //path
	String getOrder() {
		return "hello";
	}
	
	@PostMapping("/order") //path
	Order1 saveOrder(@RequestBody  @Valid Order1 order) {
		log.info("Received order: " + order);
		service.inserOrder(order);
		return order;
	}
	
}
