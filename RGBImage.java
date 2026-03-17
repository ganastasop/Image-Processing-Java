public class RGBImage implements Image {
    static final int MAX_COLORDEPTH = 255;
    int width;
    int height;
    int colordepth;
    RGBPixel[][] pixels;
    
    public RGBImage(){};
    
    public RGBImage(int width, int height, int colordepth) {
        this.width = width;
        this.height = height;
        this.colordepth = colordepth;
        this.pixels = new RGBPixel[height][width];
    }
    
    public RGBImage(RGBImage copyImg) {
        this.width = copyImg.width;
        this.height = copyImg.height;
        this.colordepth = copyImg.colordepth;
        this.pixels = copyImg.pixels;
    }
    
    public RGBImage(YUVImage YUVImg) {
        this.width = YUVImg.width;
        this.height = YUVImg.height;
        this.colordepth = 255;
        this.pixels = new RGBPixel[height][width];
        for (int i = 0; i < height; i++) {
            for  (int j = 0; j < width; j++) {
                this.pixels[i][j] = new RGBPixel(YUVImg.pixels[i][j]);
            }
        }
    }
    
    int getWidth() {
        return width;
    }
    
    int getHeight() {
        return height;
    }
    
    int getColorDepth() {
        return colordepth;
    }
    
    RGBPixel getPixel(int row, int col) {
        return pixels[row][col];
    }
    
    void setPixel(int row, int col, RGBPixel pixel) {
        pixels[row][col] = new RGBPixel(pixel);
    }
    
    public void grayscale() {
        short red, green, blue;
        for (int i = 0; i < height; i++) {
            for  (int j = 0; j < width; j++) {
                red = pixels[i][j].getRed();
                green = pixels[i][j].getGreen();
                blue = pixels[i][j].getBlue();
                short gray = (short)(red * 0.3 + green * 0.59 + blue * 0.11);
                pixels[i][j].setRGB(gray, gray, gray);
            }
        }
    }
    
    public void doublesize() {
        RGBImage doubleSize = new RGBImage(width*2, height*2, MAX_COLORDEPTH);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                doubleSize.pixels[2*i][2*j] = new RGBPixel(pixels[i][j]);
                doubleSize.pixels[2*i+1][2*j] = new RGBPixel(pixels[i][j]);
                doubleSize.pixels[2*i][2*j+1] = new RGBPixel(pixels[i][j]);
                doubleSize.pixels[2*i+1][2*j+1] = new RGBPixel(pixels[i][j]);
            }
        }
        height = doubleSize.height;
        width = doubleSize.width;
        pixels = doubleSize.pixels;
    }
    
    public void halfsize() {
        RGBImage halfSize = new RGBImage(width/2, height/2, MAX_COLORDEPTH); 
        for (int i = 0; i < height/2; i++) {
            for (int j = 0; j < width/2; j++) {
                int red = 0, green = 0, blue = 0;
                red += pixels[2*i][2*j].getRed();
                red += pixels[2*i+1][2*j].getRed();
                red += pixels[2*i][2*j+1].getRed();
                red += pixels[2*i+1][2*j+1].getRed();
                red = red/4;
                green += pixels[2*i][2*j].getGreen();
                green += pixels[2*i+1][2*j].getGreen();
                green += pixels[2*i][2*j+1].getGreen();
                green += pixels[2*i+1][2*j+1].getGreen();
                green = green/4;
                blue += pixels[2*i][2*j].getBlue();
                blue += pixels[2*i+1][2*j].getBlue();
                blue += pixels[2*i][2*j+1].getBlue();
                blue += pixels[2*i+1][2*j+1].getBlue();
                blue = blue/4;
                halfSize.pixels[i][j] = new RGBPixel((short)(red), (short)(green), (short)(blue));
            }
        }
        height = halfSize.height;
        width = halfSize.width;
        pixels = halfSize.pixels;
    }
    
    public void rotateClockwise() {
        RGBImage rotate = new RGBImage(height, width, MAX_COLORDEPTH);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotate.setPixel(j, i, getPixel(i, j));
            }
        }
        for (int i = 0; i < rotate.height; i++) {
            for (int j = 0; j < rotate.width / 2; j++) {
                RGBPixel temp = rotate.getPixel(i, j);
                rotate.setPixel(i, j, rotate.getPixel(i, rotate.width - 1 - j));
                rotate.setPixel(i, rotate.width - j - 1, temp);
            }
        }
        height = rotate.height;
        width = rotate.width;
        pixels = rotate.pixels;
    }
}
