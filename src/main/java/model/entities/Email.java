package model.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

	private String email;

	public Email(String email) {

		validaEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		validaEmail(email);
	}

	public void validaEmail(String email) {

		String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {

			this.email = email;

		} else {

			System.out.println("email não valido");
			this.email = null;

		}
	}

	@Override
	public String toString() {
		return email;
	}

	
}
