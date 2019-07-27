# jmeter压测demo

## 压测
1. 安装jmeter
   1. 下载最新的jmeter(jmeter 5.1.1) http://mirrors.tuna.tsinghua.edu.cn/apache//jmeter/binaries/apache-jmeter-5.1.1.tgz
   2. tar zxvf apache-jmeter-5.1.1.tgz
2. 打包测试代码 mvn package
3. 将待测试的子工程下的target目录下的${sub_project_name}-jar-with-dependencies.jar拷贝到${JMETER_HOME}/lib/ext目录下
4. 修改${JMETER_HOME}/bin/jmeter文件中的堆大小（在159行处："${HEAP:="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m"}"，
可以去除掉-XX:MaxMetaspaceSize=256m，避免加载的类大小超过该限制导致oom)
5. ./${JMETER_HOME}/bin/jemter 打开可视化编辑页面
6. 编写测试脚本jmx文件 参考 https://my.oschina.net/u/1241970/blog/635522
7. nohup ${JMETER_HOME}/bin/jmeter -n -t test.jmx -l result.out -e -o webreport > log 2>&1 &
   - -t 指定jmx测试脚本
   - -l 指定输出结果路径
   - -o 指定web界面的报告输出路径
8. 可以在测试结束后，将webreport文件夹下载到本地，使用浏览器打开，查看可视化的压测结果