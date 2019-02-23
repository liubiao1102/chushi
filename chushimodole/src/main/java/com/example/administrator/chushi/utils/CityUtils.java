package com.example.administrator.chushi.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.example.administrator.chushi.CityNameInterface;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.bean.AllCityBean;
import com.example.administrator.chushi.bean.EventMessageOne;
import com.example.administrator.chushi.bean.ProvinceBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/8.
 */

public class CityUtils {
    //城市列表选择
    OptionsPickerView optionsPickerView;

    ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String id = "17";
    private String id1 = "1701";
    private ArrayList<AllCityBean> allCityBeen;
    private ArrayList<String> strarrayList;
    private ArrayList<ArrayList<String>> arrayLists;


    private static class CityHelper {
        private static CityUtils INSTANCE = new CityUtils();
    }


    public static CityUtils getInstance() {
        return CityHelper.INSTANCE;
    }

    //获取全国所有城市列表
    public void getAllCity(Context context, final CityNameInterface cityNameInterface) {

        optionsPickerView = new OptionsPickerView(context);

//        AllAreaBean allAreaBean = null;

        try {
            InputStream inputStream = context.getAssets().open("AllCity.txt");
//            InputStream inputStream = context.getAssets().open("province.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String city = new String(buffer);
//            Toast.makeText(context, city, Toast.LENGTH_SHORT).show();
            initNetqu(context, cityNameInterface);


//            allAreaBean = JSON.parseObject(city, AllAreaBean.class);


//            LogUtils.i(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String userid;
    private String token;

    public static ArrayList<AllCityBean> fromJson(String json) {
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<AllCityBean>>() {
        }.getType();
        return gson.fromJson(json, objectType);
    }
    private String[] strings= new String[]{};
    private void initNetqu(final Context context, final CityNameInterface cityNameInterface) {
        OkGo.get(MyContants.BASEURL + "Area/getArea")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        allCityBeen = fromJson(s);
                        if (options1Items.size()>0){
                            options1Items.clear();
                        }
                        //添加一级省份
                        for (int i = 0; i < allCityBeen.size(); i++) {
                            ProvinceBean provinceBean = new ProvinceBean();
                            provinceBean.setName(allCityBeen.get(i).getName());
                            strarrayList = new ArrayList<String>();
                            arrayLists = new ArrayList<ArrayList<String>>();
                            options1Items.add(provinceBean);
                            for (int i1 = 0; i1 < allCityBeen.get(i).getChild().size(); i1++) {
                                strarrayList.add(allCityBeen.get(i).getChild().get(i1).getName());
                                List<String> list = new ArrayList<String>();
                                    for (int i2 = 0; i2 < allCityBeen.get(i).getChild().get(i1).getChild().size(); i2++) {
                                            list.add(allCityBeen.get(i).getChild().get(i1).getChild().get(i2).getName());
                                    }
                                arrayLists.add((ArrayList<String>) list);

                            }
                        options2Items.add(strarrayList);
                            options3Items.add(arrayLists);
                        }

                        //三级联动效果
                        optionsPickerView.setPicker(options1Items, options2Items, options3Items, true);
                        optionsPickerView.setTitle(context.getString(R.string.action_settings));
                        //设置是否有滚动效果
                        optionsPickerView.setCyclic(false, false, false);
                        optionsPickerView.show();
                        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                //返回的分别是三个级别的选中位置  判断省 和 城市 是否重名
                                String area = "";
                                if (options1Items.get(options1).getName().equals(options2Items.get(options1).get(option2))) {
                                    area = options1Items.get(options1).getName() + " "
                                            + options3Items.get(options1).get(option2).get(options3) + "  ";
                                } else {
                                    area = options1Items.get(options1).getName() + " "
                                            + options2Items.get(options1).get(option2) + " "
                                            + options3Items.get(options1).get(option2).get(options3) + "  ";
                                }
                                //获取所有的城市名称
                                cityNameInterface.getCityName(area);
                                //市id
                                String id1 = allCityBeen.get(options1).getId();
//                                    c_id = mHomeFragmentBean.getData().get(options1).getId();
                                //区id
                                String id2 = allCityBeen.get(options1).getChild().get(option2).getId();
                                //县id
                                String id3 = allCityBeen.get(options1).getChild().get(option2).getChild().get(options3).getId();
                                EventMessageOne eventMessage = new EventMessageOne();
                                eventMessage.setMsg(id1);
                                eventMessage.setMsgone(id2);
                                eventMessage.setMsgtwo(id3);
                                EventBus.getDefault().postSticky(eventMessage);
                            }

                        });
                        optionsPickerView.setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismiss(Object o) {
                            }
                        });
//                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(context, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });


    }

}
