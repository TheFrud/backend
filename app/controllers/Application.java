package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Person;
import play.*;
import play.api.libs.json.Json;
import play.api.mvc.Session;
import play.mvc.*;
import play.mvc.Http.RequestBody;
import play.mvc.Http.Response;
import views.html.*;
import static play.libs.Json.*;
import play.mvc.Http.Context;

public class Application extends Controller {

	public static Result preflight(String all) {
		response().setHeader("Access-Control-Allow-Origin", "*");
		response().setHeader("Allow", "*");
		response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
		return ok();
	}
	
	public static Result connectUser() {
		session("connected", "frud");
		return ok("Connected - Welcome!");
	}
	
	public static Result disconnectUser() {
		session().remove("connected");
		return ok("Disconnected.");
		
	}
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getPersons() {
    	List<Person> persons = Person.find.all();
    	return ok(toJson(persons));
    }
    
    public static Result savePerson() {
    	
    	JsonNode json = request().body().asJson();
    	if(json.isNull()) {
    		System.out.println("Json är null din idiot!");
    	}
    	String name = json.findPath("name").textValue();
    	long age = json.findPath("age").asLong();
    	Person person = new Person();
    	person.name = name;
    	person.age = age;
    	person.save();
    	System.out.println("Person saved!");
    	return ok("Person " + person.name + " was saved!");

    }
    
    public static Result removePerson() {
    	JsonNode json = request().body().asJson();
    	if(json.isNull()) {
    		System.out.println("Json är null din idiot!");
    	}
    	long id = json.findPath("id").asLong();
    	Person person = Person.find.byId(id);
    	person.delete();
    	System.out.println("Person removed");
    	return ok("Person " + person.name + " was removed!");
    }
    

}
