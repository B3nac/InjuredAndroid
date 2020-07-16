package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

public class XSSTextActivity extends AppCompatActivity {
    public static final String POST_STRING = "com.b3nac.injuredandroid.DisplayPostXSS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xsstext);
    }

    public void submitText(View view) {
        Intent intent = new Intent(this, DisplayPostXSS.class);
        EditText editText = findViewById(R.id.editText);
        String post = editText.getText().toString();
        intent.putExtra(POST_STRING, post);
        startActivity(intent);
    }

}
