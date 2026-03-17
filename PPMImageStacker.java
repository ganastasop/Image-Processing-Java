import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PPMImageStacker {
    List<PPMImage> flist;
    PPMImage stacked;
    
    public PPMImageStacker(java.io.File dir) throws java.io.FileNotFoundException,
                           UnsupportedFileFormatException{
        if (!dir.exists())
            throw new FileNotFoundException("[ERROR] Directory " + dir + " does not exist!");
        if (dir.isFile()) 
            throw new FileNotFoundException("[ERROR] " + dir + " is not a directory!");
        this.flist = new ArrayList<>();
        File[] farray = dir.listFiles();
        
        try {
            stacked = new PPMImage(farray[0]);
            for (int i = 0; i < farray.length; i++) { 
                PPMImage image = new PPMImage(farray[i]);
                this.flist.add(image);
            }
        }
        catch (UnsupportedFileFormatException | FileNotFoundException e) {
            throw new UnsupportedFileFormatException("");
        }
    }
    
    public void stack() {
        int[][] reds = new int[stacked.height][stacked.width];
        int[][] greens = new int[stacked.height][stacked.width];
        int[][] blues = new int[stacked.height][stacked.width];
        for (int i = 0; i < flist.size(); i++) {
            for (int j = 0; j < stacked.height; j++) {
                for (int k = 0; k < stacked.width; k++) {
                    reds[j][k] = reds[j][k] + flist.get(i).pixels[j][k].getRed();
                    greens[j][k] = greens[j][k] + flist.get(i).pixels[j][k].getGreen();
                    blues[j][k] = blues[j][k] + flist.get(i).pixels[j][k].getBlue();
                }
            }
        }
        for (int j = 0; j < stacked.height; j++) {
            for (int k = 0; k < stacked.width; k++) {
                stacked.pixels[j][k].setRed((short)(reds[j][k]/flist.size()));
                stacked.pixels[j][k].setGreen((short)(greens[j][k]/flist.size()));
                stacked.pixels[j][k].setBlue((short)(blues[j][k]/flist.size()));
            }
        }
    }
    
    public PPMImage getStackedImage() {
        return stacked;
    }
}
