package com.mlj.customviews.userEnter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mlj.customviews.R;
import com.mlj.customviews.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class UserEnterView extends RelativeLayout {
    private String mDefaultString;
    private int mViewTextColor = Color.RED;
    private float mViewTextDimension = 0;
    private Drawable mExampleDrawable;
    private float mViewCoverDimension = 0;

    private UserIcons mUserIcons;
    private TextView mUserNick;
    private Button mZanIcon;
    private TextView mZanCount;
    private ArrayList<User> mUsers = new ArrayList<>();

    public UserEnterView(Context context) {
        super(context);
        init(null, 0);
    }

    public UserEnterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public UserEnterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.UserEnterView, defStyle, 0);
        mDefaultString = a.getString(R.styleable.UserEnterView_defaultString);
        mViewTextColor = a.getColor(R.styleable.UserEnterView_viewTextColor, mViewTextColor);
        mViewTextDimension = a.getDimension(R.styleable.UserEnterView_viewTextDimension, mViewTextDimension);
        mViewCoverDimension = a.getDimension(R.styleable.UserEnterView_viewTextDimension, mViewTextDimension);
        a.recycle();
        //
        LayoutInflater.from(getContext()).inflate(R.layout.view_userenter_layout, this);
        mUserIcons = findViewById(R.id.user_icons);
        mUserNick = findViewById(R.id.user_nick);
        mZanIcon = findViewById(R.id.zan_icon);
        mZanCount = findViewById(R.id.zan_count);
        //
    }

    public void updateUsers(ArrayList<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        SortUtils.sortUserByCreateTime(mUsers);
        for (int i = 0; (i < mUsers.size()) && (i < 3); i++) {
            CircleImageView circleImageView = new CircleImageView(getContext());
            circleImageView.setImageResource(mUsers.get(i).getAvatarId());
            mUserIcons.addView(circleImageView, 0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    public void myZan(User mySelf) {
        mUsers.add(mySelf);
        SortUtils.sortUserByCreateTime(mUsers);
        ImageView circleImageView = new ImageView(getContext());
        circleImageView.setImageResource(mySelf.getAvatarId());
        mUserIcons.addView(circleImageView, 0, new LinearLayout.LayoutParams(150, 150));
        circleImageView.bringToFront();
    }

    public void delete() {
        if (!mUsers.isEmpty()) {
            mUsers.remove(new Random().nextInt(mUsers.size()));
            SortUtils.sortUserByCreateTime(mUsers);
            requestLayout();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mUsers.isEmpty()) {

        } else {
            int size = mUsers.size();
            if (size > 1) {
                // 多个头像
            } else {
                // 一个头像
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        /*if (mUsers.isEmpty()) {
            mUserNick.setText(mDefaultString);
            mUserIcons.setVisibility(GONE);
        } else {
            mUserIcons.setVisibility(VISIBLE);
            User lastUser = mUsers.get(0);
            int userCount = mUsers.size();
            if (userCount > 1) {
                mUserNick.setText(lastUser.getNick() + "等" + userCount + "位好友赞过");
            } else {
                mUserNick.setText(lastUser.getNick() + "赞过");
            }
        }
        int childCount = mUserIcons.getChildCount();
        for (int i = 0; i < childCount; i++) {

        }*/
    }
}
