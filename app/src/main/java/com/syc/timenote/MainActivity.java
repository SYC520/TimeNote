/*
 *    项目名称:TimeNote
 *    文件名称:MainActivity.java
 *    Date:4/3/20 4:01 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.syc.timenote.adapter.NoteAdapter;
import com.syc.timenote.bean.Note;
import com.syc.timenote.ui.LoginActivity;
import com.syc.timenote.ui.SettingActivity;
import com.syc.timenote.utils.AnalysisUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private Note[] notes = {
            new Note(1, "content1", "game", "2020.4.4", "title1", "subContent"),
            new Note(2, "content2", "game", "2020.4.4", "title2", "subContent"),
    };
    private List<Note> noteList = new ArrayList<>();
    SearchView mSearchView;
    SearchView.SearchAutoComplete mSearchAutoComplete;
    private NoteAdapter adapter;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    TextView mTv_userName;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        initNotes();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) searchItem.getActionView();
        //通过id得到搜索框控件
        mSearchAutoComplete = mSearchView.findViewById(R.id.search_src_text);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void initNotes() {
        noteList.clear();
        for (int i = 0; i < 50; ++i) {
            Random random = new Random();
            int index = random.nextInt(notes.length);
            noteList.add(notes[index]);
        }
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        View view = navigationView.getHeaderView(0);
        mTv_userName = view.findViewById(R.id.user_name);
        if (isLogin = (AnalysisUtils.isLogin(this)))
            mTv_userName.setText(AnalysisUtils.getLoginUserName(this));
        else mTv_userName.setText(R.string.no_login);
        fab.setOnClickListener(this);
        mTv_userName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "data deleted", Snackbar.LENGTH_SHORT).setAction("undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "data restored", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.user_name:
                if (!isLogin) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            default:
        }
    }
}

