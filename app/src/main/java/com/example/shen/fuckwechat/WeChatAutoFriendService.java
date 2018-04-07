package com.example.shen.fuckwechat;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WeChatAutoFriendService extends AccessibilityService {
    public WeChatAutoFriendService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //performClick(findNodesByText("搜索指定内容"));
        if (pressMore()){
            pressMore();
        }
        if (pressAddFriendStepOne()){
            pressAddFriendStepOne();
        }
//        if (findNodesByText("微信号/QQ号/手机号")!=null){
//            pressAddFriendStepTwo();
//        };



    }


    public boolean pressMore(){
        return performClick(findNodesByText("更多功能按钮"));//更多功能按钮
    }

    public boolean pressAddFriendStepOne(){
        return performClick(findNodesByText("添加朋友"));//更多功能按钮
    }

    public boolean pressAddFriendStepTwo(){
        //return performClick(findNodesByText("微信号/QQ号/手机号"));//更多功能按钮

        List<AccessibilityNodeInfo> nodeInfos = findNodesByText("微信号/QQ号/手机号");
        if (nodeInfos==null||nodeInfos.isEmpty()) return false;
        else {
            for (AccessibilityNodeInfo node : nodeInfos){
                AccessibilityNodeInfo parent = node;
                while (parent!=null){
                    if(parent.isClickable()){
                        // 模拟点击，跳出循环
                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        break;
                    }
                    parent = parent.getParent();
                }
                }



        }
        return false;
    }


    //

    public List<AccessibilityNodeInfo> findNodesByText(String text) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            return nodeInfo.findAccessibilityNodeInfosByText(text);
        }
        return null;
    }
    private boolean performClick(List<AccessibilityNodeInfo> nodeInfos) {
        if (nodeInfos != null && !nodeInfos.isEmpty()) {
            for (AccessibilityNodeInfo node:nodeInfos) {
                if (node == null){
                    continue;
                }
                // 获得点击View的类型
                try{
                    Log.d("Clicke-------:","View类型：" + node.getClassName());
                }catch (Exception e){
                    e.printStackTrace();
                }

                // 进行模拟点击
                AccessibilityNodeInfo parent = node;
                while (parent!=null){
                    if(parent.isClickable()){
                        // 模拟点击，跳出循环
                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        break;
                    }
                    parent = parent.getParent();
                }
        }
    }

        return false;
    }


    @Override
    public void onInterrupt() {

    }

    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


    //微信自动添加好友流程
    // 从txt or web搜索得到通讯录列表
    // 找到搜索框
    // 编辑搜索框
    // 点击  查找微信号
    // 被搜账号异常 无法显示
    //  异常情况     被搜帐号状态异常，无法显示
    //              用户不存在



}
