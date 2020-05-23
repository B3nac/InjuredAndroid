package b3nac.injuredandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.widget.Button;

public class FlagsOverview extends AppCompatActivity {

    public static boolean flagOneButtonColor;
    public static boolean flagTwoButtonColor;
    public static boolean flagThreeButtonColor;
    public static boolean flagFourButtonColor;
    public static boolean flagFiveButtonColor;
    public static boolean flagSixButtonColor;
    public static boolean flagSevenButtonColor;
    public static boolean flagEightButtonColor;
    public static boolean flagNineButtonColor;
    public static boolean flagTenButtonColor;
    public static boolean flagElevenButtonColor;
    public static boolean flagTwelveButtonColor;
    public static boolean flagThirteenButtonColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_overview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "If the flag button is green you have captured that flag!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button flagOneButton;
        flagOneButton = findViewById(R.id.button10);
        Button flagTwoButton;
        flagTwoButton = findViewById(R.id.button12);
        Button flagThreeButton;
        flagThreeButton = findViewById(R.id.button13);
        Button flagFourButton;
        flagFourButton = findViewById(R.id.button14);
        Button flagFiveButton;
        flagFiveButton = findViewById(R.id.button15);
        Button flagSixButton;
        flagSixButton = findViewById(R.id.button16);
        Button flagSevenButton;
        flagSevenButton = findViewById(R.id.button17);
        Button flagEightButton;
        flagEightButton = findViewById(R.id.button18);
        Button flagNineButton;
        flagNineButton = findViewById(R.id.button19);
        Button flagTenButton;
        flagTenButton = findViewById(R.id.button20);
        Button flagElevenButton;
        flagElevenButton = findViewById(R.id.button21);
        Button flagTwelveButton;
        flagTwelveButton = findViewById(R.id.button22);
        Button flagThirteenButton;
        flagThirteenButton = findViewById(R.id.button37);

        //Start of flag one
        if (settings.getBoolean("flagOneButtonColor", false)) {
            flagOneButtonColor = settings.getBoolean("flagOneButtonColor", true);
            flagOneButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagOneButtonColor", true)) {
            flagOneButtonColor = settings.getBoolean("flagOneButtonColor", false);
            flagOneButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (!flagOneButtonColor) {
            flagOneButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag two
        if (settings.getBoolean("flagTwoButtonColor", false)) {
            flagTwoButtonColor = settings.getBoolean("flagTwoButtonColor", true);
            flagTwoButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagTwoButtonColor", true)) {
            flagTwoButtonColor = settings.getBoolean("flagTwoButtonColor", false);
            flagTwoButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (!flagTwoButtonColor) {
            flagTwoButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag three
        if (settings.getBoolean("flagThreeButtonColor", false)) {
            flagThreeButtonColor = settings.getBoolean("flagThreeButtonColor", true);
            flagThreeButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagThreeButtonColor", true)) {
            flagThreeButtonColor = settings.getBoolean("flagThreeButtonColor", false);
            flagThreeButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (!flagThreeButtonColor) {
            flagThreeButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag four
        if (settings.getBoolean("flagFourButtonColor", false)) {
            flagFourButtonColor = settings.getBoolean("flagFourButtonColor", true);
            flagFourButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagFourButtonColor", true)) {
            flagFourButtonColor = settings.getBoolean("flagFourButtonColor", false);
            flagFourButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagFourButtonColor) {
            flagFourButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag five
        if (settings.getBoolean("flagFiveButtonColor", false)) {
            flagFiveButtonColor = settings.getBoolean("flagFiveButtonColor", true);
            flagFiveButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagFiveButtonColor", true)) {
            flagFiveButtonColor = settings.getBoolean("flagFiveButtonColor", false);
            flagFiveButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (!flagFiveButtonColor) {
            flagFiveButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag six
        if (settings.getBoolean("flagSixButtonColor", false)) {
            flagSixButtonColor = settings.getBoolean("flagSixButtonColor", true);
            flagSixButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

        }

        if (settings.getBoolean("flagSixButtonColor", true)) {
            flagSixButtonColor = settings.getBoolean("flagSixButtonColor", false);
            flagSixButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagSixButtonColor) {
            flagSixButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag seven
        if (settings.getBoolean("flagSevenButtonColor", false)) {
            flagSevenButtonColor = settings.getBoolean("flagSevenButtonColor", true);
            flagSevenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagSevenButtonColor", true)) {
            flagSevenButtonColor = settings.getBoolean("flagSevenButtonColor", false);
            flagSevenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagSevenButtonColor) {
            flagSevenButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag eight
        if (settings.getBoolean("flagEightButtonColor", false)) {
            flagEightButtonColor = settings.getBoolean("flagEightButtonColor", true);
            flagEightButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagEightButtonColor", true)) {
            flagEightButtonColor = settings.getBoolean("flagEightButtonColor", false);
            flagEightButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagEightButtonColor) {
            flagEightButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag nine
        if (settings.getBoolean("flagNineButtonColor", false)) {
            flagNineButtonColor = settings.getBoolean("flagNineButtonColor", true);
            flagNineButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagNineButtonColor", true)) {
            flagNineButtonColor = settings.getBoolean("flagNineButtonColor", false);
            flagNineButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagNineButtonColor) {
            flagNineButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag ten
        if (settings.getBoolean("flagTenButtonColor", false)) {
            flagTenButtonColor = settings.getBoolean("flagTenButtonColor", true);
            flagTenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagTenButtonColor", true)) {
            flagTenButtonColor = settings.getBoolean("flagTenButtonColor", false);
            flagTenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagTenButtonColor) {
            flagTenButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag eleven
        if (settings.getBoolean("flagElevenButtonColor", false)) {
            flagElevenButtonColor = settings.getBoolean("flagElevenButtonColor", true);
            flagElevenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagElevenButtonColor", true)) {
            flagElevenButtonColor = settings.getBoolean("flagElevenButtonColor", false);
            flagElevenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagElevenButtonColor) {
            flagElevenButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag twelve
        if (settings.getBoolean("flagTwelveButtonColor", false)) {
            flagTwelveButtonColor = settings.getBoolean("flagTwelveButtonColor", true);
            flagTwelveButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagTwelveButtonColor", true)) {
            flagTwelveButtonColor = settings.getBoolean("flagTwelveButtonColor", false);
            flagTwelveButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagTwelveButtonColor) {
            flagTwelveButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        //Start of flag thirteen
        if (settings.getBoolean("flagThirteenButtonColor", false)) {
            flagThirteenButtonColor = settings.getBoolean("flagThirteenButtonColor", true);
            flagThirteenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (settings.getBoolean("flagThirteenButtonColor", true)) {
            flagThirteenButtonColor = settings.getBoolean("flagThirteenButtonColor", false);
            flagThirteenButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        }

        if (!flagThirteenButtonColor) {
            flagThirteenButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
    }

}
