package classesForWorkWithFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.json.JsonException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import dto.EBookDTOFieldValues;
import luceneHelpClasses.LuceneIndexer;

@SuppressWarnings({"serial", "unchecked"})
public class Upload extends Mock {
	
	private static final String FILENAME = "resorces/app";
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);
	
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storagePath = resourceBundle.getString("storage");
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				FileItem fileItem = null;
				File uploadedFile = null;
				String fileName = "";
				String extension = "";
				for(FileItem item : items){ //trebalo bi da ima samo 1
					if(!item.isFormField()){
						fileName = item.getName();
						if(fileName.endsWith("pdf")){
							extension = ".pdf";
						}else{
							return;
						}
						fileName = System.currentTimeMillis() + extension;
						uploadedFile = new File(storagePath, fileName);
						fileItem = item;
						break;
					}
				}
				while (uploadedFile.exists()) {
					fileName = System.currentTimeMillis() + extension;
					uploadedFile = new File(storagePath, fileName);
				}
				uploadedFile.createNewFile();
				fileItem.write(uploadedFile);
				EBookDTOFieldValues entity = LuceneIndexer.getInstance().index(uploadedFile);
				entity.setFileName(fileName);
				String json = new Gson().toJson(entity);
				response.getWriter().write(json);
				response.setStatus(HttpServletResponse.SC_OK);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			}
		}
	}

}
