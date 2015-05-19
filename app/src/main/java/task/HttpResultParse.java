package task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.json.JSONArray;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
/**
 * 接受网络返回数据，返回指定的对象结果
 * @author zhupei
 *
 */
public abstract class HttpResultParse extends HttpResultCusomedParse implements HttpAPIClient.HttpCallBack{
	
	public final static int SHOW_TIME = 2000 ;
	
	public static final int USER_INFO = 0x100; 					// 获取用户信息
	public static final int ACTION_INFO = 0x101; 				// 获取活动详情
	public static final int REGION_INFO = 0x102; 				// 区域详情
	
	
	
	public final static String TAG = "HttpResultParse-->";
	public Context context;
	public Integer mTag;
	public HttpAysnResultInterface callback ;
	
	/**
	 * @param context
	 * @param mTag
	 * @param callback
	 */
	public HttpResultParse(Context context, Integer mTag,HttpAysnResultInterface callback) {
		this.context = context;
		this.mTag = mTag;
		this.callback = callback;
	}
	
	
	/**
	 * 解析单个对象
	 * @param result
	 * @return Class<?> obj
	 */
	public Object parseJsonObject(Object result,Class<?> entityClass){
		Object obj = null ;
		try {
			obj = new Gson().fromJson(result.toString(), entityClass) ;
		} catch (Exception e) {
			
		}
		return obj ;
	}
	
	/**
	 * 解析数组
	 * @param result
	 * @return Class<?> obj
	 */
	public ArrayList<Object> parseJsonArray(Object result,Class<?> entityClass){
		ArrayList<Object> list = null ;
		try {
			list = new ArrayList<Object>();
			JSONArray array = new JSONArray(result.toString()) ;
			for (int i = 0; i < array.length(); i++) {
				Object obj = new Gson().fromJson(array.optJSONObject(i).toString(), entityClass) ;
				list.add(obj);
			}
			return list ;
		} catch (Exception e) {
			
		}
		return list ;
	}
	
	/**
	 * @param statusCode
	 * @return
	 */
	public boolean isSuccess(int statusCode){
		boolean flag = false ;
		if(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED){
			flag =  true ;
		}
		return flag ;
	}
	
	/**
	 * Toast 处理各种业务
	 * @param context
	 * @param tag
	 * @param isSuccessed
	 * @param errorInfo
	 */
	public void toastMsgByTag(Context context , int tag,boolean isSuccessed,Throwable errorInfo){
		if(tag == HttpResultParse.USER_INFO){
			Toast.makeText(context, ""+errorInfo.getMessage(), Toast.LENGTH_LONG).show();
		}else if(tag == ACTION_INFO){
			Toast.makeText(context, ""+errorInfo.getMessage(), Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(context, ""+errorInfo.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onSuccess(int tag, int statusCode, Header[] headers,
			String responseBody, Class<?> clazz) {

		//Log.e("Call back success", "" + responseBody);
		if(tag == USER_INFO){// 获取用户信息
			if (null != responseBody && !"".equals(responseBody)) {
				callback.dataCallBack(tag, statusCode,
						parseJsonObject(responseBody, clazz));
			} else {
				toastMsgByTag(context, tag, false,new Throwable("响应内容错误!"));
			}
		}else if(tag == ACTION_INFO){// 获取活动详情信息
			if (null != responseBody && !"".equals(responseBody)) {
				callback.dataCallBack(tag, statusCode,
						/*parseActionDetails*/parseJsonObject(responseBody, clazz));
			} else {
				toastMsgByTag(context, tag, false,new Throwable("响应内容错误!"));
			}
		}else if(tag == REGION_INFO){// 区域详情信息
			if (null != responseBody && !"".equals(responseBody)) {
				callback.dataCallBack(tag, statusCode,
						parseJsonArray(responseBody, clazz));
			} else {
				toastMsgByTag(context, tag, false,new Throwable("响应内容错误!"));
			}
		}
		

	}

	@Override
	public void onFailed(int tag, int statusCode, Header[] headers,
			String responseBody, Throwable errorInfo) {
		toastMsgByTag(context, tag, false,errorInfo);
	}
	
	
	
}
