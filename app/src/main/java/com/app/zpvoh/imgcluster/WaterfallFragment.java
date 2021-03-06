package com.app.zpvoh.imgcluster;

import android.content.Context;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WaterfallFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WaterfallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterfallFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LIST="imgList";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GridView mGridView;
    ArrayList<Image> mItems;
    ImageLoader imageLoader;

    private OnFragmentInteractionListener mListener;

    public WaterfallFragment(){

    }


    /*public static WaterfallFragment newInstance(String param1, String param2) {
        WaterfallFragment fragment = new WaterfallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public static WaterfallFragment newInstance(ArrayList<Image> mItems){
        WaterfallFragment waterfallFragment=new WaterfallFragment();
        Bundle args=new Bundle();
        args.putSerializable(LIST, mItems);
        waterfallFragment.setArguments(args);
        return waterfallFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItems=(ArrayList<Image>)getArguments().getSerializable(LIST);
        }

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getContext());

        imageLoader=ImageLoader.getInstance();
        imageLoader.init(configuration);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_waterfall, container, false);
        mGridView=v.findViewById(R.id.grid);
        setupAdapter();

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setupAdapter(){
        if(getActivity()==null||mGridView==null)
            return;

        if(mItems!=null){
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        }else{
            mGridView.setAdapter(null);
        }
    }

    private class GalleryItemAdapter extends ArrayAdapter<Image>{
        public GalleryItemAdapter(ArrayList<Image> items){
            super(getActivity(), 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.gallery_item,parent,false);
            }

            ImageView imageView=convertView.findViewById(R.id.photoItem);
            Image image=mItems.get(position);
            //imageView.setImageResource(R.drawable.anastasia);
            imageLoader.displayImage(image.path, imageView);

            imageView.setOnClickListener(view -> {
                ImageDisplayFragment displayFragment=ImageDisplayFragment.newInstance(image);
                getFragmentManager().beginTransaction().replace(R.id.contentFragment, displayFragment).commit();
            });

            return convertView;
        }
    }
}
