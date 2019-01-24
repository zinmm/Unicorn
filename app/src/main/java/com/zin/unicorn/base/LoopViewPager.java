package com.zin.unicorn.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by ZhuJinMing on 2019/01/18
 */
public class LoopViewPager extends ViewPager implements Runnable {

    //无限大的PagerAdapter
    private LoopPagerAdapter loopAdapter;
    private PagerAdapter adapter;

    //自动滚屏时间间隔
    private int POST_DELAYED_TIME = 2000;
    //自动滚屏开关
    private boolean isLoop = true;
    //解决 notifyDataSetChanged 不刷新数据问题
    private boolean isNotify = false;

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            //修改PagerAdapter为无限大
            loopAdapter = new LoopPagerAdapter(adapter);
            this.adapter = adapter;
        }
        super.setAdapter(loopAdapter);
        startLoop();
    }

    @Override
    public PagerAdapter getAdapter() {
        return adapter;
    }

    public LoopPagerAdapter getLoopAdapter() {
        return loopAdapter;
    }

    /**
     * 解决数据刷新问题
     */
    public void notifyDataSetChanged() {
        isNotify = true;
        loopAdapter.notifyDataSetChanged();
        if (isLoop) {
            stopLoop();
            startLoop();
        }
    }

    /**
     * 设置自动滚屏时间
     *
     * @param time
     */
    public void setLoopTime(int time) {
        POST_DELAYED_TIME = time;
    }

    /**
     * 开启和关闭滚屏
     *
     * @param isLoop
     */
    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        if (isLoop) {
            startLoop();
        } else {
            startLoop();
        }
    }

    private void startLoop() {
        stopLoop();
        postDelayed(this, POST_DELAYED_TIME);
    }

    private void stopLoop() {
        removeCallbacks(this);
    }

    /**
     * 获取当前的item
     *
     * @return
     */
    public int getLoopCurrentItem() {
        int curent_item = 0;
        if (adapter != null && getCurrentItem() != 0 && adapter.getCount() != 0) {
            if (getCurrentItem() >= adapter.getCount()) {
                curent_item = getCurrentItem() % adapter.getCount();
            }
        }
        return curent_item;
    }

    // 自动滚动关键
    @Override
    public void run() {
        if (!isLoop) {
            return;
        }
        if (getAdapter() != null && getAdapter().getCount() > 1) {
            setCurrentItem(getCurrentItem() + 1);
        }
        startLoop();
    }

    /**
     * 当touch时取消自动滚动
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            stopLoop();
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            if (isLoop) {
                startLoop();
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 无限大的pagerAdapter
     *
     * @author sdsw
     */
    class LoopPagerAdapter extends PagerAdapter {

        private PagerAdapter adapter;

        LoopPagerAdapter(PagerAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return adapter.isViewFromObject(arg0, arg1);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            int count = adapter.getCount();
            int new_position = 0;
            if (count != 0)
                new_position = position % adapter.getCount();
            adapter.destroyItem(container, new_position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int count = adapter.getCount();
            int new_position = 0;
            if (count != 0)
                new_position = position % adapter.getCount();
            return adapter.instantiateItem(container, new_position);
        }

        @Override
        public int getItemPosition(Object object) {
            if (isNotify) {
                isNotify = false;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }
    }

}