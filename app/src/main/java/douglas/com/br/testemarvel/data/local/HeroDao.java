package douglas.com.br.testemarvel.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

@Dao
public interface HeroDao {

    @Query("SELECT * FROM heroes")
    Maybe<List<Hero>> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Hero... heroes);

    @Query("SELECT * FROM heroes WHERE id== :id")
    Maybe<Hero> getHero(int id);

    @Query("SELECT * FROM heroes WHERE id== :id")
    Flowable<Hero> getHeroflow(int id);

    @Query("DELETE FROM heroes WHERE id== :id")
    void deleteHero(int id);

    @Delete
    void delete(Hero hero);
}
