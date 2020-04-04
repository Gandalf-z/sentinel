package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.controller.DegradeController;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Properties;

/**
 * nacos持久化配置以及各个规则类型序列化方式
 * @author lansent-zpj
 * @date 2020/3/11 13:57
 */
@Configuration
public class NacosConfig {

    @Autowired
    private Environment evn;
    private final Logger logger = LoggerFactory.getLogger(DegradeController.class);

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        try{
            properties.put(PropertyKeyConst.SERVER_ADDR, evn.getProperty(PropertyKeyConst.SERVER_ADDR));
            properties.put(PropertyKeyConst.NAMESPACE, evn.getProperty(PropertyKeyConst.NAMESPACE));
        }catch (NullPointerException e){
            logger.error("请指定serverAddr与namespace地址",e);
            System.exit(0);
        }
        return ConfigFactory.createConfigService(properties);
    }

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, FlowRuleEntity.class);
    }

    /**
     * degradeRule
     *
     * @return
     */
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, DegradeRuleEntity.class);
    }

    /**
     * systemRule
     *
     * @return
     */
    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, SystemRuleEntity.class);
    }

    /**
     * paramFlowRule
     *
     * @return
     */
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramFlowRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramFlowRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, ParamFlowRuleEntity.class);
    }

    /**
     * authorityRule
     *
     * @return
     */
    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<AuthorityRuleEntity>> authorityRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, AuthorityRuleEntity.class);
    }

    /**
     * GatewayFlowRule
     *
     * @return
     */
    @Bean
    public Converter<List<GatewayFlowRuleEntity>, String> gatewayRuleEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<GatewayFlowRuleEntity>> gatewayRuleEntityDecoder() {
        return s -> JSONObject.parseArray(s, GatewayFlowRuleEntity.class);
    }
    /**
     * GatewayApiDefinition
     *
     * @return
     */
    @Bean
    public Converter<List<ApiDefinitionEntity>, String> apiDefinitionEntityEncoder() {
        return s ->JSONObject.toJSONString(s,true);
    }

    @Bean
    public Converter<String, List<ApiDefinitionEntity>> apiDefinitionEntityDecoder() {
        return s -> JSONObject.parseArray(s, ApiDefinitionEntity.class);
    }
}
