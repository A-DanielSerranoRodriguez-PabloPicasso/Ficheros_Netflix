package models;

public class User {
	private String name, passwd, email;

	public User(String name, String email, String passwd) {
		this.name = name;
		this.email = email;
		this.passwd = passwd;
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
}
