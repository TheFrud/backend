package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Resource extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	public String title;
	
	public String link;
	
	public String description;
	
	public static Finder<Long,Resource> find = new Finder<Long,Resource>(
		    Long.class, Resource.class
	);
}
