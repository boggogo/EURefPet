package georgikoemdzhiev.eurefpet.UI;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefData;

public class AboutActivity extends AppCompatActivity {
    private EURefData mEURefData;
    private FloatingActionButton fab;
    private TextView mLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAppVersion();
        setUpFabButton();
        setUpLinkTextView();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mEURefData = (EURefData) extras.getSerializable(Constants.KEY_EU_REF_OBJECT);
        }else{

        }

    }

    private void setUpLinkTextView() {
        mLink = ((TextView)findViewById(R.id.link));
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://petition.parliament.uk/petitions/131215";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ActivityCompat.getColor(AboutActivity.this,R.color.colorPrimary));

                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(AboutActivity.this, Uri.parse(url));
            }
        });
    }

    private void setUpFabButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Developer");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setData(Uri.parse("koemdjiev@gmail.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
            }
        });
    }

    private void setAppVersion() {
        String version = ":";
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version = pInfo.versionName;

        ((TextView)findViewById(R.id.version)).setText("Version: " + version);
    }

}
