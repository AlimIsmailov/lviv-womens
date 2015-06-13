package ua.lviv.woman.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Donors {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Blob image;

	private String link;

	@Column(length = 1000)
	private String donorsInfoUA;

	@Column(length = 1000)
	private String donorsInfoEN;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDonorsInfoUA() {
		return donorsInfoUA;
	}

	public void setDonorsInfoUA(String donorsInfoUA) {
		this.donorsInfoUA = donorsInfoUA;
	}

	public String getDonorsInfoEN() {
		return donorsInfoEN;
	}

	public void setDonorsInfoEN(String donorsInfoEN) {
		this.donorsInfoEN = donorsInfoEN;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
