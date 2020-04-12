module SGServer {
	exports server.network;
	exports server;
	exports server.network.packet;
	exports server.user;
	exports server.core;
	exports server.menu;
	exports server.network.packet.encoder;
	exports server.utils;
	exports server.network.packet.decoder;

	requires java.desktop;
	requires netty;
}