package dao;

import java.sql.Statement;

import models.Show;

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
}
