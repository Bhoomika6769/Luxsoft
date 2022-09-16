import Model.Data;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Driverlogic {
    public void getAverageVowel() throws IOException {
        String path = "C:\\Users\\bhojoshi\\Desktop\\Luxsoft\\src\\main\\resources\\input";
        String content = readFile(path);
        String output="";
        Map<Integer, Data> mapOfWords = populateData(content);
        for (Map.Entry<Integer, Data> entry : mapOfWords.entrySet()) {
            double value = (double) entry.getValue().getNumberOfVowels() / entry.getValue().getWordCount();
            output += "(" + entry.getValue().getVowels() + "," + entry.getKey() + ")->" + value+"\n";
            writeToFile(output);
        }
        System.out.print(
                "File is created successfully with the content.");

    }

     private void writeToFile(String output)  {
        try{
            BufferedWriter f_writer
                    = new BufferedWriter(new FileWriter(
                    "C:\\Users\\bhojoshi\\Desktop\\Luxsoft\\src\\main\\resources\\output"));
            f_writer.write(output);
            f_writer.write("\r\n");
            f_writer.close();
        }catch (IOException e){
            e.getStackTrace();

        }

    }
     public boolean isVowel(char chars) {
        if (chars == 'a' || chars == 'e' || chars == 'i'
                || chars == 'o' || chars == 'u' ) {
            return true;
        } else {
            return false;
        }
    }
    public  String readFile(String path) throws FileNotFoundException {
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
            throw new FileNotFoundException("File specified doesn't exist"+e.getMessage());
        }catch (IOException e){
            e.getStackTrace();
        }
        return content;
    }


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
