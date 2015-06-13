package ua.lviv.woman.entity;

import java.sql.Blob;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Events implements Comparable<Events> {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Date publishedDate;

	private Date expirationDate;

	private Date startDate;

	private byte topOfEvents;

	private byte toArchive;

	@Column(length = 1000)
	private String eventInformationUA;

	private Blob image;

	@Column(length = 1000)
	private String eventInformationEN;

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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getToArchive() {
		return toArchive;
	}

	public void setToArchive(byte toArchive) {
		this.toArchive = toArchive;
	}

	public String getEventInformationUA() {
		return eventInformationUA;
	}

	public void setEventInformationUA(String eventInformationUA) {
		this.eventInformationUA = eventInformationUA;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getEventInformationEN() {
		return eventInformationEN;
	}

	public void setEventInformationEN(String eventInformationEN) {
		this.eventInformationEN = eventInformationEN;
	}

	@Override
	public int compareTo(Events o) {
		byte compareToTop = ((Events) o).getTopOfEvents();
		return this.topOfEvents - compareToTop;
	}

	public static Comparator<Events> eventsPubDateComparator = new Comparator<Events>() {

		@Override
		public int compare(Events o1, Events o2) {
			String pubDate1 = o1.getPublishedDate().toString().toUpperCase();
			String pubDate2 = o2.getPublishedDate().toString().toUpperCase();
			return pubDate2.compareTo(pubDate1);
		}
	};

	public byte getTopOfEvents() {
		return topOfEvents;
	}

	public void setTopOfEvents(byte topOfEvents) {
		this.topOfEvents = topOfEvents;
	}

}
