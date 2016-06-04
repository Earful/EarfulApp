package earful.earful_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TrickyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricky_page);
        Intent intent_0 = getIntent();
        String ID = intent_0.getStringExtra("ID");


        Intent intent = new Intent();
        intent.setClass(TrickyPage.this, ArticleDetail.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
        TrickyPage.this.finish();
    }
}
