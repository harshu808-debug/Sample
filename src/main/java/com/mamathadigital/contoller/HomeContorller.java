package com.mamathadigital.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContorller 
{
	@GetMapping(value="/")
     public String homePage()
     {
    	 return "Hii Welcome to homepage";
     }
	
	
}
