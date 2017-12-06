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

    //get all the heroes of the database
    @Query("SELECT * FROM heroes")
    Maybe<List<Hero>> getAll();

    //select only the ids of the heroes
    @Query("SELECT id FROM heroes")
    Maybe<List<Integer>> getAllIds();

    //insert a new hero in the database or replaces if it's already in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Hero... heroes);

    //select the hero by id
    @Query("SELECT * FROM heroes WHERE id== :id")
    Maybe<Hero> getHero(int id);

    //delete the hero with the same id
    @Query("DELETE FROM heroes WHERE id== :id")
    void deleteHero(int id);

    //delete a hero
    @Delete
    void delete(Hero hero);
}
