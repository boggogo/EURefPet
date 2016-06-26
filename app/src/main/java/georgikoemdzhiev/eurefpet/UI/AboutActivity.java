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

import java.text.SimpleDateFormat;
import java.util.Date;

import georgikoemdzhiev.eurefpet.R;
import georgikoemdzhiev.eurefpet.Utils.Constants;
import georgikoemdzhiev.eurefpet.Utils.EURefData;
import georgikoemdzhiev.eurefpet.Utils.EuRefAttr;

public class AboutActivity extends AppCompatActivity {
    private EURefData mEURefData;
    private FloatingActionButton fab;
    private TextView mLink, mCreatedAt, mUpdatedAt, mOpenAt, mClosedAt, mGovernmentResponseAt, mScheduledDebateDate, mRejectedAt, mDebateOutcomeAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAppVersion();
        setUpFabButton();
        setUpLinkTextView();
        setUpViews();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mEURefData = (EURefData) extras.getSerializable(Constants.KEY_EU_REF_OBJECT);
            if (mEURefData.getAttributes() != null) {
                EuRefAttr euRefAttr = mEURefData.getAttributes();
                if (euRefAttr.getCreated_at() != null)
                    mCreatedAt.setText(getFormattedDateFromDateObject(euRefAttr.getCreated_at()));
                if (euRefAttr.getUpdated_at() != null)
                    mUpdatedAt.setText(getFormattedDateFromDateObject(euRefAttr.getUpdated_at()));
                if (euRefAttr.getOpen_at() != null)
                    mOpenAt.setText(getFormattedDateFromDateObject(euRefAttr.getOpen_at()));
                if (euRefAttr.getGovernment_response_at() != null)
                    if (euRefAttr.getGovernment_response_at() != null)
                        mGovernmentResponseAt.setText(getFormattedDateFromDateObject(euRefAttr.getGovernment_response_at()));
                if (euRefAttr.getScheduled_debate_date() != null)
                    mScheduledDebateDate.setText(getFormattedDateFromDateObject(euRefAttr.getScheduled_debate_date()));
                if (euRefAttr.getRejected_at() != null)
                    mRejectedAt.setText(getFormattedDateFromDateObject(euRefAttr.getRejected_at()));
                if (euRefAttr.getDebate_outcome_at() != null)
                    mDebateOutcomeAt.setText(getFormattedDateFromDateObject(euRefAttr.getDebate_outcome_at()));
                if (euRefAttr.getClosed_at() != null)
                    mClosedAt.setText(getFormattedDateFromDateObject(euRefAttr.getClosed_at()));
            }
        } else {

        }

    }

    private String getFormattedDateFromDateObject(Date d) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        return inputFormat.format(d);
    }

    private void setUpViews() {
        mCreatedAt = (TextView) findViewById(R.id.createdAt);
        mClosedAt = (TextView) findViewById(R.id.closedAt);
        mUpdatedAt = (TextView) findViewById(R.id.updatedAt);
        mOpenAt = (TextView) findViewById(R.id.openAt);
        mGovernmentResponseAt = (TextView) findViewById(R.id.government_response_at);
        mScheduledDebateDate = (TextView) findViewById(R.id.scheduled_debate_date);
        mRejectedAt = (TextView) findViewById(R.id.rejected_at);
        mDebateOutcomeAt = (TextView) findViewById(R.id.debate_outcome_at);
    }

    private void setUpLinkTextView() {
        mLink = ((TextView) findViewById(R.id.link));
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://petition.parliament.uk/petitions/131215";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ActivityCompat.getColor(AboutActivity.this, R.color.colorPrimary));

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

        ((TextView) findViewById(R.id.version)).setText("Version: " + version);
    }

}
