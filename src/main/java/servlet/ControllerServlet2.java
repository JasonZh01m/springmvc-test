package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import form.ProductForm;

public class ControllerServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1579L;

	/**
	 * Constructor of the object.
	 */
	public ControllerServlet2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 process(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 process(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("##This is ControllerServlet2##");
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		// execute an action
		if(action.equals("product_input.action")) {
			// no action
		} else if (action.equals("product_save.action")) {
			// create form
			ProductForm productFrom = new ProductForm();
			productFrom.setName(request.getParameter("name"));
			productFrom.setDescription(request.getParameter("description"));
			productFrom.setPrice(request.getParameter("price"));
			
			// create model
			Product product = new Product();
			product.setName(productFrom.getName());
			product.setDescription(productFrom.getDescription());
			try {
				product.setPrice(Float.parseFloat(productFrom.getPrice()));
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
			
			// code to save product
			
			// store model in a scope variable for the view
			request.setAttribute("product", product);
		}
		
		// forward to a view
		String dispatchUrl = null;
		if(action.equals("product_input.action")) {
			dispatchUrl = "/WEB-INF/jsp/ProductForm.jsp";
		} else if (action.equals("product_save.action")) {
			dispatchUrl = "/WEB-INF/jsp/ProductDetails.jsp";
		}
		if(dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		}
		
	}
	
}
