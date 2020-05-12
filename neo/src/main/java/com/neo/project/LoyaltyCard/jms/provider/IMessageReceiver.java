package com.neo.project.LoyaltyCard.jms.provider;

import javax.jms.JMSException;
import javax.jms.Message;

public interface IMessageReceiver<T> {

    public <T> T receiveMessage(final Message message) throws JMSException;

    public <T> T receiveMessage(final String destination) throws JMSException;

}
