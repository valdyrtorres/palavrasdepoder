package br.com.roentgen.palavrasdepoder;

import android.content.res.AssetManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Quote> list = new ArrayList<>();

        AssetManager manager = getAssets();
        try {
            InputStream in = manager.open("data.txt");
            Scanner scn = new Scanner(in);

            while(scn.hasNext()) {
                String temp = scn.nextLine();
                int quoteID = Integer.parseInt(temp.split("[|]")[0]);
                String quote = temp.split("[|]")[1];
                String author = temp.split("[|]")[2];
                Log.d("MainActivity", quote + "---by " + author);

                Quote qt = new Quote(quoteID, quote, author);
                list.add(qt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewPager viewPager = findViewById(R.id.viewpager);
        QuotePageAdapter adapter = new QuotePageAdapter(list,this);
        viewPager.setAdapter(adapter);

    }
}
