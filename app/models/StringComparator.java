package models;

public class StringComparator implements KeyComparator<String> {

	@Override
	public int compareTo(String key1, String key2) {
		return key1.compareTo(key2);
	}

}
