package luceneHelpClasses;

import model.EBook;

public class HighlightBook extends EBook{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String highlight;

	public HighlightBook(){
		super();
	}
	public HighlightBook(EBook book){
		super();
		this.seteBookAuthor(book.geteBookAuthor());
		this.setCategory(book.getCategory());
		this.setFileName(book.getFileName());
		this.setId(book.getId());
		this.seteBookKeywords(book.geteBookKeywords());
		this.setLanguage(book.getLanguage());
		this.seteBookPublicationYear(book.geteBookPublicationYear());
		this.seteBookTitle(book.geteBookTitle());
		this.setUser(book.getUser());
	}
	public HighlightBook(String highlight) {
		super();
		this.highlight = highlight;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
}
