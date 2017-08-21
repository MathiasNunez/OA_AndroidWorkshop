package com.overactiveinc.oarestapi.network;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by slaport on 11/22/16.
 */

public class RestApiTLSSocketFactory extends SSLSocketFactory {

    private SSLContext sc;
    private SSLSocketFactory ssf;

    private static final String TAG = "RestApiTLSSocketFactory";

    public RestApiTLSSocketFactory() {
        try {
            sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, null, null);
            ssf = sc.getSocketFactory();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public Socket createSocket() throws IOException {
        return super.createSocket();
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose)
            throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(s, host, port, autoClose);
        ss.setEnabledProtocols(new String[]{"TLSv1.2"});
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return ssf.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return ssf.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException, UnknownHostException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port, localHost, localPort);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress,
                               int localPort) throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(address, port, localAddress, localPort);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }
}
