package com.tradeRepublic.candleStick.candleStick.config;


import java.net.URI;
import java.net.URISyntaxException;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeRepublic.candleStick.candleStick.entity.InstrumentEvent;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

import com.tradeRepublic.candleStick.candleStick.service.InstrumentServiceImpl;


@Component
public class InstrumentStream {

    private final InstrumentServiceImpl instrumentServiceImpl ;

    @Autowired
    public InstrumentStream(InstrumentServiceImpl instrumentServiceImpl) {
        this.instrumentServiceImpl = instrumentServiceImpl;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void getInstrumentStream(){
        WebSocketClient mWebSocketClient;

        URI uri;
        try {
            uri = new URI("ws://localhost:8032/instruments");
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
               // System.out.println("Received raw message: " + message);
                try {
                    //System.out.println("Received message I1 ");
                    InstrumentEvent instrumentEvent = objectMapper.readValue(message, InstrumentEvent.class);
                    //System.out.println("Received message I2 ");
                    instrumentServiceImpl.processInstrumentEvent(instrumentEvent);
                } catch (Exception e) {
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





   /* @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received raw message: " + message.getPayload());
        try {
            System.out.println("Received message 1 ");
            InstrumentEvent instrumentEvent = objectMapper.readValue(message.getPayload(), InstrumentEvent.class);
            System.out.println("Received message 2 ");
            instrumentServiceImpl.processInstrumentEvent(instrumentEvent);
        } catch (Exception e) {
            System.out.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
        // Handle incoming messages here
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected to server");
        // Send a message after connection is established if necessary
        //session.sendMessage(new TextMessage("Hello Server"));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed. Status: " + status);
    }
*/

   /* InstrumentServiceImpl instrumentServiceImpl ;

    @Bean
    public WebSocketStompClient stompClient() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    @Bean
    public StompSessionHandler sessionHandler() {
        return new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(org.springframework.messaging.simp.stomp.StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Connected to WebSocket server.");
                // Subscribe to the desired destination
                session.subscribe("/instruments", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return String.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        InstrumentEvent instrumentEvent = (InstrumentEvent) payload;
                        instrumentServiceImpl.processInstrumentEvent(instrumentEvent);
                        System.out.println("Received message: " + payload);

                    }
                });
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(WebSocketStompClient stompClient, StompSessionHandler sessionHandler) {
        return args -> {
            try {
                stompClient.connect("ws://localhost:8032/instruments", sessionHandler).get(5, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        };
    }
*/
    /*private final Logger logger = LoggerFactory.getLogger(getClass());
    private final URI uri;
    private static final String INSTRUMENTS_ENDPOINT = "ws://localhost:8032/instruments";

    private Websocket ws;

    public InstrumentStream(String uriString) throws URISyntaxException {
        uri = URI.create(INSTRUMENTS_ENDPOINT);
    }

    public void connect(EventConsumer<InstrumentEvent> onEvent) {
        ws = WebsocketClient.nonBlocking(uri, (req, resp) -> logger.info("Connected instrument stream"));

        ws.onMessage(message -> {
            try {
                InstrumentEvent event = new ObjectMapper().readValue(message.body.stream(), InstrumentEvent.class);
                onEvent.accept(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ws.onClose((code, message) -> {
            logger.info("Disconnected instrument stream: {}; {}", code, message);
            try {
                Thread.sleep(5000L);
                logger.info("Attempting reconnect for instrument stream");
                connect(onEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ws.onError(error -> logger.info("Exception in instrument stream: {}", error));
    }*/
}
