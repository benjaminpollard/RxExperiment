package trans.dd.com.rxtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private  List<StringWrapper> items;
    private ListAdaptor ad;
    mySubscriber fdf;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        StringWrapper n = new StringWrapper();
        n.value = "1";
        StringWrapper ds = new StringWrapper();
        ds.value = "2";
        items.add(n);
        items.add(ds);

        list = (RecyclerView) findViewById(R.id.view);
        ad = new ListAdaptor(items);

        fdf = new mySubscriber(this);
        ad.SetSubscriber(fdf);
        ad.SetDisbpoable(disposables);

        list.setAdapter(ad);
        list.setLayoutManager( new LinearLayoutManager(this));


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context con = v.getContext();
                con.startActivity(new Intent(con, NormalOnClickActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }


}
