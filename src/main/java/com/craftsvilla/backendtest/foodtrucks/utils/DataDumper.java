package com.craftsvilla.backendtest.foodtrucks.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
	
	Client client = Client.create();
	
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
	
	@RequestMapping(value= "/es", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Async
	public void uploadPermitsES(@RequestParam("fileName") String fileName) throws IOException, ParseException, JSONException{
		
		BufferedReader br = null;
		FileReader fr = null;

		Long id =0l;
		
		try {

			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			List<String> currentLines = new ArrayList<String>();
            int i=1;
			while ((sCurrentLine = br.readLine()) != null) {
				
				if(id % 18334 == 0 && id !=0){
					updateES(currentLines, id);
					currentLines = new ArrayList<String>();
					i++;
				}
				currentLines.add(sCurrentLine);
				id++;
			}
			
			System.out.println(id);
			updateES(currentLines, id - (id%18334));
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	@Async
	public void updateES(List<String> currentLines, long id) throws JSONException{
		String tt = "craftsvilla";
		
//		if(id> 300000){
//			tt = "craftsvilla2";
//		}
//		if(id> 100000 && id < 200000){
//			tt = "craftsvilla2";
//		}
//		if(id> 200000 && id < 300000){
//			tt = "craftsvilla3";
//		}
//		if(id> 300000 && id < 400000){
//			tt = "craftsvilla4";
//		}
//		if(id> 400000 && id < 500000){
//			tt = "craftsvilla5";
//		}
//		if(id> 500000){
//			tt = "craftsvilla6";
//		}
		
		
		for(long k =id-18334l ; k < id; k +=200) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Long i = k; i< k + 200; i++){
		try{
		String [] strs = currentLines.get(i.intValue() % 18334).split(",");
		
		Integer weight = Integer.parseInt(strs[strs.length-1]);
		
		String [] inputs = strs[0].split(" ");
		
		Set<String> contextCharacters = new HashSet<String>();
		List<String> finalInputs = new ArrayList<String>();
		
		if(inputs.length >1){
			contextCharacters.add(new String(strs[0].charAt(0)+ "").toLowerCase());
		finalInputs.add(strs[0]);
		}
		
		for(String input :inputs){
			if(input!=null && input !=" " && !input.isEmpty()){
				contextCharacters.add(new String(input.charAt(0)+ "").toLowerCase());
			finalInputs.add(input);
			}
		}
		
		for(int j=0; j< inputs.length-1; j++){
			
			if((inputs[j]!=null && !inputs[j].isEmpty()) && (inputs[j+1]!=null && !inputs[j+1].isEmpty())){
			String a = inputs[j] + " " +inputs[j+1];
			String b = inputs[j+1] + " " + inputs[j];
			
			contextCharacters.add(new String(a.charAt(0)+ "").toLowerCase());
			contextCharacters.add(new String(b.charAt(0)+ "").toLowerCase());
			finalInputs.add(a);
			finalInputs.add(b);
			}
		}
		
		
		JSONObject object = new JSONObject();
		object.put("name", strs[0]);
		JSONObject object2 = new JSONObject();
		JSONObject object3 = new JSONObject();
		//object3.put("seperator", new JSONArray(contextCharacters));
		object2.put("input", new JSONArray(finalInputs));
		object2.put("context", object3);
		object2.put("weight", weight);
		object2.put("output", strs[0]);
		
		object.put("nameSuggest", object2);
		
		String index = "{ \"index\" : {\"_id\" : \"" + i +"\" } }\n";
		
		
		stringBuilder.append(index);
		stringBuilder.append(object.toString() + "\n");
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			break;
			}
		}
		String elasticSearchUrl = "http://35.154.43.96:8765/" +tt +"/searchterms/_bulk";
		WebResource webResource = client.resource(elasticSearchUrl);
		
		ClientResponse clientResponse = webResource.type("application/json").post(
				ClientResponse.class, stringBuilder.toString());
		}
	}
}
