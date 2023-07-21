package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Landing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {

	@Autowired
	ListingsRepository listingsRepository;
	
	//TODO: Task 2

	public List<String> getAllCountries(){

		return listingsRepository.getAllCountries();

	}

	
	//TODO: Task 3

	public List<Document> getSearch(Landing landing){

		return listingsRepository.getSearch(landing);
	}



	//TODO: Task 4

	public List<Document> getDetail(String id){

		return listingsRepository.getDetail(id);
	}
	

	//TODO: Task 5


}
