package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * Servlet Filter implementation class FileUploadFilter
 */
public class FilterFileUpload extends HttpFilter implements Filter {
	 private static final String[] ALLOWED_TYPES = {"image/jpeg", "image/png"};
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterFileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    Part filePart = httpRequest.getPart("photo");
	    if (filePart != null) {
            String contentType = filePart.getContentType();
            boolean allowed = false;
            for (String type : ALLOWED_TYPES) {
                if (type.equals(contentType)) {
                    allowed = true;
                    break;
                }
            }
            if (allowed) {
                chain.doFilter(request, response); // Continue to the next filter or servlet
            } else {
                request.setAttribute("message", "Unsupported file type. Only JPG and PNG files are allowed.");
                httpRequest.getRequestDispatcher("/message.jsp").forward(request, response);
            }
        } else {
            chain.doFilter(request, response); // No file uploaded, continue normally
        }
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
