package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	//TODO: Task 2
	public List<String> getAllCountries(){

		// db.listings.distinct("address.country")

		Criteria criteria = Criteria.where("address.country").exists(true);
		Query query = Query.query(criteria);
		List<String> listOfCountries = mongoTemplate.findDistinct(query, "address.country", "listings",String.class);
		System.out.println("countries " + listOfCountries);
		return listOfCountries; 
		
		
	}

	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
