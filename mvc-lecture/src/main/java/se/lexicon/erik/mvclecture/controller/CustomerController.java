package se.lexicon.erik.mvclecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.lexicon.erik.mvclecture.domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//Create method for showing the view
	@RequestMapping("/viewcustomer")
	public String viewCustomer(Model theModel) {
		
		Customer cust = new Customer("Erik", "Svensson", "erik@gmail.com", "gaming");
		
		theModel.addAttribute("customer", cust);
		
		return "customer-view";
	}

	
	

}
