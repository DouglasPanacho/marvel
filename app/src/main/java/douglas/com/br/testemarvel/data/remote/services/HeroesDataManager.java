package douglas.com.br.testemarvel.data.remote.services;

import javax.inject.Inject;

import douglas.com.br.testemarvel.NetworkConstants;
import douglas.com.br.testemarvel.data.remote.ServiceGenerator;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import io.reactivex.Observable;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public class HeroesDataManager {

    private HeroesService mService;

    @Inject
    public HeroesDataManager() {
        mService = ServiceGenerator.createService(HeroesService.class, NetworkConstants.BASE_URL);
    }


    public Observable<CharactersResponse> getCharacters(int offset) {
        return mService.getCharacters(offset);
    }

    public Observable<CharactersResponse> getCharacters(int offset, String name) {
        return mService.getCharacters(offset, name);
    }

}
