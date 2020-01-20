package com.example.shan.fragmentsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMain extends Fragment
        {

    private FragmentMainListener mFragmentMainListener;
    private EditText mEditText;
    private Button mButton;



    public interface FragmentMainListener {
        void onDataSent(String input);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Toast.makeText(getActivity(), "fragment", Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mEditText  = view.findViewById(R.id.editTextMain);
        mButton = view.findViewById(R.id.btnUpdateData);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mFragmentMainListener.onDataSent(mEditText.getText().toString());
            }
        });

        return  view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentMainListener) {
            mFragmentMainListener = (FragmentMainListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentMainListener = null;
    }

}
