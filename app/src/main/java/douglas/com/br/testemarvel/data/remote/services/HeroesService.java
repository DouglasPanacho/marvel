package douglas.com.br.testemarvel.data.remote.services;

import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public interface HeroesService {

    @GET("characters")
    Observable<CharactersResponse> getCharacters(@Query("offset") int offset);

    @GET("characters")
    Observable<CharactersResponse> getCharacters(@Query("offset") int offset, @Query("name") String name);


//
//    GET /v1/public/characters/{characterId}
}
