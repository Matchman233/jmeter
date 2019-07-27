package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import test.testclient.DemoClient;

/**
 *
 * Stress test demo
 *
 * Created by lukaiyan on 2019-07-27
 */
@Slf4j
public class StressTestDemo extends AbstractJavaSamplerClient {
    // 可以设置参数
    protected int reqNum;


    // 初始化参数
    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("AK", "", "String", "ak");
        params.addArgument("SK", "", "String", "sk");
        params.addArgument("ReqBatchSize", "5", "Integer", "Number of Requests");
        return params;
    }

    // runTest之前的初始化参数。
    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);

        String ak = context.getParameter("AK");
        String sk = context.getParameter("SK");

        reqNum = Integer.parseInt(context.getParameter("ReqBatchSize"));

        log.info("Finish init. ");
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }

    // 执行测试
    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult result = new SampleResult();

        // 开始执行测试
        result.sampleStart();

        String s = "";
        // 执行测试用例
        try {
            s = DemoClient.sendGet("https://www.baidu.com/s", "ie=UTF-8&wd=baidu");
            if (StringUtils.isEmpty(s)) {
                result.setSuccessful(false);
            } else {
                result.setSuccessful(true);
            }
        } catch (Exception e) {
            result.setSuccessful(false);
        }


        // 结束执行测试
        result.sampleEnd();
        return result;
    }

    // 可以用来测试运行
    public static void main(String[] args) {
        Arguments params = new Arguments();
        params.addArgument("AK", "akakakkaka");
        params.addArgument("SK", "skskskskks");
        params.addArgument("ReqBatchSize", "1");
        JavaSamplerContext arg0 = new JavaSamplerContext(params);
        StressTestDemo test = new StressTestDemo();
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }

}
