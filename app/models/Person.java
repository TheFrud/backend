package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;


@Entity
public class Person extends Model {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	public String name;

	public Long age;

	public static Finder<Long,Person> find = new Finder<Long,Person>(
		    Long.class, Person.class
	);
}