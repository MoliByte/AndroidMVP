package task;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpStatus;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author zhup
 *
 */
public class APPHttpService extends HttpResultParse{


	public APPHttpService(Context context, Integer mTag,
			HttpAysnResultInterface callback) {
		super(context, mTag, callback);
	}
	

	public void getUserInfoDemo(String Token,final Class<?> clazz) {
		try {
			final String user_info_url = "/v1/account";
			final Map<String,String> map = new HashMap<String,String>();
			map.put("Authorization", "Token " + Token);
			HttpAPIClient.getInstance().httpGetOriginal(context, this.mTag, user_info_url, map,clazz, this);
		} catch (Exception e) {
			Log.e(TAG, "getUserInfo error:" + e.toString());
		}
	}
	
	public void getActionDetails(long id,int pageIndex,String Token,final Class<?> clazz){
		try {
			final String v1_activity = "/v1/activity/"+id;
			final Map<String,String> map = new HashMap<String,String>();
			map.put("Authorization", "Token " + Token);
			HttpAPIClient.getInstance().httpGetOriginal(context, this.mTag, v1_activity, map,clazz, this);
		} catch (Exception e) {
			Log.e(TAG, "getActionDetails error:" + e.toString());
		}
	}
	
	public void getRegions(final Class<?> clazz){
		try {
			final String v1_region = "/v1/region";
			final Map<String,String> map = new HashMap<String,String>();
			HttpAPIClient.getInstance().httpGetOriginal(context, this.mTag, v1_region, map,clazz, this);
		} catch (Exception e) {
			Log.e(TAG, "getRegions error:" + e.toString());
		}
	}

	

}
