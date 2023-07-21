package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.models.Landing;

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
		// System.out.println("countries " + listOfCountries);
		return listOfCountries; 
	}

	//TODO: Task 3
	public List<Document> getSearch(Landing landing){

			// db.listings.aggregate([
			// {$match : {"address.country" : "Australia"}},
			// {$match : {"accommodates" : 2}},
			// {$match : {"price" : {$lte: 200, $gte: 50}}},
			// {$project : {"name" : 1 , "price" : 1, "images.picture_url":1, "_id":1 }},
			// {$sort : {"price" : -1}}
			// ])

		MatchOperation matchCountry = Aggregation.match(Criteria.where("address.country").is(landing.getCountry()));
		
		MatchOperation matchNumOfPerson = Aggregation.match(Criteria.where("accommodates").is(landing.getNumOfPerson()));

		MatchOperation matchPrice = Aggregation.match(Criteria.where("price").gte(landing.getMin()).lte(landing.getMax()));

		ProjectionOperation projectValue = Aggregation.project("name", "price", "images.picture_url", "_id");

		SortOperation sortByPrice = Aggregation.sort(Direction.DESC, "price");

		Aggregation pipeline = Aggregation.newAggregation(matchCountry, matchNumOfPerson, matchPrice, projectValue, sortByPrice);

		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "listings", Document.class);

		// System.out.println("Aggre Check: " + results);
		// System.out.println("Results in repo" + results.getMappedResults());
		return results.getMappedResults();

	}


	//TODO: Task 4

	public List<Document> getDetail(String id){

		// db.listings.aggregate([
		// {$match : {"_id" : "394428"}},
		// {$project : {"_id": 1, "description" : 1, "address.street" : 1, "address.suburb" : 1, "address.country" : 1, "images.picture_url" : 1, "price" : 1, "amenities":1}}
		// ])

		MatchOperation matchId = Aggregation.match(Criteria.where("_id").is(id));
		ProjectionOperation projectValue = Aggregation.project("_id", "description", "address.street", "address.suburb", "address.country", "images.picture_url", "price", "amenities");
		Aggregation pipeline = Aggregation.newAggregation(matchId, projectValue);
		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline,"listings", Document.class);
	
		return results.getMappedResults();
	}
	

	//TODO: Task 5


}
