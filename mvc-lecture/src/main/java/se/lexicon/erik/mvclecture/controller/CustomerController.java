package se.lexicon.erik.mvclecture.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lexicon.erik.mvclecture.db_access.CustomerRepository;
import se.lexicon.erik.mvclecture.domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Create method for showing the view
	@RequestMapping(value="/details", method=RequestMethod.GET)
	public String viewCustomer(@RequestParam("id")Integer id, Model theModel) {
		Customer theCustomer= null; 
		
		Optional<Customer> result = customerRepository.findById(id);
		
		if(result.isPresent()) {
			theCustomer = result.get();
		}
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-details";
	}
	
	@GetMapping("/edit")
	public String editCustomer(@RequestParam("id") int id, Model theModel) {
		Customer theCustomer = null;
		
		Optional<Customer> result = customerRepository.findById(id);
		
		if(result.isPresent()) {
			theCustomer = result.get();
		}
		
		theModel.addAttribute("customer", theCustomer);
		
		return "edit-customer-form";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("id") Integer id, Model theModel) {
		customerRepository.deleteById(id);
		
		return "redirect:/customer/showall";
	}
	
	@GetMapping("/showall")
	public String getCustomerList(Model theModel) {
		List <Customer> customers = new ArrayList<>();
		
		customers = (List<Customer>) customerRepository.findAll();
		theModel.addAttribute("customers", customers);
		
		return "show-customers";
		
	}
	
	@GetMapping("/customerform")
	public String showCustomerForm(Model theModel) {
		
		Customer customer = new Customer();
		
		theModel.addAttribute("customer", customer);
		return "customer-form";
		
	}
	
	@RequestMapping(value = "/processform", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("customer") Customer theCustomer ,Model theModel) {
		
		
		//Save the customer to database
		customerRepository.save(theCustomer);
	
		return "redirect:/customer/showall";
		
		
	}
	
	@PostMapping("/updatecustomer")
	public String processUpdate(@RequestParam("id") int id, @ModelAttribute("customer") Customer updated, Model theModel) {
		Customer toUpdate = null;

		
		Optional<Customer> result = customerRepository.findById(id);
		if(result.isPresent()) {
			toUpdate = result.get();
		}else {
			throw new NullPointerException("Could not find the requested customer");
		}
		
		if(!toUpdate.getFirstName().equals(updated.getFirstName()))
			toUpdate.setFirstName(updated.getFirstName());
		if(!toUpdate.getLastName().equals(updated.getLastName()))
			toUpdate.setLastName(updated.getLastName());
		if(!toUpdate.getEmail().equals(updated.getEmail()))
			toUpdate.setEmail(updated.getEmail());
		if(!toUpdate.getHobby().equals(updated.getHobby()))
			toUpdate.setHobby(updated.getHobby());
		
		customerRepository.save(toUpdate);
		
		return "redirect:/customer/showall";
			
	}
	
	

	
	

}
