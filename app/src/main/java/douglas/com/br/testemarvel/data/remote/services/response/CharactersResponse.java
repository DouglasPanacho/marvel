package douglas.com.br.testemarvel.data.remote.services.response;

import java.util.List;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public class CharactersResponse {

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

    public class Comics {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

        public Integer getAvailable() {
            return available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<Object> getItems() {
            return items;
        }

        public Integer getReturned() {
            return returned;
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


    public class Events {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

        public Integer getAvailable() {
            return available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<Object> getItems() {
            return items;
        }

        public Integer getReturned() {
            return returned;
        }
    }


    public class Result {

        public Integer id;
        public String name;
        public String description;
        public String modified;
        public Thumbnail thumbnail;
        public String resourceURI;
        public Comics comics;
        public Series series;
        public Stories stories;
        public Events events;
        public List<Url> urls = null;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getModified() {
            return modified;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public Comics getComics() {
            return comics;
        }

        public Series getSeries() {
            return series;
        }

        public Stories getStories() {
            return stories;
        }

        public Events getEvents() {
            return events;
        }

        public List<Url> getUrls() {
            return urls;
        }
    }


    public class Series {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

        public Integer getAvailable() {
            return available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<Object> getItems() {
            return items;
        }

        public Integer getReturned() {
            return returned;
        }
    }


    public class Stories {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

        public Integer getAvailable() {
            return available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<Object> getItems() {
            return items;
        }

        public Integer getReturned() {
            return returned;
        }
    }

    public class Thumbnail {

        public String path;
        public String extension;

        public String getFullPath() {
            return path+"."+getExtension();
        }

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }
    }

    public class Url {

        public String type;
        public String url;

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
