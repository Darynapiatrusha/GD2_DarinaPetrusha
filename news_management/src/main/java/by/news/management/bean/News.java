package by.news.management.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;
	private String brief;
	private String content;
	private String status;
	private int userId;
	private int id;

	public News() {
		super();
	}

	public News(Date date, String title, String brief, String content, String status, int userId, int id) {
		super();
		this.date = date;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
		this.userId = userId;
		this.id = id;
	}

	public News(Date date, String title, String brief, String content, String status, int userId) {
		super();
		this.date = date;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
		this.userId = userId;
	}

	public News(String title, String brief, String content, String status, int userId, int id) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
		this.userId = userId;
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brief, content, date, id, status, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date) && id == other.id && Objects.equals(status, other.status)
				&& Objects.equals(title, other.title) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "News [date=" + date + ", title=" + title + ", brief=" + brief + ", content=" + content + ", status="
				+ status + ", userId=" + userId + ", id=" + id + "]";
	}
}
