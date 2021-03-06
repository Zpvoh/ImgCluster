package com.app.zpvoh.imgcluster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        WaterfallFragment.OnFragmentInteractionListener,
        MyClusterFragment.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener {

    private WaterfallFragment waterfallFragment=new WaterfallFragment();
    private MyClusterFragment myClusterFragment=new MyClusterFragment();
    public static User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateHeaderView(R.layout.nav_header_main2);
        navigationView.inflateMenu(R.menu.activity_main2_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        ImageView icon=(ImageView)header.findViewById(R.id.userView);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        if(user!=null){
            ArrayList<Group> groups=Deal.getUserGroups(user.uid);
            ArrayList<Image> images=new ArrayList<>();
            groups.forEach(group->{
                ArrayList<Image> group_imgs=Deal.getImageByGroup(group.group_id);
                images.addAll(group_imgs);
            });

            waterfallFragment=WaterfallFragment.newInstance(images);
        }else{
            Toast.makeText(this, R.string.login_hint, Toast.LENGTH_SHORT).show();
        }
        getFragmentManager().beginTransaction().replace(R.id.contentFragment, waterfallFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_join) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            if(user!=null){
                ArrayList<Group> groups=Deal.getUserGroups(user.uid);
                ArrayList<Image> images=new ArrayList<>();
                groups.forEach(group->{
                    ArrayList<Image> group_imgs=Deal.getImageByGroup(group.group_id);
                    images.addAll(group_imgs);
                });

                waterfallFragment=WaterfallFragment.newInstance(images);
            }else{
                Toast.makeText(this, R.string.login_hint, Toast.LENGTH_SHORT).show();
            }
            getFragmentManager().beginTransaction().replace(R.id.contentFragment, waterfallFragment).commit();

        } else if (id == R.id.nav_manage) {
            getFragmentManager().beginTransaction().replace(R.id.contentFragment, myClusterFragment).commit();
        } else if(id==R.id.userView){
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
