package main.tasks;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class CResponse {
    private int action;
    private int transictionId;
    private byte[] connectionId;

    /**
     * @return the action
     */
    public CResponse(byte[] response) {
        this.connectionId = Arrays.copyOfRange(response, 8, 16);
        this.action = ByteBuffer.wrap(Arrays.copyOfRange(response, 0, 4)).getInt();
        this.transictionId = ByteBuffer.wrap(Arrays.copyOfRange(response, 4, 8)).getInt();
    }

    public int getAction() {
        return action;
    }

    /**
     * @return the transictionId
     */
    public int getTransictionId() {
        return transictionId;
    }

    /**
     * @return the connectionId
     */
    public byte[] getConnectionId() {
        return connectionId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Response [action=" + action + ", connectionId=" + Arrays.toString(connectionId) + ", transictionId="
                + transictionId + "]";
    }

}
