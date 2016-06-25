package georgikoemdzhiev.eurefpet.UI;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefConstituency;
import georgikoemdzhiev.eurefpet.Utils.EURefCountry;
import georgikoemdzhiev.eurefpet.Utils.EURefData;
import georgikoemdzhiev.eurefpet.Utils.EuRefAttr;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Callback {
    private TextView mSigCount;
    private EURefData mEURefData;
    private String TAG = MainActivity.class.getSimpleName();
    private Request request;
    private OkHttpClient client;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    private FloatingActionButton fab;
    private boolean finishedLoadingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
//                Bundle extras = new Bundle();
                intent.putExtra(Constants.KEY_EU_REF_OBJECT,mEURefData);
                if(finishedLoadingData) {
                    startActivity(new Intent(intent));
                }else {
                    showToastMessage("No data to show!");
                }
            }
        });

        Drawable fabDrawable = fab.getDrawable();
        DrawableCompat.setTint(fabDrawable, Color.WHITE);


        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setWaveARGBColor(255,0,136,0);

        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.

//                mWaveSwipeRefreshLayout.setWaveColor(R.color.colorAccent); // Random assign
                new Task().execute();
            }
        });

        mSigCount = (TextView) findViewById(R.id.signatureCount);
        mSigCount.setOnClickListener(swipeToRefreshgListener);
        findViewById(R.id.text).setOnClickListener(swipeToRefreshgListener);
        mEURefData = new EURefData();


        // should be a singleton
        client = new OkHttpClient();
        request = new Request.Builder()
                .url("https://petition.parliament.uk/petitions/131215.json")
                .build();

        mWaveSwipeRefreshLayout.setRefreshing(true);


        // get data from internet...
        getDataFromInternet();
    }

    private View.OnClickListener swipeToRefreshgListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showToastMessage("Swipe down to refresh.");
        }
    };

    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            getDataFromInternet();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            mWaveSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(result);
        }
    }

    private void showToastMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataFromInternet() {
        client.newCall(request).enqueue(this);
    }

    private void parseAndSetAttributes(JSONObject attrJsonData) throws JSONException, ParseException {
        final EuRefAttr euRefAttr = new EuRefAttr();

        euRefAttr.setAction(attrJsonData.getString("action"));
        euRefAttr.setBackground(attrJsonData.getString("background"));
        euRefAttr.setAdditional_details(attrJsonData.getString("additional_details"));
        euRefAttr.setState(attrJsonData.getString("state"));
        euRefAttr.setSignature_count(attrJsonData.getInt("signature_count"));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formatted = formatter.format(euRefAttr.getSignature_count());
                mSigCount.setText(formatted);
            }
        });

        euRefAttr.setCreated_at(getDateFromString(attrJsonData.getString("created_at")));
        euRefAttr.setUpdated_at(getDateFromString(attrJsonData.getString("updated_at")));
        euRefAttr.setOpen_at(getDateFromString(attrJsonData.getString("open_at")));
        euRefAttr.setClosed_at(getDateFromString(attrJsonData.getString("closed_at")));
        euRefAttr.setGovernment_response_at(getDateFromString(attrJsonData.getString("government_response_at")));
        euRefAttr.setScheduled_debate_date(getDateFromString(attrJsonData.getString("scheduled_debate_date")));
        euRefAttr.setDebate_threshold_reached_at(getDateFromString(attrJsonData.getString("debate_threshold_reached_at")));
        euRefAttr.setRejected_at(getDateFromString(attrJsonData.getString("rejected_at")));
        euRefAttr.setDebate_outcome_at(getDateFromString(attrJsonData.getString("debate_outcome_at")));
        euRefAttr.setModeration_threshold_reached_at(getDateFromString(attrJsonData.getString("moderation_threshold_reached_at")));
        euRefAttr.setResponse_threshold_reached_at(getDateFromString(attrJsonData.getString("response_threshold_reached_at")));
        euRefAttr.setCreator_name(attrJsonData.getString("creator_name"));
        euRefAttr.setRejection(attrJsonData.getString("rejection"));
        euRefAttr.setGovernment_response(attrJsonData.getString("government_response"));
        euRefAttr.setDebate(attrJsonData.getString("debate"));

        ArrayList<EURefCountry> sigByCountries = setUpAndAddSigByCountry(attrJsonData.getJSONArray("signatures_by_country"));
        euRefAttr.setSignatures_by_country(sigByCountries);

        ArrayList<EURefConstituency> sigByConstituency = setUpAndAddSigByConstituency(attrJsonData.getJSONArray("signatures_by_constituency"));
        euRefAttr.setSignatures_by_constituency(sigByConstituency);


        // set attrs object to EURefData...
        mEURefData.setAttributes(euRefAttr);
    }

    private ArrayList<EURefConstituency> setUpAndAddSigByConstituency(JSONArray signatures_by_constituency) throws JSONException {
        ArrayList<EURefConstituency> sigByConstituencies = new ArrayList<>();
        JSONArray sigbyConstituencyJsonArray = signatures_by_constituency;

        for (int i = 0; i < sigbyConstituencyJsonArray.length(); i++) {
            EURefConstituency euRefConstituency = new EURefConstituency();
            JSONObject euRefConstiJsonObject = sigbyConstituencyJsonArray.getJSONObject(i);

            euRefConstituency.setName(euRefConstiJsonObject.getString("name"));
            euRefConstituency.setOns_code(euRefConstiJsonObject.getString("ons_code"));
            euRefConstituency.setMp(euRefConstiJsonObject.getString("mp"));
            euRefConstituency.setSignature_count(euRefConstiJsonObject.getInt("signature_count"));

            sigByConstituencies.add(euRefConstituency);
        }

        return sigByConstituencies;
    }

    private ArrayList<EURefCountry> setUpAndAddSigByCountry(JSONArray signatures_by_country) throws JSONException {
        ArrayList<EURefCountry> sigByContries = new ArrayList<>();
        JSONArray signatureByContries = signatures_by_country;

        for (int i = 0; i < signatureByContries.length(); i++) {
            JSONObject sigByConJsonObject = signatureByContries.getJSONObject(i);

            EURefCountry euRefCountry = new EURefCountry();
            euRefCountry.setName(sigByConJsonObject.getString("name"));
            euRefCountry.setCode(sigByConJsonObject.getString("code"));
            euRefCountry.setSignature_count(sigByConJsonObject.getInt("signature_count"));

            sigByContries.add(euRefCountry);

        }

        return sigByContries;
    }

    private Date getDateFromString(String created_at) throws ParseException {
        if (!created_at.equals("null")) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            inputFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
            Date date = inputFormat.parse(created_at);

            return date;
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
//        mMaterialRefreshLayout.finishRefreshing();
        showToastMessage("Fail to get data from server. Try again.");
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (!response.isSuccessful()) {
            showToastMessage("Fail to get data from server. Try again.");
            if (mWaveSwipeRefreshLayout.isRefreshing()) {
                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
            throw new IOException("Unexpected code " + response);
        }
        String jsonData = response.body().string();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            JSONObject links = jsonObject.getJSONObject("links");
            JSONObject data = jsonObject.getJSONObject("data");

            mEURefData.setLinks(links.getString("self"));
            mEURefData.setType(data.getString("type"));
            mEURefData.setId(data.getInt("id"));

            JSONObject attrData = data.getJSONObject("attributes");
            // set attributes object...
            parseAndSetAttributes(attrData);

            Log.d(TAG, mEURefData.toString());

            finishedLoadingData = true;

        } catch (JSONException | ParseException e) {
            showToastMessage("Fail to convert data from server. Try again.");
            e.printStackTrace();

        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mWaveSwipeRefreshLayout.isRefreshing()) {
                    mWaveSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });


    }


}
