package Service;

import Model.Data;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Driver logic is responsible to perform all the tasks
 */
public class DriverService {
    static Logger logger = Logger.getLogger(DriverService.class.getName());
static  final  String INPUT_FILE = "C:\\Users\\bhojoshi\\Desktop\\Luxsoft\\src\\main\\resources\\input";
    static  final  String OTPUT_FILE ="C:\\Users\\bhojoshi\\Desktop\\Luxsoft\\src\\main\\resources\\output";    /**
     * This method calculates the average
     * @throws IOException
     */
    public void getAverageVowel() throws IOException {
        logger.info("Inside getAverageVowel method");
        String path = INPUT_FILE;
        String content = readFile(path);
        String output="";
        if(content !=null){
            Map<Integer, Data> mapOfWords = populateData(content);
            for (Map.Entry<Integer, Data> entry : mapOfWords.entrySet()) {
                double value = (double) entry.getValue().getNumberOfVowels() / entry.getValue().getWordCount();
                output += "(" + entry.getValue().getVowels() + "," + entry.getKey() + ")->" + value+"\n";
                writeToFile(output);
            }
            System.out.print(
                    "File is created successfully with the content.");
        }else{
            logger.info("File doesn't have any data");
        }
    }

    /**
     * This method is responsible to write output in file
     * @param output
     */
     private void writeToFile(String output) throws IOException {
        try{
            BufferedWriter f_writer
                    = new BufferedWriter(new FileWriter(OTPUT_FILE));
            f_writer.write(output);
            f_writer.write("\r\n");
            f_writer.close();
        }catch (IOException e){
            logger.info("IOException occured");
            throw new IOException("IOException occured"+e.getMessage());

        }

    }

    /**
     * This method is use to validate Character is vowel or not
     * @param chars
     * @return
     */
     public boolean isVowel(char chars) {
        if (chars == 'a' || chars == 'e' || chars == 'i'
                || chars == 'o' || chars == 'u' ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is responsible for reading the file and returning file content
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public  String readFile(String path) throws IOException {
        File file = new File(path);
        String content = "";
        try {
            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                content+= str+" ";
            }
        } catch (FileNotFoundException e) {
            logger.info("File not found Exception occured");
            throw new FileNotFoundException("File specified doesn't exist"+e.getMessage());
        }catch (IOException e){
            logger.info("IOException occured");
            throw new IOException("IOException occured"+e.getMessage());
        }
        return content;
    }

    /**
     * This method sort and populate the data in map for further processing
     * @param content
     * @return
     */
    public  Map<Integer, Data> populateData(String content) {
        Map<Integer, Data> mapOfWords = new HashMap<>();
        String[] list = content.split(" ");
        for (int i = 0; i < list.length; i++) {
            int length = list[i].length();
            Data data = new Data();
            int count = 0;
            Set<Character> set = new HashSet<>();
            if (mapOfWords.containsKey(length)) {
                data= mapOfWords.get(length);
                count = data.getNumberOfVowels();
                char[] wordCharacters = list[i].toCharArray();
                for (int j = 0; j < wordCharacters.length; j++) {
                    if (isVowel(java.lang.Character.toLowerCase(wordCharacters[j]))) {
                        set.add(java.lang.Character.toLowerCase(wordCharacters[j]));
                        count++;
                    }
                }
                data.getVowels().addAll(set);
                data.setNumberOfVowels(count);
                data.setWordCount(data.getWordCount()+1);
            } else {
                data.setWordCount(1);
                char[] wordCharacters = list[i].toCharArray();
                for (int j = 0; j < wordCharacters.length; j++) {
                    if (isVowel(wordCharacters[j])) {
                        set.add(java.lang.Character.toLowerCase(wordCharacters[j]));
                        count++;
                    }
                }
                data.getVowels().addAll(set);
                data.setNumberOfVowels(count);
            }
            mapOfWords.put(list[i].length(), data);
        }
        return mapOfWords;
    }

}
