package SampleProgram1;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) 
	{
		JsonPath js = new JsonPath(payload.CoursePrice());
		 
		int count =js.getInt("courses.size()");
		
		System.out.println(count);
		
		String totalAmount= js.getString("dashboard.purchaseAmount");
		
		System.out.println(totalAmount);
		
		String titleFirstCourse= js.get("courses[0].title");
		
		System.out.println(titleFirstCourse);
		// Print all title and price
		System.out.println("_______________________________________________");
		
		for(int i=0; i<count;i++) 
		{
			String courseTitles= js.get("courses["+i+"].title");
			
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
						
		}
		
		System.out.println("____________________________________________________");
		
		// Print number copies soled by RPA
		
		System.out.println("Print number copies soled by RPA");
		for(int i=0; i<count;i++) 
		{
			String courseTitles= js.get("courses["+i+"].title");
			
			if (courseTitles.contentEquals("RPA"))
			{
				int copies =js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
			
		}
	}

}
