package ua.lviv.woman.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projects {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column(length = 1000)
	private String projectInfoUA;

	@Column(length = 1000)
	private String projectInfoEN;

	private Blob image;

	private Date publishedDate;

	private Date exparationDate;

	private Blob file;

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

	public String getProjectInfo() {
		return projectInfoUA;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfoUA = projectInfo;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getProjectInfoUA() {
		return projectInfoUA;
	}

	public void setProjectInfoUA(String projectInfoUA) {
		this.projectInfoUA = projectInfoUA;
	}

	public String getProjectInfoEN() {
		return projectInfoEN;
	}

	public void setProjectInfoEN(String projectInfoEN) {
		this.projectInfoEN = projectInfoEN;
	}

	public Date getExparationDate() {
		return exparationDate;
	}

	public void setExparationDate(Date exparationDate) {
		this.exparationDate = exparationDate;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

}
