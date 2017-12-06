package douglas.com.br.testemarvel.data.remote.models.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglaspanacho on 04/12/2017.
 * <p>
 * Class used to set all the items in only one list of objects, being able to create
 * the headers and set items in the adapter
 */

public class HeroDetailsModel {
    private final int TYPE_COMICS = 0, TYPE_EVENTS = 1, TYPE_STORIES = 2, TYPE_SERIES = 3;
    //need to be object because i am inseting some item to work as header in the recyclerview
    private List<Object> result = new ArrayList<>();

    public List<Object> getResult() {
        return result;
    }

    //add my items to the array and set the type
    public void addItem(List<GeneralResponse.Result> items, int type) {
        if (items.size() > 0) {
            switch (type) {
                case TYPE_COMICS:
                    result.add(new HeaderModel("Comics"));
                    break;
                case TYPE_EVENTS:
                    result.add(new HeaderModel("Events"));
                    break;
                case TYPE_STORIES:
                    result.add(new HeaderModel("Stories"));
                    break;
                case TYPE_SERIES:
                    result.add(new HeaderModel("Series"));
                    break;
            }
            for (GeneralResponse.Result item : items
                    ) {
                item.setiTemtype(type);
                result.add(item);
            }
        }
    }

}
