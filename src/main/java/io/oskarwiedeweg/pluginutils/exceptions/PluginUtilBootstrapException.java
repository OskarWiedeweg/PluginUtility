package io.oskarwiedeweg.pluginutils.exceptions;

public abstract class PluginUtilBootstrapException extends PluginUtilException {

    public PluginUtilBootstrapException(String message) {
        super(message);
    }

    public PluginUtilBootstrapException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginUtilBootstrapException(Throwable cause) {
        super(cause);
    }
}
