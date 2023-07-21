package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Landing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
@RequestMapping
public class ListingsController {

	@Autowired
	ListingsRepository listingsRepository;
	@Autowired
	ListingsService listingsService;

	//TODO: Task 2
	@GetMapping("/")
	public String displayIndex(Model m, Landing landing){

		List<String> listOfCountries = listingsService.getAllCountries();
		m.addAttribute("listOfCountries", listOfCountries);
		m.addAttribute("Landing", landing);
		return "view1";
		

	}

	//TODO: Task 3
	@GetMapping("/search")
	public String search(Model m, @Valid Landing landing, BindingResult br){

		List<String> listOfCountries = listingsService.getAllCountries();
		m.addAttribute("listOfCountries", listOfCountries);
		m.addAttribute("Landing", landing);

		if(br.hasErrors()){

			return "view1";
		}

		// System.out.println("in controller: "+ landing.getCountry());

		List<Document> searchResult = listingsService.getSearch(landing);
		m.addAttribute("searchResult", searchResult);
		List<String> listOfName = new ArrayList<>();
		List<String> listOfPrice = new ArrayList<>();
		List<String> listofImage = new ArrayList<>();
		
		for(Document result : searchResult){

			listOfName.add(result.getString("name"));
			listOfPrice.add(result.getDouble("price").toString());
			listofImage.add(result.getString("images.picture_url"));
		}

		m.addAttribute("listofName", listOfName);
		m.addAttribute("listofPrice", listOfPrice);
		m.addAttribute("listofImages", listofImage);

		// System.out.println("result in controller: " + listingsService.getSearch(landing));
		return "view2";

	}

	//TODO: Task 4

	public String getDetailOfEach(@RequestParam String id){
		
		listingsService.getDetail(id);
		return "view3";

	}
	

	//TODO: Task 5


}
