package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@WebServlet("/BookPagesServlet")
public class BookPagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String bookTitle = request.getParameter("bookTitle");
		bookTitle=bookTitle.trim();
		String genre = request.getParameter("genre");
		String filePath = getServletContext().getRealPath("/books")+"\\"+genre+"\\"+bookTitle+".pdf";
		System.out.println(filePath);
        File file = new File(filePath);

        try(PDDocument document = PDDocument.load(file))
        {
		Splitter splitter = new Splitter();
		List<PDDocument> splitPages = splitter.split(document);
		PDFTextStripper pdfStripper =new PDFTextStripper();
		int i=0;
		
		String book[]=new String[splitPages.size()];
		System.out.println(splitPages.size());
		for(PDDocument page : splitPages)
		{
			String text = pdfStripper.getText(page);
			System.out.println(text.length());
			String words[]=text.split(" ");
			StringBuilder merge= new StringBuilder("");
			for(String word : words)
			{
				merge.append("<span class=\"word\"> " + word + "</span>");
//				merge.append(word);
			}
			book[i++]=merge.toString();
		}
		request.setAttribute("Book",book);
		request.getRequestDispatcher("/BookPage.jsp").forward(request, response);
	    }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

}

