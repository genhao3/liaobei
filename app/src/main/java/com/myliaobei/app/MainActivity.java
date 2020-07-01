package com.myliaobei.app;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SettingsFragment mSettingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            mSettingsFragment = new SettingsFragment();
            replaceFragment(R.id.settings_container, mSettingsFragment);
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void replaceFragment(int viewId, android.app.Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }
    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //try{
            // getPreferenceManager().setSharedPreferencesMode(MODE_PRIVATE);//安卓7.0以上需要把MODE_WORLD_READABLE改为MODE_PRIVATE，但是却读取不了界面的值，默认操作模式，代表该文件是私有数据，只能被应用本身访问
            // }
            //catch (Exception e){
            getPreferenceManager().setSharedPreferencesMode(MODE_WORLD_READABLE);//真实手机能读取到数据，模拟器不行
            //}

            addPreferencesFromResource(R.xml.pref_setting);

            Preference author = findPreference("author");
            author.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference pref) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    String payUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1351688745";
                    intent.setData(Uri.parse(payUrl));
                    try {
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        } else {
                            intent.setData(Uri.parse(payUrl));
                            startActivity(intent);
                        }
                    }
                    catch (Throwable localThrowable2){}
                    return true;
                }
            });

//            Preference jiaoliu = findPreference("jiaoliu");
//
//            jiaoliu.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference pref) {
//
//                    Intent intent = new Intent();
//                    intent.setAction("android.intent.action.VIEW");
//                    String payUrl = "mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D";
//                    intent.setData(Uri.parse(payUrl+"8n8HGA52XornOVk1l5DczydeZ451Mun8"));//key获取地址是  http://qun.qq.com/join.html
//                    try {
//                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                            startActivity(intent);
//                        } else {
//                            intent.setData(Uri.parse(payUrl));
//                            startActivity(intent);
//                        }
//                    }
//                    catch (Throwable localThrowable2){
//
//                    }
//                    return true;
//                }
//            });

        }
    }
}
