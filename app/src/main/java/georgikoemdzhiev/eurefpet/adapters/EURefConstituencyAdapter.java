package georgikoemdzhiev.eurefpet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.EURefConstituency;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EURefConstituencyAdapter extends RecyclerView.Adapter<EURefConstituencyAdapter.EURefConstituencyAdapterViewHolder> {
    private List<EURefConstituency> mEURefConstituencies;
    private Context mContext;


    public EURefConstituencyAdapter(List<EURefConstituency> euRefConstituencies, Context context) {
        mEURefConstituencies = euRefConstituencies;
        mContext = context;
    }

    @Override
    public EURefConstituencyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.details_constituety_list_item_layout, parent, false);
        return  new EURefConstituencyAdapterViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EURefConstituencyAdapterViewHolder holder, int position) {
        EURefConstituency euRefConstituency = mEURefConstituencies.get(position);

        holder.mConstituencyName.setText(euRefConstituency.getName());
        holder.mConstituencyNumOfSign.setText(euRefConstituency.getSignature_count()+"");
        holder.mMpTextView.setText(euRefConstituency.getMp());

    }

    @Override
    public int getItemCount() {
        return mEURefConstituencies.size();
    }

    public static class EURefConstituencyAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView mConstituencyName;
        public TextView mMpTextView;
        public TextView mConstituencyNumOfSign;

        public EURefConstituencyAdapterViewHolder(View view) {
            super(view);

            mConstituencyName = (TextView) view.findViewById(R.id.constituencyName);
            mConstituencyNumOfSign = (TextView) view.findViewById(R.id.constituencyNumberOfSing);
            mMpTextView = (TextView) view.findViewById(R.id.mpName);

        }
    }
}
