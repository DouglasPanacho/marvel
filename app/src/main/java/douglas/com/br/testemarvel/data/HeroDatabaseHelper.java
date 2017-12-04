package douglas.com.br.testemarvel.data;

import java.util.List;

import douglas.com.br.testemarvel.data.local.Hero;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

public class HeroDatabaseHelper {

    public static Hero addHero(final AppDatabase db, Hero heroe) {
        db.userDao().insertAll(heroe);
        return heroe;
    }

    public static Maybe<List<Hero>> getHeroes(final AppDatabase db) {
        return db.userDao().getAll();

    }

    public static Maybe<Hero> getHero(final AppDatabase db, int id) {
        return db.userDao().getHero(id);

    }

//    private static void populateWithTestData(AppDatabase db) {
//        Hero hero = new Hero();
//        hero.setFirstName("Ajay");
//        hero.setLastName("Saini");
//        hero.setAge(25);
//        addUser(db, hero);
}

