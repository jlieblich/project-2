package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemDetailFragment extends Fragment {

    public static Fragment newInstance(Bundle bundle) {
        Fragment fragment = new ItemDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView productName = (TextView)view.findViewById(R.id.title);
        TextView productDesc = (TextView)view.findViewById(R.id.description);
        TextView productPrice = (TextView)view.findViewById(R.id.price);
        ImageView productImage = (ImageView)view.findViewById(R.id.product_picture);
    }
}
