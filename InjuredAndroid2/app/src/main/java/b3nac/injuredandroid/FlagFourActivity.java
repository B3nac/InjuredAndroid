package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class FlagFourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_flag_four);
    }

    public void submitFlag(View view) {

        EditText editText2 = findViewById(R.id.editText2);
        String post = editText2.getText().toString();
        Decoder decoder = new Decoder();

        String bob = new String(decoder.getData());

        if (post.equals(bob)) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            startActivity(intent);
        }
    }

}
