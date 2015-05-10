package models;

public class Gem implements Comparable<Gem>{
	private long id;
	private String name;
	private String[] images;
	private String description;
	private int shine;
	private double price;
	private int rarity;
	private String color;
	private int faces;
	private Review[] reviews;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getShine() {
		return shine;
	}

	public void setShine(int shine) {
		this.shine = shine;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getFaces() {
		return faces;
	}

	public void setFaces(int faces) {
		this.faces = faces;
	}
	
	public Review[] getReviews() {
		return reviews;
	}

	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}

	public String toString(){
		return "(" + this.id + ", " + this.name + ", " + this.description
				+ ", " + this.shine + ", " + this.price + ", " + this.rarity
				+ ", " + this.color + ", " + this.faces + ")";
	}

	@Override
	public int compareTo(Gem o) {
		int result = this.getName().compareTo(o.getName());
		return result;
	}
	
}
