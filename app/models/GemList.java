package models;

import java.util.Random;

public class GemList {
	
	private BSTImp<String, Gem> gemList;
	int counter = 1;
	String[] images = {"assets/images/gem-02.gif","assets/images/gem-05.gif","assets/images/gem-09.gif"};
	String[] images2 = {"assets/images/gem-01.gif","assets/images/gem-03.gif","assets/images/gem-04.gif"};
	String[] images3 = {"assets/images/gem-06.gif","assets/images/gem-07.gif","assets/images/gem-08.gif"};
	
	public GemList(){
		this.gemList = new BSTImp<String, Gem>(new StringComparator());
		Gem moonstone = new Gem();
		moonstone.setId(counter++);
		moonstone.setName("Moonstone");
		moonstone.setDescription("Moonstone is used in a lot of jewelry for achieving a transparent classy glow.");
		moonstone.setShine(6);
		moonstone.setPrice(220.55);
		moonstone.setRarity(6);
		moonstone.setColor("transparent");
		moonstone.setFaces(2);
		moonstone.setImages(images3);
		moonstone.setReviews(new Review[0]);
		gemList.add(moonstone.getName(), moonstone);
		// First gem
		Gem azurite = new Gem();
		azurite.setId(counter++);
		azurite.setName("Azurite");
		azurite.setDescription("Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.");
		azurite.setShine(8);
		azurite.setPrice(110.55);
		azurite.setRarity(7);
		azurite.setColor("#CCC");
		azurite.setFaces(14);
		azurite.setImages(images);
		Review rev1 = new Review();
		rev1.setStars(5);
		rev1.setBody("I love this gem!");
		rev1.setAuthor("joe@example.org");
		rev1.setCreatedOn(1431328350752.0);
		Review rev2 = new Review();
		rev2.setStars(1);
		rev2.setBody("This gem sucks.");
		rev2.setAuthor("tim@example.org");
		rev2.setCreatedOn(1431328350752.0);
		Review[] revs = { rev1, rev2 };
		azurite.setReviews(revs);
		gemList.add(azurite.getName(), azurite);
		// Second gem
		Gem bloodstone = new Gem();
		bloodstone.setId(counter++);
		bloodstone.setName("Bloodstone");
		bloodstone.setDescription("Origin of the Bloodstone is unknown, hence its low value. It has a very high shine and 12 sides, however.");
		bloodstone.setShine(9);
		bloodstone.setPrice(22.95);
		bloodstone.setRarity(6);
		bloodstone.setColor("#EEE");
		bloodstone.setFaces(12);
		bloodstone.setImages(images2);
		Review rev3 = new Review();
		rev3.setStars(3);
		rev3.setBody("I think this gem was just OK, could honestly use more shine, IMO.");
		rev3.setAuthor("JimmyDean@example.org");
		rev3.setCreatedOn(1431328350752.0);
		Review rev4 = new Review();
		rev4.setStars(4);
		rev4.setBody("Any gem with 12 faces is for me! ");
		rev4.setAuthor("gemsRock@example.org");
		rev4.setCreatedOn(1431328350752.0);
		Review[] revs2 = { rev3, rev4 };
		bloodstone.setReviews(revs2);
		gemList.add(bloodstone.getName(), bloodstone);
		// Third gem
		Gem zircon = new Gem();
		zircon.setId(counter++);
		zircon.setName("Zircon");
		zircon.setDescription("Zircon is our most coveted and sought after gem. You will pay much to be the proud owner of this gorgeous and high shine gem.");
		zircon.setShine(70);
		zircon.setPrice(1101);
		zircon.setRarity(2);
		zircon.setColor("#000");
		zircon.setFaces(6);
		zircon.setImages(images3);
		Review rev5 = new Review();
		rev5.setStars(1);
		rev5.setBody("This gem is WAY too expensive for its rarity value.");
		rev5.setAuthor("turtleguyy@example.org");
		rev5.setCreatedOn(1431328350752.0);
		Review rev6 = new Review();
		rev6.setStars(1);
		rev6.setBody("BBW: High Shine != High Quality.");
		rev6.setAuthor("LouisW407@example.org");
		rev6.setCreatedOn(1431328350752.0);
		Review rev7 = new Review();
		rev7.setStars(1);
		rev7.setBody("Don't waste your rubles!");
		rev7.setAuthor("nat@example.org");
		rev7.setCreatedOn(1431328350752.0);
		Review[] revs3 = { rev5, rev6, rev7 };
		zircon.setReviews(revs3);
		gemList.add(zircon.getName(), zircon);
	}
	
	public Gem addGem(Gem obj){
		long id = this.counter++;
		obj.setId(id);
		obj.setReviews(new Review[0]);
		Random rand = new Random();
		int random = rand.nextInt(2);
		if(random == 0)
			obj.setImages(images);
		else if(random == 1)
			obj.setImages(images2);
		else
			obj.setImages(images3);
		this.gemList.add(obj.getName(), obj);
		return obj;
	}
	
	public Gem getGemById(long id){
		for(Gem g: this.gemList){
			if(g.getId() == id)
				return g;
		}
		return null;
	}
	
	public Gem[] getAllGems(){
		int i = 0;
		Gem result[] = new Gem[this.gemList.size()];
		for(Gem g : this.gemList){
			result[i++] = g;
		}
		i = 0;
		return result;
	}
	
	public boolean deleteGem(long id){
		for(Gem g : this.gemList){
			if(id == g.getId()){
				this.gemList.remove(g.getName());
				return true;
			}
		}
		return false;
	}
	
	public Gem updateGem(Gem obj){
		for(Gem e : this.gemList){
			if(obj.getId() == e.getId()){
				Gem g = new Gem();
				g.setId(obj.getId());
				g.setName(obj.getName());
				g.setDescription(obj.getDescription());
				g.setShine(obj.getShine());
				g.setPrice(obj.getPrice());
				g.setRarity(obj.getRarity());
				g.setColor(obj.getColor());
				g.setFaces(obj.getFaces());
				g.setImages(obj.getImages());
				g.setReviews(obj.getReviews());
				this.deleteGem(obj.getId());
				this.gemList.add(g.getName(), g);
				return g;
			}
		}
		return null;
	}
	
	private static GemList singleton = new GemList();
	public static GemList getInstance(){
		return singleton;
	}
	
}
