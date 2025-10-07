package cs544.message;

import lombok.*;
@AllArgsConstructor
@Data
class Book {
	private int id;
	private String title;
	private String ISBN;
	private String author;
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
}
