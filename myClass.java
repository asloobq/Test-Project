/*Author: Asloob Qureshi
 * Date:18/01/2011
 * Sapna Solutions Freshers - Software Development Task
  * */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class myClass {

	void breadCrumb(String fname, String keyString) throws IOException
	{
		//Open the file
		FileInputStream fstream = new FileInputStream(fname);
		fstream.skip(339); //initial text formatting characters
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in));
		String strLine;
	
		//categories will be named as Main , Sub and Adjunct, starting from top to bottom
		String curMainStr = new String();	//stores current Main Category
		String curSubStr = new String();	//stores current Sub category

		//Read File Line By Line
		while ((strLine = br2.readLine()) != null)   {

			if(strLine.startsWith("|"))	//whether its Sub category
			{
				//check if match found
				String temp=strLine.substring(1, strLine.length()-1); //extract name without '|'and '/'
				curSubStr=temp; //store name of sub category for future use
				if(temp.equalsIgnoreCase(keyString)) //check if category name matches key
				{
					System.out.println(" Sub Category Found!!!");
					System.out.println(" Path is " + curMainStr + " >> " + temp);
					return;
				}
			}
			else if (strLine.startsWith("**"))	//whether its adjunct category
			{
				//check if match found
				String temp=strLine.substring(2, strLine.length()-1); //extract name without '**'and '/'
				if(temp.equalsIgnoreCase(keyString)) //check if category name matches key
				{
					System.out.println(" Adjunct Category Found!!!");
					System.out.println(" Path is "  + curMainStr + " >> " + curSubStr + " >> " + temp);
					return;
				}
			}
			else // it belongs to Main Category
			{
				String temp=strLine.substring(0, strLine.length()-1); //extract name without '/'
				curMainStr=temp; //store name of main category for future use
				if(temp.equalsIgnoreCase(keyString)) //check if category name matches key
				{
					System.out.println(" Main Category Found!!!");
					System.out.println(" Path is "  + curMainStr );
					return;
				}
			}
		}
		System.out.println("Category Not Found");

		in.close(); //Close the input stream
	}
	
	public static void main(String[] args) throws IOException{

		//Store pathname of text file to be read.
		String fname="C:\\Users\\AsloobProject\\workspace\\myProj\\src\\category.txt";

		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the category name ");
		String keyString=br1.readLine();
		
	myClass myClassObj =	new myClass();
	myClassObj.breadCrumb(fname, keyString);
		
	} 
}