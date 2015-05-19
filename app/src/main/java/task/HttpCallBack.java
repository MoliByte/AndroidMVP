package task;

import org.apache.http.Header;

public interface HttpCallBack {
	public void onSuccess(int tag, int statusCode, Header[] headers,
                          String responseBody, Class<?> clazz);
	
	public void onFailed(int tag, int statusCode, Header[] headers,
                         String responseBody, Throwable errorInfo);
}
