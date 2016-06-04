package earful.earful_prototype;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleList extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);



        JDBC jdbc = new JDBC();
        ArrayList<String> data = jdbc.getAllArticle();

        TableLayout tl = (TableLayout) findViewById(R.id.ArticleLines);
        for(int i = 0;i<data.size()/5;i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            TextView Title = new TextView(this);
            TextView Date = new TextView(this);
            Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            Date.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));


            Title.setText(data.get(i*5+1));
            Date.setText(data.get(i*5+3));

            Title.setMinimumHeight(40);
            Title.setMinimumWidth(800);
            Date.setMinimumHeight(40);
            Title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            Title.setGravity(Gravity.CENTER);
            Date.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            Date.setGravity(Gravity.CENTER);

            tr.addView(Title);
            tr.addView(Date);


            if(i%2==0)
            tr.setBackgroundColor(Color.GRAY);
            tr.setMinimumHeight(40);

            final Integer finalI = Integer.valueOf(data.get(i*5+0));
            tr.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent intent = new Intent();
                    intent.setClass(ArticleList.this, ArticleDetail.class);
                    intent.putExtra("ID", finalI.toString());
                    startActivity(intent);
                    ArticleList.this.finish();
                }
            });
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }
    }

    public void MoceToPost(View view)
    {
        Intent intent = new Intent();
        intent.setClass(ArticleList.this, PostArticle.class);
        startActivity(intent);
        ArticleList.this.finish();
    }
}
