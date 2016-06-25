package georgikoemdzhiev.eurefpet.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import java.util.Collections;
import java.util.List;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefConstituency;
import georgikoemdzhiev.eurefpet.Utils.EURefCountry;
import georgikoemdzhiev.eurefpet.Utils.EURefData;
import georgikoemdzhiev.eurefpet.adapters.EURefConstituencyAdapter;
import georgikoemdzhiev.eurefpet.adapters.EURefCountryAdapter;


public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName().toString();
    private EURefData mEURefData;
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private EURefCountryAdapter mContryAdapter;
    private EURefConstituencyAdapter mEURefConstituencyAdapter;
    private boolean isCountryAdapterSet = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEURefData = (EURefData) getIntent().getExtras().getSerializable(Constants.KEY_EU_REF_OBJECT);


        Log.d(TAG, mEURefData.getAttributes().getSignatures_by_country().toString());
        Log.d(TAG, "=======================================================");
        Log.d(TAG, mEURefData.getAttributes().getSignatures_by_constituency().toString());

// Lookup the recyclerview in activity layout
        mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);

        sortDataByNumOfSing(mEURefData.getAttributes().getSignatures_by_country(), mEURefData.getAttributes().getSignatures_by_constituency());
        // Create mContryAdapter passing in the sample user data
        mContryAdapter = new EURefCountryAdapter(mEURefData.getAttributes().getSignatures_by_country(), this);
        mEURefConstituencyAdapter = new EURefConstituencyAdapter(mEURefData.getAttributes().getSignatures_by_constituency(), this);
        // Attach the mContryAdapter to the recyclerview to populate items
        mRecyclerView.setAdapter(mContryAdapter);
        // Set layout manager to position the items
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        // That's all!
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // change list's data

                if (isCountryAdapterSet) {
                    // current adapter is Country so set it to Constituency...
                    mRecyclerView.setAdapter(mEURefConstituencyAdapter);

                    // change title...
                    getSupportActionBar().setTitle(getString(R.string.title_activity_details_constituency));
                    isCountryAdapterSet = false;
                } else {
                    // current adapter is Constituency so set it to Country...
                    mRecyclerView.setAdapter(mContryAdapter);
                    // change title...
                    getSupportActionBar().setTitle(getString(R.string.title_activity_details));

                    isCountryAdapterSet = true;
                }

            }
        });


    }

    private void sortDataByNumOfSing(List<EURefCountry> signatures_by_country, List<EURefConstituency> signatures_by_constituecy) {
        Collections.sort(signatures_by_country);
        Collections.sort(signatures_by_constituecy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(DetailsActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
