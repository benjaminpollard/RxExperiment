package trans.dd.com.rxtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class NormalOnClickActivity extends AppCompatActivity implements IClickHost {
    private RecyclerView list;
    private NormalListAdaptor ad;
    private List<StringWrapper> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal_on_click);
        list = (RecyclerView) findViewById(R.id.view);

        items = new ArrayList<>();
        StringWrapper n = new StringWrapper();
        n.value = "1";
        StringWrapper ds = new StringWrapper();
        ds.value = "2";
        items.add(n);
        items.add(ds);
        ad = new NormalListAdaptor(items , this);
        list.setAdapter(ad);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void clicked(String value) {
        Toast.makeText(this,value, Toast.LENGTH_SHORT).show();

    }
}
