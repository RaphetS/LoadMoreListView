package org.raphets.demoloadmorelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by matou0289 on 2016/10/14.
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private View mFootView;
    private int mTotalItemCount;
    private OnLoadMoreListener mLoadMoreListener;
    private boolean mIsLoading=false;

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.mContext=context;
        mFootView= LayoutInflater.from(context).inflate(R.layout.foot_view,null);
        addFooterView(mFootView);
        mFootView.setVisibility(GONE);
        setOnScrollListener(this);
    }


    @Override
    public void onScrollStateChanged(AbsListView listView, int scrollState) {
        // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        int lastVisibleIndex=listView.getLastVisiblePosition();
        if (!mIsLoading&&scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && lastVisibleIndex >=mTotalItemCount-2) {
            mIsLoading=true;
            mFootView.setVisibility(VISIBLE);
            if (mLoadMoreListener!=null) {
                mLoadMoreListener.onloadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount=totalItemCount;
    }

    public void setONLoadMoreListener(OnLoadMoreListener listener){
        mLoadMoreListener=listener;
    }

    public interface OnLoadMoreListener{
        void onloadMore();
    }
    public void setLoading(boolean isLoading){
        mIsLoading=isLoading;
        mFootView.setVisibility(GONE);
    }
}
