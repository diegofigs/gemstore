package models;

import java.util.Random;

public class GemList {
	
	private SortedCircularDoublyLinkedList<Gem> gemList;
	int counter = 1;
	String[] images = {"assets/images/gem-02.gif","assets/images/gem-05.gif","assets/images/gem-09.gif"};
	String[] images2 = {"assets/images/gem-01.gif","assets/images/gem-03.gif","assets/images/gem-04.gif"};
	String[] images3 = {"assets/images/gem-06.gif","assets/images/gem-07.gif","assets/images/gem-08.gif"};
	
	private GemList(){
		this.gemList = new SortedCircularDoublyLinkedList<Gem>();
		// First gem
		Gem azurite = new Gem();
		azurite.setId(counter++);
		azurite.setName("Azurite");
		azurite.setDescription("Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.");
		azurite.setShine(8);
		azurite.setPrice(110.50);
		azurite.setRarity(7);
		azurite.setColor("#CCC");
		azurite.setFaces(14);
		azurite.setImages(images);
		gemList.add(azurite);
		// Second gem
		Gem bloodstone = new Gem();
		bloodstone.setId(counter++);
		bloodstone.setName("Bloodstone");
		bloodstone.setDescription("Origin of the Bloodstone is unknown, hence its low value. It has a very high shine and 12 sides, however.");
		bloodstone.setShine(9);
		bloodstone.setPrice(22.90);
		bloodstone.setRarity(6);
		bloodstone.setColor("#EEE");
		bloodstone.setFaces(12);
		bloodstone.setImages(images2);
		gemList.add(bloodstone);
		// Third gem
		Gem zircon = new Gem();
		zircon.setId(counter++);
		zircon.setName("Zircon");
		zircon.setDescription("Zircon is our most coveted and sought after gem. You will pay much to be the proud owner of this gorgeous and high shine gem.");
		zircon.setShine(70);
		zircon.setPrice(1100);
		zircon.setRarity(2);
		zircon.setColor("#000");
		zircon.setFaces(6);
		zircon.setImages(images3);
		gemList.add(zircon);
	}
	
	public Gem addGem(Gem obj){
		long id = this.counter++;
		obj.setId(id);
		Random rand = new Random();
		int random = rand.nextInt(2);
		if(random == 0)
			obj.setImages(images);
		else if(random == 1)
			obj.setImages(images2);
		else
			obj.setImages(images3);
		this.gemList.add(obj);
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
		Gem result[] = new Gem[this.gemList.size()];
		for(int i=0; i < this.gemList.size(); ++i){
			result[i] = this.gemList.get(i);
		}
		return result;
	}
	
	public boolean deleteGem(long id){
		for(int i=0; i < this.gemList.size(); ++i){
			if(this.gemList.get(i).getId() == id){
				this.gemList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Gem updateGem(Gem obj){
		for(int i=0; i<this.gemList.size(); ++i){
			if(this.gemList.get(i).getId() == obj.getId()){
				Gem g = this.gemList.get(i);
				g.setName(obj.getName());
				g.setDescription(obj.getDescription());
				g.setShine(obj.getShine());
				g.setPrice(obj.getPrice());
				g.setRarity(obj.getRarity());
				g.setColor(obj.getColor());
				g.setFaces(obj.getFaces());
				g.setImages(obj.getImages());
				this.deleteGem(obj.getId());
				this.gemList.add(g);
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
