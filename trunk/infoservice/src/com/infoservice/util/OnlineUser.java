package com.infoservice.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.infoservice.domain.LoginUser;
import com.infoservice.domain.TreeObject;

public class OnlineUser {
	private static OnlineUser instance = new OnlineUser();
	private static Vector<LoginUser> admin_userList;
	private static Vector<LoginUser> userList;

	private OnlineUser() {
	}

	public static OnlineUser getInstance() {
		if (null == instance)
			instance = new OnlineUser();
		return instance;
	}

	public void addUser(LoginUser user, Integer type) {
		if (null != type) {
			if (1 == type) {
				if (null == admin_userList)
					admin_userList = new Vector<LoginUser>();
				admin_userList.add(user);
			} else {
				if (null == userList)
					userList = new Vector<LoginUser>();
				userList.add(user);
			}
		}
	}

	public void removeUser(LoginUser user, Integer type) {
		if (null != type) {
			if (1 == type) {
				if (null == admin_userList)
					return;
				admin_userList.remove(user);
			} else {
				if (null == userList)
					return;
				userList.remove(user);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<TreeObject> getTreeObject(Integer type) {
		List<TreeObject> list = new ArrayList<TreeObject>();
		if (null != type) {
			int num = 0;
			if (1 == type) {
				for (Iterator iterator = admin_userList.iterator(); iterator
						.hasNext();) {
					LoginUser user = (LoginUser) iterator.next();
					TreeObject to = new TreeObject();
					to.setId(num);
					to.setText(user.getUsername());
					num++;
					to.setLeaf(true);
					list.add(to);
				}
			} else {
				for (Iterator iterator = userList.iterator(); iterator
						.hasNext();) {
					LoginUser user = (LoginUser) iterator.next();
					TreeObject to = new TreeObject();
					to.setId(num);
					to.setText(user.getUsername());
					num++;
					to.setLeaf(true);
					list.add(to);
				}
			}
		}
		return list;
	}

	public static Vector<LoginUser> getAdmin_userList() {
		return admin_userList;
	}

	public static void setAdmin_userList(Vector<LoginUser> admin_userList) {
		OnlineUser.admin_userList = admin_userList;
	}

	public static Vector<LoginUser> getUserList() {
		return userList;
	}

	public static void setUserList(Vector<LoginUser> userList) {
		OnlineUser.userList = userList;
	}
}
