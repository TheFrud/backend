package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Aggregate extends Model{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	public String title;
	
	public String description;
	
	public long score;
	
	@ManyToOne
	public User user;
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<Resource> resources = new ArrayList<Resource>();
	
	public static Finder<Long,Aggregate> find = new Finder<Long,Aggregate>(
		    Long.class, Aggregate.class
	);
	
	public void increaseScore(long point) {
		this.score += point;
	}
	
	public void decreaseScore(long point) {
		this.score -= point;
	}
}
