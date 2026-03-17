import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PPMImage extends RGBImage {
    public PPMImage(java.io.File file) throws java.io.FileNotFoundException, UnsupportedFileFormatException {
        Scanner sc = new Scanner(file);
        String data = sc.nextLine();
        if (!data.equals("P3")) {
            throw new UnsupportedFileFormatException();
        }
        int number = sc.nextInt();
        this.width = number;
        number = sc.nextInt();
        this.height = number;
        number = sc.nextInt();
        this.colordepth = number;
        this.pixels = new RGBPixel[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                number = sc.nextInt();
                short red = (short)(number);
                number = sc.nextInt();
                short green = (short)(number);
                number = sc.nextInt();
                short blue = (short)(number);
                this.pixels[i][j] = new RGBPixel(red, green, blue);
            }
        }
    }
    
    public PPMImage(RGBImage img) {
        this.width = img.width;
        this.height = img.height;
        this.colordepth = img.colordepth;
        this.pixels = img.pixels;
    }
    
    public PPMImage(YUVImage img) {
        this.width = img.width;
        this.height = img.height;
        this.colordepth = 255;
        this.pixels = new RGBPixel[height][width];
        for (int i = 0; i < height; i++) {
            for  (int j = 0; j < width; j++) {
                this.pixels[i][j] = new RGBPixel(img.pixels[i][j]);
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("P3\n%d %d %d\n", width, height, colordepth));       
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(String.format("%d %d %d\n", pixels[i][j].getRed(), 
                          pixels[i][j].getGreen(), pixels[i][j].getBlue()));
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
}
