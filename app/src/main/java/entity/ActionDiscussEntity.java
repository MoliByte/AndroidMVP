package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 * 活动评论
 * @author zhup
 * "id": 1,
    "author_id": 8070,
    "author_nick": "笑",
    "title": "测试文章",
    "content": "测试文章",
    "view_count": 0,
    "comment_count": 4,
    "public_time": "0000-00-00 00:00:00",
    "create_time": "0000-00-00 00:00:00",
 */
@SuppressWarnings("serial")
public class ActionDiscussEntity implements Serializable {
	@SerializedName("id")
	private long id ;
	@SerializedName("author_id")
	private long author_id ;
	@SerializedName("author_nick")
	private String author_nick ;
	@SerializedName("title")
	private String title ;
	@SerializedName("content")
	private String content ;
	@SerializedName("imgURL")
	private String imgURL ;
	@SerializedName("view_count")
	private int view_count ;
	@SerializedName("public_time")
	private String public_time;
	@SerializedName("create_time")
	private String create_time;
	@SerializedName("end_time")
	private String end_time;
	@SerializedName("comment_count")
	private int comment_count; // 评论数目
	@SerializedName("praise_status")
	private int praise_status ; //当前用户是否对该活动或者文章点赞
	@SerializedName("praise_count")
	private int praise_count ;//总的点赞次数
	
	private Map<Integer,String> join_users;//参与人数 ;
	
	@SerializedName("comments")
	private ArrayList<ActionCommentEntity> commentList ;

	
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
	public Map<Integer, String> getJoin_users() {
		if(join_users == null){
			return new HashMap<Integer,String>(0);
		}
		return join_users;
	}
	public void setJoin_users(Map<Integer, String> join_users) {
		this.join_users = join_users;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(long author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_nick() {
		return author_nick;
	}
	public void setAuthor_nick(String author_nick) {
		this.author_nick = author_nick;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public String getPublic_time() {
		return public_time;
	}
	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public ArrayList<ActionCommentEntity> getCommentList() {
		if(commentList == null){
			this.commentList = new ArrayList<ActionCommentEntity>();
		}
		return commentList;
	}

	public void setCommentList(ArrayList<ActionCommentEntity> commentList) {
		this.commentList = commentList;
	}
	
	

}
