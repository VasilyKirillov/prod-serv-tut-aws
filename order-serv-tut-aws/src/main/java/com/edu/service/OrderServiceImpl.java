package com.edu.service;


import com.edu.model.Cart;
import com.edu.model.CheckoutResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;
    @Value("${aws.sqs.queue.name}")
    private String queueName;

    public OrderServiceImpl(SqsClient sqsClient, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public CheckoutResponse checkout(Cart cart) {

        try {
            String messageBody = objectMapper.writeValueAsString(cart);
            SendMessageResponse sendMessageResponse = sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(getQueueUrl())
                    .messageBody(messageBody)
                    .delaySeconds(10)
                    .build());

            System.out.println("*** message sent: " + sendMessageResponse);
            return new CheckoutResponse("response: " + sendMessageResponse.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new CheckoutResponse("error: " + e.getMessage());
        }

    }

    @Override
    public String receive() {
        // short polling
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(getQueueUrl())
                .waitTimeSeconds(0)  // forces short polling
                .build();
        List<Message> messages = this.sqsClient.receiveMessage(receiveMessageRequest).messages();
        System.out.println("*** received " + messages.size() + " message(s)");
        return messages.stream().map(Message::toString)
                .collect(Collectors.joining());
    }

    @Override
    public String receive2() {
        // long polling and wait for waitTimeSeconds before timed out
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(getQueueUrl())
                .waitTimeSeconds(2 * 60)  // forces long polling
                .build();
        List<Message> messages = this.sqsClient.receiveMessage(receiveMessageRequest).messages();
        System.out.println("*** received " + messages.size() + " message(s)");
        return messages.stream().map(Message::toString)
                .collect(Collectors.joining());
    }

    private String getQueueUrl() {
        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();
        GetQueueUrlResponse queueUrl = sqsClient.getQueueUrl(getQueueRequest);
        System.out.println("*** queueUrl:" + queueUrl);
        return queueUrl.queueUrl();
    }


        public static int counter = 0;
    @Override
    public void createQueue() {
        CreateQueueRequest request = CreateQueueRequest.builder().queueName("MyQueue"+counter++).build();
        CreateQueueResponse response = sqsClient.createQueue(request);
        System.out.println("*** queue created with url: " + response.queueUrl());
    }

    @Override
    public void listQueues() {
        String prefix = "My";

        try {
            ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder().queueNamePrefix(prefix).build();
            ListQueuesResponse listQueuesResponse = sqsClient.listQueues(listQueuesRequest);
            System.out.println("*** list of queue");
            for (String url : listQueuesResponse.queueUrls()) {
                System.out.println(url);
            }

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    @Override
    public void deleteQueue(String queueName) {
        try {

            GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                    .queueName(queueName)
                    .build();

            String queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl();

            DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                    .queueUrl(queueUrl)
                    .build();

            sqsClient.deleteQueue(deleteQueueRequest);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

}
