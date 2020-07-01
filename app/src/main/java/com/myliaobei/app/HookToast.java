package com.myliaobei.app;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.widget.Toast.LENGTH_LONG;
import static com.myliaobei.app.hidden.hideModule;
import static de.robv.android.xposed.XposedBridge.hookAllConstructors;
import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findClassIfExists;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

public class HookToast implements IXposedHookLoadPackage{
    private static Context globalContext;
    private Boolean Qiang_flang = false;
    public static final String LB_PACKAGE_NAME = "com.yunzhan.liaobei";
    private void dohook(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        findAndHookMethod("com.stub.StubApp", loadPackageParam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //获取到360的Context对象，通过这个对象来获取classloader
                Context context = (Context) param.args[0];
                //获取360的classloader，之后hook加固后的就使用这个classloader

                ClassLoader classLoader = context.getClassLoader();
                //下面就是强classloader修改成360的classloader就可以成功的hook了
                //com.yunzhan.liaobei.chat.ChatActivity A com.yunzhan.liaobei.chat.i
                //com.yunzhan.liaobei.chat.ChatActivity A com.yunzhan.liaobei.chat.i
                //com.yunzhan.liaobei.message.a d com.yunzhan.liaobei.message.a.a com.yunzhan.liaobei.message.d
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity aP com.yunzhan.liaobei.chat.groupchat.GroupChatActivity
                //com.yunzhan.liaobei.account.ui.redpacket.GroupRedPacketDetailActivity a com.yunzhan.liaobei.account.ui.redpacket.GroupRedPacketDetailActivity
                //初始化globalContext
                findAndHookMethod("com.yunzhan.liaobei.splash.SplashActivity", classLoader,"onCreate",Bundle.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        globalContext = (Context) param.thisObject;
                    }
                });
                if(globalContext!=null) {
                    toast("聊呗JJ秒已启动");
                }
//                final Class <?> httpUrlConnection = findClass("java.net.HttpURLConnection",loadPackageParam.classLoader);
//                hookAllConstructors(httpUrlConnection, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        if (param.args.length < 1 || param.args[0].getClass() != URL.class)
//                            return;
//                        XposedBridge.log("网络: " + param.args[0] + "");
//                    }
//                });
                //获取聊天消息
                //com.yunzhan.liaobei.message.ChatConversationListActivity b com.yunzhan.liaobei.message.ChatConversationListActivity
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity b java.util.List
                //com.yunzhan.liaobei.message.ChatConversationListActivity d java.util.List
                //com.yunzhan.liaobei.message.ChatConversationListActivity e java.util.List
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity a com.yunzhan.liaobei.chat.groupchat.GroupChatActivity java.util.List
                //com.yunzhan.liaobei.message.ChatConversationListActivity r
                //com.yunzhan.liaobei.message.ChatConversationListActivity q
                //com.yunzhan.liaobei.message.c valueOf java.lang.String
                //com.yunzhan.liaobei.message.ChatConversationListActivity lambda$BicewzV7oO2z8h96RGpbjFjqVJ0 com.yunzhan.liaobei.message.ChatConversationListActivity
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity A com.yunzhan.liaobei.chat.groupchat.GroupChatActivity
                //com.yunzhan.liaobei.message.ChatConversationListActivity a java.util.List
                //com.yunzhan.liaobei.message.ChatConversationListActivity b java.util.List
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity a com.yunzhan.liaobei.chat.groupchat.GroupChatActivity java.util.List
                //com.yunzhan.liaobei.message.ChatConversationListActivity a java.util.List
                //com.yunzhan.liaobei.widget.AutoLinkTextView a java.lang.CharSequence com.yunzhan.liaobei.chat.i
                //com.cqchat.android.a.c.a.a(OkHttpClientProxy)
                //com.yunzhan.liaobei.account.ui.redpacket.b.a$1
                //google.flutter.FlutterEmbeddingFragmentActivity onRequestPermissionsResult int java.lang.String[] int[]
                //com.yunzhan.liaobei.login.a a java.lang.String
                //com.yunzhan.liaobei.setting.myself.privacysecurit.gesturelock.a
                //im.a.a.c.b.a.b.c.t
//               toast("开始");
//                hookAllMethods(ClassLoader.class, "loadClass", new XC_MethodHook() {
//                   @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        try {
//                            Class clazz = (Class) param.getResult();
//                            String clazzName = clazz.getName();
//                            if (clazzName.contains("StringBuilder")) {
//                                XposedBridge.log("在此"+clazzName);
//                                //System.out.print(clazzName);
//                                //com.yunzhan.liaobei.login.-$$Lambda$d$4HwVtv04Lx17IaUqgD2gvcGyzJE
//                            }
//                        }catch (Throwable throwable) {
//                            XposedBridge.log("错误");
//                       }
//                        }
//
//                });
//                final Class<?> aClass = findClassIfExists("com.alibaba.wireless.security.open.SecurityGuardManager",classLoader);
//                Object testndkobject = aClass.newInstance();
//                Method myteststr =  XposedHelpers.findMethodBestMatch(testndkobject.getClass(), "getSafeTokenComp");
//                String str5 = (String) myteststr.invoke(testndkobject);
//                XposedBridge.log("在此："+str5);
                ////                if(aClass == null){
////                    XposedBridge.log("嘿嘿嘿null");
////                }else {
                    //ClassLoader tokenloader = aClass.getClassLoader();
//                    Object tk =callStaticMethod(aClass,"getSafeTokenComp");
//                    XposedBridge.log("tk在此" + tk);
////                    for (int i = 0; i < aClass.getDeclaredMethods().length; i++) {
////                        XposedBridge.log("在此" + aClass.getDeclaredMethods()[i].toString());
////                    }
//                }
                //com.yunzhan.liaobei.message.ChatConversationListActivity a com.yunzhan.liaobei.message.ChatConversationListActivity com.cqchat.e.a.n.g.g
                //com.yunzhan.liaobei.messagepush.b a java.lang.String
                //com.yunzhan.liaobei.messagepush.b a com.yunzhan.liaobei.messagepush.e
                //com.yunzhan.liaobei.messagepush.b c
                //com.yunzhan.liaobei.messagepush.b h
                //com.yunzhan.liaobei.messagepush.b a
                //com.yunzhan.liaobei.messagepush.mipush.a a java.lang.String
                //com.yunzhan.liaobei.messagepush.hwpush.HwPush showPushMessage java.lang.String
                //com.cqchat.g.l.b$a
                //com.cqchat.g.l.b a
                //java.util.concurrent.CopyOnWriteArrayList getArray
                //com.cqchat.g.l.b a
                //com.cqchat.j.a.a.g a
                //com.cqchat.j.n.c.c.b.b.f a com.cqchat.e.a.k.o
                //是通过查看抛出的异常找到类，再在反射大师搜索这个类，复制其hook函数的
                findAndHookMethod("com.cqchat.j.n.c.c.b.b.f", classLoader,"a","com.cqchat.e.a.k.o",new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
//                        for(int i =0;i<param.args.length;i++){
//                            XposedBridge.log("在此"+i+"："+param.args[i].toString());
//                        }
                        //77df9b0d4ac56bec 1c6b114a f888df33e96420ba
//                          XposedBridge.log("在此"+param.args[0].toString());
//                          XposedBridge.log("在此"+param.args.length);
                          JSONObject jsonobject = new JSONObject(param.args[0].toString());
                          int messagetype = jsonobject.getInt("msgtp");

                          if( messagetype == 15){//16为拆开红包消息
                              if(!pre.open()){
                                  return;
                              }
                              String uid =pre.set_uid();
                              String tk =pre.set_token();
                              String uuid =pre.set_uuid();
                              if(TextUtils.isEmpty(uid)||TextUtils.isEmpty(tk)||TextUtils.isEmpty(uuid)){
                                  toast("uid、uuid、token不能为空");
                                  return;
                              }
                              double amt = jsonobject.getJSONObject("msg").getDouble("amt");
//                              if(pre.how_to_qiang() >amt){
//                                  return;
//                              }
                              String msg = jsonobject.getJSONObject("msg").optString("t");
                              String roomname = jsonobject.optString("rn");
                              String sengname = jsonobject.optString("rmk");

                              long starttime = new Date().getTime();
                              String tno = jsonobject.getJSONObject("msg").getString("tno");

                              int tp = jsonobject.getJSONObject("msg").getInt("tp");
                              String ver = jsonobject.getString("ver");
                              JSONObject data = new JSONObject();
                              data.put("tno",tno);
                              data.put("fuid",uid);
                              data.put("tp",tp);
                              data.put("tk",tk);
                              String mtdata = URLEncoder.encode( data.toString(), "UTF-8" );
                              if (Double.valueOf(pre.how_to_qiang())<=amt&&!Qiang_flang){
                                  XposedBridge.log("金额："+amt);
                                qiang_hb(mtdata, uid, uuid, ver, roomname, sengname, msg,starttime);
                              }
                              else if(Qiang_flang) {
                                  qiang_hb(mtdata, uid, uuid, ver, roomname, sengname, msg,starttime);
//                                  getdetail( mtdata, uid, uuid, ver, roomname, sengname, msg,starttime);
                              }

                          }
                          //else if( messagetype == 16){
                          //    double money
                         // }
                    }
                });
                //java.lang.StringBuilder
                //android.app.Activity getActivityToken
                //android.content.ContextWrapper getUserId
                //java.lang.StringBuilder toString
                //google.flutter.FlutterEmbeddingFragmentActivity onRequestPermissionsResult int java.lang.String[] int[]
                ////com.yunzhan.liaobei.login.-$$Lambda$d$4HwVtv04Lx17IaUqgD2gvcGyzJE
                //com.android.internal.os.ZygoteHooks preFork
//                findAndHookMethod("com.android.internal.os.ZygoteHooks",loadPackageParam.classLoader,"preFork",new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        try {
//                            super.afterHookedMethod(param);
//                            for (int i = 0; i < param.args.length; i++) {
//                                XposedBridge.log("在此" + i + "：" + param.args[i].toString());
//                            }
//                        }catch (Throwable throwable){
//                            XposedBridge.log("异常："+throwable);
//                        }
//                    }
//                });
                //获取tno红包id,&uid,tk
                //登录方法public void com.yunzhan.liaobei.login.d.a(android.net.Uri)
                //03-16 23:54:38.099 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.a(java.lang.String)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.a(java.util.ArrayList)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.a(java.util.List)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.b(android.net.Uri)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.b(java.util.ArrayList)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.c(android.net.Uri)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.d(android.net.Uri)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法public void com.yunzhan.liaobei.login.d.run()
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法private void com.yunzhan.liaobei.login.d.a()
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法static void com.yunzhan.liaobei.login.d.a(com.yunzhan.liaobei.login.d)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法private static void com.yunzhan.liaobei.login.d.a(io.a.d) throws java.lang.Exception
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法private void com.yunzhan.liaobei.login.d.b()
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法static void com.yunzhan.liaobei.login.d.b(com.yunzhan.liaobei.login.d)
                //03-16 23:54:38.100 I/Xposed  ( 9825): 登录方法private void com.yunzhan.liaobei.login.d.c()
                //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ0OTcyNTQsInN1IjpudWxsLCJjdSI6eyJpcCI6IjExMi45Ni4xNzkuNTgiLCJ1c2VySWQiOiIxMDUyNTM4NjgxIiwicGFjayI6IjAiLCJ2ZXIiOiIyLjAuNS4xMCIsImltZWkiOiI4NjExNjE1ODIyNzE3OTUiLCJuZXQiOiIyIiwib3MiOiJBbmRyb2lkIn19.WtWeOW0BuPd-kSHJS5FQQPVkwSszZiCiMf2MCmb3Dvo
                //android.content.ContextWrapper enforceCallingOrSelfUriPermission android.net.Uri int java.lang.String
//              //com.alibaba.wireless.security.open.SecurityGuardManager
                //com.yunzhan.liaobei.utils.aa
                //com.yunzhan.liaobei.setting.crop.a
                //com.yunzhan.liaobei.setting.myself.privacysecurit.gesturelock.b$a
                //com.yunzhan.liaobei.setting.myself.privacysecurit.gesturelock.b a
                //com.cqchat.j.i.k a
                //
                //com.yunzhan.liaobei.account.ui.redpacket.b.a
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity a com.cqchat.k.a.a.d.b.c.a.l.a
                //com.yunzhan.liaobei.setting.myself.privacysecurit.gesturelock.b a java.lang.String
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity c com.google.common.collect.ImmutableList int
                //com.yunzhan.liaobei.chat.groupchat.GroupChatActivity a com.cqchat.j.l.f.b com.cqchat.e.a.n.c.a.b.b
                //com.alibaba.wireless.security.open.SecurityGuardManager
                //com.yunzhan.liaobei.mainview.MainTabActivity onRequestPermissionsResult int java.lang.String[] int[]
//                findAndHookMethod("com.alibaba.wireless.security.open.safetoken", classLoader,"ISafeTokenComponent",new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        for(int i =0;i<param.args.length;i++){
//                            XposedBridge.log("在此tk"+i+"："+param.args[i].toString());
//                        }
//                    }
//                });
            }

        });

    }
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.yunzhan.liaobei")) {
            hideModule(loadPackageParam);
            XposedBridge.log("包名：" + loadPackageParam.packageName);
            dohook(loadPackageParam);
//            XposedHelpers.findAndHookConstructor("com.yunzhan.liaobei.account.ui.redpacket.GroupRedPacketDetailActivity",classLoader, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("密钥1： " + param.args[0].toString());
//                    XposedBridge.log("内容2： " + param.args[1].toString());
//                    param.setResult(param.args[1].toString());
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                   // param.setResult("你已被劫持");
//                    XposedBridge.log("之后："+param.args[0]);
//                    XposedBridge.log("密钥3： " + param.args[0].toString());
//                    XposedBridge.log("内容4： " + param.args[1].toString());
//                }
//            });



        }
    }
    private void toast(final String content) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Toast.makeText(globalContext, content, LENGTH_LONG).show();
                    }catch (Throwable throwable){
                        XposedBridge.log(content);
                    }

                }
            });


    }
    private void getdetail(String mtdata,String uid,String uuid,String ver,String roomname,String sengname,String msg,Long starttime){
        String datail_url = new StringBuffer("https://api.liaobe.cn/csh/hb?")
                .append("data").append("=").append(mtdata)
                .append("&").append("cpt").append("=").append("1566733373002")
                .append("&").append("uid").append("=").append(uid)
                .append("&").append("uuid").append("=").append(uuid)
                .append("&").append("ver").append("=").append(ver)
                .toString();
        try {
            URL Datail_url = new URL(datail_url);
            HttpURLConnection connection2 = (HttpURLConnection) Datail_url.openConnection();

            connection2.setDoOutput(true); // 设置该连接是可以输出的
            connection2.setRequestMethod("GET"); // 设置请求方式
            connection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            BufferedReader br2 = new BufferedReader(new InputStreamReader(connection2.getInputStream(), "utf-8"));
            String line2 = null;
            StringBuilder result2 = new StringBuilder();
            while ((line2 = br2.readLine()) != null) { // 读取数据
                result2.append(line2 + "\n");
            }
            connection2.disconnect();
            long endtime = new Date().getTime();
            Long time = (endtime - starttime);
            //JSONObject r2 = new JSONObject( new String(result2.toString().getBytes("UTF-8"), "gbk"));
            JSONObject r2 = new JSONObject( result2.toString());
            //{"data":{
            // "hb":{
            // "hbid":2004201913224065181,
            // "tno":"302004201913224065181",
            // "rid":184385545,
            // "tp":1,
            // "tuid":0,
            // "sts":1,
            // "msg":"580",
            // "tkncnt":8,
            // "cnt":9,
            // "tknamt":288.19,
            // "amt":300,
            // "tmlngth":0,
            // "ise":1,
            // "rmny":12.94,
            // "ct":1587381202000,
            // "rcvrs":[{"hbrid":2004201913224095240,"uid":1045755620,"unk":"赵先生","mny":1.28,"ismxmny":0,"tm":1587381203000},{"hbrid":2004201913224015203,"uid":1082831766,"unk":"秒7","mny":61.4,"ismxmny":1,"tm":1587381203000},{"hbrid":2004201913224075230,"uid":1098543977,"unk":"中了","mny":55.01,"ismxmny":0,"tm":1587381203000},{"hbrid":2004201913224055220,"uid":1082602970,"unk":"秒4","mny":25.23,"ismxmny":0,"tm":1587381203000},{"hbrid":2004201913224085196,"uid":1052538681,"unk":"根号③","mny":12.94,"ismxmny":0,"tm":1587381203000},{"hbrid":2004201913224075234,"uid":1098502204,"unk":"惜念星空","mny":53.95,"ismxmny":0,"tm":1587381203000},{"hbrid":2004201913224055226,"uid":1097491545,"unk":"王大少爷","mny":44.58,"ismxmny":0,"tm":1587381203000}]}},
            // "m":"","r":"0"}

            JSONObject hb1 = r2.getJSONObject("data");
            JSONObject hb2 = hb1.getJSONObject("hb");
            Double rmny = hb2.getDouble("rmny");
            if(rmny>0){
                Qiang_flang =true;
            }
            String from = "抢到："+ rmny + "元\n" +"群名：" + roomname + "\n" +"来自：" + sengname+ "\n" +"留言：" + msg + "\n" + "耗时：" +time + "ms";
            toast(from);
            XposedBridge.log(r2.toString());
        }catch (Exception e) {
            XposedBridge.log(e);
        }
    }
    private void qiang_hb(String mtdata,String uid,String uuid,String ver,String roomname,String sengname,String msg,Long starttime){
        //抢
        String openLuckyMoneyUrl = new StringBuffer("https://api.liaobe.cn/csh/rhb?")
                .append("cid").append("=").append(1003)
                .append("&").append("cpt").append("=").append("1566733373002")
                .append("&").append("data").append("=").append(mtdata)
                .append("&").append("den").append("=").append("iPhone%206S")
                .append("&").append("dv").append("=").append(2)
                .append("&").append("evs").append("=").append("e_0.0.1")
                .append("&").append("idfa").append("=").append(uuid)
                .append("&").append("pkgn").append("=").append("com.chaoqu.lailiaobei")
                .append("&").append("uid").append("=").append(uid)
                .append("&").append("uuid").append("=").append(uuid)
                .append("&").append("ver").append("=").append(ver)
                .toString();
        try {
            URL url = new URL(openLuckyMoneyUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
            JSONObject r=new JSONObject(result.toString());
            if (r.getInt("r")==0){
                getdetail( mtdata, uid, uuid, ver, roomname, sengname, msg,starttime);
            }
            else {
                toast("没抢到");
            }
//                                  int start=r.indexOf("{");
//                                  int end=r.indexOf(";");
//                                  json=r.substring(start,end);


        }catch (Exception e) {
            XposedBridge.log(e);
        }
    }

}


























//import android.content.Context;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.Toast;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
//import de.robv.android.xposed.IXposedHookLoadPackage;
//import de.robv.android.xposed.XC_MethodHook;
//import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
//
//import static android.widget.Toast.LENGTH_LONG;
//import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
//import de.robv.android.xposed.callbacks.XC_LoadPackage;
//
//// 自定义的回调函数接口
//
//public class HookToast implements IXposedHookLoadPackage {
//    private static Context globalContext;
//    static String strClassName = "";
//
//    @Override
//    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        toast(lpparam.packageName);
//        // 被Hook操作的目标Android应用的包名，进行Hook操作的过滤
//        String strPackageName = "com.yunzhan.liaobei";
//        if (lpparam.packageName.equals(strPackageName)) {
//
//            XposedBridge.log("Loaded App:" + lpparam.packageName);
//
////          // 枚举指定Android应用的所有类方法并对指定的类方法进行java Hook操作
////          PackageHooker packageHooker = new PackageHooker(lpparam);
////          // 获取指定类的方法、属性变量、内部类等的信息
////          ClassLoader classLoader = lpparam.classLoader;
////          Class<?> dumpClass = XposedHelpers.findClass("com.tencent.bugly.lejiagu.crashreport.BuglyLog", classLoader);
////          packageHooker.dumpClass(dumpClass);
//
////           // 被Hook操作的目标类的名称
////          String strNomalCln = "";
////           // 被Hook操作的目标类的方法的名称
////          String strNomalMdn = "";
////          // 在Android应用默认的classes.dex文件中的类方法的Hook操作
////          XposedHelpers.findAndHookMethod(
////                   // 被Hook操作的目标类
////                  strNomalCln,
////                  lpparam.classLoader,
////                  // 被Hook操作的目标类方法
////                  strNomalMdn,
////                  // 被Hook操作的目标类方法的第1个参数的类型
////                  String.class,
////                  // 被Hook操作的目标类方法的第2个参数的类型
////                  String.class,
////                  new XC_MethodHook() {
////                      // 在被Hook操作的类方法执行之前执行代码
////                      @Override
////                      protected void beforeHookedMethod(MethodHookParam param)
////                              throws Throwable {
////
////                          // 打印被Hook操作的目标类方法的第1个参数值
////                          XposedBridge.log("beforeHookedMethod 第1个参数:" + param.args[0]);
////                          // 打印被Hook操作的目标类方法的第2个参数值
////                          XposedBridge.log("beforeHookedMethod  第2个参数:" + param.args[1]);
////
////                          // 打印被Hook操作的目标类方法的函数返回值ֵ
////                          XposedBridge.log("beforeHookedMethod result:" + param.getResult());
////                      }
////
////                      // 在被Hook操作的类方法执行之后执行代码
////                      @Override
////                      protected void afterHookedMethod(MethodHookParam param)
////                              throws Throwable {
////
////                          // 打印被Hook操作的目标类方法的第1个参数值
////                          XposedBridge.log("afterHookedMethod userName:" + param.args[0]);
////                          // 打印被Hook操作的目标类方法的第2个参数值
////                          XposedBridge.log("afterHookedMethod sn:" + param.args[1]);
////
////                          // 修改被Hook操作的目标类方法的函数返回值
////                          param.setResult(true);
////
////                          // 打印被Hook操作的目标类方法的函数返回值ֵ
////                          XposedBridge.log("afterHookedMethod 函数返回值:" + param.getResult());
////                      }
////                  });
//
//
//            // 不在Android应用默认的classes.dex文件中的类方法的Hook操作，例如:
//            // 1.MultiDex情况下的，多dex文件中的类方法的Hook操作，例如:classes1.dex中的类方法
//            // 2.主dex加载的jar(包含dex)情况下的，类方法的的Hook操作
//
//            // Hook类方法ClassLoader#loadClass(String)
//            findAndHookMethod(ClassLoader.class, "loadClass", String.class, new XC_MethodHook() {
//
//                // 在类方法loadClass执行之后执行的代码
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                    // 参数的检查
//                    if (param.hasThrowable()) {
//                        return;
//                    }
//
//                    // 获取指定名称的类加载之后的Class<?>
//                    Class<?> clazz = (Class<?>) param.getResult();
//                    // 获取加载的指定类的名称
//                    String strClazz = clazz.getName();
//                    XposedBridge.log("LoadClass : "+strClazz);
//
////                  // 被Hook操作的目标类名称
////                  String strClazzName = "";
////                  // 被Hook操作的类方法的名称
////                  String strMethodName = "";
//
//                    // 所有的类都是通过loadClass方法加载的
//                    // 过滤掉Android系统的类以及一些常见的java类库
//                    if (!strClazz.startsWith("android.")
//                            && !strClazz.startsWith(".system")
//                            && !strClazz.startsWith("java.")
//                            && !strClazz.startsWith("org.")
//                            && !strClazz.contains("umeng.")
//                            && !strClazz.contains("com.google")
//                            && !strClazz.contains(".alipay")
//                            && !strClazz.contains(".netease")
//                            && !strClazz.contains(".alibaba")
//                            && !strClazz.contains(".pgyersdk")
//                            && !strClazz.contains(".daohen")
//                            && !strClazz.contains(".bugly")
//                            && !strClazz.contains("mini")
//                            && !strClazz.contains("xposed")) {
//                        // 或者只Hook加密算法类、网络数据传输类、按钮事件类等协议分析的重要类
//
//                        // 同步处理一下
//                        synchronized (this.getClass()) {
//
//                            // 获取被Hook的目标类的名称
//                            strClassName = strClazz;
//                            //XposedBridge.log("HookedClass : "+strClazz);
//                            // 获取到指定名称类声明的所有方法的信息
//                            Method[] m = clazz.getDeclaredMethods();
//                            // 打印获取到的所有的类方法的信息
//                            for (int i = 0; i < m.length; i++) {
//
//                                //XposedBridge.log("HOOKED CLASS-METHOD: "+strClazz+"-"+m[i].toString());
//                                if (!Modifier.isAbstract(m[i].getModifiers())           // 过滤掉指定名称类中声明的抽象方法
//                                        && !Modifier.isNative(m[i].getModifiers())     // 过滤掉指定名称类中声明的Native方法
//                                        && !Modifier.isInterface(m[i].getModifiers())  // 过滤掉指定名称类中声明的接口方法
//                                ) {
//
//                                    // 对指定名称类中声明的非抽象方法进行java Hook处理
//                                    XposedBridge.hookMethod(m[i], new XC_MethodHook() {
//
//                                        // 被java Hook的类方法执行完毕之后，打印log日志
//                                        @Override
//                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                                            // 打印被java Hook的类方法的名称和参数类型等信息
//                                            XposedBridge.log("HOOKED METHOD: "+strClassName+"-"+param.method.toString());
//                                        }
//                                    });
//                                }
//                            }
//                        }
//
//
////                  // 所有的类都是通过loadClass方法加载的
////                  // 所以这里通过判断全限定类名，查找到被Hook操作的目标类
////                  if (strClazz.contains(strClazzName)) {
////
////                      // Hook目标类方法
////                      findAndHookMethod(clazz,
////                              // 被Hook操作的类方法的名称
////                              strMethodName,
////                              // 被Hook操作的类方法的参数类型
////                              //paramTypes, // 根据实际情况进行修改
////                              new XC_MethodHook() {
////                          @Override
////                          protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////
////                              // ......
////
////                              // 打印被Hook操作的目标类方法的第1个参数值
////                              XposedBridge.log("beforeHookedMethod 第1个参数:" + param.args[0]);
////                              // 打印被Hook操作的目标类方法的第2个参数值
////                              XposedBridge.log("beforeHookedMethod  第2个参数:" + param.args[1]);
////
////                              // ......
////                          }
////
////                          @Override
////                          protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////
////                              // ......
////
////                              // 打印被Hook操作的目标类方法的函数返回值ֵ
////                              XposedBridge.log("afterHookedMethod 函数返回值:" + param.getResult());
////
////                              // ......
////                          }
////                      });
////                  }
//                        // ......
//
//                    }
//                }
//            });
//        }
//
//
//    }
//
//    // 获取指定名称的类声明的类成员变量、类方法、内部类的信息
//    public void dumpClass(Class<?> actions) {
//
//        XposedBridge.log("Dump class " + actions.getName());
//        XposedBridge.log("Methods");
//
//        // 获取到指定名称类声明的所有方法的信息
//        Method[] m = actions.getDeclaredMethods();
//        // 打印获取到的所有的类方法的信息
//        for (int i = 0; i < m.length; i++) {
//
//            XposedBridge.log(m[i].toString());
//        }
//
//        XposedBridge.log("Fields");
//        // 获取到指定名称类声明的所有变量的信息
//        Field[] f = actions.getDeclaredFields();
//        // 打印获取到的所有变量的信息
//        for (int j = 0; j < f.length; j++) {
//
//            XposedBridge.log(f[j].toString());
//        }
//
//        XposedBridge.log("Classes");
//        // 获取到指定名称类中声明的所有内部类的信息
//        Class<?>[] c = actions.getDeclaredClasses();
//        // 打印获取到的所有内部类的信息
//        for (int k = 0; k < c.length; k++) {
//
//            XposedBridge.log(c[k].toString());
//        }
//    }
//    private void toast(final String content) {
//
//            new Handler(Looper.getMainLooper()).post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(globalContext, content, LENGTH_LONG).show();
//                }
//            });
//
//
//    }
//}
//
///**
// * Look up a method and place a hook on it. The last argument must be the callback for the hook.
// * @see #findMethodExact(Class, String, Object...)
// */
///* 目标java方法的Hook
//public static XC_MethodHook.Unhook findAndHookMethod(Class<?> clazz, String methodName, Object... parameterTypesAndCallback) {
//    if (parameterTypesAndCallback.length == 0 || !(parameterTypesAndCallback[parameterTypesAndCallback.length-1] instanceof XC_MethodHook))
//        throw new IllegalArgumentException("no callback defined");
//
//    XC_MethodHook callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length-1];
//    Method m = findMethodExact(clazz, methodName, getParameterClasses(clazz.getClassLoader(), parameterTypesAndCallback));
//
//    return XposedBridge.hookMethod(m, callback);
//}*/
//
///** @see #findAndHookMethod(Class, String, Object...) */
///* 目标java方法的Hook
// public static XC_MethodHook.Unhook findAndHookMethod(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback) {
//    return findAndHookMethod(findClass(className, classLoader), methodName, parameterTypesAndCallback);
//}*/
//
//
///**
// * Loads the class with the specified name. Invoking this method is
// * equivalent to calling {@code loadClass(className, false)}.
// * <p>
// * <strong>Note:</strong> In the Android reference implementation, the
// * second parameter of {@link #loadClass(String, boolean)} is ignored
// * anyway.
// * </p>
// *
// * @return the {@code Class} object.
// * @param className
// *            the name of the class to look for.
// * @throws ClassNotFoundException
// *             if the class can not be found.
// */
////public Class<?> loadClass(String className) throws ClassNotFoundException {
////        return loadClass(className, false);
////    }
