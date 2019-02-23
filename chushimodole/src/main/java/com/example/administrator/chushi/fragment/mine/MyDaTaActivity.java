package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.MineDataBean;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.example.administrator.chushi.view.CircleImageView;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MyDaTaActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_name;
    private ImageView tv_back;
    private TextView tv_name;
    private String userid;
    private String token;
    private CircleImageView mydata_img;
    private TextView mymobilephone;
    private Button btn_unlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_da_ta_hou);
        initView();
        initData();
    }
    private void initView() {
        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        mydata_img= (CircleImageView) findViewById(R.id.mydata_img);
        mydata_img.setOnClickListener(this);
        ll_name= (LinearLayout) findViewById(R.id.ll_name);
        ll_name.setOnClickListener(this);
        mydata_img= (CircleImageView) findViewById(R.id.mydata_img);
        mymobilephone= (TextView) findViewById(R.id.mymobilephone);
        btn_unlogin= (Button) findViewById(R.id.btn_unlogin);
        btn_unlogin.setOnClickListener(this);
        initNet();
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL+"User/detail/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        MineDataBean mHomeFragmentBean = gson.fromJson(s, MineDataBean.class);
                        MineDataBean.DataBean data = mHomeFragmentBean.getData();
                        if (mHomeFragmentBean.getCode()==200){
                            Glide.with(MyDaTaActivity.this).load(data.getFace()+"").into(mydata_img);
                            tv_name.setText(data.getUsername()+"");
                            mymobilephone.setText(data.getMobile()+"");
                        }else {

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MyDaTaActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }


    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mydata_img:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.ll_name:
                Intent intent=new Intent(MyDaTaActivity.this,ModifyNameActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_unlogin:
                MyUtils.cleanSharedPreference(this);//清除所有的sp数据

//                removeAllActivitys();
                SharedPreferencesUtils.getInstace(MyDaTaActivity.this).setStringPreference("userid", "");
                SharedPreferencesUtils.getInstace(MyDaTaActivity.this).setStringPreference("token", "");
//                SharedPreferencesUtils.getInstace(SettingsActivity.this).setStringPreference("logindata", "");
                String userid = SharedPreferencesUtils.getInstace(MyDaTaActivity.this).getStringPreference("userid", "");
                if (userid.equals("")){
//                    startActivity(new Intent(MyDaTaActivity.this, MainActivity.class));
                    EventMessage eventMessage = new EventMessage();
                    eventMessage.setMsg("back");
                    EventBus.getDefault().postSticky(eventMessage);

                    EventMessage eventMessageone = new EventMessage();
                    eventMessageone.setMsg("clearcar");
                    EventBus.getDefault().postSticky(eventMessageone);

                    finish();

                }else {
                    Toast.makeText(this, "退出失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_selectphoto)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_pai = dialog.getView(R.id.tv_pai);
        tv_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCamera();
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_tuku = dialog.getView(R.id.tv_tuku);
        tv_tuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPhoto();
                //关闭dialog
                dialog.close();
            }
        });
    }
    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(MyDaTaActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(list)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    private void requestCamera() {
        PictureSelector.create(MyDaTaActivity.this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .selectionMedia(list)// 是否传入已选图片
                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String param = data.getStringExtra("param");
            if (param!=null){
                if (param.toString().equals("")){

                }else {
                    tv_name.setText(param+"");
                }
            }

        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    String compressPath = localMedias.get(0).getPath();
                    MyUtils.syso("照片地址是：" + compressPath);
//                Toast.makeText(this, "照片地址是：" + compressPath, Toast.LENGTH_SHORT).show();
//        SpUtils.putString(this, "userhead", compressPath);
                    Glide.with(this).load(compressPath).into(mydata_img);
                    Bitmap bitmap = BitmapFactory.decodeFile(compressPath);

                    OkGo.post(MyContants.BASEURL+"User/update/")
                            .tag(this)
                            .params("userid",userid)
                            .params("token",token)
                            .params("face", MyUtils.Bitmap2StrByBase64(bitmap))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    MineDataBean mineDataBean = gson.fromJson(s, MineDataBean.class);
                                    String face = mineDataBean.getData().getFace();
                                    Log.d("Mydataactivity","------------------"+face+"------------------------");
                                    SharedPreferencesUtils.getInstace(MyDaTaActivity.this).setStringPreference("face",face);
                                    EventMessage eventMessage = new EventMessage();
                                    eventMessage.setMsg("face");
                                    EventBus.getDefault().postSticky(eventMessage);
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    Toast.makeText(MyDaTaActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                                    Log.e("请求失败", "失败原因：" + response);
                                }
                            });
                    break;
            }
        }
    }

}
