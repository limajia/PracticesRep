package com.mlj.practicesrep.bindertest;

import android.os.Parcel;
import android.os.Parcelable;

// activity传递数据 必须序列化 内核态和用户态进行切换 经过了ams进程间通讯
public class Book implements Parcelable {
    private int price;
    private String name;

    public Book() {
    }

    protected Book(Parcel in) {
        price = in.readInt();
        name = in.readString();
    }

    // 接口中的定义的接口 实现的话 通过匿名内部类 实现
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.price);
        dest.writeString(this.name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}

