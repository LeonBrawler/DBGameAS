package ru.samsung.itschool.dbgame;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatActivity extends Activity {
    private DBManager dbManager;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        dbManager = DBManager.getInstance(this);
        TextView gamesCount = findViewById(R.id.gameCount);
        TextView maxNumber = findViewById(R.id.maxNumber);
        gamesCount.setText(String.format("%d", dbManager.gamesCount()));
        maxNumber.setText(String.format("%d", dbManager.maxNumber()));
    }


}
