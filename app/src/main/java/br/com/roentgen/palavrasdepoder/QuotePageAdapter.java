package br.com.roentgen.palavrasdepoder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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

        return layout;
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
