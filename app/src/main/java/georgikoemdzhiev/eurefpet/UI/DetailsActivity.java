package georgikoemdzhiev.eurefpet.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.Collections;
import java.util.List;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefCountry;
import georgikoemdzhiev.eurefpet.Utils.EURefData;
import georgikoemdzhiev.eurefpet.adapters.EURefCountryAdapter;


public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName().toString();
    private EURefData mEURefData;
    private RecyclerView rvContacts;


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
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        sortDataByNumOfSing(mEURefData.getAttributes().getSignatures_by_country());
        // Create adapter passing in the sample user data
        EURefCountryAdapter adapter = new EURefCountryAdapter(mEURefData.getAttributes().getSignatures_by_country(), this);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!



    }

    private void sortDataByNumOfSing(List<EURefCountry> signatures_by_country) {
        Collections.sort(signatures_by_country);
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
