import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Project 
{
//	public ArrayList<String> query1
	public static void main(String[] args) 
	{
		try
		{
			Class.forName("java.sql.DriverManager");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital?useSSL=false","root","aarushi");
			Statement stmpt = (Statement)con.createStatement();
			System.out.println("Query 1 -  Names of all female patients who have age>30 and have Typhoid");
			String query1 = "select FName,LName from Patient p,Patient_Details d where p.Age>30 AND d.Ailment='Typhoid' AND d.Pid=p.Pid AND p.Gender='F'";
			ResultSet rs1 = stmpt.executeQuery(query1);
			while(rs1.next())
			{
				System.out.println(rs1.getString("Fname")+" "+rs1.getString("LName"));
			}
			
			System.out.println("");
			
			System.out.println("Query 2 -  Names of all patients assigned to a Doctor with Last name Garg");
			
			String query2 = "select p.FName,p.LName from Patient p,Patient_Details d,Doctor doc where doc.LName='Garg' AND doc.Did=d.Did AND d.Pid=p.Pid";
			ResultSet rs2 = stmpt.executeQuery(query2);
			while(rs2.next())
			{
				System.out.println(rs2.getString("Fname")+" "+rs2.getString("LName"));
			}
			
			System.out.println("");
			
			System.out.println("Query 3 -  Find Phone numbers and relations of all guardians of patient with PID 6 ");
			String query3 = "select g.Phone_Number,d.relation from Guardian g,Dependent d where d.Pid=6 AND g.Gid=d.Gid";
			ResultSet rs3 = stmpt.executeQuery(query3);
			while(rs3.next())
			{
				System.out.println(rs3.getString(1)+" "+rs3.getString(2));
			}
			
			System.out.println("");
			System.out.println("Query 4 -  Find the pid of patient with maximum age");
			String query4 = "select pid from Patient  where age = (Select max(age) from Patient)";
			ResultSet rs4 = stmpt.executeQuery(query4);
			while(rs4.next())
			{
				System.out.println(rs4.getString(1));
			}
			
			System.out.println("");
			System.out.println("Query 5 - Find the Dids of all doctors having salary > avg salary ");
			String query5 = "select did from Doctor_details  where salary > (Select avg(salary) from Doctor_details)";
			ResultSet rs5 = stmpt.executeQuery(query5);
			while(rs5.next())
			{
				System.out.println(rs5.getString(1));
			}
			
			
			System.out.println("");
			System.out.println("Query 6 - Find specialization of Doctors whose time slot is either morning or evening");
			String query6 = "select det.specialization from Doctor d, Doctor_details det where d.did = det.did AND (det.time_slot='Morning' OR det.time_slot='Evening')";
			ResultSet rs6 = stmpt.executeQuery(query6);
			while(rs6.next())   
			{
				System.out.println(rs6.getString(1));
			}
			
			System.out.println("");
			System.out.println("Query 7 - Find Did, Name, salaray of all doctors having experience between 15 to 20 years - range");
			String query7 = "select d.did,d.FName,d.LName,det.salary from Doctor d, Doctor_details det where d.did = det.did AND det.experience BETWEEN 15 AND 20";
			ResultSet rs7 = stmpt.executeQuery(query7);
			while(rs7.next())   
			{
				System.out.println(rs7.getString(1)+" "+rs7.getString(2)+" "+rs7.getString(3)+" "+rs7.getString(4));
			}	
			
			System.out.println("");
			System.out.println("Query 8 - Find specialization of doctor treating patient with name Arjun");
			String query8 = "select det_d.specialization from Patient p, Patient_Details det_p,Doctor_details det_d where det_p.did = det_d.did AND p.pid = det_p.pid AND p.Fname='Arjun'";
			ResultSet rs8 = stmpt.executeQuery(query8);
			while(rs8.next())   
			{
				System.out.println(rs8.getString(1));
			}	
			
			System.out.println("");
			System.out.println("Query 9 -Find the Pid of patient who have blood gp O+");
			String query9 = "select pid from Patient_Details det_p where det_p.blood_group='O+'";
			ResultSet rs9 = stmpt.executeQuery(query9);
			while(rs9.next())   
			{
				System.out.println(rs9.getString(1));
			}	
			
			System.out.println("");
			System.out.println("Query 10 - Display the details of the doctor having second maximum experience");
			String query10 = "select d.did, d.Fname, d.Lname from Doctor d,Doctor_details det where d.did=det.did AND det.experience IN (select max(experience) from doctor_details det1 where det1.experience NOT IN (select max(experience) from Doctor_details))";
			ResultSet rs10 = stmpt.executeQuery(query10);
			while(rs10.next())   
			{
				System.out.println(rs10.getString(1)+" "+rs10.getString(2)+" "+rs10.getString(3));
			}	
			
			System.out.println("");
			System.out.println("Query 11 - Increase salary of General Physicians by 10000");
			String query11 = "UPDATE Doctor_details SET salary = salary + 10000";
			stmpt.executeUpdate(query11);
			
			
			System.out.println("");
			System.out.println("Query 12 - Find Gids and corresp Pid of guardians who are either mother or father");
			String query12 = "select g.gid,g.pid from Dependent g where g.relation='Mother' OR g.relation='Father'";
			ResultSet rs12 = stmpt.executeQuery(query12);
			while(rs12.next())   
			{
				System.out.println(rs12.getString(1)+" "+rs12.getString(2));
			}	
			
			
			
			
			
		}
		
		catch(Exception e)
		{
			 e.printStackTrace();
		}		
		

	}
	

}
