import java.util.List;
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter

public class AACMappings extends AAC {

  static String filename;
  AssociativeArray<String, String> contents;

  public AACMappings(String filename) {
    super(filename);
    this.contents = new AssociativeArray<>();
  }

  void add(String imageLoc, String text) {
    this.contents.set(imageLoc, text, text);
  }

  public String getCurrentCategory(String imageLoc) {
    if (imageLoc.contains("food")) {
        return "food";
    } else if (imageLoc.contains("clothing")) {
        return "clothing";
    } else {
        return ""; // Default category
    }
}

  String[] getImageLocs() {
    List<String> locs = this.contents.getImageLocs();
    
    return locs.toArray(new String[0]);
  }

  public String getText(String imageLoc) throws KeyNotFoundException {
    if (isCategory(imageLoc)) {
        String category = getCurrentCategory(imageLoc);
        getCurrentCategory(category);
        return category;
    } else {
        return this.contents.get(imageLoc);
    }
}

  boolean isCategory(String imageLoc) {
    return imageLoc.contains("clothing") || imageLoc.contains("food");
  }

  void reset() {
    this.contents.set("", "", "");
  }

  void writeToFile(String filename) {
    try {
      PrintWriter pen = new PrintWriter(new FileWriter(filename));

      for (String imageLoc : getImages()) {
        pen.println(imageLoc + " " + getText(imageLoc));
      }

      pen.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}