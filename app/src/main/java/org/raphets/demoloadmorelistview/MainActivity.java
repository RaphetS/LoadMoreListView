package org.raphets.demoloadmorelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoadMoreListView mListView;
    private List<String> mData=new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (LoadMoreListView) findViewById(R.id.listview);
        init();

    }

    private void init() {
        mData.add("jojipoojg");
        mData.add("jojipoojg");
        mData.add("jojipoojg");
        mData.add("jojipoojg");
        mData.add("jojipoojg");
        mData.add("jojipoojg");
        mData.add("jojipoojg");

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData);
        mListView.setAdapter(adapter);
        mListView.setONLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                loadMore();
            }
        });
    }

    private void loadMore() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mData.add("atate");
                mData.add("546646");
                mData.add("546646");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        mListView.setLoading(false);
                    }
                });
            }
        }.start();
    }
}
