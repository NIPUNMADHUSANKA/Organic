package Foodify.Backend.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Foodify.Backend.model.IntakeChart;
import Foodify.Backend.model.IntakeChartHistory;
import Foodify.Backend.model.IntakePending;
import Foodify.Backend.repository.IntakeChartRepository;
import Foodify.Backend.repository.IntakeHistoryRepository;
import Foodify.Backend.repository.IntakeItemPending;
import Foodify.Backend.repository.IntakePendingRepository;

import Foodify.Backend.model.IntakePendingItem;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IntakeChartController {
    
    @Autowired
    private IntakeChartRepository intakeChartRepository;

    @Autowired
    private IntakeHistoryRepository intakeHistoryRepository;

    @Autowired
    private IntakePendingRepository intakePendingRepository;

    @Autowired
    private IntakeItemPending intakePendingItemRepository;


    @PostMapping("/FoodiFy/Premium/addIntakeChart")
	public void addIntakeChart(@RequestBody IntakeChart intakeChart) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        String localDate = LocalDate.now().toString();

        //LocalDate date = LocalDate.parse(localDate);

        intakeChart.setUserName(userName);
        intakeChart.setLastUpdate(localDate);

        intakeChartRepository.save(intakeChart);


    }

    @GetMapping("/FoodiFy/Premium/getIntakeChart")   
    public ResponseEntity<?> getIntakeChart() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        try {

            IntakeChart intakeChart = intakeChartRepository.findByuserName(userName);

            LocalDate date = LocalDate.parse(intakeChart.getLastUpdate());

            if(LocalDate.now().equals(date)){
                return new ResponseEntity<>(intakeChart, HttpStatus.OK);
            }
            else{

                IntakeChartHistory intakeChartHistory = new IntakeChartHistory();

                intakeChartHistory.setUserName(userName);
                intakeChartHistory.setDate(intakeChart.getLastUpdate());
                intakeChartHistory.setCalaries(intakeChart.getCalaries());
                intakeChartHistory.setFat(intakeChart.getFat());
                intakeChartHistory.setProtein(intakeChart.getProtein());
                intakeChartHistory.setCarbo(intakeChart.getCarbo());

                intakeHistoryRepository.save(intakeChartHistory);
                
                intakeChart.setLastUpdate(LocalDate.now().toString());
                intakeChart.setCalaries(0.0);
                intakeChart.setFat(0.0);
                intakeChart.setProtein(0.0);
                intakeChart.setCarbo(0.0);
                intakeChartRepository.save(intakeChart);
                return new ResponseEntity<>(intakeChart, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/FoodiFy/Premium/getPending")
    public ResponseEntity<?> getIntakePending() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        List<IntakePending> pending = intakePendingRepository.findAllByuserName(userName);

        if(!pending.isEmpty()){
            return new ResponseEntity<>(pending, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/FoodiFy/Premium/getPendingItems/{id}")
	public ResponseEntity<?> getPendingItems(@PathVariable(value="id") String pendingid) {
		
        try {

            return new ResponseEntity<>(intakePendingItemRepository.findBypending(pendingid), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

	}


    @GetMapping("/FoodiFy/Premium/removePendingItems/{id}")
	public ResponseEntity<?> removePendingItems(@PathVariable(value="id") String removependingid) {
		
        Optional<IntakePendingItem> intakependingitem = intakePendingItemRepository.findById(removependingid);

        List<IntakePendingItem> intakepending = intakePendingItemRepository.findBypending(intakependingitem.get().getPending());

        if(intakepending.size()>1){
            
            Optional<IntakePending> pending = intakePendingRepository.findById(intakependingitem.get().getPending());

             
            //IntakePending pendingnew = new IntakePending();
                
            pending.get().setCalaries(pending.get().getCalaries() - intakependingitem.get().getCalaries());
            pending.get().setCarbo(pending.get().getCarbo() - intakependingitem.get().getCarbo());
            pending.get().setFat(pending.get().getFat() - intakependingitem.get().getFat());
            pending.get().setProtein(pending.get().getProtein() - intakependingitem.get().getProtein());
            pending.get().setPrice(pending.get().getPrice() - intakependingitem.get().getPrice());
            
            intakePendingRepository.save(pending.get());
            intakePendingItemRepository.deleteById(removependingid);  
            
            return new ResponseEntity<>(pending, HttpStatus.OK);

           //return null;
            
        }
        else{
            intakePendingRepository.deleteById(intakependingitem.get().getPending()); 
            intakePendingItemRepository.deleteById(removependingid);  
            return new ResponseEntity<>("Work", HttpStatus.OK);         
        }

        //System.out.println(intakepending.size());       
        
	}


    @GetMapping("/FoodiFy/Premium/addPendingItems/{id}")
	public ResponseEntity<?> acceptPendingItems(@PathVariable(value="id") String acceptpendingid) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        IntakeChart intakeChart = intakeChartRepository.findByuserName(userName);

        Optional<IntakePendingItem> intakependingitem = intakePendingItemRepository.findById(acceptpendingid);
        
        intakeChart.setCalaries(intakeChart.getCalaries() + intakependingitem.get().getCalaries());
        intakeChart.setFat(intakeChart.getFat() + intakependingitem.get().getFat());
        intakeChart.setProtein(intakeChart.getProtein() + intakependingitem.get().getProtein());
        intakeChart.setCarbo(intakeChart.getCarbo() + intakependingitem.get().getCarbo());
        
        intakeChartRepository.save(intakeChart);
		

        List<IntakePendingItem> intakepending = intakePendingItemRepository.findBypending(intakependingitem.get().getPending());

        if(intakepending.size()>1){
            
            Optional<IntakePending> pending = intakePendingRepository.findById(intakependingitem.get().getPending());

             
            //IntakePending pendingnew = new IntakePending();
                
            pending.get().setCalaries(pending.get().getCalaries() - intakependingitem.get().getCalaries());
            pending.get().setCarbo(pending.get().getCarbo() - intakependingitem.get().getCarbo());
            pending.get().setFat(pending.get().getFat() - intakependingitem.get().getFat());
            pending.get().setProtein(pending.get().getProtein() - intakependingitem.get().getProtein());
            pending.get().setPrice(pending.get().getPrice() - intakependingitem.get().getPrice());
            
            intakePendingRepository.save(pending.get());
            intakePendingItemRepository.deleteById(acceptpendingid);  
            
            return new ResponseEntity<>(pending, HttpStatus.OK);

           //return null;
            
        }
        else{
            intakePendingRepository.deleteById(intakependingitem.get().getPending()); 
            intakePendingItemRepository.deleteById(acceptpendingid);  
            return new ResponseEntity<>("Work", HttpStatus.OK);         
        }
 
        
	}

    @PostMapping("/FoodiFy/Premium/editPendingItems")
	public ResponseEntity<?> editPendingItems(@Valid @RequestBody IntakePendingItem foodItem) {

        String ID = foodItem.getId();
        Double Quantity = foodItem.getQuantity();

        Optional<IntakePendingItem> intakependingitem = intakePendingItemRepository.findById(ID);

        Double calaries = (double)Math.round((intakependingitem.get().getCalaries() * Quantity) / intakependingitem.get().getQuantity());
        Double fat = (double)Math.round((intakependingitem.get().getFat() * Quantity) / intakependingitem.get().getQuantity());
        Double protein = (double)Math.round((intakependingitem.get().getProtein() * Quantity) / intakependingitem.get().getQuantity());
        Double carbo = (double)Math.round((intakependingitem.get().getCarbo() * Quantity) / intakependingitem.get().getQuantity());

        intakependingitem.get().setQuantity(Quantity);
        intakependingitem.get().setCalaries(calaries);
        intakependingitem.get().setFat(fat);
        intakependingitem.get().setCarbo(carbo);
        intakependingitem.get().setProtein(protein);
        intakePendingItemRepository.save(intakependingitem.get());

        String List_ID = intakependingitem.get().getPending();
        
        Optional<IntakePending> intakepending = intakePendingRepository.findById(List_ID);

        intakepending.get().setCalaries(intakepending.get().getCalaries() - calaries);
        intakepending.get().setFat(intakepending.get().getFat() - fat);
        intakepending.get().setProtein(intakepending.get().getProtein() - protein);
        intakepending.get().setCarbo(intakepending.get().getCarbo() - carbo);
        intakePendingRepository.save(intakepending.get());
        return null;        
	}


    @GetMapping("/FoodiFy/Premium/searchbydateintake/{date}")
	public ResponseEntity<?> intakeChartDate(@PathVariable(value="date") Date filterdate) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
       
        Integer Year = (filterdate.getYear()+1900);
        Integer Month = filterdate.getMonth()+1;
        Integer DateNo = filterdate.getDate();
        String date = Year.toString() + "-" +Month.toString()  + "-" + DateNo.toString();

        try {
            if(intakeHistoryRepository.findByDate(date,userName) != null){
                return new ResponseEntity<>(intakeHistoryRepository.findByDate(date,userName), HttpStatus.OK);   
            }else{
                return new ResponseEntity<>(intakeChartRepository.findByDate(date,userName), HttpStatus.OK);
            }
             
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);   
        }

    }


}