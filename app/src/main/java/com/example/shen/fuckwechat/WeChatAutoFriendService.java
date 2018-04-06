package com.example.shen.fuckwechat;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class WeChatAutoFriendService extends AccessibilityService {
    public WeChatAutoFriendService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        performClick(findNodesByText("搜索"));

    }


    public List<AccessibilityNodeInfo> findNodesByText(String text) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            return nodeInfo.findAccessibilityNodeInfosByText(text);
        }
        return null;
    }
    private boolean performClick(List<AccessibilityNodeInfo> nodeInfos) {
        if (nodeInfos != null && !nodeInfos.isEmpty()) {
            AccessibilityNodeInfo node;
            for (int i = 0; i < nodeInfos.size(); i++) {
                node = nodeInfos.get(i);
                // 获得点击View的类型
                Log.d("Clicke-------:","View类型：" + node.getClassName());
                // 进行模拟点击
                if (node.isEnabled()) {
                    return node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
        return false;
    }


    @Override
    public void onInterrupt() {

    }




}
