package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

import dto.CategorySearchDTO;
import dto.LanguageSearchDTO;
import luceneHelpClasses.HighlightBook;
import luceneHelpClasses.RequiredHighlight;
import luceneHelpClasses.ResultData;
import luceneHelpClasses.SearchType;
import model.EBook;
import model.SearchEntity;
import search.QueryBuilder;
import search.ResultRetriever;
import session.EBookDaoLocal;

@Path("/search")
public class SearchService {

	@EJB
	private EBookDaoLocal eBookDao;
	
	@POST
	@Produces("application/json")
	public List<HighlightBook> search(SearchEntity entity){
		List<HighlightBook> hbooks = new ArrayList<HighlightBook>();
		List<ResultData> results = new ArrayList<ResultData>();
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();
			System.out.println(entity);
			if (!(entity.getText() == null || entity.getText().equals(""))) {
				System.out.println("usao u text");
				SearchType.Type textst = SearchType.getType(entity.getTextSearchType());
				Query query = QueryBuilder.buildQuery(textst, "text", entity.getText());
				Occur textOccur = getOccur(entity.getTextSearchCondition());
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", entity.getText());
				System.out.println(rh.toString());
				rhs.add(rh);
			}

			if (!(entity.getAuthor() == null || entity.getAuthor().equals(""))) {
				SearchType.Type authorst = SearchType.getType(entity.getAuhtorSearchType());
				Query query = QueryBuilder.buildQuery(authorst, "author", entity.getAuthor());
				Occur authorOccur = getOccur(entity.getAuthorSearchCondition());
				bquery.add(query, authorOccur);
			}
			
			if (!(entity.getKeywords() == null || entity.getKeywords().equals(""))) {
				SearchType.Type keywordsst = SearchType.getType(entity.getKeywordsSearchType());
				Query query = QueryBuilder.buildQuery(keywordsst, "keyword", entity.getKeywords());
				Occur keywordsoccur = getOccur(entity.getKeywordsSearchCondition());
				bquery.add(query, keywordsoccur);
			}
			
			if (!(entity.getTitle() == null || entity.getTitle().equals(""))) {
				SearchType.Type titlest = SearchType.getType(entity.getTitleSearchType());
				Query query = QueryBuilder.buildQuery(titlest, "title", entity.getTitle());
				Occur titleoccur = getOccur(entity.getTitleSearchCondition());
				bquery.add(query, titleoccur);
			}

			results = ResultRetriever.getResults(bquery, rhs);

		} catch (IllegalArgumentException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		List<EBook> ebooks = eBookDao.findAll();
		for(ResultData r : results){
			EBook eBook = new EBook();
			for(EBook b : ebooks){
				if(b.getFileName().equals(r.getLocation())){
					eBook = b;
					System.out.println(b);
				}
			}
			System.out.println("r je "+r.getHighlight());
			HighlightBook hbook = new HighlightBook(eBook);
			try{
				hbook.setHighlight(r.getHighlight());
				System.out.println("hbook je: "+hbook);
			}catch(NullPointerException e){
				System.out.println("There is no highlight.");
			}
			
				hbooks.add(hbook);
		}
		return hbooks;
	}
	
	@POST
	@Path("/searchByLanguage")
	@Produces("application/json")
	public List<EBook> searchByLanguage(LanguageSearchDTO entity){
		System.out.println(entity);
		List<EBook> hbooks = new ArrayList<EBook>();
		List<ResultData> results = new ArrayList<ResultData>();
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();

			if (!(entity.getName() == null || entity.getName().equals(""))) {
				SearchType.Type textst = SearchType.getType(entity.getType());
				Query query = QueryBuilder.buildQuery(textst, "language", entity.getName());
				Occur textOccur = getOccur(entity.getoccur());
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", entity.getName());
				rhs.add(rh);
			}

			results = ResultRetriever.getResults(bquery, rhs);

		} catch (IllegalArgumentException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		List<EBook> ebooks = eBookDao.findAll();
		for(ResultData r : results){
			EBook eBook = new EBook();
			for(EBook b : ebooks){
				if(b.getFileName().equals(r.getLocation())){
					eBook = b;
				}
			}
			hbooks.add(eBook);
		}
		return hbooks;
	}
	
	@POST
	@Path("/searchByCategory")
	@Produces("application/json")
	public List<EBook> searchByCategory(CategorySearchDTO entity){
		System.out.println(entity);
		List<EBook> hbooks = new ArrayList<EBook>();
		List<ResultData> results = new ArrayList<ResultData>();
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();

			if (!(entity.getName() == null || entity.getName().equals(""))) {
				SearchType.Type textst = SearchType.getType(entity.getType());
				Query query = QueryBuilder.buildQuery(textst, "category", entity.getName());
				Occur textOccur = getOccur(entity.getOccur());
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", entity.getName());
				rhs.add(rh);
			}

			results = ResultRetriever.getResults(bquery, rhs);

		} catch (IllegalArgumentException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		List<EBook> ebooks = eBookDao.findAll();
		for(ResultData r : results){
			EBook eBook = new EBook();
			for(EBook b : ebooks){
				if(b.getFileName().equals(r.getLocation())){
					eBook = b;
				}
			}
			hbooks.add(eBook);
		}
		return hbooks;
	}
	
	private Occur getOccur(String value){
		if(value.equals("+")){
			return Occur.MUST;
		}else if(value.equals("-")){
			return Occur.MUST_NOT;
		}else{
			return Occur.SHOULD;
		}
	}
}
