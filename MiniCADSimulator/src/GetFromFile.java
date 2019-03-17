import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import shapes.Canvas;
import shapes.Visitable;

public class GetFromFile {
  static String fileName;
  public static int placeAfter;

  private GetFromFile() {}

  private GetFromFile(String getName) {
    fileName = getName;
  }

  private static GetFromFile objectnumberParam;
  
  public static GetFromFile getObject() {
    objectnumberParam = new GetFromFile();
    return objectnumberParam;
  }

  private static GetFromFile objectFileSpecified;
  
  public static GetFromFile getObject(String fileName) {
    objectFileSpecified = new GetFromFile(fileName);
    return objectFileSpecified;
  }

  /** {@inheritDoc} 
   * 
   */
  public String readWord(String line, int position) {
    char spaceOrnumbert = line.charAt(position);
    String word = "";
    while (spaceOrnumbert == 32 && position < line.length()) {
      spaceOrnumbert = line.charAt(++position);
    }
    if (position < line.length()) {
      word = String.valueOf(spaceOrnumbert);
      spaceOrnumbert = line.charAt(++position);
      while (spaceOrnumbert != 32 && spaceOrnumbert != 13 && spaceOrnumbert != 10) {
        word = word.concat(String.valueOf(spaceOrnumbert));
        spaceOrnumbert = line.charAt(++position);
      }
    }
    placeAfter += word.length() + 1;
    return word;
  }

  /** {@inheritDoc}
   * 
   */
  public int readInt(String line, int position, boolean isColor) {
    line = line.concat(String.valueOf((char)13));
    int number = 0;
    if (isColor == false) {
      char spaceOrnumbert = line.charAt(position);
      while (spaceOrnumbert == 32 && position < line.length()) {
        spaceOrnumbert = line.charAt(++position);
        placeAfter ++;
      }
      while (line.charAt(position) > 32) {
        number *= 10;
        number += line.charAt(position++) - 48;
        placeAfter++;
      }
    } else {
      position++;
      placeAfter ++;
      char spaceOrnumbert = line.charAt(position);
      while (spaceOrnumbert == 32 && position < line.length()) {
        spaceOrnumbert = line.charAt(++position);
      }

      position++;
      placeAfter ++;
      if (spaceOrnumbert > 32) {
        String theColor = line.substring(position, position + 6);
        int[] numbers = new int[6];
        for (int i = 0; i < theColor.length(); i ++) {
          numbers[i] = theColor.charAt(i);
          if (theColor.charAt(i) >= 58 && theColor.charAt(i) < 97) {
            numbers[i] -= 7;
          }
          if (theColor.charAt(i) >= 97) {
            numbers[i] -= 39;
          }
        }
        int r;
        int g;
        int b;
        int a;
        r = 16 * (numbers[0] - 48) + numbers[1] - 48;
        g = 16 * (numbers[2] - 48) + numbers[3] - 48;
        b = 16 * (numbers[4] - 48) + numbers[5] - 48;

        position += 6;
        placeAfter += 6;
        spaceOrnumbert = line.charAt(position);
        while (spaceOrnumbert == 32 && position < line.length()) {
          spaceOrnumbert = line.charAt(++position);
          placeAfter ++;
        }
        if (spaceOrnumbert > 32) {
          a = readInt(line, position, false);
          number = (new Color(r, g, b, a)).hashCode();
        }
      }
    }
    return number;
  }

  ArrayList<Visitable> figures;

  /** {@inheritDoc}
   * 
   */
  public ArrayList<Visitable> readsInfo() {
    int number;
    figures = new ArrayList<Visitable>();

    try {
      FileReader fileIn = new FileReader(fileName);
      BufferedReader scanner = new BufferedReader(fileIn);

      number = readInt(scanner.readLine(), 0, false);
      placeAfter = 0;
      String firstLine = scanner.readLine();
      String canvasCall = readWord(firstLine, 0);
      if (canvasCall.equals("CANVAS")) {
        shapes.Canvas.height = readInt(firstLine, placeAfter, false);
        shapes.Canvas.width = readInt(firstLine, placeAfter, false);
        shapes.Canvas.hashClr = readInt(firstLine, placeAfter, true);
      }

      figures = new ArrayList<Visitable>();
      for (int shapenumber = 0; shapenumber < number - 1; shapenumber ++) {
        placeAfter = 0;
        String specificLine = scanner.readLine();
        figures.add((new GetShape(specificLine)).getAttributes());
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("Opening file error." + fileName);
    } catch (IOException e) {
      System.out.println("Undetected error.");
    }

    return figures;
  }

  /** {@inheritDoc}
   * 
   */
  public void outputsShapes(ArrayList<Visitable> figures) {
    BufferedImage image = new BufferedImage(shapes.Canvas.width, shapes.Canvas.height, BufferedImage.TYPE_4BYTE_ABGR);

    Canvas.computeCanvas(image);
    Visitor toDraw = new Visitor(image);
    for (Visitable form : figures) {
      form.accept(toDraw);
    }
    try {
      ImageIO.write(image, "png", new File("drawing.png"));
    } catch (IOException e) {
      System.out.println("Output error.");
    }
  }

}
