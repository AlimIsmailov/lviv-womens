package ua.lviv.woman.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class News {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column(length = 1000)
	private String newsInfoUA;

	@Column(length = 1000)
	private String newsInfoEn;

	private Date publishedDate;

	private Date exparationDate;

	private Blob image;

	private byte topOfNews;

	private byte toArchive;

	@ManyToOne
	@JoinColumn(name = "USERS_ID")
	private Users user;

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

	public String getNewsInfoUA() {
		return newsInfoUA;
	}

	public void setNewsInfoUA(String newsInfoUA) {
		this.newsInfoUA = newsInfoUA;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getNewsInfoEn() {
		return newsInfoEn;
	}

	public void setNewsInfoEn(String newsInfoEn) {
		this.newsInfoEn = newsInfoEn;
	}

	public Date getExparationDate() {
		return exparationDate;
	}

	public void setExparationDate(Date exparationDate) {
		this.exparationDate = exparationDate;
	}

	public byte getTopOfNews() {
		return topOfNews;
	}

	public void setTopOfNews(byte topOfNews) {
		this.topOfNews = topOfNews;
	}

	public byte getToArchive() {
		return toArchive;
	}

	public void setToArchive(byte toArchive) {
		this.toArchive = toArchive;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
