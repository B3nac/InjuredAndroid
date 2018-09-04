package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class FlagOneLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bypass_main);
    }

    public void submitFlag(View view) {
        //String decode = getString(R.string.flag_one);
        EditText editText2 = findViewById(R.id.editText2);
        String post = editText2.getText().toString();
        if (post.equals(getString(R.string.flag_one))) { //Add encrypted more secure flag for flag two
            Intent intent = new Intent(this, FlagOneSuccess.class);
            startActivity(intent);
        }
    }
}
