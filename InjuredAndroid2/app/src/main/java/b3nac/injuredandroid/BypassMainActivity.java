package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class BypassMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bypass_main);
    }

    public void submitFlag(View view) {
        String decode = FlagOne.getFlag();
        EditText editText2 = findViewById(R.id.editText2);
        String post = editText2.getText().toString();
        if (post.equals(decode)) { //Add encrypted more secure flag for flag two
            Intent intent = new Intent(this, FlagOneSuccess.class);
            startActivity(intent);
        }
    }
}
