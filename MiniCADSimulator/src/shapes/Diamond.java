package shapes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Diamond implements Visitable {
  public int coordx;
  public int coordy;
  public int diagHoriz;
  public int diagVert;
  public int diagHorizDiv2 = 0;
  public int diagVertDiv2 = 0;
  public int hashClr1;
  public int hashClr2;

  /** {@docRoot}.}
   * 
   */
  public Diamond(int coordx, int coordy, int diagHoriz, int diagVert, int hashClr1, int hashClr2) {
    this.coordx = coordx;
    this.coordy = coordy;
    this.hashClr1 = hashClr1;
    this.hashClr2 = hashClr2;
    this.diagHoriz = diagHoriz;
    this.diagVert = diagVert;
    this.diagHorizDiv2 = Math.round((float)diagHoriz / 2);
    this.diagVertDiv2 = Math.round((float)diagVert / 2);

    posU = new Position();
    posD = new Position();
    posL = new Position();
    posR = new Position();
  }

  Position posU;
  Position posD;
  Position posL;
  Position posR;
  
  /** {@inheritDoc}
   * 
   */
  public void accept(Draw specificShape) {
    posU.posX = coordx;
    posU.posY = coordy + diagVertDiv2;
    posD.posX = coordx;
    posD.posY = coordy - diagVertDiv2;
    posL.posX = coordx - diagHorizDiv2;
    posL.posY = coordy;
    posR.posX = coordx + diagHorizDiv2;
    posR.posY = coordy;
    specificShape.draw(this);
  }

  public Position calculateCentroid() {
    return new Position(coordx, coordy);
  }

  /** {@inheritDoc}
   * 
   */
  public void floodFill(BufferedImage img) {
    Position centralPos = calculateCentroid();
    int replacementColor = hashClr2;
    int boundColor = hashClr1;

    List<Pixel> pixels = new ArrayList<Pixel>();
    pixels.add(new Pixel(centralPos, 4));
    ListIterator<Pixel> it = pixels.listIterator();


    while (it.hasNext()) {
      Pixel px = it.next();
      img.setRGB(px.getPos().posX, px.getPos().posY, replacementColor);
      if (px.getPos().posX >= 1) {
        if (img.getRGB(px.getPos().posX - 1, px.getPos().posY) != replacementColor 
            && img.getRGB(px.getPos().posX - 1, px.getPos().posY) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX - 1, px.getPos().posY), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posY >= 1) {
        if (img.getRGB(px.getPos().posX, px.getPos().posY - 1) != replacementColor 
            && img.getRGB(px.getPos().posX, px.getPos().posY - 1) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX, px.getPos().posY - 1), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posX < Canvas.width - 1) {
        if (img.getRGB(px.getPos().posX + 1, px.getPos().posY) != replacementColor 
            && img.getRGB(px.getPos().posX + 1, px.getPos().posY) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX + 1, px.getPos().posY), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getPos().posY < Canvas.height - 1) {
        if (img.getRGB(px.getPos().posX, px.getPos().posY + 1) != replacementColor 
            && img.getRGB(px.getPos().posX, px.getPos().posY + 1) != boundColor) {
          it.add(new Pixel(new Position(px.getPos().posX, px.getPos().posY + 1), 4));
          px.getLowerNeighbour();
          it.previous();
        }
      }
      if (px.getNeighbour() == 0) {
        it.previous();
        it.next();
        it.remove();
      }
    }
  }

}
