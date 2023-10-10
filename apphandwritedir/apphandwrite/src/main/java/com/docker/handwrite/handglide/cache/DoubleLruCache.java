package com.docker.handwrite.handglide.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.docker.handwrite.handglide.request.BitmapRequest;

public class DoubleLruCache implements BitmapCache {

    private MemoryLruCache memoryLruCache;
    private DiskBitmapCache diskBitmapCache;

    public DoubleLruCache(Context context) {
        memoryLruCache = MemoryLruCache.getInstance();
        diskBitmapCache = DiskBitmapCache.getInstance(context);
    }

    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        memoryLruCache.put(bitmapRequest, bitmap);
        diskBitmapCache.put(bitmapRequest, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest bitmapRequest) {
        Bitmap bitmap = memoryLruCache.get(bitmapRequest);
        if (bitmap == null) {
            bitmap = diskBitmapCache.get(bitmapRequest);
            if (bitmap != null) {
                memoryLruCache.put(bitmapRequest, bitmap);
            }
        }
        return bitmap;
    }

    @Override
    public void remove(BitmapRequest bitmapRequest) {
        memoryLruCache.remove(bitmapRequest);
        diskBitmapCache.remove(bitmapRequest);
    }
}
