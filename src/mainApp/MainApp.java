package mainApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import org.joda.time.DateTime;

import dao.ShowsDAO;
import models.Show;

public class MainApp {
	public static void main(String[] args) {
		ShowsDAO ndao = new ShowsDAO();
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
			ndao.insertShow(s);
		DateTime dFinish = new DateTime();
		System.out.println("Ha tardado " + ((dFinish.getMillisOfDay() - dStart.getMillisOfDay()) / 1000) + " segundos");

		sc.close();
	}

}
