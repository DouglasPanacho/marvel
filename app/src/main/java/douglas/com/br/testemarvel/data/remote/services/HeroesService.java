package douglas.com.br.testemarvel.data.remote.services;

import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.data.remote.models.response.GeneralResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public interface HeroesService {

    @GET("characters")
    Observable<CharactersResponse> getCharacters(@Query("offset") int offset);

    @GET("characters")
    Observable<CharactersResponse> getCharacters(@Query("offset") int offset, @Query("name") String name);

    @GET("characters/{characterId}/comics")
    Observable<GeneralResponse> getComics(@Path("characterId") int characterId, @Query("limit") int limit);

    @GET("characters/{characterId}/events")
    Observable<GeneralResponse> getEvents(@Path("characterId") int characterId, @Query("limit") int limit);

    @GET("characters/{characterId}/stories")
    Observable<GeneralResponse> getStories(@Path("characterId") int characterId, @Query("limit") int limit);

    @GET("characters/{characterId}/series")
    Observable<GeneralResponse> getSeries(@Path("characterId") int characterId, @Query("limit") int limit);


//
//    GET /v1/public/characters/{characterId}
}
