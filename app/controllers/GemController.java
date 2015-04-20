package controllers;

import models.Gem;
import models.GemList;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GemController extends Controller {

	@SuppressWarnings("deprecation")
	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeGem(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.err.println("POST Data");
			JsonNode json = request().body().asJson();
			System.err.println("json payload: " + json);
			Gem newGem = mapper.readValue(json.toString(), Gem.class);
			GemList theList = GemList.getInstance();
			newGem = theList.addGem(newGem);
			System.err.println(Json.toJson(newGem));
			ObjectNode result = Json.newObject();
			result.put("Gem", Json.toJson(newGem));
			return created(result);
		}
		catch(Exception e){
			e.printStackTrace();
			return badRequest("Missing information");
		}
	}
	
	public static Result getGems(){
		GemList theList = GemList.getInstance();
		Gem[] G = theList.getAllGems();
		if (G == null){
			return notFound(); // 404
		}
		else {
			return ok(Json.toJson(G));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static Result getGem(Long id){
		// DEBUG 
		System.err.println("GET on id: "+ id);

		ObjectNode result = Json.newObject();
		GemList theList = GemList.getInstance();
		Gem G = theList.getGemById(id);
		if (G == null){
			return notFound(); // 404
		}
		else {
			result.put("Gem", Json.toJson(G));
			return ok(result);
		}
	}
	
	@SuppressWarnings("deprecation")
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateGem(Long id){
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = request().body().asJson();
			Gem updGem = mapper.readValue(json.toString(), Gem.class);
			GemList theList = GemList.getInstance(); 
			updGem = theList.updateGem(updGem);
			if (updGem == null){
				return notFound(); // 404 
			}
			else {
				ObjectNode result = Json.newObject();
				result.put("Gem", Json.toJson(updGem));
				return ok(result);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return badRequest("Missing information");			
		}
	}
	
	public static Result deleteGem(Long id){
		GemList theList = GemList.getInstance();
		boolean erased = theList.deleteGem(id);
		if (erased){
			// This is code 204 - OK with no content to return
			return noContent();
		}
		else {
			return notFound("Person not found");
		}

	}
}
