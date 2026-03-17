public class YUVPixel {
    short Y;
    short U;
    short V;
    
    public YUVPixel(short Y, short U, short V) {
        this.U = U;
        this.V = V;
        this.Y = Y;
    }
    
    public YUVPixel(YUVPixel pixel) {
        this.Y = pixel.Y;
        this.U = pixel.U;
        this.V = pixel.V;
    }
    
    public YUVPixel(RGBPixel pixel) {
        this.Y = (short)(((66 * pixel.getRed() + 129 * pixel.getGreen()
                  + 25 * pixel.getBlue() + 128) >> 8) + 16);
        this.U = (short)(((-38 * pixel.getRed() - 74 * pixel.getGreen()
                  + 112 * pixel.getBlue() + 128) >> 8) + 128);
        this.V = (short)(((112 * pixel.getRed() - 94 * pixel.getGreen()
                  - 18 * pixel.getBlue() + 128) >> 8) + 128);
    }
    
    short getY() {
        return Y;
    }
    
    short getU() {
        return U;
    }
    
    short getV() {
        return V;
    }
    
    void setY(short Y) {
        this.Y = Y;
    }
    
    void setU(short U) {
        this.U = U;
    }
    
    void setV(short V) {
        this.V = V;
    }
}
