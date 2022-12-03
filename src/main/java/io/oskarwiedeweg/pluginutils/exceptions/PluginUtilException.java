package io.oskarwiedeweg.pluginutils.exceptions;

public abstract class PluginUtilException extends RuntimeException {

    public PluginUtilException() {
    }

    public PluginUtilException(String message) {
        super(message);
    }

    public PluginUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginUtilException(Throwable cause) {
        super(cause);
    }
}
