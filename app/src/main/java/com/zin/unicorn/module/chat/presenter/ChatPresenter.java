package com.zin.unicorn.module.chat.presenter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.chat.adapter.ChatAdapter;
import com.zin.unicorn.module.chat.view.ChatView;
import com.zin.unicorn.pojo.ChatPoJo;
import com.zin.unicorn.pojo.UserPoJo;

import java.io.File;

import static android.provider.MediaStore.EXTRA_OUTPUT;
import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Create by ZhuJinMing on 2018/06/26
 */
public class ChatPresenter extends BasePresenter<ChatView> {

    private final static int IMAGE_CROP_CODE = 2348;
    public final static int REQUEST_CAMERA = 52348;

    private ChatAdapter chatAdapter;

    // 初始化
    public void init() {

        chatAdapter = getChatAdapter();

        // 初始化键盘
        initKeyboardEvent();

        for (int i = 0; i < 10; i++) {

            UserPoJo userPoJo = new UserPoJo();
            userPoJo.setId(i % 2);
            ChatPoJo chatPoJo = new ChatPoJo();
            chatPoJo.setContent("dsksfd" + i);
            chatPoJo.setDate(i + "时间");
            chatPoJo.setUserPoJo(userPoJo);

            chatAdapter.addItem(chatPoJo);
        }

    }

    // 回调数据
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case REQUEST_CAMERA:
                photoCrop(Environment.getExternalStorageDirectory().getPath() + "/x.jpg");
                break;

            case IMAGE_CROP_CODE:

                break;
        }
    }

    private void photoCrop(String imagePath) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/xxx.jpg");
//        cropImagePath = file.getAbsolutePath();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(getImageContentUri(new File(imagePath)), "image/*");
        intent.putExtra("photoCrop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 1280);
        intent.putExtra("outputY", 1280);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        mActivity.startActivityForResult(intent, IMAGE_CROP_CODE);
    }

    /**
     * 转换 content:// uri
     *
     * @param imageFile
     * @return
     */
    private Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = mApplicationContext.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return mApplicationContext.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private ChatAdapter getChatAdapter() {

        if (chatAdapter == null) {
            chatAdapter = new ChatAdapter(mContext);
            getView().mChatListRecyclerView().setAdapter(chatAdapter);
            getView().mChatListRecyclerView().setLayoutManager(
                    new LinearLayoutManager(mContext, VERTICAL, false));
        }

        return chatAdapter;
    }

    public void setTopBarViewContent(LinearLayout leftLinearLayout,
                                     LinearLayout centerLinearLayout,
                                     LinearLayout rightLinearLayout) {


    }

    /**
     * 初始化键盘
     */
    private void initKeyboardEvent() {

        getView().mChatContentEditText().setFocusable(true);
        getView().mChatContentEditText().setFocusableInTouchMode(true);
        getView().mChatContentEditText().requestFocus();

        getView().mChatContentEditText().setOnKeyListener((v, keyCode, event) -> {

            if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {

                getView().mChatContentEditText().setText("");

                String content = getView().mChatContentEditText().toString();
                UserPoJo userPoJo = new UserPoJo();
                userPoJo.setId(1);
                ChatPoJo chatPoJo = new ChatPoJo();
                chatPoJo.setContent(content);
                chatPoJo.setDate("时间");
                chatPoJo.setUserPoJo(userPoJo);
                getChatAdapter().addItem(chatPoJo);
                getChatAdapter().notifyDataSetChanged();
                getView().mChatListRecyclerView().smoothScrollToPosition(getChatAdapter().getItemCount() - 1);

                return true;
            }
            return false;
        });
    }
}
