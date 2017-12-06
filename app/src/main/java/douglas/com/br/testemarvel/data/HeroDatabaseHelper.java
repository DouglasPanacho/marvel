package douglas.com.br.testemarvel.data;

import douglas.com.br.testemarvel.data.local.Hero;
import io.reactivex.Maybe;

/**
 * Created by douglaspanacho on 03/12/2017.
 *
 * Responsable of make interaction with the database
 */

public class HeroDatabaseHelper {

    public static Hero addHero(final AppDatabase db, Hero heroe) {
        db.userDao().insertAll(heroe);
        return heroe;
    }

    public static void getHeroes(final AppDatabase db) {
        db.userDao().getAll();

    }

    public static Maybe<Hero> getHero(final AppDatabase db, int id) {
        return db.userDao().getHero(id);

    }

}

