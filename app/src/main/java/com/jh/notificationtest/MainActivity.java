package com.jh.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.ycbjie.notificationlib.NotificationUtils;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/yangchong211/YCNotification
 * 通知栏封装库，链式编程调用，解决了8.0以上通知栏不显示问题，兼容老版本
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.tv_13)
    TextView tv13;
    @BindView(R.id.tv_14)
    TextView tv14;
    @BindView(R.id.tv_15)
    TextView tv15;

    private NotificationManager mNotificationManager;


    public static final int NOTIFICATION_REQUEST_CODE = 12;
    public static final String NOTIFICATION_ACTION = "action";


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ToastUtils.showShort("onNewIntent方法执行");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkOpenNotification();

        // 创建一个NotificationManager的引用
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    private void checkOpenNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!NotificationUtils.isNotificationEnabled(this)) {
                new CircleDialog.Builder()
                        .setTitle("您还未开启系统通知，可能会影响消息的接收，要去开启吗？")
                        .setTitleColor(getResources().getColor(R.color.black))
                        .setWidth(0.8f)

                        .setCancelable(false)
                        .setPositive("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // 跳转权限设置
                                NotificationUtils.gotoSet(MainActivity.this);
                            }
                        })
                        .setNegative("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .show(getSupportFragmentManager());
            } else {
            }
        }
    }



    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5,
            R.id.tv_6, R.id.tv_7, R.id.tv_8, R.id.tv_9, R.id.tv_10, R.id.tv_11,
            R.id.tv_12, R.id.tv_13, R.id.tv_14, R.id.tv_15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                cancelAllNotification();
                break;
            case R.id.tv_2:
                sendNotification1();
                break;
            case R.id.tv_3:
                sendNotification2();
                break;
            case R.id.tv_4:
                sendNotification3();
                break;
            case R.id.tv_5:
                sendNotification4();
                break;
            case R.id.tv_6:
                Intent intent = new Intent(this, ReminderReceiver.class);
                sendBroadcast(intent);
                break;
            case R.id.tv_7:
                Toast.makeText(this, "未做", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_8:
                sendNotification8();
                break;
            case R.id.tv_9:
                sendNotification9();
                break;
            case R.id.tv_10:
                sendNotification10();
                break;
            case R.id.tv_11:
                sendNotification11();
                break;
            case R.id.tv_12:
                sendNotification12();
                break;
            case R.id.tv_13:
                sendNotification13();
                break;
            case R.id.tv_14:
                sendNotification14();
                break;
            case R.id.tv_15:
                sendNotification15();
                break;
        }
    }


    private void cancelAllNotification() {
//        NotificationUtils notificationUtils = new NotificationUtils(this);
//        notificationUtils.clearNotification();
        NotificationUtils.getInstance(this).clearNotification();
    }


    private void sendNotification1() {
        //这三个属性是必须要的，否则异常
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification(1, "这个是标题", "这个是内容", R.mipmap.ic_launcher);
    }


    private void sendNotification2() {
        //处理点击Notification的逻辑
        //创建intent
        Intent resultIntent = new Intent(this, TestActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what", 2);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 2, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 定义Notification的各种属性
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                .setContentIntent(resultPendingIntent)
                .sendNotificationCompat(2, "这个是标题2", "这个是内容2", R.mipmap.ic_launcher);
    }


    private void sendNotification3() {
        long[] vibrate = new long[]{0, 500, 1000, 1500};
        //处理点击Notification的逻辑
        //创建intent
        Intent resultIntent = new Intent(this, TestActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what", 3);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 3, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //发送pendingIntent

        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                //让通知左右滑的时候是否可以取消通知
                .setOngoing(true)
                //设置内容点击处理intent
                .setContentIntent(resultPendingIntent)
                //设置状态栏的标题
                .setTicker("来通知消息啦")
                //设置自定义view通知栏布局
                .setContent(getRemoteViews())
                //设置sound
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                //设置优先级
                .setPriority(Notification.PRIORITY_DEFAULT)
                //自定义震动效果
                .setVibrate(vibrate)
                //必须设置的属性，发送通知
                .sendNotification(3, "这个是标题3", "这个是内容3", R.mipmap.ic_launcher);
    }


    private void sendNotification4() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.setContent(getRemoteViews());
        Notification notification = notificationUtils.getNotification("这个是标题4", "这个是内容4", R.mipmap.ic_launcher);
        notificationUtils.getManager().notify(4, notification);
    }


    private RemoteViews getRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_mobile_play);
        // 设置 点击通知栏的上一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_pre, getActivityPendingIntent(11));
        // 设置 点击通知栏的下一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_next, getActivityPendingIntent(12));
        // 设置 点击通知栏的播放暂停按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_start, getActivityPendingIntent(13));
        // 设置 点击通知栏的根容器时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.ll_root, getActivityPendingIntent(14));
        remoteViews.setTextViewText(R.id.tv_title, "标题");     // 设置通知栏上标题
        remoteViews.setTextViewText(R.id.tv_artist, "艺术家");   // 设置通知栏上艺术家
        return remoteViews;
    }


    /**
     * 获取一个Activity类型的PendingIntent对象
     */
    private PendingIntent getActivityPendingIntent(int what) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        intent.putExtra("what", what);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, what, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


    private void sendNotification8() {
        for (int a = 0; a < 3; a++) {
            //这三个属性是必须要的，否则异常
            NotificationUtils notificationUtils = new NotificationUtils(this);
            notificationUtils.sendNotification(8, "这个是标题8", "这个是内容8", R.mipmap.ic_launcher);

        }
    }


    private void sendNotification9() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                //让通知左右滑的时候是否可以取消通知
                .setOngoing(true)
                //设置状态栏的标题
                .setTicker("有新消息呢9")
                //设置自定义view通知栏布局
                .setContent(getRemoteViews())
                //设置sound
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                //设置优先级
                .setPriority(Notification.PRIORITY_DEFAULT)
                //自定义震动效果
                .setFlags(Notification.FLAG_NO_CLEAR)
                //必须设置的属性，发送通知
                .sendNotification(9, "有新消息呢9", "这个是标题9", R.mipmap.ic_launcher);
    }


    private void sendNotification10() {

        //处理点击Notification的逻辑
        //创建intent
        Intent resultIntent = new Intent(this, TestActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what", 10);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 10, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //设置 Notification 的 flags = FLAG_NO_CLEAR
        //FLAG_NO_CLEAR 表示该通知不能被状态栏的清除按钮给清除掉,也不能被手动清除,但能通过 cancel() 方法清除
        //flags 可以通过 |= 运算叠加效果

        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                //让通知左右滑的时候是否可以取消通知
                .setOngoing(true)
                .setContentIntent(resultPendingIntent)
                //设置状态栏的标题
                .setTicker("有新消息呢10")
                //设置自定义view通知栏布局
                .setContent(getRemoteViews())
                .setDefaults(Notification.DEFAULT_ALL)
                //设置sound
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                //设置优先级
                .setPriority(Notification.PRIORITY_DEFAULT)
                //自定义震动效果
                .setFlags(Notification.FLAG_AUTO_CANCEL)
                //必须设置的属性，发送通知
                .sendNotification(10, "有新消息呢10", "这个是标题10", R.mipmap.ic_launcher);
    }


    private void sendNotification11() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                .setOngoing(false)
                .setTicker("来通知消息啦")
                .setContent(getRemoteViews())
                //.setSound(Uri.parse("android.resource://com.yc.cn.ycnotification/" + R.raw.hah))
                .setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "2"))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .sendNotification(11, "我是伴有铃声效果的通知11", "美妙么?安静听~11", R.mipmap.ic_launcher);

    }


    private void sendNotification12() {
        //震动也有两种设置方法,与设置铃声一样,在此不再赘述
        long[] vibrate = new long[]{0, 500, 1000, 1500};
//        Notification.Builder builder = new Notification.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("我是伴有震动效果的通知")
//                .setContentText("颤抖吧,逗比哈哈哈哈哈~")
//                //使用系统默认的震动参数,会与自定义的冲突
//                //.setDefaults(Notification.DEFAULT_VIBRATE)
//                //自定义震动效果
//                .setVibrate(vibrate);
//        //另一种设置震动的方法
//        //Notification notify = builder.build();
//        //调用系统默认震动
//        //notify.defaults = Notification.DEFAULT_VIBRATE;
//        //调用自己设置的震动
//        //notify.vibrate = vibrate;
//        //mManager.notify(3,notify);
//        mNotificationManager.notify(12, builder.build());

        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setVibrate(vibrate)
                .sendNotification(12, "我是伴有震动效果的通知", "颤抖吧,逗比哈哈哈哈哈~", R.mipmap.ic_launcher);

    }


    private void sendNotification13() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                .setDefaults(Notification.DEFAULT_ALL)
                .setFlags(Notification.FLAG_ONLY_ALERT_ONCE)
                .sendNotification(13, "仔细看,我就执行一遍", "好了,已经一遍了~", R.mipmap.ic_launcher);

    }


    private void sendNotification14() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                .setDefaults(Notification.DEFAULT_ALL)
                .setFlags(Notification.FLAG_ONLY_ALERT_ONCE)
                .sendNotification(14, "显示进度条14", "显示进度条内容，自定定义", R.mipmap.ic_launcher);
    }

    private void sendNotification15() {

        /**
         * PendingIntent.getActivity(),getBroadcast(),getService()等方法
         */
        Intent intent = new Intent(App.getApp(), MainActivity.class);
        intent.putExtra(MainActivity.NOTIFICATION_ACTION, "commission_action");
        PendingIntent pendingIntent = PendingIntent.getActivity(App.getApp(),
                MainActivity.NOTIFICATION_REQUEST_CODE, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        String title = "新消息来了";
        String content = "周末到了，不用上班了";

        String notifyId = randomUUID();
        Log.e(TAG, "sendNotification15 ->notifyId: " + notifyId);

        NotificationUtils.getInstance(App.getApp())
                .setContentIntent(pendingIntent)   //设置可点击跳转
                .setOngoing(true)
                .sendNotification(1, title, content,
                        R.mipmap.icon);

    }



    /**
     * 生成随机数<br>
     * GUID： 即Globally Unique Identifier（全球唯一标识符） 也称作 UUID(Universally Unique
     * IDentifier) 。
     *
     * 所以GUID就是UUID。
     *
     * GUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID。
     *
     * 从理论上讲，如果一台机器每秒产生10000000个GUID，则可以保证（概率意义上）3240年不重复。
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }



    /**
     * 错误代码
     */
    private void sendNotification151() {
        String id = "channel_1";
        String description = "123";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(id, "123", importance);
            //  mChannel.setDescription(description);
            //  mChannel.enableLights(true);
            //  mChannel.setLightColor(Color.RED);
            //  mChannel.enableVibration(true);
            //  mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(this, id)
                    .setContentTitle("Title")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("您有一条新通知")
                    .setContentText("这是一条逗你玩的消息")
                    .setAutoCancel(true)
//                    .setContentIntent(pintent)
                    .build();
            mNotificationManager.notify(1, notification);
        }
    }




}
