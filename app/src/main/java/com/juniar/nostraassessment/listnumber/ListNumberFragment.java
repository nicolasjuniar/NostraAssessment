package com.juniar.nostraassessment.listnumber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.juniar.nostraassessment.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class ListNumberFragment extends Fragment implements ListNumberView{

    EditText etNumber1, etNumber2;
    Button btnGenerate;
    ListView lvNumber;
    TextView tvListTitle;
    ListNumberAdapter adapter;
    ArrayList<Integer> listNumber;
    ArrayList<String> listFilteredNumber;
    ListNumberPresenter presenter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_number, container, false);
        etNumber1 = view.findViewById(R.id.et_number_1);
        etNumber2 = view.findViewById(R.id.et_number_2);
        btnGenerate = view.findViewById(R.id.btn_generate);
        lvNumber = view.findViewById(R.id.lv_number);
        tvListTitle = view.findViewById(R.id.tv_list_title);

        listNumber = new ArrayList<>();
        listFilteredNumber=new ArrayList<>();
        presenter=new ListNumberPresenter(this);
        adapter = new ListNumberAdapter(getActivity(), listFilteredNumber);
        lvNumber.setAdapter(adapter);

        Observable.combineLatest(
                RxTextView.textChanges(etNumber1)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return etNumber1.length()>0;
                            }
                        }),
                RxTextView.textChanges(etNumber2)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return etNumber2.length()>0;
                    }
                }),
                new BiFunction<Boolean,Boolean,Boolean>(){
                    @Override
                    public Boolean apply(Boolean number1,Boolean number2) throws Exception {
                        return number1&&number2;
                    }
                }
        ).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                btnGenerate.setEnabled(aBoolean);
                if (aBoolean) {
                    btnGenerate.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    btnGenerate.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNumber.clear();
                for (int i = Integer.parseInt(etNumber1.getText().toString()); i <= Integer.parseInt(etNumber2.getText().toString()); i++) {
                    listNumber.add(i);
                }
                presenter.generateListNumber(listNumber);
            }
        });

        return view;
    }

    @Override
    public void onGenerateListNumber(ArrayList<String> listFilteredNumber) {
        this.listFilteredNumber.clear();
        this.listFilteredNumber.addAll(listFilteredNumber);
        tvListTitle.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }
}
