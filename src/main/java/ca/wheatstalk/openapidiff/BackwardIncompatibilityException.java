package ca.wheatstalk.openapidiff;

import org.apache.maven.plugin.MojoFailureException;

class BackwardIncompatibilityException extends MojoFailureException {
    public BackwardIncompatibilityException(String message) {
        super(message);
    }
}
