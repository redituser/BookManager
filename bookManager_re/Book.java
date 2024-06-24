package bookManager_re;

public class Book {
	private int  bookid;	
	private String author;
	private String title;
	private int publisher;
	
	public Book(int bookid, String author, String title, int publisher) {
		
		this.bookid = bookid;
		this.author = author;
		this.title = title;
		this.publisher = publisher;
	}
	

	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPublisher() {
		return publisher;
	}
	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}
	
	
	 @Override
	    public String toString() {
	        return "Book{" +
	                "bookid=" + bookid +
	                ", author='" + author + '\'' +
	                ", title='" + title + '\'' +
	                ", price=" + publisher +
	                '}';
	    }
	
	
}
