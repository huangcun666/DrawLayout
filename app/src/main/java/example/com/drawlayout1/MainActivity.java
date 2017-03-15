package example.com.drawlayout1;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private DrawerLayout dl;
    private ListView lv;
    private ArrayList<String> al;
    private ArrayAdapter<String> aa;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl= (DrawerLayout) findViewById(R.id.dl);
        lv= (ListView) findViewById(R.id.lv);
        al=new ArrayList<String>();
        for (int i=0;i<5;i++){
            al.add("诸葛钢铁"+i);
        }
        aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(this);
        actionBarDrawerToggle=
                new ActionBarDrawerToggle
                        (this,dl,R.drawable.ic_drawer,R.string.draw_open,
                                R.string.draw_close){
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        getSupportActionBar().setTitle("请选择");
                        invalidateOptionsMenu();//call onCreateOptionsMenu
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        getSupportActionBar().setTitle(getTitle());
                        invalidateOptionsMenu();//call onCreateOptionsMenu
                    }
                };

        dl.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment fragment=new Fragment1();
        Bundle bundle=new Bundle();
        bundle.putString("text",al.get(i));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();
        dl.closeDrawer(lv);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent=new Intent();
                Uri uri=Uri.parse("http://baidu.com");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean b=dl.isDrawerOpen(lv);
        menu.findItem(R.id.setting).setVisible(!b);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();//没用，不同步
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
