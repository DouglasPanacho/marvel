package douglas.com.br.testemarvel.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.local.HeroDao;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

@Singleton
@Database(entities = {Hero.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HeroDao userDao();

}
