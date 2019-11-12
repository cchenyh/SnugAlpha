public void LoginByGet() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //我们请求的数据:
//            String data = "account=" + URLEncoder.encode(name, "UTF-8") +
//                    "&password=" + URLEncoder.encode(passwd, "UTF-8");

                    //get请求的url
                    URL url=new URL( "https://api.shisanshui.rtxux.xyz/rank");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //设置请求方式,请求超时信息
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(30000);
                    conn.setConnectTimeout(30000);
//            //设置运行输入,输出:
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            //Post方式不能缓存,需手动设置为false
//            conn.setUseCaches(false);
//
//            //设置请求的头信息
//            conn.setRequestProperty("staffid",StaffId );  //当前请求用户StaffId
//            conn.setRequestProperty("timestamp", ApiHelper.GetTimeStamp()); //发起请求时的时间戳（单位：毫秒）
//            conn.setRequestProperty("nonce", ApiHelper.GetRandom()); //发起请求时的随机数

                    //开启连接
                    conn.connect();
                    InputStream inputStream=null;
                    BufferedReader reader=null;
                    //如果应答码为200的时候，表示成功的请求带了，这里的HttpURLConnection.HTTP_OK就是200
                    if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                        //获得连接的输入流
                        inputStream=conn.getInputStream();
                        //转换成一个加强型的buffered流
                        reader=new BufferedReader(new InputStreamReader(inputStream));
                        //把读到的内容赋值给result
                        String result = reader.readLine();

                        msg = result;
                        Log.d("Ranking", "Result:" + result);
//                        JSONObject json_test = new JSONObject(result);
                        //打印json 数据
//                        Log.e("json", json_test.get("player_id").toString());
//                        Log.e("json", json_test.get("score").toString());
//                        Log.e("json", json_test.get("name").toString());

                    }
                    //关闭流和连接
                    reader.close();
                    inputStream.close();
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
	
	
	
	
	private void registerPost() {
        //请求
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 0.相信证书

                    // 1. 获取访问地址URL
                    URL url = new URL("https://api.shisanshui.rtxux.xyz/auth/register");
                    // 2. 创建HttpURLConnection对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // 3. 设置请求参数等
                    // 请求方式
                    connection.setRequestMethod("POST");
                    // 超时时间
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    // 设置是否输出
                    connection.setDoOutput(true);
                    // 设置是否读入
                    connection.setDoInput(true);
                    // 设置是否使用缓存
                    connection.setUseCaches(false);
                    // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
                    connection.setInstanceFollowRedirects(true);
                    // 设置使用标准编码格式编码
                    connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//json格式
                    // 连接
                    connection.connect();
                    // 4. 处理输入输出
                    // 写入参数到请求中

                    //Content-Type：application/json;charset=UTF-8 对应json格式参数数据
                    String params = "{" +
                            "\"username\":\"" + login_name.getText() + "\"" + "," +
                            "\"password\":\"" + login_password.getText() + "\"" +
                            "}";


                    Log.d("BaoFuPay", "RiskManagerParams:" + params);

                    OutputStream out = connection.getOutputStream();
                    out.write(params.getBytes());
                    out.flush();
                    out.close();
                    // 从连接中读取响应信息
                    String msg = "";
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            msg += line + "\n";
                        }
                        Log.d("BaoFuPay", "Result:" + msg);
                        reader.close();
                    }
                    // 5. 断开连接
                    connection.disconnect();

                    //loading

                    // 处理结果
                    Log.d("BaoFuPay", "RiskManagerResult:" + msg);
                } catch (Exception e) {
                    //loading
                    Log.e("BaoFuPay", "RiskManager：" + e);
                }
            }
        }).start();


    }