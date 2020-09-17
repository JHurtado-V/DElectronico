package com.ubication.gps_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {


    Button boton_GPS;
    TextView Obtenercoordenadas;
    TextView Obtenertiempo;
    TextView Obtenerhora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true); //Para mostrar el ícono de la app en el action Bar
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        Obtenercoordenadas = (TextView)findViewById(R.id.tvUbicacion);
        boton_GPS = (Button)findViewById(R.id.button);

        Obtenertiempo = (TextView) findViewById(R.id.texttime);
        Obtenerhora = (TextView) findViewById(R.id.texthora);



        boton_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);


                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {

                        Obtenercoordenadas.setText(""+location.getLatitude()+";"+location.getLongitude());

                        Calendar tiempo = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        String dataTime = simpleDateFormat.format(tiempo.getTime());
                        Obtenerhora.setText(dataTime);

                        Calendar fecha = Calendar.getInstance();
                        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
                        String dataDate = simpleDate.format(fecha.getTime());
                        Obtenertiempo.setText(dataDate);

                        //UDP2 udp2 = new UDP2();
                       // udp2.execute(Obtenercoordenadas.getText().toString()+";"+Obtenertiempo.getText().toString()+";"+ Obtenerhora.getText().toString()+";");

                        UDP udp = new UDP();
                        udp.execute(Obtenercoordenadas.getText().toString()+";"+Obtenertiempo.getText().toString()+";"+ Obtenerhora.getText().toString()+";");

                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {}

                    public void onProviderEnabled(String provider) {}

                    public void onProviderDisabled(String provider) {}
                };


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener); //GPS provider es un método de localización que utiliza Android Studio.
            }
        });

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck== PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                
            } else {

               
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }

        }

    }

    //public void send (View v)
    //{
      //  UDP2 udp2 = new UDP2();
        //udp2.execute(Obtenercoordenadas.getText().toString()+";"+Obtenertiempo.getText().toString()+";"+ Obtenerhora.getText().toString()+";");

        //UDP udp = new UDP();
        //udp.execute(Obtenercoordenadas.getText().toString()+";"+Obtenertiempo.getText().toString()+";"+ Obtenerhora.getText().toString()+";");


    //}


}
