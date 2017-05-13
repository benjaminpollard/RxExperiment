package trans.dd.com.rxtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ben on 13/05/2017.
 */

public class NormalListAdaptor  extends RecyclerView.Adapter<NormalListAdaptor.ViewHolder> {

    private List<StringWrapper> items;
    private IClickHost host;
    public NormalListAdaptor(final List<StringWrapper> items , IClickHost host)
    {
        this.host = host;
        this.items = items;
    }

    @Override
    public NormalListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NormalListAdaptor.ViewHolder holder, int position) {
        holder.setItem(items.get(position).value);
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private String value;
        private TextView mTextView;

        public ViewHolder(View view ) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    host.clicked(value);
                }
            });
        }

        public void setItem(String item) {
            value = item;
            mTextView.setText(item);
        }

    }
}
