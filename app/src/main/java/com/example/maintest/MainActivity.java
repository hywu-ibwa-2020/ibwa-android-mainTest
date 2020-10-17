package com.example.maintest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // 하단 바를 위한 프레그먼트 선언.
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentCurtain fragmentCurtain = new FragmentCurtain();
    //    private FragmentTimer fragmentTimer = new FragmentTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); // 테마 지정해줌.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 첫 화면에서 커튼 프래그먼트 나오도록하기 위해서 사용한 것인데 코드 상 필요 없어보임.
//        transaction.replace(R.id.frameLayout, fragmentCurtain).commitAllowingStateLoss();

        // 하단 바 id 찾아서 BottomNavigationView 타입 bottomNavigationView 정의
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        // 리스너 적용.
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        // 첫 화면에서 커튼 프래그먼트 나오도록
        bottomNavigationView.setSelectedItemId(R.id.curtainItem);
    }

    // 하단바 클릭하면 프래그먼트 변하도록 해주는 리스너 클래스 생성.
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.curtainItem:
                    transaction.replace(R.id.frameLayout, fragmentCurtain).commitAllowingStateLoss();
                    break;
//                case R.id.timerItem:
//                    transaction.replace(R.id.frameLayout, fragmentTimer).commitAllowingStateLoss();
//                    break;
//                case R.id.recommendItem:
//                    transaction.replace(R.id.frameLayout, fragmentRecommend).commitAllowingStateLoss();
//                    break;
            }
            return true;
        }
    }
}
