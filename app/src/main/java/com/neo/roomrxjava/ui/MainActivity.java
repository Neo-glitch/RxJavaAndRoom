package com.neo.roomrxjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neo.roomrxjava.Injection;
import com.neo.roomrxjava.R;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mUserName, mAge, mSex;
    private EditText mUserNameInput, mAgeInput, mSexInput;
    private Button mUpdateButton;

    private MainActivityViewModelFactory mViewModelFactory;
    private MainActivityViewModel mViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.userName);
        mAge = findViewById(R.id.userAge);
        mSex = findViewById(R.id.userSex);

        mUserNameInput = findViewById(R.id.userNameInput);
        mAgeInput = findViewById(R.id.userNameAgeInput);
        mSexInput = findViewById(R.id.userNameSexInput);
        mUpdateButton = findViewById(R.id.updateUser);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel.class);

        mUpdateButton.setOnClickListener(v -> updateUserName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // subscribe to emissions and do needed work on subscribe
        Disposable userNameDisposable = mViewModel.getUserName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userName -> mUserName.setText(userName), throwable -> Log.d(TAG, "unable to update userName", throwable));

        Disposable userAgeDisposable = mViewModel.getUserAge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(age -> mAge.setText(age), throwable -> Log.d(TAG, "unable to update userAge", throwable));

        Disposable userSexDisposable = mViewModel.getUserSex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sex -> mSex.setText(sex), throwable -> Log.d(TAG, "unable to update userSex", throwable));

        mDisposable.addAll(userNameDisposable, userAgeDisposable, userSexDisposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void updateUserName() {
        String userName = mUserNameInput.getText().toString();
        String userAge = mAgeInput.getText().toString();
        String userSex = mSexInput.getText().toString();

        mUpdateButton.setEnabled(false);

        // subscribe to update userName and re-enable btn after update
        mDisposable.add(mViewModel.updateUserName(userName, userAge, userSex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mUpdateButton.setEnabled(true),
                        throwable -> Log.d(TAG, "failed to update UserInfo", throwable))
        );
    }
}