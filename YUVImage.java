import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class YUVImage {
    int height;
    int width;
    YUVPixel[][] pixels;
    
    public YUVImage(int width, int height) {
        this.height = height;
        this.width = width;
        this.pixels = new YUVPixel[height][width];
    }
    
    public YUVImage(YUVImage copyImg) {
        this.height = copyImg.height;
        this.width = copyImg.width;
        this.pixels = copyImg.pixels;
    }
    
    public YUVImage(RGBImage RGBImg) {
        this.height = RGBImg.height;
        this.width = RGBImg.width;
        this.pixels = new YUVPixel[RGBImg.height][RGBImg.width];
        for (int i = 0; i < RGBImg.height; i++) {
            for (int j = 0; j < RGBImg.width; j++) {
                this.pixels[i][j] = new YUVPixel(RGBImg.pixels[i][j]);
            }
        }
    }
    
    public YUVImage(java.io.File file) throws java.io.FileNotFoundException, UnsupportedFileFormatException {
        int number;
        Scanner sc = new Scanner(file);
        String data = sc.nextLine();
        if (!data.equals("YUV3")) {
            throw new UnsupportedFileFormatException();
        }
        this.width = sc.nextInt();
        this.height = sc.nextInt();
        this.pixels = new YUVPixel[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                number = sc.nextInt();
                short Y = (short)(number);
                number = sc.nextInt();
                short U = (short)(number);
                number = sc.nextInt();
                short V = (short)(number);
                this.pixels[i][j] = new YUVPixel(Y, U, V);
            }
        }       
    }
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("YUV3\n%d %d\n", width, height));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(String.format("%d %d %d\n", pixels[i][j].getY(), 
                        pixels[i][j].getU(), pixels[i][j].getV()));
            }
        }
        return sb.toString();
    }
        
    public void toFile(java.io.File file) {
        try {
        FileWriter writer = new FileWriter(file);
        String string = this.toString();
        writer.write(string);
        writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
    
    public void equalize() {
        Histogram histogram = new Histogram(this);
        histogram.equalize();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                short old = this.pixels[i][j].getY();
                this.pixels[i][j].setY((short)histogram.histogram[old]);
            }
        }
    }
}
