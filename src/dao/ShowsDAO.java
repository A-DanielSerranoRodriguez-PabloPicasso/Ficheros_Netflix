package dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import org.joda.time.DateTime;

import models.Show;
import utils.Almacen;
import utils.ShowFilter;

public class ShowsDAO extends AbstractDAO {
	private static Statement statement;

	public ShowsDAO() {
		super();
		statement = AbstractDAO.createStatement();
	}

	public static void showFiller() {
		statement = createStatement();
		ResultSet showCount = showCount();
		try {
			if (showCount != null) {
				if (showCount.next())
					if (showCount.getInt(1) != 7787) {
						fill();
					}
			} else {
				fill();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ResultSet showCount() {
		try {
			return statement.executeQuery("SELECT count(*) from shows;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void fill() {
		File f = new File("resources/netflix2.csv");
		TreeSet<Show> shows = new TreeSet<>();
		Scanner sc = null;
		int cont = 0;
		String oopsie1 = "", oopsie2 = "", laMalaOracion = "";

		try {
			sc = new Scanner(f);
			sc.useDelimiter(",");

			while (sc.hasNextLine()) {
				if (cont == 0) {
					sc.nextLine();
					cont++;
				} else {
					String oracion = sc.nextLine(), frase;
					oracion = oracion.replace("\"\"\"", "\"`").replace(".\"`", ".`\"").replace("\"\"", "`").replace("'",
							"`");
					laMalaOracion = oracion;
					String[] conjunto = oracion.split(",");
					ArrayList<String> palabras = new ArrayList<>();
					String set = "";
					boolean grupo = false;
					int comillas = 0;

					for (int i = 0, length = conjunto.length; i < length; i++) {
						frase = conjunto[i];
						if (grupo) {
							if (frase.charAt(frase.length() - 1) == '"') {
								for (int j = 0, size = frase.length(); j < size; j++) {
									if (frase.charAt(j) == '"')
										comillas++;
								}
								if (comillas % 2 != 0) {
									grupo = false;
									palabras.add(set + frase);
									oopsie2 = set + frase;
									set = "";
								} else {
									set += frase + ",";
								}
								comillas = 0;
							} else {
								set += frase + ",";
							}
						} else {
							if (frase.isEmpty()) {
								palabras.add(null);
							} else {
								if (frase.charAt(0) == '"') {
									for (int j = 0, size = frase.length(); j < size; j++) {
										if (frase.charAt(j) == '"')
											comillas++;
									}
									if (comillas % 2 != 0) {
										grupo = true;
										set += frase + ",";
									} else {
										palabras.add(frase);
										oopsie1 = frase;
									}
									comillas = 0;
								} else {
									palabras.add(frase);
									oopsie1 = frase;
								}
							}
						}
					}

					if (palabras.size() != 12) {
						System.out.println(palabras);
					}

					for (int i = 0, size = palabras.size(); i < size; i++) {
						if (palabras.get(i) != null) {
							if (palabras.get(i).charAt(0) == '"'
									&& palabras.get(i).charAt(palabras.get(i).length() - 1) == '"') {
								palabras.add(i, palabras.get(i).substring(1, palabras.get(i).length() - 2));
								palabras.remove(i + 1);
							}
						}
					}

					shows.add(new Show(palabras.get(0), palabras.get(1), palabras.get(2), palabras.get(3),
							palabras.get(4), palabras.get(5), palabras.get(6), palabras.get(7), palabras.get(8),
							palabras.get(9), palabras.get(10), palabras.get(11)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(oopsie1);
			System.out.println(oopsie2);
			System.out.println(laMalaOracion);
		}
		DateTime dStart = new DateTime();
		for (Show s : shows)
			insertShow(s);
		DateTime dFinish = new DateTime();
		System.out.println("Ha tardado " + ((dFinish.getMillisOfDay() - dStart.getMillisOfDay()) / 1000) + " segundos");
	}

	/**
	 * Inserts shows in the database
	 * 
	 * @param show Object 'Show'
	 * @return True if the show has been inserted, false if not
	 */
	public static boolean insertShow(Show show) {
		try {
			statement.execute("insert into shows values (" + Integer.parseInt(show.getShow_id().substring(1)) + ", '"
					+ show.getType() + "', '" + show.getTitle() + "', '" + show.getDirector() + "', '" + show.getCast()
					+ "', '" + show.getCountry() + "', '" + show.getDate_added() + "', "
					+ Integer.parseInt(show.getRelease_year()) + ", '" + show.getRating() + "', '" + show.getDuration()
					+ "', '" + show.getListed_in() + "', '" + show.getDescription() + "');");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates the shows of the list
	 * 
	 * @param filter Filter used to get the shows
	 * @param what   String that represents what we are looking for in the filter
	 * @param start  Int that represents from where we are taking the shows
	 */
	public void fillShows(ShowFilter filter, String what, int start) {
		switch (filter) {
		case Nada:
			try (ResultSet shows = statement.executeQuery("select * from shows limit " + start + ",40;")) {
				while (shows.next()) {
					addShow(shows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Director:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where director like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					addShow(shows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Pais:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where country like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					addShow(shows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Salida:
			try (ResultSet shows = statement.executeQuery(
					"select * from shows where date_release like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					addShow(shows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Titulo:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where title like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					addShow(shows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * Adds the shows to the ArrayLists<Show>
	 * 
	 * @param s ResultSet with the show
	 * @throws SQLException If the show couldn't be fetched
	 */
	private void addShow(ResultSet s) throws SQLException {
		Almacen.shows.add(new Show(s.getString(1), s.getString(2), s.getString(3), s.getString(4), s.getString(5),
				s.getString(6), s.getString(7), s.getString(8), s.getString(9), s.getString(10), s.getString(11),
				s.getString(12)));
	}
}
