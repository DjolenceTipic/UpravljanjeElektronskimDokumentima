package classesForWorkWithFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Download extends Mock {
	
	private static final String FILENAME = "resorces/app";
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		handleDownload(req,res); 
	}
	  
    private void handleDownload(HttpServletRequest req, HttpServletResponse res) {  
		String storagePath = resourceBundle.getString("storage");
		String nameOfFile = req.getParameter("fileName");
		String filename = storagePath+"/"+nameOfFile;
		try {
			if(filename != null) {
				InputStream stream;
				stream = new BufferedInputStream(
						new FileInputStream(new File(filename)));
				ServletOutputStream out;
				out = res.getOutputStream();
				byte[] bbuf = new byte[100];
				int length = 0;
				while ((stream != null) && ((length = stream.read(bbuf)) != -1))
				   {
				       out.write(bbuf,0,length);
				   }
				out.flush();
				out.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			writeError(res, e1);
		}
	}	
	  
	private void writeError(HttpServletResponse res, Exception ex) {
		res.setContentType("text/plain");
		res.setStatus(500);
		try {
			PrintWriter out = res.getWriter();
			out.println("ERROR");
		    out.println(ex);      
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
	}
	  
}