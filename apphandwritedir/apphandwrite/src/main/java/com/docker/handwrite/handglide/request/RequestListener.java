package com.docker.handwrite.handglide.request;

import android.graphics.Bitmap;

public interface RequestListener {
    boolean onSuccess(Bitmap bitmap);

    boolean onFaile();
}
