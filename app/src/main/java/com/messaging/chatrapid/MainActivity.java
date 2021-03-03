package com.messaging.chatrapid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.messaging.chatrapid.Adapters.FragmentAdapter;
import com.messaging.chatrapid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();






        binding.viewPagerId.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tabLayoutId.setupWithViewPager(binding.viewPagerId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.search:

                break;
            case R.id.groupChat:
                    Intent  groupChat= new Intent(MainActivity.this,GroupChatActivity.class);
                    startActivity(groupChat);
                break;

            case R.id.invite:

                 break;
            case R.id.setting:
                Toast.makeText(this,"Setting Activity", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.logout :
                auth.signOut();
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}