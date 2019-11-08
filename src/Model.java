import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Utils;
import weka.core.Instances;

import java.io.FileReader;
import java.util.Enumeration;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class Model  {

    public Instances data;

    public Model(String file) throws Exception {
        this.data = new Instances(new FileReader("C:\\Users\\ASUS X541U\\Documents\\HEART_Stat.txt"));
    }

    public double getMean(String attribute){
        Attribute att = this.data.attribute(attribute);
        System.out.println(this.data.meanOrMode(att));
        System.out.println(this.data.numAttributes());
        return this.data.meanOrMode(att);
    }

    public double getMean(Attribute attribute){
        System.out.println(this.data.meanOrMode(attribute));

        return this.data.meanOrMode(attribute);
    }


    public int getAttIndex(String attribute){
        Enumeration<Attribute> attributeEnumeration = this.data.enumerateAttributes();
        Attribute attr = this.data.attribute(attribute);
        int index = 1;
        while (attributeEnumeration.hasMoreElements())
        {
            Attribute a = attributeEnumeration.nextElement();
            if (a.equals(attr)){
                index = a.index()+1;
            }
        }
        return index;

    }

    public double getMedian(String attribute){
        int index = this.getAttIndex(attribute);
        double[] col = this.data.attributeToDoubleArray(index);
        return Utils.kthSmallestValue(col,col.length/2);
    }

    public double[][] matrixCorrelation(){
        int size=this.data.numAttributes();
        double [][] corrMat = new double[size][size];
        for (int i=0; i < this.data.numAttributes(); i++) {
            double[] col1 = this.data.attributeToDoubleArray(i);
            for (int j=0; j < this.data.numAttributes(); j++) {
                double[] col2 = this.data.attributeToDoubleArray(j);
                corrMat[i][j] = Utils.correlation(col1,col2,col1.length);
                System.out.print(corrMat[i][j]+" ");
                double[] col = this.data.attributeToDoubleArray(i);
            }
            System.out.print("\n");
        }
        return corrMat;
    }

    public double firstQ(String attribute){
        int index = this.getAttIndex(attribute);
        double[] col = this.data.attributeToDoubleArray(index);
        return Utils.kthSmallestValue(col,col.length/4);
    }

    public double lastQ(String attribute){
        int index = this.getAttIndex(attribute);
        double[] col = this.data.attributeToDoubleArray(index);
        return Utils.kthSmallestValue(col,3*col.length/4);
    }

    public boolean isSymetrical(String attribute){
        if (Math.abs(this.getMedian(attribute) - this.getMean(attribute))<=1) return true;
        return false;
    }

    public void summary(){
        System.out.println(this.data.toSummaryString());
    }

}
