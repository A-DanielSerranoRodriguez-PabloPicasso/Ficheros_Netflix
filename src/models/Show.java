package models;

public class Show implements Comparable<Show> {
	private String show_id, type, title, director, cast, country, date_added, release_year, rating, duration, listed_in,
			description;

	public Show(String show_id, String type, String title, String director, String cast, String country,
			String date_added, String release_year, String rating, String duration, String listed_in,
			String description) {
		super();
		if (show_id == null)
			this.show_id = "N/A";
		else
			this.show_id = show_id;

		if (type == null)
			this.type = "N/A";
		else
			this.type = type;

		if (title == null)
			this.title = "N/A";
		else
			this.title = title;

		if (director == null)
			this.director = "N/A";
		else
			this.director = director;

		if (cast == null)
			this.cast = "N/A";
		else
			this.cast = cast;

		if (country == null)
			this.country = "N/A";
		else
			this.country = country;

		if (date_added == null)
			this.date_added = "N/A";
		else
			this.date_added = date_added;

		if (release_year == null)
			this.release_year = "N/A";
		else
			this.release_year = release_year;

		if (rating == null)
			this.rating = "N/A";
		else
			this.rating = rating;

		if (duration == null)
			this.duration = "N/A";
		else
			this.duration = duration;

		if (listed_in == null)
			this.listed_in = "N/A";
		else
			this.listed_in = listed_in;

		if (description == null)
			this.description = "N/A";
		else
			this.description = description;
	}

	public String getShow_id() {
		return show_id;
	}


	public String getType() {
		return type;
	}


	public String getTitle() {
		return title;
	}


	public String getDirector() {
		return director;
	}


	public String getCast() {
		return cast;
	}


	public String getCountry() {
		return country;
	}


	public String getDate_added() {
		return date_added;
	}


	public String getRelease_year() {
		return release_year;
	}


	public String getRating() {
		return rating;
	}

	public String getDuration() {
		return duration;
	}


	public String getListed_in() {
		return listed_in;
	}


	public String getReleaseYear() {
		return release_year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Show [show_id=" + show_id + ", type=" + type + ", title=" + title + ", director=" + director + ", cast="
				+ cast + ", country=" + country + ", date_added=" + date_added + ", release_year=" + release_year
				+ ", rating=" + rating + ", duration=" + duration + ", listed_in=" + listed_in + ", description="
				+ description + "]";
	}

	@Override
	public int compareTo(Show o) {
		int first = release_year.compareTo(o.release_year);

		if (first != 0)
			return first;
		else {
			int second = rating.compareTo(o.rating);
			if (second != 0)
				return second;
			else {
				return title.compareTo(o.title);
			}
		}
	}

}
