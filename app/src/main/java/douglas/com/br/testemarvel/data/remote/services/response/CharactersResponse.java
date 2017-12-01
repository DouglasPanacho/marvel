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


    public class Comics {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

    }

    public class Data {

        public Integer offset;
        public Integer limit;
        public Integer total;
        public Integer count;
        public List<Result> results = null;

    }


    public class Events {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

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

    }


    public class Series {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

    }


    public class Stories {

        public Integer available;
        public String collectionURI;
        public List<Object> items = null;
        public Integer returned;

    }

    public class Thumbnail {

        public String path;
        public String extension;

    }

    public class Url {

        public String type;
        public String url;

    }
}
