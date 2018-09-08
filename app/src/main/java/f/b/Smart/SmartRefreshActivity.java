package f.b.Smart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import f.b.R;
import f.base.BaseActivity;
import f.base.BaseRecyclerAdapter;
import f.base.BaseRecyclerHolder;
import m.b.utils.ViewUtils;

/**
 * @author: FYL
 * @time: 2018/9/5
 * @email:347933430@qq.com
 * @describe: f.b.Smart
 */
public class SmartRefreshActivity extends BaseActivity {
    @ViewInject(value = R.id.recycler)
    private RecyclerView recycler;
    @ViewInject(value = R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;
    private List<String> mLists = new ArrayList<>();
    private BaseRecyclerAdapter<String> adapter;
    @Override
    public int bindLayout() {
        return R.layout.smart_refresh_activity;
    }
    @Override
    public void initView(View view) {
        x.view().inject(this);
        //设置管理器
        recycler.setLayoutManager(ViewUtils.getLayoutManager(mContext));
        //设置初始化控件完成后自动获取数据并刷新
        refreshLayout.autoRefresh();
        //打开上拉加载
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            /**
             * 加载更多
             * @param refreshLayout
             */
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                List<String> lsits = getLData();
                adapter.addAll(lsits);
                refreshLayout.finishLoadMore();
            }
            /**
             * 刷新
             * @param refreshLayout
             */
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mLists.clear();
                getRData();
                bindData();
                refreshLayout.finishRefresh();
            }
        });
    }
    /**
     * 绑定适配器
     */
    private void bindData() {
        adapter = new BaseRecyclerAdapter<String>(mContext,mLists,R.layout.recycler_view_item_layout) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int position) {
                holder.setText(R.id.item_data,item+"");
            }
        };
        recycler.setAdapter(adapter);
    }

    @Override
    public void initData(Context mContext) {
    }

    @Override
    public void widgetClick(View v) {
    }

    private void getRData() {
        for(int i=0;i<20;i++){
            mLists.add("这是刷新数据"+i);
        }
    }
    private List<String> getLData() {
        List<String> lists = new ArrayList<>();
        for(int i=0;i<10;i++){
            lists.add("这是加载更多数据"+i);
        }
        return lists;
    }

}
