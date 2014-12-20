package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class User extends Model{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	public String name;
	
	public String password;
	
	@OneToMany
	public List<Aggregate> aggregates;
}
