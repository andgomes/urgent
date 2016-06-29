package br.ufc.dspm.urgent;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 29/06/2016.
 */
public class Util {


    public static ArrayList<PostoDeSaude> getEnderecoList(Context context){
        ArrayList<PostoDeSaude> enderecoList = new ArrayList<PostoDeSaude>();

        InputStream is = context.getResources().openRawResource(R.raw.record);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            is.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();

        try {
            JSONArray array = new JSONArray(jsonString);
            for(int i=0; i<array.length(); i++){
                PostoDeSaude posto = new PostoDeSaude();
                JSONObject obj = array.getJSONObject(i);
                if(obj.has("endereco"))posto.setEndereco(obj.getString("endereco"));
                enderecoList.add(posto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return enderecoList;

    }




    public static ArrayList<PostoDeSaude> setCoordinatesByAddress(ArrayList<PostoDeSaude> list, Context context){
        ArrayList<PostoDeSaude> newList = new ArrayList<PostoDeSaude>();

        Geocoder geocoder = new Geocoder(context);
        List<Address> enderecos = null;
        try {
            for(int i=0; i<list.size(); i++) {
                enderecos = geocoder.getFromLocationName(list.get(i).getEndereco(), 1);
                if (enderecos.size() > 0) {
                    //Log.v("tag", "coordenadas " + enderecos.get(0).getLatitude() + ", " + enderecos.get(0).getLongitude());
                    PostoDeSaude posto = new PostoDeSaude(enderecos.get(0).getLatitude(), enderecos.get(0).getLongitude());
                    posto.setEndereco(list.get(i).getEndereco());
                    newList.add(posto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return newList;
    }


}
