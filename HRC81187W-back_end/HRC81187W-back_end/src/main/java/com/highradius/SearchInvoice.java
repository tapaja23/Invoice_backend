package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.POJO;



/**
 * Servlet implementation class SearchInvoice
 */
@WebServlet("/SearchInvoice")
public class SearchInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			Connection con = DBconnection.createConnect();
			
			String searchInvoice = request.getParameter("CustomerId");
			

			
			Statement st = con.createStatement();
			String sql_statement = "SELECT * FROM winter_internship WHERE cust_number=" + "'"+searchInvoice+"'";
			ResultSet rs = st.executeQuery(sql_statement);

			ArrayList<POJO> data = new ArrayList<>();
			while(rs.next()) {
				POJO inv = new POJO();
				inv.setSl_no(rs.getInt("sl_no"));
				inv.setBusiness_code(rs.getString("business_code"));
				inv.setCust_number(rs.getInt("cust_number"));
				inv.setClear_date(rs.getString("clear_date"));
				inv.setBusiness_year(rs.getInt("business_year"));
				inv.setDoc_id(rs.getLong("doc_id"));
				inv.setPosting_date(rs.getString("posting_date"));
				inv.setDocument_create_date(rs.getString("document_create_date"));
				inv.setDue_in_date(rs.getString("due_in_date"));
				inv.setInvoice_currency(rs.getString("invoice_currency"));
				inv.setDocument_type(rs.getString("documnet_type"));
				inv.setPosting_id(rs.getInt("posting_id"));
				inv.setTotal_open_amount(rs.getDouble("total_open_amount"));
				inv.setBaseline_create_date(rs.getString("baseline_create_date"));
				inv.setCust_payment_terms(rs.getString("cust_payment_terms"));
				inv.setInvoice_id(rs.getLong("invoice_id"));
				data.add(inv);
			}
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices  = gson.toJson(data);
			response.setContentType("application/json");
			try {
				response.getWriter().write(invoices);//getWriter() returns a PrintWriter object that can send character text to the client. 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			rs.close();
			st.close();
			con.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
