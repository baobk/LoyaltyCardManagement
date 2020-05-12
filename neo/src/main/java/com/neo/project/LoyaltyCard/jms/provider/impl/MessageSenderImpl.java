package com.neo.project.LoyaltyCard.jms.provider.impl;

import com.neo.project.Entity.User;
import com.neo.project.LoyaltyCard.jms.provider.IMessageSender;
import com.neo.project.LoyaltyCard.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.transaction.Transactional;
import java.io.Serializable;


@Component
public class MessageSenderImpl implements IMessageSender {

    @Autowired private JmsTemplate jmsTemplate;

    static final Logger LOG = (Logger) LoggerFactory.getLogger(MessageSenderImpl.class);

    @Override
    @Transactional
    public void sendMessage(final Serializable obj) {
       final String json = JsonUtil.convertBeanToJson((User)obj);

        jmsTemplate.send(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(json);
                LOG.info("System : send : {}",json);
                return objectMessage;
            }
        });
    }

    @Override
    @Transactional
    public void sendMessage(final String destination, final Serializable obj) {
        final String json = JsonUtil.convertBeanToJson((User)obj);

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(json);
                LOG.info("System : send : {}",json);
                return objectMessage;
            }
        });
    }
}
