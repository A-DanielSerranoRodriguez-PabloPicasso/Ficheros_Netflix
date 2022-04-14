package models;

import java.util.ArrayList;

public class User {
	private String name, passwd, email, separator;
	private ArrayList<Show> favorites;

	public User(String name, String email, String passwd) {
		this.name = name;
		this.email = email;
		this.passwd = passwd;
		this.favorites = new ArrayList<Show>();
		this.separator = "";
	}

	public void populateShows() {

	}

	public String getName() {
		return name;
	}

	public char[] getPasswd() {
		int l = passwd.length();
		char[] chars = new char[l];

		for (int i = 0; i < l; i++)
			chars[i] = passwd.charAt(i);

		return chars;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<Show> getFavorites() {
		return favorites;
	}

	public void addShow(Show s) {
		favorites.add(s);
	}

	public void removeShow(Show s) {
		favorites.remove(s);
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}
}
