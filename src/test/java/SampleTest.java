import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;


public class SampleTest {

    HashSet<String> sampleSet;

    @Before
    public void setUp() throws Exception {
        // initialize all the variables required for testing
        // in our case the sample object

        sampleSet = new HashSet<>();
        sampleSet.add("test (4)");
        sampleSet.add("test1 (5)");
        sampleSet.add("test2 (6)");
        sampleSet.add("test3 (7)");

    }

    @After
    public void tearDown() throws Exception {
        // remove all the references after testing
        sampleSet.clear();
        sampleSet = null;
    }

    @Test
    public void constructorTest(){
        Sample sample1 = new Sample();
        Sample sample2 = new Sample(sampleSet);


        // sample 1 => Constructor initializes a empty set. so we check if it is true
        Assert.assertTrue(sample1.mySet.isEmpty());
        // sample 2 => Constructor initializes with provided sample set. so we check if size is equal after initialization
        Assert.assertEquals(sampleSet.size(), sample2.getSize());
    }

    @Test
    public void addToMySet() throws Exception {
        Sample sample1 = new Sample();
        Assert.assertTrue(sample1.addToMySet("test")); // should return true as test is added to set
        //check if the value is inserted in proper format
        Assert.assertEquals(1, sample1.getSize()); // should be equal
        // check if text inserted is properly formatted
        Assert.assertTrue(sample1.mySet.contains("test (4)"));

        // inserting "test" again should return false as it was already added before
        Assert.assertFalse(sample1.addToMySet("test"));

        //test similarly for second insert
        Assert.assertTrue(sample1.addToMySet("test1")); // should return true as test is added to set
        //check if the value is inserted in proper format
        Assert.assertEquals(2, sample1.getSize()); // should be equal
        // check if text inserted is properly formatted
        Assert.assertTrue(sample1.mySet.contains("test1 (5)"));
    }

    @Test
    public void removeFromMySet() throws Exception {
        Sample sample = new Sample(sampleSet);
        Assert.assertTrue(sample.removeFromMySet("test")); // should return true as test exists on the sample set
        Assert.assertEquals(3, sample.getSize()); // after removing the total count should be 3
        Assert.assertFalse(sample.removeFromMySet("sample")); // should return false as sample does not exists on the sample set
    }

    @Test
    public void getSize() throws Exception {
        // check if the size returned is the actual size
        Sample sample = new Sample(sampleSet);
        Assert.assertEquals(4, //expected value as we had added 4 items
                sample.getSize() // actual return value of the function
        );
    }

}