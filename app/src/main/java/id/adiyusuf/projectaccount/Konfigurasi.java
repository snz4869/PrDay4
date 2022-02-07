package id.adiyusuf.projectaccount;

public class Konfigurasi {
    // url web API berada
    public static final String URL_GET_ALL = "http://192.168.92.102/pegawai/tampilSemuaAccount.php";
    public static final String URL_GET_DETAIL = "http://192.168.92.102/pegawai/tampilAccount.php?id=";
    //public static final String URL_ADD = "http://192.168.92.102/pegawai/tambahPgw.php";
    public static final String URL_UPDATE = "http://192.168.92.102/pegawai/updateAcc.php?id=";
    public static final String URL_DELETE = "http://192.168.92.102/pegawai/hapusAcc.php?id=";

    // key and value json yang muncul di browser
    public static final String KEY_ACC_ID = "id";
    public static final String KEY_ACC_NAMA_DPN = "nama_depan";
    public static final String KEY_ACC_NAMA_BLK = "nama_belakng";
    public static final String KEY_ACC_ALAMAT = "alamat";
    public static final String KEY_ACC_REK = "no_rekening";
    public static final String KEY_ACC_STATUS = "status";
    public static final String KEY_ACC_CIF = "cif";

    // flag json
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID = "id";
    public static final String TAG_JSON_NAMA_DPN = "nama_depan";
    public static final String TAG_JSON_NAMA_BLK = "nama_belakang";
    public static final String TAG_JSON_ALAMAT = "alamat";
    public static final String TAG_JSON_REK = "no_rekening";
    public static final String TAG_JSON_STATUS = "status";
    public static final String TAG_JSON_CIF = "cif";

    //variable ID Account
    public static final String ACC_ID = "acc_id";
}

