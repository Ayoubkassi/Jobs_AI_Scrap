package machcinelearning;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.io.FileWriter;
import java.io.IOException;

public class LoadDataForApriori{
	
	
	public static Connection creat_connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver ok ");
			String url="jdbc:mysql://localhost:3306/JobsScraper";
			String user="root";
			String passwd="guillaume";
			Connection con=DriverManager.getConnection(url,user,passwd);
			System.out.println("connexion ok");
			return con;
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
	}
	


	public static void main(String[] args) throws SQLException {
		try {
		FileWriter myWriter = new FileWriter("/home/ryota/Desktop/CRUD/Java_Desktop/association_data.arff");
		String row,domaine;
		Connection con=creat_connection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select requirements,domaine from jobs");
		while(rs.next()) {
			row = rs.getString("requirements");
			domaine = rs.getString("domaine");
			domaine=domaine.replace(" ","_");
			
		
			row=row.replace(" ",",");
			row=row.replace("0","?");
//			row=row.replace("1","true");
			row=row.substring(0, row.length()-1);
		//	if(!domaine.equals("Informatique")) {
			
			 myWriter.write(row+"\n");
		      
		      System.out.println("done !");
//			}else {
//				continue;
//			}
		    }
	myWriter.flush();
		myWriter.close();
		
	}  
		 catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}

}
