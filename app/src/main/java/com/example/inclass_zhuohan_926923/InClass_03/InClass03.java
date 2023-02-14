package com.example.inclass_zhuohan_926923.InClass_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.inclass_zhuohan_926923.R;

public class InClass03 extends AppCompatActivity implements IntefaceToInclass03Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class03);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ContainerFragment, EditProfileFragment.newInstance(), "EditProfile")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void avatarClickedInSelectAvatar(int drawableId){
        getSupportFragmentManager().popBackStack();
        EditProfileFragment fragment = (EditProfileFragment) getSupportFragmentManager().findFragmentByTag("EditProfile");
        Bundle bundle  = new Bundle();
        bundle.putInt("drawableID",drawableId);
        fragment.setArguments(bundle);

    }

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            super.onBackPressed();
        }
    }


    @Override
    public void submitButtonClickedInEditProfile(Profile profile){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ContainerFragment, DisplayFragment.newInstance(profile), "Display")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void selectAvtarClickedInEditProfile(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ContainerFragment, SelectAvatarFragment.newInstance(), "SelectAvatar")
                .addToBackStack(null)
                .commit();
    }




}