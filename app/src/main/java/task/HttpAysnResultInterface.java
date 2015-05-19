package task;

/**
 * @author zhup
 * 处理各种业务逻辑接口
 */
public interface  HttpAysnResultInterface{
	
	public void dataCallBack(int tag, int statusCode, Object result);
}
