package com.saaveale.mascota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.saaveale.mascota.adapter.PageAdapter;
import com.saaveale.mascota.fragment.PerfilFragment;
import com.saaveale.mascota.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setUpViewPager();

        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        /*Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);*/
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pet);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mFavorito:
                if(Data.getItemFavorite()==0) {
                    Snackbar.make(findViewById(android.R.id.content),"Se agregaron los 5 primeras mascotas tus favoritos automáticamente",Snackbar.LENGTH_SHORT).show();
                    Data.mascotas.get(0).setFavorite(true);
                    Data.mascotas.get(1).setFavorite(true);
                    Data.mascotas.get(2).setFavorite(true);
                    Data.mascotas.get(3).setFavorite(true);
                    Data.mascotas.get(4).setFavorite(true);
                }
                Intent intent= new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent);
                break;
            case R.id.mContacto:
                Intent iContacto= new Intent(MainActivity.this,CommentActivity.class);
                startActivity(iContacto);
                break;
            case R.id.mAcercaDe:
                Intent iAcercaDe= new Intent(MainActivity.this,BioActivity.class);
                startActivity(iAcercaDe);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}