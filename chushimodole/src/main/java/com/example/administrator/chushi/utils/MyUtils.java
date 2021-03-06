package com.example.administrator.chushi.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    private static int mScreenWidth, mScreenHeight;

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /**
     * 根据图片的url路径获得Bitmap对象
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr =unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }


    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
    // base64图片转字符串
    public static String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
    public static String getUserid(Context context){
        String userid = SharedPreferencesUtils.getInstace(context).getStringPreference("userid", "");
        return userid;
    }
    public static String getToken(Context context){
        String userid = SharedPreferencesUtils.getInstace(context).getStringPreference("token", "");
        return userid;
    }
    public static boolean dologin(Context context) {
        String userid = SpUtils.getString(context, "userid", "");
        if (TextUtils.isEmpty(userid)) {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getPsw(String psw) {
        return getMD5(getMD5(psw)) + "fujuwang";
    }


    /*将日期转为时间戳*/
    public static long getStringToDate(String time) {
        //        String timestamp = String.format("%010d", stringToDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    //  时间戳转为日期  /年/月/日/时/分
    public static String getDateToStringTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
    //    long stringToDate = MyUtils.getStringToDate(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日")/1000;
    //    String timestamp = String.format("%010d", stringToDate);


    //    String yuyue_time = item.getYuyue_time();
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
    //    long lcc_time = Long.valueOf(yuyue_time);
    //    String format = sdf.format(new Date(lcc_time * 1000L));

    //手机号判断逻辑
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //正则6-16位数字或字母
    public static boolean isPassMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    //写这个方法主要是想当发布release版本的时候不会输出日志
    public static void syso(String str) {
        System.out.print(str);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        return mScreenWidth;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenHeight = metrics.heightPixels;
        return mScreenHeight;
    }

    //启动应用的设置，跳转到应用的设置界面
    public static void startAppSettingsResult(Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, 0);
    }

    //得到此设备的设备信息，这里用到的是方便进行友盟的集成测试
    public static String getDeviceInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = manager.getDeviceId();
        return deviceId;
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppPackageName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

    /**
     * 获取程序的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        //包管理操作管理类
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getAppPackageName(context), 0);
            return packinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
