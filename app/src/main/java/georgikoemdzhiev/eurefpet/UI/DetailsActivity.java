package georgikoemdzhiev.eurefpet.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefData;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName().toString();
    private EURefData mEURefData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEURefData = (EURefData) getIntent().getExtras().getSerializable(Constants.KEY_EU_REF_OBJECT);

        Log.d(TAG,mEURefData.getAttributes().getSignatures_by_country().toString());
        Log.d(TAG,"=======================================================");
        Log.d(TAG,mEURefData.getAttributes().getSignatures_by_constituency().toString());










//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

}
