package com.example.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class CustomDialog extends Dialog {
    private Context context;
    private CustomDialogClickListener listener;
    private ImageView image_iv;
    private TextView title_tv, text_tv;
    private Button postive_btn, negative_btn;

    private String title_val, text_val, image_val;
    private Drawable image_dra;
    private String imageKind;

    public CustomDialog(@NonNull Context context, CustomDialogClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        image_iv = findViewById(R.id.customDialog__header_image);
        title_tv = findViewById(R.id.customDialog__header_title);
        text_tv = findViewById(R.id.customDialog__body_text);
        negative_btn = findViewById(R.id.customDialog__footer_btn_negative);
        postive_btn = findViewById(R.id.customDialog__footer_btn_positive);

        postive_btn.setOnClickListener(v -> {
            this.listener.onPositiveClick();
            dismiss();
        });

        negative_btn.setOnClickListener(v -> {
            this.listener.onNegativeClick();
            dismiss();
        });

        applyMessage();
        applyTitle();
        applyImage();
    }

    public void setMessage(String text) {
        this.text_val = text;
    }

    public void setTitle(String title) {
        this.title_val = title;
    }

    public void setImage(String image) {
        this.image_val = image;
        imageKind = "string";
    }

    public void setImage(Drawable image) {
        this.image_dra = image;
        imageKind = "drawable";
    }

    public void applyMessage() { this.text_tv.setText(text_val); }

    public void applyTitle() { this.title_tv.setText(title_val); }

    public void applyImage() {
        RequestOptions options = new RequestOptions();
        options = options.transform(new CenterCrop(), new RoundedCorners(16));
        if (imageKind.equals("string"))
        Glide.with(context).load(image_val).apply(options).into(this.image_iv);
        else
        Glide.with(context).load(image_dra).apply(options).into(this.image_iv);
    }
}
