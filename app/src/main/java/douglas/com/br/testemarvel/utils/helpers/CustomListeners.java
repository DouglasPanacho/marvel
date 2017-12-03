package douglas.com.br.testemarvel.utils.helpers;

import android.view.View;

import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;

/**
 * Created by douglaspanacho on 02/12/2017.
 */

public class CustomListeners {

    public interface OnHeroClicked {
        void OnHeroClicked(CharactersResponse.Result hero);
        void OnHeroClicked(CharactersResponse.Result hero, View image, View name);
    }
}
