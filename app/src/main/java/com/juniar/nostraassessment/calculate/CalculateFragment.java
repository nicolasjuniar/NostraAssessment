package com.juniar.nostraassessment.calculate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.juniar.nostraassessment.R;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class CalculateFragment extends Fragment implements CalculateView {

    EditText etNumber;
    Button btnCalculate;
    TextView tvResult, tvCalculate;
    CalculatePresenter presenter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        etNumber = view.findViewById(R.id.et_number);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        tvResult = view.findViewById(R.id.tv_result);
        tvCalculate = view.findViewById(R.id.tv_calculate);
        presenter=new CalculatePresenter(this);

        RxTextView.textChanges(etNumber)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return charSequence.length() != 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        btnCalculate.setEnabled(aBoolean);
                        if (aBoolean) {
                            btnCalculate.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        } else {
                            btnCalculate.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.calculate(etNumber.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onCalculate(String result, String resultCalculation, int total) {
        tvResult.setText(getString(R.string.result_text, etNumber.getText().toString(), result.substring(0, result.length() - 2)));
        tvCalculate.setText(getString(R.string.calculate_result_text, resultCalculation, String.valueOf(total)));
        tvResult.setVisibility(View.VISIBLE);
        tvCalculate.setVisibility(View.VISIBLE);
    }
}
