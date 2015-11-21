package com.easein;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;



///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link InfoFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link InfoFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class InfoFragment extends FragmentActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txt_TextoP;
    private TextView txt_UsuarioP;
    private TextView txt_RangoP;

    //private OnFragmentInteractionListener mListener;


    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);
        Intent intent = getIntent();

        txt_TextoP = (TextView) findViewById(R.id.txt_TextoP);
        txt_UsuarioP = (TextView) findViewById(R.id.txt_UsuarioP);
        txt_RangoP = (TextView) findViewById(R.id.txt_RangoP);

        txt_TextoP.setText(intent.getStringExtra("Texto"));
        txt_UsuarioP.setText(intent.getStringExtra("Usuario"));
        txt_RangoP.setText(intent.getStringExtra("Rango"));
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
