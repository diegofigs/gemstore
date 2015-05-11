package models;

public class Review {
	int stars;
	String body;
	String author;
	double createdOn;
	
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(double createdOn) {
		this.createdOn = createdOn;
	}
}
