package machcinelearning;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class LoadJavaForClassifier {

	public static void main(String[] args) throws SQLException {
		try {
		FileWriter myWriter = new FileWriter("/home/ryota/Desktop/CRUD/Java_Desktop/classify/classificationdata.arff");
		String row,domaine;
		Connection con=LoadDataForApriori.creat_connection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select requirements,domaine from Jobs");
		while(rs.next()) {
			row = rs.getString("requirements");
			domaine = rs.getString("domaine");
			domaine=domaine.replace(" ","");
			
		
			row=row.replace(" ",",");
			
			row=row.substring(0, row.length()-1);
		if(!domaine.equals("Informatique")) {
			
			 myWriter.write(row+","+domaine+"\n");
		      
		      System.out.println("done !");
			}else {
				continue;
			}
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
