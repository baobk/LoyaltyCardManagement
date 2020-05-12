package com.neo.project.LoyaltyCard.jms.provider;

import javax.print.attribute.standard.Destination;
import java.io.Serializable;

public interface IMessageSender {

    public void sendMessage(final Serializable obj);

    public void sendMessage(final String destination,  final Serializable obj);

}
