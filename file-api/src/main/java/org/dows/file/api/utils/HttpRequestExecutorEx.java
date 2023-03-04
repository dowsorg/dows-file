package org.dows.file.api.utils;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.Args;
import org.dows.file.api.model.SecretInfo;

import java.io.IOException;
import java.net.Socket;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/3/2022
 */
public class HttpRequestExecutorEx extends HttpRequestExecutor {

    public static final String HTTP_HOSTADDRESS = "http.hostAddress";

    private SecretInfo secretInfo;

    public HttpRequestExecutorEx() {

    }

    public HttpRequestExecutorEx(SecretInfo secretInfo) {
        this.secretInfo = secretInfo;
    }

    private static void closeConnection(final HttpClientConnection conn) {
        try {
            conn.close();
        } catch (final IOException ignore) {
        }
    }

    @Override
    public HttpResponse execute(
            final HttpRequest request,
            final HttpClientConnection conn,
            final HttpContext context) throws IOException, HttpException {
        Args.notNull(request, "HTTP request");
        Args.notNull(conn, "Client connection");
        Args.notNull(context, "HTTP context");
        try {
            HttpResponse response = doSendRequest(request, conn, context);
            if (response == null) {
                response = doReceiveResponse(request, conn, context);
            }
            processRequestIp(conn, context);
            return response;
        } catch (final IOException ex) {
            processRequestIp(conn, context);
            closeConnection(conn);
            throw ex;
        } catch (final HttpException ex) {
            processRequestIp(conn, context);
            closeConnection(conn);
            throw ex;
        } catch (final RuntimeException ex) {
            processRequestIp(conn, context);
            closeConnection(conn);
            throw ex;
        }
    }

    private void processRequestIp(HttpClientConnection conn, HttpContext context) {
        if (conn instanceof ManagedHttpClientConnection) {
            ManagedHttpClientConnection managedConn = (ManagedHttpClientConnection) conn;
            if (managedConn != null && managedConn.isOpen()) {
                Socket socket = managedConn.getSocket();
                String hostAddress = socket.getInetAddress().getHostAddress();
                context.setAttribute(HTTP_HOSTADDRESS, hostAddress);
            }
        }
    }

}
