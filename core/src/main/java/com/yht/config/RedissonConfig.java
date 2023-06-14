package com.yht.config;//package com.university.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RedissonConfig  extends CachingConfigurerSupport {
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private String port;
//    @Value("${redis.pwd}")
//    private String pwd;
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://"+host+":"+port)
//                .setPassword(pwd);
////        RedissonClient redissonClient = Redisson.create(config);
////        // 获取布隆过滤器
////        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("testBloom");
////        //初始化布隆过滤器：预计元素为1wL,误差率为3%
////        // 总数量100
////        long total = 100L;
////        bloomFilter.tryInit(total,0.03);
////        // 初始化 1W条数据到过滤器中
////        for (int i = 0; i < total; i++) {
////            bloomFilter.add("niceTry" + i);
////        }
//
//        return Redisson.create(config);
//    }
//}
