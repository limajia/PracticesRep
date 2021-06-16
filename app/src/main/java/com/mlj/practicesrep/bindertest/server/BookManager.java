package com.mlj.practicesrep.bindertest.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.mlj.practicesrep.bindertest.Book;

import java.util.List;

// 定义服务端具备的能力
public interface BookManager extends IInterface {

    List<Book> getBooks() throws RemoteException;

    void addBook(Book book) throws RemoteException;
}
