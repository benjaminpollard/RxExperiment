package trans.dd.com.rxtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Ben on 13/05/2017.
 */

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ViewHolder> {

    private List<StringWrapper> items;
    private Observable<StringWrapper> myObservable;
    private mySubscriber sub;
    private CompositeDisposable dis;
    public ListAdaptor(final List<StringWrapper> items)
    {
        this.items = items;
    }

    public void SetSubscriber( mySubscriber item)
    {
        sub = item;
    }
    public void SetDisbpoable(CompositeDisposable diss)
    {
        dis = diss;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(items.get(position).value);
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public ViewHolder(View view ) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myObservable = Observable.defer(new Callable<ObservableSource<? extends StringWrapper>>() {
                    @Override
                    public ObservableSource<? extends StringWrapper> call() throws Exception {
                        final StringWrapper r = new StringWrapper();
                        r.value = mTextView.getText().toString();
                        return Observable.just(r);
                    }
                });
                dis.add(myObservable.subscribeWith(sub));
               // myObservable.just(r);

            }
        });
    }

    public void setItem(String item) {
        mTextView.setText(item);
    }

}

}
