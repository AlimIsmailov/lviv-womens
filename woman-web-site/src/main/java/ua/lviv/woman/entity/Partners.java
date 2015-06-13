package ua.lviv.woman.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Partners {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Blob image;

	private String link;

	@Column(length = 1000)
	private String partnersInfoUA;

	@Column(length = 1000)
	private String partnersInfoEN;

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

	public String getPartnersInfoUA() {
		return partnersInfoUA;
	}

	public void setPartnersInfoUA(String partnersInfoUA) {
		this.partnersInfoUA = partnersInfoUA;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPartnersInfoEN() {
		return partnersInfoEN;
	}

	public void setPartnersInfoEN(String partnersInfoEN) {
		this.partnersInfoEN = partnersInfoEN;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
