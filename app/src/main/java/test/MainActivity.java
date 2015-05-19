package test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.base.supertoasts.util.AppToast;
import com.example.httpclientutils.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ActionDiscussEntity;
import entity.Regions;
import entity.UpdateInfo;
import entity.UserInfoEntity;
import iview.IUserView;
import presenter.UserPresenter;
import utils.EasyLog;

public class MainActivity extends Activity implements IUserView,
        OnClickListener {
    @InjectView(R.id.request)
    Button request;
    @InjectView(R.id.actionDetail)
    Button actionDetail;
    @InjectView(R.id.requestIon)
    Button requestIon;

    @InjectView(R.id.regions)
    Button regions;
    @InjectView(R.id.download)
    Button download;
    @InjectView(R.id.content)
    TextView content;
    // @InjectView(R.id.imageView) private ImageView imageView;

    long begin = System.currentTimeMillis();

    UserPresenter userPresenter;
    final String token = "fe7960dd52790f5194582edee8cd533e";

    final static int Down_Pro = 100;
    Handler handler = new Handler() {

        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case Down_Pro:
                    content.setText((String) msg.obj + "");
                    break;

                default:
                    break;
            }
            super.dispatchMessage(msg);
        }

    };

    // private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request = (Button) findViewById(R.id.request);
        // requestIon = (Button) findViewById(R.id.requestIon);
        // actionDetail = (Button) findViewById(R.id.actionDetail);
        // regions = (Button) findViewById(R.id.regions);
        // download = (Button) findViewById(R.id.download);
        // content = (TextView) findViewById(R.id.content);

        ButterKnife.inject(this);
        try {

            request.setOnClickListener(this);

            requestIon.setOnClickListener(this);

            actionDetail.setOnClickListener(this);

            regions.setOnClickListener(this);

            download.setOnClickListener(this);

            findViewById(R.id.cancel).setOnClickListener(this);

            userPresenter = new UserPresenter(this);

            // 帧动画
            /**
             * imageView = (ImageView) findViewById(R.id.imageView1);
             *
             * imageView.setBackgroundResource(R.anim.frame_by_frame);
             *
             * animationDrawable = (AnimationDrawable)
             * imageView.getBackground();
             *
             * animationDrawable.start();
             **/
            // 开始

            begin = System.currentTimeMillis();

            int i = 1 / 0 ;
        } catch (Exception e) {
            //EasyLog.e(e);
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        begin = System.currentTimeMillis();
        UserInfoEntity modelEntity = new UserInfoEntity();
        switch (v.getId()) {
            case R.id.request:
                modelEntity.setToken(token);
                userPresenter.login(getApplicationContext(), modelEntity);
                break;
            case R.id.requestIon:
                modelEntity = new UserInfoEntity();
                modelEntity.setToken(token);
                userPresenter.loginByIon(getApplicationContext(), modelEntity);
                break;
            case R.id.actionDetail:
                userPresenter.getActionDetails(getApplicationContext(), token);
                break;
            case R.id.regions:
                userPresenter.regions(getApplicationContext());
                break;
            case R.id.download:
                userPresenter.download(getApplicationContext());
                break;
            case R.id.cancel:
                content.setText("Please send Request!");
                break;

            default:
                break;
        }
    }

    @Override
    public void afterLoginHandler(UserInfoEntity model) {
        AppToast.toastMsgCenter(this, "登录成功!").show();
        long times = System.currentTimeMillis() - begin;
        content.setText("" + model.getUser_avatar() + " \n using time " + times);
        Log.e("afterLoginHandler times = ", times + "");

    }

    @Override
    public void actionDetails(ActionDiscussEntity model) {
        content.setText("" + model.getContent());
        long times = System.currentTimeMillis() - begin;
        Log.e("actionDetails times = ", times + "");
    }

    @Override
    public void regions(ArrayList<Regions> modelList) {
        content.setText("" + modelList.size());
        long times = System.currentTimeMillis() - begin;
        Log.e("regions times = ", times + "");
    }

    @Override
    public void download(UpdateInfo modelInfo) {

        String downloadPathString = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "Trunk"
                + File.separator
                + "download/trunk_" + modelInfo.getVersionName() + ".apk";
        content.setText("" + modelInfo.getApkUrl());
        Ion.with(getApplicationContext())
                .load(modelInfo.getApkUrl())
                        /*
                        .progressBar(progressBar)
                        and a ProgressDialog
                        .progressDialog(progressDialog)
                        can also use a custom callback
                        */
                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        String result = "";// 接受百分比的值
                        // double tempresult=downloaded/total;
                        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
                        format.setMinimumFractionDigits(2);// 设置小数位
                        result = format.format((double) downloaded
                                / (double) total);

                        Message msgMessage = new Message();
                        msgMessage.what = Down_Pro;
                        msgMessage.obj = result;
                        // msgMessage.arg1 = (int)(downloaded / total) ;
                        handler.sendMessage(msgMessage);
                        System.out.println("result = " + result);
                        System.out.println("" + downloaded + " / " + total);
                    }
                }).write(new File(downloadPathString))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
                        // download done...
                        // do stuff with the File or error
                        Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public String getPercent(int x, int total) {
        String result = "";// 接受百分比的值
        // double x_double=x*1.0;
        double tempresult = x / total;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
        DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
        // 百分比格式，后面不足2位的用0补齐
        // result=nf.format(tempresult);
        result = df1.format(tempresult);
        return result;
    }

}
