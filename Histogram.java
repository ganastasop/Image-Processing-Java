import java.io.FileWriter;
import java.io.IOException;


public class Histogram {
    int[] histogram;
    double totalPixels;
    
    public Histogram(YUVImage img) {
        this.histogram = new int[256];
        for (int i = 0; i < img.height; i++) {
            for (int j = 0; j < img.width; j++) {
                this.histogram[img.pixels[i][j].getY()]++;
            }
        }
        this.totalPixels = img.height * img.width;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 236; i++) {
            sb.append(String.format("\n%3d.(%4d)\t", i, histogram[i]));
            int thousands = histogram[i] / 1000;
            sb.append("#".repeat(thousands));
            int hundreds = (histogram[i] % 1000) / 100;
            sb.append("$".repeat(hundreds));
            int tens = (histogram[i] % 100) / 10;
            sb.append("@".repeat(tens));
            int units = histogram[i] % 10;
            sb.append("*".repeat(units));
        }
        sb.append("\n");
        return sb.toString();
    }
    
    public void toFile(java.io.File file) {
        try {
            FileWriter writer = new FileWriter(file);
            String line = toString();
            writer.write(line);
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
    
    public void equalize() {
        double[] P = new double[256];
        for (int i = 0; i < 256; i++) {
            P[i] = (double)(this.histogram[i] / totalPixels);
        }
        
        double[] CDF = new double[256];
        CDF[0] = P[0];
        for (int i = 1; i < 256; i++) {
            CDF[i] = CDF[i - 1] + P[i];
        }
        
        int maxLuminosity = 235;
        for (int i = 0; i < 256; i++) {
            this.histogram[i] = (int)(CDF[i] * maxLuminosity);
        }       
    }
}
