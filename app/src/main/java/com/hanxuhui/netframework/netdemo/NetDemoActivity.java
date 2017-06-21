package com.hanxuhui.netframework.netdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hanxuhui.netframework.R;
import com.hanxuhui.netframework.application.Constants;
import com.hanxuhui.netframework.base.BaseActivity;
import com.hanxuhui.netframework.protocol.NetRequest;
import com.hanxuhui.netframework.protocol.bean.Contact;
import com.hanxuhui.netframework.protocol.bean.Location;
import com.hanxuhui.netframework.protocol.bean.Locations;
import com.hanxuhui.netframework.protocol.bean.Member;
import com.hanxuhui.netframework.protocol.bean.Messages;
import com.hanxuhui.netframework.protocol.bean.Response;
import com.hanxuhui.netframework.protocol.bean.User;
import com.hanxuhui.netframework.protocol.filedownload.FileDownloadManager;
import com.hanxuhui.netframework.protocol.network.ZaSubscriber;
import com.hanxuhui.netframework.util.ToastUtil;
import com.hanxuhui.netframework.util.ZaLog;
import com.hanxuhui.netframework.util.ZaUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetDemoActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.tv_message_list)
    TextView tvMessageList;
    @Bind(R.id.tv_load_other_location)
    TextView tvLoadOtherLocation;
    @Bind(R.id.tv_upload_locations)
    TextView tvUploadLocations;
    @Bind(R.id.tv_contact_list)
    TextView tvContactList;
    @Bind(R.id.tv_save_image_to_local)
    TextView tvSaveImageToLocal;
    @Bind(R.id.tv_upload_image)
    TextView tvUploadImage;
    @Bind(R.id.tv_download_image)
    TextView tvDownloadImage;
    @Bind(R.id.tv_upload_file)
    TextView tvUploadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                initData();
            }
        });

        initView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_demo;
    }

    private void initView() {
        ZaLog.d("-----------NetDemoActivity---------------initView--------------");
    }

    private void initData() {
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvMessageList.setOnClickListener(this);
        tvLoadOtherLocation.setOnClickListener(this);
        tvUploadLocations.setOnClickListener(this);
        tvContactList.setOnClickListener(this);
        tvSaveImageToLocal.setOnClickListener(this);
        tvUploadImage.setOnClickListener(this);
        tvDownloadImage.setOnClickListener(this);
        tvUploadFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ZaLog.d("----------------onClick------------------");
        switch (view.getId()) {
            case R.id.tv_login:
                User user = new User();
                user.mobile = "15123879601";
                user.vcode = "123456";
                NetRequest.login(user, new ZaSubscriber<Response<User>>() {
                    @Override
                    protected void onCallBack(Response<User> response) {
                        User user = response.returnData;
                        System.out.println(user.toString());
                    }

                });
                break;
            case R.id.tv_register:
                Map<String, String> map = new HashMap<>();
                map.put("mobile", "15123879601");
                map.put("vcode", "123456");
                NetRequest.register(map, new ZaSubscriber<Response<User>>() {

                    @Override
                    protected void onCallBack(Response<User> response) {
                        User user = response.returnData;
                        System.out.println(user.toString());
                    }

                });
                break;
            case R.id.tv_message_list:
                NetRequest.getMessageList("0", "20", new ZaSubscriber<Response<Messages>>() {

                    @Override
                    protected void onCallBack(Response<Messages> response) {
                        ZaLog.d("-------------onNext---------------------");
                        List<Messages> messageList = (List<Messages>)response.returnData;
                        for(Messages message : messageList) {
                            System.out.println(message.toString());
                        }
                    }

                });
                break;
            case R.id.tv_load_other_location:
                NetRequest.loadOtherLocation("70080", new ZaSubscriber<Response<Member>>() {

                    @Override
                    protected void onCallBack(Response<Member> response) {
                        ZaLog.d("-------------onNext---------------------");
                        Member member = response.returnData;
                        System.out.println(member.toString());
                    }

                });
                break;
            case R.id.tv_upload_locations:
                List<Location> locationList = new ArrayList<>();
                //"address":"北京市西城区武定侯街靠近中国银行(北京金融中心支行)","altitude":"0.0","latitude":"39.918104","longtitude":"116.358343","priority":0,"scene":1,"timestamp":"2016-07-11 15:47:00"}
                locationList.add(new Location(116.358343, 39.918104, 0.0, "北京市西城区武定侯街靠近中国银行(北京金融中心支行)", "0", "1", "2016-07-11 15:47:00"));
                locationList.add(new Location(116.358343, 39.918104, 0.0, "北京市西城区武定侯街靠近中国银行(北京金融中心支行)", "0", "1", "2016-07-11 15:47:00"));
                locationList.add(new Location(116.358343, 39.918104, 0.0, "北京市西城区武定侯街靠近中国银行(北京金融中心支行)", "0", "1", "2016-07-11 15:47:00"));
                Locations locations = new Locations(locationList);
                NetRequest.uploadLocations(locations, new ZaSubscriber<Response>() {

                    @Override
                    protected void onCallBack(Response response) {
                        ZaLog.d("-------------onNext---------------------");
                        ToastUtil.showLong("上传成功");
                    }

                });
                break;
            case R.id.tv_contact_list:
                NetRequest.getContacts(new ZaSubscriber<Response<List<Contact>>>() {

                    @Override
                    protected void onCallBack(Response response) {
                        ZaLog.d("-------------onNext---------------------");
                        List<Contact> contactList = (List<Contact>)response.returnData;
                        for(Contact contact : contactList) {
                            System.out.println(contact.toString());
                        }
                    }

                });
                break;
            case R.id.tv_save_image_to_local:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, Constants.GALLERY);
                break;
            case R.id.tv_upload_image:
                NetRequest.uploadImage(ZaUtil.getImagesFilePath() + "head.png", new ZaSubscriber<Response>() {
                    @Override
                    protected void onCallBack(Response response) {
                        //上传成功
                        ToastUtil.showLong("上传成功");
                    }
                });
                break;
            case R.id.tv_download_image:
                new FileDownloadManager().startDownload();
                break;
            case R.id.tv_upload_file:
//                new DownloadDemo().upload();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.GALLERY:
                if (data != null) {
                    startPhotoZoom(data.getData(), 150);
                }
                break;
            case Constants.PHOTO_REQUEST_CUT:
                ToastUtil.showShort("保存成功");
                break;
        }
    }

    /**
     * 图片裁剪
     *
     * @param uri
     * @param size
     */
    private void startPhotoZoom(Uri uri, int size) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //指定剪切后的图片存储路径
            String desImagePath = ZaUtil.getImagesFilePath() + "head.png";
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(desImagePath)));
            // outputX,outputY 是剪裁图片的宽高
            intent.putExtra("outputX", size);
            intent.putExtra("outputY", size);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("scale", true);
            startActivityForResult(intent, Constants.PHOTO_REQUEST_CUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
