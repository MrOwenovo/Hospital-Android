package com.example.myapplication.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.activity.OnSpecialMessageClickListener;
import com.example.myapplication.R;
import com.example.myapplication.entity.ChatMessage;

import java.util.ArrayList;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private OnSpecialMessageClickListener specialMessageClickListener;
    TextView textView;
    public ChatMessageAdapter(Context context, ArrayList<ChatMessage> messages, OnSpecialMessageClickListener listener) {
        super(context, 0, messages);
        this.specialMessageClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = getItem(position);
        // 每次都根据消息类型选择不同的布局
        int layout = message.isBot() ?
                (message.isSpecialMessage() ? R.layout.special_bot_message : R.layout.bot_message) :
                R.layout.user_message;
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);

        if (message.isSpecialMessage()) {
            Button specialButton = convertView.findViewById(R.id.special_button);
            specialButton.setVisibility(View.VISIBLE); // 确保按钮是可见的
            specialButton.setOnClickListener(v -> {
                if (specialMessageClickListener != null) {
                    specialMessageClickListener.onSpecialMessageClick();
                }
            });
        } else {
            // 处理常规消息
            textView = convertView.findViewById(
                    message.isBot() ? R.id.tv_bot_message : R.id.tv_user_message);
            textView.setText(message.getContent());
        }

        // 如果该消息未播放过动画，播放动画
        if (!message.isAnimated()) {
            // 获取头像视图引用
            ImageView imageView = convertView.findViewById(
                    message.isBot() ? R.id.iv_bot : R.id.iv_user);

            // 为头像设置动画
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0f, 1f);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);

            // 为气泡设置动画
            ObjectAnimator scaleXBubble = ObjectAnimator.ofFloat(textView, "scaleX", 0f, 1f);
            ObjectAnimator scaleYBubble = ObjectAnimator.ofFloat(textView, "scaleY", 0f, 1f);
            ObjectAnimator alphaBubble = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);

            AnimatorSet avatarSet = new AnimatorSet();
            avatarSet.playTogether(scaleX, scaleY, alpha);

            AnimatorSet bubbleSet = new AnimatorSet();
            bubbleSet.playTogether(scaleXBubble, scaleYBubble, alphaBubble);

            AnimatorSet set = new AnimatorSet();
            set.playSequentially(avatarSet, bubbleSet);
            set.setDuration(300); // 设置动画时长
            set.start();

            // 标记该消息动画已经播放
            message.setAnimated(true);
        }

        return convertView;
    }



}
