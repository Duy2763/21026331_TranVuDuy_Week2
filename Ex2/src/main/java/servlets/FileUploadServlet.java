package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Servlet implementation class FileUploadServlet
 */
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1 MB Nếu kích thước file upload lơn hơn threshold sẽ được ghi trực tiếp vào ổ đĩa thay vì lưu ở memory đệm.
		maxFileSize = 1024 * 1024 * 5, // 5 MB Kích thước tối da của file được upload.
		maxRequestSize = 1024 * 1024 * 10) // 10 MB Kích thước tối đa cho một request.

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String uploadPath = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() throws ServletException {
		super.init();
		uploadPath = getServletContext().getInitParameter("upload.path");
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			boolean hasFile = false;
			for (Part filePart : request.getParts()) {
			    if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
			        hasFile = true;
			        String fileName = filePart.getSubmittedFileName();
			        InputStream inputStream = filePart.getInputStream();
			        Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName),
			                StandardCopyOption.REPLACE_EXISTING);
			        System.out.println(uploadPath + File.separator + fileName);
			    }
			}

			if (hasFile) {
			    response.getWriter().println("File uploaded successfully!");
			} else {
			    response.getWriter().println("No file selected for upload.");
			}

		} catch (IOException | ServletException e) {
			response.getWriter().println("File upload failed due to an error: " + e.getMessage());
		}

	}

}
