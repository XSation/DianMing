package com.zyr.teacher.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.zyr.base.BaseActivity;
import com.zyr.bean.Teacher;
import com.zyr.teacher.R;
import com.zyr.teacher.db.Dao;
import com.zyr.util.RxSchedulerHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by X.Sation on 2017/5/3.
 */

public class TeacherLoginActivity extends BaseActivity {

    private Button addTeacher;
    private RecyclerView teacherList;
    private Dao dao;

    @Override
    protected void setLayout() {
        setContentView(R.layout.layout_login);
    }

    @Override
    protected void findViews() {
        addTeacher = (Button) findViewById(R.id.btn_add);
        teacherList = (RecyclerView) findViewById(R.id.rv_teacher_list);

    }

    @Override
    protected void setupViews(Bundle bundle) {

    }

    @Override
    protected void setListener() {
        addTeacher.setOnClickListener(v -> {
            toActivity(TeacherRegisterActivity.class);
        });
    }

    @Override
    protected void fetchData() {
        dao = new Dao(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllTeacher();
    }

    /**
     * 获取所有的老师
     */
    private void getAllTeacher() {

        Observable
                .create((Observable.OnSubscribe<List<Teacher>>) subscriber -> {
                    List<Teacher> teachers = dao.queryAllTeacher();
                    subscriber.onNext(teachers);
                })
                .compose(RxSchedulerHelper.io_main())
                .subscribe(teachers -> Log.e(TAG, "getAllTeacher" + teachers));
//        Observable<List<Teacher>>.create(subscriber -> {
//                    List<Teacher> teachers = dao.queryAllTeacher();
//                    subscriber.onNext(teachers);
//                })
//                .compose(RxSchedulerHelper.io_main())
//                .subscribe(o -> {
//
//                });

    }
}
