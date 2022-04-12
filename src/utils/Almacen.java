package utils;

import java.util.ArrayList;

import models.Show;

public class Almacen {
	public static ArrayList<Show> shows = new ArrayList<>();
	
	/**
	 * Removes the shows
	 */
	public static void clearShows() {
		shows.clear();
	}
}
