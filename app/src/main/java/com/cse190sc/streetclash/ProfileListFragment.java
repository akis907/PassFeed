package com.cse190sc.streetclash;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aki on 11/12/15.
 */
public class ProfileListFragment extends Fragment{
    private RecyclerView mProfileRecyclerView;
    private ProfileAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile_list, container, false);

        mProfileRecyclerView = (RecyclerView) view.findViewById(R.id.profile_recycler_view);
        mProfileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        ProfileLib profileLib = ProfileLib.get(getActivity());
        List<Profile> profiles = profileLib.getProfile();

        mAdapter = new ProfileAdapter(profiles);
        mProfileRecyclerView.setAdapter(mAdapter);
    }

    private class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameView;
        private ImageView mPhotoView;

        private Profile mProfile;

        public ProfileHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mNameView = (TextView) itemView.findViewById(R.id.list_item_profile_name_text_view);
            mPhotoView = (ImageView) itemView.findViewById(R.id.list_item_profile_photo_image_view);
        }

        public void bindProfile (Profile profile){
            mProfile = profile;
            //mNameView.setText(mProfile.getMname());
            //mPhotoView.setImageDrawable(null);
        }

        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(), mProfile.getMname() + "clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ProfileAdapter extends RecyclerView.Adapter<ProfileHolder>{
        private List<Profile> mProfiles;

        public ProfileAdapter(List<Profile> profiles){mProfiles = profiles;}

        @Override
        public ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInfalter = LayoutInflater.from(getActivity());
            View view = layoutInfalter.inflate(R.layout.list_item_profile, parent, false);
            return new ProfileHolder(view);
        }

        @Override
        public void onBindViewHolder(ProfileHolder holder, int position){
            Profile profile = mProfiles.get(position);
            holder.bindProfile(profile);
        }

        @Override
        public int getItemCount(){
            return mProfiles.size();
        }
    }
}

