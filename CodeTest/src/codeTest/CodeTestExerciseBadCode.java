package codeTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CodeTestExerciseBadCode {

	public CodeTestExerciseBadCode(){
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CodeTestExerciseBadCode part2 = new CodeTestExerciseBadCode();
		System.out.println(part2.readFileFixed());		
	}

	public String readFile(){
		// Observations can be seen as comments
		File f = null; // poor variable names
		FileReader fr = null; // not following java convention for variables, fR in place of fr
		StringBuffer content = null;
		try{
			f = new File("C:/Users/ankuyada/Desktop/samplefile.txt");//new File("c:/samplefile.txt");
			fr = new FileReader(f);
			
			int c;	
			// buffered reading would be encouraged, either character buffer of a specific size or read line by line.
			while((c = fr.read()) != -1){				
				// initialise variables out of while loop.
				if(content == null){
					content = new StringBuffer();
				}
				
				content.append((char)c); // bad type casting practice, it can be improved by using Character.toChars(c) instead
				
			}
			
			fr.close();	// Resource leak: leaving a handle is wrong here if any of the code above throws exception, this line will not executed, better put this in finally block.	
		}
		catch (Exception e) { // catching generic exception, better to specify which exception to expect.
			throw new RuntimeException("An error occured reading your file"); // bug: why need runtime exception? the exception handling mechanism is needed so the program can recover if such problems occur 
		}		
		
		return content.toString();
	}
	
	public String readFileFixed(){
		File aFile = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer fileContent = null;
		
		try {
		    //use buffering, reading one line at a time
			aFile = new File("C:/Users/ankuyada/Desktop/samplefile.txt");
			
			if(!aFile.isFile()){
				System.out.println("Specified location - " + aFile.getAbsolutePath() + " is not a file.");
				return "";
			}
			else if (aFile.length() > 0){
				System.out.println("Specified file - " + aFile.getAbsolutePath() + " is empty");
				return "";
			}
			
			fileReader = new FileReader(aFile);  
			bufferedReader =  new BufferedReader(fileReader);
		      try {
		        String line = null; 
		        
		        if(fileContent == null){
					fileContent = new StringBuffer();
				}
		        
		        while (( line = bufferedReader.readLine()) != null){
		          fileContent.append(line);
		          // Since readLine removes the new line while reading, we need to add it by ourselves. 
		          fileContent.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		    	  if(bufferedReader != null){
		    		  bufferedReader.close();
		    	  }
		      }						
		    }
		    catch (FileNotFoundException fileNotFound){
		      System.out.println("File not found: Exception message: " + fileNotFound.getMessage());
		      System.out.println("Exception trace: " + fileNotFound.getStackTrace());
		    }
		    catch (IOException ioException){
		      System.out.println("File cannot be read: Exception message: " + ioException.getMessage());
		      System.out.println("Exception trace: " + ioException.getStackTrace());
		    }
			catch (Exception exception){
		      System.out.println("Generic exception: Exception message: " + exception.getMessage());
		      System.out.println("Exception trace: " + exception.getStackTrace());
		    }
		    return fileContent.toString();
		  }
}
