package douglas.com.br.testemarvel.data.remote.services;

import java.util.List;

import douglas.com.br.testemarvel.data.remote.services.response.CharactersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public interface HeroesService {

    @GET("characters")
    Observable<CharactersResponse> getCharacters(@Query("offset") int offset);
}
