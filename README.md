#支持加载更多的ListView
![image](https://github.com/RaphetS/LoadMoreListView/blob/master/%E6%95%88%E6%9E%9C%E5%9B%BE/%E4%B8%8A%E6%8B%89%E5%8A%A0%E8%BD%BD.gif)

#使用方法
布局文件

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

 <org.raphets.demoloadmorelistview.LoadMoreListView
     android:id="@+id/listview"
     android:layout_width="match_parent"
     android:layout_height="200dp">
 </org.raphets.demoloadmorelistview.LoadMoreListView>
</RelativeLayout>
```

Activity中使用

```
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
```

**加载完成，记得调用setLoading(false);**
