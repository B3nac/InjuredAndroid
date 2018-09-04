package b3nac.injuredandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToXSSText(View view) {
        Intent intent = new Intent(this, XSSTextActivity.class);
        startActivity(intent);
    }

    public void goToFlagOneLogin(View view) {
        Intent intent = new Intent(this, FlagOneLoginActivity.class);
        startActivity(intent);
    }

    public void goToFlagTwoBypass(View view) {
        Intent intent = new Intent(this, FlagTwoActivity.class);
        startActivity(intent);
    }
}
