package Foodify.Backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import Foodify.Backend.model.IntakeChart;

@Repository
public interface IntakeChartRepository extends MongoRepository<IntakeChart,String>{

    IntakeChart findByuserName(String userName);

    @Query("{'$and':[ {'lastUpdate': ?0}, {'userName':?1} ] }")
	IntakeChart findByDate(String lastUpdate, String userName);
   
}