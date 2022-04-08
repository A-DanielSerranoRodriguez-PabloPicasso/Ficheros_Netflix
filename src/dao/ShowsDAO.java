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
		Almacen.nextSet.clear();
		switch (where) {
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

	private void addShow(ResultSet s) throws SQLException {
		Almacen.shows.add(new Show(s.getString(1), s.getString(2), s.getString(3), s.getString(4), s.getString(5),
				s.getString(6), s.getString(7), s.getString(8), s.getString(9), s.getString(10), s.getString(11),
				s.getString(12)));
		Almacen.nextSet.add(new Show(s.getString(1), s.getString(2), s.getString(3), s.getString(4), s.getString(5),
				s.getString(6), s.getString(7), s.getString(8), s.getString(9), s.getString(10), s.getString(11),
				s.getString(12)));
	}
}
