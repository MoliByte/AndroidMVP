package task;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
/**
 * App请求
 * @author zhupei
 *
 */
public class CopyOfHttpAPIClient{
	
	private final static String TAG =  "HttpAPIClient--:";

	public static final String BASE_URL = "http://napi.skinrun.cn";
	
	public static final String NEW_BASE_URL = "http://v2.api.skinrun.me" ;

	public static final String IMAGE_URL = "http://a.skinrun.cn/images/";
	
	public static final String AUTHOR_IMAGE_URL = "http://a.skinrun.cn/userpic/";
	
	public static final String USER_IMAGE_URL = "http://napi.skinrun.cn/uploads/";

	private static final int CONNECTION_TIME_OUT = 20000;//设置请求超时20秒钟
	
	private static final int SO_TIMEOUT = 10000;  //设置等待数据超时时间10秒钟 
	
	private static HttpClient  httpclient ;
	
	/**
	 * HTTP请求参数设置
	 * **********************************************/
	public static HttpClient getHttpClientInstance(){
		if(httpclient == null){
			httpclient = new DefaultHttpClient();
		}
		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), CONNECTION_TIME_OUT);  
        HttpConnectionParams.setSoTimeout(httpclient.getParams(), SO_TIMEOUT);  
		return httpclient ;
	}
	
	public static CopyOfHttpAPIClient instatance ;
	
	public static CopyOfHttpAPIClient getInstance(){
		if(null == instatance){
			instatance = new CopyOfHttpAPIClient();
		}
		return instatance ;
	}

	public interface HttpCallBack{
		
		public void onSuccess(int tag, int statusCode, Header[] headers,
                              String responseBody, Class<?> clazz);
		
		public void onFailed(int tag, int statusCode, Header[] headers,
                             String responseBody, Throwable errorInfo);
	};
	/**
	 * GET
	 * @param context
	 * @param tag
	 * @param url
	 * @param headMap
	 * @param callBack
	 */
	public void httpGetOriginal(final Context context, final int tag,
			final String url, final Map<String, String> headMap, final Class<?> clazz,
			final HttpCallBack callBack){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare() ;
				HttpResponse response = null;
				try {
					HttpGet httpget = new HttpGet(HttpAPIClient.BASE_URL+url.trim());
					if(headMap!=null){
						for(String key : headMap.keySet()){
							httpget.addHeader(key, headMap.get(key));
						}
					}
					httpget.addHeader("Content-Type", "application/json");
					response = getHttpClientInstance().execute(httpget);
					int code = response.getStatusLine().getStatusCode();
					Log.e(TAG, "code = "+code+"");
					if (code == HttpStatus.SC_OK || code == HttpStatus.SC_CREATED){
						String rev = EntityUtils.toString(response.getEntity());
						callBack.onSuccess(tag,code, response.getAllHeaders(), rev.toString(), clazz);
						//Log.e("httpGetOriginal of "+url+TAG, "result = "+rev);
					}else{
						callBack.onFailed(tag,code, response.getAllHeaders(), null , new Throwable("Error Code " + code));
					}
				} catch (Exception e) {
					Log.e(TAG, "httpGetOriginal Error of " + url + e.toString() );
				} finally{
					try {
						if (null != response) {
							response.getEntity().getContent().close(); // That is very important !!!
						}
					} catch (Exception e) {
						// go to hell
					}
				}
				Looper.loop();
			}
		}).start();
	}
	/**
	 * POST
	 * @param context
	 * @param tag
	 * @param url
	 * @param headMap
	 * @param bodyEntity
	 */
	public void httpPostOriginal(final Context context, final int tag,
			final String url, final Map<String, String> headMap,final StringEntity bodyEntity,final Class<?> clazz,
			final HttpCallBack callBack){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare() ;
				HttpResponse response = null;
				try {
					HttpPost httppost = new HttpPost(CopyOfHttpAPIClient.BASE_URL+url);
					if (headMap != null) {
						for(String key : headMap.keySet()){
							httppost.addHeader(key, headMap.get(key));
						}
					}
					httppost.addHeader("Content-Type", "application/json");
					httppost.setEntity(bodyEntity);
					response = getHttpClientInstance().execute(httppost);
					int code = response.getStatusLine().getStatusCode();
					Log.e(TAG, "code = "+code+"");
					if (code == HttpStatus.SC_OK || code == HttpStatus.SC_CREATED) {
						String rev = EntityUtils.toString(response.getEntity());
						callBack.onSuccess(tag,code, response.getAllHeaders(), rev.toString(),clazz);
						//Log.e("httpPostOriginal of "+url+TAG, "result = "+rev);
					}else{
						callBack.onFailed(tag,code, response.getAllHeaders(), null , new Throwable("Error Code " + code));
					}
			           
				} catch (Exception e) {
					//Log.e(TAG, "httpPostOriginal Error of " + url + " by reason " + e.toString()  );
				} finally{
					try {
						if (null != response) {
							response.getEntity().getContent().close(); // That is very important !!!
						}
					} catch (Exception e) {
						// go to hell
					}
				}
				Looper.loop();
			}
		}).start();
	}
	
	/**
	 * PUT
	 * @param context
	 * @param tag
	 * @param url
	 * @param headMap
	 * @param bodyEntity
	 */
	public void httpPutOriginal(final Context context, final int tag,
			final String url, final Map<String, String> headMap,final StringEntity bodyEntity,final Class<?> clazz,
			final HttpCallBack callBack){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare() ;
				HttpResponse response = null;
				try {
					HttpPut httpput = new HttpPut(CopyOfHttpAPIClient.BASE_URL+url);
					if (headMap != null) {
						for(String key : headMap.keySet()){
							httpput.addHeader(key, headMap.get(key));
						}
					}
					httpput.addHeader("Content-Type", "application/json");
					httpput.setEntity(bodyEntity);
					response = getHttpClientInstance().execute(httpput);
					int code = response.getStatusLine().getStatusCode();
					Log.e(TAG, "code = "+code+"");
					if (code == HttpStatus.SC_OK || code == HttpStatus.SC_CREATED){
						String rev = EntityUtils.toString(response.getEntity());
						callBack.onSuccess(tag,code, response.getAllHeaders(), rev.toString(),clazz);
						//Log.e("httpPutOriginal of "+url+TAG, "result = "+rev);
					}else{
						callBack.onFailed(tag,code, response.getAllHeaders(), null , new Throwable("Error Code " + code));
					}
				} catch (Exception e) {
					//Log.e(TAG, "httpPutOriginal Error of " + url );
				} finally{
					try {
						if (null != response) {
							response.getEntity().getContent().close(); // That is very important !!!
						}
					} catch (Exception e) {
						// go to hell
					}
				}
				Looper.loop();
			}
		}).start();
	}
	
	/**
	 * DELETE
	 * @param context
	 * @param tag
	 * @param url
	 * @param headMap
	 * @param bodyEntity
	 */
	public void httpDeleteOriginal(final Context context, final int tag,
			final String url, final Map<String, String> headMap,final StringEntity bodyEntity,
			final Class<?> clazz,final HttpCallBack callBack){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare() ;
				HttpResponse response = null;
				try {
					HttpDelete httpDel = new HttpDelete(CopyOfHttpAPIClient.BASE_URL+url);
					if (headMap != null) {
						for(String key : headMap.keySet()){
							httpDel.addHeader(key, headMap.get(key));
						}
					}
					httpDel.addHeader("Content-Type", "application/json");
					response = getHttpClientInstance().execute(httpDel);
					int code = response.getStatusLine().getStatusCode();
					Log.e(TAG, "code = "+code+"");
					if (code == HttpStatus.SC_OK || code == HttpStatus.SC_CREATED){
						String rev = EntityUtils.toString(response.getEntity());
						callBack.onSuccess(tag,code, response.getAllHeaders(), rev.toString(),clazz);
						//Log.e("httpPutOriginal of "+url+TAG, "result = "+rev);
					}else{
						callBack.onFailed(tag,code, response.getAllHeaders(), null , new Throwable("Error Code " + code));
					}
				} catch (Exception e) {
					//Log.e(TAG, "httpPutOriginal Error of " + url );
				} finally{
					try {
						if (null != response) {
							response.getEntity().getContent().close(); // That is very important !!!
						}
					} catch (Exception e) {
						// go to hell
					}
				}
				Looper.loop();
			}
		}).start();
	}
}
