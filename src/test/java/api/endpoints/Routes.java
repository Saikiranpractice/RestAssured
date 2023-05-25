package api.endpoints;

public class Routes {
	public static String base_url="http://localhost:3000";
	
	public static String createEmployee=base_url+"/Employee";
	public static String getEmployee=base_url+"/Employee/{id}";
	public static String updateEmployee=base_url+"/Employee/{id}";
	public static String deleteEmployee=base_url+"/Employee/{id}";
}
