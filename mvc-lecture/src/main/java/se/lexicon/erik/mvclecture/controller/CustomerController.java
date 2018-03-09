package se.lexicon.erik.mvclecture.controller;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.lexicon.erik.mvclecture.db_access.CustomerRepository;
import se.lexicon.erik.mvclecture.domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Create method for showing the view
	@RequestMapping("/viewcustomer")
	public String viewCustomer(Model theModel) {
		
		Customer cust = new Customer("Erik", "Svensson", "erik@gmail.com", "gaming");
		
		theModel.addAttribute("customer", cust);
		
		return "customer-view";
	}
	
	@GetMapping("/customerform")
	public String showCustomerForm(Model theModel) {
		
		Customer customer = new Customer();
		
		theModel.addAttribute("customer", customer);
		return "customer-form";
		
	}
	
	@RequestMapping(value = "/processform", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("customer") Customer theCustomer
			,Model theModel) {
		
		Customer toSave = theCustomer;
		//Save the customer to database
		customerRepository.save(toSave);
		
		
		theModel.addAttribute("customer",theCustomer);
		
		
		return "form-confirmation";
		
		
	}
	
	

	
	

}
