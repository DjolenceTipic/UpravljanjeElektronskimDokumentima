package luceneHelpClasses;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import dto.EBookDTOCreate;
import dto.EBookDTOFieldValues;
import model.EBook;

public class LuceneIndexer {

	private final Version v = Version.LUCENE_40;
	private File indexDirPath;
	private IndexWriter indexWriter;
	private Directory indexDir;
	
	private static final String FILENAME = "resorces/app";
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);
	private static LuceneIndexer indexer = new LuceneIndexer(true);
	
	private EBookDTOCreate dto;
	
	public static LuceneIndexer getInstance(){
		return indexer;
	}
	
	/**
	 * @param path - kanonicka adresa direktorijuma u koji ce biti smesteni indeksi
	 * @param restart - <b>true</b> za kreiranje novog indeksa, <b>false</b> za nastavak koriscenja vec postojecih indeksa
	 */
	private LuceneIndexer(String path, boolean restart) {
		IndexWriterConfig iwc = new IndexWriterConfig(v, new SerbianAnalyzer());
		if(restart){
			iwc.setOpenMode(OpenMode.CREATE);
		}else{
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		}
		
		try{
			this.indexDir = new SimpleFSDirectory(new File(path));
			this.indexWriter = new IndexWriter(this.indexDir, iwc);
		}catch(IOException ioe){
			throw new IllegalArgumentException("Path not correct");
		}
		
	}
	
	/**
	 * @param restart - <b>true</b> za kreiranje novog indeksa, <b>false</b> za nastavak koriscenja vec postojecih indeksa
	 * <p>
	 * Direktorijum u kojem ce se indeks nalaziti se ucitava iz <i>app.properties</i> datoteke
	 */
	private LuceneIndexer(boolean restart){
		this(resourceBundle.getString("index"), restart);
	}
	
	private LuceneIndexer(){
		this(false);
	}
	
	public IndexWriter getIndexWriter(){
		return this.indexWriter;
	}
	
	public Directory getIndexDir(){
		return indexDir;
	}
	
	public File getIndexDirPath(){
		return indexDirPath;
	}
	
	/**
	 * Od dobijenih vrednosti se konstruise Term po kojem se vrsi pretraga dokumenata
	 * Dokumenti koji zadovoljavaju uslove pretrage ce biti obrisani
	 * 
	 * @param fieldName naziv polja
	 * @param value vrednost polja
	 * @return
	 */
	public boolean delete(String filename){
		Term delTerm = new Term("filename", filename);
		try {
			synchronized (this) {
				this.indexWriter.deleteDocuments(delTerm);
				this.indexWriter.commit();
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public boolean add(Document doc){
		try {
			synchronized (this) {
				this.indexWriter.addDocument(doc);
				this.indexWriter.commit();
			}
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	
	public boolean updateDocument(String filename, List<IndexableField> fields){		
		try {
			DirectoryReader reader = DirectoryReader.open(this.indexDir);
			IndexSearcher is = new IndexSearcher(reader);
			Query query = new TermQuery(new Term("filename", filename));
			TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
			is.search(query, collector);
			
			ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;
			if(scoreDocs.length > 0){
				int docID = scoreDocs[0].doc;
				Document doc = is.doc(docID);
				if(doc != null){
					for(IndexableField field : fields){
						doc.removeFields(field.name());
					}
					for(IndexableField field : fields){
						doc.add(field);
					}
					try{
						synchronized (this) {
							this.indexWriter.updateDocument(new Term("filename", filename), doc);
							this.indexWriter.commit();
							return true;
						}
					}catch(IOException e){
					}
				}
			}
			
			return false;
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Indeksni direktorijum nije u redu");
		} 
	}
	
	/**
	 * 
	 * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
	 */
	public EBookDTOFieldValues index(File file){	
		EBookDTOFieldValues entity = null;
		DocumentHandler handler = null;
		String fileName = null;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}
					Document doc = handler.getDocument(newFile);
					this.indexWriter.addDocument(doc);
					try{
						List<IndexableField> list = doc.getFields();
						entity = new EBookDTOFieldValues();
						for(IndexableField fild : list){
							if(fild.name().equals("author")){
								entity.setAuthor(fild.stringValue());
							}else if(fild.name().equals("title")){
								entity.setTitle(fild.stringValue());
							}else if(fild.name().equals("category")){
								entity.setCategory(fild.stringValue());
							}else if(fild.name().equals("language")){
								entity.setLanguage(fild.stringValue());
							}else if(fild.name().equals("keyword")){
								if(entity.getKeywords()!=null){
									List<String> keywords = entity.getKeywords();
									keywords.add(fild.stringValue());
									entity.setKeywords(keywords);
								}else{
									List<String> keywords = null;
									entity.setKeywords(keywords);
								}
							}	
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			this.indexWriter.commit();
			System.out.println("indexing done");
			
		} catch (IOException e) {
			System.out.println("indexing NOT done");
		}
		return entity;
	}
	
	protected void finalize() throws Throwable {
		this.indexWriter.close();
	}
	
	protected DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".pdf")){
			return new PDFHandler();
		}else{
			return null;
		}
	}

	public void index(File file, EBookDTOCreate entity) {
		DocumentHandler handler = null;
		String fileName = null;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}
					Document doc = handler.getDocument(newFile);
					doc.removeField("title");
					doc.add(new TextField("title", entity.getTitle(), Store.YES));
					doc.removeField("author");
					doc.add(new TextField("author", entity.getAuthor(), Store.YES));
					doc.removeField("keyword");
					doc.add(new TextField("keyword", entity.getKeywords(), Store.YES));
					doc.removeField("filename");
					doc.add(new TextField("filename", entity.getFileName(), Store.YES));
					//find category name]
					doc.removeField("category");
					doc.add(new TextField("category", entity.getCategoryID(), Store.YES));
					//find language name
					doc.removeField("language");
					doc.add(new TextField("language", entity.getLanguageID(), Store.YES));
					
					
					this.indexWriter.addDocument(doc);
				}
			}
			this.indexWriter.commit();
			System.out.println("indexing done");
		} catch (IOException e) {
			System.out.println("indexing NOT done");
		}
		
	}
	
	public void index(File file, EBook entity) {
		DocumentHandler handler = null;
		String fileName = null;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}
					Document doc = handler.getDocument(newFile);
					doc.removeField("title");
					doc.add(new TextField("title", entity.geteBookTitle(), Store.YES));
					doc.removeField("author");
					doc.add(new TextField("author", entity.geteBookAuthor(), Store.YES));
					doc.removeField("keyword");
					doc.add(new TextField("keyword", entity.geteBookKeywords(), Store.YES));
					doc.removeField("filename");
					doc.add(new TextField("filename", entity.getFileName(), Store.YES));
					//find category name]
					doc.removeField("category");
					doc.add(new TextField("category", entity.getCategory().getName(), Store.YES));
					//find language name
					doc.removeField("language");
					doc.add(new TextField("language", entity.getLanguage().getLanguageName(), Store.YES));
					
					
					this.indexWriter.addDocument(doc);
				}
			}
			this.indexWriter.commit();
			System.out.println("indexing done");
		} catch (IOException e) {
			System.out.println("indexing NOT done");
		}
		
	}
}
