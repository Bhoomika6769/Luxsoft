import Model.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @InjectMocks
    Driverlogic drivelogic = new Driverlogic();


    @Test
    public void readFileTest() throws IOException {
        String path = "C:\\Users\\bhojoshi\\Desktop\\Luxsoft\\src\\main\\resources\\input";
        String content = drivelogic.readFile(path);
        Assert.assertEquals(content,"Platon made bamboO boats ");
    }

    @Test
    public void isVowelTest() throws IOException {

      boolean response =  drivelogic.isVowel('a');
      Assert.assertTrue(response);

    }
    @Test
    public void isNotVowelTest() throws IOException {
        boolean response =  drivelogic.isVowel('b');
        Assert.assertFalse(response);

    }

    @Test
    public  void populateDataTest(){
        String input = "Platon made bamboO boats";
        Map<Integer, Data> response = drivelogic.populateData(input);
        Map<Integer, Data> expectedResponse = new HashMap<>();
        Data data = new Data();
        Set<Character> vowelsTest = new HashSet<>('a','e');
        data.setVowels(vowelsTest);
        data.setWordCount(1);
        data.setNumberOfVowels(2);
        expectedResponse.put(4,data);
        Set<Character> vowelsTestStr2 = new HashSet<>('a','o');
        data.setVowels(vowelsTestStr2);
        expectedResponse.put(5,new Data(vowelsTest,1,2));
        expectedResponse.put(6,new Data(vowelsTest,2,5));

        Assert.assertEquals(expectedResponse.keySet(),response.keySet());

    }

}
