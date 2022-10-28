package Foodify.Backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import Foodify.Backend.model.IntakeChartHistory;

@Repository
public interface IntakeHistoryRepository extends MongoRepository<IntakeChartHistory,String>{

    @Query("{'$and':[ {'date': ?0}, {'userName':?1} ] }")
	IntakeChartHistory findByDate(String date, String userName);
   

}