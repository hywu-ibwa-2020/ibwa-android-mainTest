package com.example.maintest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class FragmentCurtain extends Fragment implements View.OnClickListener, ColorPickerDialog.OnColorChangedListener{

    ToggleButton lightPower_btn;
    Button lightBar_btn;
    ImageButton curtainUp;
    ImageButton lighting;
    ImageButton curtainDown;
    ImageButton btnConnect;
    ToggleButton curtain1, curtain2, curtain1_1, curtain2_1;
    int color;
    Context curtain_context;
    GradientDrawable drawable;

    private BluetoothSPP bt;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View curtain_view =  inflater.inflate(R.layout.fragment_curtain, container, false);

        curtainUp = (ImageButton) curtain_view.findViewById(R.id.curtainUp);
        curtainDown = (ImageButton) curtain_view.findViewById(R.id.curtainDown);
        curtain1 = (ToggleButton) curtain_view.findViewById(R.id.curtain1);
        curtain2 = (ToggleButton) curtain_view.findViewById(R.id.curtain2);
        curtain1_1 = (ToggleButton) curtain_view.findViewById(R.id.curtain1_1);
        curtain2_1 = (ToggleButton) curtain_view.findViewById(R.id.curtain2_1);

        lightPower_btn = (ToggleButton) curtain_view.findViewById(R.id.lightPower);
        lightBar_btn = (Button) curtain_view.findViewById(R.id.lightBar);

        curtain_context = container.getContext();
        drawable = (GradientDrawable) ContextCompat.getDrawable(curtain_context, R.drawable.light_bar_btn);



        // 조도 상태 표시 기능
        int sun_state = 100; // 조도값으로 받아오는 값 저장
         lighting = (ImageButton) curtain_view.findViewById(R.id.lighting);

        if (sun_state >= 100 && sun_state < 300) //조도값이 100~300이면 좋음 값 출력
            lighting.setImageResource(R.drawable.lighting_blue);
        else if (sun_state > 301 && sun_state < 500) //조도값이 301~500이면 보통 값 출력
            lighting.setImageResource(R.drawable.lighting_green);
        else //조도값이 500초과이면 나쁨 값 출력
            lighting.setImageResource(R.drawable.lighting_red);

//         조명바 온오프 설정 코드 (3번)
        lightPower_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {        // 전원버튼 클릭시
                if (lightPower_btn.isChecked()){         // 조명 전원 버튼이 켜져있다면
                    lightBar_btn.setClickable(true);     // 조명 바 활성화
                    lightBar_btn.setEnabled(true);

                } else {                                  // 조명 전원 버튼이 꺼져있다면
                    lightBar_btn.setClickable(false);   // 조명 바 비활성화
                    lightBar_btn.setEnabled(false);
                }

            }
        });

        lightBar_btn.setOnClickListener(this);



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

        // 블루투스 연결
        bt = new BluetoothSPP(curtain_context); //Initializing

        btnConnect = curtain_view.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(curtain_context, DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });



        return curtain_view;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "컬러피커가 열립니다.", Toast.LENGTH_LONG).show();
        color = androidx.preference.PreferenceManager.getDefaultSharedPreferences(curtain_context).getInt("color", Color.WHITE);
        new ColorPickerDialog(curtain_context, this, color).show();
    }

    @Override
    public void colorChanged(int color) {
        PreferenceManager.getDefaultSharedPreferences(curtain_context).edit().putInt("color", color).commit();
//        lightBar_btn.setBackgroundColor(color);
//        ResourcesCompat.getDrawable(curtain_context.getResources(), color, null);
        drawable.setColor(color);
        lightBar_btn.setBackground(drawable);

    }


}