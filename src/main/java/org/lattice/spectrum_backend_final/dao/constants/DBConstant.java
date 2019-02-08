package org.lattice.spectrum_backend_final.dao.constants;

public class DBConstant {

	
	//Queries
	
	public static final String CREATE_NEW_USER_DETAILS = "insert into user_details (user_id,prefix,first_name,middle_name,last_name,email_id,country_code,contact_number,last_login_date) values(?,?,?,?,?,?,?,?,?);";
	
	public static final String CREATE_NEW_USER_MASTER = "insert into user_master (employee_id,is_active,created_by,created_on,org_id) values(?,?,?,?,?);";

	public static final String ADD_USER_ROLE_MAP = "insert into user_role_map (user_id , role_id , is_active) values(?,?,?);";
	
	public static final String ADD_USER_CREDENTIALS = "insert into user_credentials (user_id , preset_password , new_password , is_verified) values(?,?,?,?);";

}
