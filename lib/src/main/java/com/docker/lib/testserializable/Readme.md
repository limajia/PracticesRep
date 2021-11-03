1.序列化的目的

  （1）.永久的保存对象数据(将对象数据保存在文件当中,或者是磁盘中

  （2）.通过序列化操作将对象数据在网络上进行传输(由于网络传输是以字节流的方式对数据进行传输的.因此序列化的目的是将对象数据转换成字节流的形式)

  （3）.将对象数据在进程之间进行传递(Activity之间传递对象数据时,需要在当前的Activity中对对象数据进行序列化操作.在另一个Activity中需要进行反序列化操作讲数据取出)

  （4）.Java平台允许我们在内存中创建可复用的Java对象，但一般情况下，只有当JVM处于运行时，这些对象才可能存在，即，这些对象的生命周期不会比JVM的生命周期更长（即每个对象都在JVM中）但在现实应用中，就可能要停止JVM运行，但有要保存某些指定的对象，并在将来重新读取被保存的对象。这是Java对象序列化就能够实现该功能。（可选择入数据库、或文件的形式保存）

  （5）.序列化对象的时候只是针对变量进行序列化,不针对方法进行序列化.

  （6）.在Intent之间,基本的数据类型直接进行相关传递即可,但是一旦数据类型比较复杂的时候,就需要进行序列化操作了.





//这里是网络传输序列化的例子 =============start==============
HttpURLConnection httpUrlConn = null;
    InputStream inputStream = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;
    ObjectOutputStream oos = null;
    StringBuffer buffer = new StringBuffer();
    try
    {
      URL url = new URL(requestUrl);
      httpUrlConn = (HttpURLConnection)url.openConnection();
      // 设置content_type=SERIALIZED_OBJECT
      // 如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException
      httpUrlConn.setRequestProperty("Content-Type","application/x-java-serialized-object");
      httpUrlConn.setConnectTimeout(connTimeoutMills);
      httpUrlConn.setReadTimeout(readTimeoutMills);
      // 设置是否向httpUrlConn输出，因为是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false
      httpUrlConn.setDoOutput(true);
      // 设置是否从httpUrlConn读入，默认情况下是true
      httpUrlConn.setDoInput(true);
      // 不使用缓存
      httpUrlConn.setUseCaches(false);

      // 设置请求方式，默认是GET
      httpUrlConn.setRequestMethod("POST");
      httpUrlConn.connect();

      if (serializedObject != null)
      {
        // 此处getOutputStream会隐含的进行connect，即：如同调用上面的connect()方法，
        // 所以在开发中不调用上述的connect()也可以，不过建议最好显式调用
        // write object(impl Serializable) using ObjectOutputStream
        oos = new ObjectOutputStream(httpUrlConn.getOutputStream());
        oos.writeObject(serializedObject);
        oos.flush();
        // outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络，
        // 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。所以这里的close是必须的
        oos.close();
      }
      // 将返回的输入流转换成字符串
      // 无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去
      inputStream = httpUrlConn.getInputStream();//注意，实际发送请求的代码段就在这里
      inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
      bufferedReader = new BufferedReader(inputStreamReader);

      String str = null;
      while ((str = bufferedReader.readLine()) != null)
      {
        buffer.append(str);
      }
    }
    catch (Exception e)
    {
      log.error(requestUrl + " error ", e);
      throw e;
    }
    finally
    {
      try
      {
        IOUtils.closeQuietly(bufferedReader);
        IOUtils.closeQuietly(inputStreamReader);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(oos);
        if (httpUrlConn != null)
        {
          httpUrlConn.disconnect();
        }
      }
      catch (Exception e)
      {
        log.error(e);
      }
    }
    return buffer.toString();
  }
//这里是网络传输序列化的例子 ===============end============