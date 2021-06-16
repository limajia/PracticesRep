package com.mlj.practicesrep.bindertest.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mlj.practicesrep.bindertest.Book;

import java.util.List;

// 定义binder对象  ibinder说明对象具备可传递能力
// Stub 跨其他应用的时候 应该是对用户提供stub实现，具体的BookManager实现在service中实现 且隐藏
public abstract class Stub extends Binder implements BookManager {

    private static final String DESCRIPTOR = "com.mlj.practicesrep.bindertest.server.BookManager";

    public static final int TRANSACTION_getBooks = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public Stub() {
        //将特定接口与 Binder 相关联的便捷方法。
        // 调用后，queryLocalInterface() 会为您实现，在请求相应的描述符时返回给定的所有者 IInterface。
        attachInterface(this, DESCRIPTOR);
    }

    /**
     * @param binder 内核传递出的binder对象
     */
    public static BookManager asInterface(IBinder binder) {
        if (binder == null) {
            return null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if (iin instanceof BookManager) {
            return (BookManager) iin; // 同一个进程 直接调用了server中的对象
        }
        return new Proxy(binder);//不同进程
    }

    @Override
    public IBinder asBinder() {
        return this;
    }


    // 只有跨进程 才会走到这里 所有跨进程才需要考虑 序列化的问题 同一个进程的binder 则不会走到这里
    // 可以不用考虑序列话的问题  AIDL就是定义提供哪些功能的工具 来自动生成我们需要的这些代码。
    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSACTION_getBooks:
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBooks(); // getBooks等接口 需要子类来具体实现
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;

            case TRANSACTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book book = null;
                if (data.readInt() != 0) {
                    book = Book.CREATOR.createFromParcel(data);
                }
                this.addBook(book);
                reply.writeNoException();
                return true;

        }
        return super.onTransact(code, data, reply, flags);
    }
}
