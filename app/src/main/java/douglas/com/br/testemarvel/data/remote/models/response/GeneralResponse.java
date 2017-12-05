package douglas.com.br.testemarvel.data.remote.models.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

public class GeneralResponse {


    public class Characters {

        public Integer available;
        public String collectionURI;
        public List<Item_> items = null;
        public Integer returned;

    }


    public class Creators {

        public Integer available;
        public String collectionURI;
        public List<Item> items = new ArrayList<>();
        public Integer returned;
        private String creatorsName;

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

        public Integer offset;
        public Integer limit;
        public Integer total;
        public Integer count;
        public List<Result> results = null;

        public Integer getOffset() {
            return offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public Integer getTotal() {
            return total;
        }

        public Integer getCount() {
            return count;
        }

        public List<Result> getResults() {
            return results;
        }
    }


    public class Date {

        public String type;
        public String date;

    }


    public class Events {

        public Integer available;
        public String collectionURI;
        public List<Item___> items = null;
        public Integer returned;

    }


    public Integer code;
    public String status;
    public String copyright;
    public String attributionText;
    public String attributionHTML;
    public String etag;
    public Data data;

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public Data getData() {
        return data;
    }

    public class Image {

        public String path;
        public String extension;

    }


    public class Item {
        public String name;

        public String getName() {
            return name;
        }
    }


    public class Item_ {

        public String resourceURI;
        public String name;

    }


    public class Item__ {

        public String resourceURI;
        public String name;
        public String type;

    }


    public class Item___ {

        public String resourceURI;
        public String name;

    }


    public class Price {

        public String type;
        public Float price;

    }


    public class Result {

        public Integer id;
        public Integer digitalId;
        public String title;
        public Integer issueNumber;
        public String variantDescription;
        public String description;
        public String modified;
        public String isbn;
        public String upc;
        public String diamondCode;
        public String ean;
        public String issn;
        public String format;
        public Integer pageCount;
        public List<TextObject> textObjects = null;
        public String resourceURI;
        public List<Url> urls = null;
        public Series series;
        public List<Object> variants = null;
        public List<Object> collections = null;
        public List<Object> collectedIssues = null;
        public List<Date> dates = null;
        public List<Price> prices = null;
        public Thumbnail thumbnail;
        public List<Image> images = null;
        public Creators creators;
        public Characters characters;
        public Events events;

        public int iTemtype;

        public int getiTemtype() {
            return iTemtype;
        }

        public void setiTemtype(int iTemtype) {
            this.iTemtype = iTemtype;
        }

        public Integer getId() {
            return id;
        }

        public Integer getDigitalId() {
            return digitalId;
        }

        public String getTitle() {
            return title;
        }

        public Integer getIssueNumber() {
            return issueNumber;
        }

        public String getVariantDescription() {
            return variantDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getModified() {
            return modified;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getUpc() {
            return upc;
        }

        public String getDiamondCode() {
            return diamondCode;
        }

        public String getEan() {
            return ean;
        }

        public String getIssn() {
            return issn;
        }

        public String getFormat() {
            return format;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public List<TextObject> getTextObjects() {
            return textObjects;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public List<Url> getUrls() {
            return urls;
        }

        public Series getSeries() {
            return series;
        }

        public List<Object> getVariants() {
            return variants;
        }

        public List<Object> getCollections() {
            return collections;
        }

        public List<Object> getCollectedIssues() {
            return collectedIssues;
        }

        public List<Date> getDates() {
            return dates;
        }

        public List<Price> getPrices() {
            return prices;
        }

        public Thumbnail getThumbnail() {
            if (thumbnail == null)
                thumbnail = new Thumbnail();

            return thumbnail;
        }

        public List<Image> getImages() {
            return images;
        }

        public Creators getCreators() {
            return creators;
        }

        public Characters getCharacters() {
            return characters;
        }


        public Events getEvents() {
            return events;
        }
    }

    public class Series {

        public String resourceURI;
        public String name;

    }


    public class TextObject {
        public String text;

        public String getText() {
            return text;
        }
    }


    public class Thumbnail {

        public String path = "";
        public String extension = "";

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }

        public String getFullPath() {
            return path + "." + getExtension();
        }

    }

    public class Url {

        public String type;
        public String url;

    }
}
