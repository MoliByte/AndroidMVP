package task;

import android.util.Log;

import entity.ActionDiscussEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 自定义解析业务逻辑
 * @author zhupei
 *
 */
public class HttpResultCusomedParse {
	
	public final static String TAG = "HttpResultCusomedParse" ;
	
	/**
	 * 返回活动详情-自定义解析方式
	 * @param responseBody
	 * @param clazz
	 * @return
	 */
	public ActionDiscussEntity parseActionDetails(String responseBody, Class<?> clazz){
		ActionDiscussEntity obj = null ;
		try {
			Gson gson = new GsonBuilder().create();
			obj = (ActionDiscussEntity) gson.fromJson(responseBody.toString(),clazz) ;
			/*JSONArray commentArray = new JSONObject(responseBody.toString()).optJSONArray("comments");
			ArrayList<ActionCommentEntity> comments = new ArrayList<ActionCommentEntity>();
			if(null != commentArray){
				for (int i = 0; i < commentArray.length(); i++) {
					ActionCommentEntity commentEntity = gson.fromJson(commentArray.optString(i), ActionCommentEntity.class);
					comments.add(commentEntity);
				}
				
			}
			obj.setCommentList(comments);*/
		} catch (Exception e) {
			Log.e("parseActionDetails:", e.toString());
		}
		return obj ;
	}
}
