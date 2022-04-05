package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Show;
import utils.Almacen;
import utils.ShowFilter;

public class ShowsDAO extends AbstractDAO {
	private Statement statement;

	public ShowsDAO() {
		super();
		statement = this.createStatement();
	}

	public boolean insertShow(Show show) {
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
	
	public void fillShows(ShowFilter where, String what, int start) {
		switch (where) {
		case Nada:
			try (ResultSet shows = statement.executeQuery("select * from shows limit " + start + ",40;")) {
				while (shows.next()) {
					Almacen.shows.add(
							new Show(shows.getString(1), shows.getString(2), shows.getString(3), shows.getString(4),
									shows.getString(5), shows.getString(6), shows.getString(7), shows.getString(8),
									shows.getString(9), shows.getString(10), shows.getString(11), shows.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Director:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where director like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					Almacen.shows.add(
							new Show(shows.getString(1), shows.getString(2), shows.getString(3), shows.getString(4),
									shows.getString(5), shows.getString(6), shows.getString(7), shows.getString(8),
									shows.getString(9), shows.getString(10), shows.getString(11), shows.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Pais:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where country like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					Almacen.shows.add(
							new Show(shows.getString(1), shows.getString(2), shows.getString(3), shows.getString(4),
									shows.getString(5), shows.getString(6), shows.getString(7), shows.getString(8),
									shows.getString(9), shows.getString(10), shows.getString(11), shows.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Salida:
			try (ResultSet shows = statement.executeQuery(
					"select * from shows where date_release like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					Almacen.shows.add(
							new Show(shows.getString(1), shows.getString(2), shows.getString(3), shows.getString(4),
									shows.getString(5), shows.getString(6), shows.getString(7), shows.getString(8),
									shows.getString(9), shows.getString(10), shows.getString(11), shows.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case Titulo:
			try (ResultSet shows = statement
					.executeQuery("select * from shows where title like '%" + what + "%' limit " + start + ",40;")) {
				while (shows.next()) {
					Almacen.shows.add(
							new Show(shows.getString(1), shows.getString(2), shows.getString(3), shows.getString(4),
									shows.getString(5), shows.getString(6), shows.getString(7), shows.getString(8),
									shows.getString(9), shows.getString(10), shows.getString(11), shows.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
}
