package shapes;

import java.awt.image.BufferedImage;

public class Circle implements Visitable {
  public int xc;
  public int yc;
  public int rad;
  public int hashClr1;
  public int hashClr2;

  int crP;
  int crD;
  int crX;
  int crY;
  int crQ;

  /**{@docRoot}.
   * 
   */
  public Circle(int xc, int yc, int rad, int hashClr1, int hashClr2) {
    super();
    this.xc = xc;
    this.yc = yc;
    this.rad = rad;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
    crP = 0;
    crQ = rad;
    crD = 3 - 2 * rad;
    crX = xc;
    crY = yc;
  }

  /** {@inheritDoc}
   * 
   */
  public void accept(Draw specificShape) {
    specificShape.draw(this);
  }

  /** {@inheritDoc}
   * 
   */
  public boolean allowedToSet(int xx, int yy) {
    if ((xx >= 0 && xx < Canvas.width) && (yy >= 0 && yy < Canvas.height)) {
      return true;
    }
    return false;
  }

  /** {@inheritDoc}
   * 
   */
  public void bresenham_alg(BufferedImage img) {

    if (crP == 0) {
      if (allowedToSet(crX + crQ, crY)) {
        img.setRGB(crX + crQ, crY, hashClr1);
      }
      if (allowedToSet(crX - crQ, crY)) {
        img.setRGB(crX - crQ, crY, hashClr1);
      }
      if (allowedToSet(crX, crY + crQ)) {
        img.setRGB(crX, crY + crQ, hashClr1);
      }
      if (allowedToSet(crX, crY - crQ)) {
        img.setRGB(crX, crY - crQ, hashClr1);
      }
    }

    crP ++;
    if (crD < 0) {
      crD += 4 * crP + 6;
    } else {
      crQ --;
      crD += 4 * (crP - crQ) + 10;
    }

    drawCircleMeth(crX, crY, crP, crQ, img, hashClr1);

    if (crP < crQ) {
      bresenham_alg(img);
    }

  }

  /** {@inheritDoc}
   * 
   */
  public void drawCircleMeth(int crX, int crY, int crP, int crQ, BufferedImage img, int hashClr1) {
    if (allowedToSet(crX + crP, crY + crQ)) {
      img.setRGB(crX + crP, crY + crQ, hashClr1);
    }
    if (allowedToSet(crX - crP, crY + crQ)) {
      img.setRGB(crX - crP, crY + crQ, hashClr1);
    }
    if (allowedToSet(crX + crP, crY - crQ)) {
      img.setRGB(crX + crP, crY - crQ, hashClr1);
    }
    if (allowedToSet(crX - crP, crY - crQ)) {
      img.setRGB(crX - crP, crY - crQ, hashClr1);
    }
    if (allowedToSet(crX + crQ, crY + crP)) {
      img.setRGB(crX + crQ, crY + crP, hashClr1);
    }
    if (allowedToSet(crX - crQ, crY + crP)) {
      img.setRGB(crX - crQ, crY + crP, hashClr1);
    }
    if (allowedToSet(crX + crQ, crY - crP)) {
      img.setRGB(crX + crQ, crY - crP, hashClr1);
    }
    if (allowedToSet(crX - crQ, crY - crP)) {
      img.setRGB(crX - crQ, crY - crP, hashClr1);
    }
  }


  public Position calculateCentroid() {
    return new Position(xc, yc);
  }

  int xfill;
  
  /** {@inheritDoc}
   * 
   */
  public void floodFill(BufferedImage img) {
    for (int i = -rad + 1; i <= rad - 1; i ++) {
      xfill = xc;
      if (allowedToSet(xfill, yc + i) == true) {
        while (img.getRGB(xfill, yc + i) != hashClr1) {
          if (allowedToSet(xfill, yc + i)) {
            img.setRGB(xfill, yc + i, hashClr2);
          }
          xfill ++;
          if (!allowedToSet(xfill, yc + i)) {
            break;
          }
        }
      }
      xfill = xc - 1;
      if (allowedToSet(xfill, yc + i) == true) {
        while (img.getRGB(xfill, yc + i) != hashClr1) {
          if (allowedToSet(xfill, yc + i)) {
            img.setRGB(xfill, yc + i, hashClr2);
          }
          xfill --;
          if (!allowedToSet(xfill, yc + i)) {
            break;
          }
        }
      }
    }
  }
}
