package br.com.roentgen.palavrasdepoder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_APPEND;

public class QuotePageAdapter extends PagerAdapter{

    ArrayList<Quote> list;
    Context context;

    public QuotePageAdapter(ArrayList<Quote> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Quote object = list.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slide_layout, container, false);

        TextView quote = layout.findViewById(R.id.quoteHolder);
        TextView author = layout.findViewById(R.id.authorHolder);

        quote.setText(object.getQuote());
        author.setText(object.getAuthor());

        ImageButton btn = layout.findViewById(R.id.shareButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toShare = object.getQuote() + " by " + object.getAuthor() + " send using Palavras de Poder";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, toShare);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

        ImageButton btn1 = layout.findViewById(R.id.likeButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fout = context.openFileOutput("likedQuotes.txt", MODE_APPEND);
                    PrintWriter printWriter = new PrintWriter(fout, true);
                    printWriter.println(object.getId());
                    printWriter.close();

                    Scanner scanner = new Scanner(context.openFileInput("likedQuotes.txt" ));

                    while (scanner.hasNext()) {
                        Log.d("QuotePagerAdapter", scanner.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
}
