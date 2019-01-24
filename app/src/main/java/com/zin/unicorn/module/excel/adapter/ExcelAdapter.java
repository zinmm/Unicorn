package com.zin.unicorn.module.excel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zin.unicorn.pojo.ExcelPoJo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhuJinMing on 2017/7/24.
 */
public class ExcelAdapter extends BaseAdapter {

    private Context context;
    private List<ExcelPoJo> excelPoJos;

    public ExcelAdapter(Context context) {
        this.context = context;
        this.excelPoJos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return excelPoJos.size();
    }

    @Override
    public Object getItem(int position) {
        return excelPoJos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        ExcelPoJo excelPoJo = excelPoJos.get(position);
        String createTime = excelPoJo.getCreateTime();
        String fileName = excelPoJo.getFileName();
        String fileSize = excelPoJo.getFileSize();

        TextView createTimetextView = new TextView(context);
        createTimetextView.setText(createTime);

        TextView fileNametextView = new TextView(context);
        fileNametextView.setText(fileName);

        TextView fileSizetextView = new TextView(context);
        fileSizetextView.setText(fileSize);

        linearLayout.addView(createTimetextView);
        linearLayout.addView(fileNametextView);
        linearLayout.addView(fileSizetextView);

        return linearLayout;
    }

    public void setData(List<ExcelPoJo> excelPoJos) {
        this.excelPoJos = excelPoJos;
    }

    public void addItem(ExcelPoJo item) {
        excelPoJos.add(item);

    }
}
