//package com.example.music3;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.telephony.PhoneStateListener;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//public class PhoneCallListener {
//
//}
//
///*  Отработка входящих звонков, можно крактически любой функционал добавить вплоть до считывания номер звонящего.
//    Добавляем в манифест (обязательно в конце перед словом манифест) :
//        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
//    </manifest>
//
//    ЧТобы код работал надо вручную добавить разрешение пользователем через настройки телефона!
//                        ЭТО ВАЖНО!
// */
//class Main extends AppCompatActivity {
//    private static final String TAG = "MyLog";
//
//    private TelephonyManager telephonyManager;
//    private MyPhoneStateListener myPhoneStateListener;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Log.d(TAG, "onCreate");
//
//        // Получение объекта TelephonyManager
//        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//
//        // Создание и регистрация объекта MyPhoneStateListener для получения уведомлений о событиях звонка
//        myPhoneStateListener = new MyPhoneStateListener();
//        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Отмена регистрации объекта MyPhoneStateListener при выходе из активности или службы
//        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
//    }
//
//
//
//    // Реализация класса MyPhoneStateListener с обработкой состояний звонка
//    private static class MyPhoneStateListener extends PhoneStateListener {
//        //applicationContext.registerReceiver();
//        @Override
//        public void onCallStateChanged(int state, String incomingNumber) {
//            super.onCallStateChanged(state, incomingNumber);
//            switch (state) {
//                case TelephonyManager.CALL_STATE_IDLE:
//                    // Звонок завершен
//                    Log.d(TAG, "Звонок завершен");
//
//                    break;
//                case TelephonyManager.CALL_STATE_OFFHOOK:
//                    // Исходящий/входящий звонок начался
//                    Log.d(TAG, "Исходящий/входящий звонок начался");
//
//                    break;
//                case TelephonyManager.CALL_STATE_RINGING:
//                    // Входящий звонок
//                    Log.d(TAG, "Входящий звонок");
//                    break;
//            }
//        }
//    }}