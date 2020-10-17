package com.example.maintest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCurtain extends Fragment {

    ImageButton lightPower_btn;
    Button lightbar_btn;
    ImageButton curtainUp;
    ImageButton curtainDown;
    ToggleButton curtain1, curtain2, curtain1_1, curtain2_1;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View curtain_view =  inflater.inflate(R.layout.fragment_curtain, container, false);




        // 조도 상태 표시 기능
        int sun_state = 100; // 조도값으로 받아오는 값 저장
        ImageView lighting = (ImageView) curtain_view.findViewById(R.id.lighting);

        if (sun_state >= 100 && sun_state < 300) //조도값이 100~300이면 좋음 값 출력
            lighting.setImageResource(R.drawable.lighting_blue);
        else if (sun_state > 301 && sun_state < 500) //조도값이 301~500이면 보통 값 출력
            lighting.setImageResource(R.drawable.lighting_green);
        else //조도값이 500초과이면 나쁨 값 출력
            lighting.setImageResource(R.drawable.lighting_red);

//         조명바 온오프 설정 코드01
        lightPower_btn = (ImageButton) curtain_view.findViewById(R.id.lightPower);
        lightPower_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { // 전원버튼 클릭시
                Button lightBar_btn = (Button) curtain_view.findViewById(R.id.lightBar);
                if (!lightBar_btn.isClickable()){ // 조명바 버튼이 꺼져있다면
                    lightBar_btn.setClickable(true);
                    lightPower_btn.setBackgroundResource(R.drawable.ic_baseline_power_settings_red_24);}
                else { // 조명바 버튼이 켜져있다면
                    lightBar_btn.setClickable(false);
                }

            }
        });

        // 조명바 클릭시 컬러피커 오픈
        lightbar_btn = (Button) curtain_view.findViewById(R.id.lightBar);
        lightbar_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { // 조명바 버튼 클릭시
                Toast.makeText(getActivity(), "컬러피커가 열립니다.", Toast.LENGTH_LONG).show();
            }
        });

        curtainUp = (ImageButton) curtain_view.findViewById(R.id.curtainUp);
        curtainDown = (ImageButton) curtain_view.findViewById(R.id.curtainDown);
        curtain1 = (ToggleButton) curtain_view.findViewById(R.id.curtain1);
        curtain2 = (ToggleButton) curtain_view.findViewById(R.id.curtain2);
        curtain1_1 = (ToggleButton) curtain_view.findViewById(R.id.curtain1_1);
        curtain2_1 = (ToggleButton) curtain_view.findViewById(R.id.curtain2_1);
        // 컬러바
        lightbar_btn = (Button) curtain_view.findViewById(R.id.lightBar);

        // 위쪽 화살표를 눌렀을때
        curtainUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) curtain1.getLayoutParams();
                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) curtain2.getLayoutParams();

//                Toast.makeText(getActivity(), "curtain1: " + curtain1.getHeight()
//                        + "curtain2: " + curtain2.getHeight(), Toast.LENGTH_SHORT).show();
                if (curtain1_1.isChecked() && !curtain2_1.isChecked()){
                    if (curtain1.getHeight() == 1400){
                        params.height = 100;
                        curtain1.setLayoutParams(params);
//                        curtain1.setHeight(0);
                        Toast.makeText(getActivity(), "커튼1이 올라갑니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "커튼1이 이미 올라갔습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (curtain2_1.isChecked() && !curtain1_1.isChecked()){
                    if (curtain2.getHeight() == 1400){
                        params2.height = 100;
                        curtain2.setLayoutParams(params2);
                        Toast.makeText(getActivity(), "커튼2가 올라갑니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "커튼2가 이미 올라갔습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (curtain2_1.isChecked() && curtain1_1.isChecked()){
                    params.height = 100;
                    curtain1.setLayoutParams(params);
                    params2.height = 100;
                    curtain2.setLayoutParams(params2);
                    Toast.makeText(getActivity(), "커튼1, 2가 올라갔습니다.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "활성화된 커튼이 없습니다. 커튼을 활성화 시켜주세요.", Toast.LENGTH_SHORT).show();

            };
        });



        // 오른쪽 화살표를 눌렀을때
        curtainDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) curtain1.getLayoutParams();
                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) curtain2.getLayoutParams();

//                Toast.makeText(getActivity(), "curtain1: " + curtain1.getHeight()
//                        + "curtain2: " + curtain2.getHeight(), Toast.LENGTH_SHORT).show();
                if (curtain1_1.isChecked() && !curtain2_1.isChecked()){
                    if (curtain1.getHeight() == 100){
                        params.height = 1400;
                        curtain1.setLayoutParams(params);
//                        curtain1.setHeight(0);
                        Toast.makeText(getActivity(), "커튼1이 내려갑니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "커튼1이 이미 내려갔습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (curtain2_1.isChecked() && !curtain1_1.isChecked()){
                    if (curtain2.getHeight() == 100){
                        params2.height = 1400;
                        curtain2.setLayoutParams(params2);
                        Toast.makeText(getActivity(), "커튼2가 내려갑니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "커튼2가 이미 내려갔습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (curtain2_1.isChecked() && curtain1_1.isChecked()){
                    params.height = 1400;
                    curtain1.setLayoutParams(params);
                    params2.height = 1400;
                    curtain2.setLayoutParams(params2);
                    Toast.makeText(getActivity(), "커튼1, 2가 내려갔습니다.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "활성화된 커튼이 없습니다. 커튼을 활성화 시켜주세요.", Toast.LENGTH_SHORT).show();
            };
        });



        return curtain_view;
    }



}