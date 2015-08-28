package com.listindex.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.listindex.project.adapter.ItemAdapter;
import com.listindex.project.bean.User;
import com.listindex.project.view.IndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends Activity {
    private ListView listView;
    private IndexView indexView;
    /**
     * 列表数据
     */
    ArrayList<User> itemArray = getListData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        indexView = (IndexView) findViewById(R.id.index);
        TextView selectIndexView = (TextView) findViewById(R.id.select_index);
        indexView.init(listView, selectIndexView);

        ItemAdapter itemAdapter = new ItemAdapter(this, itemArray, indexView);
        listView.setAdapter(itemAdapter);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int position;

            /**
             * 滚动状态改变时调用
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 不滚动时保存当前滚动到的位置
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                }

            }

            /**
             * 滚动时调用
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (null != indexView) {
                        position = listView.getFirstVisiblePosition();
                        int i=0;
                        for(String type:ItemAdapter.IndexArrar){
                            if(type.equals(((User)listView.getAdapter().getItem(position)).getType())){
                                i++;
                                break;
                            }
                            i++;
                        }
                        indexView.changeTextViewState(i,false);
                        System.out.println("位置:" + i);
                    }
                }
        });
    }

    private ArrayList<User> getListData() {
        ArrayList<User> arrayList=new ArrayList<User>();
        for(String type:ItemAdapter.IndexArrar)
        for(int i=0;i<10;i++){
            User user=new User();
            user.setType(type);
            user.setName(type + "_item" + i);
            arrayList.add(user);
        }
        return arrayList;
    }


}
