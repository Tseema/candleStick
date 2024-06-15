package com.tradeRepublic.candleStick.candleStick.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tradeRepublic.candleStick.candleStick.entity.QuoteEvent;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import com.tradeRepublic.candleStick.candleStick.service.QuoteServiceImpl;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class QuoteStream {

    private final QuoteServiceImpl quoteServiceImpl ;

    @Autowired
    public QuoteStream(QuoteServiceImpl quoteServiceImpl) {
        this.quoteServiceImpl = quoteServiceImpl;
    }


    public void getQuoteStream(){
        WebSocketClient mWebSocketClient;
        ObjectMapper objectMapper = new ObjectMapper();

        URI uri;
        try {
            uri = new URI("ws://localhost:8032/quotes");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("Websocket Opened");
                //mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + ProjectInfoProperties.Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                System.out.println(s);
                //System.out.println("Received raw message: " + message);
                try {
                    //System.out.println("Received message Q1 ");
                    QuoteEvent quoteEvent = objectMapper.readValue(message, QuoteEvent.class);
                    //System.out.println("Received message Q2 ");
                    quoteServiceImpl.processQuoteEvent(quoteEvent);
                }
                catch (JsonProcessingException e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    System.err.println("Received message: " + message);
                }
                catch (Exception e) {
                    System.out.println("Error processing message: " + e.getMessage());
                    e.printStackTrace();
                }
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.edittext_chatbox);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });*/
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("Websocket Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("Websocket Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
