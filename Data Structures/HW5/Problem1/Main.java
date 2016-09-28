import java.io.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CitySpanner span = new CitySpanner();
		try {
			span = new CitySpanner("resources/cityxy.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		span.printCityData();
		
	}

}
