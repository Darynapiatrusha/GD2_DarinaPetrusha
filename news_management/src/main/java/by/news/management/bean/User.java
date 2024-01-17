package by.news.management.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	private String login;
	private String email;
	private String password;
	private Roles roles;
	private Status status = Status.ACTIVE;

	public User() {
		super();
	}

	public User(int id, String name, String surname, String login, String email, String password, Roles roles,
			Status status) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.status = status;
	}

	public User(String name, String surname, String login, String email, String password, Roles roles, Status status) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.status = status;
	}

	public User(int id, String name, String surname, Roles roles) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.roles = roles;
	}

	public User(int id, String name, String surname, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public User(int id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, name, password, roles, status, surname);
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
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password) && roles == other.roles
				&& status == other.status && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", email=" + email
				+ ", password=" + password + ", roles=" + roles + ", status=" + status + "]";
	}
}
