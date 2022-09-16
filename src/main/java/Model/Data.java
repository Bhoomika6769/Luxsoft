package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Data {

    Set<Character> vowels = new HashSet<>();
    Integer wordCount;
    Integer numberOfVowels;
    public Data(){}
    public Data(Set<Character> vowels, Integer wordCount, Integer numberOfVowels) {
        this.vowels = vowels;
        this.wordCount = wordCount;
        this.numberOfVowels = numberOfVowels;
    }

    public Set<Character> getVowels() {
        return vowels;
    }

    public void setVowels(Set<Character> vowels) {
        this.vowels = vowels;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getNumberOfVowels() {
        return numberOfVowels;
    }

    public void setNumberOfVowels(Integer numberOfVowels) {
        this.numberOfVowels = numberOfVowels;
    }
}
