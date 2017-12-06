package douglas.com.br.testemarvel.data.remote.models.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

public class GeneralResponse {

    private Integer code;
    private String status;
    private Data data;

    public class Creators {
        public List<Item> items = new ArrayList<>();

        public String getCreatorsName() {
            StringBuilder names = new StringBuilder();
            for (Item item : items
                    ) {
                names.append(item.getName());
                if (items.indexOf(item) != items.size() - 1) {
                    names.append(", ");
                }
            }
            return names.toString();
        }
    }

    public class Data {

        public List<Result> results = null;

        public List<Result> getResults() {
            return results;
        }
    }


    public Integer getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }


    public class Item {
        public String name;

        public String getName() {
            return name;
        }
    }


    public class Result {

        private Integer id;
        private String title;
        private String description;
        private List<TextObject> textObjects = null;
        private Thumbnail thumbnail;
        private Creators creators;

        private int iTemtype;

        public int getiTemtype() {
            return iTemtype;
        }

        public void setiTemtype(int iTemtype) {
            this.iTemtype = iTemtype;
        }

        public Integer getId() {
            return id;
        }


        public String getTitle() {
            return title;
        }


        public String getDescription() {
            return description;
        }

        public List<TextObject> getTextObjects() {
            return textObjects;
        }

        public Thumbnail getThumbnail() {
            if (thumbnail == null)
                thumbnail = new Thumbnail();

            return thumbnail;
        }

        public Creators getCreators() {
            return creators;
        }
    }

    public class TextObject {
        private String text;

        public String getText() {
            return text;
        }
    }


    public class Thumbnail {

        private String path = "";
        private String extension = "";

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }

        public String getFullPath() {
            if (!path.contains("image_not_available")) {
                return path + "." + getExtension();
            } else return "";
        }

    }
}
