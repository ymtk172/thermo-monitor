package com.yamalc.ytmp.thermomonitor.config

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient

@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.yamalc.ytmp.thermomonitor.repositories"])
class DynamoDBConfig {
    @Value("\${amazon.dynamodb.endpoint}")
    private val amazonDynamoDBEndpoint: String? = null

    @Value("\${amazon.credential.profile}")
    private val profile: String? = null
    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        // TODO: fix duplicate message
        val amazonDynamoDB: AmazonDynamoDB = AmazonDynamoDBClient(amazonAWSCredentials())
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint)
        }
        return amazonDynamoDB
    }

    @Bean
    fun amazonAWSCredentials(): AWSCredentials {
        return ProfileCredentialsProvider(profile).credentials
    }
}