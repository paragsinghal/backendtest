package com.craftsvilla.backendtest.foodtrucks.utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.craftsvilla.backendtest.foodtrucks.enums.PermitStatus;
import com.craftsvilla.backendtest.foodtrucks.manager.PermitManager;
import com.craftsvilla.backendtest.foodtrucks.permit.viewobjects.entity.Permit;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

/**
 * @author parag
 *
 *Utility to upload json data to Mongo. 
 */
@Controller
@RequestMapping("/dataupload")
public class DataDumper {
	
	@Autowired
	PermitManager permitManager;
	
	Logger logger = LogFactory.getLogger(DataDumper.class);
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Async
	public void uploadPermits(@RequestParam("fileName") String fileName) throws IOException, ParseException{
		
//		final Type REVIEW_TYPE = new TypeToken<List<JsonObject>>() {
//		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(fileName));
		JsonArray array = gson.fromJson(reader, JsonArray.class);
		for(JsonElement elementas : array){
			JsonArray jsonOArray = elementas.getAsJsonArray();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"); //Or whatever format fits best your needs.
			
			SimpleDateFormat sdfReceived = new SimpleDateFormat("yyyy-MM-dd"); //Or whatever format fits best your needs.
			
			Permit permit = new Permit();
			
			if(!jsonOArray.get(8).isJsonNull()){
				permit.setLocationId(jsonOArray.get(8).getAsLong());
			}
			if(!jsonOArray.get(9).isJsonNull()){
				permit.setApplicant(jsonOArray.get(9).getAsString());
			}
			if(!jsonOArray.get(10).isJsonNull()){
				permit.setFacilityType(jsonOArray.get(10).getAsString());
			}
			if(!jsonOArray.get(11).isJsonNull()){
				permit.setCnn(jsonOArray.get(11).getAsLong());
			}
			if(!jsonOArray.get(12).isJsonNull()){
				permit.setLocationDescription(jsonOArray.get(12).getAsString());
			}
			if(!jsonOArray.get(13).isJsonNull()){
				permit.setAddress(jsonOArray.get(13).getAsString());
			}
			if(!jsonOArray.get(14).isJsonNull()){
				permit.setBlockLot(jsonOArray.get(14).getAsString());
			}
			if(!jsonOArray.get(15).isJsonNull()){
				permit.setBlock(jsonOArray.get(15).getAsString());
			}
			if(!jsonOArray.get(16).isJsonNull()){
				permit.setLot(jsonOArray.get(16).getAsString());
			}
			if(!jsonOArray.get(17).isJsonNull()){
				permit.setPermit(jsonOArray.get(17).getAsString());
			}
			if(!jsonOArray.get(18).isJsonNull()){
				permit.setStatus(PermitStatus.valueOf(jsonOArray.get(18).getAsString()));
			}
			if(!jsonOArray.get(19).isJsonNull()){
				permit.setFoodItems(jsonOArray.get(19).getAsString());
			}
			if(!jsonOArray.get(20).isJsonNull()){
				permit.setX(jsonOArray.get(20).getAsDouble());
			}
			if(!jsonOArray.get(21).isJsonNull()){
				permit.setY(jsonOArray.get(21).getAsDouble());
			}
			if(!jsonOArray.get(22).isJsonNull()){
				permit.setLatitude(jsonOArray.get(22).getAsDouble());
			}
			if(!jsonOArray.get(23).isJsonNull()){
				permit.setLongitude(jsonOArray.get(23).getAsDouble());
			}
			if(!jsonOArray.get(24).isJsonNull()){
				permit.setSchedule(jsonOArray.get(24).getAsString());
			}
			if(!jsonOArray.get(25).isJsonNull()){
				permit.setDaysHours(jsonOArray.get(25).getAsString());
			}
			if(!jsonOArray.get(26).isJsonNull()){
				permit.setNOISent(sdf.parse(jsonOArray.get(26).getAsString()));
			}
			if(!jsonOArray.get(27).isJsonNull()){
				permit.setApproved(sdf.parse(jsonOArray.get(27).getAsString()));
			}
			if(!jsonOArray.get(28).isJsonNull()){
				permit.setReceived(sdfReceived.parse(jsonOArray.get(28).getAsString()));
			}
			if(!jsonOArray.get(29).isJsonNull()){
				permit.setPriorPermit(jsonOArray.get(29).getAsInt());
			}
			if(!jsonOArray.get(30).isJsonNull()){
				permit.setExpirationDate(sdf.parse(jsonOArray.get(30).getAsString()));
			}
			
			permit.setCreatedBy("123");
			permit.setCreationTime(System.currentTimeMillis());
			permit.setLastUpdatedBy("123");
			permit.setLastUpdatedTime(System.currentTimeMillis());
			try{
			permitManager.addPermit(permit);
			}catch(Throwable e){
				logger.error(e.getMessage());
			}
		}
		
		//List<String> data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
		///System.out.print(data.toString());
	}
}
