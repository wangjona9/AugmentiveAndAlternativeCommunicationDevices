import java.util.List;
import structures.AssociativeArray;
import structures.KeyNotFoundException;

public class AACCategories extends AAC {

    AssociativeArray<String, String> contents;

    public AACCategories(String name) {
        super(name);
        this.contents = new AssociativeArray<>();
    }

    public void addItem(String imageLoc, String text) {
        this.contents.set(imageLoc, text, text);
    }

    public String getCategory(String imageLoc) throws KeyNotFoundException {
        return this.contents.get(imageLoc);
    }

    public String getText(String imageLoc) throws KeyNotFoundException {
        return this.contents.get(imageLoc);
    }

    public boolean hasImage(String imageLoc) {
        return this.contents.hasKey(imageLoc);
    }

    public String[] getImages() {
        List<String> contentList = this.contents.keys();
        return contentList.toArray(new String[0]);
    }
}
