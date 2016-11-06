package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mProductKey;

    private OnFragmentInteractionListener mListener;

    public ItemDetailFragment() {
    }

    public static ItemDetailFragment newInstance(String param1) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductKey = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    public void onButtonPressed(String productKey) {
        if (mListener != null) {
            mListener.onFragmentInteraction(productKey);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String productKey);
    }
}
