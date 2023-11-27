package by.news.management.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String login;
	private String email;
	private String password;
	private String status;

	public User() {
		super();
	}

	public User(String login,String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public User(String name, String surname, String login, String email, String password, String status) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, login, name, password, status, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(status, other.status) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", login=" + login + ", email=" + email + ", password="
				+ password + ", status=" + status + "]";
	}
}
