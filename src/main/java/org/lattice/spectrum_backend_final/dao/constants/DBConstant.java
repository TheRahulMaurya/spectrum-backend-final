package org.lattice.spectrum_backend_final.dao.constants;

public class DBConstant {

	
	//Queries
	
	public static final String CREATE_NEW_USER = "insert into user_details (user_id,prefix,first_name,middle_name,last_name,email_id,country_code,contact_number,last_login_date) values(?,?,?,?,?,?,?,?,?);";
	
}
