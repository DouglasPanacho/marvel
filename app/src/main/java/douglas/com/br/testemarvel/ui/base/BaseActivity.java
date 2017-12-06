package douglas.com.br.testemarvel.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import douglas.com.br.testemarvel.R;
import douglas.com.br.testemarvel.inject.components.MainActvityComponent;

/**
 * Created by douglaspanacho on 26/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private MainActvityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setToolbar(String title, boolean displayHomeAsUpEnabled) {
        setToolbar(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();

    }

    public void setToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    public MainActvityComponent getComponent() {
        return mComponent;
    }
}
