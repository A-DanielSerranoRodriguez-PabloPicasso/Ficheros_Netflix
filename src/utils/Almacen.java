package utils;

import java.util.ArrayList;

import models.Show;

public class Almacen {
	public static ArrayList<Show> shows = new ArrayList<>();
	public static ArrayList<Show> nextSet = new ArrayList<>();
	
	public static void clearShows() {
		shows.clear();
	}
}
