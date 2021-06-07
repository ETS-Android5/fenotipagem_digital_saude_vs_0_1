package br.ufma.lsdi.digitalphenotyping.contextdataprovider.services;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufma.lsdi.cddl.listeners.ISubscriberListener;
import br.ufma.lsdi.cddl.message.Message;
import br.ufma.lsdi.cddl.pubsub.Subscriber;
import br.ufma.lsdi.cddl.pubsub.SubscriberFactory;
import br.ufma.lsdi.digitalphenotyping.BusSystem;

public class ContextDataProvider{
//public class ContextDataProvider extends Service {
//    private String statusCon = "undefined";
//    private final BusSystem busSystem = BusSystem.getInstance();
//    private static final String TAG = br.ufma.lsdi.digitalphenotyping.contextdataprovider.services.ContextDataProvider.class.getName();
//    Subscriber sub;
//    Subscriber subDeactive;
//    //Subscriber subPlugins;
//
//    public ContextDataProvider() {
//    }
//
//
//    @Override
//    public void onCreate() {
//        sub = SubscriberFactory.createSubscriber();
//        sub.addConnection(busSystem.getInstance().getInstanceCDDL().getConnection());
//
//        subDeactive = SubscriberFactory.createSubscriber();
//        subDeactive.addConnection(busSystem.getInstance().getInstanceCDDL().getConnection());
//
////        subPlugins = SubscriberFactory.createSubscriber();
////        subPlugins.addConnection(busSystem.getInstance().getInstanceCDDL().getConnection());
////        subDeactive.subscribeServiceByName("a");
////        subPlugins.subscribeTopic("mhub/l/query_response_topic");
////        subPlugins.setSubscriberListener(subscriberPlugins);
//    }
//
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.i(TAG, "#### Iniciando ContextDataProvider");
//
//        subscribeMessageActive("activesensor");
//
//        subscribeMessageDeactive("deactivatesensor");
//
//        //subscribeMessagePlugin("comunicationexternal");  // exemplo de topic: mhub/+/command_topic
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//
//    public void subscribeMessageActive(String serviceName) {
//        sub.subscribeServiceByName(serviceName);
//        sub.setSubscriberListener(subscriberStart);
//    }
//
//    public void subscribeMessageDeactive(String serviceName) {
//        subDeactive.subscribeServiceByName(serviceName);
//        subDeactive.setSubscriberListener(subscriberStop);
//    }
//
//
////    public void subscribeMessagePlugin(String serviceName) {
////        subDeactive.subscribeServiceByName(serviceName);
////        subDeactive.setSubscriberListener(subscriberPlugins);
////    }
//
//
//    public ISubscriberListener subscriberStart = new ISubscriberListener() {
//        @Override
//        public void onMessageArrived(Message message) {
////                    if (message.getServiceName().equals("Meu serviço")) {
////                        Log.d(TAG, ">>> #### Read messages +++++: " + message);
////                    }
//            Log.d(TAG, "#### Read messages (start):  " + message);
//
//            Object[] valor = message.getServiceValue();
//            String mensagemRecebida = StringUtils.join(valor, ", ");
//            Log.d(TAG, "#### " + mensagemRecebida);
//            String[] separated = mensagemRecebida.split(",");
//            String atividade = String.valueOf(separated[0]);
//
//
//            if (isInternalSensor(atividade) || isVirtualSensor(atividade)) {
//                Log.d(TAG, "#### Start sensor monitoring->  " + atividade);
//                startVirtualSensor(atividade);
//            } else {
//                Log.d(TAG, "#### Invalid sensor name: " + atividade);
//            }
//        }
//    };
//
//
//    public ISubscriberListener subscriberStop = new ISubscriberListener() {
//        @Override
//        public void onMessageArrived(Message message) {
////                    if (message.getServiceName().equals("Meu serviço")) {
////                        Log.d(TAG, ">>> #### Read messages +++++: " + message);
////                    }
//            Log.d(TAG, "#### Read messages (stop):  " + message);
//
//            Object[] valor = message.getServiceValue();
//            String mensagemRecebida = StringUtils.join(valor, ", ");
//            Log.d(TAG, "#### " + mensagemRecebida);
//            String[] separated = mensagemRecebida.split(",");
//            String atividade = String.valueOf(separated[0]);
//
//
//            if (isInternalSensor(atividade) || isVirtualSensor(atividade)) {
//                Log.d(TAG, "#### Stop sensor monitoring->  " + atividade);
//                stoptSensor(atividade);
//            } else {
//                Log.d(TAG, "#### Invalid sensor name: " + atividade);
//            }
//        }
//    };
//
//
//    public ISubscriberListener subscriberPlugins = new ISubscriberListener() {
//        @Override
//        public void onMessageArrived(Message message) {
////                    if (message.getServiceName().equals("Meu serviço")) {
////                        Log.d(TAG, ">>> #### Read messages +++++: " + message);
////                    }
//            Log.d(TAG, "#### >>>>>>>>>>>>>>>>>>> Read messages PLUGINS:  " + message);
//
////            Object[] valor = message.getServiceValue();
////            String mensagemRecebida = StringUtils.join(valor, ", ");
////            Log.d(TAG, "#### " + mensagemRecebida);
////            String[] separated = mensagemRecebida.split(",");
////            String atividade = String.valueOf(separated[0]);
//        }
//    };
//
//
//    public synchronized void publishMessage(String service, String text) {
//        busSystem.getInstance().publishMessage(service, text);
//    }
//
//
//    private Boolean isInternalSensor(String sensor) {
//        if (listInternalSensor().contains(sensor)) {
//            return true;
//        }
//        return false;
//    }
//
//
//    private Boolean isVirtualSensor(String sensor) {
//        if (listVirtualSensor().contains(sensor)) {
//            return true;
//        }
//        return false;
//    }
//
//
//    public List<String> listVirtualSensor() {
//        List<String> s = busSystem.getInstance().getInstanceCDDL().getSensorVirtualList();
//
//        Log.i(TAG, "\n #### Sensores virtuais disponíveis, Tamanho: \n" + s.size());
//        for (int i = 0; i < s.size(); i++) {
//            Log.i(TAG, "#### (" + i + "): " + s.get(i).toString());
//        }
//        return s;
//    }
//
//
//    public List<String> listInternalSensor() {
//        List<String> s = new ArrayList();
//        List<Sensor> sensorInternal = busSystem.getInstance().getInstanceCDDL().getInternalSensorList();
//
//        Log.i(TAG, "\n #### Sensores internos disponíveis, Tamanho: \n" + sensorInternal.size());
//        if (sensorInternal.size() != 0) {
//            for (int i = 0; i < sensorInternal.size(); i++) {
//                s.add(sensorInternal.get(i).getName());
//                Log.i(TAG, "#### (" + i + "): " + sensorInternal.get(i).getName());
//            }
//            return s;
//        }
//        return s;
//    }
//
//
//    public void startVirtualSensor(String sensor) {
//        if (sensor.equalsIgnoreCase("TouchScreen")) {
//            // Solicita permissão de desenhar (canDrawOverlays) para Toque de Tela
//            checkDrawOverlayPermission();
//            busSystem.getInstanceCDDL().startSensor(sensor, 0);
//        } else {
//            initPermissions(sensor);
//            busSystem.getInstanceCDDL().startSensor(sensor, 0);
//        }
//        //cddl.startSensor("SMS",0);
//        //cddl.startSensor("Call",0);
//        //cddl.startSensor("ScreenOnOff",0);
//    }
//
//
//    public void stoptSensor(String sensor) {
//        busSystem.getInstanceCDDL().stopSensor(sensor);
//    }
//
//
//    public void startAllVirtualSensors() {
//        // solicita permissão ao  usuário
//        initAllPermissions();
//
//        //Start sensores virtuais pelo nome e delay
//        busSystem.getInstanceCDDL().startSensor("SMS", 0);
//        busSystem.getInstanceCDDL().startSensor("Call", 0);
//        busSystem.getInstanceCDDL().startSensor("ScreenOnOff", 0);
//
//        // Solicita permissão de desenhar (canDrawOverlays) para Toque de Tela
//        checkDrawOverlayPermission();
//        busSystem.getInstanceCDDL().startSensor("TouchScreen", 0);
//    }
//
//    private void checkDrawOverlayPermission() {
//        Log.i(TAG, "#### Permissao para o sensor TouchScreen");
//        // check if we already  have permission to draw over other apps
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(busSystem.getContext())) {
//                // if not construct intent to request permission
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                        Uri.parse("package:" + busSystem.getContext().getPackageName()));
//                //ac.startService(intent);
//                // request permission via start activity for result
//                Log.i(TAG, "#### permissao dada pelo usuário");
//
//                busSystem.getActivity().startActivityForResult(intent, 1);
//            }
//        }
//    }
//
//
//    private static boolean hasPermissions(Context context, String... permissions) {
//        if (context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//
//    private void initPermissions(String sensor) {
//        // Checa as permissões para rodar os sensores virtuais
//        int PERMISSION_ALL = 1;
//
//        if (sensor.equalsIgnoreCase("SMS")) {
//            String[] PERMISSIONS = {
//                    // SMS entrada
//                    android.Manifest.permission.SEND_SMS,
//                    android.Manifest.permission.RECEIVE_SMS,
//                    android.Manifest.permission.READ_SMS,
//                    android.Manifest.permission.WRITE_CONTACTS,
//                    android.Manifest.permission.READ_CONTACTS,
//                    android.Manifest.permission.GET_ACCOUNTS,
//                    // SMS saída
//                    android.Manifest.permission.READ_EXTERNAL_STORAGE};
//
//            if (!hasPermissions(busSystem.getActivity(), PERMISSIONS)) {
//                Log.i(TAG, "##### Permission enabled for the sensor: " + sensor);
//                ActivityCompat.requestPermissions(busSystem.getActivity(), PERMISSIONS, PERMISSION_ALL);
//            }
//        } else if (sensor.equalsIgnoreCase("Call")) {
//            String[] PERMISSIONS = {
//                    //Call
//                    android.Manifest.permission.READ_PHONE_STATE,
//                    android.Manifest.permission.CALL_PHONE,
//                    android.Manifest.permission.READ_CALL_LOG,
//                    android.Manifest.permission.WRITE_CALL_LOG,
//                    android.Manifest.permission.ADD_VOICEMAIL};
//
//            if (!hasPermissions(busSystem.getActivity(), PERMISSIONS)) {
//                Log.i(TAG, "##### Permission enabled for the sensor: " + sensor);
//                ActivityCompat.requestPermissions(busSystem.getActivity(), PERMISSIONS, PERMISSION_ALL);
//            }
//        }
////        String[] PERMISSIONS = {
////                // SMS entrada
////                android.Manifest.permission.SEND_SMS,
////                android.Manifest.permission.RECEIVE_SMS,
////                android.Manifest.permission.READ_SMS,
////
////                // SMS saída
////                android.Manifest.permission.READ_EXTERNAL_STORAGE,
////
////                //Call
////                android.Manifest.permission.READ_PHONE_STATE,
////                android.Manifest.permission.CALL_PHONE,
////                android.Manifest.permission.READ_CALL_LOG,
////                android.Manifest.permission.WRITE_CALL_LOG,
////                android.Manifest.permission.ADD_VOICEMAIL,
////
////                // Escrita no storage Certificado Digital
////                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
////
////                // Para usar o GPS
////                android.Manifest.permission.ACCESS_FINE_LOCATION
////        };
//    }
//
//
//    private void initAllPermissions() {
//        // Checa as permissões para rodar os sensores virtuais
//        int PERMISSION_ALL = 1;
//        String[] PERMISSIONS = {
//                // SMS
//                android.Manifest.permission.SEND_SMS,
//                android.Manifest.permission.RECEIVE_SMS,
//                android.Manifest.permission.READ_SMS,
//
//                //Call
//                android.Manifest.permission.READ_PHONE_STATE,
//                android.Manifest.permission.CALL_PHONE,
//                android.Manifest.permission.READ_CALL_LOG,
//                android.Manifest.permission.WRITE_CALL_LOG,
//                android.Manifest.permission.ADD_VOICEMAIL,
//
//                // SMS saída
//                android.Manifest.permission.READ_EXTERNAL_STORAGE,
//
//                // Escrita no storage Certificado Digital
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//
//                // Para usar o GPS
//                android.Manifest.permission.ACCESS_FINE_LOCATION,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//        };
//
//        if (!hasPermissions(busSystem.getActivity(), PERMISSIONS)) {
//            Log.i(TAG, "##### Permissão Ativada");
//            ActivityCompat.requestPermissions(busSystem.getActivity(), PERMISSIONS, PERMISSION_ALL);
//        }
//    }
//
//
//    private void initPermissionsRequired() {
//        // Checa as permissões para rodar os sensores virtuais
//        int PERMISSION_ALL = 1;
//
//        if (true) {
//            String[] PERMISSIONS = {
//                    // Service location
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION
//
//                    // Outros services
//            };
//
//            if (!hasPermissions(busSystem.getActivity(), PERMISSIONS)) {
//                Log.i(TAG, "##### Permission enabled for framework");
//                ActivityCompat.requestPermissions(busSystem.getActivity(), PERMISSIONS, PERMISSION_ALL);
//            }
//        }
//    }
//

}

