package com.listindex.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.listindex.project.adapter.ItemAdapter;
import com.listindex.project.view.IndexComparator;
import com.listindex.project.view.IndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends Activity {
    private ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Item> itemArray = new ArrayList<Item>();
        itemListView = (ListView) findViewById(R.id.item_list);
        for (int i = 0; i < 300; i++) {
            itemArray.add(new Item(generateRandomWord(), R.mipmap.ic_launcher));
        }

        Collections.sort(itemArray, new IndexComparator());
        ItemAdapter itemAdapter = new ItemAdapter(this, itemArray,((IndexView)findViewById(R.id.index)));

        TextView selectIndexView = (TextView) findViewById(R.id.select_index);

        IndexView indexView = (IndexView) findViewById(R.id.index);
        indexView.init(itemListView, selectIndexView);

        itemListView.setAdapter(itemAdapter);
    }

    Random random = new Random();

    private String generateRandomWord() {

        int length = 1 + random.nextInt(5);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (32 + random.nextInt(127 - 32)));
        }
        return sb.toString();
    }

}
