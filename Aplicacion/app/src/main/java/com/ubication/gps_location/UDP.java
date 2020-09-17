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
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class UDP extends AsyncTask<String,Void,Void>
{

    protected Void doInBackground(String... voids) {

        String mensaje = voids[0];

        //puerto del servidor
        final int PUERTO_SERVIDOR = 20000;
        final String IP_FINAL = "190.165.37.120";
        //buffer donde se almacenara los mensajes

        byte[] buffer = new byte[1024];

        try {

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();

            //Convierto el mensaje a bytes
            buffer = mensaje.getBytes();

            //Creo un datagrama
            DatagramPacket coordenadas = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(IP_FINAL), PUERTO_SERVIDOR);

            //Lo envio con send
            socketUDP.send(coordenadas);


            //cierro el socket
            socketUDP.close();

        } catch (SocketException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

