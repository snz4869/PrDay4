package id.adiyusuf.projectaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListDetailDataActivity extends AppCompatActivity {

    String id;
    EditText edit_id, edit_nama_dpn,edit_nama_blk, edit_alamat, edit_rek,edit_status,edit_cif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Account");

        edit_id = findViewById(R.id.edit_id);
        edit_nama_dpn = findViewById(R.id.edit_nama_dpn);
        edit_nama_blk = findViewById(R.id.edit_nama_blk);
        edit_alamat = findViewById(R.id.edit_alamat);
        edit_rek = findViewById(R.id.edit_rek);
        edit_status = findViewById(R.id.edit_status);
        edit_cif = findViewById(R.id.edit_cif);

        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.ACC_ID);
        edit_id.setText(id);

        getJSON();
    }

    private void getJSON() {

        // batuan dari class AsynTask
        class GetJSON extends AsyncTask<Void,Void,String> { // boleh membuat class dalam method (Inner Class)
            ProgressDialog loading;

            @Override
            protected void onPreExecute() { // sebelum proses
                super.onPreExecute();
                loading = ProgressDialog.show(ListDetailDataActivity.this,
                        "Mengambil Data","Harap Menunggu...",
                        false,false);
            }

            @Override
            protected String doInBackground(Void... voids) { // saat proses
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL,id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) { // setelah proses
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
//                Toast.makeText(ListDetailDataActivity.this, message.toString(),
//                        Toast.LENGTH_LONG).show();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama_dpn = object.getString(Konfigurasi.TAG_JSON_NAMA_DPN);
            String nama_blk = object.getString(Konfigurasi.TAG_JSON_NAMA_BLK);
            String alamat = object.getString(Konfigurasi.TAG_JSON_ALAMAT);
            String rek = object.getString(Konfigurasi.TAG_JSON_REK);
            String status = object.getString(Konfigurasi.TAG_JSON_STATUS);
            String cif = object.getString(Konfigurasi.TAG_JSON_CIF);

            edit_nama_dpn.setText(nama_dpn);
            edit_nama_blk.setText(nama_blk);
            edit_alamat.setText(alamat);
            edit_rek.setText(rek);
            edit_status.setText(status);
            edit_cif.setText(cif);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();
        return true;
    }

}