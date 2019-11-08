import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import java.io.FileReader;
import java.util.Arrays;
public class test {

    public static void main(String[] args) throws Exception {
        Model dataset = new Model("C:\\Users\\ASUS X541U\\Documents\\HEART_Stat.txt");
        //dataset.summary();
        System.out.println(dataset.getMean("class"));
        dataset.matrixCorrelation();

    }
}
