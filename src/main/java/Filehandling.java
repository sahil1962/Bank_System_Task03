
import java.io.FileWriter;   // Import the FileWriter class
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner; // Import the Scanner class to read text files


public class Filehandling {
    public void CreateFile(String Filename) throws Exception {
        try {
            File myObj = new File(Filename);
            if (myObj.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("ERROR!");
        }
    }

    public void WriteToFile(String Filename, String Data) throws Exception {
        try {
            FileWriter myWriter = new FileWriter(Filename, true);
            myWriter.write(Data);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("ERROR!");
        }
    }

    public int ReturnCountOfMatchingLine(String Filename, String value) throws Exception {
        int counter = 1;
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(value)) {
                    return counter;
                }
                counter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("ERROR!");
        }
        return counter;
    }

    public String ReturnStringOfMatchingCount(String Filename, int lineNumber) {
        int counter = 1;
        String ReturnString = "";
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (lineNumber == counter) {
                    ReturnString = data;
                    return ReturnString;
                }
                counter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return "";
    }

    public boolean ReturnBoleanOfMatchingString(String Filename, String value) {
        int counter = 1;
        String ReturnString = "";
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (value.equals(data)) {
                    return true;
                }
                counter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return false;
    }

    public void ReadAndWriteSepcificLine(String filePath, int lineNumber, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        int count = 1;
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                if ((count == lineNumber) && (oldString.equals(line))) {
                    int old = Integer.parseInt(oldString);
                    int newB = Integer.parseInt(newString);
                    String BalanceString = String.valueOf(old + newB);
                    oldContent = oldContent + BalanceString + System.lineSeparator();
                    line = reader.readLine();
                } else {
                    oldContent = oldContent + line + System.lineSeparator();
                    line = reader.readLine();
                }
            }
            String newContent = oldContent.replaceAll(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}





