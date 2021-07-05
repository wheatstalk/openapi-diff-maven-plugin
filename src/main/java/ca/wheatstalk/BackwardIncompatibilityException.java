package ca.wheatstalk;

import org.apache.maven.plugin.MojoFailureException;

class BackwardIncompatibilityException extends MojoFailureException {
    public BackwardIncompatibilityException(String message) {
        super(message);
    }
}
