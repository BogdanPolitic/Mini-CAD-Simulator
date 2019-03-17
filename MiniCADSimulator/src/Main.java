public class Main {
  
  static GetFromFile shapeInfo;
  
  public static void main(String[] args) {
    shapeInfo = GetFromFile.getObject(args[0]);
    shapeInfo.outputsShapes(shapeInfo.readsInfo());
  }

}