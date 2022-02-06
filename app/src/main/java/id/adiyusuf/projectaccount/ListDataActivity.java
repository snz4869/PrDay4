package id.adiyusuf.projectaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list_view;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        // menampilkan list view
        list_view = findViewById(R.id.list_view);
        list_view.setOnItemClickListener(this);

        getJSON();
    }

    private void getJSON() {

        // batuan dari class AsynTask
        class GetJSON extends AsyncTask<Void,Void,String> { // boleh membuat class dalam method (Inner Class)
            ProgressDialog loading;

            @Override
            protected void onPreExecute() { // sebelum proses
                super.onPreExecute();
                loading = ProgressDialog.show(ListDataActivity.this,
                        "Mengambil Data","Harap Menunggu...",
                        false,false);
            }

            @Override
            protected String doInBackground(Void... voids) { // saat proses
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL);
                return result;
            }

            @Override
            protected void onPostExecute(String message) { // setelah proses
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
//                Toast.makeText(ListDataActivity.this,
//                message.toString(), Toast.LENGTH_SHORT).show();
                Log.d("DATA_JSON: ",JSON_STRING);

                //menampilkan data dalam bentuk list view
                displayAllData();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayAllData() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA_JSON: ",JSON_STRING);

            for (int i = 0; i < result.length(); i++){
                JSONObject object = result.getJSONObject(i);
                String id = object.getString(Konfigurasi.TAG_JSON_ID);
                String name = object.getString(Konfigurasi.TAG_JSON_NAMA_DPN);
                String rek = object.getString(Konfigurasi.TAG_JSON_REK);

                HashMap<String,String> account = new HashMap<>();
                account.put(Konfigurasi.TAG_JSON_ID,id);
                account.put(Konfigurasi.TAG_JSON_NAMA_DPN,name);
                account.put(Konfigurasi.TAG_JSON_REK,rek);
                //ubah json ke array list
                list.add(account);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        //adapter untuk meletakan array list ke dalam list view
        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(),list,
                R.layout.activity_list_item,
                new String[]{Konfigurasi.TAG_JSON_ID,Konfigurasi.TAG_JSON_NAMA_DPN,Konfigurasi.TAG_JSON_REK},
                new int[]{R.id.txt_id,R.id.txt_nama,R.id.txt_rek}
        );
        list_view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ketika salah satu list dipilih
        // detail: ID, Name, Desg, Salary
        Intent myIntent = new Intent(ListDataActivity.this,
                ListDetailDataActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String accId = map.get(Konfigurasi.TAG_JSON_ID).toString();
        myIntent.putExtra(Konfigurasi.ACC_ID, accId);
        startActivity(myIntent);
    }
}