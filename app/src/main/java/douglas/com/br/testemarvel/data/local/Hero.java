package douglas.com.br.testemarvel.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by douglaspanacho on 03/12/2017.
 */

@Entity(tableName = "heroes")
public class Hero {

    @PrimaryKey
    private int id;


    public Hero(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
