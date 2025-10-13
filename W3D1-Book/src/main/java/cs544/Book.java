package cs544;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import org.hibernate.validator.constraints.ISBN;
//import org.hibernate.validator.constraints.SafeHtml;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@SafeHtml see: https://stackoverflow.com/questions/58913428/what-alternatives-are-there-to-hibernate-validators-safehtml-to-validate-strin
	@NotBlank
	private String title;
	@ISBN
	private String ISBN;
	//@SafeHtml
	@NotBlank
	private String author;
	@Positive
	private double price;

	public Book() {
		super();
	}

	public Book(String title, String iSBN, String author, double price) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
