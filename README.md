# jmeter压测demo

## 压测
1. 安装jmeter
   1. 下载最新的jmeter(jmeter 5.1.1) http://mirrors.tuna.tsinghua.edu.cn/apache//jmeter/binaries/apache-jmeter-5.1.1.tgz
   2. tar zxvf apache-jmeter-5.1.1.tgz
2. 打包测试代码 mvn package
3. 将待测试的子工程下的target目录下的${sub_project_name}-jar-with-dependencies.jar拷贝到${JMETER_HOME}/lib/ext目录下
4. 修改${JMETER_HOME}/bin/jmeter文件中的堆大小（在159行处："${HEAP:="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m"}"，
可以去除掉-XX:MaxMetaspaceSize=256m，避免加载的类大小超过该限制导致oom)

**GUI模式**
**不要用这种模式压测，仅用于调试**
这里介绍调试流程。
![图片](http://agroup-bos.cdn.bcebos.com/5b8b827b75f31797be0d8ce33754933bbc328a99)

1. ./${JMETER_HOME}/bin/jemter 打开可视化编辑页面
2.	设置 线程数，循环次数。
![图片](http://agroup-bos.cdn.bcebos.com/f664634bc19e135e7cf72e38b859ab9ea34487cb)
![图片](http://agroup-bos.cdn.bcebos.com/0dbf3aad4ba1cfb539c4c86891d2de17ced66923)
3.	选择压测脚本

![图片](http://agroup-bos.cdn.bcebos.com/bebce6ad699bca18235e9beb8ab632824b8ec642)
4. 初始化参数

![图片](http://agroup-bos.cdn.bcebos.com/4146f3d1f4f89d9fcdfeafd478e775084d01df6b)
5.其他参数。例如生成聚合报告

![图片](http://agroup-bos.cdn.bcebos.com/2e631bd70dc72a5de4e01c753fef2376a0daa1ee)
6. 点击保存按钮，生成测试计划。**请选择英文。中文模式下可能会导致执行计划出错**。然后点击开始按钮。开始执行测试计划。
![图片](http://agroup-bos.cdn.bcebos.com/452ea37a813278c0568f0f63f3fea7902e36ccec)

**Non GUI模式**
1. 编写测试脚本jmx文件 参考 https://my.oschina.net/u/1241970/blog/635522。如有疑问，可以查看**GUI模式**下生成的执行计划。
7. nohup ${JMETER_HOME}/bin/jmeter -n -t test.jmx -l result.out -e -o webreport > log 2>&1 &
   - -t 指定jmx测试脚本
   - -l 指定输出结果路径
   - -o 指定web界面的报告输出路径
8. 可以在测试结束后，将webreport文件夹下载到本地，使用浏览器打开，查看可视化的压测结果。



**其他**
1.如果需要预先处理数据，然后执行计划。请google jmeter前置处理器。