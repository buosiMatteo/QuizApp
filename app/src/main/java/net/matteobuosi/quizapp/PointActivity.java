package net.matteobuosi.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PointActivity extends AppCompatActivity {

    private static final String TAG = PointActivity.class.getSimpleName();
    private static final String POINT_KEY = TAG + "point.key";
    private static final String POINT_TOTAL_KEY = TAG + "point.total.key";
    private TextView pointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        pointView =findViewById(R.id.pointView);

        int puntiTot = getIntent().getIntExtra(POINT_TOTAL_KEY,0);
        int punti = getIntent().getIntExtra(POINT_KEY, 0);
        pointView.setText(punti + "/" + puntiTot);
    }

    public static Intent getInstanceIntent(Context context, int point, int pointTotal) {
        Intent intent = new Intent(context, PointActivity.class);
        intent.putExtra(POINT_KEY, point);
        intent.putExtra(POINT_TOTAL_KEY, pointTotal);
        return intent;
    }
}