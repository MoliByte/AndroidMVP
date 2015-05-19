package iview;

import java.util.ArrayList;

import entity.ActionDiscussEntity;
import entity.Regions;
import entity.UpdateInfo;
import entity.UserInfoEntity;

public interface IUserView {
	
	void afterLoginHandler(UserInfoEntity model);//登录
	
	void actionDetails(ActionDiscussEntity model);//活动详情
	
	void regions(ArrayList<Regions> modelList);//地区信息
	
	void download(UpdateInfo modelInfo);
	
	
}
