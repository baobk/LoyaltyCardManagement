package com.neo.project.LoyaltyCard.jms.provider.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neo.project.Entity.User;
import com.neo.project.LoyaltyCard.jms.provider.IMessageReceiver;
import com.neo.project.LoyaltyCard.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.transaction.Transactional;


@Transactional
@Component
public class MessageReceiverImpl implements IMessageReceiver<User> {

    static final Logger LOG = (Logger) LoggerFactory.getLogger(MessageReceiverImpl.class);

    @Autowired private JmsTemplate jmsTemplate;

    @Autowired private MessageConverter messageConverter;

    @Override
    @JmsListener(destination = "${inbound.queue}")
    public User receiveMessage(final Message message) throws JMSException {
        User user = null;
        try {
                String json = (String) messageConverter.fromMessage(message);
                LOG.info("System response received : {}", json);
                user = JsonUtil.convertJsonToBean(json, new TypeReference<User>() {});
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User receiveMessage(String destination) throws JMSException {
        User user = null;
        try {
                Message message = jmsTemplate.receive(destination);
                String json = (String) messageConverter.fromMessage(message);
                user = JsonUtil.convertJsonToBean(json, new TypeReference<User>() {
            });
        }catch (Exception e){
            e.printStackTrace();
            throw  new JMSException(e.getMessage());
        }
        return user;
    }
}
