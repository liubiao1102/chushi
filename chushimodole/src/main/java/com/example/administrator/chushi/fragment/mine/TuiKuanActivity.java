package com.example.administrator.chushi.fragment.mine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.Allbean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.TuiKuanBean;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class TuiKuanActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private TextView tv_check,tijiao_tuikuan;
    private ListView lv_tuikuan;
    private String userid;
    private String token;
    private List<TuiKuanBean.DataBean> data;
    private boolean ischick=true;
    private String yuanyin;
    private String sp_id,name,num,price,pic,tag;
    private EditText edt_beizhu;
    private ImageView iv_tupian;
    private TextView tv_name,tv_num,tv_price,tv_zhuangtai;
    private ImageView img_one;
    private RecyclerView mine_title_recycler;
    private AllDingDanAdapter allDingDanAdapter;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    private Bitmap bm1,bm2,bm3;
    private Boolean isevent = false;

    private ArrayList<LocalMedia> mSelectPath;
    private ImageView img_add;
    private String path="";
    private String path1="";
    private String path2="";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_kuan);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        initView();
        initData();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
        if (messageEvent.getMsg().equals("deleteimg")){
            int num = messageEvent.getNum();
            mSelectPath.remove(num);
            initImage();
        }
    }

    private void initImage() {
        if (mSelectPath.size() >= 3) {
            for (int i = 3; i < mSelectPath.size(); i++) {
                mSelectPath.remove(i);
            }
            img_one.setVisibility(View.GONE);
        } else {
            img_one.setVisibility(View.VISIBLE);
        }
        allDingDanAdapter = new AllDingDanAdapter(R.layout.img_layout, mSelectPath);
        mine_title_recycler.setLayoutManager(new GridLayoutManager(TuiKuanActivity.this, 3));
        mine_title_recycler.setNestedScrollingEnabled(false);
        mine_title_recycler.setAdapter(allDingDanAdapter);
        allDingDanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(TuiKuanActivity.this, FeedBackXQActivity.class);
                intent.putExtra("img", mSelectPath.get(position).getPath());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }

    private void initPath() {
        if (mSelectPath.size() == 0) {
            path = "";
            path1 = "";
            path2 = "";
        } else if (mSelectPath.size() == 1) {
            //压缩，用于节省BITMAP内存空间--解决BUG的关键步骤
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;    //这个的值压缩的倍数（2的整数倍），数值越小，压缩率越小，图片越清晰
            //返回原图解码之后的bitmap对象
            Bitmap bitmap = BitmapFactory.decodeFile(mSelectPath.get(0).getPath(),opts);
            path = MyUtils.Bitmap2StrByBase64(bitmap);
            if(bitmap != null && !bitmap.isRecycled()){
                bitmap.recycle();
                bitmap = null;
            }
            System.gc();
            path1 = "";
            path2 = "";
        } else if (mSelectPath.size() == 2) {
            //压缩，用于节省BITMAP内存空间--解决BUG的关键步骤
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;    //这个的值压缩的倍数（2的整数倍），数值越小，压缩率越小，图片越清晰
            //返回原图解码之后的bitmap对象
            Bitmap bitmap = BitmapFactory.decodeFile(mSelectPath.get(0).getPath(),opts);
            path = MyUtils.Bitmap2StrByBase64(bitmap);
            bitmap.recycle();
            Bitmap bitmap1 = BitmapFactory.decodeFile(mSelectPath.get(1).getPath(),opts);
            path1 = MyUtils.Bitmap2StrByBase64(bitmap1);
            bitmap1.recycle();
            path2 = "";
            if(bitmap != null && !bitmap.isRecycled()){
                bitmap.recycle();
                bitmap = null;
            }else  if(bitmap1 != null && !bitmap1.isRecycled()){
                bitmap1.recycle();
                bitmap1 = null;
            }
            System.gc();
        } else {
            //压缩，用于节省BITMAP内存空间--解决BUG的关键步骤
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;    //这个的值压缩的倍数（2的整数倍），数值越小，压缩率越小，图片越清晰
            //返回原图解码之后的bitmap对象
            Bitmap bitmap = BitmapFactory.decodeFile(mSelectPath.get(0).getPath(), opts);
            path = MyUtils.Bitmap2StrByBase64(bitmap);
            bitmap.recycle();
            Bitmap bitmap1 = BitmapFactory.decodeFile(mSelectPath.get(1).getPath(),opts);
            path1 = MyUtils.Bitmap2StrByBase64(bitmap1);
            bitmap1.recycle();
            Bitmap bitmap2 = BitmapFactory.decodeFile(mSelectPath.get(2).getPath(),opts);
            path2 = MyUtils.Bitmap2StrByBase64(bitmap2);
            bitmap2.recycle();
            System.gc();
        }
    }

    private void initView() {
        mSelectPath = new ArrayList<>();
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        sp_id = getIntent().getStringExtra("sp_id");
        name = getIntent().getStringExtra("name");
        num = getIntent().getStringExtra("num");
        price = getIntent().getStringExtra("price");
        pic = getIntent().getStringExtra("pic");
        tag = getIntent().getStringExtra("tag");
        img_one= (ImageView) findViewById(R.id.img_one);
        img_one.setOnClickListener(this);
        mine_title_recycler = (RecyclerView) findViewById(R.id.mine_title_recycler);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_check= (TextView) findViewById(R.id.tv_check);
        lv_tuikuan= (ListView) findViewById(R.id.lv_tuikuan);
        tijiao_tuikuan= (TextView) findViewById(R.id.tijiao_tuikuan);
        tv_zhuangtai= (TextView) findViewById(R.id.tv_zhuangtai);
        tijiao_tuikuan.setOnClickListener(this);
        edt_beizhu= (EditText) findViewById(R.id.edt_beizhu);
        iv_tupian= (ImageView) findViewById(R.id.iv_tupian);
        tv_name= (TextView) findViewById(R.id.name);
        tv_num= (TextView) findViewById(R.id.num);
        tv_price= (TextView) findViewById(R.id.price);
        Glide.with(TuiKuanActivity.this).load(pic).into(iv_tupian);
        tv_name.setText("商品名称："+name);
        tv_num.setText("商品数量："+num+"/份");
        tv_price.setText("¥："+price);


    }
    private void initData() {
        tv_check.setOnClickListener(this);
        if (tag.equals("1")){
            tv_zhuangtai.setText("待发货");
        }else if (tag.equals("2")){
            tv_zhuangtai.setText("待评论");
        }
        initNet();
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "order/tui_res/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        TuiKuanBean tuiKuanBean = gson.fromJson(s, TuiKuanBean.class);
                        if (tuiKuanBean.getCode()==200){
                            data = tuiKuanBean.getData();
                            lv_tuikuan.setAdapter(new Myadapter());
                            lv_tuikuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    lv_tuikuan.setVisibility(View.GONE);
                                    yuanyin = data.get(position).getYuanyin();
                                    tv_check.setText(yuanyin);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(TuiKuanActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_check:
                lv_tuikuan.setVisibility(View.VISIBLE);
                if (ischick){
                    lv_tuikuan.setVisibility(View.VISIBLE);
                    ischick=false;
                }else {
                    lv_tuikuan.setVisibility(View.GONE);
                    ischick=true;
                }
                break;
            case R.id.tijiao_tuikuan:

                    initNetstart();
                break;
            case R.id.img_one:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
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
        PictureSelector.create(TuiKuanActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(3)// 最大图片选择数量
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
        PictureSelector.create(TuiKuanActivity.this)
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
                    if (localMedias.size() <= 3) {
                        mSelectPath.addAll(localMedias);
                    } else {

                    }
                    initImage();
                    allDingDanAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    private void initNetstart() {
        initPath();
        if (tv_check.getText().toString().equals("选择退款原因")){
            Toast.makeText(this, "请选择退款原因", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog = new ProgressDialog(TuiKuanActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载...");
        dialog.show();
        OkGo.post(MyContants.BASEURL+"Order/tui/")
                .tag(this)
                .params("tui_type",tag)
                .params("order_id",sp_id)
                .params("tuikuanyuanyin", tv_check.getText().toString())
                .params("order_beizhu", edt_beizhu.getText().toString())
                .params("img1", path+"")
                .params("img2", path1+"")
                .params("img3", path2+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Allbean allbean = gson.fromJson(s, Allbean.class);
                        if (allbean.getCode()==200){
                            Toast.makeText(TuiKuanActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            EventBus.getDefault().postSticky(eventMessage);
                        }else {
                            Toast.makeText(TuiKuanActivity.this, allbean.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(TuiKuanActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
    class AllDingDanAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<LocalMedia> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, LocalMedia item) {
            img_add = helper.getView(R.id.img_add);
            Glide.with(TuiKuanActivity.this).load(item.getPath()).into(img_add);
        }
    }

    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(TuiKuanActivity.this,R.layout.mlist_tuikuan_item,null);
            TextView tv_lv_item= (TextView) view.findViewById(R.id.tv_lv_item);
            tv_lv_item.setText(data.get(position).getYuanyin()+"");
            return view;
        }
    }
}
