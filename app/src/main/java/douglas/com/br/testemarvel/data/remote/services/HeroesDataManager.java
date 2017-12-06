package douglas.com.br.testemarvel.data.remote.services;

import javax.inject.Inject;

import douglas.com.br.testemarvel.NetworkConstants;
import douglas.com.br.testemarvel.data.remote.ServiceGenerator;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;
import io.reactivex.Observable;

/**
 * Created by douglaspanacho on 01/12/2017.
 * Used to get the data from the marvel api
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

    public Observable<GeneralResponse> getComics(int heroId, int limit) {
        return mService.getComics(heroId, limit);
    }

    public Observable<GeneralResponse> getEvents(int heroId, int limit) {
        return mService.getEvents(heroId, limit);
    }

    public Observable<GeneralResponse> getSeries(int heroId, int limit) {
        return mService.getSeries(heroId, limit);
    }

    public Observable<GeneralResponse> getStories(int heroId, int limit) {
        return mService.getStories(heroId, limit);
    }


}
