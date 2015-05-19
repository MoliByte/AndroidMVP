package presenter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import entity.ActionDiscussEntity;
import entity.Regions;
import entity.UpdateInfo;
import entity.UserInfoEntity;
import task.APPHttpService;
import task.HttpAysnResultInterface;
import task.HttpResultParse;
import iview.IUserView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class UserPresenter implements HttpAysnResultInterface{

	private IUserView mUserView;

	public UserPresenter(IUserView mUserView) {
		this.mUserView = mUserView ;
	}
	
	/**
	 * 登录
	 * @param context
	 * @param model
	 */
	public void login(Context context,UserInfoEntity model){
		new APPHttpService(context,
				HttpResultParse.USER_INFO, this)
				.getUserInfoDemo(model.getToken(),UserInfoEntity.class);
	}
	
	/**
	 * login by Ion
	 * @param context
	 * @param model
	 */
	public void loginByIon(Context context,UserInfoEntity model){
		final long begin = System.currentTimeMillis() ;
		 Ion.with(context)
	        .load("http://napi.skinrun.cn/v1/account")
	        .addHeader("Authorization", "Token " + model.getToken())
	        .setLogging("loginByIon", Log.ERROR)
	        .asJsonObject()
	        .setCallback(new FutureCallback<JsonObject>() {
	           @Override
	            public void onCompleted(Exception e, JsonObject result) {
	        	    UserInfoEntity entity = new Gson().fromJson(result.toString(), UserInfoEntity.class);
	                //Log.e("", result.toString());
	                long times = System.currentTimeMillis()-begin ;
	                mUserView.afterLoginHandler(entity);
	                //Log.e("times = ", times+"");
	            }
	        });
	}
	
	/**
	 * 活动详情
	 * @param context
	 * @param token
	 */
	public void getActionDetails(Context context,String token){
		new APPHttpService(context,
				HttpResultParse.ACTION_INFO, this)
				.getActionDetails(225l,1,"fe7960dd52790f5194582edee8cd533e",ActionDiscussEntity.class);
	}
	
	/**
	 * 区域详情
	 * @param context
	 */
	public void regions(Context context){
		new APPHttpService(context,
				HttpResultParse.REGION_INFO, this)
		.getRegions(Regions.class);
	}
	
	/**
	 * 下载
	 */
	public void download(Context context) {
		Ion.with(context)
        .load("http://napi.skinrun.cn/v1/Version?type=4")
        .asJsonObject()
        .setCallback(new FutureCallback<JsonObject>() {
           @Override
            public void onCompleted(Exception e, JsonObject result) {
        	   Log.e("download info ", result.toString());
        	    UpdateInfo entity = new Gson().fromJson(result.toString(), UpdateInfo.class);
                mUserView.download(entity);
            }
        });
		
	}

	/**
	 * 数据处理
	 */
	@Override
	public void dataCallBack(int tag, int statusCode, Object result) {
		try {
			if(tag == HttpResultParse.USER_INFO){
				UserInfoEntity entity = (UserInfoEntity) result ;
				mUserView.afterLoginHandler(entity);
			}else if(tag == HttpResultParse.ACTION_INFO){
				ActionDiscussEntity model = (ActionDiscussEntity)result ;
				mUserView.actionDetails(model);
			}else if(tag == HttpResultParse.REGION_INFO){
				ArrayList<Regions> modelList = (ArrayList<Regions>) result ;
				mUserView.regions(modelList);
			}
		} catch (Exception e) {
			Log.e("exception = ", e.toString());
		}
		
	}


}
