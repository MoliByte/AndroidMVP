package entity;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

/**
 * 文章评论
 * 
 * @author zhup
 *  "id": 6,
    "post_id": 1,
    "parent_id": 0,
    "to_uid": 8070,
    "to_nick": "ghhh",
    "from_uid": 8070,
    "from_nick": "ghhh",
    "content": "aaaa",
    "comment_count": 0,
    "create_time": "2014-12-30 13:56:13"
 */
@SuppressWarnings("serial")
public class ActionCommentEntity implements Serializable {
	
	@SerializedName("id") private long id ;
	
	@SerializedName("post_id") private long post_id ;
	@SerializedName("parent_id") private long parent_id ;
	@SerializedName("to_uid") private long to_uid ;
	@SerializedName("from_uid") private long from_uid ;
	@SerializedName("to_nick") private String to_nick ;
	@SerializedName("from_nick") private String from_nick ;
	@SerializedName("content") private String content ;
	@SerializedName("comment_count") private int comment_count ;
	@SerializedName("support_count") private int support_count ;
	@SerializedName("integral") private int integral ;
	@SerializedName("create_time") private String create_time ;
	
	@SerializedName("praise_status") private int praise_status ; //当前用户是否对该活动或者文章点赞
	@SerializedName("praise_count") private int praise_count ;//总的点赞次数
	
	@SerializedName("replys") private ArrayList<ActionReplayEntity> replys ;
	
	
	
	public int getPraise_status() {
		return praise_status;
	}


	public void setPraise_status(int praise_status) {
		this.praise_status = praise_status;
	}


	public int getPraise_count() {
		return praise_count;
	}


	public void setPraise_count(int praise_count) {
		this.praise_count = praise_count;
	}
	
	public int getIntegral() {
		return integral;
	}


	public void setIntegral(int integral) {
		this.integral = integral;
	}


	public ArrayList<ActionReplayEntity> getReplys() {
		if(replys == null){
			replys = new ArrayList<ActionReplayEntity>();
		}
		return replys;
	}
	
	
	public void setReplys(ArrayList<ActionReplayEntity> replys) {
		this.replys = replys;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}
	public long getTo_uid() {
		return to_uid;
	}
	public void setTo_uid(long to_uid) {
		this.to_uid = to_uid;
	}
	public long getFrom_uid() {
		return from_uid;
	}
	public void setFrom_uid(long from_uid) {
		this.from_uid = from_uid;
	}
	public String getTo_nick() {
		return to_nick;
	}
	public void setTo_nick(String to_nick) {
		this.to_nick = to_nick;
	}
	public String getFrom_nick() {
		return from_nick;
	}
	public void setFrom_nick(String from_nick) {
		this.from_nick = from_nick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getSupport_count() {
		return support_count;
	}
	public void setSupport_count(int support_count) {
		this.support_count = support_count;
	}
	
	
	

}
