package georgikoemdzhiev.eurefpet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.EURefCountry;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EURefCountryAdapter extends RecyclerView.Adapter<EURefCountryAdapter.EURefCountryAdapterViewHolder> {
    private List<EURefCountry> mEURefCountries;
    private Context mContext;


    public EURefCountryAdapter(List<EURefCountry> EURefCountries, Context context) {
        mEURefCountries = EURefCountries;
        mContext = context;
    }

    @Override
    public EURefCountryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.details_contry_list_item_layout, parent, false);



        return  new EURefCountryAdapterViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EURefCountryAdapterViewHolder holder, int position) {
        EURefCountry euRefCountry = mEURefCountries.get(position);

        holder.mCountryName.setText(euRefCountry.getName());
        holder.mCountryNumOfSign.setText(euRefCountry.getSignature_count()+"");

    }

    @Override
    public int getItemCount() {
        return mEURefCountries.size();
    }

    public static class EURefCountryAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView mCountryName;
        public TextView mCountryNumOfSign;

        public EURefCountryAdapterViewHolder(View view) {
            super(view);

            mCountryName = (TextView) view.findViewById(R.id.countryName);
            mCountryNumOfSign = (TextView) view.findViewById(R.id.countryNumberOfSing);

        }
    }
}
