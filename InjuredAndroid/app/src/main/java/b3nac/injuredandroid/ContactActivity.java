package b3nac.injuredandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void goToEmailAppSelection(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:b3nac.sec@gmail.com"));
        startActivity(emailIntent);
    }

    public void goToDigitalOcean(View view) {
        Intent digitalOceanIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.do.co/c/9348bb7410b4"));
        startActivity(digitalOceanIntent);
    }
}
