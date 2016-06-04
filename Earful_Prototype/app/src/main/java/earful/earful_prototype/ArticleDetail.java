package earful.earful_prototype;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ArticleDetail extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article_detail);


        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");

        JDBC jdbc = new JDBC();
        ArrayList<String> result = jdbc.getArticle(ID);
        TextView Title = (TextView)findViewById(R.id.DetailTitle);
        TextView Article = (TextView)findViewById(R.id.DetailArticle);
        Title.setText(result.get(1));
        Article.setText(result.get(2));



        JDBC jdbc2 = new JDBC();
        ArrayList<String> result2 = jdbc2.getAllreply(ID);

        TableLayout tl = (TableLayout) findViewById(R.id.responceLines);
        for(int i = 0;i<result2.size()/3;i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            TextView resTitle = new TextView(this);
            TextView resDate = new TextView(this);
            resTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            resDate.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            resTitle.setText(result2.get(i*3+1));
            resDate.setText(result2.get(i*3+2));

            resTitle.setMinimumHeight(40);
            resTitle.setMinimumWidth(800);
            resDate.setMinimumHeight(40);
            resTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            resTitle.setGravity(Gravity.CENTER);
            resDate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            resDate.setGravity(Gravity.CENTER);
            tr.addView(resTitle);
            tr.addView(resDate);
            if(i%2==0)
                tr.setBackgroundColor(Color.GRAY);
            tr.setMinimumHeight(40);

            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }



    }

    public void moveArticleList(View view)
    {
        Intent intent = new Intent();
        intent.setClass(ArticleDetail.this, ArticleList.class);
        startActivity(intent);
        ArticleDetail.this.finish();
    }

    public void ResponceArticle(View view)
    {
        TextView responseTV = (TextView)findViewById(R.id.responseText);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");

        String Response = responseTV.getText().toString();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = Calendar.getInstance().getTime();
        String Date = sdf.format(date);

        String Query = "INSERT INTO response (articleID, ID, Content, Date) VALUES (" +
                "'" + ID + "'," +
                "NULL," +
                "'" + Response + "',"+
                "'" + Date + "');";
        JDBC jdbc = new JDBC();
        jdbc.InsertQuery(Query);
        System.out.println(Query);

        Intent intent2 = new Intent();
        intent2.setClass(ArticleDetail.this, TrickyPage.class);
        intent2.putExtra("ID", ID);
        startActivity(intent2);
        ArticleDetail.this.finish();

    }
    public void refresh() {

        onCreate(null);

    }
}
