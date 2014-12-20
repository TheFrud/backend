package controllers;

import java.util.List;

import models.Aggregate;
import models.Person;
import models.Resource;
import models.User;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.*;

public class Multilink extends Controller {
	
	public static Result getAggregate() {
		JsonNode json = request().body().asJson();
		Long id = json.findPath("aggregateId").asLong();
		Aggregate aggregate = Aggregate.find.byId(id);
		return ok(toJson(aggregate));
	}
	
	public static Result getAggregates() {
		List<Aggregate> aggregates = Aggregate.find.all();
		return ok(toJson(aggregates));
	}
	
	public static Result likeAggregate() {
		JsonNode json = request().body().asJson();
		Long id = json.findPath("aggregateId").asLong();
		Aggregate aggregate = Aggregate.find.byId(id);
		aggregate.increaseScore(1);
		aggregate.save();
		return ok("Aggregat gillat.");
	}
	
	public static Result dislikeAggregate() {
		JsonNode json = request().body().asJson();
		Long id = json.findPath("aggregateId").asLong();
		Aggregate aggregate = Aggregate.find.byId(id);
		aggregate.decreaseScore(1);
		aggregate.save();
		return ok("Aggregat ogillat.");
	}	
	
	public static Result createAggregate() {
		JsonNode json = request().body().asJson();
		System.out.println(json);
		Aggregate aggregate = new Aggregate();
		aggregate.title = json.findPath("title").asText();
		aggregate.description = json.findPath("description").asText();
		for(int i = 0; i<json.findPath("resources").size(); i++){
			System.out.println("Item");
			String title = json.findPath("resources").findPath("title").asText();
			String description = json.findPath("resources").findPath("description").asText();
			String url = json.findPath("resources").findPath("url").asText();
			Resource resource = new Resource();
			resource.title = title;
			resource.description = description;
			resource.link = url;
			
			aggregate.resources.add(resource);
		}
		for(int i = 0; i<aggregate.resources.size(); i++){
			System.out.println(aggregate.resources.get(i));
		}
		aggregate.save();

		System.out.println("BACKEND: CreateAggregate() has executed.");
		return ok("FRONTEND: CreateAggregate() has executed.");
	}

}
