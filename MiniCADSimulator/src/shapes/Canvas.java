package shapes;

import java.awt.image.BufferedImage;

public class Canvas {
  public static int hashClr;
  public static int height = 0;
  public static int width = 0;

  private static Canvas canvasObject;

  private Canvas(int cvHeight, int cvWidth, int cvHashClr) {
    height = cvHeight;
    width = cvWidth;
    hashClr = cvHashClr;
  }

  public Canvas getCanvas(int height, int width, int hashClr) {
    canvasObject = new Canvas(height, width, hashClr);
    return canvasObject;
  }
  
  /** {@DocRoot}.
   * 
   */
  public static void computeCanvas(BufferedImage img) {
    for (int i = 0; i < height; i ++) {
      for (int j = 0; j < width; j ++) {
        img.setRGB(j, i, hashClr);
      }
    }
  }

  public boolean notExceeded() {
    return true;
  }
}