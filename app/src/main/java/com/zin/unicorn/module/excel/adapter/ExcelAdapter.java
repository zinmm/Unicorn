package com.zin.unicorn.module.excel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zin.unicorn.pojo.ExcelPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujinming on 2017/7/24.
 */
public class ExcelAdapter extends BaseAdapter {

    private Context mContext;
    private List<ExcelPojo> excelPojos;

    public ExcelAdapter(Context context) {
        this.mContext = context;
        this.excelPojos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return excelPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return excelPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        ExcelPojo excelPojo = excelPojos.get(position);
        String createTime = excelPojo.getCreateTime();
        String fileName = excelPojo.getFileName();
        String fileSize = excelPojo.getFileSize();

        TextView createTimetextView = new TextView(mContext);
        createTimetextView.setText(createTime);

        TextView fileNametextView = new TextView(mContext);
        fileNametextView.setText(fileName);

        TextView fileSizetextView = new TextView(mContext);
        fileSizetextView.setText(fileSize);

        linearLayout.addView(createTimetextView);
        linearLayout.addView(fileNametextView);
        linearLayout.addView(fileSizetextView);

        return linearLayout;
    }

    public void setData(List<ExcelPojo> excelPojos) {
        this.excelPojos = excelPojos;
    }

    public void addItem(ExcelPojo item) {
        excelPojos.add(item);

    }
}
