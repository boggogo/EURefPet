package georgikoemdzhiev.eurefpet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EURefData mEURefData;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEURefData = new EURefData();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // should be a singleton
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://petition.parliament.uk/petitions/131215.json")
                .build();

        // Get a handler that can be used to post to the main thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {

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

                } catch (JSONException | ParseException e) {
                    e.printStackTrace();

                }


            }
        });
    }

    private void parseAndSetAttributes(JSONObject attrJsonData) throws JSONException, ParseException {
        EuRefAttr euRefAttr = new EuRefAttr();

        euRefAttr.setAction(attrJsonData.getString("action"));
        euRefAttr.setBackground(attrJsonData.getString("background"));
        euRefAttr.setAdditional_details(attrJsonData.getString("additional_details"));
        euRefAttr.setState(attrJsonData.getString("state"));
        euRefAttr.setSignature_count(attrJsonData.getInt("signature_count"));
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

        for(int i = 0; i < sigbyConstituencyJsonArray.length(); i++){
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
        if(!created_at.equals("null")) {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
