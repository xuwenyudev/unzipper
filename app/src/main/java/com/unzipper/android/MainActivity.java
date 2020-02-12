package com.unzipper.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<File> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFiles();
        FileAdapter adapter = new FileAdapter(MainActivity.this, R.layout.file_item, fileList);
        final ListView fileListView = (ListView)findViewById(R.id.file_list);
        fileListView.setAdapter(adapter);
        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = fileList.get(position);
                Toast.makeText(MainActivity.this, file.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFiles(){
        for(int i = 0; i < 2; i++){
            File apple = new File("Apple", R.drawable.fox);
            fileList.add(apple);
            File banana = new File("Banana", R.drawable.fox);
            fileList.add(banana);
            File orange = new File("Orange", R.drawable.fox);
            fileList.add(orange);
            File watermelon = new File("Watermelon", R.drawable.fox);
            fileList.add(watermelon);
            File pear = new File("Pear", R.drawable.fox);
            fileList.add(pear);
            File grape = new File("Grape", R.drawable.fox);
            fileList.add(grape);
        }
    }
}
