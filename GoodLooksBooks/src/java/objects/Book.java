package objects;

import java.io.Serializable;

/**
 *
 * @author mobile-mann
 */
public class Book implements Serializable {
    private String imageUrl;
    private String name;
    
    public Book(){}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
