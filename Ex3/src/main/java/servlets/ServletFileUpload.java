package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import dao.ContactDao;
import entities.Contact;

/**
 * Servlet implementation class ServletFileUpload
 */
@MultipartConfig
public class ServletFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096; // 4KB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        System.out.println(firstName + lastName);
        Part filePart = request.getPart("photo");

        InputStream inputStream = null;
        String fileUploadName = "";
        
        if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            fileUploadName = filePart.getSubmittedFileName();
            inputStream = filePart.getInputStream();
            System.out.println("File part có dữ liệu");
        } else 
        	System.out.println("File part không có dữ liệu");

        Contact contact = new Contact(firstName, lastName, inputStream);
        String message;
        
        try (ContactDao contactDAO = new ContactDao()) {
            // Lưu thông tin vào CSDL
            contactDAO.insertContact(contact);
            message = "File uploaded and saved into database";

            // Đọc từ CSDL và lưu ra file
            Blob photoBlob = contactDAO.getPhotoByContactName(firstName, lastName);

            if (photoBlob != null) {
            	InputStream blobInputStream = photoBlob.getBinaryStream();
                
                // Lấy MIME type từ đối tượng filePart
                String contentType = filePart.getContentType();
                String fileExtension = "";
                
                // Xác định phần mở rộng tệp dựa trên MIME type
                if ("image/jpeg".equals(contentType)) {
                    fileExtension = ".jpg";
                } else if ("image/png".equals(contentType)) {
                    fileExtension = ".png";
                }
                
                // Tạo đường dẫn file với phần mở rộng thích hợp
                String filePath = "/Users/admin/Desktop/week2/Ex3/src/main/webapp/FileLocation/" + fileUploadName + fileExtension;
                savePhotoToFile(blobInputStream, filePath);
            }

        } catch (SQLException e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }

        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }

	private void savePhotoToFile(InputStream inputStream, String filePath) throws IOException {
        OutputStream outputStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }

}
