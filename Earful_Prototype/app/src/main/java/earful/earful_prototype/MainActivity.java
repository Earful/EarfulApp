package earful.earful_prototype;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnClick(View view)
    {



        Button btn = (Button)findViewById(R.id.button);



        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ArticleList.class);
        startActivity(intent);
        MainActivity.this.finish();
    }


}
