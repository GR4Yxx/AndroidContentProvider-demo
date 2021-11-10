package gray.helloworld.moadmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String>listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);

        listdata=new ArrayList<String>();
        fetchContact();
    }

    private void fetchContact() {
        Uri uri= Uri.parse("content://com.gray.own.moadMP");

        ContentResolver resolver=getContentResolver();
        String[] projection=null;    //use to access column-wise Data
        String selection=null;       //use to access Row-Wise Data
        String[] selectionArgs=null;
        String order=null;

        Cursor cursor= resolver.query(uri,projection,selection,selectionArgs,order);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String pid=cursor.getString(cursor.getColumnIndex("pid"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String number=cursor.getString(cursor.getColumnIndex("contact"));
                String mycontact=pid+"-"+name+ "\n" + number;
                listdata.add(mycontact);
            }
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, R.layout.list_layout,listdata);
        list.setAdapter(adapter);
    }

    private void fetchName() {
        Uri uri= Uri.parse("content://com.gray.own.moadMP");

        ContentResolver resolver=getContentResolver();
        String[] projection=null;    //use to access column-wise Data
        String selection=null;       //use to access Row-Wise Data
        String[] selectionArgs=null;
        String order=null;

        Cursor cursor= resolver.query(uri,projection,selection,selectionArgs,order);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String number=cursor.getString(cursor.getColumnIndex("contact"));
                String mycontact=name+ "\n" + number;
                listdata.add(mycontact);
            }
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, R.layout.list_layout,listdata);
        list.setAdapter(adapter);
    }
}