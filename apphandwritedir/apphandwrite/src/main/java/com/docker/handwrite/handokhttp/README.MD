该例子么有处理 连接超时、读取和写入超时。

// 创建 OkHttpClient.Builder 对象
OkHttpClient.Builder builder = new OkHttpClient.Builder();
// 设置连接超时时间（单位：毫秒）
builder.connectTimeout(10, TimeUnit.SECONDS);
// 设置读取超时时间（单位：毫秒）
builder.readTimeout(10, TimeUnit.SECONDS);
// 设置写入超时时间（单位：毫秒）
builder.writeTimeout(10, TimeUnit.SECONDS);
// 创建 OkHttpClient 对象
OkHttpClient okHttpClient = builder.build();


okhttp中读取超时的源码是怎样的？
//在OkHttp中，读取超时的相关代码位于okio库中的Timeout类。OkHttp使用Timeout类来管理连接超时、读取超时和写入超时。


Socket 如何判断连接超时、读取超时和写入超时
//在Java中，Socket类本身并不直接提供连接超时、读取超时和写入超时的设置，而是通过Socket的底层实现SocketImpl或SocketChannel来进行控制。
//在实际开发中，一般会使用Socket的setSoTimeout方法来设置超时，该方法用于设置读取操作的超时时间。

// // 创建Socket对象
//Socket socket = new Socket();
//
//  // 设置连接超时时间为5秒
//  socket.connect(new InetSocketAddress("example.com", 80), 5000); //会阻塞当前进程。可以使用Java NIO（New I/O）提供的SocketChannel来创建异步的Socket
//
// // 设置读取超时时间为10秒
//  socket.setSoTimeout(10000);
//
//   // 进行读取或写入操作...
//
//    // 关闭Socket
//    socket.close();
//对于写入超时，通常需要在具体的操作中处理。例如，如果你使用Socket的OutputStream进行写入操作，你可以使用OutputStream的write方法，并在写入前设置一个超时。这个超时时间实际上是通过Socket的底层实现来控制的。
//需要注意的是，Socket的底层实现可能因操作系统和环境的不同而有所差异。在Java NIO（New I/O）中，也可以使用SocketChannel来进行非阻塞的网络操作，并通过SocketChannel的configureBlocking方法、SocketChannel.socket().setSoTimeout方法来设置超时。


//"Socket读取超时"通常指的是在进行读取数据的操作时，如果在指定的时间内没有从连接的另一端接收到数据，就会触发读取超时异常。


//okio 是一个流操作类，bytebuffer
//socket   socksSocket [socks是v4 v5的sockets简称]


//可以使用DatagramSocket类来创建基于UDP的Socket


//客户端
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) {
        try {
            // 创建DatagramSocket
            DatagramSocket socket = new DatagramSocket();

            // 要发送的数据
            String message = "Hello, UDP Server!";

            // 将数据转换为字节数组
            byte[] sendData = message.getBytes();

            // 服务器地址和端口号
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            // 创建DatagramPacket用于发送数据
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort); ！！！！！、每个数据包都会带

            // 发送数据
            socket.send(sendPacket);

            System.out.println("Message sent to server: " + message);

            // 关闭Socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//服务端

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String[] args) {
        try {
            // 创建DatagramSocket，指定端口号
            DatagramSocket socket = new DatagramSocket(9876);

            // 创建字节数组用于接收数据
            byte[] receiveData = new byte[1024];

            while (true) {
                // 创建DatagramPacket用于接收数据
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据
                socket.receive(receivePacket);

                // 从DatagramPacket中获取数据
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // 输出接收到的数据
                System.out.println("Received message: " + receivedMessage);

                // 如果需要，可以在此处进行响应操作

                // 清空接收缓冲区
                receiveData = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

