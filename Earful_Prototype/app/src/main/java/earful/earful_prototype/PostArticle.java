package earful.earful_prototype;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostArticle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_article);
        Button btn = (Button)findViewById(R.id.SubmitBtn);
        btn.setText("POST");


    }


    public void SendArticle(View view)
    {
        TextView TitleTV = (TextView)findViewById(R.id.postTitle);
        TextView ContentTV = (TextView)findViewById(R.id.postContent);

        String Content = ContentTV.getText().toString();
        String Title = TitleTV.getText().toString();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = Calendar.getInstance().getTime();
        String Date = sdf.format(date);

        String Query = "INSERT INTO article (ID, Title, Content, Date, Site) VALUES (NULL," +
                        "'" + Title + "'," +
                        "'" + Content + "'," +
                        "'" + Date + "'," +"'0');";
        JDBC jdbc = new JDBC();
        jdbc.InsertQuery(Query);
        System.out.println(Query);

        Intent intent = new Intent();
        intent.setClass(PostArticle.this, ArticleList.class);
        startActivity(intent);
        PostArticle.this.finish();
    }
}
