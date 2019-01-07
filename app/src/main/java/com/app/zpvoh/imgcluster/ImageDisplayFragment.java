package com.app.zpvoh.imgcluster;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String IMAGE = "image_info";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Image image;
    private String mParam2;

    private ImageLoader imageLoader;
    private ArrayList<Comment> comments;

    private OnFragmentInteractionListener mListener;

    public ImageDisplayFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ImageDisplayFragment newInstance(Image img) {
        ImageDisplayFragment fragment = new ImageDisplayFragment();
        Bundle args = new Bundle();
        args.putSerializable(IMAGE, img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image=(Image)getArguments().getSerializable(IMAGE);
        }

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getContext());

        imageLoader=ImageLoader.getInstance();
        imageLoader.init(configuration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_image_display, container, false);
        if(image!=null) {
            comments=Deal.getCommentByImage(image.img_id);
            ImageView imageView = v.findViewById(R.id.displayView);
            TextView nameView = v.findViewById(R.id.img_name);
            TextView timeView = v.findViewById(R.id.img_time);
            ListView commentView = v.findViewById(R.id.comment_view);

            imageLoader.displayImage(Constant.hostname + image.path, imageView);
            nameView.setText(image.name);
            timeView.setText(image.time);

            if(comments!=null){
                commentView.setAdapter(new CommentAdapter(comments));
            }
        }

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

    private class CommentAdapter extends ArrayAdapter<Comment>{
        public CommentAdapter(ArrayList<Comment> comments){
            super(getActivity(), 0,comments);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.comment_item,parent,false);
            }
            TextView nameView=convertView.findViewById(R.id.comment_user_name);
            TextView contentView=convertView.findViewById(R.id.comment_content);
            Comment comment=comments.get(position);

            nameView.setText(comment.username);
            contentView.setText(comment.content);

            return convertView;
        }
    }
}
