package trans.dd.com.rxtest;

import android.content.Context;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.Subject;

/**
 * Created by Ben on 13/05/2017.
 */

public class mySubscriber extends DisposableObserver<StringWrapper> {

    Context con;
        public mySubscriber(Context con)
        {
            this.con = con;

        }
    

    @Override
    public void onNext(StringWrapper stringWrapper) {
        Toast.makeText(con,stringWrapper.value, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
