package com.tradeRepublic.candleStick.candleStick;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class CandleStickApplication {

	public static void main(String[] args) {

		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:postgresql://localhost:5435/postgres", "postgres", "postgresFirst")
				.load();
		flyway.repair();

		SpringApplication.run(CandleStickApplication.class, args);


		/*QuoteStream quoteStream = new QuoteStream();
		quoteStream.getInstrumentStream();*/


/*
		InstrumentStream instrumentStream = new InstrumentStream();
		instrumentStream.getInstrumentStream();*/

		/*WebSocketClient mWebSocketClient;

		URI uri;
		try {
			uri = new URI("ws://localhost:8200/instruments");
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
                *//*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.edittext_chatbox);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });*//*
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
		mWebSocketClient.connect();*/
	}


	//private static final Logger logger = Logger.getLogger(CandleStickApplication.class.getName());

	/*InstrumentServiceImpl instrumentServiceImpl ;

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
	/*private static final String INSTRUMENTS_ENDPOINT = "ws://localhost:8032/instruments";
	private static final String QUOTES_ENDPOINT = "ws://localhost:8032/quotes";*/

	//private final Map<String, Instrument> instruments = new ConcurrentHashMap<>();


/*

	public CandleStickApplication() {
		connectToInstrumentsStream(); // for each event call process instrument to save in db
		connectToQuotesStream(); // // for each event call process quote to save in db
		scheduleCandlestickGeneration();
	}

	private void scheduleCandlestickGeneration() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this::generateCandlesticks, 0, 60, TimeUnit.SECONDS);
	}

	private void generateCandlesticks() {
		long currentTimestamp = System.currentTimeMillis();
		long previousMinute = currentTimestamp - 60_000;
		for (Instrument instrument : instruments.values()) {
			instrument.generateCandlestick(previousMinute, currentTimestamp);
		}
	}

	@GetMapping("/candlesticks")
	public ResponseEntity<?> getCandlesticks(@RequestParam String isin) {
		Instrument instrument = instruments.get(isin);
		if (instrument == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instrument not found");
		}
		return ResponseEntity.ok(instrument.getCandlesticks());
	}


*/

}
