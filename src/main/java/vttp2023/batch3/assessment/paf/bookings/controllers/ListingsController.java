package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Landing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Controller
@RequestMapping
public class ListingsController {

	@Autowired
	ListingsRepository listingsRepository;

	//TODO: Task 2

	@GetMapping("/")
	public String displayIndex(Model m, @Valid Landing landing, BindingResult br){

		List<String> listOfCountries = listingsRepository.getAllCountries();
		m.addAttribute("listOfCountries", listOfCountries);
		m.addAttribute("Landing", landing);
		return "view1";
		

	}

	@GetMapping("/search")
	public String search(Model m, @Valid Landing landing, BindingResult br){

		m.addAttribute("Landing", landing);
		List<String> listOfCountries = listingsRepository.getAllCountries();

		if(br.hasErrors()){

			return "view1";
		}

		return "view1";

	}

	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
