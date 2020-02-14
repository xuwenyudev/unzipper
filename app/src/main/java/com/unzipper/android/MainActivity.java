package com.unzipper.android;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private static final String ROOT_PATH = "/";
    private List<FileItem> fileList = new ArrayList<>();
    private FileAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        adapter = new FileAdapter(MainActivity.this, R.layout.file_item, fileList);
        final ListView fileListView = (ListView)findViewById(R.id.file_list);
        fileListView.setAdapter(adapter);
        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num = fileListView.getSelectedItemPosition();
                FileItem file = fileList.get(position);
                Toast.makeText(MainActivity.this, file.getName(), Toast.LENGTH_SHORT).show();
                enterDirectory(file.getName());
            }
        });
    }

    private void getPermission() {
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        else
            enterDirectory(ROOT_PATH);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int result : grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    enterDirectory(ROOT_PATH);
                }
                else{
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    private void enterDirectory(String path){
        Log.d("hello", "file");
        //File file = new File(path);
        File file = Environment.getExternalStorageDirectory();//获取的值为： /storage/emulated/0
        //如果当前目录不是根目录
        if (!ROOT_PATH.equals(path)){
            file = new File(path);
        }

        if(!file.exists()){
            Toast.makeText(this, "目录不存在", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!file.isDirectory()){
            Toast.makeText(this, "不是目录", Toast.LENGTH_SHORT).show();
            return;
        }

        File[] files = file.listFiles();

        //添加所有文件
        fileList.clear();
        for (File f : files){
            FileItem fitem;
            if(f.isDirectory()) {
                fitem = new FileItem(f.getPath(), R.drawable.folder);
            }
            else{
                fitem = new FileItem(f.getPath(), R.drawable.file);
            }
            fileList.add(fitem);
        }

        //FileItem fitem = new FileItem("hello", R.drawable.folder);
        //fileList.add(fitem);

        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

}
