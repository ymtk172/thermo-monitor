package com.example.demo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import redis.clients.jedis.JedisPoolConfig

@Configuration
@EnableRedisHttpSession
class HttpSessionConfig {
    @Value("\${spring.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.redis.port}")
    private val redisPort = 0

    @Value("\${spring.redis.password}")
    private val redisPassword: String? = null

    @Bean
    fun jedisPoolConfig(): JedisPoolConfig? {
        val poolConfig = JedisPoolConfig()
        poolConfig.setMaxTotal(10)
        poolConfig.setMaxIdle(5)
        poolConfig.setMinIdle(1)
        poolConfig.setTestOnBorrow(true)
        poolConfig.setTestOnReturn(true)
        poolConfig.setTestWhileIdle(true)
        return poolConfig
    }

    // JSONにシリアライズする場合
    //@Bean
    //public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
    //    return new GenericJackson2JsonRedisSerializer();
    //}
    @Bean
    fun connectionFactory(jedisPoolConfig: JedisPoolConfig): JedisConnectionFactory {
        val factory = RedisStandaloneConfiguration()
        factory.hostName = redisHost!!
        factory.port = redisPort
        factory.setPassword(redisPassword!!)
        val clientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(jedisPoolConfig)
                .build()
        return JedisConnectionFactory(factory,clientConfig)
    }
}
