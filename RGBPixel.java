public class RGBPixel {
    int pixel;
    
    public RGBPixel(short red, short green, short blue) {
        this.pixel = 0;
        int tempRed = red << 16;
        int tempGreen = green << 8;
        this.pixel = tempRed | tempGreen | blue;
    }
    
    public RGBPixel(RGBPixel pixel) {
        this.pixel = pixel.pixel;
    }
    
    public RGBPixel(YUVPixel pixel) {
        short C = (short)(pixel.getY() - 16);
        short D = (short)(pixel.getU() - 128);
        short E = (short)(pixel.getV() - 128);
        setRed((short)(clip((298 * C + 409 * E + 128) >> 8)));
        setGreen((short)(clip((298 * C - 100 * D - 208 * E + 128) >> 8)));
        setBlue((short)(clip((298 * C + 516 * D + 128) >> 8)));
    }
    
    private short clip(int value) {
        if (value < 0) {
            return 0;
        }
        else if (value > 255) {
            return 255;
        }
        else
            return (short)value;
    }
    
    public short getRed() {
        short red = (short)(pixel >> 16);
        return red;
    }
    
    public short getGreen() {
        short green = (short)(pixel >> 8 & 0xFF);
        return green;
    }
    
    public short getBlue() {
        short blue = (short)(pixel & 0xFF);
        return blue;
    }
    
    public void setRed(short red) {
        int tempRed = red << 16;
        pixel = pixel & 0xFF00FFFF;
        pixel = pixel | tempRed;
    }
    
    public void setGreen(short green) {
        int tempGreen = green << 8;
        pixel = pixel & 0xFFFF00FF;
        pixel = pixel | tempGreen;
    }
    
    public void setBlue(short blue) {
        pixel = pixel & 0xFFFFFF00;
        pixel = pixel | blue;
    }
    public int getRGB() {
        short tempRed = getRed();
        short tempGreen = getGreen();
        short tempBlue = getBlue();
        return tempRed | tempGreen | tempBlue;
    }
    
    public void setRGB(int value) {
        short red = (short)(value >> 16);
        short green = (short)(value >> 8 & 0xFF);
        short blue = (short)(value & 0xFF);
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }
    
    public final void setRGB(short red, short green, short blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %d %d", getRed(), getGreen(), getBlue()));
        return sb.toString();
    }
}
